package sample;
//Controller class for JavaFX

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    private ListView<Member> memberListView;
    @FXML
    private ListView<Car> carListView;
    @FXML
    private TextField tf_firstName;
    @FXML
    private TextField tf_lastName;
    @FXML
    private TextField tf_street;
    @FXML
    private TextField tf_number;
    @FXML
    private TextField tf_zipCode;
    @FXML
    private TextField tf_city;
    @FXML
    private TextField tf_country;
    @FXML
    private TextField tf_phone;
    @FXML
    private TextField tf_mobile;
    @FXML
    private TextField tf_email;
    @FXML
    private TextField tf_constructor;
    @FXML
    private TextField tf_carModel;
    @FXML
    private TextField tf_buildYear;
    @FXML
    private TextField tf_color;
    @FXML
    private TextField tf_field;
    @FXML
    private TextField tf_ownerNumber;

    private final Model dbQueries = new Model();

    private final ObservableList<Member> memeberList = FXCollections.observableArrayList();
    private final ObservableList<Car> carList = FXCollections.observableArrayList();

    public void initialize() {
        memberListView.setItems(memeberList);
        carListView.setItems(carList);
        getAllMemberEntries();
        getAllCarEntries();

        memberListView.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldValue, newValue) -> {
                    displayMember(newValue);
                }
        );

        carListView.getSelectionModel().selectedItemProperty().addListener(
                (observableValue, oldValue, newValue) -> {
                    displayCar(newValue);
                }
        );
    }

    private void getAllMemberEntries() {
        memeberList.setAll(dbQueries.getAllMembers());
    }

    private void getAllCarEntries() {
        carList.setAll(dbQueries.getAllCars());
    }

    /*private void selectFirstEntry() {
        memberListView.getSelectionModel().selectFirst();
        carListView.getSelectionModel().selectFirst();
    }*/

    private void displayMember(Member member) {
        if (member != null) {
            tf_firstName.setText(member.getFirstName());
            tf_lastName.setText(member.getLastName());
            tf_street.setText(member.getStreet());
            tf_number.setText(String.valueOf(member.getHouseNumber()));
            tf_city.setText(member.getCity());
            tf_zipCode.setText(String.valueOf(member.getZipCode()));
            tf_country.setText(member.getCountry());
            tf_phone.setText(member.getPhoneNumber());
            tf_mobile.setText(member.getMobileNumber());
            tf_email.setText(member.getEmailAddress());
        } else {
            clearMemberInputFields();
        }
    }

    private void displayCar(Car car) {
        if (car != null) {
            tf_constructor.setText(car.getCarConstructor());
            tf_carModel.setText(car.getCarModel());
            tf_color.setText(car.getCarColor());
            tf_buildYear.setText(String.valueOf(car.getBuildYear()));
            tf_field.setText(car.getCarField());
            tf_ownerNumber.setText(String.valueOf(car.getMemberNumber()));
        } else {
            clearCarInputFields();
        }
    }

    private void clearMemberInputFields() {
        tf_firstName.clear();
        tf_lastName.clear();
        tf_street.clear();
        tf_number.clear();
        tf_zipCode.clear();
        tf_city.clear();
        tf_country.clear();
        tf_phone.clear();
        tf_mobile.clear();
        tf_email.clear();
    }

    private void clearCarInputFields() {
        tf_constructor.clear();
        tf_carModel.clear();
        tf_color.clear();
        tf_buildYear.clear();
        tf_field.clear();
        tf_ownerNumber.clear();
    }

    @FXML
    void addMemberButtonPressed(ActionEvent event) {
        int result = dbQueries.addMember(
                tf_firstName.getText(), tf_lastName.getText(),
                tf_street.getText(), Integer.parseInt(tf_number.getText()),
                Integer.parseInt(tf_zipCode.getText()), tf_city.getText(), tf_country.getText(),
                tf_phone.getText(), tf_mobile.getText(), tf_email.getText());

        if (result == 1) {
            displayAlert(AlertType.INFORMATION, "Entry Added",
                    "New entry successfully added.");
        } else {
            displayAlert(AlertType.ERROR, "Entry Not Added",
                    "Unable to add entry.");
        }

        getAllMemberEntries();
    }

    @FXML
    void addCarButtonPressed(ActionEvent event) {
        int result = dbQueries.addCar(
                tf_constructor.getText(), tf_carModel.getText(),
                Integer.parseInt(tf_buildYear.getText()),
                tf_color.getText(), tf_field.getText(),
                Integer.parseInt(tf_ownerNumber.getText())
        );

        if (result == 1) {
            displayAlert(AlertType.INFORMATION, "Entry Added",
                    "New entry successfully added.");
        } else {
            displayAlert(AlertType.ERROR, "Entry Not Added",
                    "Unable to add entry.");
        }

        getAllCarEntries();
    }

    @FXML
    void clearMemberButtonPressed(ActionEvent event) {
        clearMemberInputFields();
    }

    @FXML
    void clearCarButtonPressed(ActionEvent event) {
        clearCarInputFields();
    }

    @FXML
    void deleteCarButtonPressed(ActionEvent event){
        int result = dbQueries.deleteCar(tf_carModel.getText(), Integer.parseInt(tf_buildYear.getText()), Integer.parseInt(tf_ownerNumber.getText()));
        getAllCarEntries();

        if (result == 1) {
            displayAlert(AlertType.INFORMATION, "Entry Deleted",
                    "New entry successfully deleted.");
        } else {
            displayAlert(AlertType.ERROR, "Entry Not Deleted",
                    "Unable to delete entry.");
        }
        clearCarInputFields();
        getAllCarEntries();

    }

    @FXML
    void deleteMemberButtonPressed(ActionEvent event){
        int result = dbQueries.deleteMember(tf_firstName.getText(), tf_lastName.getText());

        if (result == 1) {
            displayAlert(AlertType.INFORMATION, "Entry Deleted",
                    "New entry successfully deleted.");
        } else {
            displayAlert(AlertType.ERROR, "Entry Not Deleted",
                    "Unable to delete entry.");
        }
        clearMemberInputFields();
        getAllMemberEntries();
        getAllCarEntries();
    }


    private void displayAlert(
            AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
