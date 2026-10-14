import com.mysql.cj.protocol.a.SqlDateValueEncoder;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    private static Scanner entrada;

    public static void main(String[] args) {
        entrada = new Scanner(System.in);

        try {
            int option = 0;
            while (option != 5) {
                Menu();
                System.out.println("Option: ");
                option = entrada.nextInt();
                entrada.nextLine();
                if (option >= 1 && option <= 5) {
                    switch (option) {
                        case 1:
                            int optionMechanic=0;
                            while (optionMechanic != 6) {
                                mechanicMenu();
                                System.out.println("Option: ");
                                optionMechanic= entrada.nextInt();
                                entrada.nextLine();
                                if (optionMechanic >= 1 && optionMechanic <= 6) {
                                    switch (optionMechanic) {
                                        case 1:
                                            addMechanic();
                                            break;
                                        case 2:
                                            modifyMechanic();
                                            break;
                                        case 3:
                                            deleteMechanic();
                                            break;
                                        case 4:
                                            BBDD.viewMechanicByName();
                                            break;
                                        case 5:
                                            BBDD.viewRepairsByMechanicName();
                                            break;
                                    }
                                } else System.out.println("Choose a logical option");
                            }
                            break;
                        case 2:
                            int optionClient=0;
                            while (optionClient != 7) {
                                clientMenu();
                                System.out.println("Option: ");
                                optionClient = entrada.nextInt();
                                entrada.nextLine();
                                if (optionClient >= 1 && optionClient <= 7) {
                                    switch (optionClient) {
                                        case 1:
                                            addClient();
                                            break;
                                        case 2:
                                            modifyClient();
                                            break;
                                        case 3:
                                            deleteClient();
                                            break;
                                        case 4:
                                            BBDD.viewClientByName();
                                            break;
                                        case 5:
                                            BBDD.viewCarByClientName();
                                            break;
                                        case 6:
                                            BBDD.viewRepairsByClientName();
                                            break;


                                    }
                                } else System.out.println("Choose a logical option");
                            }
                            break;
                        case 3:
                            int optionCar=0;
                            while (optionCar != 6) {
                                carMenu();
                                System.out.println("Option: ");
                                optionCar = entrada.nextInt();
                                entrada.nextLine();
                                if (optionCar >= 1 && optionCar <= 6) {
                                    switch (optionCar) {
                                        case 1:
                                            addCar();
                                            break;
                                        case 2:
                                            modifyCar();
                                            break;
                                        case 3:
                                            deleteCar();
                                            break;
                                        case 4:
                                            BBDD.viewCarByBrand();
                                            break;
                                        case 5:
                                            BBDD.viewRepairsByCarId();
                                            break;


                                    }
                                } else System.out.println("Choose a logical option");
                            }
                            break;
                        case 4:
                            int optionRepair=0;
                            while (optionRepair != 5) {
                                repairMenu();
                                System.out.println("Option: ");
                                optionRepair = entrada.nextInt();
                                entrada.nextLine();
                                if (optionRepair >= 1 && optionRepair <= 6) {
                                    switch (optionRepair) {
                                        case 1:
                                            addRepair();
                                            break;
                                        case 2:
                                            modifyRepair();
                                            break;
                                        case 3:
                                            deleteRepair();
                                            break;
                                        case 4:
                                            BBDD.viewAllRepairs();
                                            break;
                                    }
                                } else System.out.println("Choose a logical option");
                            }
                        break;
                    }
                } else System.out.println("Choose a logical option.");

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void Menu() {
        System.out.println("\t\t\t MAIN MENU \n");
        System.out.println("1-Mechanics Management");
        System.out.println("2-Clients Management");
        System.out.println("3-Cars Management");
        System.out.println("4-Repairs Management");
        System.out.println("5-EXIT");
    }

    private static void mechanicMenu() {
        System.out.println("\t\t\t MECHANIC MENU \n");
        System.out.println("1-Add Mechanic");
        System.out.println("2-Modify Mechanic");
        System.out.println("3-Delete Mechanic");
        System.out.println("4-View Mechanic by name");
        System.out.println("5-View mechanic repairs by mechanic name");
        System.out.println("6-EXIT");
    }

    private static void clientMenu() {
        System.out.println("\t\t\t CLIENT MENU \n");
        System.out.println("1-Add Client");
        System.out.println("2-Modify Client");
        System.out.println("3-Delete Client");
        System.out.println("4-View Clients by name");
        System.out.println("5-View client cars by client name ");
        System.out.println("6-View client repairs by client name");
        System.out.println("7-EXIT");
    }

    private static void carMenu() {
        System.out.println("\t\t\t CAR MENU \n");
        System.out.println("1-Add Car");
        System.out.println("2-Modify Car");
        System.out.println("3-Delete Car");
        System.out.println("4-View Car by brand");
        System.out.println("5-View car repairs by car id");
        System.out.println("6-EXIT");
    }

    private static void repairMenu() {
        System.out.println("\t\t\t REPAIR MENU \n");
        System.out.println("1-Add repair");
        System.out.println("2-Modify repair");
        System.out.println("3-Delete repair");
        System.out.println("4-View all repairs");
        System.out.println("5-EXIT");
    }

    private static void addMechanic() throws SQLException {
        boolean phone = false;
        System.out.println("Give me mechanic's name: ");
        String name_ = entrada.nextLine();
        System.out.println("Give me mechanic's last name: ");
        String last_name = entrada.nextLine();
        while (phone == false) {
            System.out.println("Give me mechanic's phone number: ");
            String phone_number = entrada.nextLine();
            if (phone_number.length() != 9) {
                System.out.println("The phone number must have exactly 9 digits.");
                phone = false;
            } else {
                phone = true;
                if (BBDD.addMechanic(name_, last_name, phone_number)) {
                    System.out.println("Mechanic added");
                    phone = true;
                } else {
                    System.out.println("The mechanic could not be added.");
                }
            }

        }

    }

    private static void modifyMechanic() throws SQLException {
        System.out.println("Give me mechanic's id: ");
        int id = entrada.nextInt();entrada.nextLine();
        System.out.println("Give me new name: ");
        String name=entrada.nextLine();
        System.out.println("Give me new last name: ");
        String last_name= entrada.nextLine();
        System.out.println("Give me new phone number: ");
        String phone_number=entrada.nextLine();
        if (BBDD.modifyMechanic(id,name, last_name, phone_number)) {
            System.out.println("Mechanic updated");
        } else {
            System.out.println("The mechanic could not be updated.");
        }
    }

    private static void deleteMechanic() throws SQLException {
        System.out.println("Give me the id of the mechanic you want to delete: ");
        int id=entrada.nextInt();
        if (BBDD.deleteMechanic(id)) {
            System.out.println("Mechanic deleted");
        } else {
            System.out.println("The mechanic could not be deleted.");
        }
    }

    private static void addClient() throws SQLException {
        boolean phone = false;
        System.out.println("Give me client's name: ");
        String name_ = entrada.nextLine();
        System.out.println("Give me client's last name: ");
        String last_name = entrada.nextLine();
        while (phone == false) {
            System.out.println("Give me clients's phone number: ");
            String phone_number = entrada.nextLine();
            if (phone_number.length() != 9) {
                System.out.println("The phone number must have exactly 9 digits.");
                phone = false;
            } else {
                phone = true;
                if (BBDD.addClient(name_, last_name, phone_number)) {
                    System.out.println("Client added");
                    phone = true;
                } else {
                    System.out.println("The client could not be added.");
                }
            }

        }

    }

    private static void modifyClient() throws SQLException {
        System.out.println("Give me client's id: ");
        int id = entrada.nextInt();entrada.nextLine();
        System.out.println("Give me new name: ");
        String name=entrada.nextLine();
        System.out.println("Give me new last name: ");
        String last_name= entrada.nextLine();
        System.out.println("Give me new phone number: ");
        String phone_number=entrada.nextLine();
        if (BBDD.modifyClient(id,name, last_name, phone_number)) {
            System.out.println("Client updated");
        } else {
            System.out.println("The client could not be updated.");
        }
    }
    private static void deleteClient() throws SQLException {
        System.out.println("Give me the id of the client you want to delete: ");
        int id=entrada.nextInt();
        if (BBDD.deleteClient(id)) {
            System.out.println("Client deleted");
        } else {
            System.out.println("The client could not be deleted.");
        }
    }

    private static void addCar() throws SQLException {
        System.out.println("Give me the car's brand: ");
        String brand=entrada.nextLine();
        System.out.println("Give the model: ");
        String model=entrada.nextLine();
        System.out.println("Give me client's id: ");
        int id=entrada.nextInt();
        if (BBDD.addCar(brand,model, id)) {
            System.out.println("Car added");
        } else {
            System.out.println("The car could not be added.");
        }
    }

    private static void modifyCar() throws SQLException {
        System.out.println("Give me car id: ");
        int id=entrada.nextInt();entrada.nextLine();
        System.out.println("Give new brand: ");
        String brand=entrada.nextLine();
        System.out.println("Give me new model: ");
        String model=entrada.nextLine();
        if (BBDD.modifyCar(brand,model,id)) {
            System.out.println("Car updated");
        } else {
            System.out.println("The car could not be updated.");
        }
    }

    private static void deleteCar() throws SQLException {
        System.out.println("Give me the id of the car you want to delete: ");
        int id=entrada.nextInt();
        if (BBDD.deleteCar(id)) {
            System.out.println("car deleted");
        } else {
            System.out.println("The car could not be deleted.");
        }
    }

    private static void addRepair() throws SQLException {
        System.out.println("Give me mechanic's id: ");
        int mechanicId=entrada.nextInt();entrada.nextLine();
        System.out.println("Give me car's id: ");
        int carId=entrada.nextInt();entrada.nextLine();
        System.out.println("Give me the date(dd/MM/yyyy): ");
        String date=entrada.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date_=LocalDate.parse(date,formatter);
        System.out.println("Give me a description: ");
        String description=entrada.nextLine();
        if(description.equalsIgnoreCase("")){
            description=null;
        }
        System.out.println("Give me the cost :");
        double cost=entrada.nextDouble();
        if (BBDD.addRepair(mechanicId,carId, Date.valueOf(date_),description,cost)) {
            System.out.println("repair added");
        } else {
            System.out.println("The repair could not be added.");
        }

    }

    private static void modifyRepair() throws SQLException {
        System.out.println("Give me repair id: ");
        int id=entrada.nextInt();entrada.nextLine();
        System.out.println("Give me new date: ");
        String date=entrada.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date_=LocalDate.parse(date,formatter);
        System.out.println("Give me new cost: ");
        double cost=entrada.nextDouble();
        if (BBDD.modifyRepair(id,date_,cost)) {
            System.out.println("repair updated");
        } else {
            System.out.println("The repair could not be updated.");
        }
    }

    private static void deleteRepair() throws SQLException {
        System.out.println("Give me the id of the repair you want to delete: ");
        int id=entrada.nextInt();
        if (BBDD.deleteRepair(id)) {
            System.out.println("repair deleted");
        } else {
            System.out.println("The repair could not be deleted.");
        }
    }

    }




