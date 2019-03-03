package com.codenjoy.dojo.battlecity.controller;

import com.codenjoy.dojo.battlecity.client.Board;
import com.codenjoy.dojo.battlecity.model.BestPathV4;
import com.codenjoy.dojo.battlecity.model.Elements;
import com.codenjoy.dojo.services.Direction;
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.PointImpl;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentSkipListMap;

import static com.codenjoy.dojo.battlecity.controller.BFS1StartSearchBestPath.startBSSBest;

public class BFS0ListOfScenarios {
    /**
     * Launcher for paths search
     */
//    private static void startTheBestPathSearch(Board board, MySnakeV4 mySnake, SnakeListV4 othSnakes, ConcurrentSkipListMap<Double, BestPathV4> bestPaths, HashSet<String> targets, long startTime) {
    public static void startTheBestPathSearch(Board board, Point myTank, ConcurrentSkipListMap<Double, BestPathV4> bestPaths, HashSet<Point> targets, HashSet<Point> obstacles, long startTime) {

        // Targets collection can contains further commands for moving objects
        // - mySnakeBitTail - bit my snake tail
        // - mySnakeTail - look for all except own tail

        // Max timeout
        int mazTimeout = 250;

        //Find nearest tank (AI or Competitive)
        // Step 1
        targets.clear();
        fillTargetsAndObstacles(board, targets, obstacles);
        startBSSBest(board, myTank, new LinkedList<Point>(), bestPaths, targets, 0, null, obstacles);

        // Step 2
//        Pointboard.getBullets()

    }

