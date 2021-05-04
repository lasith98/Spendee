package lk.sliit.spendee.repository;

import android.content.Context;

import androidx.annotation.Nullable;

import lk.sliit.spendee.model.GoalModel;
import lk.sliit.spendee.model.IncomeModel;

import static lk.sliit.spendee.common.DatabaseConstraints.GOAL_TABLE_NAME;
import static lk.sliit.spendee.common.DatabaseConstraints.INCOME_TABLE_NAME;

/**
 * author: Lasith Hansana
 * date: 3/19/2021
 * time: 3:56 PM
 */
public class GoalRepository extends Repository<GoalModel, Long> {
    private static GoalRepository repository;

    private GoalRepository(@Nullable Context context) {
        super(context);

    }

    public static GoalRepository getInstance(@Nullable Context context) {
        if (repository == null) {
            repository = new GoalRepository(context);
            repository.tableName = GOAL_TABLE_NAME;
            repository.model = new GoalModel();
        }
        return repository;
    }
}
