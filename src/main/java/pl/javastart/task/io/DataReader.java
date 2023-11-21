package pl.javastart.task.io;

import pl.javastart.task.model.Participant;

import java.util.Comparator;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class DataReader {
    private final Scanner scanner;
    private final ConsolePrinter printer;

    private static final int SORT_BY_FIRST_NAME = 1;
    private static final int SORT_BY_LAST_NAME = 2;
    private static final int SORT_BY_SCORE = 3;
    private static final int SORT_ASC = 1;
    private static final int SORT_DESC = 2;

    public DataReader(Scanner scanner, ConsolePrinter printer) {
        this.scanner = scanner;
        this.printer = printer;
    }

    private void addParticipant(String input, Set<Participant> participants) {
        String[] split = input.split(" ");
        participants.add(new Participant(split[0], split[1], Integer.parseInt(split[2])));
    }

    public Set<Participant> readParticipantsAndScores() {
        boolean quit = false;
        Set<Participant> participants = new TreeSet<>();
        while (!quit) {
            try {

                printer.printAddingParticipantInstruction();
                String input = scanner.nextLine();

                if (!input.equalsIgnoreCase("STOP")) {
                    addParticipant(input, participants);
                } else {
                    quit = true;
                }
            } catch (NumberFormatException e) {
                printer.printLine("Podałeś wynik w nieprawidłowym formacie. Spróbuj ponownie.");
            } catch (RuntimeException e) {
                printer.printLine("Popełniłeś błąd przy wpisywaniu danych uczestnika. Spróbuj ponownie," +
                        "trzymając się formatu wg przykładu \"Jan Kowalski 425\".");
            }
        }
        return participants;
    }

    private Comparator<Participant> readAscOrDesc(Comparator<Participant> comparator) {
        while (true) {
            try {
                printer.printLine("Sortować rosnąco czy malejąco? (1 - rosnąco, 2 - malejąco)");

                switch (Integer.parseInt(scanner.nextLine())) {
                    case SORT_ASC -> {
                        return comparator;
                    }
                    case SORT_DESC -> {
                        return comparator.reversed();
                    }
                    default -> printer.printLine("Wybrałeś nieistniejącą opcję. Spróbuj ponownie.");
                }
            } catch (NumberFormatException e) {
                printer.printLine("Podałeś opcję w nieprawidłowym formacie. Spróbuj ponownie.");
            }
        }
    }

    private Comparator<Participant> readSortingType() {
        while (true) {
            try {
                printer.printLine("Po jakim parametrze posortować? (1 - imię, 2 - nazwisko, 3 - wynik)");

                switch (Integer.parseInt(scanner.nextLine())) {
                    case SORT_BY_FIRST_NAME -> {
                        return Comparator.comparing(Participant::getFirstName);
                    }
                    case SORT_BY_LAST_NAME -> {
                        return Comparator.comparing(Participant::getLastName);
                    }
                    case SORT_BY_SCORE -> {
                        return Comparator.comparing(Participant::getScore);
                    }
                    default -> printer.printLine("Wybrałeś nieistniejącą opcję. Spróbuj ponownie.");
                }
            } catch (NumberFormatException e) {
                printer.printLine("Podałeś opcję w nieprawidłowym formacie. Spróbuj ponownie.");
            }
        }
    }

    public Comparator<Participant> createComparator() {
        Comparator<Participant> comparator = readSortingType();
        comparator = readAscOrDesc(comparator);
        return comparator;
    }
}
