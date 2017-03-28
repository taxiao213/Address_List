package com.han.address_list.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.han.address_list.R;
import com.han.address_list.bean.Content;
import com.han.address_list.bean.People;
import com.han.address_list.bean.Room;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by aaa on 2017/3/24.
 */

public class MenuFragment extends Fragment {
    @BindView(R.id.mess_ll)
    ListView ll;
    private ArrayList<Room> mroom;
    //选中条目的索引值
    public int currentPosition = 0;
    private ArrayList<People> mpeople;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(container.getContext(), R.layout.item_menu, null);
        ButterKnife.bind(this, view);
        initdata();
        return view;

    }



    private void initdata() {
        Content content = new Content();
        mroom = content.getRoom();
        mpeople = content.getPeople();

        ll.setAdapter(new MyAdapter());
    }

    /**
     * ListView的适配
     */
    public class MyAdapter extends BaseAdapter {

        ViewHolder holder;

        @Override
        public int getCount() {
            return mroom.size();
        }

        @Override
        public String getItem(int position) {
            return mroom.get(position).getName();
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                holder = new ViewHolder();
                convertView = View.inflate(getActivity(), R.layout.item_left, null);
                holder.room = (TextView) convertView.findViewById(R.id.room);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            //设置背景色
            if (position == currentPosition) {
                holder.room.setBackgroundColor(Color.WHITE);
            } else {
                holder.room.setBackgroundColor(Color.GRAY);

            }

            holder.setPosition(position);

            holder.room.setText(getItem(position));


            holder.room.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "h" + position, Toast.LENGTH_SHORT).show();
                    //点击条目更换背景色，记住当前的位置
                    currentPosition = position;
                    notifyDataSetChanged();
                    int room_typeID = mroom.get(position).getTypeID();

                    for (int i = 0; i < mpeople.size(); i++) {
                        int people_typeID = mpeople.get(i).getTypeID();
                        if (room_typeID == people_typeID) {
                            //联动右侧的列表
                            //messSlhlv.setSelection(i);
                            break;
                        }
                    }

                }
            });
            return convertView;
        }

        /**
         * 当右侧列表滚动时，左侧列表联动，显示背景色
         *
         * @param i
         */
        public void setBackgroud(int i) {

            currentPosition = i;

        }
    }

    static class ViewHolder {
        TextView room;
        private int position;

        public void setPosition(int position) {
            this.position = position;
        }
    }


}
