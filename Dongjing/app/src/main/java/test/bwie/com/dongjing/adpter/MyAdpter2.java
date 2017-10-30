package test.bwie.com.dongjing.adpter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import test.bwie.com.dongjing.R;
import test.bwie.com.dongjing.activity.SanjiActivity;
import test.bwie.com.dongjing.bean.FenLei1;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/10/18 09 25
 */

public class MyAdpter2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<FenLei1.DataBean.ListBean> list;

    public MyAdpter2(Context context, List<FenLei1.DataBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder rv;
        View view= LayoutInflater.from(context).inflate(R.layout.recycle2item,parent,false);
        rv=new TwoHolder(view);
        return rv;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final int pcid = list.get(position).getPcid();
        final int pscid = list.get(position).getPscid();
        //lll.listener(position,pcid,pscid);
        if(holder instanceof TwoHolder){
            Log.i("123", list.size()+"");
            ((TwoHolder)holder).tv.setText(list.get(position).getName());
            ((TwoHolder) holder).tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent it=new Intent(context, SanjiActivity.class);
                    it.putExtra("position",position);
                    it.putExtra("pcid",pcid);
                    it.putExtra("pscid",pscid);
                    context.startActivity(it);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    class TwoHolder extends RecyclerView.ViewHolder {
        TextView tv;

        public TwoHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }
    /*public interface listener{
        void listener(int position,int pcid,int pscid);
    }
    public listener lll;

    public void setLll(listener lll) {
        this.lll = lll;
    }*/
}
