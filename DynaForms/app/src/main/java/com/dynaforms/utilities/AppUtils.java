package com.dynaforms.utilities;

import android.content.Context;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dynaforms.R;
import com.dynaforms.register.IDateSelectionCallback;
import com.dynaforms.view.FormRegisterActivity;
import com.github.florent37.singledateandtimepicker.SingleDateAndTimePicker;
import com.github.florent37.singledateandtimepicker.dialog.SingleDateAndTimePickerDialog;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AppUtils {

    public static void shortToast(Context context, String toastMessage) {
        Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show();
    }

    public static void loadImageFromApi(ImageView imageView, int imageDrawable) {

        Glide.with(imageView.getContext()).load(imageDrawable)
                .into(imageView);

    }

    public static void showDatePickerDialog(Context context, final IDateSelectionCallback iDateSelectionCallback) {
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
        new SingleDateAndTimePickerDialog.Builder(context)
                .defaultDate(calendar.getTime())
                .minDateRange(calendar.getTime())
                .mustBeOnFuture()
                .mainColor(context.getResources().getColor(R.color.colorPrimary))
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

                            if (iDateSelectionCallback != null) {
                                iDateSelectionCallback.onSelectDate(output);
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }).display();
    }
}
