package robotricochet.services;

import robotricochet.entity.Case;
import robotricochet.entity.Color;
import robotricochet.entity.Position;
import robotricochet.entity.Robot;

/**
 * create the robots ,and contains all necessary methods to use them
 */
public class RobotBuilder {


    private Robot redRobot;
    private Robot greenRobot;
    private Robot blueRobot;
    private Robot yellowRobot;

    /**
     * constructor of the class ,initialise robots at position=(1,1)
     */
    public RobotBuilder() {
        this.greenRobot = new Robot(Color.GREEN, new Position(1, 1));
        this.redRobot = new Robot(Color.RED, new Position(1, 1));
        this.blueRobot = new Robot(Color.BLUE, new Position(1, 1));
        this.yellowRobot = new Robot(Color.YELLOW, new Position(1, 1));

    }

    /**
     *place all the robots on their own starting case
     * @param grid the current grid of the game
     */
    public void placeRobotOnStartingCases(Case[][] grid) {
        for (int i = 0; i < 18; i++) {
            for (int j = 0; j < 18; j++) {
                switch (grid[i][j].getCaseType()) {
                    case GREEN_ROBOT_START:
                        this.greenRobot = new Robot(Color.GREEN, new Position(i, j));
                        break;
                    case BLUE_ROBOT_START:
                        this.blueRobot = new Robot(Color.BLUE, new Position(i, j));
                        break;
                    case YELLOW_ROBOT_START:
                        this.yellowRobot = new Robot(Color.YELLOW, new Position(i, j));
                        break;
                    case RED_ROBOT_START:
                        this.redRobot = new Robot(Color.RED, new Position(i, j));
                        break;
                    default:
                        break;
                }
            }
        }
    }

    /**
     * getter of the blue robot
     * @return the blue robot
     */
    public Robot getBlueRobot() {
        return blueRobot;
    }
    /**
     * getter of the green robot
     * @return the green robot
     */
    public Robot getGreenRobot() {
        return greenRobot;
    }
    /**
     * getter of the red robot
     * @return the red robot
     */
    public Robot getRedRobot() {
        return redRobot;
    }
    /**
     * getter of the green robot
     * @return the green robot
     */
    public Robot getYellowRobot() {
        return yellowRobot;
    }

    ;

}
