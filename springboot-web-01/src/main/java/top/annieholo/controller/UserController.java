package top.annieholo.controller;

import cn.hutool.core.io.IoUtil;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.annieholo.pojo.User;
import top.annieholo.service.UserService;
import top.annieholo.service.impl.UserServiceImpl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


/**
 * 用户信息Controller
 */
@RestController
public class UserController {

    //1、第一种方式，属性注入
    //@Qualifier("userServiceImpl2")
    //@Autowired
    //private UserService userService;

    @Resource(name = "userServiceImpl")
    private UserService userService;

    //2、第二种方式，构造器注入 测试git
    //private final UserService userService;
    //@Autowired // 当前类中只存在一个构造函数，可以省略
    //public UserController(UserService userService) {
    //    this.userService = userService;
    //}
    //第二次提交

    //3、第三种方式、setter注入
    //private UserService userService;
    //@Autowired
    //public void setUserService(@Qualifier("userServiceImpl") UserService userService) {
    //    this.userService = userService;
    //}

    @RequestMapping("/list")
    public List<User> list() throws FileNotFoundException {
        //1\调用service获取数据
        List<User> userList = userService.findAll();

        return userList;
    }
}
