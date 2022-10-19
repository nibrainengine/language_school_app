package com.anietie.language_school_app.service;

import com.anietie.language_school_app.DTO.ResultDTO;
import com.anietie.language_school_app.model.Course;
import com.anietie.language_school_app.model.Results;
import com.anietie.language_school_app.model.Student;
import com.anietie.language_school_app.repository.CourseRepo;
import com.anietie.language_school_app.repository.ResultRepo;
import com.anietie.language_school_app.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ResultService {
    private final ResultRepo resultRepo;
    private final StudentRepo studentRepo;
    private final CourseRepo courseRepo;

    @Autowired
    public ResultService(ResultRepo resultRepo, StudentRepo studentRepo,
                         CourseRepo courseRepo){
        this.resultRepo = resultRepo;
        this.studentRepo = studentRepo;
        this.courseRepo = courseRepo;
    }

    @Transactional
    public Results postResult(ResultDTO result) throws Exception{
        try{
            Student student = studentRepo.findById(result.getStudentId()).orElseThrow(
                    () -> new RuntimeException("student not available"));
            Course course = courseRepo.findById(result.getCourseId()).orElseThrow(() ->
                    new RuntimeException("course code error"));
            Boolean isResultAvailable = resultRepo.existsByStudentAndCourse(
                    student, course);
            if(isResultAvailable) {
                throw new DuplicateKeyException("result Already posted");
            }else {
                Results examResult = new Results();
                examResult.setExamScore(result.getExamScore());
                examResult.setExamDate(result.getExamDate());
                examResult.setCourse(course);
                examResult.setStudent(student);
                Results newResult = resultRepo.save(examResult);
                return newResult;
            }
        }
        catch (DuplicateKeyException e) {
            throw new DuplicateKeyException(e.getMessage());
        }catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
