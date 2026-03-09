package top.annieholo;

import org.junit.jupiter.api.*;

public class UserServiceTest {

    @BeforeAll
    public static void beforeAll() {
        System.out.println("beforeAll");
    }

    @AfterAll
    public static void afterAll() {
        System.out.println("afterAll");
    }

    @BeforeEach
    public void beforeEach() {
        System.out.println("beforeEach");
    }
    @AfterEach
    public void afterEach() {
        System.out.println("afterEach");
    }
    @Test
    public void testGetAge(){
        UserService userService = new UserService();
        Integer age = userService.getAge("100000199607231011");
        System.out.println("年龄是："+age+"岁");
    }

    @Test
    public void testGetAgeWithAssert(){
        UserService userService = new UserService();
        Integer age = userService.getAge("100000199607231011");
        Assertions.assertEquals(29, age, "年龄获取逻辑有问题");
    }

    @Test
    public void testGetGender(){
        UserService userService = new UserService();
        String gender = userService.getGender("100000199607231011");
        Assertions.assertEquals("男", gender, "年龄获取逻辑有问题");
    }

}
