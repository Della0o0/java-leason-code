package top.annieholo.service;

import top.annieholo.pojo.User;

import java.util.List;

public interface UserService {
    /**
     * 返回所有用户信息
     * @return
     */
    public List<User> findAll();
}
