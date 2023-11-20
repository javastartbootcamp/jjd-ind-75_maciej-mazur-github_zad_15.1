package pl.javastart.task.model.comparator;

import pl.javastart.task.model.Participant;

public class ScoreComparator extends ParticipantComparator {
    @Override
    public int compare(Participant p1, Participant p2) {
        return Integer.compare(p1.getScore(), p2.getScore());
    }
}
