package MasinaTuring;

import java.util.ArrayList;
import java.util.List;

public class ListaTranzitii {
    private List<Tranzitie> listaTranzitii = new ArrayList<>();

    public void addTranzitie(Tranzitie t) {
        listaTranzitii.add(t);
    }

    public Tranzitie gasesteTranzitie(String stare, char simbol) {
        for (Tranzitie t : listaTranzitii) {
            if (t.getSti().equals(stare) && t.getSimbol1() == simbol) {
                return t;
            }
        }
        return null;
    }
    public List<Tranzitie> getLista() {
        return listaTranzitii;
    }

}
