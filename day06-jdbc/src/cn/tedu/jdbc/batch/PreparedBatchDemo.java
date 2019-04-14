package cn.tedu.jdbc.batch;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import cn.tedu.jdbc.utils.JDBCUtils;
/**
 * PreparedStatement批处理：
 * 优点：
 * 	1.有预编译功能
 *  2.可以将sql语句的主干留在数据库中，供sql参数在传输时候，快速调用。
 *  3.执行效率较高。
 * 缺点：
 * 	1.不能传输不同语义的sql
 * @author Administrator
 *
 */
public class PreparedBatchDemo {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConnection();
			ps = conn.prepareStatement("insert into student values(?,?)");
			for(int i=0;i<10001;i++){
				//设置参数1和2
				ps.setInt(1,i);
				ps.setString(2, "name"+i);
				//设置完成之后添加到批中
				ps.addBatch();
				if(i%100 == 0){//每100条数据执行一次批
					ps.executeBatch();
					System.out.println("当前第"+i/100+"次");
					//执行完一次批后，批中内容可以清空
					ps.clearBatch();
				}
				
			}
			//此句不可少，如果不写的话，有一些不满循环100的数据无法执行
			ps.executeBatch();
			System.out.println("PreparedStatement批处理完成");
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			JDBCUtils.close(conn, ps, rs);
		}
	}

}
