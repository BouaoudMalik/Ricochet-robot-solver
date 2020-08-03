package robotricochet.graphics;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * launch the rules of the game while choosing Rules Button in the principale menu
 */
public class RulesBuilder {

    /**
     * this methode builds the window that must be displayed when we press the rules button
     */
    public static void showRules() {
        Stage primaryStage = new Stage();
        primaryStage.setResizable(false);
        primaryStage.setTitle("Gaming Rules");
        Group root = new Group();
        Scene scene = new Scene(root, 638, 500);
        primaryStage.setResizable(false);
        ImageView rulesImage = null;
        try {
            rulesImage = new ImageView(
                    new Image(new FileInputStream("src/main/resources/images/RULES.jpg")));
            primaryStage.getIcons().add(new Image(new FileInputStream("src/main/resources/images/icong.jpg")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        rulesImage.setFitHeight(500);
        rulesImage.setFitWidth(800);
        rulesImage.setPreserveRatio(true);
        root.getChildren().add(rulesImage);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}



