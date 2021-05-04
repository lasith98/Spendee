package lk.sliit.spendee.repository;

import android.content.Context;

import androidx.annotation.Nullable;

import lk.sliit.spendee.model.SavingModel;

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
}
