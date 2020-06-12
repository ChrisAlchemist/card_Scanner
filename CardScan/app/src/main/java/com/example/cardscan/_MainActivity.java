package com.example.cardscan;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.card.payment.CardIOActivity;
import io.card.payment.CreditCard;

public class _MainActivity extends AppCompatActivity {

    private static final int SCAN_RESULT = 100;
    private TextView textViewTarjeta;
    private TextView textViewFecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        textViewTarjeta =  findViewById(R.id.textViewTarjeta);
        textViewFecha =  findViewById(R.id.textViewFecha);
    }

    /*public void scanearTarjeta(View view) {
        Intent intent =  new Intent(this, CardIOActivity.class)
                .putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, false)
                .putExtra(CardIOActivity.EXTRA_SCAN_EXPIRY, false)
                .putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, false)
                .putExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, false);


        startActivityForResult(intent,SCAN_RESULT);
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //if(resultCode == SCAN_RESULT){
            if(data!=null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)){
                CreditCard scanResult = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);
                textViewTarjeta.setText(scanResult.getRedactedCardNumber());
                if(scanResult.isExpiryValid()){
                    String mes = String.valueOf(scanResult.expiryMonth);
                    String an = String.valueOf(scanResult.expiryYear);
                    textViewFecha.setText(mes + "/"+ an);
                }
            }


        //}
    }

}
