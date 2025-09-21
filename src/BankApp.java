import java.util.Scanner;

public class BankApp {
    public static void main(String[] args) {

        Konto k1 = new Girokonto("Max Muster", "12345", "AT11 0001", 500.00, 300.00);
        Konto k2 = new Sparkonto("Eva Beispiel", "12345", "AT11 0002", 800.00);

        k1.kontoAnlegen();
        k2.kontoAnlegen();

        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("""
                \n=== BANK-MENÜ ===
                1) Konto 1: Einzahlen
                2) Konto 1: Abheben
                3) Konto 1: Kontoauszug

                4) Konto 2: Einzahlen
                5) Konto 2: Abheben
                6) Konto 2: Kontoauszug

                7) Überweisen (Konto 1 -> Konto 2)
                8) Überweisen (Konto 2 -> Konto 1)

                9) Beide Auszüge anzeigen
                0) Beenden
                Auswahl: 
                """);

            int wahl = leseInt(sc);

            switch (wahl) {
                case 1 -> einzahlen(sc, k1);
                case 2 -> abheben(sc, k1);
                case 3 -> k1.kontoauszug();

                case 4 -> einzahlen(sc, k2);
                case 5 -> abheben(sc, k2);
                case 6 -> k2.kontoauszug();

                case 7 -> ueberweisen(sc, k1, k2);
                case 8 -> ueberweisen(sc, k2, k1);

                case 9 -> { k1.kontoauszug(); k2.kontoauszug(); }
                case 0 -> {
                    running = false;
                    System.out.println("Programm beendet.");
                }
                default -> System.out.println("Ungültige Auswahl.");
            }
        }

        sc.close();
        k1.kontoAufloesen();
        k2.kontoAufloesen();
    }
    
    private static int leseInt(Scanner sc) {
        while (!sc.hasNextInt()) { sc.next(); System.out.print("Zahl eingeben: "); }
        return sc.nextInt();
    }

    private static double leseDouble(Scanner sc) {
        while (!sc.hasNextDouble()) { sc.next(); System.out.print("Betrag (z. B. 100.50): "); }
        return sc.nextDouble();
    }

    private static void einzahlen(Scanner sc, Konto k) {
        System.out.print("Betrag zum Einzahlen: ");
        double betrag = leseDouble(sc);
        k.einzahlen(betrag);
        System.out.printf("Eingezahlt: %.2f €%n", betrag);
    }

    private static void abheben(Scanner sc, Konto k) {
        System.out.print("Betrag zum Abheben: ");
        double betrag = leseDouble(sc);
        boolean ok = k.abheben(betrag);
        if (ok) System.out.printf("Abgehoben: %.2f €%n", betrag);
    }

    private static void ueberweisen(Scanner sc, Konto von, Konto nach) {
        System.out.print("Betrag zum Überweisen: ");
        double betrag = leseDouble(sc);
        von.ueberweisen(nach, betrag);
    }
}
