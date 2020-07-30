package com.edwin;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;

import javax.net.ssl.HandshakeCompletedEvent;
import java.io.File;
import java.io.FileNotFoundException;

@SpringBootTest
class EventMasterApplicationTests {

    @Test
    void contextLoads() throws FileNotFoundException {
//        System.out.println(ClassUtils.getDefaultClassLoader().getResource("static").getPath());
//        String filePath = request.getSession().getServletContext().getRealPath("imgupload/")
//
//        File path = new File(ResourceUtils.getURL("classpath:").getPath());
//        if(!path.exists()) {
//            path = new File("");
//        }
//        File upload = new File(path.getAbsolutePath(),"static/upload/");
//        if(!upload.exists()) {
//            upload.mkdirs();
//        }
//        FileUtils.copyInputStreamToFile(inputStream, uploadFile);
    }

}
