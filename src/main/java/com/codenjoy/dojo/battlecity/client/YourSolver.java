package com.codenjoy.dojo.battlecity.client;

/*-
 * #%L
 * Codenjoy - it's a dojo-like platform from developers to developers.
 * %%
 * Copyright (C) 2018 Codenjoy
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-3.0.html>.
 * #L%
 */

import com.codenjoy.dojo.services.Direction;
import com.codenjoy.dojo.client.Solver;
import com.codenjoy.dojo.client.WebSocketRunner;
import com.codenjoy.dojo.services.Dice;
import com.codenjoy.dojo.services.RandomDice;

import java.util.Arrays;

/**
 * User: your name
 */
public class YourSolver implements Solver<Board> {

    private Dice dice;
    private Board board;

    public YourSolver(Dice dice) {
        this.dice = dice;
    }

    @Override
    public String get(Board board) {
        this.board = board;
        if (board.isGameOver()) return "";

        return myWay();
    }

    private String myWay() {
        if(!(board.isBarrierAt(board.getMe().getX(), board.getMe().getY()+1))){
            return Direction.UP.toString();
        }
        if(!(board.isBarrierAt(board.getMe().getX()+1, board.getMe().getY()))){
            return Direction.RIGHT.toString();
        }
        return Arrays.toString(new  String[]{Direction.LEFT.toString(), Direction.ACT().toString()}) ;
    }

    public static void main(String[] args) {
        WebSocketRunner.runClient(
                // paste here board page url from browser after registration
                "http://algoritmix.dan-it.kiev.ua/codenjoy-contest/board/player/n0vra0sbflkz0c5bz8o9?code=2671867589334741720",
                new YourSolver(new RandomDice()),
                new Board());
    }

}
