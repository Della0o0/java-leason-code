package top.annieholo;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ResponseController {

    /*
    方式一、HttpServletResponse
     */
    @RequestMapping("/response")
    public void response(HttpServletResponse response) throws IOException {

        //1、设置状态码
        response.setStatus(401);

        //2、设置响应头
        response.setHeader("name", "ANNIE");

        //3、设置响应体
        response.getWriter().write("<h1>报错了annie，401</h1>");
    }

    /*
    方式二 ResponseEntity
     */
    @RequestMapping("/response2")
    public ResponseEntity<String> response2(){
        return ResponseEntity
                .status(401)
                .header("name2", "ANNIE2")
                .body("<h1>ResponseEntity</h1>");
    }
}
