package lk.sliit.spendee.activity.investment;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

import lk.sliit.spendee.R;
import lk.sliit.spendee.model.InvestmentModel;
import lk.sliit.spendee.model.RemainsModel;
import lk.sliit.spendee.repository.InvestmentRepository;
import lk.sliit.spendee.repository.RemainsRepository;

import static lk.sliit.spendee.common.Constraints.EXTRA_OBJECT_NAME;

public class InvestmentAeActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String TAG = "InvestmentAeActivity :";
    private InvestmentRepository investmentRepository;
    private RemainsRepository remainsRepository;
    private InvestmentModel model;
    private EditText amountEditText;
    private EditText descriptionEditText;
    private EditText dateEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investment_ae);

        Button deleteButton = findViewById(R.id.investmentDeleteButton);
        Button saveButton = findViewById(R.id.investmentSaveButton);
        TextView titleTextView = findViewById(R.id.investmentAeTitle);

        saveButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);

        amountEditText = findViewById(R.id.investmentAmountEditText);
        descriptionEditText = findViewById(R.id.investmentDescriptionEditText);
        dateEditText = findViewById(R.id.investmentDateEditText);
        investmentRepository = InvestmentRepository.getInstance(this);
        remainsRepository = RemainsRepository.getInstance(this);
        model = (InvestmentModel) getIntent().getSerializableExtra(EXTRA_OBJECT_NAME);
        createPopupCalender();

        if (model.getId() == null) {
            titleTextView.setText(String.format(getString(R.string.AeTitle), getString(R.string.add), getString(R.string.investment)));
            deleteButton.setEnabled(false);

        } else {
            titleTextView.setText(String.format(getString(R.string.AeTitle), getString(R.string.edit), getString(R.string.investment)));
            amountEditText.setText(String.valueOf(model.getAmount()));
            descriptionEditText.setText(model.getDescription());
            dateEditText.setText(model.getDate());
        }
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.investmentSaveButton) {
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
                if (remainsModel.getInvestment() - model.getAmount() > 0) {
                    remainsModel.setInvestment(remainsModel.getInvestment() - model.getAmount());
                    remainsRepository.save(remainsModel);
                    investmentRepository.save(model);
                } else {
                    Toast.makeText(this, "Can't withdrawal investment remain low", Toast.LENGTH_LONG).show();
                    return;
                }

            } else {
                investmentRepository.update(model);
            }
            finish();

        } else {
            new AlertDialog.Builder(this).setTitle("Spendee").setMessage("Do you want delete ?").setPositiveButton("Yes", (dialogInterface, i) -> {
                this.investmentRepository.delete(model);
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
                    InvestmentAeActivity.this, (view, year, month, dayOfMonth) -> {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                dateEditText.setText(date);
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        });
    }

}