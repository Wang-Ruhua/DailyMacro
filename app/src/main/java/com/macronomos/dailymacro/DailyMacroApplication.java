package com.macronomos.dailymacro;

import android.app.Application;

import androidx.room.Room;

import com.macronomos.dailymacro.database.DailyMacroDatabase;

public class DailyMacroApplication extends Application {
    private static DailyMacroDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        database = Room.databaseBuilder(this, DailyMacroDatabase.class, "dailymacro_db").build();
    }

    public static DailyMacroDatabase getDatabase() {
        return database;
    }
}
