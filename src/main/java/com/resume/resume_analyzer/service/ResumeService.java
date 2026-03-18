package com.resume.resume_analyzer.service;

import com.resume.resume_analyzer.model.Resume;
import com.resume.resume_analyzer.repository.ResumeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.List;

@Service
public class ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;

    public Resume saveResume(Resume resume) {
        return resumeRepository.save(resume);
    }

    public String extractTextFromPDF(MultipartFile file) {

        try {

            PDDocument document = PDDocument.load(file.getInputStream());

            PDFTextStripper stripper = new PDFTextStripper();

            String text = stripper.getText(document);

            document.close();

            return text;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }

    private List<String> skillDatabase = Arrays.asList(
            "java",
            "spring boot",
            "react",
            "mongodb",
            "mysql",
            "javascript",
            "html",
            "css",
            "docker",
            "kubernetes",
            "aws",
            "rest api"
    );

    public String detectSkills(String resumeText) { //Arrays.asList() converts values into a List object.

        List<String> detectedSkills = new ArrayList<>();

        String text = resumeText.toLowerCase();

        for (String skill : skillDatabase) {

            if (text.contains(skill)) {
                detectedSkills.add(skill);
            }

        }

        return String.join(",", detectedSkills);
    }

    public String findMissingSkills(String detectedSkills) {

        List<String> missingSkills = new ArrayList<>();

        List<String> detectedList = Arrays.asList(detectedSkills.split(","));

        for (String skill : skillDatabase) {

            if (!detectedList.contains(skill)) {
                missingSkills.add(skill);
            }

        }

        return String.join(",", missingSkills);
    }

    public int calculateScore(String detectedSkills){

        if(detectedSkills == null || detectedSkills.isEmpty()){
            return 0;
        }

        String[] skillsArray = detectedSkills.split(",");

        int detectedCount = skillsArray.length;

        int totalSkills = skillDatabase.size();

        int score = (detectedCount * 100) / totalSkills;

        return score;
    }
}