package com.example.webframework.dao.helper;


import com.example.webframework.dao.entity.QUserEntity;
import com.example.webframework.dao.entity.UserEntity;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Expression;
import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.querydsl.jpa.impl.JPAUpdateClause;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class UserRepositoryHelper {

    @Resource
    private JPAQueryFactory jpaQueryFactory;

    // 引入querydsl的entity, 可以是多个
    private final QUserEntity qUserEntity = QUserEntity.userEntity;

    public List<UserEntity> findUserByUserName(String name){

        JPAQuery<UserEntity> jpaQuery = jpaQueryFactory.select(qUserEntity).from(qUserEntity);
        jpaQuery.where(qUserEntity.userName.eq(name));

        return jpaQuery.fetch();
    }

    public UserEntity findUserByUserId(int userId){

        // 如果没有的话返回null
//        JPAQuery<UserEntity> jpaQuery = jpaQueryFactory.selectFrom(qUserEntity).where(qUserEntity.userId.eq(userId));


        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qUserEntity.userId.eq(userId));
        JPAQuery<UserEntity> jpaQuery = jpaQueryFactory.selectFrom(qUserEntity).where(booleanBuilder);

        return jpaQuery.fetchOne();
    }


    public Tuple getUserSomeParmByUserId(int userId){

        List<Expression<?>> selectArgs = new ArrayList<>();
        selectArgs.add(qUserEntity.userId);
        selectArgs.add(qUserEntity.userName);

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qUserEntity.userId.eq(userId));
        JPAQuery<Tuple> jpaQuery = jpaQueryFactory.select(selectArgs.toArray(new Expression[]{}))
                .from(qUserEntity)
                .where(booleanBuilder);

        return jpaQuery.fetchOne();
    }


    // 返回值是多个tuple或者需要分页(包括返回的entity,不是tuple)的则用fetchResults()
    public QueryResults<Tuple> getMoreUserSomeParmByUserId(List<Integer> userIds){

        List<Expression<?>> selectArgs = new ArrayList<>();
        selectArgs.add(qUserEntity.userId);
        selectArgs.add(qUserEntity.userName);

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qUserEntity.userId.in(userIds));
        JPAQuery<Tuple> jpaQuery = jpaQueryFactory.select(selectArgs.toArray(new Expression[]{}))
                .from(qUserEntity)
                .where(booleanBuilder);

        return jpaQuery.fetchResults();
    }


    // 返回值是多个tuple或者需要分页(包括返回的entity,不是tuple)的则用fetchResults()
    public QueryResults<Tuple> getMoreUserSomeParmByUserIdWithPage(List<Integer> userIds, int pageNumber, int pageSize){

        List<Expression<?>> selectArgs = new ArrayList<>();
        selectArgs.add(qUserEntity.userId);
        selectArgs.add(qUserEntity.userName);

        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qUserEntity.userId.in(userIds));
        JPAQuery<Tuple> jpaQuery = jpaQueryFactory.select(selectArgs.toArray(new Expression[]{}))
                .from(qUserEntity)
                .where(booleanBuilder);

        int offset = (pageNumber - 1) * pageSize;

        return jpaQuery.offset(offset).limit(pageSize).fetchResults();
    }


    @Transactional
    public long updateUserByUserId(int userId, String password){

        Date date = new Date();
        JPAUpdateClause jpaUpdateClause = jpaQueryFactory.update(qUserEntity)
                .set(qUserEntity.password, password)
                .set(qUserEntity.updatedDate, date.toString())
                .where(qUserEntity.userId.eq(userId));

        return jpaUpdateClause.execute();
    }

    @Transactional
    public long deleteUserByUserId(int userId){

        JPADeleteClause jpaDeleteClause = jpaQueryFactory.delete(qUserEntity).where(qUserEntity.userId.eq(userId));

        return jpaDeleteClause.execute();
    }
}
