package com.example.pen.navermoviesearchapi.ui;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

//fonts 넣기위한 클래스.
public class BaseActivity extends AppCompatActivity {
    public Typeface mTypeFace = null;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        if(mTypeFace == null){
            mTypeFace = Typeface.createFromAsset(this.getAssets(),"fonts/BMHANNA_ttf.ttf");
        }

        setGlobalFont(getWindow().getDecorView());
    }

    private void setGlobalFont(View view){
        if(view != null){
            if(view instanceof ViewGroup){
                ViewGroup vg = (ViewGroup) view;
                int vgCnt = vg.getChildCount();

                for(int i = 0; i<vgCnt ; i++){
                    View v = vg.getChildAt(i);
                    if(v instanceof TextView){
                        ((TextView)v).setTypeface(mTypeFace);
                    }
                    setGlobalFont(v);
                }
            }
        }
    }
}
