package cn.tedu.jdbc.batch;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import cn.tedu.jdbc.utils.JDBCUtils;
/**
 * Statement批处理：
 * 优点：
 * 	1.可以执行不同语义的sql语句
 * 缺点：
 * 	1.没有预编译功能
 *  2.每次都需要传输完整的sql语句，不能够将sql语句的主干留在数据库中，作复用。
 *  3.执行的效率较低。
 * 
 * 
 * @author Administrator
 *
 */
public class StateBatchDemo {
	/*
	 	use mydb1;
	 	create table student(id int,name varchar(20));
	 	insert into student values(1,'ls');
	 	insert into student values(2,'zds');
	 	insert into student values(3,'ww');
	 	insert into student values(4,'zl');
	  
	 */
	public static void main(String[] args) {
		Connection conn = null;
		Statement stat = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			stat = conn.createStatement();
			stat.addBatch("use mydb1;");
			stat.addBatch("create table student(id int,name varchar(20));");
			stat.addBatch("insert into student values(1,'ls');");
			stat.addBatch("insert into student values(2,'zds');");
			stat.addBatch("insert into student values(3,'ww');");
			stat.addBatch("insert into student values(4,'zl');");
			stat.executeBatch();
			System.out.println("Statement批处理执行完成");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.close(conn, stat, rs);
		}
	}

}
