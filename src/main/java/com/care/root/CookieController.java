package com.care.root;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("cookie")
public class CookieController {

	@Autowired(required = false)
	// required : TestClass라는 bean의 유무에 따라서 null로 처리하거나 빈 주입
	TestClass tc;
	
	@GetMapping("index")
	public String index(HttpServletResponse res) {
		System.out.println(tc);
		
		// cookie 생성
		Cookie cookie = new Cookie("key", "12345");
		cookie.setMaxAge(5);
		res.addCookie(cookie);
		
		return "cookie/index";
	}
	
	@GetMapping("result")
	public void result(HttpServletRequest req) {
		// 사용자 모든 cookie 가져오기
		Cookie[] arrCo = req.getCookies();
		for (Cookie c : arrCo) {
			System.out.println("name : "+ c.getName());
			System.out.println("value : "+ c.getValue());
			System.out.println("-----------------------");
		}
	}
	
	@GetMapping("result02")
	public void result02(@CookieValue(value="key", required = false) Cookie cook) {
		// 원하는 cookie의 값만 가져오기
		// cookie값 만료되면 400에러 남 (required 넣기 전)
		// cookie값 만료되면 500에러 남 (required 넣은 후 : Null포인트)

		System.out.println("cookie02 : "+ cook);
		
		if (cook != null) {
			System.out.println("name02 : "+ cook.getName());
			System.out.println("value02 : "+ cook.getValue());
		}		
	}
	
}
