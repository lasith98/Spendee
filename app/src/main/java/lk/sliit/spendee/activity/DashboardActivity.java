package lk.sliit.spendee.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import lk.sliit.spendee.MainActivity;
import lk.sliit.spendee.R;
import lk.sliit.spendee.activity.expenses.ExpensesActivity;
import lk.sliit.spendee.activity.goal.GoalActivity;
import lk.sliit.spendee.activity.income.IncomeActivity;

import lk.sliit.spendee.activity.investment.InvestmentActivity;

import lk.sliit.spendee.activity.saving.SavingActivity;

/**
 * author: Lasith Hansana
 * date: 3/19/2021
 * time: 11:02 PM
 */
public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {
    CardView incomeCard;
    CardView savingCard;
    CardView investmentCard;
    CardView expensesCard;
    CardView goalCard;
    CardView toolCard;
    CardView settingCard;
    CardView exitCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        incomeCard = findViewById(R.id.income);
        savingCard = findViewById(R.id.saving);
        investmentCard = findViewById(R.id.investment);
        expensesCard = findViewById(R.id.expenses);
        goalCard = findViewById(R.id.goal);
        toolCard = findViewById(R.id.tool);
        settingCard = findViewById(R.id.setting);
        exitCard = findViewById(R.id.exit);

        incomeCard.setOnClickListener(this);
        incomeCard.setOnClickListener(this);
        savingCard.setOnClickListener(this);
        investmentCard.setOnClickListener(this);
        expensesCard.setOnClickListener(this);
        goalCard.setOnClickListener(this);
        toolCard.setOnClickListener(this);
        settingCard.setOnClickListener(this);
        exitCard.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.income:
                startIntent(IncomeActivity.class);
                break;
            case R.id.investment:
                startIntent(InvestmentActivity.class);
                break;
            case R.id.setting:
                startIntent(SettingActivity.class);
                break;
            case R.id.expenses:
                startIntent(ExpensesActivity.class);
                break;
            case R.id.goal:
                startIntent(GoalActivity.class);
                break;
            case R.id.saving:
                startIntent(SavingActivity.class);
                break;
            case R.id.tool:
                startIntent(ToolActivity.class);
                break;
            case R.id.exit:
                Intent intent = new Intent(this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK |Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                this.finish();
        }
    }

    private <T> void startIntent(Class<T> tClass) {
        Intent intent = new Intent(this, tClass);
        this.startActivity(intent);
    }
}