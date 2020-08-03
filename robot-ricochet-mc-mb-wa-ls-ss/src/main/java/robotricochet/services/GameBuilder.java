package robotricochet.services;

import robotricochet.entity.CaseType;
import robotricochet.entity.Position;
import robotricochet.entity.Robot;
import robotricochet.entity.Token;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

/**
 * allow us to launch the construction of the grid game ,initialise the tokens
 */
public class GameBuilder {
    private static Logger logger = Logger.getAnonymousLogger();
    private static final boolean DEBUG = true;
    private Plateau plateau;
    private RobotBuilder robotBuilder;

    /**GameBuilder
     * constructor of Gamebuilder
     * @throws IOException thrown by the instancition of Objectc plateau
     */
    public GameBuilder() throws IOException {
        this.plateau = new Plateau();
        this.robotBuilder = new RobotBuilder();
        this.initGame();
    }

    /**getPlateau
     * getter of the Plateau
     * @return Plateau of the current game
     */
    public Plateau getPlateau() {
        return plateau;
    }

    /**getRobotBuilder
     * getter of the RobotBuilder class
     * @return a object type RobotBuilder
     */
    public RobotBuilder getRobotBuilder() {
        return this.robotBuilder;
    }

    /**initGame
     * initialize the game by placing the robots on their starting cases
     */
    public void initGame() {
        if (DEBUG) {
            Token.printTokensContent();
            this.plateau.printPlateau();
        }
        this.robotBuilder.placeRobotOnStartingCases(this.plateau.getPlateau());
    }

    /**
     * currentRobot
     * @param  token the  current drown token
     * @return a robot according to the given token
     */
    public Robot currentRobot(CaseType token) {

        if (Token.redTokensList().contains(token)) {
            return robotBuilder.getRedRobot();
        }
        if (Token.greenTokensList().contains(token)) {
            return robotBuilder.getGreenRobot();
        }
        if (Token.blueTokensList().contains(token)) {
            return robotBuilder.getBlueRobot();
        }
        if (Token.yellowTokensList().contains(token)) {
            return robotBuilder.getYellowRobot();
        }
        if (token == CaseType.MULTICOLOR_VORTEX) {
            List<Robot> robotsList = new ArrayList<>();
            robotsList.add(robotBuilder.getBlueRobot());
            robotsList.add(robotBuilder.getGreenRobot());
            robotsList.add(robotBuilder.getRedRobot());
            robotsList.add(robotBuilder.getYellowRobot());
            Random random = new Random();
            return robotsList.get(random.nextInt(robotsList.size()));
        }

        return null;
    }

    /**
     * play non-graphic method to play the game
     */
    public void play() {

        logger.info("start of game ");
        CaseType tokenCaseType = Token.getTokenCaseType();// to start the round we draw a token from the arraylistof tokens
        Robot currentRobot = currentRobot(tokenCaseType);
        currentRobot.injectCurrentGrid(this.plateau);
        Position targetPosition = getPlateau().searchPositionOf(tokenCaseType);
        logger.info("the current token is : " + tokenCaseType + "* position *" + targetPosition);
        logger.info("the current robot is  : " + currentRobot.getColor() + " *position * " + currentRobot.getPosition());
        List<Position> astarArray = currentRobot.aStar(currentRobot(tokenCaseType), tokenCaseType);
        logger.info("the path : " + astarArray);
        logger.info("final position of robot : " + currentRobot(tokenCaseType).getPosition());


        logger.info("end of game ");
    }

}
