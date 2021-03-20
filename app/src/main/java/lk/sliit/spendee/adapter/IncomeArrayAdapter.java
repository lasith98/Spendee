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

import lk.sliit.spendee.R;
import lk.sliit.spendee.activity.income.IncomeAeActivity;
import lk.sliit.spendee.model.IncomeModel;

import static lk.sliit.spendee.common.Constraints.EXTRA_OBJECT_NAME;

/**
 * author: Lasith Hansana
 * date: 3/19/2021
 * time: 7:42 PM
 */
public class IncomeArrayAdapter extends ArrayAdapter<IncomeModel> implements View.OnClickListener {
    private final int resource;
    private IncomeModel model;

    public IncomeArrayAdapter(@NonNull Context context, int resource, @NonNull List<IncomeModel> objects) {
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

        amountTextView.setText(String.valueOf(getItem(position).getAmount()));
        descriptionTextView.setText(getItem(position).getDescription());

        return convertView;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getContext(), IncomeAeActivity.class);
        intent.putExtra(EXTRA_OBJECT_NAME, model);
        getContext().startActivity(intent);
    }
}
