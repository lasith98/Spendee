package lk.sliit.spendee.activity.expense;

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
import lk.sliit.spendee.model.ExpenseModel;
import lk.sliit.spendee.repository.ExpenseRepository;

import static lk.sliit.spendee.common.Constraints.EXTRA_OBJECT_NAME;

/**
 * author: DULAJ DILSHAN
 * date: 3/19/2021
 * time: 10:32 PM
 */
public class ExpenseAeActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "ExpenseAeActivity :";
    private ExpenseRepository expenseRepository;
    private ExpenseModel model;
    private EditText amountEditText;
    private EditText descriptionEditText;
    private EditText dateEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense_ae);

        Button deleteButton = findViewById(R.id.expenseDeleteButton);
        Button saveButton = findViewById(R.id.expenseSaveButton);
        TextView titleTextView = findViewById(R.id.expenseAeTitle);

        saveButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);

        amountEditText = findViewById(R.id.expenseAmountEditText);
        descriptionEditText = findViewById(R.id.expenseDescriptionEditText);
        dateEditText = findViewById(R.id.incomeDateEditText);
        expenseRepository = ExpenseRepository.getInstance(this);
        model = (ExpenseModel) getIntent().getSerializableExtra(EXTRA_OBJECT_NAME);
        createPopupCalender();

        if (model.getId() == null) {
            titleTextView.setText(String.format(getString(R.string.AeTitle), getString(R.string.add), getString(R.string.expenses)));
            deleteButton.setEnabled(false);

        } else {
            titleTextView.setText(String.format(getString(R.string.AeTitle), getString(R.string.edit), getString(R.string.expenses)));
            amountEditText.setText(String.valueOf(model.getAmount()));
            descriptionEditText.setText(model.getDescription());
            dateEditText.setText(model.getDate());
        }
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.expenseSaveButton) {
            model.setDate(dateEditText.getText().toString());
            model.setDescription(descriptionEditText.getText().toString());
            model.setAmount(Double.parseDouble(amountEditText.getText().toString()));
            if (model.getId() == null) {
                expenseRepository.save(model);
            } else {
                expenseRepository.update(model);
            }
            finish();

        } else {
            new AlertDialog.Builder(this).setTitle("Spendee").setMessage("Do you want delete ?").setPositiveButton("Yes", (dialogInterface, i) -> {
                this.expenseRepository.delete(model);
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
                    ExpenseAeActivity.this, (view, year, month, dayOfMonth) -> {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                dateEditText.setText(date);
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        });
    }

}