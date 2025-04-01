package com.kh.start.member.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class MemberDTO {
	
	private Long memberNo;
	private String memberId;
	private String memberPw;
	private String memberName;
	private String role;



}
