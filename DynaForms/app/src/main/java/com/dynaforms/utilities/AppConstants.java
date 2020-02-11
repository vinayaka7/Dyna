package com.dynaforms.utilities;

public interface AppConstants {

    int DEFAULT_VALUE = Integer.MAX_VALUE;
    enum RowTypes {
        TextArea(0),

        DateTime(1),
        CheckBox(2),
        DropDown(3),
        PlainText(4),
        MultiSelect(5),
        SingleSelect(6);

        private final int value;

        RowTypes(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }


    interface DataExtraUtils {

        String CHILD_LIST = "CHILD_LIST";
        String SECTION_NAME = "SECTION_NAME";
    }
}
