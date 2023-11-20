package pl.javastart.task;

import pl.javastart.task.io.ConsolePrinter;
import pl.javastart.task.io.DataReader;
import pl.javastart.task.io.file.FileManager;
import pl.javastart.task.model.Participant;

import java.util.*;

public class TournamentStats {
    /*
    Użyłem TreeSetu, ponieważ chciałem zapewnić unikalność dodawanych do zawodów uczestników. Szerzej napisałem o tym
    w pliku klasy Participant przy implementacji equals i hashCode.
     */
    private Set<Participant> participants = new TreeSet<>();
    private final ConsolePrinter printer = new ConsolePrinter();

    void run(Scanner scanner) {
        DataReader reader = new DataReader(scanner, printer);
        reader.readScores(participants);
        Comparator<Participant> comparator = reader.readSortingType();
        comparator = reader.readAscOrDesc(comparator);
        sortParticipants(comparator);
        FileManager.exportToCsv(participants, printer);
    }

    private void sortParticipants(Comparator<Participant> comparator) {
        Set<Participant> sortedSet = new TreeSet<>(comparator);
        sortedSet.addAll(participants);
        participants = sortedSet;
    }
}
