public class Country {
    String name;
    String state;
    Double fullRate;
    Double reducedRate;
    Boolean usesRate;

    public Country(String name, String state, Double fullRate,
                   Double reducedRate, Boolean usesRate) {
        this.name = name;
        this.state = state;
        this.fullRate = fullRate;
        this.reducedRate = reducedRate;
        this.usesRate = usesRate;
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

    public Boolean getUsesRate() {
        return usesRate;
    }

    public void setUsesRate(Boolean usesRate) {
        this.usesRate = usesRate;
    }

    public String toString() {
        return "\n"+getState()+" ("+getName()+"): "+getFullRate()+" %";
    }
}
