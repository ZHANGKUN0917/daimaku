package com.example.hp.jinritoutiao.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.bwie.myxlistview.XListView;
import com.example.hp.jinritoutiao.R;
import com.example.hp.jinritoutiao.bean.DataBean;
import com.example.hp.jinritoutiao.helper.Helper;
import com.example.imagelibrary.utils;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class shoucang extends AppCompatActivity {
    private XListView xlv;
    private TextView tv;
    private SQLiteDatabase database;

    private ImageView iv;
    private int type1=0;
    private int type2=1;
    private int type3=2;
    private int type4=3;

    private DataBean dataBean;
    private List<DataBean> list;
    private Context context;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shoucang);
        View view=View.inflate(this,R.layout.list,null);
        tv= ((TextView) findViewById(R.id.tv));
        iv= ((ImageView) findViewById(R.id.iv));
        list=new ArrayList<>();
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(shoucang.this,MainActivity.class);
                startActivity(it);
            }
        });
        Helper helper=new Helper(this);
        database = helper.getWritableDatabase();

        context=this;
        SwipeMenuCreator creator = new SwipeMenuCreator() {

            @Override
            public void create(SwipeMenu menu) {

                SwipeMenuItem openItem = new SwipeMenuItem(context);
                openItem.setBackground(new ColorDrawable(Color.GREEN));
                openItem.setWidth(dp2px(90));
                openItem.setTitle("打开");
                openItem.setTitleSize(20);
                openItem.setTitleColor(Color.WHITE);
                menu.addMenuItem(openItem);

                SwipeMenuItem deleteItem = new SwipeMenuItem(context);
                deleteItem.setBackground(new ColorDrawable(Color.LTGRAY));
                deleteItem.setWidth(dp2px(90));
                deleteItem.setIcon(android.R.drawable.ic_delete);
                menu.addMenuItem(deleteItem);
            }
        };

        SwipeMenuListView listView = (SwipeMenuListView) findViewById(R.id.listView);
        listView.setMenuCreator(creator);
        final MyBase base=new MyBase();
        listView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu,int index) {
                //index的值就是在SwipeMenu依次添加SwipeMenuItem顺序值，类似数组的下标。
                //从0开始，依次是：0、1、2、3...
                switch (index) {
                    case 0:
                        Toast.makeText(context, "打开:"+position,Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(context, "删除:"+position,Toast.LENGTH_SHORT).show();
                        String sql="delete from xinwens where id="+id;
                        database.execSQL(sql);
                        list.remove(list.get(position));
                        base.notifyDataSetChanged();
                        break;
                }
                // false : 当用户触发其他地方的屏幕时候，自动收起菜单。
                // true : 不改变已经打开菜单的样式，保持原样不收起。
                return false;
            }
        });
        // 监测用户在ListView的SwipeMenu侧滑事件。
        listView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {

            @Override
            public void onSwipeStart(int pos) {
                Log.d("位置:" + pos, "开始侧滑...");
            }

            @Override
            public void onSwipeEnd(int pos) {
                Log.d("位置:" + pos, "侧滑结束.");
            }
        });

        String sql="select id,path from xinwens";
        Cursor cursor = database.rawQuery(sql, null);
        while(cursor.moveToNext()){
            String path=cursor.getString(cursor.getColumnIndex("path"));
            id = cursor.getInt(cursor.getColumnIndex("id"));
            Log.i("xxx",path);
            Gson gson=new Gson();
            dataBean = gson.fromJson(path, DataBean.class);
            list.add(dataBean);
        }
        listView.setAdapter(base);
    }
    public int dp2px(float dipValue) {
        final float scale = this.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
       }

    class MyBase extends BaseAdapter{

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int i) {
            return list.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            final ViewHolder vh;
            int type=getItemViewType(i);
            if(view==null) {
                vh = new ViewHolder();
                switch (type){
                    case 0:
                        view=View.inflate(shoucang.this,R.layout.vieww,null);
                        vh.tv=view.findViewById(R.id.tv);
                        vh.tv1=view.findViewById(R.id.tv2);
                        vh.text=view.findViewById(R.id.tvtv);
                        break;
                    case 1:
                        view=View.inflate(shoucang.this,R.layout.vieww2,null);
                        vh.tv2=view.findViewById(R.id.tv);
                        vh.tv3=view.findViewById(R.id.tv2);
                        vh.iv1=view.findViewById(R.id.ivv);
                        vh.iv2=view.findViewById(R.id.ivv1);
                        vh.iv3=view.findViewById(R.id.ivv2);
                        vh.text=view.findViewById(R.id.tvtv);
                        break;
                    case 2:
                        view=View.inflate(shoucang.this,R.layout.vieww3,null);
                        vh.tv4=view.findViewById(R.id.tv1);
                        vh.iv4=view.findViewById(R.id.iv1);
                        vh.tv5=view.findViewById(R.id.tv2);
                        vh.text=view.findViewById(R.id.tvtv);
                        break;
                    case 3:
                        view=View.inflate(shoucang.this,R.layout.vieww1,null);
                        vh.tv4=view.findViewById(R.id.tv1);
                        vh.iv4=view.findViewById(R.id.iv1);
                        vh.tv5=view.findViewById(R.id.tv2);
                        vh.text=view.findViewById(R.id.tvtv);
                        break;
                }
                view.setTag(vh);
            }else{
                vh= (ViewHolder) view.getTag();
            }
            DisplayImageOptions options = utils.getOptions();
            switch (type){
                case 0:
                    vh.tv.setText(list.get(i).getTitle());
                    vh.tv.setText(list.get(i).getSource());
                    break;
                case 1:
                    vh.tv2.setText(list.get(i).getTitle());
                    vh.tv3.setText(list.get(i).getSource());
                    ImageLoader.getInstance().displayImage(list.get(i).getImage_list().get(0).getUrl(),vh.iv1,options);
                    ImageLoader.getInstance().displayImage(list.get(i).getImage_list().get(1).getUrl(),vh.iv2,options);
                    ImageLoader.getInstance().displayImage(list.get(i).getImage_list().get(2).getUrl(),vh.iv3,options);
                    break;
                case 2:
                    vh.tv4.setText(list.get(i).getTitle());
                    vh.tv5.setText(list.get(i).getSource());
                    //ImageLoader.getInstance().displayImage(data.get(i).getVideo_detail_info().getDetail_video_large_image().getUrl(),vh.iv4,options);
                    break;
                case 3:
                    vh.tv4.setText(list.get(i).getTitle());
                    vh.tv5.setText(list.get(i).getSource());
                    ImageLoader.getInstance().displayImage(list.get(i).getVideo_detail_info().getDetail_video_large_image().getUrl(),vh.iv4,options);

            }
            return view;
        }

        @Override
        public int getViewTypeCount() {
            return 4;
        }

        @Override
        public int getItemViewType(int position) {
            if(list.get(position).getVideo_detail_info()==null){
                if(list.get(position).getImage_list()==null)
                {
                    return type1;
                }
                else if(list.get(position).getImage_list().size()==3)
                {
                    return type2;
                }else
                {
                    return type3;
                }
            }else
            {
                return type4;
            }
        }
    }
    class ViewHolder{
        TextView tv,tv1,tv2,tv3,tv4,tv5,text;
        ImageView iv1,iv2,iv3,iv4;
    }

}





