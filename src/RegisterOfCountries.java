import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class RegisterOfCountries {
//    public static final String DELIMITER = "\t";

    private List<Country> countries = new ArrayList<>();

    public void addCountry(Country newCountry) {
        countries.add(newCountry);
    }

    public List<Country> getCountries() {
        return new ArrayList<>(countries);
    }

    public void readCountriesFromFile(String filename) throws CountryException {
        String nextLine = null;
        String[] countries = new String[0];
        Country newCountry;
        String name = null;
        String state = null;
        Double fullRate = null;
        Double reducedRate = null;
        Boolean specialRate = null;

        int lineNumber = 0;
        String separator = "\t";

        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(filename)))) {
            while (scanner.hasNextLine()) {
                lineNumber++;
                nextLine = scanner.nextLine();
                countries = nextLine.split(separator);
                name = countries[0];
                state = countries[1];
                fullRate = Double.parseDouble(countries[2]);
                reducedRate = Double.parseDouble(countries[3]);
                specialRate = Boolean.getBoolean(countries[4]);

                newCountry = new Country(name, state, fullRate, reducedRate, specialRate);
                addCountry(newCountry);
            }
        } catch (FileNotFoundException e) {
            throw new CountryException(
                    "Nepodařilo se najít soubor "+filename+":"+e.getLocalizedMessage());
        }
    }

    public void writeCountriesToFile(String filename) throws  CountryException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {

//            for (Country country : countries) {
////                writer.println(country); // vypíše všechny státy
//            }

            writer.println("Seřazení států, které mají základní sazbu z daně z přidané hodnoty vyšší než 20% \n" +
                    "a nepoužívají speciální sazbu daně. (Řazení je sestupně podle výše základní sazby):");
            Collections.sort(countries, Collections.reverseOrder());

            for (Country country : countries) {
                if (country.getFullRate() > 20 && !country.getSpecialRate()) {
                    String line = country+" ("+country.getReducedRate()+" %)";
                    writer.println(line);
                }
            }

            writer.println("==============================");
            writer.print("Sazba VAT 20 % nebo nižší nebo používají speciální sazbu: ");

            for (Country country : countries) {
                if (country.getFullRate() <= 20 || country.getSpecialRate()) {
                    String line = country.getName()+", ";
                    writer.print(line);
                }
            }

        } catch (IOException e) {
            throw new CountryException(
                    "Nastala chyba při zápisu do souboru: "+e.getLocalizedMessage());
        }
    }
}
