package com.example.florarie.controller;

import com.example.florarie.entity.OrderLine;
import com.example.florarie.entity.OrderLineId;
import com.example.florarie.repository.OrderLineRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/order-lines")
public class OrderLineController {

    private final OrderLineRepository orderLineRepository;

    public OrderLineController(OrderLineRepository orderLineRepository) {
        this.orderLineRepository = orderLineRepository;
    }

    @GetMapping
    public List<OrderLine> getAll() {
        return orderLineRepository.findAll();
    }

    @GetMapping("/{orderId}/{lineId}")
    public ResponseEntity<OrderLine> getById(@PathVariable Long orderId, @PathVariable Long lineId) {
        OrderLineId id = new OrderLineId(orderId, lineId);
        return orderLineRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OrderLine> create(@RequestBody OrderLine orderLine) {
        OrderLine saved = orderLineRepository.save(orderLine);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @DeleteMapping("/{orderId}/{lineId}")
    public ResponseEntity<Void> delete(@PathVariable Long orderId, @PathVariable Long lineId) {
        OrderLineId id = new OrderLineId(orderId, lineId);
        if (!orderLineRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        orderLineRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
