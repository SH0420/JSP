package model2.mvcboard;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import common.DBConnPool;

public class MVCBoardDAO extends DBConnPool{ //커넥션 풀 상속
	public MVCBoardDAO() {
		super();
	}
	
	//검색 조건에 맞는 게시물의 개수를 반환합니다.
	public int selectCount(Map<String, Object> map) {
		int totalCount = 0;
		//쿼리문 준비
		String query ="SELECT COUNT(*) FROM mvcboard";
		//검색 조건이 있다면 WHERE절로 추가
		if (map.get("searchWord") !=null) {
			query += " WHERE " + map.get("searchField") + " "
				   + " LIKE '%" + map.get("searchWord") + "%'";
		}
		try {
			stmt = con.createStatement();  //쿼리문 생성
			rs = stmt.executeQuery(query); //쿼리문 실행
			rs.next();
			totalCount = rs.getInt(1); //검색된 게시물 개수 저장
		}
		catch (Exception e) {
			System.out.println("게시물 카운트 중 예외 발생");
			e.printStackTrace();
		}
		
		return totalCount; //게시물 개수를 서블릿으로 반환
	}
	
	//검색 조건에 맞는 게시물 목록을 반환합니다.(페이징 기능 지원).
	public List<MVCBoardDTO>selectListPage(Map<String,Object>map){
		List<MVCBoardDTO> board = new Vector<MVCBoardDTO>();
		//쿼리문 준비
		String query = " "
					 + "SELECT * FROM ( "
					 + "    SELECT Tb.*, ROWNUM rNum FROM ( "
					 + "        SELECT * FROM mvcboard ";
		//검색 조건이 있다면 WHERE절로 추가
		if (map.get("searchWord") !=null)
		{
			query += " WHERE " + map.get("searchField")
				   + " LIKE '%" + map.get("searchWord") + "%' ";
		}
		
		query += "         ORDER BY idx DESC "
			   + "     ) Tb "
			   + " )"
			   +" WHERE rNum BETWEEN ? AND ?"; //게시물 구간은 인파라미터로
	
		try {
			psmt = con.prepareStatement(query); //동적쿼리문 생성
			psmt.setString(1, map.get("start").toString()); //인파라미터 설정
			psmt.setString(2, map.get("end").toString()); 
			rs =  psmt.executeQuery(); //쿼리문실행
			
			//반환된 게시물 목록을 List 컬렉션에 추가
			while (rs.next()) {
				MVCBoardDTO dto = new MVCBoardDTO();
				
				dto.setIdx(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setOfile(rs.getString(6));
				dto.setSfile(rs.getString(7));
				dto.setDowncount(rs.getInt(8));
				dto.setPass(rs.getString(9));
				dto.setVisitcount(rs.getInt(10));
				
				board.add(dto);
			}
		}
		catch (Exception e) {
			System.out.println("게시물 조회 중 예외발생");
			e.printStackTrace();
		}
		return board; //목록반환
	}		
	//Write작성후 12/8
	//게시글 데이터를 받아 DB에 추가합니다(파일 업로드지원).
	public int insertWrite(MVCBoardDTO dto) {
		int result = 0;
		try {
			String query = "INSERT INTO mvcboard ( "
					 	 + " idx, name, title, content, ofile, sfile, pass) "
					 	 + " VALUES ( "
					 	 + " seq_board_num.NEXTVAL,?,?,?,?,?,?)";
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getName());
			psmt.setString(2, dto.getTitle());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getOfile());
			psmt.setString(5, dto.getSfile());
			psmt.setString(6, dto.getPass());
			result = psmt.executeUpdate();					
		}
		catch (Exception e) {
			System.out.println("게시물 입력 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	
	//주어진 일련번호에 해당하는 게시물의 조회수를 1증가시킨다.
	public void updateVisitCount(String idx) {
		String query = "UPDATE mvcboard SET "
					 + " visitcount=visitcount+1 "
					 + " WHERE idx=?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			psmt.executeQuery();
		}
		catch (Exception e) {
			System.out.println("게시물 조회수 증가 중 예외 발생");
			e.printStackTrace();
		}
	}
	
	public MVCBoardDTO selectView(String idx) {
		MVCBoardDTO dto = new MVCBoardDTO();
		String query = "SELECT * FROM mvcboard where idx = ?";
		
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			rs = psmt.executeQuery();
			
			if(rs.next()) {
				dto.setIdx(rs.getString(1));
				dto.setName(rs.getString(2));
				dto.setTitle(rs.getString(3));
				dto.setContent(rs.getString(4));
				dto.setPostdate(rs.getDate(5));
				dto.setOfile(rs.getString(6));
				dto.setSfile(rs.getString(7));
				dto.setDowncount(rs.getInt(8));
				dto.setPass(rs.getString(9));
				dto.setVisitcount(rs.getInt(10));
			}
			
		}
		catch (Exception e) {
			System.out.println("게시물 상세보기 중 예외 발생");
			e.printStackTrace();
		}
		
		return dto;
	}
	
