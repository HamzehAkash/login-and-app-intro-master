package android.alcode.com.login;

import com.firebase.client.Firebase;

/**
 * Created by echessa on 3/10/16.
 */
public class TheApplication extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);
    }

}