package lk.sliit.spendee.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.TextUtils;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import lk.sliit.spendee.model.Model;

import static lk.sliit.spendee.common.DatabaseConstraints.DATABASE_NAME;
import static lk.sliit.spendee.common.DatabaseConstraints.DATABASE_TABLES;

/**
 * author: Lasith Hansana
 * date: 3/19/2021
 * time: 2:27 PM
 */
public class Repository<M, I> extends SQLiteOpenHelper {
    private SQLiteDatabase db;
    private long status;
    public String tableName;
    public Model<I> model;

    public Repository(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_TABLES.length);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        for (String table : DATABASE_TABLES) {
            db.execSQL(table);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion > newVersion) {
            onCreate(db);
        }
    }

    public long save(Model<I> model) {
        db = getWritableDatabase();
        status = db.insert(tableName, null, model.toContentValue());
        db.close();
        return status;
    }

    public long update(Model<I> model) {
        return rowUpdateDB(model.toContentValue(), "id=?", new String[]{String.valueOf(model.getId())});
    }

    public long delete(Model<I> model) {
        return rowDeleteDB("id=?", new String[]{String.valueOf(model.getId())});
    }


    public List<M> findByAll() {

        return rowFindALl(TextUtils.join(" ", new String[]{"SELECT * FROM ", tableName}), null);
    }

    public M findById(I id) {
        return rowFindByOne(TextUtils.join(" ", new String[]{"SELECT * FROM ", tableName, "WHERE id = ? "}), new String[]{String.valueOf(id)});
    }

    public long rowUpdateDB(ContentValues contentValues, String where, String[] args) {
        db = getWritableDatabase();
        status = db.update(tableName, contentValues, where, args);
        db.close();
        return status;
    }

    public long rowDeleteDB(String where, String[] args) {
        db = getWritableDatabase();
        status = db.delete(tableName, where, args);
        db.close();
        return status;
    }

    public List<M> rowFindALl(String query, String[] args) {
        List<M> models = new ArrayList<>();

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, args);
        if (cursor.moveToFirst()) {
            do {
                models.add((M) model.mapToObject(cursor));
            } while (cursor.moveToNext());
        }
        db.close();
        return models;
    }

    public M rowFindByOne(String query, String[] args) {
        M object = null;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, args);
        if (cursor.moveToFirst()) {
            object = (M) model.mapToObject(cursor);
        }
        db.close();
        return object;
    }

    public M lastRecode() {
        return rowFindByOne(TextUtils.join(" ", new String[]{"SELECT * FROM ", tableName, "ORDER BY ID DESC LIMIT 1"}), null);
    }

    public long count() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(TextUtils.join(" ", new String[]{"SELECT * FROM ", tableName}), null);
        cursor.close();
        return cursor.getCount();
    }
}
