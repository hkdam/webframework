package com.example.webframework.dao.repository;

import com.example.webframework.dao.entity.UserEntity;
import com.example.webframework.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer>, JpaSpecificationExecutor<UserRepository> {

    UserEntity findByUserName(String UserName);

    @Modifying
    @Transactional
    @Query(value = "update users set password = ?2, updated_date = CURRENT_TIMESTAMP" +
            " where user_name = ?1", nativeQuery = true)
    void updatePasswordByUserName(String UserName, String newPassword);
}
