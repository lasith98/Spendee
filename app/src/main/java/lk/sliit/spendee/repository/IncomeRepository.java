package lk.sliit.spendee.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import lk.sliit.spendee.model.IncomeModel;

import static lk.sliit.spendee.common.DatabaseConstraints.INCOME_TABLE_NAME;

/**
 * author: Lasith Hansana
 * date: 3/19/2021
 * time: 3:56 PM
 */
public class IncomeRepository extends Repository<IncomeModel, Long> {
    private static IncomeRepository repository;

    private IncomeRepository(@Nullable Context context) {
        super(context);

    }

    public static IncomeRepository getInstance(@Nullable Context context) {
        if (repository == null) {
            repository = new IncomeRepository(context);
            repository.tableName = INCOME_TABLE_NAME;
            repository.model = new IncomeModel();
        }
        return repository;
    }

    public double findTotalIncome() {
        double total = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT sum(amount) FROM " + INCOME_TABLE_NAME, null);
        if (cursor.moveToFirst()) {
            total = cursor.getDouble(0);
        }

        cursor.close();
        return total;
    }
}
