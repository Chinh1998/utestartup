package com.quangchinh.demo.service;

import com.quangchinh.demo.dao.Majors;

import java.util.List;

public interface MajorsService {

    Majors create(Majors majors);

    List<Majors> getAll();

    Majors getById(String id);

    Majors updateMajors(Majors majors);

    boolean deleteMajors(String id);
}
