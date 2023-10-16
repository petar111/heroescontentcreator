package com.springpj.heroescontentcreator.model.dto;

import com.springpj.heroescontentcreator.model.validation.ValidEmail;
import com.springpj.heroescontentcreator.model.validation.ValidPassword;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RegisterRequestDto {

	
	@Size(max = 100, message = "Maximum email size is 100.")
	@ValidEmail
	@NotNull(message = "Email is required.")
    private String email;
	@Size(max = 100, message = "Maximum email size is 100.")
	@ValidEmail
    private String backupEmail;
    
    @Size(max = 50, message = "Maximum username size is 50.")
    @NotNull(message = "Username is required.")
    private String username;
    @ValidPassword
    @NotNull(message = "Password is required.")
    private String password;


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

	public String getBackupEmail() {
		return backupEmail;
	}

	public void setBackupEmail(String backupEmail) {
		this.backupEmail = backupEmail;
	}
    

}
