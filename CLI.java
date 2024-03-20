import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class CLI {
    public static void main(String[] args) {
        ReportCreate.CreateFolder();
        Scanner scanner = new Scanner(System.in);
        HashMap<Integer, String> dictionary = new HashMap<>();
        dictionary.put(1, "Ambulance");
        dictionary.put(2, "Police");
        dictionary.put(3, "Fireman");

        System.out.println("Hi! What emergency service do you need?");
        System.out.println("You can choose between:");
        System.out.println("1) Ambulance");
        System.out.println("2) Police");
        System.out.println("3) Fireman");

        System.out.println("How many services do you need? (Choose between 1/2/3): ");
        int numServices = scanner.nextInt();

        ArrayList<String> servicesRequested = null;
        if (numServices >= 1 && numServices <= 3) {
            System.out.println("You can choose between:");
            for (int i = 1; i <= 3; i++) {
                System.out.println(i + ") " + dictionary.get(i));
            }
            servicesRequested = new ArrayList<>();
            for (int i = 0; i < numServices; i++) {
                System.out.println("Enter the option for service " + (i + 1) + ": ");
                int serviceRequest = scanner.nextInt();
                servicesRequested.add(dictionary.get(serviceRequest));
            }

            System.out.println("Services requested:");
            for (String service : servicesRequested) {
                System.out.println(service);
            }
        } else {
            System.out.println("Invalid input. Please choose between 1, 2, or 3.");
        }
        // Costruzione della stringa dei servizi richiesti
        StringBuilder servicesString = new StringBuilder();
        for (int i = 0; i < servicesRequested.size(); i++) {
            if (i > 0) {
                servicesString.append(", "); // Aggiungi una virgola dopo ogni elemento tranne il primo
            }
            servicesString.append(servicesRequested.get(i));
        }



        scanner.nextLine();
        System.out.println("Now I need you to tell me your name");
        String name = scanner.nextLine();


        System.out.println("Now I need your last name");
        String lastname = scanner.nextLine();



        System.out.println("I need a little description about your issue");
        String description = scanner.nextLine();

        Callerinformation callerinformation = new Callerinformation(name, lastname, description);
        //String report = "The caller: " + callerinformation.getName() + " " + callerinformation.getLastname() + "\n" + "has requested the following services: " + servicesRequested.toString() + ".\nFor the following issue: " + callerinformation.getDescription();
        String report ="The caller: " + callerinformation.getName() + " " + callerinformation.getLastname() + "\nhas requested the following services: " + servicesString.toString() + ".\nFor the following issue: " + callerinformation.getDescription();
        ReportCreate.generateNextReport(report);
    }
}

