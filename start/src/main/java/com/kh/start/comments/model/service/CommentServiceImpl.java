package com.kh.start.comments.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.start.auth.model.vo.CustomUserDetails;
import com.kh.start.auth.service.AuthService;
import com.kh.start.board.model.service.BoardService;
import com.kh.start.comments.model.dao.CommentMapper;
import com.kh.start.comments.model.dto.CommentDTO;
import com.kh.start.comments.model.vo.Comment;
import com.kh.start.exception.InvalidUserRequestException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

	private final CommentMapper commentMapper;
	private final BoardService boardService;
	private final AuthService authenService;
	
	
	@Override
	public void insertComment(CommentDTO comment) {
		
		// 게시글 있는지? 검사
		//getBoardorThrow()
		boardService.findById(comment.getRefBoardNo());
		
		// 요청한 사용자랑 토큰 소유주랑 같은지 검사
		String tokenMemberNo = 
				String.valueOf(((CustomUserDetails)authenService.getUserDetails()).getMemberNo());
		
		if(!tokenMemberNo.equals(comment.getCommentWriter())) {
			throw new InvalidUserRequestException("너 이름이 뭐니?");
		}
		Comment requestData = Comment.builder()
								     .commentWriter(Long.parseLong(tokenMemberNo))
								     .commentContent(comment.getCommentContent())
								     .refBoardNo(comment.getRefBoardNo())
								     .build();
		
		commentMapper.insertComment(requestData);
		
	}

	@Override
	public List<CommentDTO> selectCommentList(Long boardNo) {
		boardService.findById(boardNo);
		return commentMapper.selectCommentList(boardNo);
	}

}
