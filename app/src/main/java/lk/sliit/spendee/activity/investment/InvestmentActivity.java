package lk.sliit.spendee.activity.investment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import lk.sliit.spendee.R;
import lk.sliit.spendee.adapter.InvestmentArrayAdapter;
import lk.sliit.spendee.model.InvestmentModel;
import lk.sliit.spendee.model.RemainsModel;
import lk.sliit.spendee.repository.InvestmentRepository;
import lk.sliit.spendee.repository.RemainsRepository;

import static lk.sliit.spendee.common.Constraints.EXTRA_OBJECT_NAME;

public class InvestmentActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private InvestmentRepository investmentRepository;
    private RemainsRepository remainsRepository;
    private ListView listView;
    private TextView remainsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_investment);
        investmentRepository = InvestmentRepository.getInstance(this);
        remainsRepository = RemainsRepository.getInstance(this);
        ImageView add = findViewById(R.id.addInvestment);
        add.setOnClickListener(this);
        listView = findViewById(R.id.investmentListView);
        listView.setOnItemClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, InvestmentAeActivity.class);
        intent.putExtra(EXTRA_OBJECT_NAME, new InvestmentModel());
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        RemainsModel remainsModel = remainsRepository.lastRecode();
        if (remainsModel == null) {
            remainsModel = new RemainsModel();
        }
        remainsView.setText("Remains :" + remainsModel.getInvestment());
        listView.setAdapter(new InvestmentArrayAdapter(this, R.layout.list_tile_layout, investmentRepository.findByAll()));
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent intent = new Intent(this, InvestmentAeActivity.class);
        intent.putExtra(EXTRA_OBJECT_NAME, (InvestmentModel) listView.getItemAtPosition(position));
        startActivity(intent);
    }
}

