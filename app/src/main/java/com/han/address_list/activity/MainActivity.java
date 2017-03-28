package com.han.address_list.activity;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.han.address_list.R;
import com.han.address_list.fragment.BaseFragment;
import com.han.address_list.fragment.ContactFragment;
import com.han.address_list.fragment.DingFragment;
import com.han.address_list.fragment.MeFragment;
import com.han.address_list.fragment.MessageFragment;
import com.han.address_list.fragment.WorkFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.rg_message)
    RadioButton rgMessage;
    @BindView(R.id.rg_ding)
    RadioButton rgDing;
    @BindView(R.id.rg_work)
    RadioButton rgWork;
    @BindView(R.id.rg_contact)
    RadioButton rgContact;
    @BindView(R.id.rg_me)
    RadioButton rgMe;
    @BindView(R.id.rg)
    RadioGroup rg;
    @BindView(R.id.fl)
    FrameLayout fl;
    private FragmentManager fragmentManager;
    private ArrayList<BaseFragment> list;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        list = new ArrayList<>();
        //初始化fragment
        initFragment();

        //默认显示
        switchFragment(0);
        rg.check(R.id.rg_message);

        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rg_message:
                        switchFragment(0);
                        break;
                    case R.id.rg_ding:
                        switchFragment(1);
                        break;
                    case R.id.rg_work:
                        switchFragment(2);
                        break;
                    case R.id.rg_contact:
                        switchFragment(3);
                        break;
                    case R.id.rg_me:
                        switchFragment(4);
                        break;
                    default:
                        break;
                }
            }
        });

    }

    /**
     * 切换Fragment
     */
    private void switchFragment(int posi){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for (int i = 0; i < list.size(); i++) {
            Fragment fragment = list.get(i);
            if(i==posi){
                //说明要显示这个fragment
                if(fragment.isAdded()){
                    transaction.show(fragment);
                }else {
                    //如果没加进来。那么就添加，
                    transaction.add(R.id.fl,fragment);
                }
            }else {
                //说明要隐藏这个fragment
                if(fragment.isAdded()){
                    transaction.hide(fragment);
                }
            }
        }

        transaction.commitAllowingStateLoss();
    }

    private void initFragment() {

        BaseFragment messagefragment = new MessageFragment();
        BaseFragment dingfragment = new DingFragment();
        BaseFragment workfragment = new WorkFragment();
        BaseFragment contactfragment = new ContactFragment();
        BaseFragment mefragment = new MeFragment();

        list.add(messagefragment);
        list.add(dingfragment);
        list.add(workfragment);
        list.add(contactfragment);
        list.add(mefragment);
    }



    /*private class myViewPager extends PagerAdapter {

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            BaseFragment fr = list.get(position);
            // 获取页面的view
            View view1 = fr.view;
            if (view1 != null) {
                container.addView(view1);

            }
            return view1;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }*/


}
