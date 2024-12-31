package controller;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customer;


import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CustomerFromController implements Initializable {

    @FXML
    private TableColumn colId;

    @FXML
    private TableColumn colName;

    @FXML
    private TableColumn colAddress;

    @FXML
    private TableColumn colSalary;

    @FXML
    private TableView<Customer> tblCustomers;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSalary;

    List<Customer> connetion = DBConnection.getInstance().getConnetion();

        
    @FXML
    void btnAddOnAction(ActionEvent event) {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colSalary.setCellValueFactory(new PropertyValueFactory<>("salary"));

        connetion.add(new Customer(txtId.getText(), txtName.getText(), txtAddress.getText(), Double.parseDouble(txtSalary.getText())));
        loadTable();

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

        // Get the selected customer from the TableView
        Customer selectedCustomer = tblCustomers.getSelectionModel().getSelectedItem();

        if (selectedCustomer != null) {
            try {
                // Connect to the database
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "12345678");

                // Delete the selected customer from the database
                PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM customer WHERE id = ?");
                preparedStatement.setString(1, selectedCustomer.getId());
                int affectedRows = preparedStatement.executeUpdate();

                if (affectedRows > 0) {
                    System.out.println("Customer deleted successfully!");
                    // Refresh the table by reloading data
                    loadTable();
                } else {
                    System.out.println("Failed to delete customer.");
                }

                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No customer selected to delete.");
        }


    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void btnViewItemFormOnAction(ActionEvent event) {
        Stage stage = new Stage();

        try {
            stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/view_cutomer_form.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        stage.show();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    private void loadTable() {

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/thogakade", "root", "12345678");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT *FROM customer");
            while (resultSet.next()) {
                connetion.add(new Customer(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3), resultSet.getDouble(4)));
            }


            System.out.println(connection);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        ObservableList<Customer> customerObservableList = FXCollections.observableArrayList();

        connetion.forEach(customer -> {
            customerObservableList.add(customer);
        });


        tblCustomers.setItems(customerObservableList);
    }
}
