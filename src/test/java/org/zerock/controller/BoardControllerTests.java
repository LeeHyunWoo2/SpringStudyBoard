package org.zerock.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/spring/root-context.xml",
		"file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml" }) // 프론트라서 서블릿컨텍스트도 참조
@Log4j2
@WebAppConfiguration // 프론트 영역 테스트용 (톰캣없이 하는 임시 web.xml 느낌)
public class BoardControllerTests {

	@Setter(onMethod_ = @Autowired)
	private WebApplicationContext ctx; // 톰캣 대타

	private MockMvc mockMvc; // 크롬 대타

	@Before // junit껄로 import해야함. 구동 전에 선행해야하는 코드 작성
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
	}

	@Test // url과 결과를 처리해주는 테스트
	public void testList() throws Exception {
		log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/list")) // url을
				.andReturn() // 결과를
				.getModelAndView() // 모델에서 뷰 까지
				.getModelMap() // 표형식
		);
	}

	@Test
	public void testRegister() throws Exception {
		String resultPage = mockMvc
				.perform(MockMvcRequestBuilders.post("/board/register").param("title", "컨트롤러 테스트 제목")
						.param("content", "컨트롤러 테스트 내용").param("writer", "테스트맨"))
				.andReturn() // 결과를
				.getModelAndView().getViewName(); // 리턴값을 받아서 String 처리

		log.info("결과 url : " + resultPage); // 결과 url : redirect:/board/list
	}

	@Test // bno가 넘어가면 돌아오는 값은 객체
	public void testGet() throws Exception {
		log.info("결과 : " + mockMvc.perform(MockMvcRequestBuilders.get("/board/get").param("bno", "6")).andReturn() // 결과를
				.getModelAndView().getModelMap() // select들은 모델맵으로 리턴함 (표형식 즉, ResultSet 이라서 셀렉트면 이걸로)
		);
	} // 결과 : {board=BoardVO(bno=6, title=테스트 제5목, content=테스트 내5용, writer=5kkw,
		// regdate=Tue Aug 20 11:13:45 KST 2024, updateDate=Tue Aug 20 11:13:45 KST
		// 2024)

	@Test
	public void testModify() throws Exception {
		String resultPage = mockMvc
				.perform(MockMvcRequestBuilders.post("/board/modify").param("bno", "6").param("title", "컨트롤러 수정 테스트 제목")
						.param("content", "컨트롤러 수정 테스트 내용").param("writer", "수정테스트맨"))
				.andReturn().getModelAndView().getViewName(); // String

		log.info("결과 url : " + resultPage);

	}

	@Test
	public void testRemove() throws Exception {
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/remove").param("bno", "6")).andReturn()
				.getModelAndView().getViewName(); // 리턴값을 받아 String 처리
		log.info("결과 url : " + resultPage); // 결과 url : redirect:/board/list
	}

}
