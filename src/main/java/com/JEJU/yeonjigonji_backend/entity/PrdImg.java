package com.JEJU.yeonjigonji_backend.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "prd_img")
public class PrdImg extends BaseTimeEntity{
    @Id
    @Column(name="prd_img_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String imgName; //이미지 파일명

    private String imgUri; //이미지 조회 경로 uri

    private String repImgYn; //대표 이미지 여부

    @OneToMany(mappedBy = "andImg", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<PrdDetailAndImg> detailItems = new ArrayList<>();
}
