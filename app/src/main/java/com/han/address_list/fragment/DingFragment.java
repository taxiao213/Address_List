package com.han.address_list.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.han.address_list.R;
import com.han.address_list.bean.Room;
import com.han.address_list.utils.CharacterParser;
import com.han.address_list.utils.People;
import com.han.address_list.utils.SortToken;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;
import se.emilsjolander.stickylistheaders.StickyListHeadersListView;

/**
 * Created by aaa on 2017/3/21.
 */

public class DingFragment extends BaseFragment {


    @BindView(R.id.slhlv)
    StickyListHeadersListView slhlv;
    @BindView(R.id.rv_goods_type)
    ListView ll;
    @BindView(R.id.tv_search)
    TextView tvSearch;


    //测试数据用
    private String[] countries;

    private ArrayList<Room> mRooms;
    private ArrayList<com.han.address_list.bean.People> mPeople1;
    private ArrayList<com.han.address_list.bean.People> mPeople2;
    private ArrayList<com.han.address_list.bean.People> mPeople3;
    private ArrayList<com.han.address_list.bean.People> mPeople4;
    private ArrayList<com.han.address_list.bean.People> mPeople5;
    private ArrayList<com.han.address_list.bean.People> mPeople6;
    private ArrayList<com.han.address_list.bean.People> mPeople7;
    //用作计数
    private ArrayList<Integer> list;

    private ArrayList<com.han.address_list.bean.People> mPeople;

    //选中条目的索引值
    public int currentPosition = 0;
    private MyAdapter myAdapter;
    private PopupWindow mpopupWindow;
    private EditText etSearch;

    /**
     * 汉字转换成拼音的类
     */
    private CharacterParser characterParser;

    /**
     * 待定
     */
    private List<People> mAllContactsList;

    @Override
    protected View initView() {
        View inflate = View.inflate(getActivity(), R.layout.fragment_ding, null);
        ButterKnife.bind(this, inflate);


        //初始化数据
        initdata();
        return inflate;
    }


    /**
     * 初始化数据
     */
    private void initdata() {
        //初始化拼音
        characterParser = new CharacterParser();

        //测试数据
        countries = getActivity().getResources().getStringArray(R.array.countries);

        list = new ArrayList<>();

        mRooms = new ArrayList<>();
        Room room;
        for (int i = 1; i < 10; i++) {
            room = new Room("科室" + i, i);
            mRooms.add(room);
        }

        mPeople1 = new ArrayList<>();
        com.han.address_list.bean.People people;
        for (int i = 0; i < 10; i++) {
            people = new com.han.address_list.bean.People("小明" + i, "1823407822" + i, "科室1", 1);
            mPeople1.add(people);
            list.add(i);
        }

        mPeople2 = new ArrayList<>();

        for (int i = 0; i < 9; i++) {
            people = new com.han.address_list.bean.People("话小" + i, "1822512822" + i, "科室2", 2);
            mPeople1.add(people);
            list.add(i);
        }

        mPeople3 = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            people = new com.han.address_list.bean.People("石小" + i, "1822512700" + i, "科室3", 3);
            mPeople1.add(people);
            list.add(i);
        }

