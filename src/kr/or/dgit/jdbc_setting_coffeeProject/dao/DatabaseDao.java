package kr.or.dgit.jdbc_setting_coffeeProject.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.or.dgit.jdbc_setting_coffeeProject.jdbc.DBCon;
import kr.or.dgit.jdbc_setting_coffeeProject.jdbc.JdbcUtil;

public class DatabaseDao {
	// DatabaseDao class를 final 선언한다.
	private static final DatabaseDao instance = new DatabaseDao();

	// DatabaseDao를 불러오기위한 get메서드
	public static DatabaseDao getInstance() {
		return instance;
	}

	// 생성자
	public DatabaseDao() {
	}

	// SQL UPDATE 문을 위한 메서드
	// SQL 문을 받기위해 String 매개변수 sql 생성
	public void excuteUpdateSQL(String sql) {
		// a ) DB연결을 위해 DBcon을 Connection con에 연결
		Connection con = DBCon.getInstance().getConnection();

		// b ) DB에 Update문을 날리기 위해 PreparedStatement 객체 생성
		PreparedStatement pstmt = null;

		try {
			// c ) DB에 매개 변수 sql연결
			pstmt = con.prepareStatement(sql);
			// d ) executeUpdate() 메서드로 sql문 실행
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.err.printf("%s - %s%n", e.getErrorCode(), e.getMessage());
			e.printStackTrace();
		} finally {
			// f ) DB 업데이트후 pstmt 종료
			JdbcUtil.close(pstmt);
		}

	}

	// SQL Query 문을 위한 메서드
	// SQL 문을 받기위해 String 매개변수 sql 생성
	public ResultSet exuteQuerySQL(String sql) throws SQLException {
		// g ) DB연결을 위해 connnection 생성
		Connection con = DBCon.getInstance().getConnection();
		// h ) DB에 쿼리문을 실행하기위해 preparestatement 객채 생성후 sql문 연결
		// h-1) SQlExcepction 은 throw로 불러오는 곳에서 처리하도록 해준다.
		PreparedStatement pstmt = con.prepareStatement(sql);
		
		// i ) excuteQuery() 메서드로 sql 쿼리문 실행
		return pstmt.executeQuery();
	}

}
