package com.hb.springpersistence.service;

import java.util.List;
import com.hb.springpersistence.dto.PostCommentDTO;

public interface ICommentService {

	public PostCommentDTO getCommentDTO(Integer id);	

	public List<PostCommentDTO> getCommentDTOs();

	public PostCommentDTO save(PostCommentDTO comment);

	public void delete(Integer id);

}
