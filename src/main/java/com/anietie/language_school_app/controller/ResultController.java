package com.anietie.language_school_app.controller;

import com.anietie.language_school_app.DTO.ResultDTO;
import com.anietie.language_school_app.model.Results;
import com.anietie.language_school_app.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/result")
public class ResultController {

    private final ResultService resultService;

    @Autowired
    public ResultController(ResultService resultService) {
        this.resultService = resultService;
    }

    @PostMapping("/addResult")
    public ResponseEntity<Results> postResult(@RequestBody ResultDTO result){
        try{
            Results newResult = this.resultService.postResult(result);
            return new ResponseEntity<>(newResult, HttpStatus.OK);
        }catch (DuplicateKeyException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
}
