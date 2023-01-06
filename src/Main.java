import com.sun.jdi.BooleanType;
import com.sun.jdi.Value;

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

        ///region - Seřazení států, které mají základní sazbu z daně z přidané hodnoty vyšší než 20% a nepoužívají speciální sazbu daně. (Řazení je sestupně podle výše základní sazby)
        System.out.println("---------------------------\n"+
                "Seřazení států, které mají základní sazbu z daně z přidané hodnoty vyšší než 20% \n" +
                "a nepoužívají speciální sazbu daně. (Řazení je sestupně podle výše základní sazby):");
        Collections.sort(countries, Collections.reverseOrder());
        List<Country> usedStates = new ArrayList<>();
        for (Country country : countries) {
            if (country.getFullRate() > 20 && !country.getSpecialRate()) {
                String line = country+" ("+country.getReducedRate()+")";
                System.out.println(line);
                usedStates.add(country);
            }
        }
        ///endregion

        ///region - Státy, které mají sazbu VAT 20 % nebo nižší nebo používají speciální sazbu
        System.out.println("==============================");
        System.out.print("Sazba VAT 20 % nebo nižší nebo používají speciální sazbu: ");
        List<Country> unusedStates = new ArrayList<>();
        for (Country country : countries) {
            if (country.getFullRate() <= 20 || country.getSpecialRate()) {
                String line = country.getName()+", ";
                System.out.print(line);
                unusedStates.add(country);
            }
        }
        ///endregion
    }
}