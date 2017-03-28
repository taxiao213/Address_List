package com.han.address_list.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.han.address_list.R;
import com.han.address_list.bean.Content;
import com.han.address_list.bean.People;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * Created by aaa on 2017/3/24.
 */

public class MainFragment extends Fragment {

    @BindView(R.id.mess_search)
    TextView messSearch;
    @BindView(R.id.mess_slhlv)
    StickyListHeadersListView shl;
    private ArrayList<People> mPeople;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(container.getContext(), R.layout.item_main, null);
        ButterKnife.bind(this, view);
        initdata();
        return view;

    }


    /**
     * 填充数据
     */
    private void initdata() {
        Content content = new Content();
        mPeople = content.getPeople();

        //右侧列表的数据适配器
        StickyListHeadersAdapter myStick = new sticklistview();
        shl.setAdapter(myStick);

    }

    @OnClick(R.id.mess_search)
    public void onClick() {

    }

    public class sticklistview extends BaseAdapter implements StickyListHeadersAdapter {

        @Override
        public View getHeaderView(int position, View convertView, ViewGroup parent) {
            TextView textView = (TextView) View.inflate(getActivity(), R.layout.item_type_header, null);
            textView.setText(mPeople.get(position).getRoom());
            return textView;

        }

        @Override
        public long getHeaderId(int position) {
            return mPeople.get(position).getTypeID();
        }

        @Override
        public int getCount() {
            return mPeople.size();
        }

        @Override
        public People getItem(int position) {
            return mPeople.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final MyViewHolder holder;
            if (convertView == null) {
                holder = new MyViewHolder();
                convertView = View.inflate(getActivity(), R.layout.ding_right, null);
                holder.name = (TextView) convertView.findViewById(R.id.tv_name);
                holder.number = (ImageView) convertView.findViewById(R.id.tv_number);
                holder.message = (ImageView) convertView.findViewById(R.id.tv_message);
                convertView.setTag(holder);

            } else {
                holder = (MyViewHolder) convertView.getTag();
            }
            String name = getItem(position).getName();
            String number = getItem(position).getNumber();
            holder.name.setText(name + "    " + number);

            holder.number.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //打电话
                    //跳转打电话
                    String number = mPeople.get(position).getNumber();
                    // 意图将数据返回
                    Intent data = new Intent();
                    data.setAction(Intent.ACTION_CALL);
                    data.setData(Uri.parse("tel:" + number));
                    startActivity(data);

                }
            });

            holder.message.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //跳转发送短信
                    Uri uri = Uri.parse("smsto:" + mPeople.get(position).getNumber());
                    Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                    startActivity(intent);

                }
            });
            return convertView;

        }


    }

    static class MyViewHolder {
        TextView name;
        ImageView number;
        ImageView message;
        private int position;

        public void setPosition(int position) {
            this.position = position;
        }
    }
}
