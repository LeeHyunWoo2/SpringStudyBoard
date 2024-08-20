package org.zerock.mapper; // DB와 영속성을 가진 패키지

import java.util.List;

import org.zerock.domain.BoardVO;

public interface BoardMapper {
	// interface로 선언한 이유는 추상메서드와 xml을 결합하여 구현 클래스를 사용하는 mybatis
	// xml을 생성할때는 resources 안에 폴더를 계층별로 만들고 파일명을 인터페이스와 똑같이 만들어야함.
	// 걍 폴더 생성 시 제목을 org/zerock/mapper 로 하면 쫙 생김

//	@Select("Select * from tbl_board where bno > 0") // where bno > 0 조건을 건 이유는 bno가 pk로 지정되서 인덱싱이 되어있어서 검색이 빠름
	public List<BoardVO> getList(); // 인터페이스는 추상메서드라서 실행문안씀
	// 리턴은 List<BoardVO> 임으로 배열 안쪽에 객체가 BoardVO로 완성 됨


	//board 삽입용 코드
	public void insert(BoardVO board);


	// 삽입할 번호를 먼저 파악후 게시물 등록
	public void insertSelectKey(BoardVO board);
	
	// 게시물의 번호를 받아 객체를 출력한다.
	public BoardVO read(long bno);
	
	// 게시물의 번호를 받아 객체를 수정한다.
	public int update(BoardVO boardVO);
	
	public int delete(long bno);
	

}
