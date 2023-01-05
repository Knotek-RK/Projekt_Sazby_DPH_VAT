public class Country implements Comparable<Country> {
    String name;
    String state;
    Double fullRate;
    Double reducedRate;
    Boolean specialRate;

    public Country(String name, String state, Double fullRate,
                   Double reducedRate, Boolean specialRate) {
        this.name = name;
        this.state = state;
        this.fullRate = fullRate;
        this.reducedRate = reducedRate;
        this.specialRate = specialRate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Double getFullRate() {
        return fullRate;
    }

    public void setFullRate(Double fullRate) {
        this.fullRate = fullRate;
    }

    public Double getReducedRate() {
        return reducedRate;
    }

    public void setReducedRate(Double reducedRate) {
        this.reducedRate = reducedRate;
    }

    public Boolean getSpecialRate() {
        return specialRate;
    }

    public void setSpecialRate(Boolean specialRate) {
        this.specialRate = specialRate;
    }

    public String toString() {
        return getState()+" ("+getName()+"): "+getFullRate()+" %";
    }

    // Seřazení podle základní sazby DPH/VAT sestupně
    @Override
    public int compareTo(Country secondCountry) {
        return this.getFullRate().compareTo(secondCountry.getFullRate());
    }
}
