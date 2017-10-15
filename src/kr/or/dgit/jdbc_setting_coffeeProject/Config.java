package kr.or.dgit.jdbc_setting_coffeeProject;

//DB 경로와 백업파일 경로를 위한 Config 클래스
public class Config {
	//DB명
	public static final String DB_NAME = "coffee_project";
	//TABLE명
	public static final String[] TABLE_NAME = {"productcode","product","coffeereport"};
	//사용자의 디렉토리 경로
	public static final String USER_DIR = System.getProperty("user.dir") + "\\";
	
	// 백업 , 복원을 위한 디렉토리 경로
	// sql문을 불러오기 위한 디렉토리 경로
	public static final String EXPORT_DIR = USER_DIR + "BackupFiles\\";
	public static final String IMPORT_DIR = USER_DIR + "DataFiles\\";
	public static final String CREATE_SQL = USER_DIR + "resources\\create_sql.txt";
	
	//경로를 받기위한 getFilePath 메서드
	public static final String getFilePath(String tableName, boolean isExport){
		StringBuilder sb = new StringBuilder();
		// StringBuilder sb 에 매개변수 tableName을 넣어 파일경로를 리턴한다.
		sb.append(isExport ? EXPORT_DIR : IMPORT_DIR).append(tableName).append(".txt");		
		
		// 경로의 '\\'를 '/'로 바꿔준다.
		return sb.toString().replace("\\","/"); 
	}
}
