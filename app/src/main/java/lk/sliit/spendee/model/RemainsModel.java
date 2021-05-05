package lk.sliit.spendee.model;

import android.content.ContentValues;
import android.database.Cursor;

import static lk.sliit.spendee.common.DatabaseConstraints.EXPENSES_REMAINS_COLUMN;
import static lk.sliit.spendee.common.DatabaseConstraints.GOAL_REMAINS_COLUMN;
import static lk.sliit.spendee.common.DatabaseConstraints.INVESTMENT_REMAINS_COLUMN;
import static lk.sliit.spendee.common.DatabaseConstraints.SAVING_REMAINS_COLUMN;

/**
 * author: Lasith Hansana
 * date: 5/6/2021
 * time: 12:46 AM
 */
public class RemainsModel extends Model<Long> {

    private double investment = 0;
    private double expenses = 0;
    private double goal = 0;
    private double saving = 0;

    public double getInvestment() {
        return investment;
    }

    public void setInvestment(double investment) {
        this.investment = investment;
    }

    public double getExpenses() {
        return expenses;
    }

    public void setExpenses(double expenses) {
        this.expenses = expenses;
    }

    public double getGoal() {
        return goal;
    }

    public void setGoal(double goal) {
        this.goal = goal;
    }

    public double getSaving() {
        return saving;
    }

    public void setSaving(double saving) {
        this.saving = saving;
    }

    @Override
    public ContentValues toContentValue() {
        ContentValues contentValues = new ContentValues();
        contentValues.put(INVESTMENT_REMAINS_COLUMN, investment);
        contentValues.put(EXPENSES_REMAINS_COLUMN, expenses);
        contentValues.put(SAVING_REMAINS_COLUMN, saving);
        contentValues.put(GOAL_REMAINS_COLUMN, goal);
        return contentValues;
    }

    @Override
    public Object mapToObject(Cursor cursor) {
        RemainsModel object = new RemainsModel();
        object.setId(cursor.getLong(0));
        object.setInvestment(cursor.getDouble(1));
        object.setExpenses(cursor.getDouble(2));
        object.setSaving(cursor.getDouble(3));
        object.setGoal(cursor.getDouble(4));

        return object;
    }
}
