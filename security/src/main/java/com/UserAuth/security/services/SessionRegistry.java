package com.UserAuth.security.services;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="session_registry")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SessionRegistry {

	@Column(name="username")
	private String username;
	
	@Id
	@Column(name="sessionId")
	private String sessionId;

}
