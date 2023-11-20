package pl.javastart.task.model;

import pl.javastart.task.model.comparator.ScoreComparator;

import java.util.Objects;

public class Participant implements Comparable<Participant> {
    private final String firstName;
    private final String lastName;
    private final int score;

    public Participant(String firstName, String lastName, int score) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.score = score;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getScore() {
        return score;
    }

    public String toCsv() {
        return String.format("%s %s;%d", firstName, lastName, score);
    }

    @Override
    public int compareTo(Participant p) {
        return new ScoreComparator().compare(this, p);
    }

    /*
    Wykluczyłem z equals i z hashCode wliczanie score, ponieważ uznałem, że o unikalności danego uczestnika powinna decydować
    jedynie unikalność jego pełnego nazwiska (imię + nazwisko). Dzięki temu przy dodawaniu kolejnych uczestników do
    TreeSetu uniknąłem sytuacji, w której zawodnik o takim samym nazwisku niejako mógłby przystąpić do zawodów więcej
    niż jeden raz tylko dlatego, że za drugim razem uzyskał inny wynik. Obrałem założenie, że każdy uczestnik o danym
    pełnym nazwisku przystąpić może do zawodów wyłącznie raz.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Participant that = (Participant) o;
        return Objects.equals(getFirstName(), that.getFirstName()) && Objects.equals(getLastName(), that.getLastName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName());
    }
}
