package com.sudokukiller;

import java.util.ArrayList;
import java.util.Arrays;

public class Sudoku {
  public static final ArrayList<Integer> NUMBERS = new ArrayList<Integer>(
    Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9}));
  public static final int SIZE = 81;
  public static final int EMPTY = 0;
  public int[] items = new int[SIZE];

  public Sudoku() {
    clear();
  }

  public void clear() {
    for (int i = 0; i < SIZE; i++) {
      items[i] = 0;
    }
  }

  public int getItem(int row, int col) {
    return items[toIndex(row, col)];
  }

  public void setItem(int index, int item) {
    items[index] = item;
  }

  public void setItem(int row, int col, int item) {
    items[toIndex(row, col)] = item;
  }

  public ArrayList<Integer> getUnuseds(int index) {
    return getUnuseds(toRow(index), toCol(index));
  }

  public ArrayList<Integer> getUnuseds(int row, int col) {
    ArrayList<Integer> unuseds = new ArrayList<Integer>(NUMBERS);

    for (int i = 0; i < 9; i++) {
      unuseds.remove((Integer) getItem(row, i));
      unuseds.remove((Integer) getItem(i, col));
    }

    int row_offset = row / 3 * 3;
    int col_offset = col / 3 * 3;
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        unuseds.remove((Integer) getItem(row_offset + i, col_offset + j));
      }
    }

    return unuseds;
  }

  public boolean isEmpty(int index) {
    return items[index] == EMPTY;
  }

  public boolean isEmpty(int row, int col) {
    return isEmpty(toIndex(row, col));
  }

  public boolean fill() {
    return fill(0, 0);
  }

  private boolean fill(int row, int col) {
    for(; row < 9; row++) {
      for(; col < 9; col++) {
        if(isEmpty(row, col)) {
          for (int item : getUnuseds(row, col)) {
            setItem(row, col, item);
            if (fill(row, col + 1)) {
              return true;
            }
          }
          setItem(row, col, EMPTY);
          return false;
        }
      }
      col = 0;
    }
    return true;
  }

  public static int toRow(int index) {
    return index / 9;
  }

  public static int toCol(int index) {
    return index % 9;
  }

  public static int toIndex(int row, int col) {
    return row * 9 + col;
  }
}

