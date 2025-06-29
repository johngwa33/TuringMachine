package MasinaTuring;


public class Main {
    public static void main(String[] args) {
        Turing mt = new Turing("Turing.txt");
        mt.analizeazaCuvant("");
        System.out.println(mt.esteDeterminist());
    }
}
