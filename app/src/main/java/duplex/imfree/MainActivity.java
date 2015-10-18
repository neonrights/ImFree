package duplex.imfree;

import android.media.Image;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.content.Intent;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private Button refreshButton;
    private ListView friendsList;
    private ArrayList<String> availableFriends;
    private ArrayAdapter<String> friendsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        refreshButton = (Button) findViewById(R.id.test_button);

        availableFriends = updateFriendsList();

        friendsList = (ListView) findViewById(R.id.available_friends_list);
        friendsAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_expandable_list_item_1, availableFriends);
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

        // toggle available/not button
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // TODO change availability status

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
}
