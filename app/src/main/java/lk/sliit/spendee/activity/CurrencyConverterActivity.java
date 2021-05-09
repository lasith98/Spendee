package lk.sliit.spendee.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import lk.sliit.spendee.R;

public class CurrencyConverterActivity extends AppCompatActivity {

    Spinner sp1, sp2;
    EditText ed1;
    Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_currency_converter);

        ed1 = findViewById(R.id.txtamount);
        sp1 = findViewById(R.id.spfrom);
        sp2 = findViewById(R.id.spto);
        b1 = findViewById(R.id.btn1);

        String[] from = {"SriLankan Rupees"};
        ArrayAdapter ad = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, from);
        sp1.setAdapter(ad);

        String[] to = {"USD", "Singapore Dollar"};
        ArrayAdapter ad1 = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, to);
        sp2.setAdapter(ad1);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Double tot;

                if (ed1.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Can't blank or characters in amount  amount", Toast.LENGTH_LONG).show();
                } else {
                    Double amount = Double.parseDouble(ed1.getText().toString());

                    if (sp1.getSelectedItem().toString().equals("SriLankan Rupees") && sp2.getSelectedItem().toString().equals("USD")) {
                        tot = amount * 150.0;
                        Toast.makeText(getApplicationContext(), tot.toString(), Toast.LENGTH_LONG).show();
                    } else if (sp1.getSelectedItem().toString().equals("SriLankan Rupees") && sp2.getSelectedItem().toString().equals("Singapore Dollar")) {
                        tot = amount * 180.0;
                        Toast.makeText(getApplicationContext(), tot.toString(), Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }
}