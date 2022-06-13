package com.example.samplehttpvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;

    //요청 큐는 한 번만 만들어 계속 사용할 수 있으므로 static 키워드
    //한 액티비티에서 사용하는 것이 아니라 앱 전체에서 사용하는 것이 일반적
    //--> 별도의 클래스를 만들어서 사용
    static RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.et);
        textView = findViewById(R.id.tv_content);

        Button bt = findViewById(R.id.bt);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeRequest();
            }
        });
        
        //requestQueue 객체 생성
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
    }
    public void makeRequest(){
        String url = editText.getText().toString();

        //요청을 보내기 위한 StringRequest 객체 생성
        //StringRequest:문자열을 주고받기 위해 사용하는 요청 객체
        //4개의 파라미터 (요청방식, url, 응답받을 리스너, 에러가 발생했을 때 호출될 리스너)
        StringRequest request = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        println("응답 : " + response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        println("에러 : "+error.getMessage());
                    }
                }
        ){
            //POST 방식으로 요청할 경우, 해당 HashMap 객체에 파라미터 값들을 넣어주면 됨
            @Override
            protected Map<String, String> getParams() throws AuthFailureError{
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }
        };
        //이전 응답 결과를 사용하지 않도록 설정
        request.setShouldCache(false);
        requestQueue.add(request);
        println("요청 보냄");
    }

    public void println(String data){
        textView.append(data + "\n");
    }
}