package com.han.address_list.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.han.address_list.R;
import com.han.address_list.fragment.MainFragment;
import com.han.address_list.fragment.MenuFragment;

import butterknife.ButterKnife;

/**
 * Created by aaa on 2017/3/22.
 */

public class MessageActivity extends AppCompatActivity {
    private static final String TAG_MAIN = "tag_main";// MainFragment的标签
    private static final String TAG_MENU = "tag_menu";// MenuFragment的标签

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.message_activity);
        ButterKnife.bind(this);

        initFragment();

    }

    private void initFragment() {
        // 获取管理类
        FragmentManager fragmentManager = getSupportFragmentManager();
        // 得到事务
        FragmentTransaction beginTransaction = fragmentManager.beginTransaction();
        // 参1 要替换的布局的id 参2 替换的fragment 参3 tag 给fragment添加一个标签
        // fragmentManager.findFragmentByTag(tag)
        beginTransaction.replace(R.id.main, new MainFragment(), TAG_MAIN);
        beginTransaction.replace(R.id.menu, new MenuFragment(), TAG_MENU);
        beginTransaction.commit();// 提交事务

    }
}
