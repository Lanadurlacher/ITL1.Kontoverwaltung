public class Girokonto extends Konto {
    public Girokonto(String inhaber, String blz, String ktoNr, double start,
                     double ueberziehungsrahmen) {
        super(inhaber, blz, ktoNr, start, ueberziehungsrahmen, "Giro");
    }
}
