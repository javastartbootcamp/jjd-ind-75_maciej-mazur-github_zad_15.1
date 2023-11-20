package pl.javastart.task.io.file;

import pl.javastart.task.io.ConsolePrinter;
import pl.javastart.task.model.Participant;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

public class FileManager {
    private static final String FILE_NAME = "stats.csv";

    public static void exportToCsv(Set<Participant> participants, ConsolePrinter printer) {
        try (var fileWriter = new FileWriter(FILE_NAME);
            var writer = new BufferedWriter(fileWriter)
        ) {
            for (Participant participant : participants) {
                writer.write(participant.toCsv());
                writer.newLine();
            }
            printer.printLine("Dane posortowano i zapisano do pliku stats.csv.");
        } catch (IOException e) {
            printer.printLine("Błąd zapisu do pliku " + FILE_NAME);
        }
    }
}
