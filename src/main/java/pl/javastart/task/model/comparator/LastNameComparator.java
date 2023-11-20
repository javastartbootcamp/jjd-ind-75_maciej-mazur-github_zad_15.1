package pl.javastart.task.model.comparator;

import pl.javastart.task.model.Participant;

public class LastNameComparator extends ParticipantComparator {
    @Override
    public int compare(Participant p1, Participant p2) {
        return p1.getLastName().compareTo(p2.getLastName());
    }
}
