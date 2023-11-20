package pl.javastart.task.io;

import pl.javastart.task.model.Participant;
import pl.javastart.task.model.comparator.FirstNameComparator;
import pl.javastart.task.model.comparator.LastNameComparator;
import pl.javastart.task.model.comparator.ParticipantComparator;
import pl.javastart.task.model.comparator.ScoreComparator;

import java.util.Comparator;
import java.util.Scanner;
import java.util.Set;

public class DataReader {
    private final Scanner scanner;
    private final ConsolePrinter printer;

    private static final int SORT_BY_FIRST_NAME = 1;
    private static final int SORT_BY_LAST_NAME = 2;
    private static final int SORT_BY_SCORE = 3;

    public DataReader(Scanner scanner, ConsolePrinter printer) {
        this.scanner = scanner;
        this.printer = printer;
    }

    public void readScores(Set<Participant> participants) {
        while (true) {
            try {
                printer.printAddingParticipantInstruction();
                String input = scanner.nextLine();

            /*
            Wybrałem taki a nie bardziej elegancki sposób wyjścia z pętli, by uniknąć podwójnego sprawdzania warunku
            tę pętlę kończącego. W przeciwnym wypadku warunek ten sprawdzany byłby co najmniej dwa razy, czyli przed podjęciem
            próby stworzenia obiektu klasy Participant oraz w samym warunku pętli.
             */
                if (input.equalsIgnoreCase("STOP")) {
                    break;
                }

                addParticipant(input, participants);
            } catch (NumberFormatException e) {
                printer.printLine("Podałeś wynik w nieprawidłowym formacie. Spróbuj ponownie.");
            } catch (RuntimeException e) {
                printer.printLine("Popełniłeś błąd przy wpisywaniu danych uczestnika. Spróbuj ponownie," +
                        "trzymając się formatu wg przykładu \"Jan Kowalski 425\".");
            }
        }
    }

    public Comparator<Participant> readAscOrDesc(Comparator<Participant> comparator) {
        while (true) {
            try {
                printer.printLine("Sortować rosnąco czy malejąco? (1 - rosnąco, 2 - malejąco)");

                switch (Integer.parseInt(scanner.nextLine())) {
                    case ParticipantComparator.ASC -> {
                        return comparator;
                    }
                    case ParticipantComparator.DESC -> {
                        return comparator.reversed();
                    }
                    default -> printer.printLine("Wybrałeś nieistniejącą opcję. Spróbuj ponownie.");
                }
            } catch (NumberFormatException e) {
                printer.printLine("Podałeś opcję w nieprawidłowym formacie. Spróbuj ponownie.");
            }
        }
    }

    private void addParticipant(String input, Set<Participant> participants) {
        String[] split = input.split(" ");
        participants.add(new Participant(split[0], split[1], Integer.parseInt(split[2])));
    }

    public ParticipantComparator readSortingType() {
        while (true) {
            try {
                printer.printLine("Po jakim parametrze posortować? (1 - imię, 2 - nazwisko, 3 - wynik)");

                switch (Integer.parseInt(scanner.nextLine())) {
                    case SORT_BY_FIRST_NAME -> {
                        return new FirstNameComparator();
                    }
                    case SORT_BY_LAST_NAME -> {
                        return new LastNameComparator();
                    }
                    case SORT_BY_SCORE -> {
                        return new ScoreComparator();
                    }
                    default -> printer.printLine("Wybrałeś nieistniejącą opcję. Spróbuj ponownie.");
                }
            } catch (NumberFormatException e) {
                printer.printLine("Podałeś opcję w nieprawidłowym formacie. Spróbuj ponownie.");
            }
        }
    }
}
