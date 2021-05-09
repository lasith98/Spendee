package lk.sliit.spendee.activity.expenses;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import lk.sliit.spendee.R;
import lk.sliit.spendee.model.ExpensesModel;
import lk.sliit.spendee.model.RemainsModel;
import lk.sliit.spendee.repository.ExpensesRepository;
import lk.sliit.spendee.repository.RemainsRepository;

import static lk.sliit.spendee.common.Constraints.EXTRA_OBJECT_NAME;
import static lk.sliit.spendee.util.Util.convertDateSQLIteFormat;

public class ExpensesAeActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "ExpensesAeActivity :";
    private ExpensesRepository expensesRepository;
    private RemainsRepository remainsRepository;
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
        remainsRepository = RemainsRepository.getInstance(this);
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
            if (amountEditText.getText().toString().isEmpty()) {
                Toast.makeText(this, "Can't blank or characters in amount  amount", Toast.LENGTH_LONG).show();
                return;
            }

            if (dateEditText.getText().toString().isEmpty() || dateEditText.getText().toString().isEmpty()) {
                Toast.makeText(this, "Can't blank description or date", Toast.LENGTH_LONG).show();
                return;
            }
            model.setDate(dateEditText.getText().toString());
            model.setDescription(descriptionEditText.getText().toString());
            model.setAmount(Double.parseDouble(amountEditText.getText().toString()));
            if (model.getId() == null) {
                RemainsModel remainsModel = remainsRepository.lastRecode();
                if (remainsModel == null) {
                    remainsModel = new RemainsModel();
                }
                if (remainsModel.getExpenses() - model.getAmount() > 0) {
                    remainsModel.setExpenses(remainsModel.getExpenses() - model.getAmount());
                    remainsRepository.save(remainsModel);
                    expensesRepository.save(model);
                } else {
                    Toast.makeText(this, "Can't withdrawal expenses remain low", Toast.LENGTH_LONG).show();
                    return;
                }
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
