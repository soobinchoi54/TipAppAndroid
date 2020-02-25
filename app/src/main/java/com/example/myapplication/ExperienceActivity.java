package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import static java.lang.Math.floor;


/************************************************************************************************
 * The ExperienceActivity provides an interface for the users to create and submit a new dining
 * experience. The interface allows users to rate their experience based on several preset
 * criteria, which will be calculated to suggest a tip percentage. For example, if the user
 * rates "ok" for the service criteria, 0% will be added to a preset tip rate of 15%. If the
 * user rates "good", 1% will be added, resulting in a 16% tip for that meal. The increments
 * at which the tip percentage changes is as below:
 * "poor": -2%     "meh": -1%     "ok": 0%     "good": +1%     "great": +2%
 ************************************************************************************************/

public class ExperienceActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ListView criteriaList;

    private String[] criteria;

    private Button submitButton;

    private static float tipPercentage;

    //TIMER_SERVICE : Fields
    private TimerService timer = new TimerService();
    private boolean bound = false;
    private int seconds = 0;
    private boolean running = false;
    //was running can be to check save instance state
    private boolean wasRunning = false;

    //TIMER_SERVICE : field to bind timer service to ExperienceActivity
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder binder) {
            TimerService.TimerBinder timerBinder = (TimerService.TimerBinder) binder;
            timer = timerBinder.getTimer();
            bound = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            bound = false;

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_experience);

        //TIMER_SERVICE : bind timer service to ExperienceActivity
        Intent timeServiceIntent = new Intent(this, TimerService.class);
        bindService(timeServiceIntent, connection, Context.BIND_AUTO_CREATE);

        /*
         * CREATE CRITERIA LIST VIEW
         */

        // get the string array from string.xml file
        criteria = getResources().getStringArray(R.array.criteria);

        // get the reference of ListView and Button
        criteriaList = (ListView) findViewById(R.id.criteria_list);
        submitButton = (Button) findViewById(R.id.submit_button);

        // set custom adapter to fill data with ListView

        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), criteria);
        criteriaList.setAdapter(customAdapter);

        /*
         * CREATE SUBMIT BUTTON ELEMENT
         */

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tipPercentage = 0.15f;
                for (int i = 0; i < CustomAdapter.selectedAnswers.size(); i++) {
                    tipPercentage += CustomAdapter.selectedAnswers.get(i);
                }

                String message = "";
                // get the value of selected answers from custom adapter
                for (int i = 0; i < CustomAdapter.selectedAnswers.size(); i++) {
                    message = message + "\n" + (i + 1) + " " + CustomAdapter.selectedAnswers.get(i);
                }
                // display the message on screen with the help of Toast.
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();

                //TIMER_SERVICE : stop time when we go to TipResultActivity ~ will stop timer
                running = false;
                // submit to tip result activity
                Intent intent = new Intent(getApplicationContext(), TipResultActivity.class);
                startActivity(intent);

            }
        });

        //TIMER_SERVICE : run our timer
        running = true;
        runTimer();

    }

    //TIMER_SERVICE : overriding lifecycle methods potentially if we are considering exiting app, layout change etc

    /*@Override
    protected void onPause(){
        super.onPause();
        wasRunning = running;
        running = false;
    }
    @Override
    protected void onResume(){
        super.onResume();
        if(wasRunning){
            running = true;
        }
    }*/

    //TIMER_SERVICE : binding methods will have to update when we determine lifecycle pause/resume etc
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(bound) {
            unbindService(connection);
            bound = false;
        }
    }

    /*
     * Select call back methods on SPINNER
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Do nothing
    }

    public static float getTipPercentage() {
        return tipPercentage;
    }

    //TIMER_SERVICE : method to run our timer
    private void runTimer() {
        final TextView timeView = (TextView) findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {

                if(running) {
                    String time = timer.getTime();
                    //Print method to show seconds on timer
                    //Can use these seconds to send in intent to our calculation in TipResultActivity
                    System.out.println("Total seconds to use for calculation: " + timer.getSeconds());
                    timeView.setText("Current length of experience: " + time);
                }
                handler.postDelayed(this, 1000);

            }
        });
    }

}