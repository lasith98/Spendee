package lk.sliit.spendee.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import lk.sliit.spendee.R;
import lk.sliit.spendee.repository.ExpensesRepository;
import lk.sliit.spendee.repository.GoalRepository;
import lk.sliit.spendee.repository.IncomeRepository;
import lk.sliit.spendee.repository.InvestmentRepository;
import lk.sliit.spendee.repository.SavingRepository;

import static lk.sliit.spendee.common.Constraints.YEAR_OF_MONTH;

public class ReportActivity extends AppCompatActivity {

    private TextView monthOfYearTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        PieChart chart = findViewById(R.id.pieChart);
        monthOfYearTextView = findViewById(R.id.monthOfYear);
        IncomeRepository incomeRepository = IncomeRepository.getInstance(this);
        ExpensesRepository expensesRepository = ExpensesRepository.getInstance(this);
        GoalRepository goalRepository = GoalRepository.getInstance(this);
        SavingRepository savingRepository = SavingRepository.getInstance(this);
        InvestmentRepository investmentRepository = InvestmentRepository.getInstance(this);
        String yearOfMonth = getIntent().getStringExtra(YEAR_OF_MONTH);
        monthOfYearTextView.setText("Year of month : "+yearOfMonth);
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(savingRepository.findYearOfMonthReport(yearOfMonth), "Saving"));
        entries.add(new PieEntry(expensesRepository.findYearOfMonthReport(yearOfMonth), "Expenses"));
        entries.add(new PieEntry(investmentRepository.findYearOfMonthReport(yearOfMonth), "Investment"));
        entries.add(new PieEntry(goalRepository.findYearOfMonthReport(yearOfMonth),"Goal"));


        PieDataSet pieDataSet = new PieDataSet(entries, "");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.BLACK);
        pieDataSet.setValueTextSize(16);

        PieData pieData = new PieData(pieDataSet);
        chart.setData(pieData);
        chart.getDescription().setEnabled(false);
        chart.animate();
        chart.setCenterText("Income \n" + incomeRepository.findYearOfMonthReport(yearOfMonth));

    }
}