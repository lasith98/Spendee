package lk.sliit.spendee.repository;

import android.content.Context;

import androidx.annotation.Nullable;

import lk.sliit.spendee.model.IncomeModel;
import lk.sliit.spendee.model.InvestmentModel;

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
}

