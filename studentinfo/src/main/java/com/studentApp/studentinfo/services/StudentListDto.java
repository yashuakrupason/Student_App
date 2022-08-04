package com.studentApp.studentinfo.services;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentListDto {
	private long studentId;
	private String studentName;
	private String department;
	private double gpa;
	private String phoneNo;
}
