package com.JEJU.yeonjigonji_backend.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.annotation.Nullable;
import javax.persistence.*;

@Entity
@Getter @Setter
@ToString
@Table(name="item_img")
public class ItemImg extends BaseTimeEntity {
    @Id
    @Column(name="item_img_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String imgName; //이미지 파일명

    private String imgUrl; //이미지 조회 경로

    private String repImgYn; //대표 이미지 여부

    @ManyToOne(fetch = FetchType.LAZY) //다대일 방향으로 매핑
    @JoinColumn(name = "item_id")
    private Item item;

}
