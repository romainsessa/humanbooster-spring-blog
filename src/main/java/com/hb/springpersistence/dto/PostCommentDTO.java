package com.hb.springpersistence.dto;

public class PostCommentDTO {

	private Integer id;
	private String review;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public PostCommentDTO() {
	}

	public PostCommentDTO(Integer id, String review) {
		super();
		this.id = id;
		this.review = review;
	}

	@Override
	public String toString() {
		return "PostCommentDTO [id=" + id + ", review=" + review + "]";
	}

}
