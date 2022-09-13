package com.anietie.language_school_app.controller;

import com.anietie.language_school_app.model.Course;
import com.anietie.language_school_app.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/course")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/add")
    public ResponseEntity<Course> registerCourse(@RequestBody Course course) {
        try {
            Course newCourse = courseService.registerNewCourse(course);
            return new ResponseEntity<>(newCourse, HttpStatus.OK);
        }
        catch (DuplicateKeyException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/allcourses")
    public ResponseEntity<List<Course>> getAllCourses() {
        try{
            List<Course> allCourses =  courseService.getAllCourses();
            return new ResponseEntity<>(allCourses, HttpStatus.OK);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/delete/{courseId}")
    public ResponseEntity<String> deleteCourse(@PathVariable("courseId") Long courseId) {
        try {
            String deleteResponse = courseService.deleteCourse(courseId);
            return new ResponseEntity<>(deleteResponse, HttpStatus.OK);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }



}
