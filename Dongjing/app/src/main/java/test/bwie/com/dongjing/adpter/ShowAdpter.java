package test.bwie.com.dongjing.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import test.bwie.com.dongjing.R;
import test.bwie.com.dongjing.bean.Show;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/10/18 19 58
 */

public class ShowAdpter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private Show db;

    public ShowAdpter(Context context, Show db) {
        this.context = context;
        this.db = db;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder rv;
        View view= LayoutInflater.from(context).inflate(R.layout.showitem,parent,false);
        rv=new OneViewholder(view);
        return rv;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        String images = db.getData().get(position).getImages();
        Log.i("ffff",db.getData().get(0).getTitle());
        String[] strings = images.split("\\|");
        Log.i("1221",strings.length+"");
        if(holder instanceof OneViewholder){
             Picasso.with(context).load(strings[0]).placeholder(R.mipmap.ic_launcher).into(((OneViewholder) holder).iv);
             ((OneViewholder) holder).tv.setText(db.getData().get(position).getTitle());
             ((OneViewholder) holder).tv1.setText(db.getData().get(position).getPrice()+"");
         }
    }

    @Override
    public int getItemCount() {
        Log.i("a9",db.getData().size()+"");
        return db.getData().size();
    }
    class OneViewholder extends RecyclerView.ViewHolder {
        ImageView iv;
        TextView tv,tv1;
        public OneViewholder(View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.iv);
            tv=itemView.findViewById(R.id.title);
            tv1=itemView.findViewById(R.id.price);
        }
    }

}
