import java.sql.*;

public class BBDD {
    private static String bbdd=DatabaseConfig.getDbUrl();
    private static Connection conection;

    public static void connect() throws SQLException {
        conection= DriverManager.getConnection(bbdd,DatabaseConfig.getDbUser(),DatabaseConfig.getDbPassword());

    }
    public static void disconnect() throws SQLException{
        conection.close();
    }

    public static boolean addMechanic(String name_,String last_name,String phone_number) throws SQLException {
        connect();
        String sql="INSERT INTO mechanic(name_,last_name,phone_number) values(?,?,?)";
        PreparedStatement sentence=conection.prepareStatement(sql);
        sentence.setString(1,name_);
        sentence.setString(2,last_name);
        sentence.setString(3,phone_number);
        int insertrows=sentence.executeUpdate();
        disconnect();
        if (insertrows==0) return false;
        else return true;
    }

    public static boolean modifyMechanic(int id, String name, String last_name,String phone_number) throws SQLException{
        connect();
        String sql="UPDATE mechanic set name_=?, last_name=?,phone_number=?  where id=?";
        PreparedStatement sentence=conection.prepareStatement(sql);
        sentence.setString(1,name);
        sentence.setString(2,last_name);
        sentence.setString(3,phone_number);
        sentence.setInt(4,id);
        int insertrows=sentence.executeUpdate();
        disconnect();
        if (insertrows==0) return false;
        else return true;
    }




}
