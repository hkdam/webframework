package com.example.webframework.model;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="city")
public class City {

    public City(){}

    public City(String name, String city){
        this.name = name;
        this.city = city;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)   //生成策略，这里配置为自增
    @Column(name = "id")    //对应表中id这一列
    @Id     //此属性为主键
    int id;

    @Column(name = "name")   //对应表中username这一列
    String name;

    @Column(name = "city")
    String city;
}
