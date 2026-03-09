package top.annieholo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // 表示当前类是一个处理请求类
public class HelloController {


    @RequestMapping("/hello")
    public String hello(String name){
        System.out.println("name:"+name);
        ////获取请求头 - Accept
        //String accept = request.getHeader("Accept");

        return "Hello, " + name + "~";
    }
}
