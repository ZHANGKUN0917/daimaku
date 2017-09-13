package com.example.hp.jinritoutiao.fragment;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bwie.myxlistview.XListView;
import com.example.hp.jinritoutiao.R;
import com.example.hp.jinritoutiao.activity.MainActivity;
import com.example.hp.jinritoutiao.activity.VideoActivity;
import com.example.hp.jinritoutiao.bean.Bean;
import com.example.hp.jinritoutiao.bean.DataBean;
import com.example.hp.jinritoutiao.bean.Source;
import com.example.hp.jinritoutiao.helper.Helper;
import com.example.imagelibrary.utils;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/8/10 09 36
 */

public class Fragmenti extends Fragment{


    private XListView xlv;

    private Handler handler=new Handler();
    private DisplayImageOptions options;
    private Source source;

    private int type1=0;
    private int type2=1;
    private int type3=2;
    private int type4=3;
    private List<DataBean> data;
    private View view;
    private ViewParent group;
    private View viewww;
    private Button b;
    private Button b1;
    private SQLiteDatabase database;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       if(view==null){
           view = inflater.inflate(R.layout.fragment8, null);
       }
        group = view.getParent();
        if(group!=null){
             container.removeView(view);
        }


        xlv = view.findViewById(R.id.xlv);
        if(getArguments()!=null){
            source = (Source) getArguments().getSerializable("source");
            Log.e("tiet",source.getName());
        }
        xlv.setPullRefreshEnable(true);
        xlv.setPullLoadEnable(true);
        xlv.setXListViewListener(new XListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getdata();
                        xlv.stopRefresh();
                    }
                },2000);
            }

            @Override
            public void onLoadMore() {
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        getdata();
                        xlv.stopLoadMore();
                    }
                },2000);
            }
        });
        return view;
    }

   @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        Log.i("taggg","我进来了");
        getdata();
       Helper helper=new Helper(getActivity());
       database = helper.getWritableDatabase();


   }
    //获取数据
    private void getdata() {
        String path=source.getPath();
        MyAstync myAstync=new MyAstync();
        myAstync.execute(path);
    }
    //异步
    private class MyAstync extends AsyncTask<String,Integer,String> {
        @Override
        protected String doInBackground(String... strings) {
            try {
                URL uri=new URL(strings[0]);
                HttpURLConnection httpURLConnection= (HttpURLConnection) uri.openConnection();
                httpURLConnection.setConnectTimeout(3000);
                httpURLConnection.setRequestMethod("GET");
                if(httpURLConnection.getResponseCode()==200){
                    InputStream stream = httpURLConnection.getInputStream();
                    ByteArrayOutputStream bos=new ByteArrayOutputStream();
                    byte[] b=new byte[1024];
                    int len;
                    while((len=stream.read(b))!=-1){
                        bos.write(b,0,len);
                    }
                    return bos.toString();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Gson gson=new Gson();
            Bean bean = gson.fromJson(s, Bean.class);
            data = bean.getData();


            xlv.setAdapter(new MyBase());
            xlv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent it=new Intent(getActivity(), VideoActivity.class);
                    it.putExtra("path",data.get(i).getShare_url());
                    startActivity(it);
                }
            });

        }
    }
    class MyBase extends BaseAdapter{

        private View button1;
        private View button2;

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int i) {
            return data.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            final ViewHolder vh;
            int type=getItemViewType(i);
            if(view==null) {
                vh = new ViewHolder();
                switch (type){
                    case 0:
                        view=View.inflate(getActivity(),R.layout.vieww,null);
                        vh.tv=view.findViewById(R.id.tv);
                        vh.tv1=view.findViewById(R.id.tv2);
                        vh.text=view.findViewById(R.id.tvtv);
                        break;
                    case 1:
                        view=View.inflate(getActivity(),R.layout.vieww2,null);
                        vh.tv2=view.findViewById(R.id.tv);
                        vh.tv3=view.findViewById(R.id.tv2);
                        vh.iv1=view.findViewById(R.id.ivv);
                        vh.iv2=view.findViewById(R.id.ivv1);
                        vh.iv3=view.findViewById(R.id.ivv2);
                        vh.text=view.findViewById(R.id.tvtv);
                        break;
                    case 2:
                        view=View.inflate(getActivity(),R.layout.vieww3,null);
                        vh.tv4=view.findViewById(R.id.tv1);
                        vh.iv4=view.findViewById(R.id.iv1);
                        vh.tv5=view.findViewById(R.id.tv2);
                        vh.text=view.findViewById(R.id.tvtv);
                        break;
                    case 3:
                        view=View.inflate(getActivity(),R.layout.vieww1,null);
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
            viewww = View.inflate(getActivity(), R.layout.popwindow,null);
            vh.text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    PopupWindow window=new PopupWindow(viewww,ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
                    window.setFocusable(true);
                    window.setTouchable(true);
                    window.setBackgroundDrawable(new ColorDrawable(Color.BLUE));
                    window.showAsDropDown(vh.text);
                }
            });
            button1 =viewww.findViewById(R.id.button1);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Gson gson = new Gson();
                    String s = gson.toJson(data.get(i-1));
                    Log.i("xxx",s);
                    String sqll="insert into xinwens(path) values('"+s+"')";
                    database.execSQL(sqll);
                }
            });
            button2 = viewww.findViewById(R.id.button2);
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    MainActivity activity= (MainActivity) getActivity();
                    activity.share(data.get(i).getTitle(),data.get(i).getShare_url());
                }
            });

            DisplayImageOptions options = utils.getOptions();
            switch (type){
                case 0:
                    vh.tv.setText(data.get(i).getTitle());
                    vh.tv.setText(data.get(i).getSource());
                    break;
                case 1:
                    vh.tv2.setText(data.get(i).getTitle());
                    vh.tv3.setText(data.get(i).getSource());
                    ImageLoader.getInstance().displayImage(data.get(i).getImage_list().get(0).getUrl(),vh.iv1,options);
                    ImageLoader.getInstance().displayImage(data.get(i).getImage_list().get(1).getUrl(),vh.iv2,options);
                    ImageLoader.getInstance().displayImage(data.get(i).getImage_list().get(2).getUrl(),vh.iv3,options);
                    break;
                case 2:
                    vh.tv4.setText(data.get(i).getTitle());
                    vh.tv5.setText(data.get(i).getSource());

                    //ImageLoader.getInstance().displayImage(data.get(i).getVideo_detail_info().getDetail_video_large_image().getUrl(),vh.iv4,options);
                    break;
                case 3:
                    vh.tv4.setText(data.get(i).getTitle());
                    vh.tv5.setText(data.get(i).getSource());
                    ImageLoader.getInstance().displayImage(data.get(i).getVideo_detail_info().getDetail_video_large_image().getUrl(),vh.iv4,options);

            }
            return view;
        }

        @Override
        public int getItemViewType(int position) {
                if(data.get(position).getVideo_detail_info()==null){
                    if(data.get(position).getImage_list()==null)
                    {
                        return type1;
                    }
                    else if(data.get(position).getImage_list().size()==3)
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
        @Override
        public int getViewTypeCount() {
            return 4;
        }
    }
    class ViewHolder{
        TextView tv,tv1,tv2,tv3,tv4,tv5,text;
        ImageView iv1,iv2,iv3,iv4;
    }
    public static Fragment newInstance(Source source){
        Fragmenti fragment1 = new Fragmenti();
        Bundle bundle = new Bundle();
        bundle.putSerializable("source",source);
        fragment1.setArguments(bundle);
        return fragment1;
    }


}
