package com.example.sociallogin;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.sociallogin.managers.SharedPreferenceManager;
import com.example.sociallogin.model.UserModel;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.material.navigation.NavigationView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.nav_view)
    NavigationView navigationView;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    SimpleDraweeView simpleDraweeView;

    TextView nameTextView;

    TextView emailTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);
        ab.setDisplayHomeAsUpEnabled(true);

        UserModel userModel = getUserModelFromIntent();
        if(userModel!=null)
            setDataOnNavigationView(userModel);
    }

    private void setDataOnNavigationView(UserModel userModel) {
        if (navigationView != null) {
            setupDrawerContent(userModel);
        }

        navigationView.setNavigationItemSelectedListener(

                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        menuItem.setChecked(true);
                        switch (menuItem.getItemId()) {
                            case R.id.nav_sign_out:
                                drawerLayout.closeDrawers();
                                SharedPreferenceManager.getSharedInstance().clearAllPreferences();
                                startLoginActivity();
                                return true;
                            default:
                                return true;
                        }
                    }
                });
    }

    private void setupDrawerContent(UserModel userModel) {
        View headerView = navigationView.getHeaderView(0);

        simpleDraweeView = ButterKnife.findById(headerView, R.id.user_imageview);
        simpleDraweeView.setImageURI(Uri.parse(userModel.profilePic));

        nameTextView = ButterKnife.findById(headerView, R.id.name_textview);
        nameTextView.setText(userModel.userName);

        emailTextView = ButterKnife.findById(headerView, R.id.email_textview);
        emailTextView.setText(userModel.userEmail);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_another, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onSignOut(View view) {
        finish();
    }

    private UserModel getUserModelFromIntent()
    {
        Intent intent = getIntent();
        return intent.getParcelableExtra(UserModel.class.getSimpleName());
    }

    private void startLoginActivity()
    {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finishAffinity();
    }
}
