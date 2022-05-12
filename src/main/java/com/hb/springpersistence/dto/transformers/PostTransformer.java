package com.hb.springpersistence.dto.transformers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hb.springpersistence.dto.PostCommentDTO;
import com.hb.springpersistence.dto.PostDTO;
import com.hb.springpersistence.dto.PostDetailsDTO;
import com.hb.springpersistence.dto.TagDTO;
import com.hb.springpersistence.entities.Post;
import com.hb.springpersistence.entities.PostComment;
import com.hb.springpersistence.entities.Tag;

@Component
public class PostTransformer {

	public PostDTO transform(Post post) {
		return transform(post, true);
	}

	public PostDTO transform(Post post, boolean withTag) {
		PostDTO pDTO = new PostDTO();
		pDTO.setId(post.getId());
		pDTO.setTitle(post.getTitle());

		PostDetailsDTO dDTO = new PostDetailsDTO();
		dDTO.setId(post.getDetails().getId());
		dDTO.setCreatedBy(post.getDetails().getCreated_by());
		dDTO.setCreatedOn(post.getDetails().getCreate_on());

		pDTO.setDetails(dDTO);

		List<PostCommentDTO> commentDTOs = new ArrayList<>();
		for (PostComment comment : post.getComments()) {
			PostCommentDTO commentDTO = TransformerFactory.getPostCommentTransformer().transform(comment);
			commentDTOs.add(commentDTO);
		}
		pDTO.setComments(commentDTOs);

		if (withTag) {
			List<TagDTO> tagDTOs = new ArrayList<>();
			for (Tag tag : post.getTags()) {
				TagDTO tagDTO = TransformerFactory.getTagTransformer().transform(tag, false);
				tagDTOs.add(tagDTO);
			}
			pDTO.setTags(tagDTOs);
		}
		return pDTO;
	}

}
