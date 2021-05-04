package lk.sliit.spendee.activity.saving;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import lk.sliit.spendee.R;
import lk.sliit.spendee.activity.income.IncomeAeActivity;
import lk.sliit.spendee.adapter.IncomeArrayAdapter;
import lk.sliit.spendee.adapter.SavingArrayAdapter;
import lk.sliit.spendee.model.IncomeModel;
import lk.sliit.spendee.model.SavingModel;
import lk.sliit.spendee.repository.IncomeRepository;
import lk.sliit.spendee.repository.SavingRepository;

import static lk.sliit.spendee.common.Constraints.EXTRA_OBJECT_NAME;

public class SavingActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private SavingRepository savingRepository;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saving);
        savingRepository = SavingRepository.getInstance(this);
        ImageView add = findViewById(R.id.saving);
        add.setOnClickListener(this);
        listView = findViewById(R.id.savingListView);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, SavingAeActivity.class);
        intent.putExtra(EXTRA_OBJECT_NAME, new SavingModel());
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        listView.setAdapter(new SavingArrayAdapter(this, R.layout.list_tile_layout, savingRepository.findByAll()));
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent intent = new Intent(this, SavingAeActivity.class);
        intent.putExtra(EXTRA_OBJECT_NAME, (SavingModel) listView.getItemAtPosition(position));
        startActivity(intent);
    }


}