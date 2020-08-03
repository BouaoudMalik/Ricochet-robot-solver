package robotricochet.entity;

import robotricochet.services.Plateau;
import robotricochet.utils.MoveUtil;

import java.util.*;

import static java.lang.StrictMath.abs;

/**
 * class to define rebot and his path
 */
public class Robot {


    private Color color;
    private Position position;
    private Map<Position, Position> cameFrom = new HashMap<>();
    private Map<Position, Integer> currentTostartCost = new HashMap<>();//gScore
    private Map<Position, Integer> cheapestCost = new HashMap<>();//fScore
    private Plateau plateau;

    /**
     *class constructor
     * @param color robot color
     * @param position robot position
     */
    public Robot(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    /**
     * @return liste of position wehere robot can move
     */
    public List<Position> getPossibleMoves() {
        List<Position> positions = new ArrayList<>();
        positions.add(MoveUtil.getPositionWhenMovedInDirection(getPosition(), Direction.UP, getColor(), plateau));
        positions.add(MoveUtil.getPositionWhenMovedInDirection(getPosition(), Direction.DOWN, getColor(), plateau));
        positions.add(MoveUtil.getPositionWhenMovedInDirection(getPosition(), Direction.LEFT, getColor(), plateau));
        positions.add(MoveUtil.getPositionWhenMovedInDirection(getPosition(), Direction.RIGHT, getColor(), plateau));
        return positions;
    }

    /**
     * getter of position of robot
     * @return position of robot
     */
    public Position getPosition() {
        return this.position;
    }

    /**
     * setter of robot position
     * @param position new robot position
     */
    public void setPosition(Position position) {
        this.position.setX(position.getX());
        this.position.setY(position.getY());
    }

    /**
     * setter of robot position
     * @param x line index
     * @param y column index
     */
    public void setPosition(int x, int y) {
        this.position.setX(x);
        this.position.setY(y);
    }

    /**
     * getter of robot color
     * @return robot color
     */
    public Color getColor() {
        return this.color;
    }

    /**
     * robot path finding A star
     * @param robot current robot
     * @param token current token
     * @return the path of robot  as liste of position
     */
    public List<Position> aStar(Robot robot, CaseType token) {

        final Position startPosition = new Position(robot.getPosition().getX(), robot.getPosition().getY());
        final Position targetPosition = plateau.searchPositionOf(token);
        int tentativeGscore;
        Comparator<Position> comparator = getPositionComparator(cheapestCost);

        PriorityQueue<Position> openQueuePositions = new PriorityQueue<>(1, comparator);
        Set<Position> openSetPositions = new HashSet<>();


        openQueuePositions.add(startPosition);
        openSetPositions.add(startPosition);

        currentTostartCost.put(startPosition, 0);
        cheapestCost.put(startPosition, countDistance(startPosition, targetPosition));

        while (!openQueuePositions.isEmpty()) {
            final Position current = openQueuePositions.poll();
            openSetPositions.remove(current);
            currentTostartCost.put(current, countDistance(startPosition, current));

            List<Position> finalSmallestPath = finalPositionIsReached(robot, targetPosition, current);
            if (!finalSmallestPath.isEmpty()) return finalSmallestPath;

            robot.setPosition(current);

            List<Position> possibleMoves = getPossibleMoves();
            for (Position nextMove : possibleMoves) {
                tentativeGscore = currentTostartCost.getOrDefault(current, Integer.MAX_VALUE) + 15;
                if (tentativeGscore < currentTostartCost.getOrDefault(nextMove, Integer.MAX_VALUE)) {
                    cameFrom.put(nextMove, current);
                    cheapestCost.put(nextMove, tentativeGscore + countDistance(nextMove, targetPosition));
                    if (!openSetPositions.contains(nextMove)) {
                        openQueuePositions.add(nextMove);
                        openSetPositions.add(nextMove);
                    }
                }
            }
        }
        return Collections.emptyList();

    }

    /**
     *return to the final path when the robot reaches the goal and update its position
     * @param robot current robot
     * @param goal  token goal
     * @param current robot current position
     * @return return total path
     */
    private List<Position> finalPositionIsReached(Robot robot, Position goal, Position current) {
        ArrayList<Position> aStarArray;
        if ((current.getX() == goal.getX()) && (current.getY() == goal.getY())) {

            aStarArray = reconstructPath(cameFrom, current);
            robot.setPosition(aStarArray.get(aStarArray.size() - 1));
            return aStarArray;
        }
        return Collections.emptyList();
    }

    /**
     * calculate manhattan distance between two Positions
     * @param startPosition robot start position
     * @param nextPosition robot next position
     * @return manhattan distance
     */
    public int countDistance(Position startPosition, Position nextPosition) {
        int x = nextPosition.getX() - startPosition.getX();
        int y = nextPosition.getY() - startPosition.getY();
        return abs(x) + abs(y);
    }

    /**
     *used when instantiating the priorityQueue , it compares thr fscores of two positions
     * @param cheapestCost  map whose key is position and value is its fscore
     * @return comparator
     */
    private Comparator<Position> getPositionComparator(Map<Position, Integer> cheapestCost) {
        return (Position p1, Position p2) -> {
            int cheapestCostPosition1 = cheapestCost.get(p1);
            int cheapestCostPosition2 = cheapestCost.get(p2);

            return Integer.compare(cheapestCostPosition1, cheapestCostPosition2);

        };
    }

    /**
     * construct robot path
     * @param cameFrom represent map with a next next position key and previous position value
     * @param current current position
     * @return return path containing the current position
     */
    public ArrayList<Position> reconstructPath(Map<Position, Position> cameFrom, Position current) {
        ArrayList<Position> totalPath = new ArrayList<>();
        totalPath.add(0, current);
        while (cameFrom.containsKey(current)) {
            current = cameFrom.get(current);
            totalPath.add(0, current);
        }

        return totalPath;

    }

    /**
     * initialise the current grid
     * @param plateau the current grid
     */
    public void injectCurrentGrid(Plateau plateau) {
        this.plateau = plateau;
    }
}