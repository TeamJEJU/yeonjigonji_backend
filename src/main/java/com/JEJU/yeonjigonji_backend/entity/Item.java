package com.JEJU.yeonjigonji_backend.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
@ToString
@Table(name = "items") //밑에서 1:n 관계 매핑 정렬 기준을 "item"으로 지정했기 때문에 복수형 사용
public class Item extends BaseTimeEntity {

    @Id
    @Column(name = "item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name; //아이템명('브랜드제품명색상명' 형태)

    private String brand; //브랜드명

    private int price; //가격

    private String color; //색상 코드값

    @OneToMany(mappedBy = "item") // item:like = 1:n 관계
    private List<LikeItem> likes = new ArrayList<>();

    @OneToMany(mappedBy = "item") // item:itemImg = 1:n 관계
    private List<ItemImg> itemImgs = new ArrayList<>();

}
