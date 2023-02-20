package jpabasic.domain;

import jakarta.persistence.*;

@Entity
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @Embedded
    private Address address;

    private DeliveryStatus status;

    @OneToOne(mappedBy = "delivery")
    private Order order;
}
