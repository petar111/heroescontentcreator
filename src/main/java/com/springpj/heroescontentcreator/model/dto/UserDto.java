package com.springpj.heroescontentcreator.model.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springpj.heroescontentcreator.model.user.AccountStatus;
import com.springpj.heroescontentcreator.model.validation.ValidEmail;
import com.springpj.heroescontentcreator.model.validation.ValidPassword;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UserDto {
	
	private Long id;
	
	@Size(max = 50, message = "Maximum username size is 50.")
	@NotNull
	private String username;
	
	@Size(max = 100, message = "Maximum email size is 100.")
	@ValidEmail
	@NotNull
	private String email;
	
	@Size(max = 100, message = "Maximum email size is 100.")
	@ValidEmail
	private String backupEmail;

	@ValidPassword
	@JsonIgnore
	private String password;
	private AccountStatus accountStatus;
	private boolean credentialsExpired;
	
	private Date dateCreated;
	private Date dateLastUpdated;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBackupEmail() {
		return backupEmail;
	}
	public void setBackupEmail(String backupEmail) {
		this.backupEmail = backupEmail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public AccountStatus getAccountStatus() {
		return accountStatus;
	}
	public void setAccountStatus(AccountStatus accountStatus) {
		this.accountStatus = accountStatus;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	public Date getDateLastUpdated() {
		return dateLastUpdated;
	}
	public void setDateLastUpdated(Date dateLastUpdated) {
		this.dateLastUpdated = dateLastUpdated;
	}
	public boolean isCredentialsExpired() {
		return credentialsExpired;
	}
	public void setCredentialsExpired(boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}
	

}
