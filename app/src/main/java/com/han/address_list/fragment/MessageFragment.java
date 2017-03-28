package com.han.address_list.fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

/**
 * Created by aaa on 2017/3/21.
 */

public class MessageFragment extends BaseFragment {

    @Override
    protected View initView() {
        TextView textView = new TextView(getActivity());
        textView.setTextColor(Color.BLACK);
        textView.setText("通讯录");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(getContext(), MessageActivity.class));

            }
        });

        return textView;
    }

}
