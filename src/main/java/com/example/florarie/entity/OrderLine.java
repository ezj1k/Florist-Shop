package com.example.florarie.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "order_line")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderLine {

    @EmbeddedId
    private OrderLineId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    @JsonIgnore
    @ToString.Exclude
    private OrderHeader orderHeader;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "floare_id", nullable = false)
    @ToString.Exclude
    private Floare floare;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;
}
