package test.bwie.com.dongjing.adpter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import test.bwie.com.dongjing.R;
import test.bwie.com.dongjing.bean.FenLei1;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/10/17 22 16
 */

public class MyAdpter1 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private FenLei1 fl1;
    private List<FenLei1.DataBean.ListBean> list;

    public MyAdpter1(Context context, FenLei1 fl1) {
        this.context = context;
        this.fl1 = fl1;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder rv;
        View view=LayoutInflater.from(context).inflate(R.layout.recycle1item,parent,false);
        rv=new OneHolder(view);
        return rv;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof OneHolder){
            list = fl1.getData().get(position).getList();
            ((OneHolder) holder).tv.setText(fl1.getData().get(position).getName());
            MyAdpter2 adpter2=new MyAdpter2(context,list);
            ((OneHolder) holder).rcy.setAdapter(adpter2);
        }
    }
    @Override
    public int getItemCount(){
        Log.i("1234",fl1.getData().size()+"");
        return fl1.getData().size();
    }
    class OneHolder extends RecyclerView.ViewHolder{
        TextView tv;
        RecyclerView rcy;

        public OneHolder(android.view.View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.tv);
            rcy=itemView.findViewById(R.id.rcy);
            rcy.setLayoutManager(new GridLayoutManager(context,3));
        }
    }

}
