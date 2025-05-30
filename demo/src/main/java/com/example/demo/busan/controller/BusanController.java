package com.example.demo.busan.controller;

import java.util.List;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.busan.model.dto.Comment;
import com.example.demo.busan.model.service.BusanService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@CrossOrigin(origins="*")
@RestController
@RequestMapping("busans")
public class BusanController {
	
	private final BusanService busanService;
	
	@GetMapping
	public ResponseEntity<String> getBusanFoods(@RequestParam(name="pageNo", defaultValue="1")int pageNo) {
		//log.info(" 페이지 넘버 : {} ", pageNo);
		String responseData	=busanService.requestGetBusan(pageNo);
		return ResponseEntity.ok(responseData);
	
	}
	//busans/abc
	//@GetMapping("/abc)
	// 2절하기 상세조회 변수로받아서 {} 사용
	@GetMapping("/{id}")
	public ResponseEntity<String> getBusanDetail(@PathVariable(name="id")int id){
		//log.info("식당번호  : {}" , id);
		String response =busanService.requestGetBusanDetail(id);
		return ResponseEntity.ok(response);
	
	}
	
	// 3절하기 식당에 댓글달기 및 조회
	
	@PostMapping("/comments") // 식당번호,댓글내용 => 가공
	public ResponseEntity<String> save(@RequestBody Comment comment){
		//log.info("나오나 {} ", comment);
		
		busanService.saveComment(comment);
		return ResponseEntity.ok().build();
	
	
	}
	@GetMapping("comments/{id}")
	public ResponseEntity<List<Comment>> getComment(@PathVariable(name="id") Long id){
			List<Comment> comments = busanService.selectCommentList(id);
			return ResponseEntity.ok(comments);
		
	}
	
	// 4절하기 스프링 뒷단을 스프링 부트로바꿔서
	
	
	
	
}
