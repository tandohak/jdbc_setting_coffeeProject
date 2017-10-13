package kr.or.dgit.jdbc_setting_coffeeProject.jdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBCon {
	// DBCon을 하나만 만들수 있게 하기위해 static final로 객체를 생성 후 바로 인스턴스화 한다.
	private static final DBCon Instance = new DBCon();
	private Connection connection; // 데이터베이스와 연결을 위한 connection class 객체를 필드로 선언
	
	//DBCon 과 Connection 은 유일성을 위해  get___()메서드만 만든다.
	public static DBCon getInstance() {
		return Instance;
	}

	public Connection getConnection() {
		return connection;
	}

	private DBCon() {
		// a ) conf.properties 파일을 불러오기위해 getPropertys 함수를 만든다.
		// b ) conf.properties 파일을 연결할 Properties 클객체를 생성한다.	
		Properties properties = getPropertys("conf.properties");
		
		/* g ) connection에 getPropertys로 conf.properties를 읽어들인 
		 * 	   properties에서 url, user, password를 string 객체에 받아서 
		 * 	   DriverManger 클래스의 getConnection()메서드로 DB와 연결한다. 
		 *	   
		 *  * ) properties는 hashtable로 구성되어 있으며 key 값과 value 값을 가지고 있다.
		 *      hashmap 과 동일한 사용법으로 사용하면 된다.
		*/		
		
		String user = properties.getProperty("user");
		String url = properties.getProperty("url");
		String password = properties.getProperty("pwd");	
		
		try {
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private Properties getPropertys(String propertiesPath) {
		// c ) conf.properties불러 오기위한 함수.
		// 매개변수는 String으로 propertiesPath를 받는다.( properties 파일의 경로)
		Properties properties = new Properties();
		
		// d ) 파일을 불러와 읽기위해 InputStream 객체를 생성한다.
		// e ) ClassLoader.getSystemResourcesAsStream은 파일의 경로를 읽어와 
		InputStream is = ClassLoader.getSystemResourceAsStream(propertiesPath);
		
		try {
			// f ) properties에 classLoader로 conf.properties를 받은 is를 load()메서드를 통해 파일을 읽어서 저장한다.
			properties.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//conf.properties를 저장한 properties를 return 해준다.
		return properties;
	}
	
	

	
}
