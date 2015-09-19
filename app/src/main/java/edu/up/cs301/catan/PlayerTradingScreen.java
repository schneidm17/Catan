package edu.up.cs301.catan;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/* @author: Jordan Goldey
 * @date: 9/19/2015
 * @purpose: Controls the bank trading screen that allows the players to trade a set number of one
 *           resource cards for a different resource card.
 */
public class PlayerTradingScreen extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_trading_screen);

        goBackToGameBoard();
    }//onCreate

    //Function to exit the trading menu when any button is pushed
    private void goBackToGameBoard() {
        Button resumeGame = (Button) findViewById(R.id.returnToGame);
        Button playerOneAccept = (Button) findViewById(R.id.player1_accept);
        Button playerTwoAccept = (Button) findViewById(R.id.player2_accept);
        Button playerThreeAccept = (Button) findViewById(R.id.player3_accept);

        resumeGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        playerOneAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        playerTwoAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        playerThreeAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }//goBackToGameBoard

}//PlayerTradingScreen
