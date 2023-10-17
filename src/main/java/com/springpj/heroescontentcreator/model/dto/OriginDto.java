package com.springpj.heroescontentcreator.model.dto;

import java.util.Date;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class OriginDto {

	private Long id;


	@Size(max = 255, message = "Maximum name size is 255.")
	@NotBlank(message = "Name must not be blank.")
	private String name;
	@Size(max = 255, message = "Maximum description size is 255.")
	private String description;
	
	private Date dateCreated;
	private Date dateLastUpdated;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
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

}
