package android.alcode.com.login.AddPostAvtivity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.alcode.com.login.R;
import android.widget.EditText;

import com.bumptech.glide.load.engine.Resource;

public class AddPostAvtivity extends AppCompatActivity {

    private EditText editTextTitle,editTextSubtitle,editTextDescription;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        editTextTitle = (EditText) findViewById(R.id.edit_text_title);
        editTextSubtitle = (EditText) findViewById(R.id.edit_text_subtitle);
        editTextDescription = (EditText) findViewById(R.id.edit_text_description);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "added to firebase", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_post_avtivity,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                break;
            case R.id.menu_send:
                //FabClickAddOrEditAlarm(null,null);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
