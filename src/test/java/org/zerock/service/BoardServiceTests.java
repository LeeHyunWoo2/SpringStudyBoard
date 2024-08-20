package org.zerock.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j2
public class BoardServiceTests {

	@Setter(onMethod_ = @Autowired)
	private BoardService service; // setService(BoardService)

	@Test
	public void testExist() {
		// 객체 생성 유무 판단용
		log.info(service); // 인터페이스를 필드로 생성하였는데 서비스객체를 실행하면 Impl이 붙은 클래스가 실행된다.
		assertNotNull(service);
	}

	@Test
	public void testRegister() {
		BoardVO board = new BoardVO(); // 빈 객체 생성
		board.setTitle("서비스로 만든 제목");
		board.setContent("서비스로만든내용");
		board.setWriter("서비스맨");
		service.register(board);
		log.info("등록된 게시물 번호 : " + board.getBno());
	}

	@Test
	public void testGet() {
		log.info(service.get(1L));
	}

	@Test
	public void testGetList() {
		service.getList().forEach(board -> log.info(board));
		// log.info(service.getList()); 이렇게 적어도 나오긴 함
	}

	@Test
	public void testUpdate() {
		BoardVO board = service.get(1L);
		if (board == null) {
			log.info("찾는 객체가 없습니다.");
			return;
		}
		board.setTitle("서비스에서수정한제목");
		board.setContent("서비스에서수정한내용");
		board.setWriter("수정맨");
		log.info("수정된 게시물 번호 : " + service.modify(board));
	}

	@Test
	public void testDelete() {
		log.info("삭제된 결과 : " + service.remove(9L));
	}

}
