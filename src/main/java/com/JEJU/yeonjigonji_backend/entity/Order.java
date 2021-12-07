package com.JEJU.yeonjigonji_backend.entity;

import com.JEJU.yeonjigonji_backend.constant.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY) // 회원은 여러번의 주문을 할 수 있으므로
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime orderDate; // 주문일

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus; // 주문 상태

    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    // OrderItem의 Order에 의해 관리, 외래키가 order_item 테이블에 있으므로 연관관계 주인은 order_item
    // 부모의 변화 자식에게 모두 전이(cascade)
    private List<OrderItem> orderItems = new ArrayList<>();
}
