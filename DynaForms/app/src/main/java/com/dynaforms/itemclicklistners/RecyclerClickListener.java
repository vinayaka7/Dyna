package com.dynaforms.itemclicklistners;

import android.view.View;

public interface RecyclerClickListener {
    void onItemClick(int parentPos, int childPos);

    void onClickView(int parentPosition, int childPos);

}
