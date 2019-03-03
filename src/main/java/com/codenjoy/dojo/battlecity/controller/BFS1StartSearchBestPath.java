package com.codenjoy.dojo.battlecity.controller;

import com.codenjoy.dojo.battlecity.client.Board;
import com.codenjoy.dojo.battlecity.model.BestPathV4;
import com.codenjoy.dojo.services.Point;

import java.util.*;

import static com.codenjoy.dojo.battlecity.controller.BFS2StartRecurcivePart.recursiveBFSBest;

public class BFS1StartSearchBestPath {
//    static void startBSSBest(Board board, MySnakeV4 mySnake, LinkedList<Point> additionalPath, SnakeListV4 otherSnakes, Map<Double, BestPathV4> bestPaths, HashSet<String> targets, Integer mode, BestPathV4 childPath) {
    static void startBSSBest(Board board, Point myTank, LinkedList<Point> additionalPath, Map<Double, BestPathV4> bestPaths, HashSet<Point> targets, Integer mode, BestPathV4 childPath, HashSet<Point> obstacles) {
        // Mode can be:
        // - 0 way to any fruitful point
        // - 1 way directly to point (eating apple&gold and avoiding stones etc)
        // - 2 fury mode - go to othSnakesBodies avoiding any other points
        // - 3 look for fury or gold as direct target on the board

        Queue<Point> queue = new LinkedList<>();
        Queue<Integer> queueLevel = new LinkedList<>();
        Queue<LinkedList<Point>> queuePath = new LinkedList<>();
//        Queue<MySnakeV4> queueSnakes = new LinkedList<>();


//        Point mySnakeHead = mySnake.getHead();
        queue.add(myTank);
        queueLevel.add(0);
        if (additionalPath.size() > 0) {
            queuePath.add(additionalPath);
        } else {
            queuePath.add(new LinkedList<>(Arrays.asList(myTank)));
        }
//        queueSnakes.add(myTank);
        HashSet<Point> visited = new HashSet<>();

        //Start recursion
        recursiveBFSBest(board, visited, queue, queuePath, queueLevel, bestPaths, targets,  mode, childPath, obstacles);
    }
}
