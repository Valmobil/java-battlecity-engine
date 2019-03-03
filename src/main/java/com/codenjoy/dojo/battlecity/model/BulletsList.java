package com.codenjoy.dojo.battlecity.model;

import com.codenjoy.dojo.battlecity.client.Board;
import com.codenjoy.dojo.services.Direction;
import com.codenjoy.dojo.services.Point;
import com.codenjoy.dojo.services.PointImpl;
import lombok.Data;

import java.util.*;

import static com.codenjoy.dojo.battlecity.controller.StartSearch.getDirectionToNextPoint;

@Data
public class BulletsList {
    private HashMap<Point, OneBullet> bulletList = new HashMap<>();
    private static int stepBul = 0;

    public void bulletListUpdate(Board board) {
        HashMap<Point, OneBullet> bulletListOld = new HashMap<>(this.bulletList);
        this.bulletList.clear();
        if (bulletListOld.size() > 3) {
            System.out.println();
        }
        for (Point bullet : board.getBullets()) {
            OneBullet newBullet = new OneBullet(bullet, null, false);

            this.bulletList.put(bullet, newBullet);
            List<Point> listToDelete = new LinkedList<>();
            //Check new bullets with old ones
            for (Map.Entry<Point, OneBullet> oldBullet : bulletListOld.entrySet()) {
                Point oldBulletPoint = new PointImpl(oldBullet.getKey());
                if (oldBullet.getValue().getDirection() != null) {
                    //Restore Direction who has direction
                    oldBulletPoint.change(oldBullet.getValue().getDirection());
                    oldBulletPoint.change(oldBullet.getValue().getDirection());
                    if (this.bulletList.containsKey(oldBulletPoint)) {
//                        OneBullet oneBullet = this.bulletList.get(oldBulletPoint);
                        newBullet.setDirection(oldBullet.getValue().getDirection());
                        newBullet.setMineShoot(oldBullet.getValue().getMineShoot());
                        listToDelete.add(oldBulletPoint);
                    }
                } else {
                    //Calculate Direction for Bullets with history
                    if (newBullet.getCurrentPoint().distance(oldBulletPoint) == 2) {
                        newBullet.setDirection(Direction.valueOf(getDirectionToNextPoint(oldBullet.getKey(), newBullet.getCurrentPoint())));
                        newBullet.setMineShoot(oldBullet.getValue().getMineShoot());
                    } else {
                        //If new bullet have no history, check if tank is near bullet
                        Point startPoint = newBullet.getCurrentPoint();
                        Point point = checkPointsArround(board, startPoint);
                        newBullet.setDirection(Direction.valueOf(getDirectionToNextPoint(oldBullet.getKey(), newBullet.getCurrentPoint())));
                        newBullet.setMineShoot(oldBullet.getValue().getMineShoot());
                    }
                }
            }
            //Delete already used bullets
            for (Point point : listToDelete) {
                bulletListOld.remove(point);
            }
        }
        System.out.println(this.bulletList);
    }

    private Point checkPointsArround(Board board, Point point) {
        List<Point> childs = new ArrayList<>();

        Point curPoint = point;

        Point nextPoint = new PointImpl(curPoint.getX() + 1, curPoint.getY());
        if( board.isAt(nextPoint,
                Elements.AI_TANK_UP,
                Elements.AI_TANK_DOWN,
                Elements.AI_TANK_LEFT,
                Elements.AI_TANK_RIGHT,
                Elements.OTHER_TANK_UP,
                Elements.OTHER_TANK_DOWN,
                Elements.OTHER_TANK_LEFT,
                Elements.OTHER_TANK_RIGHT)) {
         return nextPoint;
        }

        nextPoint = new PointImpl(curPoint.getX(), curPoint.getY() - 1);
        if( board.isAt(nextPoint,
                Elements.AI_TANK_UP,
                Elements.AI_TANK_DOWN,
                Elements.AI_TANK_LEFT,
                Elements.AI_TANK_RIGHT,
                Elements.OTHER_TANK_UP,
                Elements.OTHER_TANK_DOWN,
                Elements.OTHER_TANK_LEFT,
                Elements.OTHER_TANK_RIGHT)) {
            return nextPoint;
        }

        nextPoint = new PointImpl(curPoint.getX() - 1, curPoint.getY());
        if( board.isAt(nextPoint,
                Elements.AI_TANK_UP,
                Elements.AI_TANK_DOWN,
                Elements.AI_TANK_LEFT,
                Elements.AI_TANK_RIGHT,
                Elements.OTHER_TANK_UP,
                Elements.OTHER_TANK_DOWN,
                Elements.OTHER_TANK_LEFT,
                Elements.OTHER_TANK_RIGHT)) {
            return nextPoint;
        }

        nextPoint = new PointImpl(curPoint.getX(), curPoint.getY() + 1);
        if( board.isAt(nextPoint,
                Elements.AI_TANK_UP,
                Elements.AI_TANK_DOWN,
                Elements.AI_TANK_LEFT,
                Elements.AI_TANK_RIGHT,
                Elements.OTHER_TANK_UP,
                Elements.OTHER_TANK_DOWN,
                Elements.OTHER_TANK_LEFT,
                Elements.OTHER_TANK_RIGHT)) {
            return nextPoint;
        }
        return null;
    }
}

