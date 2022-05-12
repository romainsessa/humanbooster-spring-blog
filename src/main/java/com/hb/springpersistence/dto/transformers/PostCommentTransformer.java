package com.hb.springpersistence.dto.transformers;

import org.springframework.stereotype.Component;

import com.hb.springpersistence.dto.PostCommentDTO;
import com.hb.springpersistence.entities.PostComment;

@Component
public class PostCommentTransformer {

	public PostCommentDTO transform(PostComment comment) {
		PostCommentDTO commentDTO = new PostCommentDTO();
		commentDTO.setId(comment.getId());
		commentDTO.setReview(comment.getReview());
		return commentDTO;
	}

}
