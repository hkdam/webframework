package com.example.webframework.dao.repository;

import com.example.webframework.dao.entity.UserEntity;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.test.context.junit4.SpringRunner;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
//@EnableJpaAuditing
public class UserRepositoryTest extends TestCase {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test1(){

//        UserEntity userEntity = userRepository.findByUserName("dhk");
//        System.out.println(userEntity);

        UserEntity userEntity = new UserEntity();
        userEntity.setUserName("dhk-3");
        userEntity.setPassword("1233");

        UserEntity userEntity2 = new UserEntity();
        userEntity2.setUserName("dhk-5");
        userEntity2.setPassword("1233");

//        userRepository.saveAndFlush(userEntity);
        List<UserEntity> list = new ArrayList<>();
        list.add(userEntity);
        list.add(userEntity2);

        Date date = new Date();
        String dateStr = date.toString();

        list.add(new UserEntity().setUserName("qq").setPassword("q123").setCreatedDate(dateStr).setUpdatedDate(dateStr));
        list.add(new UserEntity().setUserName("ww").setPassword("w123").setCreatedDate(dateStr).setUpdatedDate(dateStr));
        list.add(new UserEntity().setUserName("ee").setPassword("e123").setCreatedDate(dateStr).setUpdatedDate(dateStr));
        list.add(new UserEntity().setUserName("rr").setPassword("r123").setCreatedDate(dateStr).setUpdatedDate(dateStr));

        System.out.println(userRepository.saveAll(list));

        // userRepository.saveAll(List<UserEntity> a);
        // deleteInBatch();
    }


    @Test
    public void findTest(){
        UserEntity userEntity = userRepository.findByUserName("dhk-2");
        System.out.println(userEntity);
    }


    @Test
    public void updateTest(){
        userRepository.updatePasswordByUserName("dhk-2", "988888");
    }
}