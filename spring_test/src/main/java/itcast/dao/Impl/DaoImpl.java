package itcast.dao.Impl;

import itcast.dao.Dao;
import itcast.daomian.Role;
import itcast.daomian.User;
import itcast.daomian.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class DaoImpl implements Dao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public User login(String username,String password)throws Exception{
        User user = jdbcTemplate.queryForObject("select * from sys_user where username=? and password=?", new BeanPropertyRowMapper<User>(User.class), username, password);
        return user;
    }

    @Override
    public List<UserStatus> findUser() {
        List<UserStatus> list = jdbcTemplate.query("SELECT * from sys_user",
                new BeanPropertyRowMapper<UserStatus>(UserStatus.class));
        return list;
    }

    @Override
    public List<Role> findByID(String id1) {
        int id = Integer.parseInt(id1);
        List<Role> roles = jdbcTemplate.query("select * from sys_user_role ur,sys_role r where ur.roleId=r.id and ur.userId=?", new BeanPropertyRowMapper<>(Role.class), id);
        return roles;
    }

    @Override
    public List<Role> saveUI() {
        List<Role> roles = jdbcTemplate.query("select * from sys_role", new BeanPropertyRowMapper<>(Role.class));
        return roles;
    }

    @Override
    public void save(User user, long[] roleIds) {
        PreparedStatementCreator creator = new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement statement = con.prepareStatement("insert into sys_user value (?,?,?,?,?)",PreparedStatement.RETURN_GENERATED_KEYS);
                statement.setObject(1,null);
                statement.setString(2,user.getUsername());
                statement.setString(3,user.getEmail());
                statement.setString(4,user.getPassword());
                statement.setString(5,user.getPhoneNum());
                return statement;
            }
        };
        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(creator,holder);

        long userId = holder.getKey().longValue();

        for (long roleId : roleIds) {
            jdbcTemplate.update("insert into sys_user_role value (?,?)",userId,roleId);
        }
    }
}
