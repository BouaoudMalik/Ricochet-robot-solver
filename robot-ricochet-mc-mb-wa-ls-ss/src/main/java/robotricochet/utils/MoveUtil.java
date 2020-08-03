package robotricochet.utils;

import robotricochet.entity.CaseType;
import robotricochet.entity.Color;
import robotricochet.entity.Direction;
import robotricochet.entity.Position;
import robotricochet.services.Plateau;

/**
 * class that refers to all the robot moves
 */
public class MoveUtil {
    /**
     *getPositionWhenMovedInDirection
     * @param position current position of the robot
     * @param direction the desirable direction
     * @param color  of the robot
     * @param plateau game grid
     * @return return the allowed position according to the given direction
     */
    public static Position getPositionWhenMovedInDirection(Position position, Direction direction, Color color, Plateau plateau) { //return the position of a robot placed on position position if we were to move it in the direction
        Position ghostRobotPosition = new Position(position.getX(), position.getY()); //clone of the robot position before he moves
        if (direction == Direction.UP) {
            return moveUp(color, ghostRobotPosition, plateau);
        }
        if (direction == Direction.DOWN) {
            return moveDown(color, ghostRobotPosition, plateau);
        }
        if (direction == Direction.LEFT) {
            return moveLeft(color, ghostRobotPosition, plateau);
        }
        if (direction == Direction.RIGHT) {
            return moveRight(color, ghostRobotPosition, plateau);
        }
        return null;
    }

    /**
     * moveRight
     * @param color color of the robot
     * @param ghostRobotPosition a temporary moving robot
     * @param plateau game grid
     * @return the final position allowed by moving to the right
     */
    public static Position moveRight(Color color, Position ghostRobotPosition, Plateau plateau) {
        ghostRobotPosition.setY(ghostRobotPosition.getY() + 1);
        while (!isObstacle(ghostRobotPosition, plateau,color)) {
            if (isRicochetForRobot(color, ghostRobotPosition, plateau)) {
                if (isSlash(ghostRobotPosition, plateau)) {
                    return getPositionWhenMovedInDirection(ghostRobotPosition, Direction.UP, color, plateau);
                } else {
                    return getPositionWhenMovedInDirection(ghostRobotPosition, Direction.DOWN, color, plateau);
                }
            }
            ghostRobotPosition.setY(ghostRobotPosition.getY() + 1);
        }
        ghostRobotPosition.setY(ghostRobotPosition.getY() - 1);
        return ghostRobotPosition;
    }
    /**
     * moveLeft
     * @param color color of the robot
     * @param ghostRobotPosition a temporary moving robot
     * @param plateau game grid
     * @return the final position allowed by moving to the left
     */
    public static Position moveLeft(Color color, Position ghostRobotPosition, Plateau plateau) {
        ghostRobotPosition.setY(ghostRobotPosition.getY() - 1);
        while (!isObstacle(ghostRobotPosition, plateau,color)) {
            if (isRicochetForRobot(color, ghostRobotPosition, plateau)) {
                if (isSlash(ghostRobotPosition, plateau)) {
                    return getPositionWhenMovedInDirection(ghostRobotPosition, Direction.DOWN, color, plateau);
                } else {
                    return getPositionWhenMovedInDirection(ghostRobotPosition, Direction.UP, color, plateau);
                }
            }
            ghostRobotPosition.setY(ghostRobotPosition.getY() - 1);
        }
        ghostRobotPosition.setY(ghostRobotPosition.getY() + 1);
        return ghostRobotPosition;
    }
    /**
     * moveDown
     * @param color color of the robot
     * @param ghostRobotPosition a temporary moving robot
     * @param plateau game grid
     * @return the final position allowed by moving Down
     */
    public static Position moveDown(Color color, Position ghostRobotPosition, Plateau plateau) {
        ghostRobotPosition.setX(ghostRobotPosition.getX() + 1);
        while (!isObstacle(ghostRobotPosition, plateau,color)) {
            if (isRicochetForRobot(color, ghostRobotPosition, plateau)) {
                if (isSlash(ghostRobotPosition, plateau)) {
                    return getPositionWhenMovedInDirection(ghostRobotPosition, Direction.LEFT, color, plateau);
                } else {
                    return getPositionWhenMovedInDirection(ghostRobotPosition, Direction.RIGHT, color, plateau);
                }
            }
            ghostRobotPosition.setX(ghostRobotPosition.getX() + 1);
        }
        ghostRobotPosition.setX(ghostRobotPosition.getX() - 1);
        return ghostRobotPosition;
    }
    /**
     * moveUp
     * @param color color of the robot
     * @param ghostRobotPosition a temporary moving robot
     * @param plateau game grid
     * @return the final position allowed by moving up
     */
    public static Position moveUp(Color color, Position ghostRobotPosition, Plateau plateau) {
        ghostRobotPosition.setX(ghostRobotPosition.getX() - 1);
        while (!isObstacle(ghostRobotPosition, plateau,color)) {

            if (isRicochetForRobot(color, ghostRobotPosition, plateau)) {

                if (isSlash(ghostRobotPosition, plateau)) {
                    return getPositionWhenMovedInDirection(ghostRobotPosition, Direction.RIGHT, color, plateau);
                } else {
                    return getPositionWhenMovedInDirection(ghostRobotPosition, Direction.LEFT, color, plateau);
                }
            }
            ghostRobotPosition.setX(ghostRobotPosition.getX() - 1);
        }
        ghostRobotPosition.setX(ghostRobotPosition.getX() + 1);
        return ghostRobotPosition;
    }

