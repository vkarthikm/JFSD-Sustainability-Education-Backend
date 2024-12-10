package com.example.sustainability.controller;

import com.example.sustainability.entity.Lesson;
import com.example.sustainability.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/admin")
@CrossOrigin
public class AdminController {

    @Autowired
    private ContentService contentService;

    @PostMapping("/lessons")
    public ResponseEntity<Lesson> addLesson(@RequestBody Lesson lesson) {
        lesson.setDateCreated(new Date());
        Lesson savedLesson = contentService.addLesson(lesson);
        return ResponseEntity.ok(savedLesson);
    }

    @GetMapping("/lessons")
    public List<Lesson> getAllLessons() {
        return contentService.getAllLessons();
    }

    @PutMapping("/lessons/{id}")
    public ResponseEntity<Lesson> updateLesson(@PathVariable Long id, @RequestBody Lesson updatedLesson) {
        Lesson existingLesson = contentService.getLessonById(id);
        if (existingLesson == null) {
            return ResponseEntity.notFound().build();
        }
        updatedLesson.setId(id);
        updatedLesson.setDateCreated(existingLesson.getDateCreated());
        Lesson savedLesson = contentService.updateLesson(updatedLesson);
        return ResponseEntity.ok(savedLesson);
    }

    @DeleteMapping("/lessons/{id}")
    public ResponseEntity<Void> deleteLesson(@PathVariable Long id) {
        boolean deleted = contentService.deleteLesson(id);
        if (!deleted) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }
}
