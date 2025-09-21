public class Kreditkonto extends Konto {
    public Kreditkonto(String inhaber, String blz, String ktoNr, double start,
                       double kreditrahmen) {
        super(inhaber, blz, ktoNr, start, kreditrahmen, "Kredit");
    }
}
