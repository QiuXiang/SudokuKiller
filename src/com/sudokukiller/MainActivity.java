package com.sudokukiller;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends Activity {
  private SudokuKiller sudoku = new SudokuKiller();
  private GridView gridView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    createGridView();
  }

  @Override
  protected void onRestoreInstanceState(Bundle savedInstanceState) {
    super.onRestoreInstanceState(savedInstanceState);
    sudoku.setItems(savedInstanceState.getIntArray("sudoku_items"));
  }

  @Override
  protected void onSaveInstanceState(Bundle savedInstanceState) {
    super.onSaveInstanceState(savedInstanceState);
    savedInstanceState.putIntArray("sudoku_items", sudoku.getItems());
  }

  public void createGridView() {
    gridView = (GridView) findViewById(R.id.gridview_sudoku);
    gridView.setAdapter(new SudokuAdapter(sudoku));
    gridView.setOnItemClickListener(new ItemClickListener());
  }

  public void submit(View view) {
    sudoku.fill();
    gridView.setAdapter(gridView.getAdapter());
  }

  public void clear(View view) {
    sudoku.clear();
    gridView.setAdapter(gridView.getAdapter());
  }
}
