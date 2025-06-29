package MasinaTuring;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Turing {
    private String stare_initiala;
    private ListaTranzitii lista;
    private String[] stari_finale;

    public Turing(String nume_fisier) {
        this.lista = new ListaTranzitii();
        String sti = "";
        String[] stf = new String[0];

        try {
            File fisier = new File(nume_fisier);
            Scanner scanner = new Scanner(fisier);
            int linie_numar = 0;

            while (scanner.hasNextLine()) {
                String linie = scanner.nextLine().trim();
                linie_numar++;

                if (linie.isEmpty()) continue;

                if (linie_numar == 1) {
                    sti = linie;
                } else if (linie_numar == 2) {
                    stf = linie.split(" ");
                } else {
                    String[] parts = linie.split(" ");
                    if (parts.length != 5) {
                        System.out.println("Linie invalidă în fișier: " + linie);
                        continue;
                    }
                    Tranzitie tr = new Tranzitie(
                        parts[0], parts[1].charAt(0),
                        parts[2].charAt(0), parts[3].charAt(0),
                        parts[4]
                    );
                    lista.addTranzitie(tr);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Eroare: fișierul nu a fost găsit!");
            e.printStackTrace();
        }

        this.stare_initiala = sti;
        this.stari_finale = stf;
    }

    public void analizeazaCuvant(String sir) {
        StringBuilder banda = new StringBuilder(sir + "B");
        int pozitie = 0;
        String stare = this.stare_initiala;
        int pas = 0;
        
        try {
            while (true) {
                if (pozitie < 0) {
                    throw new RuntimeException("Capul a sărit de pe bandă la stânga!");
                }
                if (pozitie >= banda.length()) {
                    banda.append("B");
                }

                System.out.println(banda);
                Thread.sleep(100);
                char simbol = banda.charAt(pozitie);
                Tranzitie tranzitie = lista.gasesteTranzitie(stare, simbol);

                if (tranzitie == null) {
                    if (Arrays.asList(stari_finale).contains(stare)) {
                        System.out.println("Cuvânt ACCEPTAT. Am ajuns într-o stare finală: " + stare);
                    } else {
                        System.out.println("Tranziție inexistentă. Cuvânt RESPINS.");
                    }
                    break;
                }

                banda.setCharAt(pozitie, tranzitie.getSimbol2());
                stare = tranzitie.getStf();

                switch (tranzitie.getDirectie()) {
                    case 'R': pozitie++; break;
                    case 'L': pozitie--; break;
                    case 'N': break;
                }

                pas++;
                if (pas > 100) {
                    System.out.println("STOP: buclă detectată. Prea mulți pași.");
                    break;
                }
            }

           

        } catch (RuntimeException e) {
            System.out.println("Eroare: " + e.getMessage());
        } catch (InterruptedException e) {
            System.out.println("Execuția a fost întreruptă.");
        }
    }

    public boolean esteDeterminist() {
        java.util.HashMap<String, Tranzitie> perechi = new java.util.HashMap<>();
        
        for (Tranzitie tr : lista.getLista()) {
            String cheie = tr.getSti() + "_" + tr.getSimbol1();
            
            if (perechi.containsKey(cheie)) {
                Tranzitie existenta = perechi.get(cheie);
                
                if (existenta.getSimbol2() != tr.getSimbol2() ||
                    existenta.getDirectie() != tr.getDirectie() ||
                    !existenta.getStf().equals(tr.getStf())) { 
                    
                    return false;
                }
            } else {
                perechi.put(cheie, tr);
            }
        }
        
        return true;
    }
    
}
