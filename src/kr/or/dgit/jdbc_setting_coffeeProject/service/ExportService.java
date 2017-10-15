package kr.or.dgit.jdbc_setting_coffeeProject.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import kr.or.dgit.jdbc_setting_coffeeProject.Config;
import kr.or.dgit.jdbc_setting_coffeeProject.dao.DatabaseDao;
import kr.or.dgit.jdbc_setting_coffeeProject.jdbc.JdbcUtil;

public class ExportService implements DbService {
	private static final ExportService instance = new ExportService();
	
	
	private ExportService() {}

	public static ExportService getInstance() {
		return instance;
	}

	@Override
	public void service() {
		// config에 저장된 db_name을 받아온다.
		DatabaseDao.getInstance().executeUpdateSQL("USE " + Config.DB_NAME);
		
		// 디렉토리폴더가 없을 시에 새 폴더 생성
		//Config에 저장된 export_dir를 바다와서 마지막 "\\"를 제거한다뒤 File을 만든다.
		String filePath = Config.EXPORT_DIR;
		File file = new File(filePath.substring(0, filePath.lastIndexOf("\\")));
		if(!file.exists()){
		    //디렉토리 생성 메서드
		    file.mkdirs();
		   
	    }


		// TABLE_NAME을 for each 문을 이용하여 export한다.
		for(String tblName : Config.TABLE_NAME){
			exportData(tblName, String.format("select * from %s", tblName) , Config.getFilePath(tblName, true));
		}
	}


	private void exportData(String tblName ,String sql , String exportPath) {
		// 받은 데이터를 저장할 StringBuilder sb
		StringBuilder sb = new StringBuilder();
		// sql 쿼리의 결과값을 받기 위한 Resultset
		ResultSet rs = null;
		
		try {
			rs = DatabaseDao.getInstance().exuteQuerySQL(sql);//rs에 매개변수로 받아온 sql문을 실행후 결과값 저장
			int colCnt = rs.getMetaData().getColumnCount();//rs에 받아온 데이터 컬럼의 개수
			
			// 결과값 읽어서 sb에 저장하기 위한 while문
			while(rs.next()){
				// rs.next()에서 받아온 row의 column을 받아오기 위한 for문
				// index는 1부터 시작한다.
				for(int i = 1; i <= colCnt; i++){
					sb.append(rs.getObject(i) + "	");//sb에 rs의 i번째 데이터를 받아서 저장한다
				}				
//				sb.replace(sb.length() -1, sb.length(), ""); //마지막 라인의 comma 제거
				sb.append("\r\n");//줄바꿈
			}
			
			//sb에 저장된 데이터를 파일로 쓰기위한 메서드 매개변수로 export할 데이터 sb와 경로 exportPath가 있다.
			
			backupFileWrite(sb.toString(), exportPath);
		} catch (SQLException e) {				
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			//ResultSet , OutputStream 을 종료하기위해 jdbcUtil에 메서드를 만들었다.
			JdbcUtil.close(rs);
		}
	}


	private void backupFileWrite(String str, String filePath) throws FileNotFoundException, IOException {
		//매개변수로 받은 데이터, 파일 경로를 받아
		// OutputStreamWriter로 .txt 파일을 경로에 만든다.
		// 개선된 try문을 이용하여 OutputStreamWriter가 완료되면 자동으로 닫히게 된다. close() 메서드를 굳이 쓸 필요가 없다.
		
		
		try(OutputStreamWriter dos = new OutputStreamWriter(new FileOutputStream(filePath))){
			dos.write(str);
		}
	}

}
