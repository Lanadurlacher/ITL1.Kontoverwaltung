public abstract class Konto {
    private String kontoinhaber;
    private String bankleitzahl;
    private String kontonummer;
    private double kontostand;
    private double ueberziehungsrahmen;
    private String kontoart;

    public Konto(String kontoinhaber, String bankleitzahl, String kontonummer,
                 double startguthaben, double ueberziehungsrahmen, String kontoart) {
        this.kontoinhaber = kontoinhaber;
        this.bankleitzahl = bankleitzahl;
        this.kontonummer = kontonummer;
        this.kontostand = startguthaben;
        this.ueberziehungsrahmen = ueberziehungsrahmen;
        this.kontoart = kontoart;
    }


    public void einzahlen(double betrag) {
        if (betrag <= 0) {
            System.out.println("Betrag muss > 0 sein.");
            return;
        }
        kontostand += betrag;
    }

    public boolean abheben(double betrag) {
        if (betrag <= 0) {
            System.out.println("Betrag muss > 0 sein.");
            return false;
        }
        double neuerStand = kontostand - betrag;
        if (neuerStand < -ueberziehungsrahmen) {
            System.out.println("Abhebung abgelehnt: Überziehungsrahmen überschritten.");
            return false;
        }
        kontostand = neuerStand;
        return true;
    }

    public void kontoauszug() {
        System.out.printf(
                """
                --- Kontoauszug ---
                Kontoart: %s
                Inhaber:  %s
                BLZ/Kto:  %s / %s
                Stand:    %.2f €
                Überzug:  %.2f €
                ---------------
                """,
                kontoart, kontoinhaber, bankleitzahl, kontonummer, kontostand, ueberziehungsrahmen
        );
    }

    public boolean ueberweisen(Konto ziel, double betrag) {
        if (ziel == null) {
            System.out.println("Zielkonto existiert nicht.");
            return false;
        }
        if (this == ziel) {
            System.out.println("Quell- und Zielkonto dürfen nicht identisch sein.");
            return false;
        }
        if (this.abheben(betrag)) {
            ziel.einzahlen(betrag);
            System.out.printf("Überweisung erfolgreich: %.2f € von %s nach %s%n",
                    betrag, this.getKurzinfo(), ziel.getKurzinfo());
            return true;
        }
        return false;
    }

    public String getKurzinfo() {
        return kontoart + " [" + kontonummer + "]";
    }

    public String getKontoinhaber() { return kontoinhaber; }
    public String getBankleitzahl() { return bankleitzahl; }
    public String getKontonummer() { return kontonummer; }
    public double getKontostand() { return kontostand; }
    protected void setKontostand(double kontostand) { this.kontostand = kontostand; }

    public double getUeberziehungsrahmen() { return ueberziehungsrahmen; }
    protected void setUeberziehungsrahmen(double wert) { this.ueberziehungsrahmen = wert; }

    public String getKontoart() { return kontoart; }
    protected void setKontoart(String kontoart) { this.kontoart = kontoart; }


    public void kontoAnlegen() {
        System.out.println("Konto angelegt: " + getKurzinfo());
    }
    public void kontoAufloesen() {
        System.out.println("Konto aufgelöst: " + getKurzinfo());
    }
}
