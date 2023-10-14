package com.springpj.heroescontentcreator.model.authorization;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "AUTHORITY")
public class Authority {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "RESOURCE_ID", nullable = false)
	private Long resourceId;
	@Column(name = "ACCESS_TYPE_ID", nullable = false)
	private Long accessTypeId;

}
