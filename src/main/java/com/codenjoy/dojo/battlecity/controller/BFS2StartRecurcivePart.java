package com.codenjoy.dojo.battlecity.controller;

import com.codenjoy.dojo.battlecity.client.Board;
import com.codenjoy.dojo.battlecity.model.BestPathV4;
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.PointImpl;

import java.util.logging.Logger;

import java.util.*;

import static com.codenjoy.dojo.battlecity.client.YourSolver.logger;
import static com.codenjoy.dojo.battlecity.controller.BFS3FoundNextPoint.snakeFoundTargetPoint;

public class BFS2StartRecurcivePart {
    /**
     * Body ot BFS Recursion
     */
    public static void recursiveBFSBest(Board board, HashSet<Point> visited, Queue<Point> queue, Queue<LinkedList<Point>> queuePath, Queue<Integer> queueLevel, Map<Double, BestPathV4> bestPaths, HashSet<Point> targets, Integer mode, BestPathV4 childPath, HashSet<Point> obstacles) {

        if (queue.isEmpty()) {
            return;
        }

        //For debug purposes
//        printLog(board, queueSnakes, visited, queue, queuePath, queueLevel, otherSnakes, bestPaths, targets, mode, 1);

        LinkedList<Point> prevPath = queuePath.remove();
//        Point curNode = queue.remove();
        Integer curLevel = queueLevel.remove();

        //Change snake position
        Point myTank = queue.remove();
//        Point newMySnake = new PointImpl(myTank);
//        newMySnake.setNextStep(myTank);

        //Change tail position after snake recalculation
//        HashSet<Point> targetsDirection = new HashSet<>();
//        HashSet<Point> targetsCheck = new HashSet<>();
//        if (targets.size() > 0) {
//            replaceSpecialStringsWithPoints(targets, targetsDirection, targetsCheck);
//        }
        boolean skipPoint = false;
//        if (mySnake.getTail().getX() == 0) {
//            System.out.println();
//        }

        int result = snakeFoundTargetPoint(board, myTank, bestPaths, prevPath,
                targets, obstacles, mode, visited, childPath);
//        Log.printLog("Result: " + result, 0);

        if (result != 0) {
            if (result == 1) {
                //end search and save result
//                logger.info("~~~~~");
//                logger.info("recursiveBFSBest=> BestPathes: " + bestPaths.toString());
                queue.clear();
                return;
            } else {
                skipPoint = true;
                //we found obstacle, so we should reject this step
            }
        }
        if (!skipPoint) {
            //check child node
//            Log.printLog("recursiveBFSBest=> Child nodes for queue: " + getEmptyChild(board, newMySnake, visited), 1);
            for (Point child : getEmptyChild(board, myTank, visited)) {
                queue.add(child);
                LinkedList<Point> newPath = new LinkedList<>(prevPath);
                newPath.add(child);
                queuePath.add(newPath);
                queueLevel.add(curLevel + 1);
//                queueSnakes.add(newMySnake);
                visited.add(child);
            }
        }
        recursiveBFSBest(board, visited, queue, queuePath, queueLevel, bestPaths, targets, mode, childPath, obstacles);
    }


    private static List<Point> getEmptyChild(Board board, Point myTank, HashSet<Point> visited) {
        List<Point> childs = new ArrayList<>();

        Point curPoint = myTank;

        Point nextPoint = new PointImpl(curPoint.getX() + 1, curPoint.getY());
        getEmptyChildAddNextPoint(board, visited, childs, nextPoint);

        nextPoint = new PointImpl(curPoint.getX(), curPoint.getY() - 1);
        getEmptyChildAddNextPoint(board, visited, childs, nextPoint);

        nextPoint = new PointImpl(curPoint.getX() - 1, curPoint.getY());
        getEmptyChildAddNextPoint(board, visited, childs, nextPoint);

        nextPoint = new PointImpl(curPoint.getX(), curPoint.getY() + 1);
        getEmptyChildAddNextPoint(board, visited, childs, nextPoint);

        return childs;
    }

    private static void getEmptyChildAddNextPoint(Board board, HashSet<Point> visited, List<Point> childs, Point nextPoint) {
        if (!visited.contains(nextPoint)) {
            childs.add(nextPoint);
        }
    }


    private static void replaceSpecialStringsWithPoints(HashSet<String> targets, HashSet<Point> targetsDirection, HashSet<Point> targetsCheck) {
        targetsDirection.clear();
        targetsCheck.clear();
        for (String str : targets) {
            Point p = stringToPoint(str);
            targetsDirection.add(p);
            targetsCheck.add(p);
        }
    }

    private static Point stringToPoint(String str) {
        //Valid string (str) example
        int x = Integer.parseInt(str.substring(1, str.indexOf(",")));
        int y = Integer.parseInt(str.substring(str.indexOf(",") + 1, str.indexOf("]")));
        return new PointImpl(x, y);
    }
}
