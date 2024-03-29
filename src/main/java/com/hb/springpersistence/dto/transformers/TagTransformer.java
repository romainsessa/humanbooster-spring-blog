package com.hb.springpersistence.dto.transformers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.hb.springpersistence.dto.PostDTO;
import com.hb.springpersistence.dto.TagDTO;
import com.hb.springpersistence.entities.Post;
import com.hb.springpersistence.entities.Tag;

@Component
public class TagTransformer {

	public TagDTO transform(Tag tag) {
		return transform(tag, true);
	}

	public TagDTO transform(Tag tag, boolean withPost) {
		TagDTO tagDTO = new TagDTO();
		tagDTO.setId(tag.getId());
		tagDTO.setName(tag.getName());

		if (withPost) {
			List<PostDTO> posts = new ArrayList<>();
			for (Post post : tag.getPosts()) {
				PostDTO postDTO = TransformerFactory.getPostTransformer().transform(post, false);
				posts.add(postDTO);
			}
			tagDTO.setPosts(posts);
		}
		return tagDTO;
	}

	public Tag transform(TagDTO tag) {
		Tag entity = new Tag();
		entity.setId(tag.getId());
		entity.setName(tag.getName());

		return entity;
	}

}
