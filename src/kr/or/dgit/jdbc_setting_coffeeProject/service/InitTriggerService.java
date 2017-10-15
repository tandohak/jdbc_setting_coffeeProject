package kr.or.dgit.jdbc_setting_coffeeProject.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import kr.or.dgit.jdbc_setting_coffeeProject.dao.DatabaseDao;
import kr.or.dgit.jdbc_setting_coffeeProject.jdbc.DBCon;

public class InitTriggerService implements DbService {
	private static final InitTriggerService instance = new InitTriggerService();
	


	private InitTriggerService() {	}

	public static InitTriggerService getInstance() {
		return instance;
	}

	@Override
	public void service() {
		File insert = new File(System.getProperty("user.dir") + "\\resources\\insert_trigger.txt");
		File update = new File(System.getProperty("user.dir") + "\\resources\\update_trigger.txt");
		
		String dropInsert = "drop trigger if exists coffee_project.tri_product_after_insert_coffeeReport";
		String dropUpdate = "drop trigger if exists coffee_project.tri_product_after_update_cofeeReport";
		
		DatabaseDao.getInstance().executeUpdateSQL(dropInsert);;
		DatabaseDao.getInstance().executeUpdateSQL(dropUpdate);;
		
		updateQuery(insert);
		updateQuery(update);

	}

	private void updateQuery(File f) {
		try(BufferedReader br = new BufferedReader(new FileReader(f))){
			StringBuilder statement = new StringBuilder();
	
			for(String line; (line = br.readLine()) !=null;){
				if(!line.isEmpty() && !line.startsWith("--")){
					statement.append(line.trim()+" ");
				}
			}		
			DatabaseDao.getInstance().executeUpdateSQL(statement.toString());;
			statement.setLength(0);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
