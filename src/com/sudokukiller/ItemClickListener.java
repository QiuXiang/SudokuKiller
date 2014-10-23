package com.sudokukiller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ItemClickListener implements OnItemClickListener {
  public String[] createItems(List<Integer> unuseds) {
    List<String> items = new ArrayList<String>();
    items.add("");

    for (int item : unuseds) {
      items.add(String.valueOf(item));
    }

    return items.toArray(new String[(items.size())]);
  }

  @Override
  public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
    final SudokuKiller sudoku = ((SudokuAdapter) adapterView.getAdapter()).sudoku;
    final TextView textView = (TextView) view.findViewWithTag(i);
    final String[] items = createItems(sudoku.getUnuseds(i));
    final int index = i;

    int choiced = 0;
    if (textView.getText().length() != 0) {
      choiced = Integer.parseInt(textView.getText().toString());
    }

    new AlertDialog
      .Builder(view.getContext())
      .setSingleChoiceItems(
        items, choiced,
        new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialogInterface, int i) {
            if (i == 0) {
              textView.setText("");
              sudoku.setItem(index, 0);
            } else {
              textView.setText(items[i]);
              sudoku.setItem(index, Integer.parseInt(items[i]));
            }

            dialogInterface.cancel();
          }
        }
      )
      .show();
  }
}
