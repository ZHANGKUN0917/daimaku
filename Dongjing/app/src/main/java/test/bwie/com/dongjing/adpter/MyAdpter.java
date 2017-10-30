package test.bwie.com.dongjing.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import test.bwie.com.dongjing.R;
import test.bwie.com.dongjing.bean.FenLei;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/10/17 20 29
 */

public class MyAdpter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private FenLei fenLei;
    private List<FenLei.DataBean> data;

    public MyAdpter(Context context, FenLei fenLei) {
        this.context = context;
        this.fenLei = fenLei;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder rv;
        View view= LayoutInflater.from(context).inflate(R.layout.recycleitem,parent,false);
        rv=new OneViewholder(view);
        return rv;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        data = fenLei.getData();
        final int cid = data.get(position).getCid();
        View view=holder.itemView;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll.list(position,cid);
            }
        });
        if(holder instanceof OneViewholder){
            ((OneViewholder) holder).tv.setText(data.get(position).getName());
        }
    }

    @Override
    public int getItemCount() {
        return fenLei.getData().size();
    }
    class OneViewholder extends RecyclerView.ViewHolder{
       TextView tv;
        public OneViewholder(View itemView) {
            super(itemView);
            tv=itemView.findViewById(R.id.tv);
        }
    }
    public interface Listener{
        void list(int position,int cid);
    }
    private Listener ll;

    public void setLl(Listener ll) {
        this.ll = ll;
    }
}
