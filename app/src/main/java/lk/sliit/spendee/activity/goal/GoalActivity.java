package lk.sliit.spendee.activity.goal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import lk.sliit.spendee.R;
import lk.sliit.spendee.adapter.GoalArrayAdapter;
import lk.sliit.spendee.model.GoalModel;
import lk.sliit.spendee.repository.GoalRepository;

import static lk.sliit.spendee.common.Constraints.EXTRA_OBJECT_NAME;

public class GoalActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {
    private GoalRepository goalRepository;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);
        goalRepository = GoalRepository.getInstance(this);
        ImageView add = findViewById(R.id.addGoal);
        add.setOnClickListener(this);
        listView = findViewById(R.id.goalListView);
        listView.setOnItemClickListener(this);

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
        listView.setAdapter(new GoalArrayAdapter(this, R.layout.list_tile_layout, goalRepository.findByAll()));
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        Intent intent = new Intent(this, GoalAeActivity.class);
        intent.putExtra(EXTRA_OBJECT_NAME, (GoalModel) listView.getItemAtPosition(position));
        startActivity(intent);
    }
}