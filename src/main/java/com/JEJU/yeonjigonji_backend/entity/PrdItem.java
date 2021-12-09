package com.JEJU.yeonjigonji_backend.entity;

import lombok.Data;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "prd_items")
public class PrdItem extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prd_id")
    private Long id;

    private String untNm; // 브랜드제품명색상명

    private String color; //색상 코드값

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "prd_detail_id")
    private PrdDetailItem prdDetailItem;

    @OneToMany(mappedBy = "item", fetch = FetchType.LAZY, cascade = CascadeType.ALL) // item:like = 1:n 관계
    private List<LikeItem> likes = new ArrayList<>();

}