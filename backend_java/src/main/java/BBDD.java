import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BBDD {
    public static Scanner entrada = new Scanner(System.in);
    private static String bbdd = DatabaseConfig.getDbUrl();
    private static Connection conection;

    public static void connect() throws SQLException {
        conection = DriverManager.getConnection(bbdd, DatabaseConfig.getDbUser(), DatabaseConfig.getDbPassword());

    }

    public static void disconnect() throws SQLException {
        conection.close();
    }

    public static boolean addMechanic(String name_, String last_name, String phone_number) throws SQLException {
        connect();
        String sql = "INSERT INTO mechanic(name_,last_name,phone_number) values(?,?,?)";
        PreparedStatement sentence = conection.prepareStatement(sql);
        sentence.setString(1, name_);
        sentence.setString(2, last_name);
        sentence.setString(3, phone_number);
        int insertrows = sentence.executeUpdate();
        disconnect();
        if (insertrows == 0) return false;
        else return true;
    }

    public static boolean modifyMechanic(int id, String name, String last_name, String phone_number) throws SQLException {
        connect();
        String sql = "UPDATE mechanic set name_=?, last_name=?,phone_number=?  where id=?";
        PreparedStatement sentence = conection.prepareStatement(sql);
        sentence.setString(1, name);
        sentence.setString(2, last_name);
        sentence.setString(3, phone_number);
        sentence.setInt(4, id);
        int insertrows = sentence.executeUpdate();
        disconnect();
        if (insertrows == 0) return false;
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

    public static void viewMechanicByName() throws SQLException {
        connect();
        System.out.println("Give me mechanic name: ");
        String name = entrada.nextLine();
        String sql = "SELECT * FROM mechanic where name_ like ?";
        CallableStatement sentence = conection.prepareCall(sql);
        sentence.setString(1,name+"%");
        ResultSet result = sentence.executeQuery();
        while (result.next()) {
            int id = result.getInt(1);
            String name_ = result.getString(2);
            String last_name = result.getString(3);
            String phone_number = result.getString(4);
            System.out.println(id + " " + name_ + " " + last_name + " " + phone_number);
        }
        disconnect();
    }

    public static void viewRepairsByMechanicName() throws SQLException {
        connect();
        System.out.println("Give me mechanic name: ");
        String name=entrada.nextLine();
        String sql="SELECT * from repair join mechanic on repair.mechanicId=mechanic.id where mechanic.name_=?";
        CallableStatement sentence=conection.prepareCall(sql);
        sentence.setString(1,name);
        ResultSet result = sentence.executeQuery();
        while (result.next()) {
            int mechanicId = result.getInt(1);
            int carId = result.getInt(2);
            Date date_ = result.getDate(3);
            String description = result.getString(4);
            double cost=result.getDouble(5);
            System.out.println(mechanicId + " " + carId + " " + date_ + " " + description+" "+cost);
        }
        disconnect();

    }

    public static boolean addClient(String name_, String last_name, String phone_number) throws SQLException {
        connect();
        String sql = "INSERT INTO client(name_,last_name,phone_number) values(?,?,?)";
        PreparedStatement sentence = conection.prepareStatement(sql);
        sentence.setString(1, name_);
        sentence.setString(2, last_name);
        sentence.setString(3, phone_number);
        int insertrows = sentence.executeUpdate();
        disconnect();
        if (insertrows == 0) return false;
        else return true;
    }

    public static boolean modifyClient(int id,String name,String last_name,String phone_number) throws SQLException {
        connect();
        String sql = "UPDATE client set name_=?, last_name=?,phone_number=?  where id=?";
        PreparedStatement sentence = conection.prepareStatement(sql);
        sentence.setString(1, name);
        sentence.setString(2, last_name);
        sentence.setString(3, phone_number);
        sentence.setInt(4, id);
        int insertrows = sentence.executeUpdate();
        disconnect();
        if (insertrows == 0) return false;
        else return true;
    }

    public static boolean deleteClient(int id) {
        try {
            connect();
            conection.setAutoCommit(false);

            String sqlRepair = "DELETE repair from repair join car on repair.carId= car.id where car.idClient = ?";
            try (PreparedStatement sentence1 = conection.prepareStatement(sqlRepair)) {
                sentence1.setInt(1, id);
                int rowsDeleted=sentence1.executeUpdate();
                if (rowsDeleted == 0) {
                    conection.rollback();
                    return false;
                }
            }

            String sqlCar="DELETE FROM car where idClient=?";
            try(PreparedStatement sentence2=conection.prepareStatement(sqlCar)){
                sentence2.setInt(1,id);
                int rowsDeleted=sentence2.executeUpdate();
                if(rowsDeleted==0){
                    conection.rollback();
                    return false;
                }
            }


            String sqlMechanic = "DELETE FROM client WHERE id = ?";
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

    public static void viewClientByName() throws SQLException {
        connect();
        System.out.println("Give me client name: ");
        String name = entrada.nextLine();
        String sql = "SELECT * FROM client where name_ like ?";
        CallableStatement sentence = conection.prepareCall(sql);
        sentence.setString(1,name+"%");
        ResultSet result = sentence.executeQuery();
        while (result.next()) {
            int id = result.getInt(1);
            String name_ = result.getString(2);
            String last_name = result.getString(3);
            String phone_number = result.getString(4);
            System.out.println(id + " " + name_ + " " + last_name + " " + phone_number);
        }
        disconnect();
    }

    public static void viewCarByClientName() throws SQLException {
        connect();
        System.out.println("Give me client name: ");
        String name = entrada.nextLine();
        String sql = "SELECT * FROM car JOIN client ON client.id=car.idClient where client.name_ like ?";
        CallableStatement sentence = conection.prepareCall(sql);
        sentence.setString(1,name+"%");
        ResultSet result = sentence.executeQuery();
        while (result.next()) {
            int id = result.getInt(1);
            String brand = result.getString(2);
            String model = result.getString(3);
            int idClient = result.getInt(4);
            System.out.println(id + " " + brand + " " + model + " " + idClient);
        }
        disconnect();
    }

    public static void viewRepairsByClientName() throws SQLException {
        connect();
        List<Integer> carIds= new ArrayList<>();
        System.out.println("Give me client name: ");
        String name = entrada.nextLine();
        String sql1="SELECT car.id from car join client on car.idClient=client.id where client.name_ like ?";
        CallableStatement sentence1=conection.prepareCall(sql1);
        sentence1.setString(1,name+"%");
        ResultSet result1=sentence1.executeQuery();
        while(result1.next()){
            int carId=result1.getInt(1);
            carIds.add(carId);
        }
    for(int i = 0; i < carIds.toArray().length; i++) {
        String sql = "SELECT * FROM repair JOIN car ON repair.carId=car.id where car.id=?";
        CallableStatement sentence = conection.prepareCall(sql);
        sentence.setString(1, String.valueOf(carIds.get(i)));
        ResultSet result = sentence.executeQuery();
        while (result.next()) {
            int id = result.getInt(1);
            String brand = result.getString(2);
            String model = result.getString(3);
            int idClient = result.getInt(4);
            System.out.println(id + " " + brand + " " + model + " " + idClient);
        }
    }
        disconnect();
    }

}