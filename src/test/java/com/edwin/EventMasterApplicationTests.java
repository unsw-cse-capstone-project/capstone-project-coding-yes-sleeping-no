package com.edwin;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ClassUtils;

@SpringBootTest
class EventMasterApplicationTests {

    @Test
    void contextLoads() {
        System.out.println(ClassUtils.getDefaultClassLoader().getResource("static").getPath());
    }

}
