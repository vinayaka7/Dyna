package com.dynaforms.view;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dynaforms.R;
import com.dynaforms.model.ChildList;
import com.dynaforms.register.IStatusClickCallback;
import com.dynaforms.register.RegisterFormAdapter;
import com.dynaforms.utilities.AppConstants;
import com.dynaforms.utilities.AppUtils;
import com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker;
import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import static com.dynaforms.R.drawable.forward_icon;

public class FormRegisterActivity extends AppCompatActivity {
    private RegisterFormAdapter registerFormAdapter;

    private ArrayList<ChildList> childLists;
    private int listClickPos = AppConstants.DEFAULT_VALUE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_register);
        Intent intent = getIntent();
        if (intent != null) {
            childLists = intent.getParcelableArrayListExtra(AppConstants.DataExtraUtils.CHILD_LIST);
            TextView headerName = findViewById(R.id.header_view);
            headerName.setText(intent.getStringExtra(AppConstants.DataExtraUtils.SECTION_NAME));
        }

        if (childLists == null || childLists.isEmpty()) {
            finish();
        }

        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView registerView = findViewById(R.id.register_view);
        registerFormAdapter = new RegisterFormAdapter(childLists, this, iStatusClickCallback);
        registerView.setLayoutManager(new LinearLayoutManager(this));
        registerView.setAdapter(registerFormAdapter);
    }

    IStatusClickCallback iStatusClickCallback = new IStatusClickCallback() {
        @Override
        public void onClickStatus(int clickPosition, String viewType) {
            AppUtils.shortToast(FormRegisterActivity.this, viewType);

            listClickPos = clickPosition;
            if (childLists.get(listClickPos).getType().equals(AppConstants.RowTypes.DateTime.name())) {
                showDatePickerDialog();
            }
        }
    };

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

                            childLists.get(listClickPos).setDisplayValue(output);
                            registerFormAdapter.notifyItemChanged(listClickPos);
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }).display();
    }

    public void displayMultiSelect(final TextView et_multiselect) {
        final AlertDialog alertDialog = new AlertDialog.Builder(FormRegisterActivity.this).create(); //Read Update
        LayoutInflater adbInflater = this.getLayoutInflater();
        View checkboxLayout = adbInflater.inflate(R.layout.checkboxlayout, null);
        final CheckBox checkbox = (CheckBox) checkboxLayout.findViewById(R.id.checkbox);
        alertDialog.setView(checkboxLayout);
        alertDialog.setTitle("Select");
        for (int i = 0; i < 5; i++) {
            checkbox.setText("Checkbox" + i);

        }
        // alertDialog.setMessage("Choose");

        alertDialog.setButton(Dialog.BUTTON_POSITIVE, "Select", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // boolean checkBoxResult = false;
                et_multiselect.setText(checkbox.getText());

            }
        });
        alertDialog.setButton(Dialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
}
