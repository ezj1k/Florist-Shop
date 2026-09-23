package com.example.florarie.repository;

import com.example.florarie.entity.Floare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FloareRepository extends JpaRepository<Floare, Long> {
}
