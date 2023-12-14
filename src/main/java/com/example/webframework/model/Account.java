package com.example.webframework.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="account")
public class Account {

    public Account(){}

    public Account(String name, double money){
        this.name = name;
        this.money = money;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)   //生成策略，这里配置为自增
    @Column(name = "id")    //对应表中id这一列
    @Id     //此属性为主键
    int id;

    @Column(name = "name")   //对应表中username这一列
    String name;

    @Column(name = "money")   //对应表中password这一列
    double money;

    //一对一 设置外面
    @JoinColumn(name = "city_key")
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL) //设置关联操作为ALL
    City city;
}
