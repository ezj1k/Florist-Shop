package com.example.florarie.controller;

import com.example.florarie.entity.Floare;
import com.example.florarie.repository.FloareRepository;
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
@RequestMapping("/api/flori")
public class FloareController {

    private final FloareRepository floareRepository;

    public FloareController(FloareRepository floareRepository) { this.floareRepository = floareRepository; }

    @GetMapping
    public List<Floare> getAll() {
        return floareRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Floare> getById(@PathVariable Long id) {
        return floareRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Floare> create(@RequestBody Floare floare) {
        Floare saved = floareRepository.save(floare);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Floare> update(@PathVariable Long id, @RequestBody Floare floare) {
        if (!floareRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        floare.setFloareId(id);
        return ResponseEntity.ok(floareRepository.save(floare));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!floareRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        floareRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
