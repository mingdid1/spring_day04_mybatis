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
	// required : TestClass��� bean�� ������ ���� null�� ó���ϰų� �� ����
	TestClass tc;
	
	@GetMapping("index")
	public String index(HttpServletResponse res) {
		System.out.println(tc);
		
		// cookie ����
		Cookie cookie = new Cookie("key", "12345");
		cookie.setMaxAge(5);
		res.addCookie(cookie);
		
		return "cookie/index";
	}
	
	@GetMapping("result")
	public void result(HttpServletRequest req) {
		// ����� ��� cookie ��������
		Cookie[] arrCo = req.getCookies();
		for (Cookie c : arrCo) {
			System.out.println("name : "+ c.getName());
			System.out.println("value : "+ c.getValue());
			System.out.println("-----------------------");
		}
	}
	
	@GetMapping("result02")
	public void result02(@CookieValue(value="key", required = false) Cookie cook) {
		// ���ϴ� cookie�� ���� ��������
		// cookie�� ����Ǹ� 400���� �� (required �ֱ� ��)
		// cookie�� ����Ǹ� 500���� �� (required ���� �� : Null����Ʈ)

		System.out.println("cookie02 : "+ cook);
		
		if (cook != null) {
			System.out.println("name02 : "+ cook.getName());
			System.out.println("value02 : "+ cook.getValue());
		}		
	}
	
}
