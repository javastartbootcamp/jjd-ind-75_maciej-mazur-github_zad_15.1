package pl.javastart.task.model.comparator;

import pl.javastart.task.model.Participant;

public class FirstNameComparator extends ParticipantComparator {
    @Override
    public int compare(Participant p1, Participant p2) {
        return p1.getFirstName().compareTo(p2.getFirstName());
    }
}
