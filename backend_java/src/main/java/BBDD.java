import java.sql.*;
import java.time.LocalDate;
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
            int id=result.getInt(1);
            int mechanicId = result.getInt(2);
            int carId = result.getInt(3);
            Date date_ = result.getDate(4);
            String description = result.getString(5);
            double cost=result.getDouble(6);
            System.out.println(id+" "+mechanicId + " " + carId + " " + date_ + " " + description+" "+cost);
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

    public static boolean deleteClient(int id) throws SQLException {

            connect();

            String sqlRepair = "DELETE repair FROM repair JOIN car ON repair.carId = car.id WHERE car.idClient = ?";
            PreparedStatement sentence1 = conection.prepareStatement(sqlRepair);
            sentence1.setInt(1, id);
            sentence1.executeUpdate();


            String sqlCar = "DELETE FROM car WHERE idClient = ?";
            PreparedStatement sentence2 = conection.prepareStatement(sqlCar);
            sentence2.setInt(1, id);
            sentence2.executeUpdate();


            String sqlClient = "DELETE FROM client WHERE id = ?";
            PreparedStatement sentence = conection.prepareStatement(sqlClient);
            sentence.setInt(1, id);
            int rowsDeleted = sentence.executeUpdate();


            return true;

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
            int id=result.getInt(1);
            int mechanicId = result.getInt(2);
            int carId= result.getInt(3);
            Date date = result.getDate(4);
            String description = result.getString(5);
            double cost=result.getDouble(6);
            System.out.println(id+" "+mechanicId + " " + carId + " " + date + " " + description+" "+cost);
        }
    }
        disconnect();
    }

    public static boolean addCar(String brand, String model, int id) throws SQLException {
        connect();
        String sql = "INSERT INTO car(brand,model,idClient) values(?,?,?)";
        PreparedStatement sentence = conection.prepareStatement(sql);
        sentence.setString(1, brand);
        sentence.setString(2, model);
        sentence.setInt(3, id);
        int insertrows = sentence.executeUpdate();
        disconnect();
        if (insertrows == 0) return false;
        else return true;
    }

    public static boolean modifyCar(String brand, String model, int id) throws SQLException {
        connect();
        String sql = "UPDATE car set brand=?, model=? where id=?";
        PreparedStatement sentence = conection.prepareStatement(sql);
        sentence.setString(1, brand);
        sentence.setString(2, model);
        sentence.setInt(3, id);
        int insertrows = sentence.executeUpdate();
        disconnect();
        if (insertrows == 0) return false;
        else return true;
    }

    public static boolean deleteCar(int id) throws SQLException {
        connect();
        String sql="DELETE FROM car where id=?";
        PreparedStatement sentence=conection.prepareStatement(sql);
        sentence.setInt(1,id);
        int insertrows = sentence.executeUpdate();
        disconnect();
        if (insertrows == 0) return false;
        else return true;
    }

    public static void viewCarByBrand() throws SQLException {
        System.out.println("Give me the brand: ");
        String brand=entrada.nextLine();
        connect();
        String sql="SELECT * FROM car where brand like ?";
        CallableStatement sentence=conection.prepareCall(sql);
        sentence.setString(1,brand+"%");
        ResultSet result = sentence.executeQuery();
        while (result.next()) {
            int id = result.getInt(1);
            String brand_ = result.getString(2);
            String model = result.getString(3);
            int idClient = result.getInt(4);
            System.out.println(id + " " + brand_ + " " + model + " " + idClient);
        }
        disconnect();
    }

    public static void viewRepairsByCarId() throws SQLException {
        System.out.println("Give me car's id: ");
        int id=entrada.nextInt();
        connect();
        String sql="SELECT * FROM repair where carId=?";
        CallableStatement sentence=conection.prepareCall(sql);
        sentence.setInt(1,id);
        ResultSet result = sentence.executeQuery();
        while (result.next()) {
            int id_=result.getInt(1);
            int mechanicId = result.getInt(2);
            int carId= result.getInt(3);
            Date date = result.getDate(4);
            String description = result.getString(5);
            double cost=result.getDouble(6);
            System.out.println(id_+" "+mechanicId + " " + carId + " " + date + " " + description+" "+cost);
        }
        disconnect();
    }

    public static boolean addRepair(int mechanicId, int carId, Date date_, String description, double cost) throws SQLException {
        connect();
        String sql="INSERT INTO repair(mechanicId,carId,date_,description_,cost) values(?,?,?,?,?)";
        PreparedStatement sentence=conection.prepareStatement(sql);
        sentence.setInt(1,mechanicId);
        sentence.setInt(2,carId);
        sentence.setDate(3,date_);
        sentence.setString(4,description);
        sentence.setDouble(5,cost);
        int insertrows = sentence.executeUpdate();
        disconnect();
        if (insertrows == 0) return false;
        else return true;

    }


    public static boolean modifyRepair(int id, LocalDate date, double cost) throws SQLException {
        connect();
        String sql="UPDATE repair set date_=?, cost=? where id=?";
        PreparedStatement sentence=conection.prepareStatement(sql);
        sentence.setDate(1, Date.valueOf(date));
        sentence.setDouble(2,cost);
        sentence.setInt(3,id);
        int insertrows = sentence.executeUpdate();
        disconnect();
        if (insertrows == 0) return false;
        else return true;
    }

    public static boolean deleteRepair(int id) throws SQLException {
        connect();
        String sql="DELETE FROM repair where id=?";
        PreparedStatement sentence=conection.prepareStatement(sql);
        sentence.setInt(1,id);
        int insertrows = sentence.executeUpdate();
        disconnect();
        if (insertrows == 0) return false;
        else return true;
    }

    public static void viewAllRepairs() throws SQLException {
        connect();
        String sql="SELECT * FROM repair";
        CallableStatement sentence=conection.prepareCall(sql);
        ResultSet result = sentence.executeQuery();
        while (result.next()) {
            int id=result.getInt(1);
            int mechanicId = result.getInt(2);
            int carId= result.getInt(3);
            Date date = result.getDate(4);
            String description = result.getString(5);
            double cost=result.getDouble(6);
            System.out.println(id+" "+mechanicId + " " + carId + " " + date + " " + description+" "+cost);
        }

        disconnect();
    }
}