package lk.sliit.spendee.activity.goal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import lk.sliit.spendee.R;
import lk.sliit.spendee.adapter.GoalArrayAdapter;
import lk.sliit.spendee.model.GoalModel;
import lk.sliit.spendee.model.RemainsModel;
import lk.sliit.spendee.repository.GoalRepository;
import lk.sliit.spendee.repository.RemainsRepository;

import static lk.sliit.spendee.common.Constraints.EXTRA_OBJECT_NAME;

public class GoalActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private GoalRepository goalRepository;
    private RemainsRepository remainsRepository;
    private ListView listView;
    private TextView remainsView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);
        goalRepository = GoalRepository.getInstance(this);
        remainsRepository = RemainsRepository.getInstance(this);
        ImageView add = findViewById(R.id.addGoal);
        add.setOnClickListener(this);
        listView = findViewById(R.id.goalListView);
        listView.setOnItemClickListener(this);
        remainsView = findViewById(R.id.goalRemains);

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, GoalAeActivity.class);
        intent.putExtra(EXTRA_OBJECT_NAME, new GoalModel());
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        RemainsModel remainsModel = remainsRepository.lastRecode();
        if (remainsModel == null) {
            remainsModel = new RemainsModel();
        }
        remainsView.setText("Remains :" + remainsModel.getGoal());
        listView.setAdapter(new GoalArrayAdapter(this, R.layout.list_tile_layout, goalRepository.findByAll()));
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent intent = new Intent(this, GoalAeActivity.class);
        intent.putExtra(EXTRA_OBJECT_NAME, (GoalModel) listView.getItemAtPosition(position));
        startActivity(intent);
    }
}