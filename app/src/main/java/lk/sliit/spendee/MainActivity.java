package lk.sliit.spendee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.util.List;

import lk.sliit.spendee.activity.DashboardActivity;
import lk.sliit.spendee.model.AccountModel;
import lk.sliit.spendee.model.SavingModel;
import lk.sliit.spendee.repository.AccountRepository;

import static lk.sliit.spendee.common.Constraints.EXTRA_OBJECT_NAME;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button goButton;

    EditText text1;
    EditText text2;
    EditText text3;
    EditText text4;

    private AccountRepository accountRepository;
    private AccountModel model;
    private boolean entered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        goButton = findViewById(R.id.goButton);
        text1 = findViewById(R.id.txt1);
        text2 = findViewById(R.id.txt2);
        text3 = findViewById(R.id.txt3);
        text4 = findViewById(R.id.txt5);

        accountRepository = AccountRepository.getInstance(this);

        getPin();

        goButton.setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        text1.requestFocus();
    }

    @Override
    public void onClick(View view) {
        String one = text1.getText().toString();
        String two = text2.getText().toString();
        String three = text3.getText().toString();
        String four = text4.getText().toString();
        String pin = one + two + three + four;

        /*
            check PIN entered otherwise
            enter and go to dashboard
         */
        if (!entered) {
            if (pin.equals(""))
                Toast.makeText(this, "Enter four digits as a pin", Toast.LENGTH_SHORT).show();
            else {
                model.setPin(Integer.parseInt(pin));
                accountRepository.save(model);
                Intent intent = new Intent(this, DashboardActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                this.finish();
            }
        } else {
            if (pin.equals("")) {
                Toast.makeText(this, "Enter four digits as a pin", Toast.LENGTH_SHORT).show();
            } else {
                if (model.getPin() == Integer.parseInt(pin)) {
                    clear();
                    Intent intent = new Intent(this, DashboardActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(this, "Pin invalid !", Toast.LENGTH_SHORT).show();
                }
            }

        }

    }

    // check PIN entered by the user
    private void getPin() {
        List<AccountModel> list = accountRepository.findByAll();
        if (list.size() == 1) {
            entered = true;
            model = list.get(0);
        } else {
            model = new AccountModel();
        }
    }

    private void clear() {
        text1.setText("");
        text2.setText("");
        text3.setText("");
        text4.setText("");
        text1.requestFocus();
    }
}