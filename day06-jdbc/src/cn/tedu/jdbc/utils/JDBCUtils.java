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
	//Ϊ�˱�֤������ֻ��ʹ�����������ã���Ҫ�ѹ��캯��˽�л�
	private JDBCUtils(){
		
	}
	//Ϊ����prop�����ܹ�ȫ��ʹ�ã����Զ��������ڷ����⡣
	private static Properties prop = null;
	//��������ָ�����һ�������ļ������Խ�����д��ľ�̬��
	static{
		prop = new Properties();
		//ͨ�������������srcĿ¼�µ��ļ�conf.properties ʹ�÷�����1.��ȡ���������2.��ȡsrcĿ¼(getResource())3.�ṩ�����ļ���·��,Ҳ���ǵ���getPath()
		try {
			prop.load(new FileInputStream(new File(JDBCUtils.class.getClassLoader()
					.getResource("conf.properties").getPath())));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//��������
	public static Connection getConnection() throws Exception{
		//ͨ��prop�����ȡ�����ļ��е�driver��
		Class.forName(prop.getProperty("driver"));
										//ͨ��prop�����ȡ�����ļ��е�url��username��password
		return DriverManager.getConnection(prop.getProperty("url"), prop.getProperty("username"), prop.getProperty("password"));
	}

	//�ر���Դ
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
