package com.quangchinh.demo.repository;

import com.quangchinh.demo.dao.Position;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionRepository extends JpaRepository<Position,String> {
}
