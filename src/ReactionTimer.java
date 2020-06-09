import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
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
        play.setOnAction(e -> play_game(primaryStage));

        Button exit = new Button("Exit");
        exit.setOnAction(e -> Platform.exit());

        main_menu.getChildren().addAll(title, play, exit);

        Scene main_scene = new Scene(main_menu);
        primaryStage.setScene(main_scene);
        primaryStage.setTitle("Welcome to the Reaction Timer!");
        primaryStage.show();
    }

    private void play_game(Stage stage) {
        StackPane button_pane = new StackPane();
        button_pane.setAlignment(Pos.CENTER);

        Circle red_button = new Circle(30);
        red_button.setStroke(Color.RED);
        red_button.setFill(Color.TRANSPARENT);
        red_button.setOnMousePressed(e -> too_early(stage));

        Circle green_button = new Circle(30);
        green_button.setFill(Color.GREEN);
        green_button.setVisible(false);
        green_button.setOnMousePressed(e -> good_press(stage));

        Scene game_scene = new Scene(button_pane);
    }

    private void good_press(Stage stage) {
    }

    private void too_early(Stage stage) {
    }

    public static void main(String[] args) { launch(args); }
}
