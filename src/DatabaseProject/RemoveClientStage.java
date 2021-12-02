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

public class RemoveClientStage {

    private TextField id = new TextField();

    public void DeleteClientStage() {

        //Creating pane
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));

        //Aligning TextFields with Labels
        pane.add(new Label("Client ID: "),0 ,0);
        pane.add(id, 1, 0);

        //Creating delete button that calls the deleteClient method when clicked
        Button delete = new Button("Delete");
        delete.setOnAction(e -> deleteClient());
        pane.add(delete, 0, 1);

        //Creating stage and passing a new scene
        Stage stage = new Stage();
        Scene scene = new Scene(pane);
        stage.setTitle("Remove Client");
        stage.setScene(scene);
        stage.show();

    }

    private void deleteClient() {

        //Assigning the text entered to a string, then parsing it to an int variable
        String idSelection = id.getText();
        int id = Integer.parseInt(idSelection);

        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clientdata", "root", "Your_MySQL_Password");
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM `client_data` WHERE `Client_ID` = "+id+";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
