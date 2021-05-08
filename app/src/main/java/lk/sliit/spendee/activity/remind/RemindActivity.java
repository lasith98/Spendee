package lk.sliit.spendee.activity.remind;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Objects;


import lk.sliit.spendee.MainActivity;
import lk.sliit.spendee.R;
import lk.sliit.spendee.receiver.RemindReceiver;

public class RemindActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText message;
    private EditText datetime;
    private Button addButton;
    private Button cancelButton;
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy/MM/dd HH:mm");
    private final int notificationId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remind);
        datetime = findViewById(R.id.remindDateEditText);
        datetime.setOnClickListener(view -> showDateTimeDialog(datetime));
        message = findViewById(R.id.remindMessageEditText);
        addButton = findViewById(R.id.remindAddButton);
        cancelButton = findViewById(R.id.remindCancelButton);

        addButton.setOnClickListener(this);
        cancelButton.setOnClickListener(this);
    }

    private void showDateTimeDialog(final EditText date_time_in) {
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = (view, year, month, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            TimePickerDialog.OnTimeSetListener timeSetListener = (view1, hourOfDay, minute) -> {
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);



                date_time_in.setText(simpleDateFormat.format(calendar.getTime()));
            };

            new TimePickerDialog(RemindActivity.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
        };

        new DatePickerDialog(RemindActivity.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();

    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, RemindReceiver.class);
        intent.putExtra("notificationId",notificationId);
        intent.putExtra("message",message.getText().toString());

        PendingIntent pendingIntent =  PendingIntent.getBroadcast(getBaseContext(),0,intent, PendingIntent.FLAG_CANCEL_CURRENT);
        // AlarmManager
        AlarmManager alarmManager = (AlarmManager)getSystemService(ALARM_SERVICE);

        switch (view.getId()) {

            case R.id.remindAddButton:
                Calendar remindDateTime = Calendar.getInstance();
                try {
                    remindDateTime.setTime(Objects.requireNonNull(simpleDateFormat.parse(datetime.getText().toString())));
                    Calendar startTime = Calendar.getInstance();
                    startTime.set(Calendar.HOUR_OF_DAY,remindDateTime.getTime().getHours());
                    startTime.set(Calendar.MINUTE,remindDateTime.getTime().getMinutes());
                    startTime.set(Calendar.SECOND,remindDateTime.getTime().getSeconds());
                    // Set Alarm
                    alarmManager.set(AlarmManager.RTC_WAKEUP, startTime.getTimeInMillis(), pendingIntent);
                    Toast.makeText(this, "Done!", Toast.LENGTH_SHORT).show();

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.remindCancelButton:
                alarmManager.cancel(pendingIntent);
                Toast.makeText(this, "Canceled.", Toast.LENGTH_SHORT).show();
                break;



        }
    }
}