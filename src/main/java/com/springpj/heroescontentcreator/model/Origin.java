package com.springpj.heroescontentcreator.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "ORIGIN")
public class Origin {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Size(max = 255, message = "Maximum name size is 255.")
	@NotBlank(message = "Name must not be blank.")
	@Column(name = "NAME", nullable = false, unique = true)
	private String name;
	@Size(max = 255, message = "Maximum description size is 255.")
	@Column(name = "DESCRIPTION")
	private String description;
	
	@CreationTimestamp
	@Column(name = "DATE_CREATED")
	private Date dateCreated;
	@UpdateTimestamp
	@Column(name = "DATE_LAST_UPDATED")
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
