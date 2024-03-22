import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class ReportCreate {
    public static void CreateFolder(){
        File folder = new File("report");
        if (!folder.exists()) {
            folder.mkdir();
            System.out.println("Folder Created");
        } else {
            System.out.println("The folder already exists");
        }
    }
    public static void generateNextReport(String reportDescription) {
        CreateFolder();
        File folder = new File("report");
        File[] files = folder.listFiles();

        int nextReportNumber = 1;
        if (files != null) {
            nextReportNumber += files.length;
        }

        String reportName = "report" + nextReportNumber + ".txt";
        File newReport = new File(folder, reportName);

        try {
            if (newReport.createNewFile()) {
                System.out.println("Report Created: " + reportName);
                LocalDateTime myDateObj = LocalDateTime.now();
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDate = myDateObj.format(myFormatObj);

                FileWriter myWriter = new FileWriter(newReport);
                myWriter.write("Report create at the following date and time: " + formattedDate + "\n");

                myWriter.write(reportDescription);
                myWriter.close();
                System.out.println("File created successfully");
            } else {
                System.out.println("The report " + reportName + " already exist");
            }
        } catch (IOException e) {
            System.out.println("Error during report creation " + e.getMessage());
        }
    }

    public static boolean ReportReader() {
        String directoryPath = "report";
        Scanner scanner = new Scanner(System.in);
        boolean success = false;

        File directory = new File(directoryPath);

        if (directory.isDirectory() && directory.exists()) {

            File[] files = directory.listFiles();

            System.out.println("Contents of the folder: " + directoryPath);
            for (File file : files) {
                System.out.println(file.getName());
            }

            System.out.println("Choose the name of the file you want to read:");
            String fileName = scanner.nextLine() + ".txt";

            File chosenFile = new File(directory, fileName);

            if (chosenFile.isFile() && chosenFile.exists()) {
                try (BufferedReader bufferedReader = new BufferedReader(new FileReader(chosenFile))) {

                    System.out.println("Content of the file " + fileName + ":");

                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        System.out.println(line);
                    }
                    success = true; // Setting success flag to true
                } catch (IOException e) {

                    e.printStackTrace();
                }
            } else {
                System.out.println("The specified file does not exist in the folder.");
            }
        } else {
            System.out.println("The specified path is not a directory.");
        }
        scanner.close();
        return success;
    }




}

