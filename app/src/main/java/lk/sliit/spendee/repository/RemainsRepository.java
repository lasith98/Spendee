package lk.sliit.spendee.repository;

import android.content.Context;

import androidx.annotation.Nullable;

import lk.sliit.spendee.model.RemainsModel;

import static lk.sliit.spendee.common.DatabaseConstraints.REMAINS_TABLE_NAME;

/**
 * author: Lasith Hansana
 * date: 5/6/2021
 * time: 12:45 AM
 */
public class RemainsRepository extends Repository<RemainsModel, Long> {
    private static RemainsRepository repository;

    private RemainsRepository(@Nullable Context context) {
        super(context);

    }

    public static RemainsRepository getInstance(@Nullable Context context) {
        if (repository == null) {
            repository = new RemainsRepository(context);
            repository.tableName = REMAINS_TABLE_NAME;
            repository.model = new RemainsModel();
        }
        return repository;
    }

}