package com.kyle.JavaMovingWars.models;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="gigs")
public class Gig {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	@Size(min=5, message="Title must be at least 5 characters")
	private String title;
//	@Size(min=3, message="Starting Street Address must be at least 3 characters")
//	private String startStreetAddress;
//	@Size(min=3, message="Starting City must be at least 3 characters")
//	private String startCity;
//	@Size(min=5,max=5, message="Starting Zip Code must be valid")
//	private String startZip;
//	@Size(min=3, message="Destination Street Address must be at least 3 characters")
//	private String destinationStreetAddress;
//	@Size(min=3, message="Destination City must be at least 3 characters")
//	private String destinationCity;
//	@Size(min=5,max=5, message="Destination Zip Code must be valid")
//	private String destinationZip;
	@Size(min=5, message="Description must be at least 5 characters")
	private String description;
//    private Boolean fragile;
//    private String vehicleType;
//    private Date dateTime;
//    private Long duration;
	@Column(updatable=false)
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date createdAt;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date updatedAt;
    
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
	@PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
	public Gig() {
		
	}
	public Gig(String title,String desc) {
		this.description=desc;
		this.title=title;
		
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
	
	
}

