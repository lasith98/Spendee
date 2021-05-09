package lk.sliit.spendee.activity.expenses;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import lk.sliit.spendee.R;
import lk.sliit.spendee.model.ExpensesModel;
import lk.sliit.spendee.repository.ExpensesRepository;

import static lk.sliit.spendee.common.Constraints.EXTRA_OBJECT_NAME;
import static lk.sliit.spendee.util.Util.convertDateSQLIteFormat;


public class ExpensesAeActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "ExpensesAeActivity :";
    private ExpensesRepository expensesRepository;
    private ExpensesModel model;
    private EditText amountEditText;
    private EditText descriptionEditText;
    private EditText dateEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses_ae);

        Button deleteButton = findViewById(R.id.expensesDeleteButton);
        Button saveButton = findViewById(R.id.expensesSaveButton);
        TextView titleTextView = findViewById(R.id.expensesAeTitle);

        saveButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);

        amountEditText = findViewById(R.id.expensesAmountEditText);
        descriptionEditText = findViewById(R.id.expensesDescriptionEditText);
        dateEditText = findViewById(R.id.expensesDateEditText);
        expensesRepository = ExpensesRepository.getInstance(this);
        model = (ExpensesModel) getIntent().getSerializableExtra(EXTRA_OBJECT_NAME);
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

        if (view.getId() == R.id.expensesSaveButton) {
            model.setDate(dateEditText.getText().toString());
            model.setDescription(descriptionEditText.getText().toString());
            model.setAmount(Double.parseDouble(amountEditText.getText().toString()));
            if (model.getId() == null) {
                expensesRepository.save(model);
            } else {
                expensesRepository.update(model);
            }
            finish();

        } else {
            new AlertDialog.Builder(this).setTitle("Spendee").setMessage("Do you want delete ?").setPositiveButton("Yes", (dialogInterface, i) -> {
                this.expensesRepository.delete(model);
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
                    ExpensesAeActivity.this, (view, year, month, dayOfMonth) -> {
                month = month + 1;
                String date = year + "-" + month + "-" + dayOfMonth;
                dateEditText.setText(convertDateSQLIteFormat(date));
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        });
    }

}
