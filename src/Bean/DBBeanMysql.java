package Bean;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import org.junit.Test;

public class DBBeanMysql {
	private String driverStr = "com.mysql.jdbc.Driver";
	private String connStr = "jdbc:mysql://localhost:3306/test";
	private String dbusername = "root";
	private String dbpassword = "root";
	private Connection conn = null;
	private static Statement stmt = null;

	private  String driverClass;
	private  String mysqlUrl;
	private  String username;
	private  String password;
	static String strClassName = MysqlUtils.class.getName();
	static Logger logger = Logger.getLogger(strClassName);

	public DBBeanMysql() throws FileNotFoundException {
//		try {
//			Class.forName(driverStr);
//			conn = DriverManager.getConnection(connStr, dbusername, dbpassword);
//			stmt = conn.createStatement();
//		} catch (Exception ex) {
//			System.out.println("数据连接失败！");
//		}
		ReadProperties();
	}

//	public static void main(String args[]) throws SQLException {
//		String sql = "select * from test.order_userinfo limit 10";
//		ResultSet res = ConnectMysql(sql);
//		List<Map<String,Object>> results = ResultSetToList(res);
//		 results = MysqlUtils.ConnectMysql(sql);
//		  for(Map<String,Object> map : results){
//			  System.out.println(map.keySet());
//			  System.out.println(map.toString());
//		  } 
//	}

	@Test
	public void test() throws SQLException{
		String sql = "select * from test.order_userinfo limit 10";
		ResultSet res = ConnectMysql(sql);
		List<Map<String,Object>> results = ResultSetToList(res);
		 results = MysqlUtils.ConnectMysql(sql);
		  for(Map<String,Object> map : results){
			  System.out.println(map.keySet());
			  System.out.println(map.toString());
		  } 
	}
	public int executeUpdate(String s) {
		int result = 0;
		System.out.println("--更新语句:" + s + "\n");
		try {
			result = stmt.executeUpdate(s);
		} catch (Exception ex) {
			System.out.println("执行更新错误！");
		}
		return result;
	}

	public static ResultSet executeQuery(String s) {
		ResultSet rs = null;
		System.out.print("--查询语句:" + s + "\n");
		try {
			rs = stmt.executeQuery(s);
		} catch (Exception ex) {

			System.out.println("执行查询错误！");
			ex.printStackTrace();
		}
		return rs;
	}

	public void execQuery(String s) {
		try {
			stmt.executeUpdate(s);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("执行插入错误！");
		}
	}

	public void close() {
		try {
			stmt.close();
			conn.close();
		} catch (Exception e) {
		}
	}

	public void ReadProperties() throws FileNotFoundException {
		InputStream in = null;
		Properties prop = new Properties();
		try {
			in = MysqlUtils.class
					.getResourceAsStream("/Config/Config.properties");// 路径应放在classpath下
			if (in == null) {
				logger.warning("读取文件失败！请查看路径是否正确！");
			} else {
				prop.load(in);
				driverClass = prop.getProperty("mysql.driverClass");
				mysqlUrl = prop.getProperty("mysql.mysqlUrl");
				username = prop.getProperty("mysql.user");
				password = prop.getProperty("mysql.password");
			}
		} catch (Exception e) {
			logger.warning("文件装载失败 ");
		}
	}

	public ResultSet ConnectMysql(String sql) {

		try {
			ReadProperties();
			Class.forName(driverClass); // 加载MYSQL JDBC驱动程序
			// Class.forName("org.gjt.mm.mysql.Driver");
			// logger.info("成功导入Mysql Driver！");
		} catch (Exception e) {
			// logger.warning("请注意：无法导入Mysql Driver！");
			e.printStackTrace();
		}
		try {
			Connection connect = DriverManager.getConnection(mysqlUrl,
					username, password);
			// 连接URL为 jdbc:mysql//服务器地址/数据库名 ，后面的2个参数分别是登陆用户名和密码
			// logger.info("成功连接Mysql Driver！");
			Statement stmt = connect.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			return rs;
			// return ResultSetToList(rs);
		} catch (Exception e) {
			System.out.print("get data error!");
			logger.warning("请注意：获取数据失败！");
			e.printStackTrace();
		}
		return null;
	}
	
	  public List<Map<String,Object>> ResultSetToList(ResultSet rs) throws SQLException{
		  List<Map<String,Object>> results=new ArrayList<Map<String,Object>>();
		  ResultSetMetaData rsmd = rs.getMetaData();  
		  int colCount=rsmd.getColumnCount();
		  ArrayList<String> colNameList=new ArrayList<String>();
		  for(int i=0;i<colCount;i++){
			  colNameList.add(rsmd.getColumnName(i+1));
//			  System.out.println(rsmd.getTableName(i+1));
		  } 
		  while(rs.next()){
			  Map<String, Object> map=new HashMap<String, Object>();
			  for(int i=0;i<colCount;i++){
				  String key=colNameList.get(i);
				  Object value=rs.getString(colNameList.get(i));
				  map.put(key, value);	  
			  }
			  results.add(map);
//			  System.out.println(rs.getString("name"));
		  }
		  return results;
	  }
}
