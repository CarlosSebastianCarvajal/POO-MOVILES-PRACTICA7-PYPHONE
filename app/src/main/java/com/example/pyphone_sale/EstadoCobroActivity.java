package com.example.pyphone_sale;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EstadoCobroActivity extends AppCompatActivity {

    RequestQueue requestQueue;
    Button btnActualizar;

    TextView txtEstado;
    Bundle b = new Bundle();
    String tId;
    private static final String URL_PAY = "https://pay.payphonetodoesposible.com/api/Sale/";
    private static final String ACCESS_TOKEN = "sQj-_8TMNsI32sJVwTu7FGo03hv9gzXjn5tLOVYRJsb3Nz9VmBBGI7Zne7cyMDQSG0zS50CWyhVjfWYicK-ncng6R7wDwcB6kWdllkZ2y1TMAa64H5QnJBqmCAV_PTD7EWQI01-C94wxWM7FA4HqvuAMvbfzcShH54H6v8p4sDqMGhT3oCWeoW92WX2dWswJ_lflnL9UXAtJdXmH28eopE1txfewjFtF05h1Fr8Att0-YBw2XjpF6sTAYZXKxTn6_QN8qk-Gy6rn2aRHedJBVV4j-o8XfFMSDRCLi06R0hw89NVLW0hhq91rhiqOPMx8_BHaf-oCn8SzomJ90ZR-I0PB4Ws";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estado_cobro);

        requestQueue = Volley.newRequestQueue(this);

        b = this.getIntent().getExtras();
        tId = b.getString("transactionId");

        txtEstado = (TextView) findViewById(R.id.txtEstado);


        RevisarEstado();

        btnActualizar = (Button) findViewById(R.id.btnActualizar);
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RevisarEstado();

            }
        });

    }

    private void RevisarEstado(){
        txtEstado.setText("");
        /*JSONObject object = new JSONObject();
        try {
            //input your API parameters
            object.put("Nombres","Carlos Sebastian");
            object.put("Apellidos","Carvajal Florencia");
        } catch (JSONException e) {
            e.printStackTrace();
        }*/
        // Enter the correct url for your api service site
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL_PAY + tId, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String phoneNumber = response.getString("phoneNumber");
                            String reference  = response.getString("reference");
                            String transactionStatus = response.getString("transactionStatus");
                            //String message = response.getString("message");

                            txtEstado.append("Cliente con Numero : " + phoneNumber + "\n");
                            txtEstado.append("y referencia de cobro : " + reference + "\n");
                            txtEstado.append("Existe un estado de : " + transactionStatus + "\n");
                            //txtEstado.append("y accion de usuario : " + message + "\n");


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        //txtEstado.setText("String Response : "+ response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                txtEstado.setText("Error getting response");
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headerMap = new HashMap<String, String>();
                headerMap.put("Content-Type", "application/json");
                headerMap.put("Authorization", "Bearer " + ACCESS_TOKEN);
                return headerMap;
            }
        };
        requestQueue.add(jsonObjectRequest);

    }

    private void stringRequest_Aut(){
        StringRequest request = new StringRequest(
                Request.Method.GET,
                URL_PAY,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        txtEstado.setText(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        txtEstado.setText("erroor");
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headerMap = new HashMap<String, String>();
                headerMap.put("Content-Type", "application/json");
                headerMap.put("Authorization", "Bearer " + ACCESS_TOKEN);
                return headerMap;
            }
        };
        requestQueue.add(request);
    }

}