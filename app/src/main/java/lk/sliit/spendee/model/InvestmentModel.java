package lk.sliit.spendee.model;


import android.content.ContentValues;
import android.database.Cursor;

import java.io.Serializable;

public class InvestmentModel extends Model<Long> implements Serializable {


    private double amount = 0.0;
    private String description = "dhhd";
    private String date = "2020/04/23";

    public InvestmentModel() {
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public ContentValues toContentValue() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("amount", amount);
        contentValues.put("description", description);
        contentValues.put("date", date);
        return contentValues;
    }

    @Override
    public Object mapToObject(Cursor cursor) {
        InvestmentModel object = new InvestmentModel();
        object.setId(cursor.getLong(0));
        object.setAmount(cursor.getDouble(1));
        object.setDate(cursor.getString(2));
        object.setDescription(cursor.getString(3));
        return object;
    }

    @Override
    public String toString() {
        return "InvestmentModel{" +
                "amount=" + amount +
                ", description='" + description + '\'' +
                ", date='" + date + '\'' +
                ", id=" + id +
                '}';
    }
}
