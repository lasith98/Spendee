package lk.sliit.spendee.repository;

import android.content.Context;

import androidx.annotation.Nullable;

import lk.sliit.spendee.model.AccountModel;

import static lk.sliit.spendee.common.DatabaseConstraints.ACCOUNT_TABLE_NAME;
import static lk.sliit.spendee.common.DatabaseConstraints.SAVING_TABLE_NAME;

public class AccountRepository extends Repository <AccountModel, Long> {
    private static AccountRepository repository;

    public AccountRepository(@Nullable Context context) {
        super(context);
    }

    public static AccountRepository getInstance(@Nullable Context context){

        if (repository==null){
            repository = new AccountRepository(context);
            repository.tableName = ACCOUNT_TABLE_NAME;
            repository.model = new AccountModel();
        }
        return repository;
    }
}
