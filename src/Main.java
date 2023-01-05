import java.util.ArrayList;
import java.util.Collections;
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

        System.out.println("Seznam států s hodnotou základní sazby daně:");
        countries.forEach(System.out::println);
//        System.out.println(countries);

        ///region - Seřazení podle základní sazby DPH/VAT sestupně
        System.out.println("---------------------------\n"+
                "Seřazení států, které mají základní sazbu z daně z přidané hodnoty vyšší než 20% \n" +
                "a nepoužívají speciální sazbu daně. (Řazení je sestupně podle výše základní sazby):");
        Collections.sort(countries, Collections.reverseOrder());
        List<Country> unusedstates = new ArrayList<>();
        for (Country country : countries) {
            if (country.getFullRate() > 20 && !country.getSpecialRate()) {
                String line = country+" ("+country.getReducedRate()+")";
                System.out.println(line);
                unusedstates.add(country);
            }
        }
        ///endregion

    }
}