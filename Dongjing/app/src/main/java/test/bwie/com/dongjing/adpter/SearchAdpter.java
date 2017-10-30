package test.bwie.com.dongjing.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import test.bwie.com.dongjing.R;
import test.bwie.com.dongjing.bean.Search;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/10/18 21 47
 */

public class SearchAdpter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private Search sh;
    private View view;

    public SearchAdpter(Context context, Search sh) {
        this.context = context;
        this.sh = sh;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder rv;
        view = LayoutInflater.from(context).inflate(R.layout.searchitem,parent,false);
        rv=new OneHolder(view);
        return rv;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        String images = sh.getData().get(position).getImages();
        String[] split = images.split("\\|");
        final int pid = sh.getData().get(position).getPid();
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listen.listen(position,pid);
            }
        });


        if(holder instanceof OneHolder){
             Picasso.with(context).load(split[0]).placeholder(R.mipmap.ic_launcher).into(((OneHolder) holder).iv);
            ((OneHolder) holder).tv.setText(sh.getData().get(position).getTitle());
            ((OneHolder) holder).tv1.setText(sh.getData().get(position).getPrice()+"");
         }
    }

    @Override
    public int getItemCount() {
        return sh.getData().size();
    }
    class OneHolder extends RecyclerView.ViewHolder{
         ImageView iv;
        TextView tv,tv1;
        public OneHolder(View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.iv);
            tv=itemView.findViewById(R.id.title);
            tv1=itemView.findViewById(R.id.price);
        }
    }
    public interface  listener{
        void listen(int position,int pid);
    }
    public listener listen;
    public void setListen(listener listen) {
        this.listen = listen;
    }
}
