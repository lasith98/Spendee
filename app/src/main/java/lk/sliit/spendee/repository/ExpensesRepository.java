package lk.sliit.spendee.repository;

import android.content.Context;

import androidx.annotation.Nullable;

import lk.sliit.spendee.model.ExpensesModel;

import static lk.sliit.spendee.common.DatabaseConstraints.EXPENSES_TABLE_NAME;



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
}
