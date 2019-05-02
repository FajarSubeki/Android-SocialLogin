package com.example.sociallogin;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.sociallogin.managers.SharedPreferenceManager;
import com.example.sociallogin.model.UserModel;
import com.facebook.login.LoginFragment;

import androidx.fragment.app.FragmentActivity;

public class LoginActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UserModel userModel = SharedPreferenceManager.getSharedInstance().getUserModelFromPreferences();
        if(userModel!=null) {
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra(UserModel.class.getSimpleName(), userModel);
            startActivity(intent);
            finishAffinity();
        }
        else
        {
            // Check that the activity is using the layout version with
            // the fragment_container FrameLayout
            if (findViewById(R.id.content_frame) != null) {

                // However, if we're being restored from a previous state,
                // then we don't need to do anything and should return or else
                // we could end up with overlapping fragments.
                if (savedInstanceState != null) {
                    return;
                }

                // Create a new Fragment to be placed in the activity layout
                LoginFragment loginFragment = new LoginFragment();

                // In case this activity was started with special instructions from an
                // Intent, pass the Intent's extras to the fragment as arguments
                loginFragment.setArguments(getIntent().getExtras());

                // Add the fragment to the 'fragment_container' FrameLayout
                getSupportFragmentManager().beginTransaction().add(R.id.content_frame, loginFragment).commit();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        LoginFragment loginFragment = (LoginFragment) getSupportFragmentManager().findFragmentById(R.id.content_frame);
        if(loginFragment!=null)
        {
            loginFragment.onActivityResult(requestCode, resultCode, data);
        }
    }
}
