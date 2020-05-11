package com.quangchinh.demo.service;

import com.quangchinh.demo.dao.Position;
import com.quangchinh.demo.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PositionServiceImpl implements PositionService {

    private final PositionRepository positionRepository;

    @Autowired
    public PositionServiceImpl(PositionRepository positionRepository) {
        this.positionRepository = positionRepository;
    }

    @Override
    public Position create(Position position) {
        return positionRepository.save(position);
    }

    @Override
    public List<Position> getAll() {
        return positionRepository.findAll();
    }

    @Override
    public Optional<Position> getById(String id) {
        return positionRepository.findById(id);
    }
}
