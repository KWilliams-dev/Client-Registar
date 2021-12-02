package DatabaseProject;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.sql.*;
import java.util.ArrayList;

public class Main extends Application {

    private void addClient()
    {
        //Creating a new instance of NewClientStage and calling RegisterClientStage method.
        NewClientStage newClientStage = new NewClientStage();
        newClientStage.RegisterClientStage();
    }

    private void deleteClient()
    {
        //Creating a new instance of RemoveClientStage and calling DeleteClientStage method.
        RemoveClientStage removeClientStage = new RemoveClientStage();
        removeClientStage.DeleteClientStage();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        //BorderPane allows for their to be multiple panes in a simple layout
        Label mainLabel = new Label("Register a Client or Delete a Client");
        BorderPane mainBorderPane = new BorderPane();
        BorderPane.setAlignment(mainLabel, Pos.CENTER);
        mainBorderPane.setTop(mainLabel);

        //Creating register button that will call the addClient method when clicked
        Button register =  new Button("Register Client");
        register.setOnAction(e -> addClient());

        //Creating delete button that will call the deleteClient method when clicked
        Button delete =  new Button("Delete Client");
        delete.setOnAction(e -> deleteClient());

        //Creating HBox to house the button components
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);

        //Adding the buttons to the list of components in the hBox
        hBox.getChildren().add(register);
        hBox.getChildren().add(delete);

        //Using the mainBorderPane instance to set the hBox    
        mainBorderPane.setCenter(hBox);

        //Creating stage and passing a new scene
        Stage stage = new Stage();
        stage.setTitle("Client Database Portal");
        stage.setScene(new Scene(mainBorderPane, 250, 100));
        stage.show();

    }


    public static void main(String[] args) {
        //launch method invokes the start method.
        launch(args);

        ArrayList<Client> clientArrayList = new ArrayList<>();

        try {
        //Establishing a connection to the SQL database, creating a statement, storing the results in an arraylist of client objects.
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/clientdata", "root", "Your_MySQL_Password");
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM CLIENT_DATA");

        while (resultSet.next())
        {
            //Iterating through resultSet and grabbing data to be stored in Java
            int id = resultSet.getInt("Client_ID");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String email = resultSet.getString("email");
            String employer = resultSet.getString("Employer");

            //Accessing the getClients method and adding each client to a LinkedList.
            clientArrayList.add(new Client(id, firstName, lastName, email, employer));
        }
            //For each loop to print each client to the console.
            for (Client client : clientArrayList) {
                System.out.println(client);
            }

    } catch(SQLException e) {
        e.printStackTrace();
        }
    }
}
