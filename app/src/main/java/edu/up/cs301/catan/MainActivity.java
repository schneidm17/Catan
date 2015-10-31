package edu.up.cs301.catan;

import android.content.Intent;
import android.graphics.Canvas;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

/**
 * Contains the main game board, the action menu, player stats, etc.
 *
 * @author Matthew Schneider, Jordan Goldey, Jarrett Oney
 * @version 10/23/2015
 */
public class MainActivity extends ActionBarActivity implements View.OnClickListener {

    GameSurfaceView mySurfaceView;
    Button rotateUpButton;
    Button rotateRightButton;
    Button rotateDownButton;
    Button rotateLeftButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mySurfaceView = (GameSurfaceView) findViewById(R.id.displayArea);
        rotateUpButton = (Button) findViewById(R.id.goUpButton);
        rotateRightButton = (Button) findViewById(R.id.goRightButton);
        rotateDownButton = (Button) findViewById(R.id.goDownButton);
        rotateLeftButton = (Button) findViewById(R.id.goLeftButton);

        rotateUpButton.setOnClickListener(this);
        rotateRightButton.setOnClickListener(this);
        rotateDownButton.setOnClickListener(this);
        rotateLeftButton.setOnClickListener(this);

        goToTradingPopup();
    }//onCreate

    //Function to open a popup containing the trading options
    private void goToTradingPopup() {
        //Sets a listener on the button to open the trade popup
        final Button btnOpenPopup = (Button) findViewById(R.id.tradeButton);
        btnOpenPopup.setOnClickListener(new Button.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                LayoutInflater layoutInflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);

                //Opens up the popup at the center of the screen
                View popupView = layoutInflater.inflate(R.layout.activity_trade_popup, null);
                final PopupWindow popupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                popupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0);

                //Dismisses the popup when the return to game button is clicked
                Button btnDismiss = (Button) popupView.findViewById(R.id.exitTradePopup);
                btnDismiss.setOnClickListener(new Button.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });

                //Goes to the player trading page when the trade with player button is pressed
                Button tradePlayerButton = (Button) popupView.findViewById(R.id.tradeWithPlayers);
                tradePlayerButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        popupWindow.dismiss();
                        startActivity(new Intent(MainActivity.this, PlayerTradingScreen.class));
                    }
                });

                //Goes to the trade with bank page when the trade with bank button is pressed
                Button tradeBankButton = (Button) popupView.findViewById(R.id.tradeWithBank);
                tradeBankButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                        startActivity(new Intent(MainActivity.this, BankTradingScreen.class));
                    }
                });

                popupWindow.showAsDropDown(btnOpenPopup, 50, -30);

            }
        });
    }//goToTradingPopup

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }//onCreateOptionsMenu

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }//onOptionsItemSelected

    @Override
    public void onClick(View v) {
        if (v.equals(rotateUpButton)) {
            Canvas myCanvas = mySurfaceView.getHolder().lockCanvas();
            mySurfaceView.rotateUp();
            mySurfaceView.getHolder().unlockCanvasAndPost(myCanvas);
            mySurfaceView.postInvalidate();
        } else if (v.equals(rotateRightButton)) {
            Canvas myCanvas = mySurfaceView.getHolder().lockCanvas();
            mySurfaceView.rotateRight();
            mySurfaceView.getHolder().unlockCanvasAndPost(myCanvas);
            mySurfaceView.postInvalidate();
        } else if (v.equals(rotateDownButton)) {
            Canvas myCanvas = mySurfaceView.getHolder().lockCanvas();
            mySurfaceView.rotateDown();
            mySurfaceView.getHolder().unlockCanvasAndPost(myCanvas);
            mySurfaceView.postInvalidate();
        } else if (v.equals(rotateLeftButton)) {
            Canvas myCanvas = mySurfaceView.getHolder().lockCanvas();
            mySurfaceView.rotateLeft();
            mySurfaceView.getHolder().unlockCanvasAndPost(myCanvas);
            mySurfaceView.postInvalidate();
        }
    }
}
