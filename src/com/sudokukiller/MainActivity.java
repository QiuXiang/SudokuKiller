package com.sudokukiller;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;

public class MainActivity extends Activity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    createSudoKuTable();
  }

  public void createSudoKuTable() {
    TableLayout sudokuTable = (TableLayout) findViewById(R.id.sudoku_table);
    for (int row = 0; row < 9; row++) {
      sudokuTable.addView(createTableRow(row), row);
    }
  }

  public TableRow createTableRow(int row) {
    TableRow tableRow = new TableRow(this);
    for (int col = 0; col < 9; col++) {
      tableRow.addView(createButton(row, col));
    }
    return tableRow;
  }

  public Button createButton(int row, int col) {
    Button button = new Button(this);
    button.setLayoutParams(new TableRow.LayoutParams(
      0, TableRow.LayoutParams.WRAP_CONTENT));
    button.setOnClickListener(new ClickListener(row, col));
    button.setTag(row * 10 + col);
    return button;
  }

  public void submit(View view) {
    int sudokuTable[][] = loadSudokuTable(view.getRootView());
    SudokuKiller.kill(sudokuTable);
    updateSudokuTable(view.getRootView(), sudokuTable);
  }

  public void clear(View view) {
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        ((Button) view.getRootView().findViewWithTag(10 * i + j)).setText("");
      }
    }
  }

  public static int[][] loadSudokuTable(View view) {
    int sudokuTable[][] = new int[9][9];
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        sudokuTable[i][j] = getNumberByTag(view, 10 * i + j);
      }
    }
    return sudokuTable;
  }

  public static void updateSudokuTable(View view, int[][] sudokuTable) {
    for (int i = 0; i < 9; i++) {
      for (int j = 0; j < 9; j++) {
        int number = sudokuTable[i][j];
        Button button = (Button) view.findViewWithTag(10 * i + j);

        if (number == 0) {
          button.setText("");
        } else {
          button.setText(String.valueOf(sudokuTable[i][j]));
        }
      }
    }
  }

  public static int getNumberByTag(View view, int tag) {
    Button button = (Button) view.findViewWithTag(tag);

    if (button.getText() == "") {
      return 0;
    } else {
      return Integer.valueOf(button.getText().toString());
    }
  }
}
