package com.example.loginapp;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.util.ArrayList;

public class SpinnerUtils {

    public static void setupDialogSpinner(Context context, Spinner dialogSpinner, ArrayList<WishlistFolder> folders, int selectedFolderPosition, OnFolderSelectedListener listener) {
        ArrayList<String> folderNames = new ArrayList<>();
        for (WishlistFolder folder : folders) {
            folderNames.add(folder.getName());
        }

        ArrayAdapter<String> dialogSpinnerAdapter = new ArrayAdapter<>(
                context,
                android.R.layout.simple_spinner_item,
                folderNames
        );

        dialogSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dialogSpinner.setAdapter(dialogSpinnerAdapter);
        dialogSpinner.setSelection(selectedFolderPosition);
        dialogSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                listener.onFolderSelected(folderNames.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Handle case where no item is selected if needed
            }
        });
    }

    public interface OnFolderSelectedListener {
        void onFolderSelected(String folderName);
    }
}