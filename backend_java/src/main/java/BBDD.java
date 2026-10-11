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

    public static boolean deleteMechanic(int id) {
        try {
            connect();
            conection.setAutoCommit(false);

            String sqlRepair = "DELETE FROM repair WHERE mechanicId = ?";
            try (PreparedStatement sentence1 = conection.prepareStatement(sqlRepair)) {
                sentence1.setInt(1, id);
                sentence1.executeUpdate();
            }


            String sqlMechanic = "DELETE FROM mechanic WHERE id = ?";
            try (PreparedStatement sentence = conection.prepareStatement(sqlMechanic)) {
                sentence.setInt(1, id);
                int rowsDeleted = sentence.executeUpdate();

                if (rowsDeleted == 0) {
                    conection.rollback();
                    return false;
                }
            }


            conection.commit();
            return true;

        } catch (Exception e) {

            try {
                if (conection != null) {
                    conection.rollback();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;

        } finally {

            try {
                if (conection != null) {
                    conection.setAutoCommit(true);
                    conection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
