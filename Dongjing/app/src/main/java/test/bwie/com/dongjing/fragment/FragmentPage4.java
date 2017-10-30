package test.bwie.com.dongjing.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;
import test.bwie.com.dongjing.R;
import test.bwie.com.dongjing.activity.LoginActivity;
import test.bwie.com.dongjing.activity.RegisterActivity;
import test.bwie.com.dongjing.activity.UpLoadActivity;

import static test.bwie.com.dongjing.activity.RegisterActivity.bb;

/**
 * 类的用途：
 *
 * @author HP
 * @date 2017/8/31 20 44
 */

public class FragmentPage4 extends Fragment {
    private CircleImageView clv;
    private TextView tv,tv1;
    private ImageView iv;
    private File file;
    private Uri uri;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=View.inflate(getActivity(),R.layout.fragment4,null);
        clv=view.findViewById(R.id.clv);
        tv=view.findViewById(R.id.tv);
        tv1=view.findViewById(R.id.tv1);
        iv=view.findViewById(R.id.iv);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(bb=true){
                    Intent it=new Intent(getActivity(), LoginActivity.class);
                    startActivity(it);
                }else{
                    Intent it=new Intent(getActivity(), RegisterActivity.class);
                    startActivity(it);
                }
            }
        });
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        clv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it=new Intent(getActivity(), UpLoadActivity.class);
                startActivity(it);
            }
        });

    }



}
