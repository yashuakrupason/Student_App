package com.studentApp.studentinfo.services;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface StudentRepository extends CrudRepository<Student,Long>{
//	
//	public static final String STUDENT_LIST = "SELECT student_id,student_name,dept,cgpa,phno from student";
//	@Query(value=STUDENT_LIST,nativeQuery=true)
//	public List<Object> getStudentsList();
	
}
