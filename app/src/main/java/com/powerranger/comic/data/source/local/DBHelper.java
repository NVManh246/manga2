package com.powerranger.comic.data.source.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.powerranger.comic.data.model.Comic;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper{

    private static final int DB_VERSION = 1;
    private static final String DB_NAME = "db_comic";

    private static final String TABLE_NAME = "comic";
    private static final String COLUME_ID = "id";
    private static final String COLUME_TITLE = "title";
    private static final String COLUME_COVER = "cover";
    private static final String COLUME_CHAPTER_ID = "chapter_id";
    private static final String COLUME_IS_FAVORITE = "favorite";

    private static DBHelper sInstance;

    public static DBHelper getInstance(Context context) {
        if(sInstance == null) {
            sInstance = new DBHelper(context);
        }
        return sInstance;
    }

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sqlCreateTable = "CREATE TABLE " + TABLE_NAME + " ("
                + COLUME_ID + " TEXT PRIMARY KEY, "
                + COLUME_TITLE + " TEXT, "
                + COLUME_COVER + " TEXT, "
                + COLUME_CHAPTER_ID + " TEXT, "
                + COLUME_IS_FAVORITE + " TEXT)";
        sqLiteDatabase.execSQL(sqlCreateTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean addComicToFavorite(Comic comic) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUME_ID, comic.getId());
        values.put(COLUME_TITLE, comic.getTitle());
        values.put(COLUME_COVER, comic.getCover());
        values.put(COLUME_IS_FAVORITE, "true");
        Long result = db.insert(TABLE_NAME, null, values);
        db.close();
        if(result != -1){
            return true;
        }
        return false;
    }

    public List<Comic> getFavoriteComics() {
        List<Comic> comics = new ArrayList<>();
        String sql = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql, null);
        while (cursor.moveToNext()) {
            String isFavorite = cursor.getString(cursor.getColumnIndex(COLUME_IS_FAVORITE));
            if(isFavorite.equals("true")) {
                String id = cursor.getString(cursor.getColumnIndex(COLUME_ID));
                String title = cursor.getString(cursor.getColumnIndex(COLUME_TITLE));
                Log.d("kiemtra", title);
                String corver = cursor.getString(cursor.getColumnIndex(COLUME_COVER));
                Comic comic = new Comic(id, title, corver);
                comics.add(comic);
            }
        }
        cursor.close();
        db.close();
        return comics;
    }

    public boolean removeFavoriteComic(String id) {
        SQLiteDatabase db = getWritableDatabase();
        int result = db.delete(TABLE_NAME, COLUME_ID + " = ? ", new String[]{id});
        db.close();
        if(result != 0) {
            return true;
        }
        return false;
    }

    public boolean isFavorite(String comicId) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,
                null,
                COLUME_ID + " = ?",
                new String[]{comicId},
                null, null, null);
        if(!cursor.moveToNext()){
            return false;
        }
        String isFavorite = cursor.getString(cursor.getColumnIndex(COLUME_IS_FAVORITE));
        cursor.close();
        db.close();
        if(isFavorite.equals("true")) {
            return true;
        }
        return false;
    }

    public boolean saveChapter(String comicId, String chapterId) {

        return false;
    }
}
