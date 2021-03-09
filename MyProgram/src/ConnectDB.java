import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class ConnectDB {
    private long id;
    private String password;
    HashMap perinfor=new HashMap<String,String>();
    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;
    ConnectDB(long id,String password){
        this.id=id;
        this.password=password;
        try{
//        Class.forName("com.mysql.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=UTC", "root", "Scp-3999");
        stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY );
        }
        catch (Exception e){
            System.out.println("数据库连接错误");
            e.printStackTrace();
        }
    }
    ConnectDB(){
        try{
//            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?serverTimezone=UTC", "root", "Scp-3999");
            stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY );
        }
        catch (Exception e){
            System.out.println("数据库连接错误");
        }
    }
    HashMap getUserinf(long id) throws Exception{
        HashMap oneinfor=new HashMap<String,String>();
        if(id==1930026100){
            return oneinfor;
        }
        rs=stmt.executeQuery("select * from Student where id="+Long.toString(id));
        ResultSetMetaData data = rs.getMetaData();
        rs.next();
        for (int i = 1; i <= data.getColumnCount(); i++){
            if((!data.getColumnName(i).equals("Password"))){
                oneinfor.put(data.getColumnName(i),rs.getString(data.getColumnName(i)));
            }
        }
        rs.close();
        return oneinfor;
    }
    public HashMap<String, String> getPerinfor() throws Exception {
        perinfor=this.getUserinf(id);
        return perinfor;
    }
    boolean testpassword() throws Exception {
        try{
        rs=stmt.executeQuery("select * from Student where id="+Long.toString(id));}
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        rs.next();
        try{
        if(password.equals(rs.getString("Password"))){
            rs.close();
            return true;
        }else{
            rs.close();
            return false;
        }}catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
    void getout() throws Exception{
        stmt.close();
        conn.close();
    }
    boolean changeinfor(String name,String Password) throws Exception {
        conn.setAutoCommit(false);
        try {
            stmt.executeUpdate("update Student set Name=\'"+name+"\' where ID="+Long.toString(id));
            stmt.executeUpdate("update Student set Password=\'"+Password+"\' where ID="+Long.toString(id));
            conn.commit();
        }catch(Exception e){
            conn.rollback();
            e.printStackTrace();
            return false;
        }finally {
            getout();
        }
        return true;
    }
    boolean delete(long ID) throws Exception{
        conn.setAutoCommit(false);
        try {
            stmt.executeUpdate("delete from Student where ID="+Long.toString(ID));
            conn.commit();
        }catch(Exception e){
            conn.rollback();
            e.printStackTrace();
            return false;
        }
        return true;
    }
    boolean judge() throws SQLException {
        rs= stmt.executeQuery("select * from Student where id="+Long.toString(id));
        rs.next();
        if(Integer.valueOf(rs.getString("Controller"))==1){
            return true;
        }
        return false;
    }
    void insertValue(String name,String Password) throws Exception{
        conn.setAutoCommit(false);
        try {
            SimpleDateFormat ft=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            stmt.executeUpdate("insert into Student (Name,Password,Register_time) values (\'"+name+"\',\'"+Password+"\',\'"+ft.format(new Date()) +"\')");
            conn.commit();
        }catch(Exception e) {
            conn.rollback();
            e.printStackTrace();
        }
    }
   void insertController(long id) throws Exception{
       conn.setAutoCommit(false);
       try {
           stmt.executeUpdate("update Student set Controller=true where id="+Long.toString(id));
           conn.commit();
       }catch(Exception e) {
           conn.rollback();
           e.printStackTrace();
       }
        }
   ArrayList getallinfor() throws Exception{
        ArrayList all=new ArrayList<HashMap>();
        rs=stmt.executeQuery("select * from Student");
       ResultSetMetaData data = rs.getMetaData();
        while(rs.next()){
            if(rs.getString("ID")==Long.toString(1930026100)){
                continue;
            }
            HashMap<String,String> one=new HashMap<String,String>();
            for (int i = 1; i <= data.getColumnCount(); i++){
                    one.put(data.getColumnName(i),rs.getString(data.getColumnName(i)));
                }
            all.add(one);
            }
       rs.close();
        return all;
    }
    long getnewoneid () throws Exception {
        rs=stmt.executeQuery("select * from Student");
        rs.last();
        Long newid=rs.getLong("ID");
        rs.close();
        return newid;
    }
    String getnewonetime() throws Exception{
        rs=stmt.executeQuery("select * from Student");
        rs.last();
        String newtime=rs.getString("Register_time");
        rs.close();
        return newtime;
    }
    void changepass(String password,Long id) throws SQLException {
        conn.setAutoCommit(false);
        try {
            stmt.executeUpdate("update Student set Password='"+password+"' where id="+Long.toString(id));
            conn.commit();
        }catch (Exception e){
            conn.rollback();
            e.printStackTrace();
        }
    }
    boolean deletecontroller(long id) throws SQLException {
        conn.setAutoCommit(false);
        try {
            stmt.executeUpdate("update Student set Controller=false where id=" + Long.toString(id));
            conn.commit();
        }catch (Exception e){
            conn.rollback();
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
