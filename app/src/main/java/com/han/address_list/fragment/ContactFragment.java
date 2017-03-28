package com.han.address_list.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;

import com.han.address_list.R;
import com.han.address_list.activity.ContactActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by aaa on 2017/3/21.
 */

public class ContactFragment extends BaseFragment {

    @BindView(R.id.phone_contact)
    RelativeLayout phoneContact;

    @Override
    protected View initView() {
        View inflate = View.inflate(getActivity(), R.layout.fragment_contact, null);
        ButterKnife.bind(this, inflate);
        phoneContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), ContactActivity.class);
                startActivity(intent);
            }
        });
        return inflate;
    }


}
