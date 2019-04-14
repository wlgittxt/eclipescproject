package cn.tedu.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


//JDBC6步开发
public class Demo1 {
	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		//1.注册数据库驱动
		//手动注册一遍Driver类，在源码底层也注册了一遍Driver类，注册两次这种情况，不被允许出现，
		//尽管程序能够正常运行，但是依然需要我们做出修改.
		//在引入Driver类的时候，导入了mysqljar中的实现类Driver，如果后期对数据做出改变，换成其他数据库，
		//则需要修改代码的导包，这样代码和导包形成了强烈的耦合，我们应该避免这种情况。
		Class.forName("com.mysql.jdbc.Driver");
		//2.获取数据库连接
		Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb1", "root", "root");
//		DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb1?user=root&password=root")
		//3.获取传输器
		Statement stat = conn.createStatement();
		//4.利用传输器传输sql。并返回结果
		ResultSet rs = stat.executeQuery("select * from emp");
		//5.遍历结果
		while(rs.next()){
			int id = rs.getInt(1);
			String name = rs.getString("name");
			System.out.println("id:"+id+">>>name:"+name);
		}
		//6.关闭资源
		//先创建的后关闭
		rs.close();
		stat.close();
		conn.close();
		
	}
	

}
