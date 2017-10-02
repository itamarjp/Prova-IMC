package com.professorangoti.imc;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
}
