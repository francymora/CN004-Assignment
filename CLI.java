import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
public class CLI {
    public static void main(String[] args) {
        ReportCreate.CreateFolder();
        String descritpion = "A caller ask for the police because he need help";
        ReportCreate.generateNextReport(descritpion);


    }
}
