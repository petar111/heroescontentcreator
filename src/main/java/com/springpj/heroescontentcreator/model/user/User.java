package com.springpj.heroescontentcreator.model.user;

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
	@Column(name = "EMAIL", nullable = false, unique = true)
	@ValidEmail
	private String email;
	
	@Size(max = 100, message = "Maximum email size is 100.")
	@Column(name = "BACKUP_EMAIL", nullable = true, unique = true)
	@ValidEmail
	private String backupEmail;

	@Column(name = "PASSWORD", nullable = false)
	@ValidPassword
	private String password;
	
	@Column(name = "ACCOUNT_STATUS", nullable = false)
	private AccountStatus accountStatus;
	
	@Column(name = "CREDENTIALS_EXPIRED", nullable = false)
	private boolean credentialsExpired;
	
	@CreationTimestamp
	private Date dateCreated;
	@UpdateTimestamp
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
