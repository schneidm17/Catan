package edu.up.cs301.catan;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class TradePopup extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trade_popup);

        goToPlayerTradingMenu();
        goToBankTradingMenu();
        goBackToGameBoard();
    }

    private void goToPlayerTradingMenu() {
        Button tradeButton = (Button) findViewById(R.id.tradeWithPlayers);

        tradeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(TradePopup.this, Trading.class));
            }
        });
    }

    private void goToBankTradingMenu() {
        Button tradeButton = (Button) findViewById(R.id.tradeWithBank);

        tradeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                startActivity(new Intent(TradePopup.this, Trading.class));
            }
        });
    }

    private void goBackToGameBoard() {
        Button resumeGame = (Button) findViewById(R.id.goBack);

        resumeGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
