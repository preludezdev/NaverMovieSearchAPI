package com.example.pen.navermoviesearchapi.ui;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.example.pen.navermoviesearchapi.R;
import com.example.pen.navermoviesearchapi.VO.MovieListVO;
import com.example.pen.navermoviesearchapi.databinding.ActivityMainBinding;
import com.example.pen.navermoviesearchapi.network.NetworkHelper;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    private String clientId  = "n9S7GbbjoVsXLWJdZEus";
    private String clientSecret = "regEDQ1Wxd";

    ActivityMainBinding binding;

    RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //리사이클러뷰 설정
        recyclerViewAdapter = new RecyclerViewAdapter(this);
        binding.recyclerView.setAdapter(recyclerViewAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.recyclerView.setLayoutManager(linearLayoutManager);

        //리사이클러뷰 구분선 추가
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(binding.recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        binding.recyclerView.addItemDecoration(dividerItemDecoration);

        //키보드 감추기
        final InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        //클릭 이벤트 구현
        binding.btEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userInput = binding.etText.getText().toString(); // 사용자 입력값
                if (userInput.equals("")) {
                    Toast.makeText(MainActivity.this, "검색어를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                NetworkHelper.getInstance()
                        .movieAPIService
                        .getMovieListByName(clientId, clientSecret, userInput, 100)
                        .enqueue(new Callback<MovieListVO>() {
                            @Override
                            public void onResponse(Call<MovieListVO> call, Response<MovieListVO> response) {
                                //total이 0이면 입력한 검색어와 매치되는 영화검색이 없으므로 유효하지않다.
                                if (response.body().getTotal() == 0)
                                    Toast.makeText(MainActivity.this,
                                            "검색결과가 없습니다.", Toast.LENGTH_SHORT).show();
                                else {
                                    //검색에 성공하면 키보드를 감춘다
                                    imm.hideSoftInputFromWindow(binding.etText.getWindowToken(), 0);

                                    //출력된 데이터 리사이클러뷰에 뿌리기
                                    MovieListVO movieListVO = response.body();
                                    recyclerViewAdapter.clearList();
                                    recyclerViewAdapter.addList(movieListVO.getItems());
                                }
                            }

                            @Override
                            public void onFailure(Call<MovieListVO> call, Throwable t) {
                                t.printStackTrace();
                            }
                        });
            }
        });
    }
}
