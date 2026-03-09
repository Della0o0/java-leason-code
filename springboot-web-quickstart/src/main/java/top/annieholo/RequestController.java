package top.annieholo;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RequestController {
    @RequestMapping("/request")
    public String request(HttpServletRequest request){
    //    1、获取请求方式
        String method = request.getMethod();
        System.out.println("method:"+method);

    //    2、获取请求url地址
        String url = request.getRequestURL().toString();
        System.out.println("请求地址url："+url);

    //    获取请求uri地址
        String uri = request.getRequestURI();
        System.out.println("请求uri："+uri);

    //    3、获取请求协议
        String protocol = request.getProtocol();
        System.out.println("请求协议："+protocol);

    //    4、获取请求参数age
        String age = request.getParameter("age");
        System.out.println("请求参数age："+age);


    //   5、获取请求头
        String accept = request.getHeader("Accept");
        System.out.println("请求头Accept："+accept);

        return "OK";
    }
}
