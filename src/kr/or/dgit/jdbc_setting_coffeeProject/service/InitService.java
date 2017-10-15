package kr.or.dgit.jdbc_setting_coffeeProject.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import kr.or.dgit.jdbc_setting_coffeeProject.dao.DatabaseDao;

public class InitService implements DbService {
	private static final InitService instance = new InitService();	
	
	
	public static InitService getInstance() {
		return instance;
	}
	
	private InitService() {}

	@Override
	public void service() {
		// System.getProperty("user.dir") 현재 사용하는 유저의 디렉토리 경로를 가져온다.
		// 미리 만들어 놓은 create_sql.txt 파일을 불러와 File f 객체에 인스턴스화 한다.
		File f = new File(System.getProperty("user.dir") + "\\resources\\create_sql.txt");
		
		// FileReader로 읽은 파일 f를 
		// BufferedReader로 읽는다
		try (BufferedReader br = new BufferedReader(new FileReader(f));) {
			// 읽어들인 파일을 StringBuilder로 받기위해 StringBuilder 객체를 생성한다.
			
			
			//String line에 br.readLine()으로 읽어들인 내용을 넣는다.
			// line이 null이 될때까지 반복한다.
			// br.readLine()을 통해 더이상 읽어들일 파일의 내용이 없으면
			// br.readLine()은 null을 반환한다.
			StringBuilder statement = new StringBuilder();
			for (String line; (line = br.readLine()) != null;) {
				if (!line.isEmpty() && !line.startsWith("--")) {
					//line의 내용이 비었거나 line의 첫 시작이 '--'일경우 무시한다.
					//trim()메서드로 공백제거후 statement에 추가한다.
					statement.append(line.trim() + " ");
				}
				if (line.endsWith(";")) {
					//line의 마지막이 ';'일경우 DB에 statement에 저장된 SQL 문을 UPDATE한다.
					DatabaseDao.getInstance().executeUpdateSQL(statement.toString());
					
					//SQL문을 수행한 후 statement를 setLength(0)메서드로 초기화한다.
					//setLength(int) String의 길이를 매개변수만큼 바꾼다.
					statement.setLength(0);
				}				
			}			
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
			
	}
	


}
