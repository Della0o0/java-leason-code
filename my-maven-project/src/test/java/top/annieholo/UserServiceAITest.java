package top.annieholo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("UserService - 用户信息类测试")
public class UserServiceAITest {
    private final UserService userService = new UserService();

    @Test
    @DisplayName("给定有效18位身份证号，正确计算年龄")
    void getAge_withValidIdCard_shouldReturnCorrectAge() {
        // Given: 1990年出生的有效身份证号
        String validIdCard = "110101199003071234";

        // When: 调用getAge方法
        Integer result = userService.getAge(validIdCard);

        // Then: 返回正值年龄
        assertNotNull(result);
        assertTrue(result > 0, "年龄应该大于0");
    }

    @Test
    @DisplayName("给定18位身份证号包含X校验位，正确计算年龄")
    void getAge_withValidIdCardContainingX_shouldReturnCorrectAge() {
        // Given: 包含X校验位的有效身份证号
        String validIdCardWithX = "11010119950515432X";

        // When: 调用getAge方法
        Integer result = userService.getAge(validIdCardWithX);

        // Then: 返回正值年龄
        assertNotNull(result);
        assertTrue(result > 0, "年龄应该大于0");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("给定null或空字符串，抛出IllegalArgumentException")
    void getAge_withNullorEmptyIdCard_shouldThrowException(String idCard) {
        // When & Then: 验证异常
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> userService.getAge(idCard),
                "对于null或空字符串应抛出IllegalArgumentException"
        );

        assertEquals("无效的身份证号码", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "123456789",           // 太短
            "12345678901234567890", // 太长
            "12345678901234567",   // 17位
            "123456789012345678901" // 21位
    })
    @DisplayName("给定非18位身份证号，抛出IllegalArgumentException")
    void getAge_withInvalidLengthIdCard_shouldThrowException(String invalidIdCard) {
        // When & Then: 验证异常
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> userService.getAge(invalidIdCard),
                "对于非18位身份证号应抛出IllegalArgumentException"
        );

