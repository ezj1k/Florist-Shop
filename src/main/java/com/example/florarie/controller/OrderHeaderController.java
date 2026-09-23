package com.example.florarie.controller;

import com.example.florarie.entity.OrderHeader;
import com.example.florarie.repository.OrderHeaderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/comenzi")
public class OrderHeaderController {

    private final OrderHeaderRepository orderHeaderRepository;

    public OrderHeaderController(OrderHeaderRepository orderHeaderRepository) {
        this.orderHeaderRepository = orderHeaderRepository;
    }

    @GetMapping
    public List<OrderHeader> getAll() {
        return orderHeaderRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderHeader> getById(@PathVariable Long id) {
        return orderHeaderRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<OrderHeader> create(@RequestBody OrderHeader orderHeader) {
        OrderHeader saved = orderHeaderRepository.save(orderHeader);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderHeader> update(@PathVariable Long id, @RequestBody OrderHeader orderHeader) {
        if (!orderHeaderRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        orderHeader.setOrderId(id);
        return ResponseEntity.ok(orderHeaderRepository.save(orderHeader));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!orderHeaderRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        orderHeaderRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
