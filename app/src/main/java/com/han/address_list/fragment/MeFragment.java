package com.han.address_list.fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

/**
 * Created by aaa on 2017/3/21.
 */

public class MeFragment extends BaseFragment {

    @Override
    protected View initView() {
        TextView textView = new TextView(getActivity());
        textView.setTextColor(Color.BLACK);
        textView.setText("我的");
        return textView;
    }
}
