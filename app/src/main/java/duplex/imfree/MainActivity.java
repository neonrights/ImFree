package duplex.imfree;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {
    private Button refreshButton;
    private ListView friendsList;
    private ArrayList<String> availableFriends;
    private ArrayAdapter<String> friendsAdapter;
    private ImageView sadPanda;
    private TextView loserText;

    FloatingActionButton availability;

    boolean open = false; // determines if the user is currently available
    private String name; // placeholder for user details/authentication


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        availability = (FloatingActionButton) findViewById(R.id.fab);
        availability.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
        sadPanda = (ImageView) findViewById(R.id.sad_panda);
        loserText = (TextView) findViewById(R.id.loser_text);

        // check if logged in, if not go to settings to log in then return here and continue
        if (name == null || name.isEmpty()) {
            toLogin();
            // get user details through intent
            setupList();
        } else {
            setupList();
        }
    }

    // sets up list and refresh button
    public void setupList() {
        availableFriends = updateFriendsList();

        friendsList = (ListView) findViewById(R.id.available_friends_list);
        friendsAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.friends_list_layout, R.id.friend_list_item, availableFriends);
        friendsList.setAdapter(friendsAdapter);

        refreshButton = (Button) findViewById(R.id.test_button);

        if (availableFriends.isEmpty()) {
            // hide ListView, display sad panda image and caption "you have no friends"
            friendsList.setVisibility(View.INVISIBLE);
            sadPanda.setVisibility(View.VISIBLE);
            loserText.setVisibility(View.VISIBLE);
        }

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                availableFriends = updateFriendsList();
                if (availableFriends.isEmpty()) {
                    // hide ListView, display sad panda image and caption "you have no friends"
                    friendsList.setVisibility(View.INVISIBLE);
                    sadPanda.setVisibility(View.VISIBLE);
                    loserText.setVisibility(View.VISIBLE);
                } else {
                    // unhide ListView, hide image and caption
                    friendsList.setVisibility(View.VISIBLE);
                    sadPanda.setVisibility(View.INVISIBLE);
                    loserText.setVisibility(View.INVISIBLE);
                }
                friendsAdapter.notifyDataSetChanged();
            }
        });
    }

    // to first time login method
    public void toLogin() {
        // redirect to login part of settings and start tutorial
        Intent intent = new Intent(getApplicationContext(), SettingsActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    // gets currently available friends
    public ArrayList<String> updateFriendsList() {
        // TODO get time and friend names here
        ArrayList<String> updatedFriends = new ArrayList<>();

        Calendar c = Calendar.getInstance();
        if (!open) {
            for (int i = 0; i < 10; i++) {
                updatedFriends.add("fucking Sean - "
                        + c.get(Calendar.HOUR) + ":"
                        + c.get(Calendar.MINUTE) + ":"
                        + c.get(Calendar.SECOND));
            }
        }
        return updatedFriends;
    }

    // TODO updates how much time each person has left
    public void updateTime() {
        // dunno if void is needed
    }

    // availability button
    public void toggleAvailability(View v) {
        if (open) availability.setBackgroundTintList(ColorStateList.valueOf(Color.RED));
        else availability.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
        sendUserData();
        open = !open;
    }

    // send user's availability and update backend
    public void sendUserData() {

    }
}