    private static void fillTargetsAndObstacles(Board board, HashSet<Point> targets, HashSet<Point> obstacles) {
        for (Point othTank : board.getEnemies()) {
            targets.add(othTank);

            Point nextTankStep = othTank;
            for (int i = 0; i < 2; i++) {
                nextTankStep = new PointImpl(nextTankStep);
                if (board.isAt(othTank, Elements.OTHER_TANK_DOWN, Elements.AI_TANK_DOWN)) {
                    nextTankStep.change(Direction.DOWN);
                    obstacles.add(nextTankStep);
                }
                if (board.isAt(othTank, Elements.OTHER_TANK_UP, Elements.AI_TANK_UP)) {
                    nextTankStep.change(Direction.UP);
                    obstacles.add(nextTankStep);
                }
                if (board.isAt(othTank, Elements.OTHER_TANK_RIGHT, Elements.AI_TANK_RIGHT)) {
                    nextTankStep.change(Direction.RIGHT);
                    obstacles.add(nextTankStep);
                }
                if (board.isAt(othTank, Elements.OTHER_TANK_LEFT, Elements.AI_TANK_LEFT)) {
                    nextTankStep.change(Direction.LEFT);
                    obstacles.add(nextTankStep);
                }
            }
        }
    }



// Step 1
//        Log.printLog("**Step 1 - follow own tail**", 3);
//        if (targets.size() == 0) {
//            targets.add(mySnake.getTail().toString());
//        }
//        startBSSBest(board, mySnake, new LinkedList<>(), othSnakes, bestPaths, targets, 1, null);
//
//        // Step 2
//        Log.printLog("**Step 2 - if tail is not available - eat own body**", 3);
//        long endTime = System.nanoTime();
//        Log.printLog("Time: " + (endTime - startTime) / 1000000, 2);
//        if (bestPaths.size() == 0) {
//            targets.clear();
//            for (Point point : mySnake.getBody()) {
//                if (point != null) {
//                    targets.add(point.toString());
//                }
//            }
//            startBSSBest(board, mySnake, new LinkedList<>(), othSnakes, bestPaths, targets, 2, null);
//        }
//
// Step 3
//        Log.printLog("**Step 3 - if active fury mode only - find nearest other snake**", 3);
//        endTime = System.nanoTime();
////        Log.printLog("Time: " + (endTime - startTime) / 1000000, 2);
//        if (mySnake.getFury() > 0 && mySnake.getFly() == 0) {
//            targets.clear();
//            for (MySnakeV4 othSnake : othSnakes.getSnakes()) {
//                targets.add(othSnake.getHead().toString());
//                targets.add(othSnake.getTail().toString());
//                for (Point point : othSnake.getBody()) {
//                    targets.add(point.toString());
//                }
//            }
//            startBSSBest(board, mySnake, new LinkedList<>(), othSnakes, bestPaths, targets, 2, null);
//        }
//
//        // Step 4
//        Log.printLog("**Step 4 - find first fruitful point**", 3);
//        endTime = System.nanoTime();
//        Log.printLog("Time: " + (endTime - startTime) / 1000000, 2);
//        //calculate the shortest path to Fruitful points
//        targets.clear();
//        startBSSBest(board, mySnake, new LinkedList<>(), othSnakes, bestPaths, targets, 0, null);
//
//        // Step 5
//        endTime = System.nanoTime();
//        if ((endTime - startTime) / 1000000 > mazTimeout) {
//            Log.printLog("!!!Time out!!! > 600 - ignore Gold", 2);
//        } else {
//            Log.printLog("**Step 5 - Look for Gold**", 3);
//            Log.printLog("Time: " + (endTime - startTime) / 1000000, 2);
//            targets.clear();
//            for (Point point : board.getMyGold()) {
//                targets.add(point.toString());
//            }
//            if (targets.size() > 0) {
//                startBSSBest(board, mySnake, new LinkedList<>(), othSnakes, bestPaths, targets, 3, null);
//            }
//        }
//
//        // Step 6
//        Log.printLog("**Step 6 - check if after apple the tail is reachable", 3);
//        endTime = System.nanoTime();
//        Log.printLog("Time: " + (endTime - startTime) / 1000000, 2);
//        Set<Double> mapKeys = bestPaths.keySet();
//        for (Double key : mapKeys) {
//            BestPathV4 myEntry = bestPaths.get(key);
//            if (!myEntry.isTailIsReachable()) {
//                targets.clear();
//                Board boardNextStep = myEntry.getBoard();
//                MySnakeV4 snakeNextStep = myEntry.getSnake();
//                LinkedList<Point> pathNextStep = myEntry.getPath();
//                SnakeListV4 otherSnakesNextStep = myEntry.getOtherSnakes();
//                HashSet<String> targetsNextStep = new HashSet<>();
//                targetsNextStep.add(snakeNextStep.getTail().toString());
//                myEntry.setAlreadyUsed(true);
//                startBSSBest(boardNextStep, snakeNextStep, pathNextStep, otherSnakesNextStep, bestPaths, targetsNextStep, 1, myEntry);
//            }
//        }
//
//        // Step 7
//        endTime = System.nanoTime();
//        if ((endTime - startTime) / 1000000 > mazTimeout) {
//            Log.printLog("!!!Time out!!! >600 - Ignore Fury", 2);
//        } else {
//            Log.printLog("**Step 7 - look for Fury**", 3);
//            Log.printLog("Time: " + (endTime - startTime) / 1000000, 2);
//            targets.clear();
//            for (Point point : board.getMyFury()) {
//                targets.add(point.toString());
//            }
//            if (targets.size() > 0) {
//                startBSSBest(board, mySnake, new LinkedList<>(), othSnakes, bestPaths, targets, 3, null);
//            }
//        }
//
//        // Step 8
//        Log.printLog("**Step 8 - check if snakes with fury is available, check if other snakes reachable**", 3);
//        endTime = System.nanoTime();
//        Log.printLog("Time: " + (endTime - startTime) / 1000000, 2);
//        if ((endTime - startTime) / 1000000 > mazTimeout) {
//            Log.printLog("!!!Time out!!! >600 - Ignore Fury", 2);
//        } else {
//            mapKeys = bestPaths.keySet();
//            for (Double key : mapKeys) {
//                BestPathV4 myEntry = bestPaths.get(key);
//                if (!myEntry.isTailIsReachable()) {
//                    targets.clear();
//                    Board boardNextStep = myEntry.getBoard();
//                    MySnakeV4 snakeNextStep = myEntry.getSnake();
//                    LinkedList<Point> pathNextStep = myEntry.getPath();
//                    SnakeListV4 otherSnakesNextStep = myEntry.getOtherSnakes();
//                    HashSet<String> targetsNextStep = new HashSet<>();
//                    targetsNextStep.add(snakeNextStep.getTail().toString());
//                    myEntry.setAlreadyUsed(true);
//                    startBSSBest(boardNextStep, snakeNextStep, pathNextStep, otherSnakesNextStep,
//                            bestPaths, targetsNextStep, 1, myEntry);
//                }
//            }
//        }
//    }

        }
