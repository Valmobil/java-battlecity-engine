package com.codenjoy.dojo.battlecity.model;

import com.codenjoy.dojo.services.Direction;
import com.codenjoy.dojo.services.Point;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OneBullet {
    private Point currentPoint;
    private Direction direction;
    private Boolean mineShoot;
}
