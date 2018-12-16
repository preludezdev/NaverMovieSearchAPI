package com.example.pen.navermoviesearchapi.ui;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pen.navermoviesearchapi.R;
import com.example.pen.navermoviesearchapi.VO.MovieListVO;
import com.example.pen.navermoviesearchapi.VO.MovieVO;
import com.example.pen.navermoviesearchapi.network.NetworkHelper;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    private String clientId  = "n9S7GbbjoVsXLWJdZEus";
    private String clientSecret = "regEDQ1Wxd";


    EditText editText;
    Button btEnter;
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.etText);
        btEnter = findViewById(R.id.btEnter);
        recyclerView = findViewById(R.id.recyclerView);

        //리사이클러뷰 설정
        recyclerViewAdapter = new RecyclerViewAdapter(this);
        recyclerView.setAdapter(recyclerViewAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        //리사이클러뷰 구분선 추가
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        //키보드 감추기
        final InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);

        //클릭 이벤트 구현
        btEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userInput = editText.getText().toString(); // 사용자 입력값
                if(userInput.equals("")) {
                    Toast.makeText(MainActivity.this, "검색어를 입력해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                NetworkHelper.getInstance()
                        .movieAPIService
                        .getMovieListByName(clientId,clientSecret,userInput,100)
                        .enqueue(new Callback<MovieListVO>() {
                            @Override
                            public void onResponse(Call<MovieListVO> call, Response<MovieListVO> response) {
                                //total이 0이면 입력한 검색어와 매치되는 영화검색이 없으므로 유효하지않다.
                                if(response.body().getTotal()==0)
                                    Toast.makeText(MainActivity.this,
                                            "검색결과가 없습니다.", Toast.LENGTH_SHORT).show();
                                else {
                                    //검색에 성공하면 키보드를 감춘다
                                    imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
                                    //데이터 가져오기
                                    recyclerViewAdapter.addList(response.body().getItems());
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
