package top.annieholo.dao.impl;

import cn.hutool.core.io.IoUtil;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import top.annieholo.dao.UserDao;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;





//@Component

@Repository()
//@Repository("aaaa")
public class UserDaoImpl implements UserDao {

    @Override
    public List<String> findAll() {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("user.txt");
        //InputStream in = new FileInputStream(new File("/Users/tangyulian/Documents/Study/JAVA/web-project/springboot-web-01/src/main/resources/user.txt"));
        ArrayList<String> lines = IoUtil.readLines(in, StandardCharsets.UTF_8, new ArrayList<>());
        return lines;
    }
}
