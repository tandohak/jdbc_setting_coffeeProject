package kr.or.dgit.jdbc_setting_coffeeProject;

import java.sql.Connection;

import kr.or.dgit.jdbc_setting_coffeeProject.jdbc.DBCon;
import kr.or.dgit.jdbc_setting_coffeeProject.jdbc.JdbcUtil;
import kr.or.dgit.jdbc_setting_coffeeProject.service.DbService;
import kr.or.dgit.jdbc_setting_coffeeProject.service.ExportService;
import kr.or.dgit.jdbc_setting_coffeeProject.service.ImportService;
import kr.or.dgit.jdbc_setting_coffeeProject.service.InitService;
import kr.or.dgit.jdbc_setting_coffeeProject.service.InitTriggerService;

public class TestMain {

	public static void main(String[] args) {
		DBCon dbCon = DBCon.getInstance();
		
		Connection connection = dbCon.getConnection();
		System.out.println(connection);
		
		DbService service = InitService.getInstance();
		service.service();
		
		service = InitTriggerService.getInstance();
		service.service();
		
		service = ExportService.getInstance();
		service.service();
		
		service = ImportService.getInstance();
		service.service();
		
		JdbcUtil.close(connection);
	}

}
