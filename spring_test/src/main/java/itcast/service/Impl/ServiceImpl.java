package itcast.service.Impl;

import itcast.dao.Dao;
import itcast.daomian.Role;
import itcast.daomian.User;
import itcast.daomian.UserStatus;
import itcast.service.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class ServiceImpl implements Service{
    @Autowired
    private Dao dao;
    @Override
    public User login(String username, String password) throws Exception {
        User login = dao.login(username, password);
        return login;
    }

    @Override
    public List<UserStatus> findUser() {
        List<UserStatus> user = dao.findUser();
        for (UserStatus userStatus : user) {
            String id = userStatus.getId();
            List<Role> byID = dao.findByID(id);
            userStatus.setRoles(byID);
        }
        return user;
    }

    @Override
    public List<Role> saveUI() {
        return dao.saveUI();
    }

    @Override
    public void save(User user,long[] roleIds) {
        dao.save(user,roleIds);
    }
}
