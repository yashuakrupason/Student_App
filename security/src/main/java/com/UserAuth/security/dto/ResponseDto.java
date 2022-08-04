package com.UserAuth.security.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseDto {

	private String sessionId;

	@Override
	public String toString() {
		return "ResponseDto [sessionId=" + sessionId + "]";
	}
}
