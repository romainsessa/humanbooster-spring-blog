package com.hb.springpersistence.dto;

import java.util.Date;

public class PostDetailsDTO {

	private Integer id;
	private String createdBy;
	private Date createdOn;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public PostDetailsDTO() {
	}

	public PostDetailsDTO(Integer id, String createdBy, Date createdOn) {
		super();
		this.id = id;
		this.createdBy = createdBy;
		this.createdOn = createdOn;
	}

	@Override
	public String toString() {
		return "PostDetailsDTO [id=" + id + ", createdBy=" + createdBy + ", createdOn=" + createdOn + "]";
	}

}
