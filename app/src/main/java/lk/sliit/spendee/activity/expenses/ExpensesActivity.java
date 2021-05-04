package lk.sliit.spendee.activity.expenses;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import lk.sliit.spendee.R;
import lk.sliit.spendee.adapter.ExpensesArrayAdapter;
import lk.sliit.spendee.adapter.IncomeArrayAdapter;
import lk.sliit.spendee.model.ExpensesModel;
import lk.sliit.spendee.repository.ExpensesRepository;

import static lk.sliit.spendee.common.Constraints.EXTRA_OBJECT_NAME;


public class ExpensesActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private ExpensesRepository expensesRepository;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenses);
        expensesRepository = ExpensesRepository.getInstance(this);
        ImageView add = findViewById(R.id.addExpenses);
        add.setOnClickListener(this);
        listView = findViewById(R.id.expensesListView);
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, ExpensesAeActivity.class);
        intent.putExtra(EXTRA_OBJECT_NAME, new ExpensesModel());
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        listView.setAdapter(new ExpensesArrayAdapter(this, R.layout.list_tile_layout, expensesRepository.findByAll()));
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent intent = new Intent(this, ExpensesAeActivity.class);
        intent.putExtra(EXTRA_OBJECT_NAME, (ExpensesModel) listView.getItemAtPosition(position));
        startActivity(intent);
    }
}
