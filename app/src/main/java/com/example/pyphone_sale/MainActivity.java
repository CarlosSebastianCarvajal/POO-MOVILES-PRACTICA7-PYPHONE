package com.example.pyphone_sale;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    RequestQueue requestQueue;

    EditText txtTelefono, txtCodigo, txtIdCliente, txtReferencia, txtMonto, txtTrCliente;


    Button btnCobrar;

    private static final String URL_PAY = "https://pay.payphonetodoesposible.com/api/Sale";
    private static final String ACCESS_TOKEN = "sQj-_8TMNsI32sJVwTu7FGo03hv9gzXjn5tLOVYRJsb3Nz9VmBBGI7Zne7cyMDQSG0zS50CWyhVjfWYicK-ncng6R7wDwcB6kWdllkZ2y1TMAa64H5QnJBqmCAV_PTD7EWQI01-C94wxWM7FA4HqvuAMvbfzcShH54H6v8p4sDqMGhT3oCWeoW92WX2dWswJ_lflnL9UXAtJdXmH28eopE1txfewjFtF05h1Fr8Att0-YBw2XjpF6sTAYZXKxTn6_QN8qk-Gy6rn2aRHedJBVV4j-o8XfFMSDRCLi06R0hw89NVLW0hhq91rhiqOPMx8_BHaf-oCn8SzomJ90ZR-I0PB4Ws";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = Volley.newRequestQueue(this);

        txtTelefono = (EditText) findViewById(R.id.etxtTelefono);
        txtCodigo = (EditText) findViewById(R.id.etxtCodigo);
        txtIdCliente = (EditText) findViewById(R.id.etxtIdCliente);
        txtReferencia = (EditText) findViewById(R.id.etxtReferencia);
        txtMonto = (EditText) findViewById(R.id.etxtMonto);
        txtTrCliente = (EditText) findViewById(R.id.etxtTrCliente);

        btnCobrar = (Button) findViewById(R.id.btnCobrar);
        btnCobrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(ValidarCampos()){
                    Cobrar();
                }else{
                    Toast.makeText(MainActivity.this,"Por favor, llene todo los campos",Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void Cobrar(){
        /*
        {
           "phoneNumber": "968950866",
           "countryCode": "593",
           "clientUserId": "0942463746",
           "reference": "Fac-001",
           "responseUrl": "string",
           "amount": 100,
           "amountWithTax": 90,
           "amountWithoutTax": 0,
           "tax": 10,
           "clientTransactionId": "111"
        }
         */


        Double valor = Double.parseDouble(txtMonto.getText().toString())*100;
        int Monto = valor.intValue();
        int MontoImp = (Monto/100)*12;
        int MontoSinImp = Monto - MontoImp;

        JSONObject object = new JSONObject();
        try {
            //input your API parameters
            object.put("phoneNumber",txtTelefono.getText().toString());
            object.put("countryCode",txtCodigo.getText().toString());
            object.put("clientUserId",txtIdCliente.getText().toString());
            object.put("reference",txtReferencia.getText().toString());
            object.put("responseUrl","string");
            object.put("amount",Monto);
            object.put("amountWithTax",MontoSinImp);
            object.put("amountWithoutTax",0);
            object.put("tax",MontoImp);
            object.put("clientTransactionId",txtTrCliente.getText().toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Enter the correct url for your api service site
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, URL_PAY, object,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String transactionId = response.getString("transactionId");
                            Toast.makeText(MainActivity.this,transactionId,Toast.LENGTH_LONG).show();
                            redireccionar(transactionId);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this,"Ingrese los datos correctamente",Toast.LENGTH_LONG).show();
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

    public boolean ValidarCampos(){
        if(txtTrCliente.getText().toString().isEmpty() || txtMonto.getText().toString().isEmpty() || txtReferencia.getText().toString().isEmpty() || txtIdCliente.getText().toString().isEmpty() || txtCodigo.getText().toString().isEmpty() || txtTelefono.getText().toString().isEmpty()){
            return false;
        }else{
            return true;
        }
    }

    public void redireccionar(String transactionId){
        requestQueue = Volley.newRequestQueue(this);
        Bundle b = new Bundle();
        b.putString("transactionId", transactionId);
        Intent intent = new Intent(this, EstadoCobroActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
        intent.putExtras(b);
        startActivity(intent);
    }
}