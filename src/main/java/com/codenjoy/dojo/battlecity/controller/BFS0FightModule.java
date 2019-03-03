package com.codenjoy.dojo.battlecity.controller;

import com.codenjoy.dojo.battlecity.client.Board;
import com.codenjoy.dojo.battlecity.model.BulletsList;
import com.codenjoy.dojo.battlecity.model.Elements;
import com.codenjoy.dojo.services.Direction;
import com.codenjoy.dojo.services.Point;

public class BFS0FightModule {


    //If in front of tank has other tank of bullet - fight
    public static int fireAnalysis(Board board, Direction newDirection, BulletsList bulletsList, String[] lastDirection) {
        Point nextShot = board.getMe();
        if (newDirection.value() != 5) {
            while (!board.isBarrierAt(nextShot.getX(), nextShot.getY())) {
                if (board.isAt(nextShot,
                        Elements.AI_TANK_LEFT, Elements.AI_TANK_RIGHT, Elements.AI_TANK_UP, Elements.AI_TANK_DOWN,
                        Elements.OTHER_TANK_LEFT, Elements.OTHER_TANK_RIGHT, Elements.OTHER_TANK_UP, Elements.OTHER_TANK_DOWN)) {
                    return 1;
                }
                if (bulletsList.getBulletList().containsKey(nextShot)) {
                    try {
                        if (bulletsList.getBulletList().get(nextShot).getDirection().inverted().toString() == lastDirection[0]) {
                            return 1;
                        }
                    } catch (NullPointerException en) {};
                }
                nextShot.setX(newDirection.changeX(nextShot.getX()));
                nextShot.setY(newDirection.changeY(nextShot.getY()));
            }
        }
        return 0;
    }
}
