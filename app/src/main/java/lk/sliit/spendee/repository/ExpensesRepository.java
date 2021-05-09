package lk.sliit.spendee.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import lk.sliit.spendee.model.ExpensesModel;

import static lk.sliit.spendee.common.DatabaseConstraints.EXPENSES_TABLE_NAME;
import static lk.sliit.spendee.common.DatabaseConstraints.INCOME_TABLE_NAME;


public class ExpensesRepository extends Repository<ExpensesModel, Long> {

    private static ExpensesRepository repository;

    private ExpensesRepository(@Nullable Context context) {
        super(context);

    }

    public static ExpensesRepository getInstance(@Nullable Context context) {
        if (repository == null) {
            repository = new ExpensesRepository(context);
            repository.tableName = EXPENSES_TABLE_NAME;
            repository.model = new ExpensesModel();
        }
        return repository;
    }
    public long findYearOfMonthReport(String yearOfMonth) {
        long total = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT sum(amount) FROM " + EXPENSES_TABLE_NAME + " WHERE strftime('%Y-%m',date)=?", new String[]{yearOfMonth});
        if (cursor.moveToFirst()) {
            total = cursor.getLong(0);
        }

        cursor.close();
        return total;
    }
}
