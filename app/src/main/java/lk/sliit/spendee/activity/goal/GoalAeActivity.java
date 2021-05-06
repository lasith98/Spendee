package lk.sliit.spendee.activity.goal;

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

import lk.sliit.spendee.model.GoalModel;

import lk.sliit.spendee.model.RemainsModel;
import lk.sliit.spendee.repository.GoalRepository;
import lk.sliit.spendee.repository.RemainsRepository;


import static lk.sliit.spendee.common.Constraints.EXTRA_OBJECT_NAME;

public class GoalAeActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "GoalAeActivity :";
    private GoalRepository goalRepository;
    private RemainsRepository remainsRepository;
    private GoalModel model;
    private EditText amountEditText;
    private EditText descriptionEditText;
    private EditText dateEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_ae);

        Button deleteButton = findViewById(R.id.goalDeleteButton);
        Button saveButton = findViewById(R.id.goalSaveButton);
        TextView titleTextView = findViewById(R.id.goalAeTitle);

        saveButton.setOnClickListener(this);
        deleteButton.setOnClickListener(this);

        amountEditText = findViewById(R.id.goalAmountEditText);
        descriptionEditText = findViewById(R.id.goalDescriptionEditText);
        dateEditText = findViewById(R.id.goalDateEditText);
        goalRepository = GoalRepository.getInstance(this);
        remainsRepository = RemainsRepository.getInstance(this);
        model = (GoalModel) getIntent().getSerializableExtra(EXTRA_OBJECT_NAME);
        createPopupCalender();

        if (model.getId() == null) {
            titleTextView.setText(String.format(getString(R.string.AeTitle), getString(R.string.add), getString(R.string.goal)));
            deleteButton.setEnabled(false);

        } else {
            titleTextView.setText(String.format(getString(R.string.AeTitle), getString(R.string.edit), getString(R.string.goal)));
            amountEditText.setText(String.valueOf(model.getAmount()));
            descriptionEditText.setText(model.getDescription());
            dateEditText.setText(model.getDate());
        }
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.goalSaveButton) {
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
                if (remainsModel.getGoal() - model.getAmount() > 0) {
                    remainsModel.setGoal(remainsModel.getGoal() - model.getAmount());
                    remainsRepository.save(remainsModel);
                    goalRepository.save(model);
                } else {
                    Toast.makeText(this, "Can't withdrawal goal remain low", Toast.LENGTH_LONG).show();
                    return;
                }


            } else {
                goalRepository.update(model);
            }
            finish();

        } else {
            new AlertDialog.Builder(this).setTitle("Spendee").setMessage("Do you want delete ?").setPositiveButton("Yes", (dialogInterface, i) -> {
                this.goalRepository.delete(model);
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
                    GoalAeActivity.this, (view, year, month, dayOfMonth) -> {
                month = month + 1;
                String date = dayOfMonth + "/" + month + "/" + year;
                dateEditText.setText(date);
            }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
            datePickerDialog.show();
        });
    }

}