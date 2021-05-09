package lk.sliit.spendee.repository;

import android.content.Context;

import androidx.annotation.Nullable;

import lk.sliit.spendee.model.ExpenseModel;
import lk.sliit.spendee.model.IncomeModel;

import static lk.sliit.spendee.common.DatabaseConstraints.EXPENSE_TABLE_NAME;


public class ExpenseRepository extends Repository<ExpenseModel, Long> {

    private static ExpenseRepository repository;
    private ExpenseRepository(@Nullable Context context) {
        super(context);
    }
    public static ExpenseRepository getInstance(@Nullable Context context) {
        if (repository == null) {
            repository = new ExpenseRepository(context);
            repository.tableName = EXPENSE_TABLE_NAME;
            repository.model = new ExpenseModel();
        }
        return repository;
    }
}
