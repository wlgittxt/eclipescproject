package cn.tedu.jdbc.batch;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import cn.tedu.jdbc.utils.JDBCUtils;
/**
 * Statement������
 * �ŵ㣺
 * 	1.����ִ�в�ͬ�����sql���
 * ȱ�㣺
 * 	1.û��Ԥ���빦��
 *  2.ÿ�ζ���Ҫ����������sql��䣬���ܹ���sql���������������ݿ��У������á�
 *  3.ִ�е�Ч�ʽϵ͡�
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
			System.out.println("Statement������ִ�����");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.close(conn, stat, rs);
		}
	}

}
