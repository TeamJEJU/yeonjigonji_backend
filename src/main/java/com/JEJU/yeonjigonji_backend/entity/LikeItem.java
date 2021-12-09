package com.JEJU.yeonjigonji_backend.entity;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;

@Entity
@Getter @Setter
@Table(name="like_item")
public class LikeItem extends BaseTimeEntity {

    @Id
    @Column(name = "like_item_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @ManyToOne(fetch = FetchType.LAZY) // like:item = n:1 관계
//    @JoinColumn(name = "item_id")
//    private Item item;

    @ManyToOne(fetch = FetchType.LAZY) // like:item = n:1 관계
    @JoinColumn(name = "prd_id")
    private PrdItem item;

    @ManyToOne(fetch = FetchType.LAZY) // like:member = n:1 관계
    @JoinColumn(name = "member_id")
    private Member member;

    public static LikeItem createLike(PrdItem item, Member member) {
        LikeItem like = new LikeItem();
        like.setItem(item);
        like.setMember(member);
        return like;
    }

}
