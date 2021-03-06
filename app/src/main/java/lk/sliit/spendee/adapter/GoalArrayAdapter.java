package lk.sliit.spendee.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import java.util.Objects;

import lk.sliit.spendee.R;
import lk.sliit.spendee.activity.goal.GoalAeActivity;
import lk.sliit.spendee.model.GoalModel;

import static lk.sliit.spendee.common.Constraints.EXTRA_OBJECT_NAME;

/**
 * author: Lasith Hansana
 * date: 3/19/2021
 * time: 7:42 PM
 */
public class GoalArrayAdapter extends ArrayAdapter<GoalModel> implements View.OnClickListener {
    private final int resource;
    private GoalModel model;

    public GoalArrayAdapter(@NonNull Context context, int resource, @NonNull List<GoalModel> objects) {
        super(context, resource, objects);
        this.resource = resource;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        model = getItem(position);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(resource, parent, false);
        TextView amountTextView = convertView.findViewById(R.id.viewAmount);
        TextView descriptionTextView = convertView.findViewById(R.id.viewDescription);
        ImageView aeButton = convertView.findViewById(R.id.editButton);
        aeButton.setOnClickListener(this);

        amountTextView.setText(String.valueOf(Objects.requireNonNull(getItem(position)).getAmount()));
        descriptionTextView.setText(Objects.requireNonNull(getItem(position)).getDescription());

        return convertView;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getContext(), GoalAeActivity.class);
        intent.putExtra(EXTRA_OBJECT_NAME, model);
        getContext().startActivity(intent);
    }
}
