package com.dynaforms.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChildList implements Parcelable {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("value")
    @Expose
    private List<Object> value = null;
    @SerializedName("childList")
    @Expose
    private List<ChildList> childList = null;
    @SerializedName("options")
    @Expose
    private List<String> options = null;
    @SerializedName("format")
    @Expose
    private String format;
    private transient String displayValue;

    public String getDisplayValue() {
        return displayValue;
    }

    public void setDisplayValue(String displayValue) {
        this.displayValue = displayValue;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Object> getValue() {
        return value;
    }

    public void setValue(List<Object> value) {
        this.value = value;
    }

    public List<ChildList> getChildList() {
        return childList;
    }

    public void setChildList(List<ChildList> childList) {
        this.childList = childList;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }


    protected ChildList(Parcel in) {
        name = in.readString();
        code = in.readString();
        type = in.readString();
        if (in.readByte() == 0x01) {
            value = new ArrayList<Object>();
            in.readList(value, Object.class.getClassLoader());
        } else {
            value = null;
        }
        if (in.readByte() == 0x01) {
            childList = new ArrayList<ChildList>();
            in.readList(childList, ChildList.class.getClassLoader());
        } else {
            childList = null;
        }
        if (in.readByte() == 0x01) {
            options = new ArrayList<String>();
            in.readList(options, String.class.getClassLoader());
        } else {
            options = null;
        }
        format = in.readString();
        displayValue = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(code);
        dest.writeString(type);
        if (value == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(value);
        }
        if (childList == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(childList);
        }
        if (options == null) {
            dest.writeByte((byte) (0x00));
        } else {
            dest.writeByte((byte) (0x01));
            dest.writeList(options);
        }
        dest.writeString(format);
        dest.writeString(displayValue);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ChildList> CREATOR = new Parcelable.Creator<ChildList>() {
        @Override
        public ChildList createFromParcel(Parcel in) {
            return new ChildList(in);
        }

        @Override
        public ChildList[] newArray(int size) {
            return new ChildList[size];
        }
    };
}