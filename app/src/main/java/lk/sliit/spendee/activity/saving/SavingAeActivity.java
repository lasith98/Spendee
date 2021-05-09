package lk.sliit.spendee.activity.saving;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

import lk.sliit.spendee.R;
import lk.sliit.spendee.activity.income.IncomeAeActivity;
import lk.sliit.spendee.model.IncomeModel;
import lk.sliit.spendee.model.SavingModel;
import lk.sliit.spendee.repository.IncomeRepository;
import lk.sliit.spendee.repository.Repository;
import lk.sliit.spendee.repository.SavingRepository;

import static lk.sliit.spendee.common.Constraints.EXTRA_OBJECT_NAME;
import static lk.sliit.spendee.util.Util.convertDateSQLIteFormat;

public class SavingAeActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "SavingAeActivity :";
    private SavingRepository savingRepository;
    private SavingModel model;
    private EditText amountEditText;
    private EditText descriptionEditText;
    private EditText dateEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saving_ae);

        Button deleteButton = findViewById(R.id.savingDeleteButton);
        Button saveButton = findViewById(R.id.savingSaveButton);
        TextView titleTextView = findViewById(R.id.savingAeTitle);

        saveButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);

        amountEditText = findViewById(R.id.savingAmountEditText);
        descriptionEditText = findViewById(R.id.savingDescriptionEditText);
        dateEditText = findViewById(R.id.savingDateEditText);
        savingRepository = SavingRepository.getInstance(this);
        model = (SavingModel) getIntent().getSerializableExtra(EXTRA_OBJECT_NAME);
        createPopupCalender();

        if (model.getId() == null) {
            titleTextView.setText(String.format(getString(R.string.AeTitle), getString(R.string.add), getString(R.string.saving)));
            deleteButton.setEnabled(false);

        } else {
            titleTextView.setText(String.format(getString(R.string.AeTitle), getString(R.string.edit), getString(R.string.saving)));
            amountEditText.setText(String.valueOf(model.getAmount()));
            descriptionEditText.setText(model.getDescription());
            dateEditText.setText(model.getDate());
        }
    }
    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.savingSaveButton) {
            model.setDate(dateEditText.getText().toString());
            model.setDescription(descriptionEditText.getText().toString());
            model.setAmount(Double.parseDouble(amountEditText.getText().toString()));
            if (model.getId() == null) {
                savingRepository.save(model);
            } else {
                savingRepository.update(model);
            }
            finish();
        } else {
            new AlertDialog.Builder(this).setTitle("Spendee").setMessage("Do you want delete ?").setPositiveButton("Yes", (dialogInterface, i) -> {
                this.savingRepository.delete(model);
                dialogInterface.dismiss();
                finish();
            }).setNegativeButton("Cancel", (dialogInterface, i) -> {
                dialogInterface.dismiss();
            }).show();


        }
    }
    private void createPopupCalender() {
        Calendar calendar = Calendar.getInstance();
        dateEditText.setOnClickListener(v -> {
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    SavingAeActivity.this, (view, year, month, dayOfMonth) -> {
                month = month + 1;
                String date = year + "-" + month + "-" + dayOfMonth;
                dateEditText.setText(convertDateSQLIteFormat(date));
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        });
    }

}



