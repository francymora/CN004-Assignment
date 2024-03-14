import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
                System.out.println("Creato il report: " + reportName);
                LocalDateTime myDateObj = LocalDateTime.now();
                DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                String formattedDate = myDateObj.format(myFormatObj);

                FileWriter myWriter = new FileWriter(newReport);
                myWriter.write(formattedDate + "\n");

                myWriter.write(reportDescription);
                myWriter.close();
                System.out.println("Scrittura nel file completata con successo.");
            } else {
                System.out.println("Il report " + reportName + " esiste già.");
            }
        } catch (IOException e) {
            System.out.println("Errore durante la creazione del report: " + e.getMessage());
        }
    }


    }

