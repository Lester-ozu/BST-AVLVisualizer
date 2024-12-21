import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class App extends Application{
    public static void main(String[] args) throws Exception {
        
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        
        try {

            stage.initStyle(StageStyle.TRANSPARENT);

            Parent root = FXMLLoader.load(getClass().getResource("homeScene.fxml"));
            root.setStyle("-fx-background-color: white; -fx-background-radius: 20; -fx-padding: 20;" +
                          "-fx-border-color: black; -fx-border-width: 2; -fx-border-radius: 20;");

            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);

            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
        }

        catch (Exception e) {
            
            e.printStackTrace();
        }
    }
}
