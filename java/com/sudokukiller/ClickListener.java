package com.sudokukiller;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClickListener implements View.OnClickListener {
    public int row;
    public int col;

    public ClickListener(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public void onClick(View view) {
        final Button button = (Button) view;
        final String[] items = createItems(SudokuKiller.getUsedNumbers(
                MainActivity.loadSudokuTable(view.getRootView()), row, col));

        int choiced = 0;
        if (button.getText().length() != 0) {
            choiced = Integer.parseInt(button.getText().toString());
        }

        new AlertDialog
                .Builder(view.getContext())
                .setSingleChoiceItems(
                        items, choiced,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if (i == 0) {
                                    button.setText("");
                                } else {
                                    button.setText(items[i]);
                                }

                                dialogInterface.cancel();
                            }
                        }
                )
                .show();
    }

    public String[] createItems(List<Integer> usedNumbers) {
        List<String> items = new ArrayList<String>(Arrays.asList(new String[]{
                "", "1", "2", "3", "4", "5", "6", "7", "8", "9"}));

        for (int item : usedNumbers) {
            String value = String.valueOf(item);
            if (items.contains(value)) {
                items.remove(value);
            }
        }

        return items.toArray(new String[(items.size())]);
    }
}
