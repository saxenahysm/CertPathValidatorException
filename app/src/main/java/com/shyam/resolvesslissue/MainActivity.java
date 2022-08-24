package com.shyam.resolvesslissue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    String TAG = "Test";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        callAPIService();
    }
    void callAPIService(){
        StringRequest request = new StringRequest(Request.Method.POST,"https://bpms.sudacg.in/api/api-count/read.php",
                response -> {
                    try {
                        Log.e(TAG, "callAPIService: "+response );
                        JSONObject jsonObject = new JSONObject(response);
                        tv.setText(jsonObject.getString("status"));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                },
                Throwable::printStackTrace) {
            @Override
            protected Map<String, String> getParams() {
                HashMap<String, String> param = new HashMap<>();
                param.put("propid", "30001");
                Log.e(TAG, "getParams: "+ param);
                return param;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}