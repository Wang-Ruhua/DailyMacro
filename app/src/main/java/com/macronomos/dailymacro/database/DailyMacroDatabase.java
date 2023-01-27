package com.macronomos.dailymacro.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.macronomos.dailymacro.model.Article;

@Database(entities = {Article.class}, version = 1, exportSchema = false)
public abstract class DailyMacroDatabase extends RoomDatabase {
    public abstract ArticleDao articleDao();
}
