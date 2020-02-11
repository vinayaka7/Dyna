
package com.dynaforms.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BaseFormModel {

    @SerializedName("form")
    @Expose
    private ForamsModel form;

    public ForamsModel getForm() {
        return form;
    }

    public void setForm(ForamsModel form) {
        this.form = form;
    }

}
