package com.JEJU.yeonjigonji_backend.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class PrdDetailAndImg extends BaseTimeEntity{ // prdDetailItem : PrdImg = N : M -> 중간 매체 필요
    @Id
    @Column(name = "prdDetail_and_img_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "prd_detail_id")
    private PrdDetailItem andItem;

    @ManyToOne
    @JoinColumn(name = "prd_img_id")
    private PrdImg andImg;
}
