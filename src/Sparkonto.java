public class Sparkonto extends Konto {
    public Sparkonto(String inhaber, String blz, String ktoNr, double start) {

        super(inhaber, blz, ktoNr, start, 0.0, "Spar");
    }
}
