package itcast.dao;

import itcast.daomian.Role;
import itcast.daomian.User;
import itcast.daomian.UserStatus;

import java.util.List;

public interface Dao {
    //登陆程序
    public User login(String username,String password) throws Exception;
    //查询用户
    public List<UserStatus> findUser();
    //根据查询出来的用户id，来查询他所具有的角色
    public List<Role> findByID(String id);
    //查询数据库所具有的所有角色信息
    public List<Role> saveUI();
    //将数据进行保存到两张关系表
    public void save(User user,long[] roleIds);
}
