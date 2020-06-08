import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ReactionTimer extends Application {

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setWidth(500);
        primaryStage.setHeight(400);

        VBox main_menu = new VBox();
        main_menu.setAlignment(Pos.CENTER);
        main_menu.setSpacing(20);

        Text title = new Text("Reaction Timer");
        title.setStroke(Color.RED);
        title.setFont(Font.font("Verdana", 30));

        Button play = new Button("Play");
        play.setOnAction(e -> play_game());

        Button exit = new Button("Exit");
        main_menu.getChildren().addAll(title, play, exit);

        Scene main_scene = new Scene(main_menu);
        primaryStage.setScene(main_scene);
        primaryStage.setTitle("Welcome to the Reaction Timer!");
        primaryStage.show();

    }

    private void play_game() {
        
    }

    public static void main(String[] args) { launch(args); }
}
