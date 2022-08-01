package com.example.mainactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class Card_View extends AppCompatActivity implements View.OnClickListener {

    private CardView card1, card2, card3,card4,card5,card6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_card_view);
        card1 = (CardView) findViewById(R.id.card1);
        card2 = (CardView) findViewById(R.id.card2);
        card3 = (CardView) findViewById(R.id.card3);
        card4 = (CardView) findViewById(R.id.card4);
        card5 = (CardView) findViewById(R.id.card5);
        card6 = (CardView) findViewById(R.id.card6);

        card1.setOnClickListener(this);
        card2.setOnClickListener(this);
        card3.setOnClickListener(this);
        card4.setOnClickListener(this);
        card5.setOnClickListener(this);
        card6.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;

        switch (view.getId())
        {
            case R.id.card1: i = new Intent(Card_View.this,Login.class);
                startActivity(i);
                break;
            case R.id.card2: i = new Intent(Card_View.this,retailer_login.class);
                startActivity(i);
                break;
            case R.id.card3: i = new Intent(Card_View.this,Signup.class);
                startActivity(i);
                break;
            case R.id.card4: i = new Intent(Card_View.this,retailer_registration.class);
                startActivity(i);
                break;
            default:
                break;
        }

    }
}