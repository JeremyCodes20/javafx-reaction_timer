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

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class ReactionTimer extends Application {

    Scene main_scene;
    Scene game_scene;
    long start_time;
    long end_time;

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
        exit.setOnAction(e -> {
            Platform.exit();
            System.exit(0);
        });

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

        ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
        Runnable wait = () -> {
            green_button.setVisible(true);
            start_time = System.currentTimeMillis();
        };
        int delay = ThreadLocalRandom.current().nextInt(3, 9);

        ses.schedule(wait, delay, TimeUnit.SECONDS);
    }

    private void good_press(Stage stage) {
        end_time = System.currentTimeMillis();
        System.out.println(end_time);

        float diff_time = TimeUnit.MILLISECONDS.toSeconds(end_time - start_time);

        stage.setTitle("Your time was: " + diff_time + ".");
        stage.setScene(main_scene);
    }

    private void too_early(Stage stage) {
        stage.setTitle("Too early! Try again.");
        stage.setScene(main_scene);
    }

    public static void main(String[] args) { launch(args); }
}
