package com.example.cardscan

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.widget.Toast
import io.card.payment.CardIOActivity
import io.card.payment.CreditCard
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    private val SCAN_RESULT = 100

    public var numTarjeta =""
    public var fecha = ""

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        init()
        

        btn_scan.setOnClickListener {
            val intent = Intent(this, CardIOActivity::class.java)
                    .putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, false)
                    .putExtra(CardIOActivity.EXTRA_SCAN_EXPIRY, false)
                    .putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, false)
                    .putExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, false)

            startActivityForResult(intent, SCAN_RESULT)

        }


        et_numTarjeta.addTextChangedListener(object: TextWatcher {

            override fun afterTextChanged(p0: Editable?) {
                // Do something after text changed

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Do something before text changed on EditText

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                var contadorNumTarjeta = et_numTarjeta.text.length

                // Do something on text changed in EditText
                // Display the EditText change text on TextView real time
                /*
                if(contadorNumTarjeta == 4 || contadorNumTarjeta == 9 ||contadorNumTarjeta == 14){

                    et_numTarjeta.setText("${et_numTarjeta.text} ")
                    et_numTarjeta.setSelection(et_numTarjeta.text.length)

                }
                if(contadorNumTarjeta ==19){

                }*/

                Toast.makeText(applicationContext,contadorNumTarjeta.toString(), Toast.LENGTH_LONG).show()
                textViewTarjeta.text = et_numTarjeta.text

            }




        })

        et_fechaVenc.addTextChangedListener(object: TextWatcher {

            override fun afterTextChanged(p0: Editable?) {
                // Do something after text changed

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Do something before text changed on EditText



            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                // Do something on text changed in EditText
                // Display the EditText change text on TextView real time

                textViewFecha.text = et_fechaVenc.text

            }
        })

        et_titular.addTextChangedListener(object: TextWatcher {

            override fun afterTextChanged(p0: Editable?) {
                // Do something after text changed

            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // Do something before text changed on EditText

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                // Do something on text changed in EditText
                // Display the EditText change text on TextView real time

                textViewTitular.text = et_titular.text

            }
        })

    }





    /*override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {

        return when (keyCode) {
            KeyEvent.KEYCODE_DEL -> {
                et_numTarjeta.setText("")
                Toast.makeText(applicationContext,"Se Preciono DEL", Toast.LENGTH_LONG).show()
                true
            }
            else -> super.onKeyUp(keyCode, event)
        }
    }*/

    fun init(){
        numTarjeta = et_numTarjeta.text.toString().trim()
        fecha = textViewFecha.text.toString().trim()
    }


        /*val intent = Intent(this, CardIOActivity::class.java)
                .putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, false)
                .putExtra(CardIOActivity.EXTRA_SCAN_EXPIRY, false)
                .putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, false)
                .putExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, false)

        startActivityForResult(intent, SCAN_RESULT)*/


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        //if(resultCode == SCAN_RESULT){
        if (data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
            val scanResult: CreditCard = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT)
            et_numTarjeta.setText(scanResult.redactedCardNumber)
            /*if (scanResult.isExpiryValid) {
                val mes = scanResult.expiryMonth.toString()
                val an = scanResult.expiryYear.toString()
                textViewFecha.text = "$mes/$an"
            }*/
        }
        //}
    }






}