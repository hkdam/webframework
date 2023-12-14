package com.example.webframework.repository;

import com.example.webframework.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    Account findByIdAndName(int id, String name);

    @Modifying
    @Query(value = "update account set money=?2 where name=?1",nativeQuery = true)//开启原生SQL
    @Transactional
    int updateMoneyByName(String name,double money);


    @Query(value = "select a.name, a.money, c.city\n" +
            "from account as a\n" +
            "left join\n" +
            "city as c\n" +
            "on a.city_key = c.id\n" +
            "where c.city = :city_name",nativeQuery = true)//开启原生SQL
    List<Map<String, Object>> getInfoByCity(@Param("city_name") String city);
}
