package duplex.imfree;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private Button refreshButton;
    private ListView friendsList;
    private ArrayList<String> availableFriends;
    private ArrayAdapter<String> friendsAdapter;
    FloatingActionButton availability;
    boolean open = false; // determines if the user is currently available

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        availability = (FloatingActionButton) findViewById(R.id.fab);
        availability.setBackgroundTintList(ColorStateList.valueOf(Color.RED));

        refreshButton = (Button) findViewById(R.id.test_button);

        availableFriends = updateFriendsList();

        friendsList = (ListView) findViewById(R.id.available_friends_list);
        friendsAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.friends_list_layout, R.id.friend_list_item, availableFriends);
        friendsList.setAdapter(friendsAdapter);

        if (availableFriends.isEmpty()) {
            // hide ListView, display sad panda image and caption "you have no friends"
        }

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                availableFriends = updateFriendsList();
                if (availableFriends.isEmpty()) {
                    // hide ListView, display sad panda image and caption "you have no friends"
                } else {
                    // unhide ListView, hide image and caption
                }
                friendsAdapter.notifyDataSetChanged();
            }
        });
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
        for (int i = 0; i < 10; i++) {
            updatedFriends.add("fucking Sean " + c.get(Calendar.HOUR));
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
        open = !open;
    }
}
