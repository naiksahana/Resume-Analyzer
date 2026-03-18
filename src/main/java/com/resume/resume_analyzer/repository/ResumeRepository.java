package com.resume.resume_analyzer.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.resume.resume_analyzer.model.Resume;
public interface ResumeRepository extends MongoRepository<Resume, String> {
    //Resume → document type
    //String → ID type

    //Spring automatically provides functions like:save(),findAll(),findById(),deleteById()
}
