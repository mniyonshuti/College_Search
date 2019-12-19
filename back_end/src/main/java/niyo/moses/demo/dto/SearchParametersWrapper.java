package niyo.moses.demo.dto;

public class SearchParametersWrapper {
    private int zipCode;
    private int distance;
    private String predominantDegrees;
    private int year;

    public SearchParametersWrapper() {
    }

    public SearchParametersWrapper(int zipCode, int distance, String predominantDegrees, int year) {
        this.zipCode = zipCode;
        this.distance = distance;
        this.predominantDegrees = predominantDegrees;
        this.year = year;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public String getPredominantDegrees() {
        return predominantDegrees;
    }

    public void setPredominantDegrees(String predominantDegrees) {
        this.predominantDegrees = predominantDegrees;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
