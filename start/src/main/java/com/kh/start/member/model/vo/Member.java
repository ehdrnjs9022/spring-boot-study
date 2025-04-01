package com.kh.start.member.model.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;

// Value Object(값을 담는 객체)
// V     O 
// 한번들어간 값이 절대 바뀌면 안되기떄문에 @Setter 사용 안함

@Value
@Getter
@Builder
public class Member {
	
	private Long memberNo;
	private String memberId;
	private String memberPw;
	private String memberName;
	private String role;
}
