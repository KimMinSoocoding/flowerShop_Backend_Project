package kr.co.sionms.jsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import kr.co.sionms.jsp.domain.Board;
import kr.co.sionms.jsp.domain.Criteria;
import kr.co.sionms.jsp.domain.Member;
import kr.co.sionms.jsp.util.DBConn;

public class BoardDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public Long insert(Board board) {
		conn = DBConn.getConnection();
		long result = 0;
		//처리할 sql구문
		String sql = "insert into tbl_board (title, content, writer) values (?,?,?)";

		try {
			// 문장생성
			pstmt = conn.prepareStatement(sql); // 밑에문장과 다른점
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setString(3, board.getWriter());
//			stmt = conn.createStatement();
			
			// 문장처리
			pstmt.executeUpdate();
//			result = stmt.executeUpdate(sql);
			close();
			
			// 쿼리 재실행
			// 작성할 게시글의 글번호 조회 (작성한순간 바로 실행해야함) 
			sql ="select max(bno) from tbl_board"; // 최상위 글번호
			conn = DBConn.getConnection();
			pstmt = conn.prepareStatement(sql); 
			rs = pstmt.executeQuery();
			rs.next(); // 커서 이동(행간이동) 더 이동할곳이 있냐 없냐
			result = rs.getLong(1); // 1번컬럼 long 반환 
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	//반환타입 : List이였어야 한다
	public Board selectOne(Long bno) {
		conn = DBConn.getConnection();
		// 반환 예정 객체
		Board board = null;
		// 처리할 sql구문 
		String sql ="select tb.*, (select count(*) from tbl_reply tr where tr.bno = tb.bno) replyCnt\n"
				+ "from tbl_board tb where bno = ?";
		try {
			// 문장생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, bno);
			
			// 결과집합
			rs = pstmt.executeQuery();
			
			// 결과집합 >> 자바객체 
			if(rs.next()) {
				int idx = 1;
				// 객체 생성 후 값 바인딩
				board = new Board(
						rs.getLong(idx++), 
						rs.getString(idx++),
						rs.getString(idx++),
						rs.getString(idx++),
						rs.getDate(idx++),
						rs.getString(idx++),
						rs.getInt(idx++), rs.getInt(idx++), null, rs.getInt(idx++));
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 결과 반환
		return board;
	}
	
	public List<Board> selectList(Criteria cri) {
		conn = DBConn.getConnection();
		// 반환 예정 객체
		List<Board> boards = new ArrayList<Board>();
		
		// 처리할 sql구문 
		String sql ="";
		sql += "select tb.*, (select count(*) from tbl_reply tr where tr.bno = tb.bno) replyCnt\n"
				+ "from tbl_board tb where category = ?";
		// 검색
		sql += getSearchSqlBy(cri);
		sql	+= " order by bno desc limit ? offset ?";
		try {
			// 문장생성
			pstmt = conn.prepareStatement(sql);
			int idx = 1;
			pstmt.setInt(idx++, cri.getCategory());
			pstmt.setInt(idx++, cri.getAmount());
			pstmt.setInt(idx++, (cri.getPageNum() -1) * cri.getAmount());
			
			// 결과집합
			rs = pstmt.executeQuery();
			
			// 결과집합 >> 자바객체  if 로 되어있으면 1개의 행만 실행한다
			while(rs.next()) {
				idx = 1;
				// 객체 생성 후 값 바인딩
				Board board = new Board(
						rs.getLong(idx++), 
						rs.getString(idx++),
						rs.getString(idx++),
						rs.getString(idx++),
						rs.getDate(idx++),
						rs.getString(idx++),
						rs.getInt(idx++), rs.getInt(idx++), null, rs.getInt(idx++));
				boards.add(board);
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 결과 반환
		return boards;
	}

	public int selectListCount(Criteria cri) {
		conn = DBConn.getConnection();
		// 반환 예정 객체
		int count =0;
		// 처리할 sql구문 
		String sql ="select count(*) from tbl_board where category = ?";
		sql += getSearchSqlBy(cri);
		try {
			// 문장생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, cri.getCategory());
			
			// 결과집합
			rs = pstmt.executeQuery();
			
			// 결과집합 >> 자바객체  if 로 되어있으면 1개의 행만 실행한다
			while(rs.next()) {
				int idx = 1;
				count = rs.getInt(1);
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 결과 반환
		return count;
	}
	
	
	public void update(Board board) {
		conn = DBConn.getConnection();
		//처리할 sql구문
		String sql = "update tbl_board set\n"
				+ "	title = ?,\n"
				+ "	content = ?,\n"
				+ "	updatedate = now()\n"
				+ "where bno = ?";

		try {
			// 문장생성
			pstmt = conn.prepareStatement(sql); // 밑에문장과 다른점
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setLong(3, board.getBno());
//			stmt = conn.createStatement();
			
			// 문장처리
			pstmt.executeUpdate();
//			result = stmt.executeUpdate(sql);
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void increaseHitCount(Long bno) {
		conn = DBConn.getConnection();
		//처리할 sql구문
		String sql = "update tbl_board set\n"
				+ "	hitcount = hitcount+1\n"
				+ "where bno = ?";

		try {
			// 문장생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, bno);
			
			// 문장처리
			pstmt.executeUpdate();
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Long bno) {
		conn = DBConn.getConnection();
		// 처리할 sql구문 
		String sql ="delete from tbl_board \n"
				+ "where bno = ?";
		try {
			// 문장생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, bno);
			
			// 문장처리
			pstmt.executeUpdate();
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// 자원 반환 
	public void close() {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {}
		}
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {}
		}
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {}
		}
	}
	
	private String getSearchSqlBy(Criteria cri) {
		String sql = "";
		if(cri.getType() != null && cri.getKeyword() != null && String.join("", cri.getType()).length() > 0) {
			sql += " and ( ";
			
			List<String> list = Arrays.asList(cri.getType());
			// T C W 
			List<String> typeList = list.stream().map(s->" " + s +" like '%" + cri.getKeyword() + "%' ").collect(Collectors.toList());
			sql += String.join(" or ", typeList);
			
			sql += ")";
		}
		
		return sql;
	}
	
	
	public static void main(String[] args) {
//		new BoardDao().selectList(1).forEach(System.out::println);
		BoardDao dao = new BoardDao();
		
		
	}
}
