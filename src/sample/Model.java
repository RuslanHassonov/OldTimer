package sample;
//Class used to interact with DB

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;


public class Model {

    private static final String URL = "jdbc:derby://localhost:1527/oldtimers";
    private static final String USERNAME = "deitel";
    private static final String PASSWORD = "deitel";

    private Connection connection;
    private PreparedStatement selectAllMembers;
    private PreparedStatement selectAllCars;
    private PreparedStatement addNewMember;
    private PreparedStatement deleteExistingMember;
    private PreparedStatement addNewCar;
    private PreparedStatement deleteExistingCar;
    private PreparedStatement checkIfMemberExists;
    private PreparedStatement checkIfCarExists;

    //constructor

    public Model() {
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            selectAllMembers = connection.prepareStatement("SELECT * FROM members");
            selectAllCars = connection.prepareStatement("SELECT * FROM cars");
            addNewMember = connection.prepareStatement("INSERT INTO members " +
                    "(lastName, firstName, street, houseNumber, zipCode, city, country, phone, mobile, email)" +
                    "VALUES (?,?,?,?,?,?,?,?,?,?)");
            deleteExistingMember = connection.prepareStatement("DELETE FROM members WHERE lastName = ? AND firstName = ?");

            addNewCar = connection.prepareStatement("INSERT INTO cars " +
                    "(memberNumber, carConstructor, carModel, buildYear, carColor, usageField)" +
                    "VALUES (?,?,?,?,?,?)");
            deleteExistingCar = connection.prepareStatement("DELETE FROM cars WHERE carModel = ? AND buildYear = ? AND memberNumber = ?");

            checkIfMemberExists = connection.prepareStatement("SELECT 1 FROM members WHERE firstName = ? AND lastName = ? AND street = ? AND houseNumber = ? AND city = ?");
            checkIfCarExists = connection.prepareStatement("SELECT 1 FROM cars WHERE carConstructor = ? AND carModel = ? AND buildYear =? AND memberNumber = ?");

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            System.exit(1);
        }
    }

    public List<Member> getAllMembers() {
        try (ResultSet resultSet = selectAllMembers.executeQuery()) {
            List<Member> results = new ArrayList<Member>();

            while (resultSet.next()) {
                results.add(new Member(
                        resultSet.getString("LASTNAME"),
                        resultSet.getString("FIRSTNAME"),
                        resultSet.getString("STREET"),
                        resultSet.getInt("HOUSENUMBER"),
                        resultSet.getInt("ZIPCODE"),
                        resultSet.getString("CITY"),
                        resultSet.getString("COUNTRY"),
                        resultSet.getString("PHONE"),
                        resultSet.getString("MOBILE"),
                        resultSet.getString("EMAIL")));
            }

            return results;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    public List<Car> getAllCars() {
        try (ResultSet resultSet = selectAllCars.executeQuery()) {
            List<Car> results = new ArrayList<Car>();

            while (resultSet.next()) {
                results.add(new Car(
                        resultSet.getInt("MEMBERNUMBER"),
                        resultSet.getString("CARCONSTRUCTOR"),
                        resultSet.getString("CARMODEL"),
                        resultSet.getInt("BUILDYEAR"),
                        resultSet.getString("CARCOLOR"),
                        resultSet.getString("USAGEFIELD")));
            }

            return results;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }

    public int addMember(String firstName, String lastName, String streetName, int number, int zipCode, String city, String country, String phoneNumber, String mobileNumber,
                         String email) {

        try {
            if (!checkMemberExistance(firstName, lastName, streetName, number, city)) {
                addNewMember.setString(1, lastName);
                addNewMember.setString(2, firstName);
                addNewMember.setString(3, streetName);
                addNewMember.setInt(4, number);
                addNewMember.setInt(5, zipCode);
                addNewMember.setString(6, city);
                addNewMember.setString(7, country);
                addNewMember.setString(8, phoneNumber);
                addNewMember.setString(9, mobileNumber);
                addNewMember.setString(10, email);

                return addNewMember.executeUpdate();
            } else {
                return 0;
            }


        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return 0;
        }
    }

    public int addCar(String carConstructor, String carModel, int buildYear, String carColor, String usingField, int memberNumber) {

        try {

            if (!checkCarExistance(carModel, carConstructor, buildYear, memberNumber)) {
                addNewCar.setInt(1, memberNumber);
                addNewCar.setString(2, carConstructor);
                addNewCar.setString(3, carModel);
                addNewCar.setInt(4, buildYear);
                addNewCar.setString(5, carColor);
                addNewCar.setString(6, usingField);

                return addNewCar.executeUpdate();
            }
            return 0;

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return 0;
        }
    }

    public int deleteMember(String firstName, String lastName) {
        try {
            deleteExistingMember.setString(1, lastName);
            deleteExistingMember.setString(2, firstName);

            return deleteExistingMember.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return 0;
        }

    }

    public int deleteCar(String model, int buildYear, int memberNumber) {
        try {
            deleteExistingCar.setString(1, model);
            deleteExistingCar.setInt(2, buildYear);
            deleteExistingCar.setInt(3, memberNumber);

            return deleteExistingCar.executeUpdate();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
            return 0;
        }

    }

    private boolean checkMemberExistance(String firstName, String lastName, String streetName, int houseNumber, String city) {
        try {
            checkIfMemberExists.setString(1, firstName);
            checkIfMemberExists.setString(2, lastName);
            checkIfMemberExists.setString(3, streetName);
            checkIfMemberExists.setInt(4, houseNumber);
            checkIfMemberExists.setString(5, city);

            ResultSet rs = checkIfMemberExists.executeQuery();

            if (!rs.next()) {
                return false;
            }
            else {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }

    private boolean checkCarExistance(String carModel, String carConstructor, int buildYear, int memberNumber) {
        try {
            checkIfCarExists.setString(1, carConstructor);
            checkIfCarExists.setString(2, carModel);
            checkIfCarExists.setInt(3, buildYear);
            checkIfCarExists.setInt(4, memberNumber);

            ResultSet rs = checkIfCarExists.executeQuery();

            if (!rs.next()) {
                return false;
            }
            else {
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return true;
        }
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }
}
