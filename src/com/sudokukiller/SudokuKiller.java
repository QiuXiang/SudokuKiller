package com.sudokukiller;

import java.util.ArrayList;
import java.util.List;

public class SudokuKiller {
  public static int[][] kill(int[][] sudokTable) {
    kill(sudokTable, 0, 0);
    return sudokTable;
  }

  public static boolean kill(int[][] sudokuTable, int y, int x) {
    for(; y < 9; y++) {
      for(; x < 9; x++) {
        if(sudokuTable[y][x] == 0) {
          for(int n = 1; n <= 9; n++) {
            if(!getUsedNumbers(sudokuTable, y, x).contains(n)) {
              sudokuTable[y][x] = n;
              if (kill(sudokuTable, y, x + 1))
                return true;
            }
          }
          sudokuTable[y][x] = 0;
          return false;
        }
      }
      x = 0;
    }
    return true;
  }

  public static List<Integer> getUsedNumbers(int[][] sudokuTable, int y, int x) {
    List<Integer> list = new ArrayList<Integer>();

    for (int i = 0; i < 9; i++) {
      int number = sudokuTable[y][i];
      if (number != 0) {
        list.add(number);
      }

      number = sudokuTable[i][x];
      if (number != 0 && !list.contains(number)) {
        list.add(number);
      }
    }

    int m = y / 3 * 3;
    int n = x / 3 * 3;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        int number = sudokuTable[m + i][n + j];
        if (number != 0 && !list.contains(number)) {
          list.add(number);
        }
      }
    }

    return list;
  }
}
