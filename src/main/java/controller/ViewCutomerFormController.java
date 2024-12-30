package controller;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Customer;

import java.util.List;

public class ViewCutomerFormController {

    @FXML
    private Button btnReload;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colSalary;

    @FXML
    private TableView<?> tblCustomers;

    @FXML
    void btnReloadOnAction(ActionEvent event) {
        List<Customer> connection = DBConnection.getInstance().getConnetion();


    }

}
