package lk.sliit.spendee.activity.income;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

import lk.sliit.spendee.R;
import lk.sliit.spendee.model.IncomeModel;
import lk.sliit.spendee.repository.IncomeRepository;

import static lk.sliit.spendee.common.Constraints.EXTRA_OBJECT_NAME;

/**
 * author: Lasith Hansana
 * date: 3/19/2021
 * time: 10:32 PM
 */
public class IncomeAeActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "IncomeAeActivity :";
    private IncomeRepository incomeRepository;
    private IncomeModel model;
    private EditText amountEditText;
    private EditText descriptionEditText;
    private EditText dateEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income_ae);

        Button deleteButton = findViewById(R.id.incomeDeleteButton);
        Button saveButton = findViewById(R.id.incomeSaveButton);
        TextView titleTextView = findViewById(R.id.incomeAeTitle);

        saveButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);

        amountEditText = findViewById(R.id.incomeAmountEditText);
        descriptionEditText = findViewById(R.id.incomeDescriptionEditText);
        dateEditText = findViewById(R.id.incomeDateEditText);
        incomeRepository = IncomeRepository.getInstance(this);
        model = (IncomeModel) getIntent().getSerializableExtra(EXTRA_OBJECT_NAME);
        createPopupCalender();

        if (model.getId() == null) {
            titleTextView.setText(String.format(getString(R.string.AeTitle), getString(R.string.add), getString(R.string.income)));
            deleteButton.setEnabled(false);

        } else {
            titleTextView.setText(String.format(getString(R.string.AeTitle), getString(R.string.edit), getString(R.string.income)));
            amountEditText.setText(String.valueOf(model.getAmount()));
            descriptionEditText.setText(model.getDescription());
            dateEditText.setText(model.getDate());
        }
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.incomeSaveButton) {
            model.setDate(dateEditText.getText().toString());
            model.setDescription(descriptionEditText.getText().toString());
            model.setAmount(Double.parseDouble(amountEditText.getText().toString()));
            if (model.getId() == null) {
                incomeRepository.save(model);
            } else {
                incomeRepository.update(model);
            }
            finish();

        } else {
            new AlertDialog.Builder(this).setTitle("Spendee").setMessage("Do you want delete ?").setPositiveButton("Yes", (dialogInterface, i) -> {
                this.incomeRepository.delete(model);
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
                    IncomeAeActivity.this, (view, year, month, dayOfMonth) -> {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                dateEditText.setText(date);
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        });
    }

}


