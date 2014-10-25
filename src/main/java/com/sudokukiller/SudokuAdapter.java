package com.sudokukiller;

import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class SudokuAdapter extends BaseAdapter {
  public Sudoku sudoku;

  public SudokuAdapter(Sudoku sudoku) {
    this.sudoku = sudoku;
  }

  @Override
  public int getCount() {
    return Sudoku.SIZE;
  }

  @Override
  public Object getItem(int i) {
    return sudoku.items[i];
  }

  @Override
  public long getItemId(int i) {
    return i;
  }

  @Override
  public View getView(int i, View view, ViewGroup viewGroup) {
    if (view == null) {
      TextView textView = new TextView(viewGroup.getContext());
      textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
      textView.setHeight(viewGroup.getMeasuredWidth() / 9);
      textView.setTag(i);

      if (sudoku.isEmpty(i)) {
        textView.setText("");
      } else {
        textView.setText(Integer.toString(sudoku.items[i]));
      }

      if ((Sudoku.toRow(i) / 3 + Sudoku.toCol(i) / 3) % 2 == 0) {
        textView.setBackgroundResource(R.drawable.item);
      } else {
        textView.setBackgroundResource(R.drawable.item_darker);
      }

      return textView;
    } else {
      return view;
    }
  }
}
