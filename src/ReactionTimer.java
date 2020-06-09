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

import java.util.concurrent.TimeUnit;

public class ReactionTimer extends Application {

    Scene main_scene;
    Scene game_scene;

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
        play.setOnAction(e -> {
            try {
                play_game(primaryStage);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        });

        Button exit = new Button("Exit");
        exit.setOnAction(e -> Platform.exit());

        main_menu.getChildren().addAll(title, play, exit);

        main_scene = new Scene(main_menu);
        primaryStage.setScene(main_scene);
        primaryStage.setTitle("Welcome to the Reaction Timer!");
        primaryStage.show();
    }

    private void play_game(Stage stage) throws InterruptedException {
        StackPane button_pane = new StackPane();
        button_pane.setAlignment(Pos.CENTER);

        Circle red_button = new Circle(50);
        red_button.setStroke(Color.RED);
        red_button.setFill(Color.TRANSPARENT);
        red_button.setOnMousePressed(e -> too_early(stage));

        Circle green_button = new Circle(53);
        green_button.setFill(Color.GREEN);
        green_button.setVisible(false);
        green_button.setOnMousePressed(e -> good_press(stage));

        button_pane.getChildren().addAll(red_button, green_button);

        game_scene = new Scene(button_pane);

        stage.setScene(game_scene);

//        TimeUnit.SECONDS.sleep(2);
//        green_button.setVisible(true);
    }

    private void good_press(Stage stage) {
    }

    private void too_early(Stage stage) {
        stage.setTitle("Too early! Try again.");
        stage.setScene(main_scene);
    }

    public static void main(String[] args) { launch(args); }
}
