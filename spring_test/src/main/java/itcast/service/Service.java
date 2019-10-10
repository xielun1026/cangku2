package itcast.service;

import itcast.daomian.Role;
import itcast.daomian.User;
import itcast.daomian.UserStatus;

import java.util.List;

public interface Service {
    //登陆界面
    public User login(String username,String password) throws Exception;
    //查询所有的用户
    public List<UserStatus> findUser();
    //查询用户所具备的所有角色
    public List<Role> saveUI();
    //将用户添加完后保存到后台数据库
    public void save(User user,long[] roleIds);
}
