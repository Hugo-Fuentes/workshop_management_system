import java.util.Scanner;

public class Main {
    private static Scanner entrada;
    public static void main(String[] args) {
        entrada=new Scanner(System.in);

        try {
            int opcion=0;
            while(opcion !=5) {
                Menu();
                opcion = entrada.nextInt();
                entrada.nextLine();
                switch (opcion) {
                    case 1:
                        while (opcion !=6){
                        mechanicMenu();
                        opcion=entrada.nextInt();entrada.nextLine();
                        switch (opcion){
                            case 1:

                        }
                        }break;
                    case 2:
                        while (opcion !=7){
                            clientMenu();
                            opcion=entrada.nextInt();entrada.nextLine();
                            switch (opcion){
                                case 1:


                            }
                        }break;
                    case 3:
                        while (opcion !=6){
                            carMenu();
                            opcion=entrada.nextInt();entrada.nextLine();
                            switch (opcion){
                                case 1:


                            }
                        }break;
                    case 4:
                        while (opcion !=6){
                            repairMenu();
                            opcion=entrada.nextInt();entrada.nextLine();
                            switch (opcion){
                                case 1:


                            }
                        }break;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void Menu() {
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
        System.out.println("6-EXIT");}

    private static void clientMenu(){
        System.out.println("\t\t\t CLIENT MENU \n");
        System.out.println("1-Add Client");
        System.out.println("2-Modify Client");
        System.out.println("3-Delete Client");
        System.out.println("4-View Clients by name");
        System.out.println("5-View client cars by client name ");
        System.out.println("6-View client repairs by client name");
        System.out.println("7-EXIT");}

    private static void carMenu(){
        System.out.println("\t\t\t CAR MENU \n");
        System.out.println("1-Add Car");
        System.out.println("2-Modify Car");
        System.out.println("3-Delete Car");
        System.out.println("4-View Car by brand");
        System.out.println("5-View car repairs by car id");
        System.out.println("6-EXIT");}

    private static void repairMenu(){
        System.out.println("\t\t\t REPAIR MENU \n");
        System.out.println("1-Add repair");
        System.out.println("2-Modify repair");
        System.out.println("3-Delete repair");
        System.out.println("4-View repair by client name");
        System.out.println("5-View all repairs");
        System.out.println("6-EXIT");}




}
