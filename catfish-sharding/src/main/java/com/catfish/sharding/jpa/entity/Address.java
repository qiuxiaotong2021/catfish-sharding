package com.catfish.sharding.jpa.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 订单
 *
 * Created by qiuxiaotong on 2021/8/22
 */
@Entity
@Table(name = "t_address")
@Data
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    public Address(String name){
        this.name = name;
    }
}