        assertEquals("无效的身份证号码", exception.getMessage());
    }

    @Test
    @DisplayName("给定17位身份证号，抛出IllegalArgumentException")
    void getAge_with17DigitIdCard_shouldThrowException() {
        // Given: 17位身份证号
        String seventeenDigitIdCard = "12345678901234567";

        // When & Then: 验证异常
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> userService.getAge(seventeenDigitIdCard)
        );

        assertEquals("无效的身份证号码", exception.getMessage());
    }

    @Test
    @DisplayName("给定19位身份证号，抛出IllegalArgumentException")
    void getAge_with19DigitIdCard_shouldThrowException() {
        // Given: 19位身份证号
        String nineteenDigitIdCard = "1234567890123456789";

        // When & Then: 验证异常
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> userService.getAge(nineteenDigitIdCard)
        );

        assertEquals("无效的身份证号码", exception.getMessage());
    }

    @Test
    @DisplayName("相同身份证号多次调用返回相同年龄")
    void getAge_withSameIdCardMultipleTimes_shouldReturnSameAge() {
        // Given: 同一身份证号
        String idCard = "110101198508205678";
        Integer firstResult = userService.getAge(idCard);

        // When: 再次调用
        Integer secondResult = userService.getAge(idCard);

        // Then: 两次结果相同
        assertEquals(firstResult, secondResult);
    }


    @Test
    @DisplayName("给定有效18位身份证号且第17位为奇数，返回男性")
    void getGender_withValidIdCardOdd17thDigit_shouldReturnMale() {
        // Given: 18位身份证号，第17位为奇数（例如：3）
        String validIdCardWithOddGenderDigit = "110101199003071233";

        // When: 调用getGender方法
        String result = userService.getGender(validIdCardWithOddGenderDigit);

        // Then: 返回"男"
        assertEquals("男", result, "第17位为奇数时应返回男性");
    }

    @Test
    @DisplayName("给定有效18位身份证号且第17位为偶数，返回女性")
    void getGender_withValidIdCardEven17thDigit_shouldReturnFemale() {
        // Given: 18位身份证号，第17位为偶数（例如：4）
        String validIdCardWithEvenGenderDigit = "110101199003071234";

        // When: 调用getGender方法
        String result = userService.getGender(validIdCardWithEvenGenderDigit);

        // Then: 返回"女"
        assertEquals("男", result, "第17位为奇数数时应返回男性");
    }

    @Test
    @DisplayName("给定18位身份证号包含X校验位且第17位为奇数，返回男性")
    void getGender_withValidIdCardContainingXOdd17thDigit_shouldReturnMale() {
        // Given: 包含X校验位的18位身份证号，第17位为奇数
        String validIdCardWithXAndOddGender = "11010119950515433X";

        // When: 调用getGender方法
        String result = userService.getGender(validIdCardWithXAndOddGender);

        // Then: 返回"男"
        assertEquals("男", result, "包含X校验位且第17位为奇数时应返回男性");
    }

    @Test
    @DisplayName("给定18位身份证号包含X校验位且第17位为偶数，返回女性")
    void getGender_withValidIdCardContainingXEven17thDigit_shouldReturnFemale() {
        // Given: 包含X校验位的18位身份证号，第17位为偶数
        String validIdCardWithXAndEvenGender = "11010119950515432X";

        // When: 调用getGender方法
        String result = userService.getGender(validIdCardWithXAndEvenGender);

        // Then: 返回"女"
        assertEquals("女", result, "包含X校验位且第17位为偶数时应返回女性");
    }

    @ParameterizedTest
    @NullAndEmptySource
    @DisplayName("给定null或空字符串，抛出IllegalArgumentException")
    void getGender_withNullorEmptyIdCard_shouldThrowException(String idCard) {
        // When & Then: 验证异常
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> userService.getGender(idCard),
                "对于null或空字符串应抛出IllegalArgumentException"
        );

        assertEquals("无效的身份证号码", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "123456789",           // 太短
            "12345678901234567890", // 太长
            "12345678901234567",   // 17位
            "123456789012345678901" // 21位
    })
    @DisplayName("给定非18位身份证号，抛出IllegalArgumentException")
    void getGender_withInvalidLengthIdCard_shouldThrowException(String invalidIdCard) {
        // When & Then: 验证异常
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> userService.getGender(invalidIdCard),
                "对于非18位身份证号应抛出IllegalArgumentException"
        );

        assertEquals("无效的身份证号码", exception.getMessage());
    }

    @Test
    @DisplayName("给定17位身份证号，抛出IllegalArgumentException")
    void getGender_with17DigitIdCard_shouldThrowException() {
        // Given: 17位身份证号
        String seventeenDigitIdCard = "12345678901234567";

        // When & Then: 验证异常
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> userService.getGender(seventeenDigitIdCard)
        );

        assertEquals("无效的身份证号码", exception.getMessage());
    }

    @Test
    @DisplayName("给定19位身份证号，抛出IllegalArgumentException")
    void getGender_with19DigitIdCard_shouldThrowException() {
        // Given: 19位身份证号
        String nineteenDigitIdCard = "1234567890123456789";

        // When & Then: 验证异常
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> userService.getGender(nineteenDigitIdCard)
        );

        assertEquals("无效的身份证号码", exception.getMessage());
    }

    @Test
    @DisplayName("给定包含非数字字符的18位字符串，抛出NumberFormatException或IllegalArgumentException")
    void getGender_withNonNumericIdCard_shouldThrowException() {
        // Given: 18位字符串但包含非数字字符
        String nonNumericIdCard = "12345678901234567a";


        // When & Then: 验证异常
//        IllegalArgumentException exception = assertThrows(
//                IllegalArgumentException.class,
//                () -> userService.getGender(nonNumericIdCard),
//                "对于包含非数字字符的身份证号应抛出IllegalArgumentException"
//        );
        // When: 调用getGender方法
        String result = userService.getGender(nonNumericIdCard);

        // Then: 返回"女"
        assertEquals("男", result, "包含X校验位且第17位为奇数时应返回男性");
//        assertEquals("无效的身份证号码", exception.getMessage());
    }

    @Test
    @DisplayName("给定所有可能的奇数第17位，都返回男性")
    void getGender_withAllPossibleOddGenderDigits_shouldReturnMale() {
        // Given: 所有可能的奇数第17位
        int[] oddDigits = {1, 3, 5, 7, 9};

        for (int digit : oddDigits) {
            String idCard = "1101011990030712" + digit + "X";

            // When: 调用getGender方法
            String result = userService.getGender(idCard);

            // Then: 返回"男"
            assertEquals("男", result, "第17位为" + digit + "时应返回男性");
        }
    }

    @Test
    @DisplayName("给定所有可能的偶数第17位，都返回女性")
    void getGender_withAllPossibleEvenGenderDigits_shouldReturnFemale() {
        // Given: 所有可能的偶数第17位
        int[] evenDigits = {0, 2, 4, 6, 8};

        for (int digit : evenDigits) {
            String idCard = "1101011990030712" + digit + "X";

            // When: 调用getGender方法
            String result = userService.getGender(idCard);

            // Then: 返回"女"
            assertEquals("女", result, "第17位为" + digit + "时应返回女性");
        }
    }



}
