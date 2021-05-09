package lk.sliit.spendee.repository;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import lk.sliit.spendee.model.SavingModel;

import static lk.sliit.spendee.common.DatabaseConstraints.EXPENSES_TABLE_NAME;
import static lk.sliit.spendee.common.DatabaseConstraints.SAVING_TABLE_NAME;


public class SavingRepository extends Repository <SavingModel, Long> {
    private static SavingRepository repository;


    private SavingRepository(@Nullable Context context){
        super(context);
    }

    public static SavingRepository getInstance(@Nullable Context context){

        if (repository==null){
            repository = new SavingRepository(context);
            repository.tableName = SAVING_TABLE_NAME;
            repository.model = new SavingModel();
        }
        return repository;
    }
    public long findYearOfMonthReport(String yearOfMonth) {
        long total = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT sum(amount) FROM " + SAVING_TABLE_NAME + " WHERE strftime('%Y-%m',date)=?", new String[]{yearOfMonth});
        if (cursor.moveToFirst()) {
            total = cursor.getLong(0);
        }

        cursor.close();
        return total;
    }
}
