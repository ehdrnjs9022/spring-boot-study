package com.kh.start.auth.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.start.auth.service.AuthService;
import com.kh.start.auth.service.AuthServiceImpl;
import com.kh.start.member.model.dto.MemberDTO;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@RequestMapping("auth")
@RestController
@Slf4j
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	
	@PostMapping("/login")
	public ResponseEntity<?> login(@Valid @RequestBody MemberDTO member){
		
		Map<String, String> loginResponse = authService.login(member);
		
		log.info("자격증에 성공한 사용자 정보 오나 : {} " , loginResponse);
		return ResponseEntity.ok(loginResponse);
	}
	
	
}
