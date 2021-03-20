package lk.sliit.spendee.model;

import android.content.ContentValues;
import android.database.Cursor;

import java.io.Serializable;

/**
 * author: Lasith Hansana
 * date: 3/19/2021
 * time: 2:36 PM
 */
public abstract class Model<I> implements Serializable {
    I id;

    public I getId() {
        return id;
    }

    public void setId(I id) {
        this.id = id;
    }

    public  abstract  ContentValues toContentValue();

    public abstract Object mapToObject(Cursor cursor);
}
