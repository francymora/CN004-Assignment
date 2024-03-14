import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FolderTest {

    public static void main(String[] args) {
        // Chiamata alla funzione per creare la cartella "report" se non esiste
        createReportFolder();

        // Chiamata alla funzione per generare il prossimo report
        generateNextReport();
    }

    // Funzione per creare la cartella "report" se non esiste
    private static void createReportFolder() {
        File folder = new File("report");
        if (!folder.exists()) {
            folder.mkdir();
            System.out.println("Cartella 'report' creata.");
        } else {
            System.out.println("La cartella 'report' esiste già.");
        }
    }

    // Funzione per generare il prossimo report
    private static void generateNextReport() {
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

                FileWriter myWriter = new FileWriter(newReport); // Modifica qui
                myWriter.write(formattedDate);
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