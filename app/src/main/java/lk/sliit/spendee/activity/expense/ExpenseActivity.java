package lk.sliit.spendee.activity.expense;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import lk.sliit.spendee.R;
import lk.sliit.spendee.adapter.ExpenseArrayAdapter;
import lk.sliit.spendee.adapter.IncomeArrayAdapter;
import lk.sliit.spendee.model.ExpenseModel;
import lk.sliit.spendee.model.IncomeModel;
import lk.sliit.spendee.repository.ExpenseRepository;
import lk.sliit.spendee.repository.IncomeRepository;

import static lk.sliit.spendee.common.Constraints.EXTRA_OBJECT_NAME;

/**
 * author: dulaj dilshan
 * date: 3/21/2021
 * time: 7:32 PM
 */
public class ExpenseActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ExpenseRepository expenseRepository;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expense);
        expenseRepository = ExpenseRepository.getInstance(this);
        ImageView add = findViewById(R.id.addExpense);
        add.setOnClickListener(this);
        listView = findViewById(R.id.expenseListView);
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, ExpenseAeActivity.class);
        intent.putExtra(EXTRA_OBJECT_NAME, new ExpenseModel());
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        listView.setAdapter(new ExpenseArrayAdapter(this, R.layout.list_tile_layout, expenseRepository.findByAll()));
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent intent = new Intent(this, ExpenseAeActivity.class);
        intent.putExtra(EXTRA_OBJECT_NAME, (ExpenseModel) listView.getItemAtPosition(position));
        startActivity(intent);
    }
}