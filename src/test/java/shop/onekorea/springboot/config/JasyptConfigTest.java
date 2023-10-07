/**
 * jasypt 암호화 작업
 */
package shop.onekorea.springboot.config;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.junit.jupiter.api.Test;

import static java.awt.SystemColor.info;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class JasyptConfigTest {

    @Test
    public void jasyptTest() throws Exception {
//        String value = "*963210z"; // Database Password. 데이터베이스 암호를 "암호화" 한다.
//        String value = "sa"; // Database username. 데이터베이스 사용자 이름을 "암호화" 한다.
        String value = "com.mysql.cj.jdbc.Driver"; // driver-class-name를 "암호화" 한다.

        /**
         * String value = "jdbc:mysql://localhost:3306/powerapp?serverTimezone=UTC&characterEncoding=UTF-8"; // url도 암호화 할 수 있다.
         * => FDsnvImt/YaPpCisNLzh8Fx8c6sXl6a0AIp9CLTlIWzj+kh2MUHDYeTELnJyLHg8ObMtGLW7p4zfLnG3Z/Qkm1sKTPkILJPRMIcQzE7Q14qffw6oFFuVsw==
         * String value = "sa"; // username도 암호화 할 수 있다.
         *          * => FDsnvImt/YaPpCisNLzh8Fx8c6sXl6a0AIp9CLTlIWzj+kh2MUHDYeTELnJyLHg8ObMtGLW7p4zfLnG3Z/Qkm1sKTPkILJPRMIcQzE7Q14qffw6oFFuVsw==
         */
        String result = jasyptEncoding(value);
        System.err.println("---{}---" + result);
//        log.info("--------------------------------" + result);
//        log.debug("---{}---". result);

    }


    public String jasyptEncoding(String value) {
        String key = "Tbd+cUcW2wYHgrInS4S7s63SQ8hvSdFIgiOTLSPAwN3HQ2l98ZnecMDZsNen++2z"; // JasyptConfig.java 키 값.
        StandardPBEStringEncryptor pbeEnc = new StandardPBEStringEncryptor();
        pbeEnc.setAlgorithm("PBEWITHMD5ANDDES");
        pbeEnc.setPassword(key);
        return pbeEnc.encrypt(value);
    }
}