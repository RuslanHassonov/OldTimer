package sample;
//Basic Car class

public class Car {
    private int memberNumber;
    private String carConstructor;
    private String carModel;
    private int buildYear;
    private String carColor;
    private String carField;

    //constructors

    public Car() {
    }

    public Car(int memberNumber, String carConstructor, String carModel, int buildYear, String carColor, String carField) {
        setMemberNumber(memberNumber);
        setCarConstructor(carConstructor);
        setCarModel(carModel);
        setBuildYear(buildYear);
        setCarColor(carColor);
        setCarField(carField);
    }

    //getters and setters;

    public int getMemberNumber() {
        return memberNumber;
    }

    public void setMemberNumber(int memberNumber){
        this.memberNumber = memberNumber;
    }

    public String getCarConstructor() {
        return carConstructor;
    }

    public void setCarConstructor(String carConstructor) {
        this.carConstructor = carConstructor;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public int getBuildYear() {
        return buildYear;
    }

    public void setBuildYear(int buildYear) {
        this.buildYear = buildYear;
    }

    public String getCarColor() {
        return carColor;
    }

    public void setCarColor(String carColor) {
        this.carColor = carColor;
    }

    public String getCarField() {
        return carField;
    }

    public void setCarField(String carField) {
        this.carField = carField;
    }

    @Override
    public String toString()
    {return String.format("Constructor: %s%nModel: %s%nBouwjaar: %d",getCarConstructor(),getCarModel(),getBuildYear());}
}
