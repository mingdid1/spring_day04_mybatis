package com.care.root;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import com.care.root.member.controller.MemberController;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = {
				"classpath:testMember.xml",
				"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class TestMock {
	
	@Autowired MemberController mc;
	MockMvc mock;
	
	@Before	// test 실행하기 전 먼저 실행
	public void setUp() {
		System.out.println("test실행전 실행");
		mock = MockMvcBuilders.standaloneSetup(mc).build();
	}
	
	//@Test
	public void testIndex() throws Exception {
		System.out.println("---test 실행");
		
		mock.perform(get("/list"))
		.andDo(print())	// 상황 출력
		.andExpect(status().isOk()) // status가 200과 같다면? 성공
		.andExpect(forwardedUrl("member/list"));
		// Assert.assertNull(null);
	}
	
	@Test
	@Transactional(transactionManager = "txMgr")
	public void testInsert() throws Exception {
		mock.perform(post("/insert")
				.param("id", "555")
				.param("name", "test"))
		.andDo(print())
		.andExpect(status().is3xxRedirection()); //redirection동작 300과 같다면? 성공
	}
}
