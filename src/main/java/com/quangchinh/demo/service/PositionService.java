package com.quangchinh.demo.service;

import com.quangchinh.demo.dao.Position;

import java.util.List;
import java.util.Optional;

public interface PositionService {

    Position create(Position position);

    List<Position> getAll();

    Optional<Position> getById(String id);
}
