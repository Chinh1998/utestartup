package com.quangchinh.demo.controller;

import com.quangchinh.demo.dao.Position;
import com.quangchinh.demo.service.PositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/positions")
public class PositionController {

    private final PositionService positionService;

    @Autowired
    public PositionController(PositionService positionService) {
        this.positionService = positionService;
    }

    @GetMapping
    public List<Position> getAllPosition(){
        return positionService.getAll();
    }

    @GetMapping("/{id}")
    public Position getPosition(@PathVariable String id){
        Optional<Position> position =positionService.getById(id);
        return position.get();
    }

    @PostMapping
    public Position createPosition(@RequestBody Position position){
        return positionService.create(position);
    }
}
