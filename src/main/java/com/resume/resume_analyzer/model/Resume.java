package com.resume.resume_analyzer.model;
//This tells Java the location of this class in the project structure.
import lombok.Data;
//Lombok automatically creates: getters,setters,toString,equals,hashCode
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
//Maps this class to a MongoDB collection.

@Document(collection="resumes") //This means MongoDB will create a collection called:resumes
@Data //Automatically generates:getFileName(),setFileName(),getScore(),setScore()


public class Resume {
    @Id
//    MongoDB automatically generates this unique ID.
    private String id;

    private String fileName;

    private String resumeText;

    private String skills;

    private String missingSkills;
    private int score;
}
