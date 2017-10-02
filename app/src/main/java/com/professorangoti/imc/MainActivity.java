package com.professorangoti.imc;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DecimalFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.Consultar();
    }

    public void calcula(View v) {
        double peso = Double.parseDouble(((EditText)findViewById(R.id.peso_id)).getText().toString());
        double altura = Double.parseDouble(((EditText)findViewById(R.id.altura_id)).getText().toString());
        double imc = peso/(altura*altura);
        DecimalFormat formato = new DecimalFormat("0.00");
        ((TextView)findViewById(R.id.imc_id)).setText(formato.format(imc)+"");
        ImageView img = (ImageView)findViewById(R.id.imagem);
        if(imc<18.5)
            img.setImageResource(R.drawable.images1);
        else if(imc>=18.5 && imc<25)
            img.setImageResource(R.drawable.images2);
        else if(imc>=25 && imc<30)
            img.setImageResource(R.drawable.images3);
        else
            img.setImageResource(R.drawable.images4);

    }

    public void Consultar (){

        Log.i("teste","iniciando...");
        //Retrofit
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://raw.githubusercontent.com/angoti/IMC/master/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        Log.i("teste","objeto retrofit criado...");
        ApiEndpoint apiService = retrofit.create(ApiEndpoint.class);
        Log.i("teste","chamando api...");
        //Integer userid = 0;
        //EditText ed = findViewById(R.id.editText);
        //userid = Integer.parseInt(ed.getText().toString());
        Call<UserClass> call = apiService.ObterPosts();
        //chamada assíncrona
        call.enqueue(new Callback<UserClass>() {
            @Override
            public void onResponse(Call<UserClass> call, Response<UserClass> response) {
                int statusCode = response.code();
                UserClass user = response.body();

                Log.i("teste","statuscode: " + statusCode);
                Log.i("teste", "altura: " + user.getAltura());
                Log.i("teste", "peso: " + user.getPeso());
                
                ((TextView) findViewById(R.id.altura_id)).setText("" + user.getAltura());
                ((TextView) findViewById(R.id.peso_id)).setText("" + user.getPeso());
                calcula(null);
            }
            @Override
            public void onFailure(Call<UserClass> call, Throwable t) {
                // Log error here since request failed
                Log.i("teste",t.toString());
            }
        });


    }



}
