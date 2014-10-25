package com.sudokukiller;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.support.v7.app.ActionBarActivity;

public class MainActivity extends ActionBarActivity {
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

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.main, menu);
    return true;
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

  public void clear(MenuItem item) {
    sudoku.clear();
    gridView.setAdapter(gridView.getAdapter());
  }
}
