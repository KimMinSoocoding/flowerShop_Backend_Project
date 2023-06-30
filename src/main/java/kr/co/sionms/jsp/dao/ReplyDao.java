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

import kr.co.sionms.jsp.domain.Reply;
import kr.co.sionms.jsp.domain.Board;
import kr.co.sionms.jsp.domain.Criteria;
import kr.co.sionms.jsp.domain.Member;
import kr.co.sionms.jsp.util.DBConn;

public class ReplyDao {
	private Connection conn;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public int insert(Reply reply) {
		conn = DBConn.getConnection();
		int result = 0;
		//처리할 sql구문
		String sql = "insert into tbl_reply (content, writer, bno) values (?,?,?)";
		try {
			// 문장생성
			pstmt = conn.prepareStatement(sql); 
			pstmt.setString(1, reply.getContent());
			pstmt.setString(2, reply.getWriter());
			pstmt.setLong(3, reply.getBno());
			
			// 문장처리
			result = pstmt.executeUpdate();
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Reply> selectList(Long bno) {
		conn = DBConn.getConnection();
		// 반환 예정 객체
		List<Reply> replies = new ArrayList<Reply>();
		String sql ="";
		sql += "select * from tbl_reply where bno = ?";
		try {
			// 문장생성
			pstmt = conn.prepareStatement(sql);
			int idx = 1;
			pstmt.setLong(idx++, bno);
			
			rs = pstmt.executeQuery();
			while(rs.next()) {
				idx = 1;
				// 객체 생성 후 값 바인딩
				Reply reply = new Reply(
						rs.getLong(idx++), 
						rs.getString(idx++),
						rs.getTimestamp(idx++),
						rs.getString(idx++),
						rs.getLong(idx++));
					replies.add(reply);
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 결과 반환
		return replies;
	}

	public int delete(Long rno) {
		int ret = 0;
		conn = DBConn.getConnection();
		// 처리할 sql구문 
		String sql ="delete from tbl_reply where rno = ?";
		try {
			// 문장생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, rno);
			
			// 문장처리
			ret = pstmt.executeUpdate();
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ret;
	}
	
	public Reply selectOne(Long rno) {
		conn = DBConn.getConnection();
		// 반환 예정 객체
		Reply reply = null;
		// 처리할 sql구문 
		String sql = "";
		sql += "select * from tbl_reply where rno = ?";
		try {
			// 문장생성
			pstmt = conn.prepareStatement(sql);
			int idx = 1;
			pstmt.setLong(idx++, rno);
			
			// 결과집합
			rs = pstmt.executeQuery();
			
			// 결과집합 >> 자바객체 
			if(rs.next()) {
				 idx = 1;
				// 객체 생성 후 값 바인딩
				reply = new Reply(
						rs.getLong(idx++), 
						rs.getString(idx++),
						rs.getTimestamp(idx++),
						rs.getString(idx++),
						rs.getLong(idx++));
			}
			close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 결과 반환
		return reply;
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
	
	
	public static void main(String[] args) {
//		new ReplyDao().selectList(1).forEach(System.out::println);
		ReplyDao dao = new ReplyDao();

	}
}
