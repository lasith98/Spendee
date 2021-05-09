package lk.sliit.spendee.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import lk.sliit.spendee.model.IncomeModel;
import lk.sliit.spendee.model.InvestmentModel;

import static lk.sliit.spendee.common.DatabaseConstraints.EXPENSES_TABLE_NAME;
import static lk.sliit.spendee.common.DatabaseConstraints.INCOME_TABLE_NAME;
import static lk.sliit.spendee.common.DatabaseConstraints.INVESTMENT_TABLE_NAME;

public class InvestmentRepository extends Repository<InvestmentModel, Long> {
    private static InvestmentRepository repository;

    private InvestmentRepository(@Nullable Context context) {
        super(context);

    }

    public static InvestmentRepository getInstance(@Nullable Context context) {
        if (repository == null) {
            repository = new InvestmentRepository(context);
            repository.tableName = INVESTMENT_TABLE_NAME;
            repository.model = new InvestmentModel();
        }
        return repository;
    }

    public long findYearOfMonthReport(String yearOfMonth) {
        long total = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT sum(amount) FROM " + INVESTMENT_TABLE_NAME + " WHERE strftime('%Y-%m',date)=?", new String[]{yearOfMonth});
        if (cursor.moveToFirst()) {
            total = cursor.getLong(0);
        }

        cursor.close();
        return total;
    }
}

