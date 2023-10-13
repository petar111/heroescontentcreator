package com.springpj.heroescontentcreator.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.springpj.heroescontentcreator.model.validation.ValidEmail;
import com.springpj.heroescontentcreator.model.validation.ValidPassword;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "_USER")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(max = 50, message = "Maximum username size is 50.")
	@NotBlank(message = "Username must not be blank.")
	@Column(name = "USERNAME", nullable = false, unique = true)
	private String username;
	
	@Size(max = 100, message = "Maximum email size is 100.")
	@Column(name = "EMAIL", nullable = false)
	@ValidEmail
	private String email;
	
	@Size(max = 100, message = "Maximum email size is 100.")
	@Column(name = "BACKUP_EMAIL", nullable = true)
	@ValidEmail
	private String backupEmail;

	@Column(name = "PASSWORD", nullable = false)
	@ValidPassword
	private String password;
	
	@CreationTimestamp
	private Date dateCreated;
	@UpdateTimestamp
	private Date dateLastUpdated;

}
