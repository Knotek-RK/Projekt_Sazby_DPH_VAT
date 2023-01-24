import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static final String FILENAME = "vat-eu.csv";

//    public static final String OUTPUT_FILENAME = "vat-over-20.txt";

    public static String separator = "\t";

    public static Double rateAmount;

    public static <rateAmount> void main(String[] args) {
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

        ///region - Seřazení států, které mají základní sazbu z daně z přidané hodnoty vyšší než 20% a nepoužívají speciální sazbu daně. (Řazení je sestupně podle výše základní sazby)
//        System.out.println("---------------------------\n"+
//                "Seřazení států, které mají základní sazbu z daně z přidané hodnoty vyšší než 20% \n" +
//                "a nepoužívají speciální sazbu daně. (Řazení je sestupně podle výše základní sazby):");
//        Collections.sort(countries, Collections.reverseOrder());
//        List<Country> usedStates = new ArrayList<>();
//        for (Country country : countries) {
//            if (country.getFullRate() > 20 && !country.getSpecialRate()) {
//                String line = country+" ("+country.getReducedRate()+" %)";
//                System.out.println(line);
//                // usedStates.add(country);
//            }
//        }
        ///endregion

        System.out.println("---------------------------");
        System.out.print("Zadej výši sazby DPH/VAT, podle které se má filtrovat: ");
        Collections.sort(countries, Collections.reverseOrder());

        Scanner scanner = new Scanner(System.in);
        String readString = scanner.nextLine();
        if (readString.isEmpty()) {
            rateAmount = 20.0;
        } else {
            System.out.print("Zadej hodnotu ještě jednou: ");
            rateAmount = Double.valueOf(scanner.nextLine());
        }

        System.out.println("Zadal si hodnotu: "+rateAmount+" %");

        ///region - Státy, které mají sazbu VAT vyšší než rateAmount % a nepoužívají speciální sazbu
        System.out.println("---------------------------\n"+
                "Seřazení států, které mají základní sazbu z daně z přidané hodnoty vyšší než "+rateAmount+" % \n" +
                "a nepoužívají speciální sazbu daně. (Řazení je sestupně podle výše základní sazby):");
        for (Country country : countries) {
            if (country.getFullRate() > rateAmount && !country.getSpecialRate()) {
                String line = country+" ("+country.getReducedRate()+" %)";
                System.out.println(line);

            }
        }
        ///endregion

        ///region - Státy, které mají sazbu VAT rateAmount % nebo nižší nebo používají speciální sazbu
        System.out.println("===============================");
        System.out.print("Sazba VAT "+rateAmount+" % nebo nižší nebo používají speciální sazbu: ");
        for (Country country : countries) {
            if (country.getFullRate() <= rateAmount || country.getSpecialRate()) {
                String line = country.getName()+", ";
                System.out.print(line);
            }
        }
        ///endregion

        ///region - Zápis do souboru
        try {
            register.writeCountriesToFile("vat-over-"+rateAmount+".txt");

        } catch (CountryException e) {
            System.err.println(e.getLocalizedMessage());
        }
        ///endregion

    }
}