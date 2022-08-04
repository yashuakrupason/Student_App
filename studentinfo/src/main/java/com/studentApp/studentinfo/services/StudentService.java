package com.studentApp.studentinfo.services;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.studentApp.studentinfo.custexceptions.StudentNotFoundException;


@Service
public class StudentService {
	
	@Autowired
	public StudentRepository studentRepo;
	
	@Bean
	public ModelMapper modelMapper() {
	 return new ModelMapper();
	}
	
		
	
	public List<Student> getStudentsList() {
		List<Student> studentsList = new ArrayList<>();
		studentRepo.findAll().forEach(studentsList::add);
		return studentsList;
	}
	
	public Student getStudentById(long studentId) throws StudentNotFoundException {
		return studentRepo.findById(studentId).orElseThrow(() -> new StudentNotFoundException("Student with Id : "+studentId+" is not present"));
	}
	
	public StudentDto addStudent(StudentDto studentDto) throws IllegalArgumentException {
		return modelMapper().map(studentRepo.save(modelMapper().map(studentDto, Student.class)),StudentDto.class);
	}
	
	public void deleteStudent(long studentId) throws IllegalArgumentException ,StudentNotFoundException{
		studentRepo.findById(studentId).orElseThrow(() -> new StudentNotFoundException("Student with Id : "+studentId+" is not present"));
		studentRepo.deleteById(studentId);
	}
	
	public StudentDto updateStudent(StudentDto studentDto) throws IllegalArgumentException, StudentNotFoundException{
		studentRepo.findById(studentDto.getStudentId())
				.orElseThrow(() -> new StudentNotFoundException("Student with Id : "+studentDto.getStudentId()+" is not present"));
		return modelMapper().map(studentRepo.save(modelMapper().map(studentDto, Student.class)),StudentDto.class);
	}

}
