package kr.co.sionms.jsp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import kr.co.sionms.jsp.domain.Board;
import kr.co.sionms.jsp.domain.Member;
import kr.co.sionms.jsp.util.DBConn;

public class MemberDao {
	private Connection conn;
	private Statement stmt;
	private ResultSet rs;
	private PreparedStatement pstmt;
	
	public int insert(Member vo) {
		conn = DBConn.getConnection();
		int result = 0;
		//처리할 sql구문
		String sql = "insert into tbl_member(id, pw, name) values('"
				+ vo.getId() + "','" + vo.getPw() + "','" + vo.getName() + "')";
		try {
			// 문장생성
			stmt = conn.createStatement();
			
			// 문장처리
			result = stmt.executeUpdate(sql);
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	//반환타입 : List이였어야 한다
	public Member selectOne(String id) {
		conn = DBConn.getConnection();
		// 반환 예정 객체
		Member vo = null;
		// 처리할 sql구문 
		String sql ="select * from tbl_member where id = '" + id + "'";
		try {
			// 문장생성
			stmt = conn.createStatement();
			
			// 결과집합
			rs = stmt.executeQuery(sql);
			
			// 결과집합 >> 자바객체 
			if(rs.next()) {
				int idx = 1;
				// 객체 생성 후 값 바인딩
				vo = new Member(
						rs.getString(idx++),
						rs.getString(idx++),
						rs.getString(idx++),
						rs.getDate(idx++)
				);
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 결과 반환
		return vo;
	}
	
	public void update(Member member) {
		conn = DBConn.getConnection();
		//처리할 sql구문
		String sql = "update tbl_member set\n"
				+ "	name = ?\n"
				+ "where id = ?";

		try {
			// 문장생성
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, member.getName());
			pstmt.setString(2, member.getId());
//			stmt = conn.createStatement();
			
			rs = pstmt.executeQuery();
			
			// 문장처리
			pstmt.executeUpdate();
//			result = stmt.executeUpdate(sql);
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int idcheck(String id) {
		conn = DBConn.getConnection();
		int value= 0;
		
		String sql = "select id from tbl_member where id = ?";
		
		try {
			// 문장생성
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, id);
			// 결과집합
			rs = stmt.executeQuery(sql);
			
			if(rs.next()) value = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	public void delete(String id) {
		conn = DBConn.getConnection();
		// 처리할 sql구문 
		String sql ="delete from tbl_member \n"
				+ "where id = ?";
		try {
			// 문장생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			
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
		if(stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {}
		}
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {}
		}
	}
	public static void main(String[] args) {
		MemberDao dao = new MemberDao();
//		dao.insert(new MemberVo("id1","1234","홍길동",null));
		System.out.println(dao.selectOne("id2"));
	}
}
