package com.example.addgroup;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    // 可滑动的显示选中用户的View
    private LinearLayout menuLinerLayout;

    private ListView listView;
    private List<User> allUserList;
    private EditText editText;
    private TextView tv_checked;
    private ImageView iv_search;
    private ListAdapter adapter;
    private List<String> addList = new ArrayList<String>();
    private int total = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
    }

    private void initData() {
        User angelbaby = new User("0",R.mipmap.a1,0);
        User tangyan = new User("1",R.mipmap.a2,0);
        User zhaoliying = new User("2",R.mipmap.a3,0);
        User gaoyuanyuan = new User("3",R.mipmap.a4,0);
        User cat = new User("4",R.mipmap.a5,0);
        User cat1 = new User("5",R.mipmap.a5,0);
        User cat2 = new User("6",R.mipmap.a5,0);
        User cat3 = new User("7",R.mipmap.a5,0);
        User cat4 = new User("8",R.mipmap.a5,0);
        User cat5 = new User("9",R.mipmap.a5,0);
        User cat6 = new User("10",R.mipmap.a5,0);
        User cat7 = new User("11",R.mipmap.a5,0);
        User cat8 = new User("12",R.mipmap.a5,0);
        User cat9 = new User("13",R.mipmap.a5,0);
        User cat10 = new User("14",R.mipmap.a5,0);
        User cat11 = new User("15",R.mipmap.a5,0);
        User cat12 = new User("16",R.mipmap.a5,0);


        allUserList = new ArrayList<User>();
        allUserList.add(angelbaby);
        allUserList.add(tangyan);
        allUserList.add(zhaoliying);
        allUserList.add(gaoyuanyuan);
        allUserList.add(cat);
        allUserList.add(cat1);
        allUserList.add(cat2);
        allUserList.add(cat3);
        allUserList.add(cat4);
        allUserList.add(cat5);
        allUserList.add(cat6);
        allUserList.add(cat7);
        allUserList.add(cat8);
        allUserList.add(cat9);
        allUserList.add(cat10);
        allUserList.add(cat11);
        allUserList.add(cat12);

        adapter = new ListAdapter(MainActivity.this,allUserList);

        listView.setAdapter(adapter);

        //搜索栏搜索
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 0) {
                    String str_s = editText.getText().toString().trim();
                    List<User> user_temp = new ArrayList<User>();
                    for (User user : allUserList) {
                        String uesrname = user.getName();
                        if (uesrname.contains(str_s)) {
                            user_temp.add(user);
                        }
                        adapter = new ListAdapter(MainActivity.this, user_temp);
                        listView.setAdapter(adapter);
                    }
                } else {
                    adapter = new ListAdapter(MainActivity.this, allUserList);
                    listView.setAdapter(adapter);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initView() {
        listView = (ListView) findViewById(R.id.list);
        menuLinerLayout = (LinearLayout) findViewById(R.id.linearLayoutMenu);
        editText = (EditText) findViewById(R.id.et_search);
        tv_checked = (TextView) findViewById(R.id.tv_checked);
        iv_search = (ImageView) findViewById(R.id.iv_search);

    }

    //显示选择的头像
    private void showCheckImage(Bitmap bitmap, User glufineid) {
        total++;
        // 包含TextView的LinearLayout
        // 参数设置
        android.widget.LinearLayout.LayoutParams menuLinerLayoutParames = new LinearLayout.LayoutParams(
                75, 75, 1);
        View view = LayoutInflater.from(this).inflate(
                R.layout.header_item, null);
        ImageView images = (ImageView) view.findViewById(R.id.iv_avatar);
        menuLinerLayoutParames.setMargins(6, 6, 6, 6);

        // 设置id，方便后面删除
        view.setTag(glufineid);
        if (bitmap == null) {
            images.setImageResource(R.mipmap.default_useravatar);
        } else {
            images.setImageBitmap(bitmap);
        }

        menuLinerLayout.addView(view, menuLinerLayoutParames);
        tv_checked.setText("确定(" + total + ")");
        if (total > 0) {
            if (iv_search.getVisibility() == View.VISIBLE) {
                iv_search.setVisibility(View.GONE);
            }
        }
        addList.add(glufineid.getName());
    }

    //删除选择的头像
    private void deleteImage(User glufineid) {
        View view = (View) menuLinerLayout.findViewWithTag(glufineid);

        menuLinerLayout.removeView(view);
        total--;
        tv_checked.setText("确定(" + total + ")");
        addList.remove(glufineid.getName());
        if (total < 1) {
            if (iv_search.getVisibility() == View.GONE) {
                iv_search.setVisibility(View.VISIBLE);
            }
            tv_checked.setText("确定");
        }
    }

    /**
     * adapter
     */
    private class ListAdapter extends BaseAdapter {

        private LayoutInflater layoutInflater;
        private Context context;
        private List<User> list = null;


        public ListAdapter(Context context,List<User> list){
            this.context = context;
            this.list = list;
        }

        @Override
        public int getCount() {
            return this.list.size();
        }

        @Override
        public User getItem(int position) {
            return (User) list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            final int index = position;
            User user = getItem(position);
             ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = LayoutInflater.from(context).inflate(R.layout.listitem,null);
                viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageview);
                viewHolder.textView = (TextView) convertView.findViewById(R.id.textview);
                viewHolder.checkBox = (CheckBox) convertView.findViewById(R.id.checkbox);
                viewHolder.layout = (LinearLayout) convertView.findViewById(R.id.layout);
                convertView.setTag(viewHolder);
            }else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            final ImageView imageView = (ImageView) convertView.findViewById(R.id.imageview);
            viewHolder.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list.get(index).type == User.TYPE_CHECKED){
                        list.get(index).type = User.TYPE_NOCHECKED;
                        deleteImage(list.get(index));
                    } else {
                        (list.get(index)).type = User.TYPE_CHECKED;
                        Bitmap bitmap = null;
                        bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                        showCheckImage(bitmap, list.get(index));
                    }
                }
            });

            final ViewHolder finalViewHolder = viewHolder;
            viewHolder.layout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (list.get(index).type == User.TYPE_CHECKED){
                        list.get(index).type = User.TYPE_NOCHECKED;
                        deleteImage(list.get(index));
                        finalViewHolder.checkBox.setChecked(false);
                    } else {
                        (list.get(index)).type = User.TYPE_CHECKED;
                        Bitmap bitmap = null;
                        bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                        showCheckImage(bitmap, list.get(index));
                        finalViewHolder.checkBox.setChecked(true);
                    }
                }
            });
            if(list.get(index).type == User.TYPE_CHECKED){
                viewHolder.checkBox.setChecked(true);
            }else{
                viewHolder.checkBox.setChecked(false);
            }
            viewHolder.imageView.setImageResource(user.getHeader());
            viewHolder.textView.setText(user.getName());
            return convertView;
        }
    }
    private class ViewHolder {
        ImageView imageView;
        TextView textView;
        CheckBox checkBox;
        LinearLayout layout;
    }
}
