package DatabaseProject;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class NewClientStage {

    //Attributes for client stage
    private int id = 0;
    private TextField firstName = new TextField();
    private TextField lastName = new TextField();
    private TextField email = new TextField();
    private TextField employer = new TextField();

    public void RegisterClientStage() {
        //Creating pane
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        pane.setHgap(5.5);
        pane.setVgap(5.5);

        //Aligning TextFields with Labels
        pane.add(new Label("First Name: "),0 ,0);
        pane.add(firstName, 1, 0);
        pane.add(new Label("Last Name: "), 0, 1);
        pane.add(lastName, 1, 1);
        pane.add(new Label("Email: "), 0, 2);
        pane.add(email, 1, 2);
        pane.add(new Label("Employer: "), 0, 3);
        pane.add(employer, 1, 3);

        //Creating register button that calls the addClient method when clicked
        Button register = new Button("Register");
        register.setOnAction(e -> addClient());
        pane.add(register, 0, 4);

        //Creating stage and passing a new scene
        Stage stage = new Stage();
        Scene scene = new Scene(pane);
        stage.setTitle("New Client Registrar");
        stage.setScene(scene);
        stage.show();

    }
    private void addClient() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clientdata", "root", "Your_MySQL_Password");
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO CLIENT_DATA (first_name, last_name, email, Employer) VALUE ('"+firstName.getText()+"', '"+lastName.getText()+"', '"+email.getText()+"', '"+employer.getText()+"')");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
