package com.example.florarie.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

/**
 * Cheia compusa pentru OrderLine: (order_id, line_id),
 * conform diagramei relationale (OrderID + LineID).
 */
@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineId implements Serializable {

    private Long orderId;
    private Long lineId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderLineId)) return false;
        OrderLineId that = (OrderLineId) o;
        return Objects.equals(orderId, that.orderId) && Objects.equals(lineId, that.lineId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, lineId);
    }
}
