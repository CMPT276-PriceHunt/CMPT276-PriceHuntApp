package com.example.loginapp;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class FolderUtils {

    public static ArrayList<WishlistFolder> loadFolders(Context context, SharedPreferences prefs, Gson gson, String FOLDERS_KEY) {
        String foldersJson = prefs.getString(FOLDERS_KEY, null);
        Type type = new TypeToken<ArrayList<WishlistFolder>>(){}.getType();
        ArrayList<WishlistFolder> folders;

        if (foldersJson != null) {
            folders = gson.fromJson(foldersJson, type);
        } else {
            folders = new ArrayList<>();
            folders.add(new WishlistFolder("Default Wishlist"));
            saveFolders(context, prefs, gson, FOLDERS_KEY, folders);
        }

        return folders;
    }

    public static void saveFolders(Context context, SharedPreferences prefs, Gson gson, String FOLDERS_KEY, ArrayList<WishlistFolder> folders) {
        String foldersJson = gson.toJson(folders);
        prefs.edit().putString(FOLDERS_KEY, foldersJson).apply();
    }
}