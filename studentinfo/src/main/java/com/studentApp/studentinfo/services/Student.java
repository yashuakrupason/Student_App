package com.studentApp.studentinfo.services;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "student")
@Getter
@Setter
@NoArgsConstructor
public class Student {

	@Override
	public String toString() {
		return "Student [studentId=" + studentId + ", studentName=" + studentName + ", department=" + department
				+ ", skills=" + skills + ", projects=" + projects + ", gpa=" + gpa + ", phoneNo=" + phoneNo + "]";
	}

	@Id
	@Column(name="STUDENT_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long studentId;
	
	@Column(name="STUDENT_NAME")
	private String studentName;
	
	@Column(name="DEPT")
	private String department;
	
	@Column(name="SKILLS")
	private String skills;
	
	@Column(name="PROJECTS")
	private String projects;
	
	@Column(name="CGPA")
	private double gpa;
	
	@Column(name="PHNO")
	private String phoneNo;
}
