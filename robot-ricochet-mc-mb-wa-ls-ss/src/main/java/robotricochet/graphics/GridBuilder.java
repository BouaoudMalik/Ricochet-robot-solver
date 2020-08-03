package robotricochet.graphics;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import robotricochet.services.GameBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * create the game grid and the restart button
 */
public class GridBuilder {
    /**
     * JavaFx Stage is the top level JavaFx container
     * JavaFx GridPane is a layout component which lays out its child components in a grid
     */

    /**
     * this methode builds the window that must be displayed when we press the newGame button
     * @param primaryStage the stage of the GUI
     * @return a Stage
     * @throws IOException thrown buildGrid
     */

    public static Stage showDialog(Stage primaryStage) throws IOException{
        primaryStage = new Stage();
        primaryStage.setResizable(false);
        primaryStage.setTitle("Ricochet robot solver");
        GridPane gridPane = new GridPane();
        GameBuilder game = new GameBuilder();
        GameGraphic gameGraphic = new GameGraphic();
        gridPane.setHgap(2);
        gridPane.setVgap(2);
        Shadow shadow = new Shadow();
        for (int i = 0; i < game.getPlateau().getPlateau().length; i++) {
            for (int j = 0; j < game.getPlateau().getPlateau().length; j++) {
                buildGrid(gridPane, game, i, j);
            }
            GameGraphic.placeRobotButton(gridPane, game, shadow);
            gameGraphic.selectedTargetButton(gridPane, game, shadow);
            gameGraphic.playButton(gridPane, game, shadow);

            restartButton(gridPane, shadow,primaryStage);

        }

        gridPane.setStyle("-fx-background-color:#bd5700;");
        Scene scene = new Scene(gridPane, 900, 640);
        primaryStage.setScene(scene);
        primaryStage.show();
        return  primaryStage;

    }

    /**
     * this methode creates a restart button and add it to the gridPane
     * @param gridPane
     * @param shadow apply shadow to the button
     * @param primaryStage
     * @throws IOException
     */

