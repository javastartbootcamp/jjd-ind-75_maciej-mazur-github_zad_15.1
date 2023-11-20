package pl.javastart.task.model.comparator;

import pl.javastart.task.model.Participant;

import java.util.Comparator;

public abstract class ParticipantComparator implements Comparator<Participant> {
    /*
    Poniższych stałych użyłem przy wyborze między sortowaniem rosnącym i malejącym
     */
    public static final int ASC = 1;
    public static final int DESC = 2;
}
