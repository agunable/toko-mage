package com.gunglaksman.mage;

import android.app.ProgressDialog;
import android.app.VoiceInteractor;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    LinearLayout layoutBawah, layoutAtas;
    Button loginButton;
    Animation uptodown, downtoup;
    EditText etId;
    private ProgressDialog loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loginButton = (Button)findViewById(R.id.loginButton);
        //layoutAtas = (LinearLayout)findViewById(R.id.layoutAtas);
        //layoutBawah = (LinearLayout)findViewById(R.id.layoutBawah);
        //uptodown = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        //downtoup = AnimationUtils.loadAnimation(this, R.anim.downtoup);
        etId = (EditText)findViewById(R.id.etId);
        //layoutBawah.setAnimation(downtoup);
        //layoutAtas.setAnimation(uptodown);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://feazy.azurewebsites.net/mage/test.php";
                loading = ProgressDialog.show(MainActivity.this, "Please wait", "Identify the ID", false, true);

                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        loading.dismiss();
                        if (response.equalsIgnoreCase("benar")) {
                            Intent masuk = new Intent(MainActivity.this, Home.class);
                            String newId = etId.getText().toString();
                            masuk.putExtra("newId", newId);
                            startActivity(masuk);
                        } else {
                            Toast.makeText(getApplicationContext(), response, Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        loading.dismiss();
                        Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        Map<String, String> params = new HashMap<>();
                        params.put("username", etId.getText().toString());
                        return params;
                    }
                };

                MySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
            }
        });
    }
}
