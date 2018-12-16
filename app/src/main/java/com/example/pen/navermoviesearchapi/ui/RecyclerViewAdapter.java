package com.example.pen.navermoviesearchapi.ui;

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
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MovieViewHolder movieViewHolder = (MovieViewHolder) holder;

        MovieVO currMovieVO = movieList.get(position);
        movieViewHolder.tvTitle.setText(Html.fromHtml(currMovieVO.getTitle()));
        movieViewHolder.ratingBar.setRating(currMovieVO.getUserRating());
        Log.d("test","userRating : " +currMovieVO.getUserRating());
        Log.d("test","userRating2 : " +movieViewHolder.ratingBar.getNumStars());
        movieViewHolder.tvPublishYear.setText(currMovieVO.getPubDate());
        movieViewHolder.tvDirector.setText(currMovieVO.getDirector());
        movieViewHolder.tvActors.setText(currMovieVO.getActor());

        //이미지 처리
        //glide 이용
        //iv = ...
        //imgURL 설정
        Glide
                .with(holder.itemView)  //루트뷰인 itemView를 넣어준다
                .load(currMovieVO.getImage())
                .into(movieViewHolder.iv);
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
}