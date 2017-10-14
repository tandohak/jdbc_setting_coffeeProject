package kr.or.dgit.jdbc_setting_coffeeProject;

import java.sql.Connection;

import kr.or.dgit.jdbc_setting_coffeeProject.jdbc.DBCon;
import kr.or.dgit.jdbc_setting_coffeeProject.jdbc.JdbcUtil;
import kr.or.dgit.jdbc_setting_coffeeProject.servies.DbService;
import kr.or.dgit.jdbc_setting_coffeeProject.servies.InitService;

public class TestMain {

	public static void main(String[] args) {
		DBCon dbCon = DBCon.getInstance();
		
		Connection connection = dbCon.getConnection();
		System.out.println(connection);
		
		DbService service = InitService.getInstance();
		service.service();
		JdbcUtil.close(connection);
	}

}
