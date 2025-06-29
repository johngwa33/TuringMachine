package MasinaTuring;

public class Tranzitie {
    private String sti;       // stare inițială
    private char simbol1;     // simbol citit
    private char simbol2;     // simbol scris
    private char directie;    // direcția de deplasare (L, R, N)
    private String stf;       // stare finală

    public Tranzitie(String sti, char simbol1, char simbol2, char directie, String stf) {
        this.sti = sti;
        this.simbol1 = simbol1;
        this.simbol2 = simbol2;
        this.directie = directie;
        this.stf = stf;
    }

    public String getSti() {
        return sti;
    }

    public char getSimbol1() {
        return simbol1;
    }

    public char getSimbol2() {
        return simbol2;
    }

    public char getDirectie() {
        return directie;
    }

    public String getStf() {
        return stf;
    }

    @Override
    public String toString() {
        return sti + " " + simbol1 + " " + simbol2 + " " + directie + " " + stf;
    }
    

}
