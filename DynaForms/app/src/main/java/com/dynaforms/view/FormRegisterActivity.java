package com.dynaforms.view;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TableRow;
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
import java.util.List;
import java.util.Locale;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FormRegisterActivity extends AppCompatActivity {
    private RegisterFormAdapter registerFormAdapter;

    private ArrayList<ChildList> childLists;
    private int listClickPos = AppConstants.DEFAULT_VALUE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_register);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        if (intent != null) {
            childLists = intent.getParcelableArrayListExtra(AppConstants.DataExtraUtils.CHILD_LIST);
            TextView headerName = findViewById(R.id.header_view);
            headerName.setText(intent.getStringExtra(AppConstants.DataExtraUtils.CHILD_SECTION_NAME));
            getSupportActionBar().setTitle(intent.getStringExtra(AppConstants.DataExtraUtils.CHILD_SECTION_NAME));
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
            ChildList childList = childLists.get(listClickPos);
            if (childList.getType().equals(AppConstants.RowTypes.DateTime.name())) {
                showDatePickerDialog();
            } else if (childList.getType().equals(AppConstants.RowTypes.MultiSelect.name())) {
                //todo showDMultiselectionDialog();
                displayMultiSelect(childList.getOptions());
                //childList.getOptions();
            } else if (childList.getType().equals(AppConstants.RowTypes.SingleSelect.name())) {
                //todo showSingleselectionDialog();
                displaySingleSelect(childList.getOptions());
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

    public void displayMultiSelect(List<String> options) {
        final AlertDialog alertDialog = new AlertDialog.Builder(FormRegisterActivity.this).create(); //Read Update
        LayoutInflater adbInflater = this.getLayoutInflater();
        View checkboxLayout = adbInflater.inflate(R.layout.checkboxlayout, null);
        LinearLayout my_layout = (LinearLayout)checkboxLayout.findViewById(R.id.single_layout);
        alertDialog.setView(checkboxLayout);
        final String[] selectedStrings = {""};
        for (int i = 0; i < options.size(); i++)
        {
            TableRow row =new TableRow(this);
            row.setId(i);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            final CheckBox checkBox = new CheckBox(this);
            checkBox.setId(i);
            checkBox.setText(options.get(i));
            row.addView(checkBox);
            my_layout.addView(row);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                                    @Override
                                                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                        if (isChecked) {
                                                            selectedStrings[0] = checkBox.getText().toString();
                                                        }else{
                                                            selectedStrings[0]= checkBox.getText().toString();
                                                        }                     }
                                                }
            );
        }

        // alertDialog.setMessage("Choose");

        alertDialog.setButton(Dialog.BUTTON_POSITIVE, "Select", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // boolean checkBoxResult = false;
                childLists.get(listClickPos).setDisplayValue(selectedStrings[0]);
                registerFormAdapter.notifyItemChanged(listClickPos);

            }
        });
        alertDialog.setButton(Dialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
    public void displaySingleSelect(final List<String> options) {
        final AlertDialog alertDialog = new AlertDialog.Builder(FormRegisterActivity.this).create(); //Read Update
        LayoutInflater adbInflater = this.getLayoutInflater();
        View checkboxLayout = adbInflater.inflate(R.layout.checkboxlayout, null);
        LinearLayout my_layout = (LinearLayout)checkboxLayout.findViewById(R.id.single_layout);
        alertDialog.setView(checkboxLayout);
        final String[] selectedStrings = {""};
        final ArrayList<CheckBox> mCheckBoxes = new ArrayList<CheckBox>();
        final int[] selected_position = {-1};
        for (int i = 0; i < options.size(); i++)
        {
            final TableRow row =new TableRow(this);
            row.setId(i);
            row.setLayoutParams(new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT));
            final CheckBox checkBox = new CheckBox(this);
            checkBox.setId(i);
            checkBox.setText(options.get(i));
            row.addView(checkBox);
            my_layout.addView(row);

            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                                                    @Override
                                                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                        if (isChecked) {
                                                            selectedStrings[0] = checkBox.getText().toString();
                                                            checkBox.setChecked(false);

                                                        }else{
                                                            selectedStrings[0]= checkBox.getText().toString();
                                                          //  checkBox.setChecked(false);
                                                        }                     }
                                                }
            );
            /*checkBox.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick (View view) {

                    if (((CheckBox) view).isChecked())
                    {
                        for (int i = 0; i < mCheckBoxes.size(); i++) {
                            if (mCheckBoxes.get(i) == view)
                                selected_position[0] = i;
                            else
                                mCheckBoxes.get(i).setChecked(false);
                        }

                    }
                    else
                    {
                        selected_position[0] =-1;
                    }
                }

            });*/
        }

        // alertDialog.setMessage("Choose");

        alertDialog.setButton(Dialog.BUTTON_POSITIVE, "Select", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // boolean checkBoxResult = false;
                childLists.get(listClickPos).setDisplayValue(selectedStrings[0]);
                registerFormAdapter.notifyItemChanged(listClickPos);

            }
        });
        alertDialog.setButton(Dialog.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }
    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
