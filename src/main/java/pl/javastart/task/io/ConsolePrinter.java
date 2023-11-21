package pl.javastart.task.io;

public class ConsolePrinter {
    public void printLine(String text) {
        System.out.println(text);
    }

    public void printAddingParticipantInstruction() {
        printLine("Podaj wynik kolejnego gracza (lub stop):");
    }
}
