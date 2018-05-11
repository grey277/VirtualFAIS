package com.grey.virtualfais;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.grey.virtualfais.base.BaseActivity;
import com.grey.virtualfais.base.OnFragmentBackOnScreen;
import com.grey.virtualfais.modules.about.AboutFragment;
import com.grey.virtualfais.modules.contact.ContactFragment;
import com.grey.virtualfais.modules.help.HelpFragment;
import com.grey.virtualfais.modules.update.UpdateFragment;

public class MainActivity extends BaseActivity {

    private ImageView image;
    private ImageView image_mask;
    private final String CURRENT = "CURRENT_FRAGMENT";
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    private String currentFragment;

    public static boolean isBackFragment(Fragment fragment) {
        return (fragment instanceof OnFragmentBackOnScreen
                && !(fragment instanceof MainFragment));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        image = findViewById(R.id.image);
        //Use MainFagment
//        Intent intent = new Intent( MainActivity.this, MapView.class );
//        startActivity( intent );

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        bind();
        setupViews();
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putString(CURRENT, currentFragment);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        String restored = savedInstanceState.getString(CURRENT);

        if (restored != null) {
            currentFragment = restored;
            runFragment();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                return false;
        }
        return false;
    }

    public void openDrawer() {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    private void bind() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
    }

    private void setupViews() {

        toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        performDrawerClick(item);
                        return true;
                    }
                });

        attachFragment(MainFragment.newInstance(), MainFragment.TAG);
    }

    public void enableArrow(boolean enable) {

        if (enable) {

            toggle.setDrawerIndicatorEnabled(false);

            ActionBar bar = getSupportActionBar();
            if (bar != null) {
                bar.setDisplayHomeAsUpEnabled(true);
            }

            toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    onBackPressed();
                }
            });


        } else {

            ActionBar bar = getSupportActionBar();
            if (bar != null) {
                bar.setDisplayHomeAsUpEnabled(false);
            }

            toggle.setDrawerIndicatorEnabled(true);

            toggle.setToolbarNavigationClickListener(null);
        }
    }

    private void performDrawerClick(final MenuItem item) {

        item.setChecked(true);
        currentFragment = item.getTitle().toString();
        runFragment();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                item.setChecked(false);
                drawerLayout.closeDrawer(Gravity.START, true);
            }
        }, 200);
    }

    private void runFragment() {
        String title = currentFragment;

        if (title.equalsIgnoreCase(getString(R.string.nav_about_title))) {
            attachFragment(AboutFragment.newInstance("O nas", "subtitle"), AboutFragment.TAG, true);

        } else if (title.equalsIgnoreCase(getString(R.string.nav_contact_title))) {
            attachFragment(ContactFragment.newInstance("Kontakt", "subtitle"), ContactFragment.TAG, true);

        } else if (title.equalsIgnoreCase(getString(R.string.nav_help_title))) {
            attachFragment(HelpFragment.newInstance("Pomoc", "subtitle"), HelpFragment.TAG, true);

        } else if (title.equalsIgnoreCase(getString(R.string.nav_update_title))) {
            attachFragment(UpdateFragment.newInstance("Aktualizacja", "subtitle"), UpdateFragment.TAG, true);

        } else {
            attachFragment(MainFragment.newInstance(), MainFragment.TAG);
        }
    }

    @Override
    protected Toolbar getToolbar() {
        return toolbar;
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.content;
    }

    @Override
    public void onBackPressed() {
        drawerLayout.closeDrawer(Gravity.START, true);

        FragmentManager fm = getSupportFragmentManager();

        if (isBackPressConsumed())
            return;

        if (isBackOnScreenConsumed(fm))
            return;

        Fragment lastFragment = fm.findFragmentById(R.id.content);

        Log.d("lastFragment", lastFragment == null ? "null" : lastFragment.getClass().getSimpleName());

        if (lastFragment != null && !isLastFragment(lastFragment)) {
            attachFragment(MainFragment.newInstance(), MainFragment.TAG);
            return;
        }

        if (!fm.popBackStackImmediate())
            super.onBackPressed();
    }

    private boolean isBackPressConsumed() {
        if (backPressedListeners.size() == 0)
            return false;
        else {
            Log.d("consumeBack", backPressedListeners.getLast().getClass().getSimpleName());
            return backPressedListeners.getLast().onBackPressed();
        }
    }

    private boolean isBackOnScreenConsumed(FragmentManager fragmentManager) {
        int backStackCount = fragmentManager.getBackStackEntryCount();

        if (backStackCount > 0) {
            fragmentManager.popBackStackImmediate();

            int backStackPosition = 0;

            for (Fragment f : fragmentManager.getFragments()) {
                if (!(f instanceof OnFragmentBackOnScreen))
                    continue;

                OnFragmentBackOnScreen soBaseFragment = (OnFragmentBackOnScreen) f;

                if (!soBaseFragment.isBackFragment())
                    continue;

                soBaseFragment.onBackStackChanged(backPressedListeners.getLast().equals(soBaseFragment));
                Log.d("backstack", backStackPosition + "/" + backStackCount + ", " + backPressedListeners.getLast().getClass().getSimpleName() + " " + f.getClass().getSimpleName() + " " + backPressedListeners.getLast().equals(soBaseFragment));

                backStackPosition++;
            }
            return true;
        }
        return false;
    }

    protected boolean isLastFragment(Fragment fragment) {
        return fragment instanceof MainFragment;
    }
}
