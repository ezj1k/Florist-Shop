package com.example.florarie.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "floare")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Floare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "floare_id")
    private Long floareId;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "specification", length = 255)
    private String specification;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;
}
