package learn.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

public class InsertIntoMysql {
	private static Integer count = 0;

	public static Connection getConnection() throws ClassNotFoundException, SQLException {

		String driver = "com.mysql.jdbc.Driver";
		// URL指向要访问的数据库名mydata
		String url = "jdbc:mysql://localhost:3306/ttpai_pai";
		// MySQL配置时的用户名
		String user = "root";
		// MySQL配置时的密码
		String password = "123456";
		// 加载驱动程序
		Class.forName(driver);
		return DriverManager.getConnection(url, user, password);
	}
	public static void doinsert(String sql){
		try {
			Connection connection = getConnection();
			PreparedStatement pstmt=connection.prepareStatement(sql);//获得预置对象
			// 3.ResultSet类，用来存放获取的结果集！！
			int res=pstmt.executeUpdate();//执行sql语句
			if(res>0){
				count++;
			}
			pstmt.close();
			connection.close();
		} catch (SQLException e) {
			// 数据库连接失败异常处理
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws InterruptedException {
		long startTime = new Date().getTime();
		for (int i = 1101092; i < 6000000; i++) {
			if(i%3000==0){
				long endTime = new Date().getTime();
				System.out.println("总的执行时间是："+(endTime-startTime)/1000);
				Thread.currentThread().sleep(2000L);
			}
			doinsert(getInsertSql(i));
		}
		long endTime = new Date().getTime();
		System.out.println("总的执行时间是："+(endTime-startTime));
	}
	
	
	static String getInsertSql(Integer i){
		return "INSERT INTO ttpai_pai.TTP_DEALER_SEA_FLOW(FLOW_TYPE,DEALER_ID,CHECK_STATUS,CREATE_TIME,MODIFY_TIME) " +
				" VALUES (2,"+ i+",1,'2019-08-27 11:40:27','2019-08-27 11:40:27')" ;
	}

}
