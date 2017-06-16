package Bean;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.Properties;
import java.util.logging.Logger;


public class MysqlUtils {
    private   static  String driverClass;    
    private   static  String mysqlUrl; 
    private   static  String user;
    private   static  String password;
	static String strClassName = MysqlUtils.class.getName();
	static Logger logger = Logger.getLogger(strClassName);
	
  public static void main(String args[]) throws FileNotFoundException {
	  String sql="select * from test.order_userinfo limit 10";
	  List<Map<String,Object>> results=new ArrayList<Map<String,Object>>();
	  results = MysqlUtils.ConnectMysql(sql);
	  for(Map<String,Object> map : results){
		  System.out.println(map.keySet());
		  System.out.println(map.toString());
	  } 
  }
  
  public static void ReadProperties(String Data) throws FileNotFoundException{
	  InputStream in=null;
	  Properties prop=new Properties();
	  try{
		  in = MysqlUtils.class.getResourceAsStream( "/com/config/config.properties");// 路径应放在classpath下
		  if(in==null)
			  logger.warning("读取文件失败！请查看路径是否正确！");
		  else{
			  prop.load(in);
			  driverClass = prop.getProperty(Data+"."+"mysql.driverClass");
			  mysqlUrl = prop.getProperty(Data+"."+"mysql.mysqlUrl");
			  user = prop.getProperty(Data+"."+"mysql.user");
			  password = prop.getProperty(Data+"."+"mysql.password");
		  }		  
	  }
	  catch(Exception e){
		  logger.warning( "文件装载失败 ");
	  }
   }
  public static List<Map<String,Object>> ConnectMysql(String sql){
	  
	  try {
		  ReadProperties_();
		  Class.forName(driverClass);     //加载MYSQL JDBC驱动程序   
	        //Class.forName("org.gjt.mm.mysql.Driver");
//		  logger.info("成功导入Mysql Driver！");
	  }
	  catch (Exception e) {
//		  logger.warning("请注意：无法导入Mysql Driver！");
	      e.printStackTrace();
	  }
	  try {
		  Connection connect = DriverManager.getConnection(
				  mysqlUrl,user,password);
		  //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
//	      logger.info("成功连接Mysql Driver！");
	      Statement stmt = connect.createStatement();
	      ResultSet rs = stmt.executeQuery(sql);
	      return ResultSetToList(rs);
	  }
	  catch (Exception e) {
		  System.out.print("get data error!");
		  logger.warning("请注意：获取数据失败！");
	      e.printStackTrace();
	  }
	return null;
  }
  public static List<Map<String,Object>> ConnectMysql(String sql,String Data){
	  
	  try {
		  ReadProperties(Data);
		  Class.forName(driverClass);     //加载MYSQL JDBC驱动程序   
	        //Class.forName("org.gjt.mm.mysql.Driver");
//		  logger.info("成功导入Mysql Driver！");
	  }
	  catch (Exception e) {
//		  logger.warning("请注意：无法导入Mysql Driver！");
	      e.printStackTrace();
	  }
	  try {
		  Connection connect = DriverManager.getConnection(
				  mysqlUrl,user,password);
		  //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
//	      logger.info("成功连接Mysql Driver！");
	      Statement stmt = connect.createStatement();
	      ResultSet rs = stmt.executeQuery(sql);
	      return ResultSetToList(rs);
	  }
	  catch (Exception e) {
		  System.out.print("get data error!");
		  logger.warning("请注意：获取数据失败！");
	      e.printStackTrace();
	  }
	return null;
  }
  public static void ReadProperties_() throws FileNotFoundException{
	  InputStream in=null;
	  Properties prop=new Properties();
	  try{
		  in = MysqlUtils.class.getResourceAsStream( "/Config/Config.properties");// 路径应放在classpath下
		  if(in==null)
			  logger.warning("读取文件失败！请查看路径是否正确！");
		  else{
			  prop.load(in);
			  driverClass = prop.getProperty("mysql.driverClass");
			  mysqlUrl = prop.getProperty("mysql.mysqlUrl");
			  user = prop.getProperty("mysql.user");
			  password = prop.getProperty("mysql.password");
		  }		  
	  }
	  catch(Exception e){
		  logger.warning( "文件装载失败 ");
	  }
   }
  
  public static List<Map<String,Object>> ConnectMysql_(String sql){
	  
	  try {
		  ReadProperties_();
		  Class.forName(driverClass);     //加载MYSQL JDBC驱动程序   
	        //Class.forName("org.gjt.mm.mysql.Driver");
//		  logger.info("成功导入Mysql Driver！");
	  }
	  catch (Exception e) {
//		  logger.warning("请注意：无法导入Mysql Driver！");
	      e.printStackTrace();
	  }
	  try {
		  Connection connect = DriverManager.getConnection(
				  mysqlUrl,user,password);
		  //连接URL为   jdbc:mysql//服务器地址/数据库名  ，后面的2个参数分别是登陆用户名和密码
//	      logger.info("成功连接Mysql Driver！");
	      Statement stmt = connect.createStatement();
	      ResultSet rs = stmt.executeQuery(sql);
	      return ResultSetToList(rs);
	  }
	  catch (Exception e) {
		  System.out.print("get data error!");
		  logger.warning("请注意：获取数据失败！");
	      e.printStackTrace();
	  }
	return null;
  }
  public static List<Map<String,Object>> ResultSetToList(ResultSet rs) throws SQLException{
	  List<Map<String,Object>> results=new ArrayList<Map<String,Object>>();
	  ResultSetMetaData rsmd = rs.getMetaData();  
	  int colCount=rsmd.getColumnCount();
	  ArrayList<String> colNameList=new ArrayList<String>();
	  for(int i=0;i<colCount;i++){
		  colNameList.add(rsmd.getColumnName(i+1));
//		  System.out.println(rsmd.getTableName(i+1));
	  } 
	  while(rs.next()){
		  Map<String, Object> map=new HashMap<String, Object>();
		  for(int i=0;i<colCount;i++){
			  String key=colNameList.get(i);
			  Object value=rs.getString(colNameList.get(i));
			  map.put(key, value);	  
		  }
		  results.add(map);
//		  System.out.println(rs.getString("name"));
	  }
	  return results;
  }
  
}
