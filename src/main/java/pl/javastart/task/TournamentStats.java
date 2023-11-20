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
    void run(Scanner scanner) {
        ConsolePrinter printer = new ConsolePrinter();
        DataReader reader = new DataReader(scanner, printer);
        Set<Participant> participants = reader.readParticipantsAndScores();
        Comparator<Participant> comparator = reader.createComparator();
        participants = sortCollection(participants, comparator);
        FileManager fileManager = new FileManager(printer);
        fileManager.exportToCsv(participants);
    }

    private Set<Participant> sortCollection(Set<Participant> participants, Comparator<Participant> comparator) {
        if (comparator.equals(Comparator.comparing(Participant::getScore))) {
            /*
            W takim przypadku TreeSet już od początku był tak domyślnie posortowany, dlatego niepotrzebne byłoby
            przechodzenie procesu sortowania w taki sam sposób ponownie
             */
            return participants;
        }

        Set<Participant> sortedSet = new TreeSet<>(comparator);
        sortedSet.addAll(participants);
        return sortedSet;
    }
}
