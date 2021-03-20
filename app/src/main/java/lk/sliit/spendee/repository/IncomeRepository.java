package lk.sliit.spendee.repository;

import android.content.Context;

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
}
