package lk.sliit.spendee.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import lk.sliit.spendee.R;
import lk.sliit.spendee.activity.income.IncomeActivity;
import lk.sliit.spendee.activity.remind.RemindActivity;

/**
 * author: Lasith Hansana
 * date: 5/07/2021
 * time: 02:02 PM
 */
public class ToolActivity extends AppCompatActivity implements View.OnClickListener {
    CardView currencyConverterCard;
    CardView reminderCard;
    CardView reportCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool);
        currencyConverterCard = findViewById(R.id.converter);
        reminderCard = findViewById(R.id.remind);
        reportCard = findViewById(R.id.report);
        currencyConverterCard.setOnClickListener(this);
        reportCard.setOnClickListener(this);
        reminderCard.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.converter:
                startIntent(CurrencyConverterActivity.class);
                break;
            case R.id.remind:
                startIntent(RemindActivity.class);
                break;
        }

    }

    private <T> void startIntent(Class<T> tClass) {
        Intent intent = new Intent(this, tClass);
        this.startActivity(intent);
    }
}