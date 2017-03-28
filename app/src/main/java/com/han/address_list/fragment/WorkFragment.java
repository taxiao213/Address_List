package com.han.address_list.fragment;

import android.view.View;
import android.widget.TextView;

/**
 * Created by aaa on 2017/3/21.
 */

public class WorkFragment extends BaseFragment {

    @Override
    protected View initView() {
        TextView textView = new TextView(getContext());
        textView.setText("工作");
        return textView;
    }


}
