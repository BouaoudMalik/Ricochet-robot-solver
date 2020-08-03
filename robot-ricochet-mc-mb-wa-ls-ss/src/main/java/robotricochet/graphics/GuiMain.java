package robotricochet.graphics;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.sound.sampled.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * launch the javaFX application
 */
public class GuiMain extends Application {

    public static void main(String[] args) {
        Application.launch(GuiMain.class, args);
    }
    /**
     * JavaFx Stage is the top level JavaFx container
     * JavaFx Group is a container component which applies no special layout to its children
     * JavaFx Scene represents the physical contents of a JavaFx application
     */

    /**
     * this methode creates a separate call stack for the thread
     * @param primaryStage the stage of the GUI
     */

    @Override
    public void start(Stage primaryStage)  {
        String pathToSound="src/main/resources/sounds/game.wav";
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(pathToSound).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(10);
            clip.start();
        } catch (LineUnavailableException | UnsupportedAudioFileException | IOException  | NullPointerException e) {
            e.printStackTrace();
        }


        primaryStage.setTitle("RicochetRobot Game");
        primaryStage.setResizable(false);
        Group root = new Group();
        Scene scene = new Scene(root, 900, 660);
        Shadow shadow = new Shadow();
        firstWindowView(primaryStage, root, scene, shadow);

    }

    /**
     * this methode creates the menu game
     * @param primaryStage
     * @param root
     * @param scene
     * @param shadow apply shadow to the button
     */

    private void firstWindowView(Stage primaryStage, Group root, Scene scene, Shadow shadow) {
        primaryStage.setResizable(false);
        ImageView backGroundImage = null;
        try {
            backGroundImage = new ImageView(
                    new Image(new FileInputStream("src/main/resources/images/ricochet-robots-board.jpg")));
            primaryStage.getIcons().add(new Image(new FileInputStream("src/main/resources/images/icon.jpg")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        backGroundImage.setFitHeight(1400);
        backGroundImage.setFitWidth(900);
        backGroundImage.setPreserveRatio(true);
        Text gameTitle = new Text("Ricochet Robot Solver");
        gameTitle.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));
        gameTitle.setX(20);
        gameTitle.setY(70);
        gameTitle.setFill(Color.YELLOW); // Setting the color
        gameTitle.setStrokeWidth(2); // Setting the Stroke
        gameTitle.setStroke(Color.BROWN); // Setting the stroke color
        Button playButton = newGameButtonCreation(shadow);
        Button rulesButton = rulesButtonCreation(shadow);
        Button exitButton = exitButtonCreation(primaryStage, shadow);
        GridPane grid = new GridPane();
        grid.add(backGroundImage, 0, 0);
        grid.add(gameTitle, 0, 0, 2, 1);
        grid.add(playButton, 1, 1);
        grid.setHgap(25);
        grid.setVgap(20);
        root.getChildren().addAll(backGroundImage, gameTitle, playButton, rulesButton, exitButton);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * this methode creates the exit button to get out of the game
     * @param primaryStage
     * @param shadow apply shadow to the button
     * @return the exit button
     */

    private Button exitButtonCreation(Stage primaryStage, Shadow shadow) {
        Button exitButton = new Button("Exit");
        exitButton.setLayoutX(720);
        exitButton.setLayoutY(510);
        exitButton.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        exitButton.setStyle("-fx-background-color: transparent; -fx-text-fill: yellow;");
        exitButton.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                exitButton.setEffect(shadow);
            }
        });

        exitButton.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                exitButton.setEffect(null);
            }
        });
        exitButton.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });
        return exitButton;
    }

    /**
     * this methode creates the new game button to start a new game
     * @param shadow apply shadow to the button
     * @return the new game button
     */

    private Button newGameButtonCreation(Shadow shadow) {
        Stage primaryStage = null;
        Button playButton = new Button("New Game");
        playButton.setLayoutX(720);
        playButton.setLayoutY(450);
        playButton.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        playButton.setStyle("-fx-background-color: transparent;-fx-text-fill: yellow;");
        playButton.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                playButton.setEffect(shadow);

            }
        });

        // Removing the shadow when the mouse cursor is off ( 1st button)
        playButton.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                playButton.setEffect(null);

            }
        });
        playButton.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent event) {
                try {
                    GridBuilder.showDialog(primaryStage);
                } catch (IOException  e) {
                    e.printStackTrace();
                }
            }
        });
        return playButton;
    }

    /**
     * this methode creates the rules button to show game rules
     * @param shadow apply shadow to the button
     * @return the rules button
     */

    private Button rulesButtonCreation(Shadow shadow) {
        Button rulesButton = new Button("Rules");
        rulesButton.setLayoutX(720);
        rulesButton.setLayoutY(480);
        rulesButton.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 15));
        rulesButton.setStyle("-fx-background-color: transparent; -fx-text-fill: yellow;");
        rulesButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                RulesBuilder.showRules();
            }
        });
        rulesButton.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                rulesButton.setEffect(shadow);
            }
        });

        rulesButton.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<>() {
            @Override
            public void handle(MouseEvent e) {
                rulesButton.setEffect(null);
            }
        });
        return rulesButton;
    }



}



