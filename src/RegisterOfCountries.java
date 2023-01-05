import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RegisterOfCountries {
    public static final String DELIMITER = "\t";

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
}
