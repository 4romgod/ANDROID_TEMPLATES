package com.template.e_commerce;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.simplify.android.sdk.Card;
import com.simplify.android.sdk.CardEditor;
import com.simplify.android.sdk.CardToken;
import com.simplify.android.sdk.Simplify;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    Simplify simplify;
    String API_KEY = "YOUR_API_PUBLIC_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simplify = new Simplify();
        simplify.setApiKey(API_KEY);

        // create a new card object
        Card card = new Card()
                .setNumber("5555555555554444")
                .setExpMonth("01")
                .setExpYear("99")
                .setCvc("123")
                .setAddressZip("12345");

        // tokenize the card
        simplify.createCardToken(card, new CardToken.Callback() {
            @Override
            public void onSuccess(CardToken cardToken) {
                // ...
            }

            @Override
            public void onError(Throwable throwable) {
                // ...
            }
        });

        final CardEditor cardEditor = (CardEditor) findViewById(R.id.card_editor);
        final Button checkoutBtn = (Button) findViewById(R.id.btnCheckout);


        // add state change listener
        cardEditor.addOnStateChangedListener(new CardEditor.OnStateChangedListener() {
            @Override
            public void onStateChange(CardEditor cardEditor) {
                // isValid() == true : card editor contains valid and complete card information
                checkoutBtn.setEnabled(cardEditor.isValid());
            }
        });

        // add checkout button click listener
        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // create a card token
                simplify.createCardToken(cardEditor.getCard(), new CardToken.Callback() {
                    @Override
                    public void onSuccess(CardToken cardToken) {
                        // ...
                    }
                    @Override
                    public void onError(Throwable throwable) {
                        // ...
                    }
                });
            }
        });
    }



    private static JSONObject getBaseRequest() throws JSONException {
        try {
            return new JSONObject().put("apiVersion", 2).put("apiVersionMinor", 0);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

}       //end class
