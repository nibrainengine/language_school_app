package com.anietie.language_school_app.service;

import com.anietie.language_school_app.model.Course;
import com.anietie.language_school_app.repository.CourseRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    private CourseRepo courseRepo;

    @Autowired
    public CourseService(CourseRepo courseRepo) {
        this.courseRepo = courseRepo;
    }

    public Course registerNewCourse(Course course) throws Exception {
        Boolean courseExists = courseRepo.findByType(course.getType()).isPresent();
        try {
            if (!courseExists) {
                return courseRepo.save(course);
            }
            throw new DuplicateKeyException("course already registerd");
        }
        catch (DuplicateKeyException dp) {
            throw new DuplicateKeyException(dp.getMessage());
        }
        catch (Exception e) {
            throw new Exception(e.getMessage());
        }

    }

    public List<Course> getAllCourses() throws Exception {
        try {
            return courseRepo.findAll();
        }
        catch(Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public String deleteCourse(Long courseId) throws Exception{
        try {
            Course course = courseRepo.findById(courseId).orElseThrow(
                    () -> new IllegalStateException("course not available"));
            courseRepo.delete(course);
            if(!courseRepo.existsById(courseId)) {
                return "course deleted successfully!";
            }
            return "course unsuccessfully deleted";
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
