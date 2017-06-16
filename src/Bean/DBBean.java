package Bean;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
public class DBBean {
//    private String driverStr = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
//    private String connStr = "jdbc:sqlserver://localhost:1433; DatabaseName=JXP";
//    private String dbusername = "sa";
//    private String dbpassword = "123456";
//    private Connection conn = null;
//    private Statement stmt = null;

	private String driverStr = "com.mysql.jdbc.Driver";
	private String connStr = "jdbc:mysql://localhost:3306/test";
	private String dbusername = "root";
	private String dbpassword = "root";
	private Connection conn = null;
	private static Statement stmt = null;
    
    public DBBean()
    {
        try
        {
        	 System.out.println(driverStr+connStr+dbusername+dbpassword);
             
            Class.forName(driverStr);
            conn = DriverManager.getConnection(connStr, dbusername, dbpassword);
            stmt = conn.createStatement();
        } 
        catch (Exception ex) {
            System.out.println("数据连接失败！");
            ex.printStackTrace();
        } 
        
    }

    @Test
    public void test() throws SQLException{
		String sql = "select * from test.order_userinfo where username='admin' limit 10";
		ResultSet res = executeQuery(sql);
//		System.out.println(res.toString());
		List<Map<String,Object>> results = ResultSetToList(res);
		 results = MysqlUtils.ConnectMysql(sql);
		  for(Map<String,Object> map : results){
			  System.out.println(map.keySet());
			  System.out.println(map.toString());
		  } 
    }
    
    public int executeUpdate(String s) {
        int result = 0;
        System.out.println("--更新语句:"+s+"\n");
        try {
            result = stmt.executeUpdate(s);
        } catch (Exception ex) {
            System.out.println("执行更新错误！");
        }
        return result;
    }

    public ResultSet executeQuery(String s) {
        ResultSet rs = null;
        System.out.print("--查询语句:"+s+"\n");
        try {
            rs = stmt.executeQuery(s);
        } catch (Exception ex) {
            System.out.println("执行查询错误！");
            ex.printStackTrace();
        }
        return rs;
    }
    public void execQuery(String s){
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
    
    public static List<Map<String,Object>> ResultSetToList(ResultSet rs) throws SQLException{
  	  List<Map<String,Object>> results=new ArrayList<Map<String,Object>>();
  	  ResultSetMetaData rsmd = rs.getMetaData();  
  	  int colCount=rsmd.getColumnCount();
  	  ArrayList<String> colNameList=new ArrayList<String>();
  	  for(int i=0;i<colCount;i++){
  		  colNameList.add(rsmd.getColumnName(i+1));
//  		  System.out.println(rsmd.getTableName(i+1));
  	  } 
  	  while(rs.next()){
  		  Map<String, Object> map=new HashMap<String, Object>();
  		  for(int i=0;i<colCount;i++){
  			  String key=colNameList.get(i);
  			  Object value=rs.getString(colNameList.get(i));
  			  map.put(key, value);	  
  		  }
  		  results.add(map);
//  		  System.out.println(rs.getString("name"));
  	  }
  	  return results;
    }
}
