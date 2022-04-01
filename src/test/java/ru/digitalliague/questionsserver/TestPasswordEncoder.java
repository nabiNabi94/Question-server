package ru.digitalliague.questionsserver;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class TestPasswordEncoder {

    @Autowired
    BCryptPasswordEncoder passwordEncoder;
    @Test
    public void testEqualsPasswordFromDBWithPasswordFromRequest(){
      Assertions.assertTrue(
              passwordEncoder
                      .matches("Vladislav ",
                              "$2a$10$mhiNW4DxCe7X3Vfwv.BEU.PBvO697MU7V7UagogZhN/dGYCQ1WQWG"));
    }
}