    /**
     *isObstacle return if it's an obstacle or not an obstacle is also another robot 
     * @param position of the robot
     * @param plateau the grid game
     * @param color the color of the current robot
     * @return true if its an obstacle,false if not
     */
    public static boolean isObstacle(Position position, Plateau plateau,Color color ) {
        boolean caseColor=true;
        switch (color){
            case RED:
                if(plateau.getPlateau()[position.getX()][position.getY()].getCaseType() ==CaseType.RED_ROBOT_START ) caseColor=false;
                break;
            case BLUE:
                if (plateau.getPlateau()[position.getX()][position.getY()].getCaseType() ==CaseType.BLUE_ROBOT_START) caseColor=false;
                break;
            case GREEN:
                if(plateau.getPlateau()[position.getX()][position.getY()].getCaseType() ==CaseType.GREEN_ROBOT_START) caseColor=false;
                break;
            case YELLOW:
                if(plateau.getPlateau()[position.getX()][position.getY()].getCaseType() ==CaseType.YELLOW_ROBOT_START) caseColor=false;
                break;

            default:
                caseColor=true;
        }
        return ((plateau.getPlateau()[position.getX()][position.getY()].getCaseType() == CaseType.OBSTACLE ||
                ((plateau.getPlateau()[position.getX()][position.getY()].getCaseType() ==CaseType.RED_ROBOT_START )&&(caseColor))||
                ((plateau.getPlateau()[position.getX()][position.getY()].getCaseType() ==CaseType.BLUE_ROBOT_START )&&(caseColor))||
                ((plateau.getPlateau()[position.getX()][position.getY()].getCaseType() ==CaseType.GREEN_ROBOT_START )&&(caseColor))||
                ((plateau.getPlateau()[position.getX()][position.getY()].getCaseType() ==CaseType.YELLOW_ROBOT_START )&&(caseColor))));

    }

    /**isRicochetForRobot
     * determine if it's a ricochet for a robot
     * @param color color of the robot
     * @param position of the robot
     * @param plateau game grid
     * @return true if it's a ricochet for the robot(referenced by the color given in parameter)
     */
    public static boolean isRicochetForRobot(Color color, Position position, Plateau plateau) {
        CaseType caseType = plateau.getPlateau()[position.getX()][position.getY()].getCaseType();
        if (color == Color.BLUE && (caseType == CaseType.SLASH_BLUE || caseType == CaseType.ANTISLASH_BLUE)) {
            return true;
        }
        if (color == Color.GREEN && (caseType == CaseType.SLASH_GREEN || caseType == CaseType.ANTISLASH_GREEN)) {
            return true;
        }
        if (color == Color.RED && (caseType == CaseType.SLASH_RED || caseType == CaseType.ANTISLASH_RED)) {
            return true;
        }
        if (color == Color.YELLOW && (caseType == CaseType.SLASH_YELLOW || caseType == CaseType.ANTISLASH_YELLOW)) {
            return true;
        }
        return false;
    }

    /**
     * isSlash
     * @param position current position on the grid
     * @param plateau game grid
     * @return true if it's a ricochet type slash /,false if not
     */
    public static boolean isSlash(Position position, Plateau plateau) {
        CaseType caseType = plateau.getPlateau()[position.getX()][position.getY()].getCaseType();
        return (caseType == CaseType.SLASH_BLUE
                || caseType == CaseType.SLASH_GREEN
                || caseType == CaseType.SLASH_RED
                || caseType == CaseType.SLASH_YELLOW);
    }


}
