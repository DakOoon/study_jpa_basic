package jpabasic.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue // default strategy auto
    @Column(name = "order_id")
    private Long id;

//    @Column(name = "member_id")
//    private Long memberId;
    @ManyToOne(fetch =  FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Member getMember() {
        return member;
    }

    public void changeMember(Member member) {
        this.member = member;
        member.getOrderList().add(this);
    }
}
