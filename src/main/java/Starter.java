import model.Customer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Starter extends Application {
    public static void main(String[] args) {
        launch();

        Customer customer = new Customer();
        new Customer("001","saman","panadura",75000.0);
    }

    @Override
    public void start(Stage stage) throws Exception {

        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/view/Customer.fxml"))));
        stage.show();
    }
}
