package lk.sliit.spendee.model;

import android.content.ContentValues;
import android.database.Cursor;

import java.io.Serializable;

public class SettingModel extends Model<Long> implements Serializable {

    private double savingRate = 25.0;
    private double investmentRate = 25.0;
    private double expenseRate = 25.0;
    private double goalRate = 25.0;

    public SettingModel() {

    }

    public double getSavingRate() { return savingRate; }

    public void setSavingRate(double savingRate) {
        this.savingRate = savingRate;
    }

    public double getInvestmentRate() {
        return investmentRate;
    }

    public void setInvestmentRate(double investmentRate) {
        this.investmentRate = investmentRate;
    }

    public double getExpenseRate() {
        return expenseRate;
    }

    public void setExpenseRate(double expenseRate) {
        this.expenseRate = expenseRate;
    }

    public double getGoalRate() {
        return goalRate;
    }

    public void setGoalRate(double goalRate) {
        this.goalRate = goalRate;
    }

    @Override
    public ContentValues toContentValue() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("savingRate", savingRate);
        contentValues.put("investmentRate", investmentRate);
        contentValues.put("expenseRate", expenseRate);
        contentValues.put("goalRate", goalRate);
        return contentValues;
    }

    @Override
    public Object mapToObject(Cursor cursor) {
        SettingModel object = new SettingModel();
        object.setId(cursor.getLong(0));
        object.setSavingRate(cursor.getDouble(1));
        object.setInvestmentRate(cursor.getDouble(2));
        object.setExpenseRate(cursor.getDouble(3));
        object.setGoalRate(cursor.getDouble(4));
        return object;
    }

    @Override
    public String toString() {
        return "SettingModel{" +
                "savingRate=" + savingRate +
                ", investmentRate='" + investmentRate + '\'' +
                ", expenseRate='" + expenseRate + '\'' +
                ", goalRate=" + goalRate +
                ", id=" + id +
                '}';
    }
}