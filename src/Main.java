import java.util.List;

public class Main {
    public static final String FILENAME = "vat-eu.csv";


    public static void main(String[] args) {
        // načtení a přečtení souboru:
        RegisterOfCountries register = new RegisterOfCountries();
        try {
            register.readCountriesFromFile(FILENAME);
        } catch (CountryException e) {
            System.err.println("Chyba při načtení souboru: "+e.getLocalizedMessage());
        }

        List<Country> countries = register.getCountries();
    }
}