package com.springpj.heroescontentcreator.model.dto;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import com.springpj.heroescontentcreator.model.Origin;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class OriginVersionDto {
	
	private Long id;
	
	private String name;
	private String description;

	private OriginDto origin;
	
	private Date dateCreated;

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

	public OriginDto getOrigin() {
		return origin;
	}

	public void setOrigin(OriginDto origin) {
		this.origin = origin;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}
	
	
	

}
