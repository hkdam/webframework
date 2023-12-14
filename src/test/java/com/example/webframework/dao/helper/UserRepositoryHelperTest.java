package com.example.webframework.dao.helper;

import com.example.webframework.dao.entity.QUserEntity;
import com.example.webframework.dao.entity.UserEntity;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryHelperTest extends TestCase {

    @Resource
    UserRepositoryHelper userRepositoryHelper;

    @Test
    public void test1(){

        List<UserEntity> userEntitys = userRepositoryHelper.findUserByUserName("dhk-5");
        System.out.println(userEntitys);
    }


    @Test
    public void test2(){

        UserEntity userEntity = userRepositoryHelper.findUserByUserId(8);
        System.out.println(userEntity);
    }


    @Test
    public void test3(){

        System.out.println("result: " + userRepositoryHelper.updateUserByUserId(4, "dhkdhdk"));
    }


    @Test
    public void test4(){

        System.out.println("result: " + userRepositoryHelper.deleteUserByUserId(4));
    }

    @Test
    public void test5(){
        Tuple tuple = userRepositoryHelper.getUserSomeParmByUserId(10);
        System.out.println("userId: " + tuple.get(QUserEntity.userEntity.userId));
        System.out.println("userName: " + tuple.get(QUserEntity.userEntity.userName));
    }


    @Test
    public void test6(){

        List<Integer> ids = new ArrayList<>();
        ids.add(4);
        ids.add(5);
        ids.add(6);
        QueryResults<Tuple> queryResults = userRepositoryHelper.getMoreUserSomeParmByUserId(ids);
        List<Tuple> result = queryResults.getResults();
        for(Tuple tuple: result){
            System.out.println("-----------------------------------");
            System.out.println("userId: " + tuple.get(QUserEntity.userEntity.userId));
            System.out.println("userName: " + tuple.get(QUserEntity.userEntity.userName));
        }
    }


    @Test
    public void test7(){

        List<Integer> ids = new ArrayList<>();
        ids.add(4);
        ids.add(5);
        ids.add(6);
        QueryResults<Tuple> queryResults = userRepositoryHelper.getMoreUserSomeParmByUserIdWithPage(ids, 3, 3);
        List<Tuple> result = queryResults.getResults();
        for(Tuple tuple: result){
            System.out.println("-----------------------------------");
            System.out.println("userId: " + tuple.get(QUserEntity.userEntity.userId));
            System.out.println("userName: " + tuple.get(QUserEntity.userEntity.userName));
        }
    }
}