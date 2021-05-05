package lk.sliit.spendee.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import lk.sliit.spendee.R;
import lk.sliit.spendee.model.SettingModel;
import lk.sliit.spendee.repository.SettingRepository;
import lk.sliit.spendee.repository.Repository;

public class SettingActivity extends AppCompatActivity {

    double savingRate, investmentRate, expenseRate, goalRate;

    EditText savingRateEditText;
    EditText investmentRateEditText;
    EditText expensesRateEditText;
    EditText goalRateEditText;

    Button incomeSaveButton;

    SettingRepository settingRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        settingRepository = SettingRepository.getInstance(this);

        savingRateEditText = (EditText) findViewById(R.id.savingRateEditText);
        investmentRateEditText = (EditText) findViewById(R.id.investmentRateEditText);
        expensesRateEditText = (EditText) findViewById(R.id.expensesRateEditText);
        goalRateEditText = (EditText) findViewById(R.id.goalRateEditText);

        incomeSaveButton = (Button) findViewById(R.id.incomeSaveButton);
        incomeSaveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                savingRate = Double.valueOf(savingRateEditText.getText().toString());
                investmentRate = Double.valueOf(investmentRateEditText.getText().toString());
                expenseRate = Double.valueOf(expensesRateEditText.getText().toString());
                goalRate = Double.valueOf(goalRateEditText.getText().toString());

                SettingModel settingModel;
                if (settingRepository.findByAll().size() == 0) {
                    settingModel = new SettingModel();
                } else {
                    settingModel = settingRepository.lastRecode();
                }

                settingModel.setSavingRate(Double.parseDouble(savingRateEditText.getText().toString()));
                settingModel.setInvestmentRate(Double.parseDouble(investmentRateEditText.getText().toString()));
                settingModel.setExpenseRate(Double.parseDouble(expensesRateEditText.getText().toString()));
                settingModel.setGoalRate(Double.parseDouble(goalRateEditText.getText().toString()));
                if (settingModel.getId() == null) {
                    settingRepository.save(settingModel);
                } else {
                    settingRepository.update(settingModel);
                }

            }

        });
    }


}