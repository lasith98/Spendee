package lk.sliit.spendee.repository;

import android.content.Context;

import androidx.annotation.Nullable;

import lk.sliit.spendee.model.SettingModel;
import static lk.sliit.spendee.common.DatabaseConstraints.SETTING_TABLE_NAME;

public class SettingRepository extends Repository<SettingModel, Long> {
    private static SettingRepository repository;

    private SettingRepository(@Nullable Context context) {
        super(context);

    }

    public static SettingRepository getInstance(@Nullable Context context) {
        if (repository == null) {
            repository = new SettingRepository(context);
            repository.tableName = SETTING_TABLE_NAME;
            repository.model = new SettingModel();
        }
        return repository;
    }
}