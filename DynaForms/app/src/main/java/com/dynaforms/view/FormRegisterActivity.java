package com.dynaforms.view;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.dynaforms.R;
import com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker;
import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import static com.dynaforms.R.drawable.forward_icon;

public class FormRegisterActivity extends AppCompatActivity {
    LinearLayout dynamic_layout;
    TextView et_datetime,et_multiselect;
    int Array_Count=5;
    String[] Str_Array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_register);
        dynamic_layout = findViewById(R.id.dynamic_layout);
        ////////////Date and Time
        LinearLayout.LayoutParams lparams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        lparams.setMargins(10, 10, 10, 10);
        et_datetime = new TextView(this);
        et_datetime.setHint("Select Date and Time");
        et_datetime.setPadding(20, 30, 20, 30);
        et_datetime.setCompoundDrawablesWithIntrinsicBounds( 0, 0, forward_icon, 0);
       // et_datetime.setCompoundDrawables(null,null, getResources().getDrawable(forward_icon),null);
       et_datetime.setBackground(getResources().getDrawable(R.drawable.et_border));
        et_datetime.setGravity(Gravity.CENTER);
        et_datetime.setLayoutParams(lparams);
        et_datetime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog();
            }
        });

        dynamic_layout.addView(et_datetime);
        /////////////////Checkbox
        et_multiselect = new TextView(this);
        et_multiselect.setHint("Select Contents");
        et_multiselect.setPadding(20, 30, 20, 30);
        et_multiselect.setCompoundDrawablesWithIntrinsicBounds( 0, 0, forward_icon, 0);
        // et_datetime.setCompoundDrawables(null,null, getResources().getDrawable(forward_icon),null);
        et_multiselect.setBackground(getResources().getDrawable(R.drawable.et_border));
        et_multiselect.setGravity(Gravity.CENTER);
        et_multiselect.setLayoutParams(lparams);
        et_multiselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
displayMultiSelect(et_multiselect);
            }
        });
        dynamic_layout.addView(et_multiselect);
    }

    private void showDatePickerDialog() {
        DateFormat inputFormat = new SimpleDateFormat(
                "EEE MMM dd HH:mm:ss zzz yyyy");
        String currentDateandTime = inputFormat.format(new Date());
        Date date = null;
        try {
            date = inputFormat.parse(currentDateandTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MINUTE, 75);
        new SingleDateAndTimePickerDialog.Builder(FormRegisterActivity.this)
                .defaultDate(calendar.getTime())
                .minDateRange(calendar.getTime())
                .mustBeOnFuture()
                .mainColor(getResources().getColor(R.color.colorPrimary))
                .displayListener(new SingleDateAndTimePickerDialog.DisplayListener() {
                    @Override
                    public void onDisplayed(SingleDateAndTimePicker picker) {

                    }
                })
                .title("Select Date and Time")
                .listener(new SingleDateAndTimePickerDialog.Listener() {
                    @Override
                    public void onDateSelected(Date date2) {
                        String input = date2.toString();
                        DateFormat inputFormat = new SimpleDateFormat(
                                "EEE MMM dd HH:mm:ss zzz yyyy");
                        try {
                            Date dates = inputFormat.parse(input);
                            DateFormat outputFormat = new SimpleDateFormat("dd-mmm-yyyy hh:mm:ss.s",
                                    Locale.ENGLISH);

                            DateFormat outputFormat1 = new SimpleDateFormat("HH:mm",
                                    Locale.ENGLISH);

                            DateFormat outputFormat2 = new SimpleDateFormat("dd/MM/yyyy",
                                    Locale.ENGLISH);
                            String output = outputFormat.format(dates);
                            String output1 = outputFormat1.format(dates);
                            String output2 = outputFormat2.format(dates);
                            et_datetime.setText(output);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }).display();
    }

    public void displayMultiSelect(final TextView et_multiselect){
        final AlertDialog alertDialog = new AlertDialog.Builder(FormRegisterActivity.this).create(); //Read Update
        LayoutInflater adbInflater = this.getLayoutInflater();
        View checkboxLayout = adbInflater.inflate(R.layout.checkboxlayout, null);
        final CheckBox checkbox = (CheckBox)checkboxLayout.findViewById(R.id.checkbox);
        alertDialog.setView(checkboxLayout);
        alertDialog.setTitle("Select");
        for (int i = 0; i < 5; i++)
        {
            checkbox.setText("Checkbox"+i);

        }
       // alertDialog.setMessage("Choose");

        alertDialog.setButton(Dialog.BUTTON_POSITIVE,"Select", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
               // boolean checkBoxResult = false;
                et_multiselect.setText(checkbox.getText());

            }
        });
        alertDialog.setButton(Dialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int which)
            {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
}
