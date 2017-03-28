package com.han.address_list.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.han.address_list.R;
import com.han.address_list.view.QuickIndexBar;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by aaa on 2017/3/21.
 */

public class ContactActivity extends AppCompatActivity {


    @BindView(R.id.back)
    RelativeLayout back;
    @BindView(R.id.search)
    TextView search;
    @BindView(R.id.quickIndexBar)
    QuickIndexBar quickIndexBar;
    @BindView(R.id.tv1)
    TextView tv1;
    @BindView(R.id.ll)
    LinearLayout ll;
    @BindView(R.id.re)
    RelativeLayout re;
    @BindView(R.id.tv2)
    TextView tv2;
    private ArrayList<String> list;
    private PopupWindow mpopupWindow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        ButterKnife.bind(this);
        //初始化数据
        initdata();


    }

    private void initdata() {
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add("1823407822" + i);
        }
    }

    @OnClick({R.id.back, R.id.tv1,R.id.tv2})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.tv1:
                showPopup();
                break;
            case R.id.tv2:
                showPopup();
                break;

        }
    }



    /**
     * 显示popup
     */
    private void showPopup() {
        ListView listview = initListView();
        AlertDialog.Builder builder = new AlertDialog.Builder(getApplicationContext());

        /**
         * contentView popupwindw要展示的内容
         * width :popupwindo的宽
         * true :代表popupwindow可以获取焦点
         */
        //[1]初始化popupwindow
        if (mpopupWindow == null) {
            mpopupWindow = new PopupWindow(listview, ll.getWidth() - tv1.getWidth() - 20, re.getHeight(), true);

            //[1.1]点击popupwindow外部消失
            mpopupWindow.setOutsideTouchable(true);
            mpopupWindow.setBackgroundDrawable(new ColorDrawable());
            mpopupWindow.setBackgroundDrawable(new ColorDrawable());

        }
        //[2]展示popuowindow  参数1 在哪个view下面  xoff:x轴偏移量
        mpopupWindow.showAsDropDown(tv1, (int) (tv1.getWidth() + getResources().getDimension(R.dimen.width)), -20);
    }

    private ListView initListView() {
        //[1]通过打气筒把一个布局转换成一个listview
        ListView listView = (ListView) View.inflate(getApplicationContext(), R.layout.listview, null);
        //[]设置listview的分割线
        listView.setDivider(new ColorDrawable(Color.GRAY));
        listView.setDividerHeight(1);
        //[2]listview展示数据 创建适配器
        listView.setAdapter(new MyAdapter());
        //[3]给listview条目设置点击事件
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                /*//[4]取出点击条目的内容  数据在哪里 存着就去哪里取
                String data = list.get(position);
                //[5]把data的数据展示到edittext上
                tv1.setText(data);*/

                //[6]让popupwindow消失
                mpopupWindow.dismiss();


            }
        });

        return listView;
    }



    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        /**
         * 初始化listview每个条目的内容
         **/
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            //[1]对listview 进行简单优化
            View view;
            if (convertView == null) {
                view = View.inflate(getApplicationContext(), R.layout.list_item, null);
            } else {
                view = convertView;
            }
            //[2]找到条目里面的控件  然后展示数据

            TextView tv_name = (TextView) view.findViewById(R.id.name);
            ImageView tv_number = (ImageView) view.findViewById(R.id.number);
            ImageView tv_message = (ImageView) view.findViewById(R.id.message);

            //[3]展示数据  数据在哪里存着就去哪里取
            tv_name.setText(list.get(position));
            //[4]给删除按钮设置点击事件
            tv_message.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    /*//[5]把点中条目先从集合里面删除
                    list.remove(position);
                    //[6]通知适配器更新
                    notifyDataSetChanged();*/
                    //跳转发送短信
                    Uri uri = Uri.parse("smsto:"+list.get(position));
                    Intent intent = new Intent(Intent.ACTION_SENDTO,uri);
                    startActivity(intent);
                    /*
                    intent.setType("vnd.android-dir/mms-sms");
                    //intent.setData(Uri.parse("content://mms-sms/conversations/"));//此为号码
                    startActivity(intent);*/

                    //Toast.makeText(getApplicationContext(),"短信",Toast.LENGTH_SHORT).show();
                }
            });

            tv_number.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //跳转打电话
                    String number = list.get(position);
                    // 意图将数据返回
                    Intent data = new Intent();
                    data.setAction(Intent.ACTION_CALL);
                    data.setData(Uri.parse("tel:"+number));
                    startActivity(data);
                }
            });
            return view;
        }
    }
}
