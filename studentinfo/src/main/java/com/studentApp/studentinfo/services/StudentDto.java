package com.studentApp.studentinfo.services;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentDto {
	private long studentId;
	private String studentName;
	private String department;
	private String skills;
	private String projects;
	private double gpa;
	private String phoneNo;
	@Override
	public String toString() {
		return "StudentDto [studentId=" + studentId + ", studentName=" + studentName + ", department=" + department
				+ ", skills=" + skills + ", projects=" + projects + ", gpa=" + gpa + ", phoneNo=" + phoneNo + "]";
	}
		
}

