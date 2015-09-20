package edu.up.cs301.catan;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Switch;

import static edu.up.cs301.catan.R.id.hard_switch;
import static edu.up.cs301.catan.R.id.hide_switch;
import static edu.up.cs301.catan.R.id.nice_switch;
import static edu.up.cs301.catan.R.id.random_switch;


public class NewGameMenu extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_game_menu);
    }

    /*
     * this method is used to create the main game activity
     */
    public void makeNewGame(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


    /*
     * All of the toggle methods below extend the clickable area
     * for the toggleable options
     */
    public void toggleRandSwitch(View view){
        Switch randSwitch = (Switch) findViewById(random_switch);
        randSwitch.toggle();
    }

    public void toggleNiceSwitch(View view){
        Switch niceSwitch = (Switch) findViewById(nice_switch);
        niceSwitch.toggle();
    }

    public void toggleHardSwitch(View view){
        Switch hardSwitch = (Switch) findViewById(hard_switch);
        hardSwitch.toggle();
    }

    public void toggleHideSwitch(View view){
        Switch hideSwitch = (Switch) findViewById(hide_switch);
        hideSwitch.toggle();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_new_game_menu, menu);
        return true;
    }

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
    }
}
