import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class CLI {

    static void ListOfService(){
        System.out.println("Hi! What emergency service do you need?");
        System.out.println("You can choose between:");
        System.out.println("1) Ambulance");
        System.out.println("2) Police");
        System.out.println("3) Fireman");

    }
    public static HashMap<Integer, String> getDictionary() {
        HashMap<Integer, String> dictionary = new HashMap<>();
        dictionary.put(1, "Ambulance");
        dictionary.put(2, "Police");
        dictionary.put(3, "Fireman");
        return dictionary;
    }
    public static ArrayList<String> processServiceRequests(Scanner scanner) {
        HashMap<Integer, String> dictionary = getDictionary();
        ArrayList<String> servicesRequested = null;
        System.out.println("Enter the number of services you want (between 1 and 3): ");
        int numServices = scanner.nextInt();
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
        } else {
            System.out.println("Invalid input. Please choose between 1, 2, or 3.");
        }
        return servicesRequested;
    }
    public static String buildServicesString(ArrayList<String> servicesRequested) {
        StringBuilder servicesString = new StringBuilder();
        for (int i = 0; i < servicesRequested.size(); i++) {
            if (i > 0) {
                servicesString.append(", ");
            }
            servicesString.append(servicesRequested.get(i));
        }
        return servicesString.toString();
    }



    public static void main(String[] args) {



        boolean Requests = true;
        while (Requests) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Do you want to read a report or help someone with a request?(write read for the first option and help for the second)");
            String options = scanner.nextLine();
            if (options.toLowerCase().equals("help")) {
                ListOfService();
                ArrayList<String> servicesRequested = processServiceRequests(scanner);
                String servicesString = buildServicesString(servicesRequested);

                scanner.nextLine();
                System.out.println("Now I need you to tell me your name");
                String name = scanner.nextLine();


                System.out.println("Now I need your last name");
                String lastname = scanner.nextLine();


                System.out.println("I need a little description about your issue");
                String description = scanner.nextLine();

                Callerinformation callerinformation = new Callerinformation(name, lastname, description);
                //String report = "The caller: " + callerinformation.getName() + " " + callerinformation.getLastname() + "\n" + "has requested the following services: " + servicesRequested.toString() + ".\nFor the following issue: " + callerinformation.getDescription();
                String report = "The caller: " + callerinformation.getName() + " " + callerinformation.getLastname() + "\nhas requested the following services: " + servicesString + ".\nFor the following issue: " + callerinformation.getDescription();
                ReportCreate.generateNextReport(report);
                System.out.println("Do you want to create another report?(yes or no only)");
                String response = scanner.nextLine();
                Requests = !response.toLowerCase().equals("no");
            } else {
                boolean ReportResults = ReportCreate.ReportReader();
                if (!ReportResults){
                    System.out.println("There is no report try again when you create a report!");
                    break;

                }else{
                    System.out.println("Do you want to create another report?(yes or no only)");
                    scanner.nextLine();
                    String response = scanner.nextLine();
                    Requests = !response.toLowerCase().equals("no");
                }

            }
        }




        System.out.println("Thanks to use our service! Have a nice day");
    }
}



