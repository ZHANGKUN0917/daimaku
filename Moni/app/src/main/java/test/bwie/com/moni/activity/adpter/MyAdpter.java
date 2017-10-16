package test.bwie.com.moni.activity.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import test.bwie.com.moni.R;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/10/15 11 03
 */

public class MyAdpter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private JSONArray array;

    public MyAdpter(Context context, JSONArray array) {
        this.context = context;
        this.array = array;
    }
    private int type=0;
    private int type1=1;
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder vh=null;
        if(viewType==0){
            View view= LayoutInflater.from(context).inflate(R.layout.item,parent,false);
            vh=new OneViewHolder(view);
        }else if(viewType==1){
            View view1= LayoutInflater.from(context).inflate(R.layout.item1,parent,false);
            vh=new TwoViewHolder(view1);
        }
        return vh;
    }
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof OneViewHolder){
            try {
                Picasso.with(context).load(array.getJSONObject(position).getString("images")).placeholder(R.mipmap.ic_launcher).into(((OneViewHolder) holder).iv);
                ((OneViewHolder) holder).tv.setText(array.getJSONObject(position).getString("title"));
                ((OneViewHolder) holder).tv1.setText(array.getJSONObject(position).getString("price"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else if(holder instanceof TwoViewHolder){
            try {
                Picasso.with(context).load(array.getJSONObject(position).getString("images")).placeholder(R.mipmap.ic_launcher).into(((TwoViewHolder) holder).iv);
                Picasso.with(context).load(array.getJSONObject(position).getString("images")).placeholder(R.mipmap.ic_launcher).into(((TwoViewHolder) holder).iv1);
                ((TwoViewHolder) holder).tv.setText(array.getJSONObject(position).getString("title"));
                ((TwoViewHolder) holder).tv1.setText(array.getJSONObject(position).getString("price"));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
    @Override
    public int getItemCount() {
        Log.i("0909",array.length()+"");
        return array.length();
    }

    @Override
    public int getItemViewType(int position) {
        try {
            if(array.getJSONObject(position).getString("images").length()>0){
                return type1;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return type;
    }

    class OneViewHolder extends RecyclerView.ViewHolder{
        ImageView iv;
        TextView tv,tv1;
        public OneViewHolder(View itemView) {
            super(itemView);
            iv=itemView.findViewById(R.id.iv);
            tv=itemView.findViewById(R.id.tv);
            tv1=itemView.findViewById(R.id.tv1);
        }
    }
    class TwoViewHolder extends RecyclerView.ViewHolder{
        ImageView iv,iv1;
        TextView tv,tv1;
        public TwoViewHolder(View itemView) {
            super(itemView);
            iv1=itemView.findViewById(R.id.iv1);
            iv=itemView.findViewById(R.id.iv);
            tv=itemView.findViewById(R.id.tv);
            tv1=itemView.findViewById(R.id.tv1);
        }
    }
}
