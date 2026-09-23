package com.example.florarie.repository;

import com.example.florarie.entity.OrderLine;
import com.example.florarie.entity.OrderLineId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface OrderLineRepository extends JpaRepository<OrderLine, OrderLineId> {
}
