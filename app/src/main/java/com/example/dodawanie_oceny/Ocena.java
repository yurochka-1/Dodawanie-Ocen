package com.example.dodawanie_oceny;

public class Ocena {

    int ocena;
    boolean czyLiczymyDoSredniej;

    int wagaOceny;
    String przedmiot;

    public Ocena(int ocena, int wagaOceny, String przedmiot, boolean czyLiczymyDoSredniej) {
        this.ocena = ocena;
        this.wagaOceny = wagaOceny;
        this.przedmiot = przedmiot;
        this.czyLiczymyDoSredniej = czyLiczymyDoSredniej;
    }

    @Override
    public String toString() {
        return
                "ocena = " + ocena +
                ", czyLiczymyDoSredniej=" + czyLiczymyDoSredniej +
                ", wagaOceny = " + wagaOceny +
                ", przedmiot = '" + przedmiot + '\'';
    }
}
