package com.example.controller;

import com.example.dbConnector.DbConnector;
import org.apache.juli.logging.Log;
import org.apache.juli.logging.LogFactory;
import org.apache.tomcat.jni.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Configuration
@RequestMapping("/fileUpload")
public class FileUploadController {

    static final Log LOG = LogFactory.getLog(EmployeeController.class);

    @Autowired
    DbConnector dbConnector;

    @PostMapping
     public ResponseEntity<?> fileUpload(@RequestParam("file") MultipartFile file) {

        try{
            LOG.info("File Name " + file.getOriginalFilename());
            dbConnector.uploadFile(file);
        }
        catch (Exception e){
            System.out.println(e);
        }

        return ResponseEntity.accepted().body("File uploaded successfully");
    }

    @PostMapping("/multi")
    public void multiFileUpload(@RequestParam("file") MultipartFile[] files) {

        try{

            for(MultipartFile file : files){
                dbConnector.uploadFile(file);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }
    }
}
