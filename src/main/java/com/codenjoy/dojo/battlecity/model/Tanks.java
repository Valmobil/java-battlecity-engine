package com.codenjoy.dojo.battlecity.model;

import com.codenjoy.dojo.services.Point;

public class Tanks {

    public Tanks() {
    }

    private Point point;

    public Tanks(Point point) {
        this.point = point;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}
