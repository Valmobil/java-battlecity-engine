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

import com.codenjoy.dojo.client.Solver;
import com.codenjoy.dojo.client.WebSocketRunner;
import com.codenjoy.dojo.services.Dice;
import com.codenjoy.dojo.services.Direction;
import com.codenjoy.dojo.services.RandomDice;

import java.util.Arrays;
import java.util.logging.Logger;

import static com.codenjoy.dojo.battlecity.controller.StartSearch.StartSearchV4;

/**
 * User: your name
 */
public class YourSolver implements Solver<Board> {

    private Dice dice;
    private Board board;

    public YourSolver(Dice dice) {
        this.dice = dice;
    }
    public static final Logger logger = Logger.getLogger(YourSolver.class.getName());

    @Override
    public String get(Board board) {
        this.board = board;
        if (board.isGameOver()) return "";
//        return Direction.ACT.toString();
        String step = StartSearchV4(board);
        return Arrays.toString(new String[] {step, Direction.ACT.toString()});
    }

    public static void main(String[] args) {
        WebSocketRunner.runClient(
                // paste here board page url from browser after registration
//                "http://algoritmix.dan-it.kiev.ua/codenjoy-contest/board/player/n0vra0sbflkz0c5bz8o9?code=2671867589334741720",
                "http://algoritmix.dan-it.kiev.ua/codenjoy-contest/board/player/65hflip8z6g37khy31sh?code=4981973124907114953",
                new YourSolver(new RandomDice()),
                new Board());
    }

}
