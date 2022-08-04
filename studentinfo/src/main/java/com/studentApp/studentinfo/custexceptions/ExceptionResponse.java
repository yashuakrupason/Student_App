package com.studentApp.studentinfo.custexceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ExceptionResponse {

	String timeStamp;
	String errorMessage;
	String status;
	String path;
}