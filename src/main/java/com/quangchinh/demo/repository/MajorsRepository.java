package com.quangchinh.demo.repository;

import com.quangchinh.demo.dao.Majors;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MajorsRepository extends JpaRepository<Majors, String> {
}