        mPeople4 = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            people = new com.han.address_list.bean.People("米小" + i, "1822518880" + i, "科室4", 4);
            mPeople1.add(people);
            list.add(i);
        }

        mPeople5 = new ArrayList<>();

        for (int i = 0; i < 6; i++) {
            people = new com.han.address_list.bean.People("户小" + i, "1822512777" + i, "科室5", 5);
            mPeople1.add(people);
            list.add(i);
        }

        mPeople6 = new ArrayList<>();

        for (int i = 0; i < 7; i++) {
            people = new com.han.address_list.bean.People("雨小" + i, "1822512780" + i, "科室6", 6);
            mPeople1.add(people);
            list.add(i);
        }

        mPeople7 = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            people = new com.han.address_list.bean.People("宋小" + i, "1822518790" + i, "科室7", 7);
            mPeople1.add(people);
            list.add(i);
        }

        //总共的人数
        mPeople = new ArrayList<>();
        for (int i = 0; i < mPeople1.size(); i++) {
            mPeople.add(mPeople1.get(i));
        }

        for (int i = 0; i < mPeople2.size(); i++) {
            mPeople.add(mPeople2.get(i));
        }

        for (int i = 0; i < mPeople3.size(); i++) {
            mPeople.add(mPeople3.get(i));
        }

        for (int i = 0; i < mPeople4.size(); i++) {
            mPeople.add(mPeople4.get(i));
        }

        for (int i = 0; i < mPeople5.size(); i++) {
            mPeople.add(mPeople5.get(i));
        }

        for (int i = 0; i < mPeople6.size(); i++) {
            mPeople.add(mPeople6.get(i));
        }

        for (int i = 0; i < mPeople7.size(); i++) {
            mPeople.add(mPeople7.get(i));
        }


        //左侧列表的适配器
        myAdapter = new MyAdapter();
        ll.setAdapter(myAdapter);

        //右侧列表的数据适配器
        StickyListHeadersAdapter myStick = new sticklistview();
        slhlv.setAdapter(myStick);

        slhlv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                //listview数据适配器滚动  smoothToPosition
                int stickpeople_typeID = mPeople.get(firstVisibleItem).getTypeID();

                for (int i = 0; i < mRooms.size(); i++) {
                    //当typeID一样时，滚动
                    int stickroom_typeID = mRooms.get(i).getTypeID();

                    if (stickpeople_typeID == stickroom_typeID) {

                        //listview设置滑动
                        ll.smoothScrollToPosition(i);
                        //给adapter设置背景色，
                        //当右侧列表滚动时，左侧列表联动，显示背景色
                        myAdapter.setBackgroud(i);

                        myAdapter.notifyDataSetChanged();
                        break;
                    }
                }

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @OnClick(R.id.tv_search)
    public void onClick() {
        tvSearch.setBackground(getResources().getDrawable(R.drawable.ding_search_selector));

        //弹出一个popupwindow
        showPopupWindow();
    }

    /**
     * 显示弹出一个popupwindow
     */
    private void showPopupWindow() {
        View popView = View.inflate(getActivity(), R.layout.ding_search, null);
        etSearch = (EditText) popView.findViewById(R.id.et_search);

        if (mpopupWindow == null) {
            mpopupWindow = new PopupWindow(popView, tvSearch.getWidth(), tvSearch.getHeight(), true);

            //点击popupwindow外部消失
            mpopupWindow.setOutsideTouchable(true);
            mpopupWindow.setBackgroundDrawable(new ColorDrawable());
            mpopupWindow.setBackgroundDrawable(new ColorDrawable());

        }

        //展示popuowindow  参数1 在哪个view下面  轴偏移量
        mpopupWindow.showAsDropDown(tvSearch, 0, -tvSearch.getHeight());

        /*etSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Log.e("edittext", 1111 + "");
                //搜索显示相应的条目
                searchItem();

            }
        });*/
        searchItem();

    }

    /**
     * 搜索显示相应的条目
     */
    private void searchItem() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                String text = s.toString();

                if (text.length() > 0) {

                    searchMore(text);

                    //List<com.han.address_list.bean.People> fileterList = search(text);

                    // TODO: 2017/3/24 注释掉
                    //adapter.updateListView(fileterList);

                    //mAdapter.updateData(mContacts);


                } else {
                    slhlv.setSelection(0);
                }




                /*for (int i = 0; i < mPeople.size(); i++) {
                    String number = mPeople.get(i).getNumber();

                    // TODO: 2017/3/23  
                    //如果包含相应的数字，跳转
                    if (number.contains(text)) {
                        slhlv.setSelection(i);

                    }
                }*/

            }
        });

    }

    /**
     * 查询数据
     * @param text
     */
    private void searchMore(String text) {

        if (text.matches("^([0-9]|[/+]).*")) {// 正则表达式 匹配以数字或者加号开头的字符串(包括了带空格及-分割的号码)
            String simpleStr = text.replaceAll("\\-|\\s", "");

            for (int i = 0; i < mPeople.size(); i++) {
                String number = mPeople.get(i).getNumber();

                // TODO: 2017/3/23
                //如果包含相应的数字，跳转
                if (number.contains(simpleStr)) {
                    slhlv.setSelection(i);
                }
            }

        }else{

            Log.e("text===",text);

            for (int i = 0; i < mPeople.size(); i++) {
                com.han.address_list.bean.People contact = mPeople.get(i);

                String name = contact.getName();
                Log.e("name===",name);


                // TODO: 2017/3/24 转换拼接有问题
                //将名字转成拼音
                String sortLetter = getSortLetter(name);

                Log.e("sortLetter===",sortLetter);


                //如果包含相应的字母，跳转
                if (sortLetter.contains(text)) {

                    slhlv.setSelection(i);
                }

               /* //姓名全匹配,姓名首字母简拼匹配,姓名全字母匹配
                boolean isNameContains = contact.getName().toLowerCase(Locale.CHINESE)
                        .contains(text.toLowerCase(Locale.CHINESE));

                //boolean isSortKeyContains = contact.sortKey.toLowerCase(Locale.CHINESE).replace(" ", "")
                //        .contains(text.toLowerCase(Locale.CHINESE));

                boolean isSimpleSpellContains = contact.sortToken.simpleSpell.toLowerCase(Locale.CHINESE)
                        .contains(text.toLowerCase(Locale.CHINESE));

                boolean isWholeSpellContains = contact.sortToken.wholeSpell.toLowerCase(Locale.CHINESE)
                        .contains(text.toLowerCase(Locale.CHINESE));

                if (isNameContains  || isSimpleSpellContains || isWholeSpellContains) {

                    slhlv.setSelection(i);

                    Log.e("position===",i+"");
                }*/


            }

        }


    }

    /**
     * 模糊查询
     *
     * @param str
     * @return
     */
    private List<com.han.address_list.bean.People> search(String str) {

        List<com.han.address_list.bean.People> filterList = new ArrayList<>();// 过滤后的list
        //if (str.matches("^([0-9]|[/+])*$")) {// 正则表达式 匹配号码
        if (str.matches("^([0-9]|[/+]).*")) {// 正则表达式 匹配以数字或者加号开头的字符串(包括了带空格及-分割的号码)
            String simpleStr = str.replaceAll("\\-|\\s", "");
            for (com.han.address_list.bean.People contact : mPeople) {
                if (contact.getNumber() != null && contact.getName() != null) {
                    if (contact.getNumber().contains(simpleStr) || contact.getName().contains(str)) {
                        if (!filterList.contains(contact)) {
                            filterList.add(contact);
                        }
                    }
                }
            }
        } else {
            for (com.han.address_list.bean.People contact : mPeople) {
                if (contact.getNumber() != null && contact.getName() != null) {
                    //姓名全匹配,姓名首字母简拼匹配,姓名全字母匹配
                    boolean isNameContains = contact.getName().toLowerCase(Locale.CHINESE)
                            .contains(str.toLowerCase(Locale.CHINESE));

                    boolean isSortKeyContains = contact.sortKey.toLowerCase(Locale.CHINESE).replace(" ", "")
                            .contains(str.toLowerCase(Locale.CHINESE));

                    boolean isSimpleSpellContains = contact.sortToken.simpleSpell.toLowerCase(Locale.CHINESE)
                            .contains(str.toLowerCase(Locale.CHINESE));

                    boolean isWholeSpellContains = contact.sortToken.wholeSpell.toLowerCase(Locale.CHINESE)
                            .contains(str.toLowerCase(Locale.CHINESE));

                    if (isNameContains || isSortKeyContains || isSimpleSpellContains || isWholeSpellContains) {
                        if (!filterList.contains(contact)) {
                            filterList.add(contact);
                        }
                    }
                }
            }
        }
        return filterList;
    }

    /**
     * 名字转拼音,取首字母
     *
     * @param name
     * @return
     */
    private String getSortLetter(String name) {
        String letter = "#";
        if (name == null) {
            return letter;
        }

        //汉字转换成拼音
        String pinyin = characterParser.getSelling(name);



        /*

        String sortString = pinyin.substring(0, 1).toUpperCase(Locale.CHINESE);

        Log.e("sortString====",sortString);

        // 正则表达式，判断首字母是否是英文字母
        if (sortString.matches("[A-Z]")) {
            letter = sortString.toUpperCase(Locale.CHINESE);
        return letter;
        }*/
        return pinyin;
    }

    /**
     * 取sort_key的首字母
     *
     * @param sortKey
     * @return
     */
    private String getSortLetterBySortKey(String sortKey) {
        if (sortKey == null || "".equals(sortKey.trim())) {
            return null;
        }
        String letter = "#";
        //汉字转换成拼音
        String sortString = sortKey.trim().substring(0, 1).toUpperCase(Locale.CHINESE);
        // 正则表达式，判断首字母是否是英文字母
        if (sortString.matches("[A-Z]")) {
            letter = sortString.toUpperCase(Locale.CHINESE);
        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {// 5.0以上需要判断汉字
            if (sortString.matches("^[\u4E00-\u9FFF]+$"))// 正则表达式，判断是否为汉字
                letter = getSortLetter(sortString.toUpperCase(Locale.CHINESE));
        }
        return letter;
    }


    /**
     * 中文字符串匹配
     */
    String chReg = "[\\u4E00-\\u9FA5]+";

    //String chReg="[^\\u4E00-\\u9FA5]";//除中文外的字符匹配

    /**
     * 解析sort_key,封装简拼,全拼
     *
     * @param sortKey
     * @return
     */
    public SortToken parseSortKey(String sortKey) {
        SortToken token = new SortToken();
        if (sortKey != null && sortKey.length() > 0) {
            //其中包含的中文字符
            String[] enStrs = sortKey.replace(" ", "").split(chReg);
            for (int i = 0, length = enStrs.length; i < length; i++) {
                if (enStrs[i].length() > 0) {
                    //拼接简拼
                    token.simpleSpell += enStrs[i].charAt(0);
                    token.wholeSpell += enStrs[i];
                }
            }
        }
        return token;
    }

    /**
     * 解析sort_key,封装简拼,全拼。
     * Android 5.0 以上使用
     *
     * @param sortKey
     * @return
     */
    public SortToken parseSortKeyLollipop(String sortKey) {
        SortToken token = new SortToken();
        if (sortKey != null && sortKey.length() > 0) {
            boolean isChinese = sortKey.matches(chReg);
            // 分割条件：中文不分割，英文以大写和空格分割
            String regularExpression = isChinese ? "" : "(?=[A-Z])|\\s";

            String[] enStrs = sortKey.split(regularExpression);

            for (int i = 0, length = enStrs.length; i < length; i++)
                if (enStrs[i].length() > 0) {
                    //拼接简拼
                    token.simpleSpell += getSortLetter(String.valueOf(enStrs[i].charAt(0)));
                    token.wholeSpell += characterParser.getSelling(enStrs[i]);
                }
        }
        return token;
    }


    /**
     * ListView的适配
     */
    public class MyAdapter extends BaseAdapter {

        ViewHolder holder;

        @Override
        public int getCount() {
            return mRooms.size();
        }

        @Override
        public String getItem(int position) {
            return mRooms.get(position).getName();
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
                    //点击条目更换背景色，记住当前的位置
                    currentPosition = position;
                    notifyDataSetChanged();
                    int room_typeID = mRooms.get(position).getTypeID();

                    for (int i = 0; i < mPeople.size(); i++) {
                        int people_typeID = mPeople.get(i).getTypeID();
                        if (room_typeID == people_typeID) {
                            //联动右侧的列表
                            slhlv.setSelection(i);
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


    public class sticklistview extends BaseAdapter implements StickyListHeadersAdapter {

        @Override
        public View getHeaderView(int position, View convertView, ViewGroup parent) {
            TextView textView = (TextView) View.inflate(getActivity(), R.layout.item_type_header, null);
            textView.setText(mPeople.get(position).getRoom());
            // textView.setText("" + countries[position].subSequence(0, 1).charAt(0));
            // TODO: 2017/3/23
            return textView;

        }

        @Override
        public long getHeaderId(int position) {
            return mPeople.get(position).getTypeID();
            //            return countries[position].subSequence(0, 1).charAt(0);
            // TODO: 2017/3/23  
        }

        @Override
        public int getCount() {
            //            return countries.length;
            return mPeople.size();
            // TODO: 2017/3/23  
        }

        @Override
        public com.han.address_list.bean.People getItem(int position) {
            return mPeople.get(position);
            //            return countries[position];
            // TODO: 2017/3/23  
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
            //            holder.name.setText(countries[position]);
            // TODO: 2017/3/23

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
