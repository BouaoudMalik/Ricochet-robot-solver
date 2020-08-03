package robotricochet.graphics;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.effect.Shadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import robotricochet.entity.CaseType;
import robotricochet.entity.Position;
import robotricochet.entity.Robot;
import robotricochet.entity.Token;
import robotricochet.services.GameBuilder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import static java.util.concurrent.Executors.newSingleThreadScheduledExecutor;

/**
 * contains methods to run the graphic game ,buttons ,options ...
 */
public class GameGraphic {

    CaseType drownToken = Token.getTokenCaseType();
    /**
     * JavaFx GridPane is a layout component which lays out its child components in a grid
     */

    /**
     * this methode creates a Button to place robots and add it into the gridPane
     * @param gridPane the graphic grid game
     * @param game a GameBuilder Object
     * @param shadow apply shadow to the boutton
     */
    public static void placeRobotButton(GridPane gridPane, GameBuilder game, Shadow shadow) {
        Button placeRobot = new Button("Place Robots");
        placeRobot.setLayoutX(100);
        placeRobot.setLayoutY(450);
        placeRobot.setPrefWidth(120);
        placeRobot.setPrefHeight(40);
        placeRobot.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
        placeRobot.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                placeRobot.setEffect(shadow);
            }
        });
        gridPane.add(placeRobot, 50, 5);

        placeRobot.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<>() {
            @Override
            public void handle(MouseEvent e) {
                placeRobot.setEffect(null);
            }
        })
        ;
        placeRobot.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    putRobots(gridPane, game);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * this methode creates a Button to select a target and add it into the gridPane
     * @param gridPane the graphic grid game
     * @param game a GameBuilder Object
     * @param shadow apply shadow to the boutton
     */

    public void selectedTargetButton(GridPane gridPane, GameBuilder game, Shadow shadow) {
        Button target = new Button("Select a target");
        target.setLayoutX(100);
        target.setLayoutY(450);
        target.setPrefWidth(120);
        target.setPrefHeight(40);
        target.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
        target.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                target.setEffect(shadow);
            }
        });
        gridPane.add(target, 50, 7);

        target.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<>() {
            @Override
            public void handle(MouseEvent e) {
                target.setEffect(null);
            }
        })
        ;
        target.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                selectedTarget(game, drownToken, gridPane);
            }
        });
    }


    /**
     * this methode creates a Button to start the game and add it into the gridPane
     * @param gridPane the graphic grid game
     * @param game a GameBuilder Object
     * @param shadow apply shadow to the boutton
     */
    public void playButton(GridPane gridPane, GameBuilder game, Shadow shadow) {
        Button play = new Button("play");
        play.setLayoutX(100);
        play.setLayoutY(450);
        play.setPrefWidth(120);
        play.setPrefHeight(40);
        play.setStyle("-fx-background-color: yellow; -fx-text-fill: black;");
        play.addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {

                play.setEffect(shadow);
            }
        });
        gridPane.add(play, 50, 9);

        play.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<>() {
            @Override
            public void handle(MouseEvent e) {
                play.setEffect(null);
            }
        })
        ;
        play.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                try {
                    play(gridPane, drownToken, game);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * this methode puts robots on their place
     * @param pane the graphic grid game
     * @param game a GameBuilder object
     * @throws FileNotFoundException while loading the images
     */

    public static void putRobots(GridPane pane, GameBuilder game) throws FileNotFoundException {
        Image image = null;
        ImageView imageview = null;
        for (int i = 0; i < game.getPlateau().getPlateau().length; i++) {
            for (int j = 0; j < game.getPlateau().getPlateau().length; j++) {
                switch (game.getPlateau().getPlateau()[i][j].getCaseType()) {
                    case BLUE_ROBOT_START:
                        image = new Image(new FileInputStream("src/main/resources/images/BLUE_ROBOT.jpg"));
                        imageview = new ImageView(image);
                        imageview.setFitHeight(33);
                        imageview.setFitWidth(33);
                        pane.add(imageview, j, i);
                        break;

                    case RED_ROBOT_START:
                        image = new Image(new FileInputStream("src/main/resources/images/RED_ROBOT.jpg"));
                        imageview = new ImageView(image);
                        imageview.setFitHeight(33);
                        imageview.setFitWidth(33);
                        pane.add(imageview, j, i);

                        break;

                    case YELLOW_ROBOT_START:
                        image = new Image(new FileInputStream("src/main/resources/images/YELLOW_ROBOT.jpg"));
                        imageview = new ImageView(image);
                        imageview.setFitHeight(33);
                        imageview.setFitWidth(33);
                        pane.add(imageview, j, i);
                        break;


                    case GREEN_ROBOT_START:
                        image = new Image(new FileInputStream("src/main/resources/images/GREEN_ROBOT.jpg"));
                        imageview = new ImageView(image);
                        imageview.setFitHeight(33);
                        imageview.setFitWidth(33);
                        pane.add(imageview, j, i);

                        break;

                    default:
                        break;
                }
            }
        }


    }

    /**
     * this methode marks the selected target on the grid
     * @param game a GameBuilder Object
     * @param drownToken target
     * @param gridPane graphic grid game
     */
    public static void selectedTarget(GameBuilder game, CaseType drownToken, GridPane gridPane) {
        Position position = game.getPlateau().searchPositionOf(drownToken);
        Rectangle rectangle = new Rectangle(33, 33);
        rectangle.setStroke(Color.LIGHTSTEELBLUE);
        rectangle.setFill(Color.rgb(255, 0, 255, 0.4));
        gridPane.add(rectangle, position.getY(), position.getX());
    }

    /**
     * play graphically methode
     * @param gridPane graphic grid game
     * @param drownToken target
     * @param game Gamebuilder Object
     * @throws FileNotFoundException can be while loading the robot image file
     * @throws InterruptedException can be thrown by the timer
     */

    public static void play(GridPane gridPane, CaseType drownToken, GameBuilder game) throws FileNotFoundException, InterruptedException {
        Robot currentRobot = game.currentRobot(drownToken);
        currentRobot.injectCurrentGrid(game.getPlateau());
        List<Position> shortestPath = currentRobot.aStar(currentRobot, drownToken);
        String robotImageString = robotColorImages(currentRobot).get(0);
        String startingCase = robotColorImages(currentRobot).get(1);
        final String STR = "src/main/resources/images/";
        Image image = new Image(new FileInputStream(STR + robotImageString));
        ImageView imageview = new ImageView(image);
        imageview.setFitHeight(32);
        imageview.setFitWidth(32);
        Image image2 = new Image(new FileInputStream(STR + robotImageString));
        Image img = new Image(new FileInputStream(STR + startingCase));
        ImageView iv = new ImageView(img);
        iv.setFitHeight(32);
        iv.setFitWidth(32);
        ScheduledExecutorService service = null;
        int nbrCost=0;
        if (!shortestPath.isEmpty()) {
            gridPane.add(iv, shortestPath.get(0).getY(), shortestPath.get(0).getX());
            for (int i = 1; i < shortestPath.size(); i++) {
                nbrCost ++;
                Thread.sleep(1000);
                ThreadFactory tFactory = r -> {
                    Thread t = new Thread(r);
                    t.setDaemon(true);
                    return t; };
                service= newSingleThreadScheduledExecutor(tFactory);

                int finalI = i;
                service.scheduleAtFixedRate(() -> {
                    Platform.runLater(() -> moveRobot(gridPane, shortestPath, image2, finalI));
                }, 12, 2, TimeUnit.SECONDS);


            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("You won");
            alert.setHeaderText("End of the round !");
            String s = "Congratulation the target has been reached,with "+nbrCost+" moves done";
            alert.setContentText(s);
            alert.show();

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Information");
            alert.setHeaderText("Is there any path to reach the selected target?");
            String s = "Sorry !No path exists, please exit or restart the game ";
            alert.setContentText(s);
            alert.show();
        }
    }

    /**
     * this methode moves robots graphically
     * @param gridPane the graphic grid game
     * @param shortestPath list of positions
     * @param image2 robot image
     * @param i indice to access the list of positions
     */
    private static synchronized void moveRobot(GridPane gridPane, List<Position> shortestPath, Image image2, int i) {
        ImageView imageview2;
        Position currentPosition = shortestPath.get(i);
        imageview2 = new ImageView(image2);
        imageview2.setFitHeight(32);
        imageview2.setFitWidth(32);
        gridPane.add(imageview2, currentPosition.getY(), currentPosition.getX());

    }

    /**
     *load the respective path of images of the robots and it's own starting case
     * @param robot current robot
     * @return a list of current robot Image and current robot start image
     */

    private static List<String> robotColorImages(Robot robot) {
        List<String> arrayOfImages = new ArrayList<>();
        robotricochet.entity.Color robotColor = robot.getColor();
        switch (robotColor) {
            case RED:
                arrayOfImages.add("RED_ROBOT.jpg");
                arrayOfImages.add("REDROBOTSTART.jpg");
                break;
            case BLUE:

                arrayOfImages.add("BLUE_ROBOT.jpg");
                arrayOfImages.add("BLUEROBOTSTART.jpg");
                break;
            case GREEN:

                arrayOfImages.add("GREEN_ROBOT.jpg");
                arrayOfImages.add("GREENROBOTSTART.jpg");
                break;
            case YELLOW:

                arrayOfImages.add("YELLOW_ROBOT.jpg");
                arrayOfImages.add("YELLOWROBOTSTART.jpg");
                break;
            default:
                break;
        }
        return arrayOfImages;
    }

}
