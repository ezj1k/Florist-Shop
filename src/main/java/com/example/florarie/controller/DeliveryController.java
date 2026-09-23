package com.example.florarie.controller;

import com.example.florarie.entity.Delivery;
import com.example.florarie.repository.DeliveryRepository;
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
@RequestMapping("/api/livrari")
public class DeliveryController {

    private final DeliveryRepository deliveryRepository;

    public DeliveryController(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @GetMapping
    public List<Delivery> getAll() {
        return deliveryRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Delivery> getById(@PathVariable Long id) {
        return deliveryRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Delivery> create(@RequestBody Delivery delivery) {
        Delivery saved = deliveryRepository.save(delivery);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Delivery> update(@PathVariable Long id, @RequestBody Delivery delivery) {
        if (!deliveryRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        delivery.setDeliveryId(id);
        return ResponseEntity.ok(deliveryRepository.save(delivery));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!deliveryRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        deliveryRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
