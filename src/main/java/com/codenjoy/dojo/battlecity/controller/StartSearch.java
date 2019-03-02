package com.codenjoy.dojo.battlecity.controller;

import com.codenjoy.dojo.battlecity.client.Board;
import com.codenjoy.dojo.battlecity.model.BestPathV4;
import com.codenjoy.dojo.battlecity.model.Elements;
import com.codenjoy.dojo.services.Direction;
import com.codenjoy.dojo.services.Point;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListMap;

import static com.codenjoy.dojo.battlecity.controller.BFS0ListOfScenarios.startTheBestPathSearch;

public class StartSearch {



    public static String moveAndFire(Board board, int[] stepsToNextShoot, String[] lastDirection) {
        //If shoot is happend
        Point myTank = board.getMe();
        myTank.change(Direction.valueOf(lastDirection[0]));
        if (board.isAt(myTank, Elements.BULLET,Elements.BANG)) {
            stepsToNextShoot[0] = 3;
        }

        Direction newDirection = Direction.valueOf(startSearchNextStep(board));
        stepsToNextShoot[0]--;
        if (stepsToNextShoot[0] < 1 ) {
            //Fire
            if (fireAnalysis(board, newDirection) == 1) {
                //Please shoot
                return Arrays.toString(new String[]{newDirection.toString(), Direction.ACT.toString()});
            }
        }
        return newDirection.toString();
    }

    private static int fireAnalysis(Board board, Direction newDirection) {
        Point nextShot = board.getMe();
        while (!board.isBarrierAt(nextShot.getX(), nextShot.getY())) {
            if (board.isAt(nextShot, Elements.BULLET,
                    Elements.AI_TANK_LEFT, Elements.AI_TANK_RIGHT, Elements.AI_TANK_UP, Elements.AI_TANK_DOWN,
                    Elements.OTHER_TANK_LEFT, Elements.OTHER_TANK_RIGHT, Elements.OTHER_TANK_UP, Elements.OTHER_TANK_DOWN)) {
                return 1;
            }
            nextShot.setX(newDirection.changeX(nextShot.getX()));
            nextShot.setY(newDirection.changeY(nextShot.getY()));
        }
        return 0;
    }

    //    public static String StartAppV4(Board board, MySnakeV4 mySnake, SnakeListV4 othSnakes) {
    public static String startSearchNextStep(Board board) {
        //Time track for
        long startTime = System.nanoTime();

        //Update my Snake body
        Point myTank = board.getMe();
//        mySnake.addToHead(head, board.getMyBody().size(), board.getMyTail().get(0), board);
//        System.out.println("Snake=>StartAppV4: " + mySnake);

        //Build Competitive snakes
//        othSnakes.changeSnakes(board.getCompetitiveHead(), board.getCompetitiveBody().size(), board.getCompetitiveTails());
//        Log.printLog("OtherSnakes: " + othSnakes.toString(), 0);

        //Build fruitful paths
//        ConcurrentSkipListMap<Double, BestPathV4> bestPaths = new ConcurrentSkipListMap<>();
        ConcurrentSkipListMap<Double, BestPathV4> bestPaths = new ConcurrentSkipListMap<>();
        HashSet<String> targets = new HashSet<>();
//        startTheBestPathSearch(board, mySnake, othSnakes, bestPaths, targets, startTime);
        startTheBestPathSearch(board, myTank, bestPaths, targets, startTime);

        //Analyse list of built paths
        if (bestPaths.isEmpty()) {
            System.out.println("Snake=>StartAppV4: Best Path Not Found");
            return Direction.STOP.toString();
        }

//        Print paths for Debug purposes
//        bestPaths.forEach((key, value) -> Log.printLog("Key: " + key + "Path:" + value, 2));

//        Put snake by the best path
        List<Point> theBestPath = bestPaths.lastEntry().getValue().getPath();
        bestPaths.clear();
//        long endTime = System.nanoTime();
//        Log.printLog("Time lapse(ms): " + (endTime - startTime) / 1000000, 2);
        Point nextStep = theBestPath.get(theBestPath.size() > 1 ? 1 : 0);
//        checkSnakeFlyFuryStatus(board, mySnake, nextStep);
        return getDirectionToNextPoint(board.getMe(), nextStep);
//        return Direction.ACT.toString();
    }

    /**
     * Generate direction for one next step to point
     */
    static String getDirectionToNextPoint(Point fromPoint, Point toPoint) {
        if (fromPoint.getY() < toPoint.getY()) {
            return Direction.UP.toString();
        }
        if (fromPoint.getY() > toPoint.getY()) {
            return Direction.DOWN.toString();
        }
        if (fromPoint.getX() < toPoint.getX()) {
            return Direction.RIGHT.toString();
        }
        return Direction.LEFT.toString();
    }


}
