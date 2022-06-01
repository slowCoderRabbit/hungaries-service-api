package com.hangaries.controller;

import com.hangaries.model.Module;
import com.hangaries.service.module.impl.ModuleServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api")
public class ModuleController {
    private static final Logger logger = LoggerFactory.getLogger(ModuleController.class);

    @Autowired
    ModuleServiceImpl moduleService;

    @GetMapping("getAllModule")
    public ResponseEntity<List<Module>> getAllModule() {
        logger.info("Getting list of all Modules...");

        List<Module> moduleList = new ArrayList<>();
        try {
            moduleList = moduleService.getAllModule();
            return new ResponseEntity<List<Module>>(moduleList, HttpStatus.OK);
        } catch (Exception ex) {
            logger.error("Error while getting store details::" + ex.getMessage());
            return new ResponseEntity<List<Module>>(moduleList, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}