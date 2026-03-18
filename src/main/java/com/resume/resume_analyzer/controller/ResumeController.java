package com.resume.resume_analyzer.controller;

import com.resume.resume_analyzer.model.Resume;
import com.resume.resume_analyzer.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "http://localhost:3000")

@RestController
//This class returns API responses (JSON)
@RequestMapping("/api/resumes")
public class ResumeController {
    @Autowired
    private ResumeService resumeService;

    @PostMapping("/save")
    public Resume saveResume(@RequestBody Resume resume) {
        return resumeService.saveResume(resume);
    }

    @PostMapping(value="/upload", consumes="multipart/form-data")
    public Resume uploadResume(@RequestParam("file") MultipartFile file) {

        String text = resumeService.extractTextFromPDF(file);

        String skills = resumeService.detectSkills(text);

        String missingSkills = resumeService.findMissingSkills(skills);

        int score = resumeService.calculateScore(skills);

        Resume resume = new Resume();

        resume.setFileName(file.getOriginalFilename());
        resume.setResumeText(text);
        resume.setSkills(skills);
        resume.setMissingSkills(missingSkills);
        resume.setScore(score);

        return resumeService.saveResume(resume);
    }
}
