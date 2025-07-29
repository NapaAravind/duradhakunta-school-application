package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Student;
import com.example.demo.repositories.StudentRepository;

@Service
public class StudentService {

    @Autowired
    private StudentRepository repository;

    public List<Student> getAllStudents() {
        return repository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return repository.findById(id);
    }

    public Student createStudent(Student student) {
        return repository.save(student);
    }

    public Student updateStudent(Long id, Student updated) {
        return repository.findById(id).map(student -> {
            student.setName(updated.getName());
            student.setEmail(updated.getEmail());
            student.setDepartment(updated.getDepartment());
            return repository.save(student);
        }).orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public void deleteStudent(Long id) {
        repository.deleteById(id);
    }
}