    private static void restartButton(GridPane gridPane, Shadow shadow,Stage primaryStage) throws IOException{
        GameBuilder game = new GameBuilder();
        GameGraphic gameGraphic = new GameGraphic();
        Button restart = new Button("restart");
        restart.setLayoutX(100);
        restart.setLayoutY(450);
        restart.setPrefWidth(120);
        restart.setPrefHeight(40);
        restart.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
        restart.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                restart.setEffect(shadow);
            }
        });
        gridPane.add(restart, 50, 11);

        restart.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<>() {
            @Override
            public void handle(MouseEvent e) {
                restart.setEffect(null);
            }
        });
        restart.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    primaryStage.hide();
                    showDialog(primaryStage);
                    GameGraphic.placeRobotButton(gridPane,game,shadow);
                    gameGraphic.selectedTargetButton(gridPane,game,shadow);
                    gameGraphic.playButton(gridPane,game,shadow);
                    restartButton(gridPane, shadow,primaryStage);
                } catch (IOException e ) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * this methode builds the graphic grid
     * @param gridPane
     * @param game a GameBuilder Object
     * @param i indice to sweep grid rows
     * @param j indice to sweep grid columns
     * @throws FileNotFoundException
     */

    private static void buildGrid(GridPane gridPane, GameBuilder game, int i, int j) throws FileNotFoundException {
        Image image;
        ImageView imageview;
        switch (game.getPlateau().getPlateau()[i][j].getCaseType()) {
            case OBSTACLE:
                image = new Image(new FileInputStream("src/main/resources/images/OBSTACLE.jpg"));
                imageview = new ImageView(image);
                imageview.setFitHeight(33);
                imageview.setFitWidth(33);
                gridPane.add(imageview, j, i);
                break;

            case EMPTYSPACE:
                image = new Image(new FileInputStream("src/main/resources/images/EMPTYSPACE.jpg"));
                imageview = new ImageView(image);
                imageview.setFitHeight(33);
                imageview.setFitWidth(33);
                gridPane.add(imageview, j, i);

                break;

            case RED_ROBOT_START:
                image = new Image(new FileInputStream("src/main/resources/images/REDROBOTSTART.jpg"));
                imageview = new ImageView(image);
                imageview.setFitHeight(33);
                imageview.setFitWidth(33);
                gridPane.add(imageview, j, i);
                break;


            case GREEN_ROBOT_START:
                image = new Image(new FileInputStream("src/main/resources/images/GREENROBOTSTART.jpg"));
                imageview = new ImageView(image);
                imageview.setFitHeight(33);
                imageview.setFitWidth(33);
                gridPane.add(imageview, j, i);

                break;


            case BLUE_ROBOT_START:
                image = new Image(new FileInputStream("src/main/resources/images/BLUEROBOTSTART.jpg"));
                imageview = new ImageView(image);
                imageview.setFitHeight(33);
                imageview.setFitWidth(33);
                gridPane.add(imageview, j, i);

                break;


            case YELLOW_ROBOT_START:
                image = new Image(new FileInputStream("src/main/resources/images/YELLOWROBOTSTART.jpg"));
                imageview = new ImageView(image);
                imageview.setFitHeight(33);
                imageview.setFitWidth(33);
                gridPane.add(imageview, j, i);


                break;


            case YELLOW_CIRCLE:
                image = new Image(new FileInputStream("src/main/resources/images/YELLOWCIRCLE.jpg"));
                imageview = new ImageView(image);
                imageview.setFitHeight(33);
                imageview.setFitWidth(33);
                gridPane.add(imageview, j, i);

                break;


            case RED_CIRCLE:
                image = new Image(new FileInputStream("src/main/resources/images/REDCIRCLE.jpg"));
                imageview = new ImageView(image);
                imageview.setFitHeight(33);
                imageview.setFitWidth(33);
                gridPane.add(imageview, j, i);

                break;

            case GREEN_CIRCLE:
                image = new Image(new FileInputStream("src/main/resources/images/GREENCIRCLE.jpg"));
                imageview = new ImageView(image);
                imageview.setFitHeight(33);
                imageview.setFitWidth(33);
                gridPane.add(imageview, j, i);

                break;


            case BLUE_CIRCLE:
                image = new Image(new FileInputStream("src/main/resources/images/BLUECIRCLE.jpg"));
                imageview = new ImageView(image);
                imageview.setFitHeight(33);
                imageview.setFitWidth(33);
                gridPane.add(imageview, j, i);

                break;

            case BLUE_SQUARE:
                image = new Image(new FileInputStream("src/main/resources/images/BLUESQUARE.jpg"));
                imageview = new ImageView(image);
                imageview.setFitHeight(33);
                imageview.setFitWidth(33);
                gridPane.add(imageview, j, i);

                break;


            case YELLOW_SQUARE:
                image = new Image(new FileInputStream("src/main/resources/images/YELLOWSQUARE.jpg"));
                imageview = new ImageView(image);
                imageview.setFitHeight(33);
                imageview.setFitWidth(33);
                gridPane.add(imageview, j, i);

                break;


            case RED_SQUARE:
                image = new Image(new FileInputStream("src/main/resources/images/REDSQUARE.jpg"));
                imageview = new ImageView(image);
                imageview.setFitHeight(33);
                imageview.setFitWidth(33);
                gridPane.add(imageview, j, i);

                break;


            case GREEN_SQUARE:
                image = new Image(new FileInputStream("src/main/resources/images/GREENSQUARE.jpg"));
                imageview = new ImageView(image);
                imageview.setFitHeight(33);
                imageview.setFitWidth(33);
                gridPane.add(imageview, j, i);

                break;


            case BLUE_TRIANGLE:
                image = new Image(new FileInputStream("src/main/resources/images/BLUETRIANGLE.jpg"));
                imageview = new ImageView(image);
                imageview.setFitHeight(33);
                imageview.setFitWidth(33);
                gridPane.add(imageview, j, i);

                break;


            case YELLOW_TRIANGLE:
                image = new Image(new FileInputStream("src/main/resources/images/YELLOWTRIANGLE.jpg"));
                imageview = new ImageView(image);
                imageview.setFitHeight(33);
                imageview.setFitWidth(33);
                gridPane.add(imageview, j, i);

                break;


            case GREEN_TRIANGLE:
                image = new Image(new FileInputStream("src/main/resources/images/GREENTRIANGLE.jpg"));
                imageview = new ImageView(image);
                imageview.setFitHeight(33);
                imageview.setFitWidth(33);
                gridPane.add(imageview, j, i);

                break;

            case RED_TRIANGLE:
                image = new Image(new FileInputStream("src/main/resources/images/REDTRIANGLE.jpg"));
                imageview = new ImageView(image);
                imageview.setFitHeight(33);
                imageview.setFitWidth(33);
                gridPane.add(imageview, j, i);


                break;

            case RED_DIAMOND:
                image = new Image(new FileInputStream("src/main/resources/images/REDDIAMOND.jpg"));
                imageview = new ImageView(image);
                imageview.setFitHeight(33);
                imageview.setFitWidth(33);
                gridPane.add(imageview, j, i);


                break;


            case YELLOW_DIAMOND:
                image = new Image(new FileInputStream("src/main/resources/images/YELLOWDIAMOND.jpg"));
                imageview = new ImageView(image);
                imageview.setFitHeight(33);
                imageview.setFitWidth(33);
                gridPane.add(imageview, j, i);


                break;

            case GREEN_DIAMOND:
                image = new Image(new FileInputStream("src/main/resources/images/GREENDIAMOND.jpg"));
                imageview = new ImageView(image);
                imageview.setFitHeight(33);
                imageview.setFitWidth(33);
                gridPane.add(imageview, j, i);


                break;

            case BLUE_DIAMOND:
                image = new Image(new FileInputStream("src/main/resources/images/BLUEDIAMOND.jpg"));
                imageview = new ImageView(image);
                imageview.setFitHeight(33);
                imageview.setFitWidth(33);
                gridPane.add(imageview, j, i);


                break;

            case SLASH_BLUE:
                image = new Image(new FileInputStream("src/main/resources/images/BLUESLASH.jpg"));
                imageview = new ImageView(image);
                imageview.setFitHeight(33);
                imageview.setFitWidth(33);
                gridPane.add(imageview, j, i);

                break;

            case SLASH_GREEN:
                image = new Image(new FileInputStream("src/main/resources/images/GREENSLASH.jpg"));
                imageview = new ImageView(image);
                imageview.setFitHeight(33);
                imageview.setFitWidth(33);
                gridPane.add(imageview, j, i);

                break;


            case SLASH_RED:
                image = new Image(new FileInputStream("src/main/resources/images/REDSLASH.jpg"));
                imageview = new ImageView(image);
                imageview.setFitHeight(33);
                imageview.setFitWidth(33);
                gridPane.add(imageview, j, i);

                break;

            case SLASH_YELLOW:
                image = new Image(new FileInputStream("src/main/resources/images/YELLOWSLASH.jpg"));
                imageview = new ImageView(image);
                imageview.setFitHeight(33);
                imageview.setFitWidth(33);
                gridPane.add(imageview, j, i);

                break;

            case ANTISLASH_BLUE:
                image = new Image(new FileInputStream("src/main/resources/images/BLUEANTISLASH.jpg"));
                imageview = new ImageView(image);
                imageview.setFitHeight(33);
                imageview.setFitWidth(33);
                gridPane.add(imageview, j, i);

                break;

            case ANTISLASH_GREEN:
                image = new Image(new FileInputStream("src/main/resources/images/GREENANTISLASH.jpg"));
                imageview = new ImageView(image);
                imageview.setFitHeight(33);
                imageview.setFitWidth(33);
                gridPane.add(imageview, j, i);

                break;


            case ANTISLASH_YELLOW:
                image = new Image(new FileInputStream("src/main/resources/images/YELLOWANTISLASH.jpg"));
                imageview = new ImageView(image);
                imageview.setFitHeight(33);
                imageview.setFitWidth(33);
                gridPane.add(imageview, j, i);

                break;

            case MULTICOLOR_VORTEX:
                image = new Image(new FileInputStream("src/main/resources/images/MULTICOLORVORTEX.jpg"));
                imageview = new ImageView(image);
                imageview.setFitHeight(33);
                imageview.setFitWidth(33);
                gridPane.add(imageview, j, i);

                break;
            case ANTISLASH_RED:
                image = new Image(new FileInputStream("src/main/resources/images/REDANTISLASH.jpg"));
                imageview = new ImageView(image);
                imageview.setFitHeight(34);
                imageview.setFitWidth(34);
                gridPane.add(imageview, j, i);
                break;

            default:

                break;

        }
    }


}
