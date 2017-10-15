package kr.or.dgit.jdbc_setting_coffeeProject.service;

import kr.or.dgit.jdbc_setting_coffeeProject.Config;
import kr.or.dgit.jdbc_setting_coffeeProject.dao.DatabaseDao;

public class ImportService implements DbService {
	private static final ImportService Instance = new ImportService();
	
	public static ImportService getInstance() {
		return Instance;
	}

	private ImportService() {}


	@Override
	public void service() {
		// sql 외래키 체크를 하지 않겠다는 sql문
		DatabaseDao.getInstance().executeUpdateSQL("SET FOREIGN_KEY_CHECKS = 0");
		// use db
		DatabaseDao.getInstance().executeUpdateSQL("use " + Config.DB_NAME);
		
		//데이터파일 로드 sql문
		for(String tableName : Config.TABLE_NAME){
			DatabaseDao.getInstance().executeUpdateSQL(String.format("LOAD DATA LOCAL INFILE '%s' INTO TABLE %s ", Config.getFilePath(tableName, false), tableName));
		}
		
		// sql 외래키 체크를  true로 바꾼다.
		DatabaseDao.getInstance().executeUpdateSQL("SET FOREIGN_KEY_CHECKS = 1");
	}
	
	
}
