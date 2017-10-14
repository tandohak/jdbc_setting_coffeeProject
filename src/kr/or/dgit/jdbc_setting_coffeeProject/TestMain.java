package kr.or.dgit.jdbc_setting_coffeeProject;

import java.sql.Connection;

import kr.or.dgit.jdbc_setting_coffeeProject.jdbc.DBCon;
import kr.or.dgit.jdbc_setting_coffeeProject.jdbc.JdbcUtil;

public class TestMain {

	public static void main(String[] args) {
		DBCon dbCon = DBCon.getInstance();
		
		Connection connection = dbCon.getConnection();
		System.out.println(connection);
		
		JdbcUtil.close(connection);
	}

}
