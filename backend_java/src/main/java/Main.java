import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    private static Scanner entrada;

    public static void main(String[] args) {
        entrada = new Scanner(System.in);

        try {
            int opcion = 0;
            while (opcion != 5) {
                Menu();
                System.out.println("Option: ");
                opcion = entrada.nextInt();
                entrada.nextLine();
                if (opcion >= 1 && opcion <= 5) {
                    switch (opcion) {
                        case 1:
                            while (opcion != 6) {
                                mechanicMenu();
                                System.out.println("Option: ");
                                opcion = entrada.nextInt();
                                entrada.nextLine();
                                if (opcion >= 1 && opcion <= 6) {
                                    switch (opcion) {
                                        case 1:
                                            addMechanic();
                                            break;
                                        case 2:
                                            modifyMechanic();
                                            break;
                                    }
                                } else System.out.println("Choose a logical option");
                            }
                            break;
                        case 2:
                            while (opcion != 7) {
                                clientMenu();
                                System.out.println("Option: ");
                                opcion = entrada.nextInt();
                                entrada.nextLine();
                                if (opcion >= 1 && opcion <= 7) {
                                    switch (opcion) {
                                        case 1:


                                    }
                                } else System.out.println("Choose a logical option");
                            }
                            break;
                        case 3:
                            while (opcion != 6) {
                                carMenu();
                                System.out.println("Option: ");
                                opcion = entrada.nextInt();
                                entrada.nextLine();
                                if (opcion >= 1 && opcion <= 6) {
                                    switch (opcion) {
                                        case 1:


                                    }
                                } else System.out.println("Choose a logical option");
                            }
                            break;
                        case 4:
                            while (opcion != 6) {
                                repairMenu();
                                System.out.println("Option: ");
                                opcion = entrada.nextInt();
                                entrada.nextLine();
                                if (opcion >= 1 && opcion <= 6) {
                                    switch (opcion) {
                                        case 1:


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
        System.out.println("4-View repair by client name");
        System.out.println("5-View all repairs");
        System.out.println("6-EXIT");
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

    }




