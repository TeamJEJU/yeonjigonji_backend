package com.JEJU.yeonjigonji_backend.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter @Setter
@ToString
@Table(name = "item")
public class Item extends BaseTimeEntity {

    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name; //아이템명('브랜드제품명색상명' 형태)

    private String brand; //브랜드명

    private int price; //가격

    private String color; //색상 코드값


}
