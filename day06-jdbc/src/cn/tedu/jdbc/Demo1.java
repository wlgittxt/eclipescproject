package cn.tedu.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


//JDBC6������
public class Demo1 {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		//1.ע�����ݿ�����
		//�ֶ�ע��һ��Driver�࣬��Դ��ײ�Ҳע����һ��Driver�࣬ע�������������������������֣�
		//���ܳ����ܹ��������У�������Ȼ��Ҫ���������޸�.
		//������Driver���ʱ�򣬵�����mysqljar�е�ʵ����Driver��������ڶ����������ı䣬�����������ݿ⣬
		//����Ҫ�޸Ĵ���ĵ�������������͵����γ���ǿ�ҵ���ϣ�����Ӧ�ñ������������
		Class.forName("com.mysql.jdbc.Driver");
		//2.��ȡ���ݿ�����
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb1", "root", "root");
//		DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb1?user=root&password=root")
		//3.��ȡ������
		Statement stat = conn.createStatement();
		//4.���ô���������sql�������ؽ��
		ResultSet rs = stat.executeQuery("select * from emp");
		//5.�������
		while(rs.next()){
			int id = rs.getInt(1);
			String name = rs.getString("name");
			System.out.println("id:"+id+">>>name:"+name);
		}
		//6.�ر���Դ
		//�ȴ����ĺ�ر�
		rs.close();
		stat.close();
		conn.close();
		
	}
	

}
