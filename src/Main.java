import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    public static void administratorMenu() {
        boolean runAdministratorMenu = true;
        while (runAdministratorMenu) {
            System.out.println("** Hospital Administrator Menu **");
            System.out.println("1. Press 1 to add a doctor");
            System.out.println("2. Press 2 to add a doctor availability");
            System.out.println("3. Press 3 to exit");

            Scanner input = new Scanner(System.in);
            try {
                int userObjective = input.nextInt();

                if (userObjective == 1) {
                    Controller.addDoctor();

                } else if (userObjective == 2) {
                    Controller.addDoctorAvailability();

                } else if (userObjective == 3) {
                    System.out.println("back to Main Menu..");
                    System.out.println();
                    runAdministratorMenu = false;
                } else {
                    System.out.println("Invalid input. Please enter again.");
                    System.out.println();
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                System.out.println();
            }
        }
    }

    public static void patientMenu() {
        boolean runPatientMenu = true;
        while (runPatientMenu) {
            System.out.println("** Patient Menu **");
            System.out.println("1. Press 1 to view doctors");
            System.out.println("2. Press 2 to book an appointment");
            System.out.println("3. Press 3 to view a selected doctor's bookings");
            System.out.println("4. Press 4 to register patient");
            System.out.println("5. Press 5 to exit");

            Scanner input = new Scanner(System.in);
            try {
                int userObjective = input.nextInt();

                if (userObjective == 1) {
                    Controller.viewDoctors();
                } else if (userObjective == 2) {
                    Controller.bookAnAppointment();
                } else if (userObjective == 3) {
                    Controller.viewSelectedDoctorBookings();
                } else if (userObjective == 4) {
                    Controller.registerPatient();
                } else if (userObjective == 5) {
                    System.out.println("back to Main Menu..");
                    System.out.println();
                    runPatientMenu = false;
                } else {
                    System.out.println("Invalid input. Please enter again.");
                    System.out.println();
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                System.out.println();
            }
        }
    }

    public static void runProgram() {
        System.out.println("Welcome to the Healthcare app...");
        boolean runMainMenu = true;
        while (runMainMenu) {
            System.out.println("*** Main Menu ***");
            System.out.println("1. If you are a hospital administrator please press 1");
            System.out.println("2. If you are a patient please press 2");
            System.out.println("3. Press 3 to exit");

            Scanner input = new Scanner(System.in);
            try {
                int userType = input.nextInt();

                if (userType == 1) {
                    administratorMenu();
                } else if (userType == 2) {
                    patientMenu();
                } else if (userType == 3) {
                    System.out.println("You choose to exit. Thank you for using our service.");
                    runMainMenu = false;
                } else {
                    System.out.println("Invalid input. Please enter again.");
                    System.out.println();
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        runProgram();
    }
}