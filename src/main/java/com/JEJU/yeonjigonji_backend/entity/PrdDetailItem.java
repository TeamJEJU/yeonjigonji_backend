package com.JEJU.yeonjigonji_backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "prd_detail_items")
public class PrdDetailItem extends BaseTimeEntity{
    @Id
    @Column(name = "prd_detail_id")
    private Long id;

    private String brandNm; // 브랜드명

    @Column(name = "sl_pc")
    private int price; //가격

    @OneToOne(mappedBy = "prdDetailItem")
    private PrdItem prdItem;

    @OneToMany(mappedBy = "andItem", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PrdDetailAndImg> imgs = new ArrayList<>();
}
