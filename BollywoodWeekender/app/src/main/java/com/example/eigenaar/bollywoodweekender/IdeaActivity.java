package com.example.eigenaar.bollywoodweekender;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * This activity enables the user to send e-mails with ideas.
 *
 * Puja Chandrikasingh
 * 11059842
 */

public class IdeaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_idea);
    }

    public void sendMail(View view){

        // get idea
        final EditText idea = findViewById(R.id.new_idea);
        String newIdea = idea.getText().toString();

        // code from https://stackoverflow.com/questions/2197741/how-can-i-send-emails-from-my
        // -android-application
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"uvastudent2015@gmail.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "Idee volgende editie");
        i.putExtra(Intent.EXTRA_TEXT   , newIdea);

        try {

            // this leads to sending an email and it gives a message
            startActivity(Intent.createChooser(i, "Mail wordt verzonden..."));

            // clear idea field
            idea.setText("");

        } catch (android.content.ActivityNotFoundException ex) {

            // this shows an error if there is no email installed
            Toast.makeText(IdeaActivity.this, "U heeft geen email geïnstalleerd.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * The two functions bellow create the menu and send the user to the right page when they
     * select an option.
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        // check which item is selected and go to the right activity
        Intent intent;
        switch (item.getItemId()){
            case R.id.home:
                intent = new Intent(this, MainActivity.class);
                break;
            case R.id.eventsLoc:
                intent = new Intent(this, EventsLocActivity.class);
                break;
            case R.id.weather:
                intent = new Intent(this, WeatherActivity.class);
                break;
            case R.id.news:
                intent = new Intent(this, NewsActivity.class);
                break;
            case R.id.activities:
                intent = new Intent(this, EventsActivity.class);
                break;
            case R.id.program:
                intent = new Intent(this, ProgrActivity.class);
                break;
            case R.id.map:
                intent = new Intent(this, MapActivity.class);
                break;
            case R.id.idea:
                intent = new Intent(this, IdeaActivity.class);
                break;
            default:
                intent = new Intent(this, MainActivity.class);
        }
        startActivity(intent);
        finish();
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}