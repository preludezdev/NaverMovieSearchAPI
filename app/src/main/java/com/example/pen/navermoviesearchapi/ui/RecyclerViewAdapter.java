package com.example.pen.navermoviesearchapi.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.pen.navermoviesearchapi.R;
import com.example.pen.navermoviesearchapi.VO.MovieVO;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    List<MovieVO> movieList = new ArrayList<>();

    private static Typeface mTypeface;
    Context context;

    public RecyclerViewAdapter(Context context) {
        this.context = context;
    }

    public void addList(List<MovieVO> newList){
        movieList.clear();

        for(MovieVO movieVO : newList){
            movieList.add(movieVO);
        }

        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_movie,parent,false);

        //폰트 변경
        if(mTypeface == null) {
            mTypeface = Typeface.createFromAsset(context.getAssets(),
                    "fonts/BMHANNA_ttf.ttf");
        }
        setGlobalFont(view);

        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final MovieViewHolder movieViewHolder = (MovieViewHolder) holder;

        MovieVO currMovieVO = movieList.get(position);
        movieViewHolder.tvTitle.setText(Html.fromHtml(currMovieVO.getTitle()));
        movieViewHolder.ratingBar.setRating(currMovieVO.getUserRating()/2);
        movieViewHolder.tvPublishYear.setText(currMovieVO.getPubDate());
        movieViewHolder.tvDirector.setText(currMovieVO.getDirector());
        movieViewHolder.tvActors.setText(currMovieVO.getActor());

        //이미지 처리
        Glide
                .with(holder.itemView)  //루트뷰인 itemView를 넣어준다
                .load(currMovieVO.getImage())
                .into(movieViewHolder.iv);

        //아이템 클릭시 해당 링크로 이동
        //DetailActivity로 링크를 넘겨준다
        movieViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String urlLink = movieList.get(position).getLink();
                Context context = movieViewHolder.itemView.getContext();
                Intent intent = new Intent(movieViewHolder.itemView.getContext(),DetailActivity.class);
                intent.putExtra("link",urlLink);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

    class MovieViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        RatingBar ratingBar;
        TextView tvPublishYear;
        TextView tvDirector;
        TextView tvActors;
        ImageView iv;


        public MovieViewHolder(View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tvTitle);
            ratingBar = itemView.findViewById(R.id.ratingBar);
            tvPublishYear = itemView.findViewById(R.id.tvPublishYear);
            tvDirector = itemView.findViewById(R.id.tvDirector);
            tvActors = itemView.findViewById(R.id.tvActors);
            iv = itemView.findViewById(R.id.imageView);

        }
    }

    //폰트 변경
    private void setGlobalFont(View view){
        if(view != null){
            if(view instanceof ViewGroup){
                ViewGroup vg = (ViewGroup) view;
                int vgCnt = vg.getChildCount();

                for(int i = 0; i<vgCnt ; i++){
                    View v = vg.getChildAt(i);
                    if(v instanceof TextView){
                        ((TextView)v).setTypeface(mTypeface);
                    }
                    setGlobalFont(v);
                }
            }
        }
    }
}