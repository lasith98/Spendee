package lk.sliit.spendee.model;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;

import java.io.Serializable;

public class AccountModel extends Model<Long> implements Serializable {
    private int pin;

    public AccountModel() {
    }

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    @Override
    public ContentValues toContentValue() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("pin", pin);
        return contentValues;
    }

    @Override
    public Object mapToObject(Cursor cursor) {
        AccountModel object = new AccountModel();
        object.setId(cursor.getLong(0));
        object.setPin(cursor.getInt(1));
        return object;
    }

    @Override
    public String toString() {
        return "AccountModel{" +
                "pin='" + pin + '\'' +
                ", id=" + id +
                '}';
    }
}
