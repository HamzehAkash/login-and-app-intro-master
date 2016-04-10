package android.alcode.com.login.RegisrationActivity;


import android.alcode.com.login.R;
import android.alcode.com.login.SlidesActivities.AppIntroActivity.AppTutorial;
import android.alcode.com.login.SlidesActivities.DeveloperActivity.TechnologyUsed;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewConfiguration;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.lang.reflect.Field;

public class RegistrationActivity extends AppCompatActivity implements OnFragmentInteractionListener {

    private static final String TAG = RegistrationActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setStatusBarTranslucent(true);
        supportRequestWindowFeature(Window.FEATURE_ACTION_BAR_OVERLAY);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);
        //region action bar init
        ActionBar ab = getSupportActionBar();
        ab.setElevation(0);
        makeActionOverflowMenuShown();
        //endregion

        //region add SignupOrLoginFragment
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, SignupOrLoginFragment.newInstance(), SignupOrLoginFragment.TAG).commit();
        //endregion

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_signup, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_how_it_work:
                startActivity(new Intent(this,AppTutorial.class ));
                return true;
            case R.id.action_are_you_develover:
                startActivity(new Intent(this,TechnologyUsed.class ));
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onFragmentInteraction(String event) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.enter, R.anim.exit, R.anim.pop_enter, R.anim.pop_exit);

        Fragment fragment;
        String tag;
        if (event.equals("login_with_email")){
            fragment = LoginFragment.newInstance();
            tag=LoginFragment.TAG;
        }
        else if(event.equals("register_with_email")){//register
            fragment = SignupFragment.newInstance();
            tag= SignupFragment.TAG;
        }
        else if(event.equals("register_later")){//register
            Toast.makeText(getApplicationContext(), "go To main Activity", Toast.LENGTH_LONG).show();
            return;
        }
        else if(event.equals("logged_in")){//register
            Toast.makeText(getApplicationContext(), "go To main Activity", Toast.LENGTH_LONG).show();
            return;
        }
        else {
           return;
        }
        transaction.replace(R.id.fragment_container, fragment,tag);
        transaction.addToBackStack(tag);
        //transaction.remove(fragmentManager.findFragmentByTag(SignupOrLoginFragment.TAG));
        transaction.commit();

    }

    private void makeActionOverflowMenuShown() {
        //devices with hardware menu button (e.g. Samsung Note) don't show action overflow menu
        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            if (menuKeyField != null) {
                menuKeyField.setAccessible(true);
                menuKeyField.setBoolean(config, false);
            }
        } catch (Exception e) {
            Log.d(TAG, e.getLocalizedMessage());
        }
    }

    protected void setStatusBarTranslucent(boolean makeTranslucent) {
        if (makeTranslucent) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        } else {
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }
}
