package lk.sliit.spendee.activity.income;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import lk.sliit.spendee.R;
import lk.sliit.spendee.adapter.IncomeArrayAdapter;
import lk.sliit.spendee.model.IncomeModel;
import lk.sliit.spendee.repository.IncomeRepository;

import static lk.sliit.spendee.common.Constraints.EXTRA_OBJECT_NAME;

/**
 * author: Lasith Hansana
 * date: 3/19/2021
 * time: 8:32 PM
 */
public class IncomeActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private IncomeRepository incomeRepository;
    private ListView listView;
    private TextView totalIncome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_income);
        incomeRepository = IncomeRepository.getInstance(this);
        ImageView add = findViewById(R.id.addIncome);
        add.setOnClickListener(this);
        listView = findViewById(R.id.incomeListView);
        listView.setOnItemClickListener(this);
        totalIncome =findViewById(R.id.totalIncome);


    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, IncomeAeActivity.class);
        intent.putExtra(EXTRA_OBJECT_NAME, new IncomeModel());
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        updateTotal();
        super.onStart();
        listView.setAdapter(new IncomeArrayAdapter(this, R.layout.list_tile_layout, incomeRepository.findByAll()));
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent intent = new Intent(this, IncomeAeActivity.class);
        intent.putExtra(EXTRA_OBJECT_NAME, (IncomeModel) listView.getItemAtPosition(position));
        startActivity(intent);
    }

    private void  updateTotal(){
        totalIncome.setText("Total : " + incomeRepository.findTotalIncome());
    }
}