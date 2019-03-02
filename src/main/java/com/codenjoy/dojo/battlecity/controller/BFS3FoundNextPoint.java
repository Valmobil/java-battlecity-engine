package com.codenjoy.dojo.battlecity.controller;

import com.codenjoy.dojo.battlecity.client.Board;
import com.codenjoy.dojo.battlecity.model.BestPathV4;
import com.codenjoy.dojo.battlecity.model.Elements;
import com.codenjoy.dojo.services.Point;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;

public class BFS3FoundNextPoint {
    /**
     * Check for fruitful point
     * //Return    0 - blank Point
     * //          1 - found eatable Point - nead break searching and save result
     * //          2 - found not eatable Point - forbid movement to it
     * //          4 - skip the point -> to next check
     */
    public static int snakeFoundTargetPoint(Board board, Point myTank, Map<Double, BestPathV4> bestPaths, LinkedList<Point> prevPath, HashSet<Point> targets, HashSet<Point> targetsCheck, Integer mode, HashSet<Point> visited, BestPathV4 childPath) {
        int checkResult;

        checkResult = isBarriers(board, myTank);
        if (checkResult != 4) {
            return checkResult;
        }

        int score = 50;
        checkResult = isTargetCheckFound(board, myTank, bestPaths, prevPath, targets, targetsCheck, visited, score, childPath, mode);
        if (checkResult != 4) {
            return checkResult;
        }

//        checkResult = isEnemyTank(board, myTank);
//        if (checkResult != 4) {
//            return checkResult;
//        }

//        if (mySnake.getSize() == 2) {
//        System.out.println();
//        }
//        checkResult = isFuryModeAvailable(board, mySnake, bestPaths, prevPath, otherSnakes, targets, targetsCheck, childPath, mode);
//        if (checkResult != 4) {
//            return checkResult;
//        }
//
//
//        checkResult = isPointWasAlreadyVisited(board, mySnake, bestPaths, prevPath, otherSnakes, targets, targetsCheck, childPath);
//        if (checkResult != 4) {
//            return checkResult;
//        }
//
//        checkResult = isOwnBodyFound(board, mySnake, bestPaths, prevPath, otherSnakes, targets, targetsCheck, mode);
//        if (checkResult != 4) {
//            return checkResult;
//        }
//        int score = 0;
//        checkResult = isOwnTailFound(board, mySnake, bestPaths, prevPath, otherSnakes, targets, score, visited, mode, childPath);
//        if (checkResult != 4) {
//            return checkResult;
//        }
//
//        score = 5;
//        checkResult = isStoneFound(board, mySnake, bestPaths, prevPath, otherSnakes, targets, score, mode, visited, childPath);
//        if (checkResult != 4) {
//            return checkResult;
//        }
//
//        score = 1;
//        checkResult = isAppleOrGoldFound(board, mySnake, bestPaths, prevPath, otherSnakes, targets, Elements.APPLE, mode, score, visited, childPath);
//        if (checkResult != 4) {
//            return checkResult;
//        }
//
//        score = 10;
//        checkResult = isAppleOrGoldFound(board, mySnake, bestPaths, prevPath, otherSnakes, targets, Elements.GOLD, mode, score, visited, childPath);
//        if (checkResult != 4) {
//            return checkResult;
//        }
//
//        if (mode == 2) {
//            //If fury mode
//            score = 20;
//        } else if (mode == 3) {
//            //for gold and fury (when we look forward on the board)
//            score = 10;
//        } else {
//            //If tail mode
//            score = 0;
//        }
//        checkResult = isTargetCheckFound(board, mySnake, bestPaths, prevPath, otherSnakes, targets, targetsCheck, visited, score, childPath, mode);
//        if (checkResult != 4) {
//            return checkResult;
//        }
//
//        score = 20;
//        checkResult = isOtherHeadFound(board, mySnake, bestPaths, prevPath, otherSnakes, targets, score, mode, visited, childPath);
//        if (checkResult != 4) {
//            return checkResult;
//        }
//
//        score = 0;
//        checkResult = isOtherBodyFound(board, mySnake, bestPaths, prevPath, otherSnakes, targets, score, mode, visited, childPath);
//        if (checkResult != 4) {
//            return checkResult;
//        }
//
//
//        score = 20;
//        checkResult = isFuryFound(board, mySnake, bestPaths, prevPath, otherSnakes, targets, score, mode, visited, childPath);
//        if (checkResult != 4) {
//            return checkResult;
//        }
//
//        score = 1;
//        checkResult = isFlyFound(board, mySnake, bestPaths, prevPath, otherSnakes, targets, score, mode, visited, childPath);
//        if (checkResult != 4) {
//            return checkResult;
//        }
//


        return 0;
    }

    private static int isEnemyTank(Board board, Point myTank) {
//        if (board.isAt(board.getEnemies())) {
//            return 2;
//        }
        return 4;
    }

    private static int isBarriers(Board board, Point myTank) {
        if (board.isBarrierAt(myTank.getX(),myTank.getY())) {
            return 2;
        }
        return 4;
    }

    /**
     * Snake found pre-defined target POSs
     */
    public static int isTargetCheckFound(Board board, Point myTank, Map<Double, BestPathV4> bestPaths
            , LinkedList<Point> prevPath, HashSet<Point> targets
            , HashSet<Point> targetsCheck, HashSet<Point> visited, int score, BestPathV4 childPath, Integer mode) {
//        if (prevPath.size() > 15 && mode == 3) {
//            Log.printLog("Snake=>Targeted element not found due to MAX Steps = 20. Head:" + mySnake.getHead() + " Targets: " + targets + " Targets Check: " + targetsCheck, 2);
//            return 1;
//        }
        if (targetsCheck.contains(myTank)) {
//            System.out.println("Utils=>isTargetCheckFound: Target point found!" + mySnake.getHead());
            boolean tailIsFound = false;
            if (mode == 1 || mode == 2) {
                tailIsFound = true;
            }
            bestPaths.put((double) score, new BestPathV4(score, prevPath, board, visited
                            , new HashSet()));
//            Log.printLog("Snake=>Targeted element found. Head:" + mySnake.getHead() + " Targets: " + targets + " Targets Check: " + targetsCheck, 2);
            return 1;
        }
        return 4;
    }
}
