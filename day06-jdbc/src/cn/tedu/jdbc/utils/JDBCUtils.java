package cn.tedu.jdbc.utils;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class JDBCUtils {
	//为了保证工具类只能使用类名来调用，需要把构造函数私有化
	private JDBCUtils(){
		
	}
	//为了让prop对象能够全局使用，所以定义在类内方法外。
	private static Properties prop = null;
	//由于我们指向加载一次配置文件，所以将代码写入的静态块
	static{
		prop = new Properties();
		//通过类加载器加载src目录下的文件conf.properties 使用方法，1.获取类加载器。2.获取src目录(getResource())3.提供配置文件的路径,也就是调用getPath()
		try {
			prop.load(new FileInputStream(new File(JDBCUtils.class.getClassLoader()
					.getResource("conf.properties").getPath())));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//创建连接
	public static Connection getConnection() throws Exception{
		//通过prop对象获取配置文件中的driver键
		Class.forName(prop.getProperty("driver"));
										//通过prop对象获取配置文件中的url，username，password
		return DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));
	}

	//关闭资源
	public static void close(Connection conn,Statement stat,ResultSet rs){

		if(rs != null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				rs = null;
			}
		}
		if(stat != null){
			try {
				stat.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				stat = null;
			}
		}
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				conn = null;
			}
		}
	}
}