	public void downCountPlus(int idx) {
		String sql = "UPDATE mvcboard SET "
				+ " downcount=downcount+1 "
				+ " WHERE idx=? ";
			try {
				psmt =con.prepareStatement(sql);
				psmt.setLong(1, idx);
				psmt.executeUpdate();
				}
			catch (Exception e) {
				
			}
	}
	//입력한 비밀번호가 지정한 일련번호의 게시물의 비밀번호와 일치하는지확인해요
	public boolean confirmPassword(String pass, String idx) {
		
		boolean isCorr = true;
		//비밀번호와 일련번호가 일치하는지 게시물의개수를 세어 비밀번호 일치여부확인해요
		String query = "SELECT COUNT(*) FROM mvcboard WHERE pass=? AND idx=?";
		try {
			psmt = con.prepareStatement(query);
			psmt.setString(1, pass);
			psmt.setString(2, idx);
			rs = psmt.executeQuery();
			rs.next();
			if(rs.getInt(1) == 0 ) {//일치하는 게시물이 없다면(=실행결과 0이면) false를 반환,예외가 발생해도 false반환
				isCorr = false;
			}
		}
		catch (SQLException e) {
			isCorr = false;
			e.printStackTrace();
		}
		return isCorr;
	}
	
	//지정한 일련번호의 게시물을 삭제합니다
	public int deletePost(String idx) {
		int result = 0;
		
		String query = "DELETE FROM mvcboard WHERE idx=?";
		try {
			
			psmt = con.prepareStatement(query);
			psmt.setString(1, idx);
			result = psmt.executeUpdate();//정상적으로  삭제되었으면 1을반환해요
			
		}
		catch (SQLException e) {
			System.out.println("게시물 삭제 중 예외 발생");
			e.printStackTrace();
		}
		return result;
	}
	//게시글 데이터를 받아 DB에 저장되어 있던 내용을 갱신해요(파일 업로드 지원)
	public int updatePost(MVCBoardDTO dto) {
		
		int result = 0;
		
		String query ="update mvcboard"
				+" set title = ?, name = ?, content= ?, ofile= ?, sfile= ? "
				+" where idx = ? and pass =?";
				
		try {
			//쿼리문 준비
			psmt = con.prepareStatement(query);
			psmt.setString(1, dto.getTitle());
			psmt.setString(2, dto.getName());
			psmt.setString(3, dto.getContent());
			psmt.setString(4, dto.getOfile());
			psmt.setString(5, dto.getSfile());
			psmt.setString(6, dto.getIdx());
			psmt.setString(7, dto.getPass());
			//쿼리문실행
			result = psmt.executeUpdate();
		
		} catch (SQLException e) {
			System.out.println("게시물 수정 중 예외 발생");
			e.printStackTrace();
		}
		
		return result;
	}
	
}
