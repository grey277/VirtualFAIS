package com.grey.virtualfais;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.BottomNavigationView;
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
import android.widget.ImageView;

import com.grey.virtualfais.base.BaseActivity;
import com.grey.virtualfais.base.OnFragmentBackOnScreen;
import com.grey.virtualfais.daos.LanguageDao;
import com.grey.virtualfais.models.Language;
import com.grey.virtualfais.models.Level;
import com.grey.virtualfais.models.Room;
import com.grey.virtualfais.modules.contact.ContactFragment;
import com.grey.virtualfais.modules.help.HelpFragment;
import com.grey.virtualfais.modules.search.SearchFragment;
import com.grey.virtualfais.modules.settings.SettingsFragment;
import com.grey.virtualfais.services.AppDatabase;

import java.util.Locale;

public class MainActivity extends BaseActivity {

    private ImageView image;
    private ImageView image_mask;
    private final String CURRENT = "CURRENT_FRAGMENT";
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;
    public String currentFragment;
    private BottomNavigationView bottomNavigation;
    private Level currentLevel = Level.ZERO;
    AppDatabase appDatabase;
    private LanguageDao languageDao;


    public static boolean isBackFragment(Fragment fragment) {
        return (fragment instanceof OnFragmentBackOnScreen
                && !(fragment instanceof MainFragment));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        appDatabase = AppDatabase.getInstance(getApplicationContext());
        UpdateDatabase.apply(getApplicationContext());
        languageDao = appDatabase.languageDao();

        loadLocale();
        refreshApp();
    }

    public void refreshApp() {
        setContentView(R.layout.activity_main);

        image = findViewById(R.id.image);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bind();
        setupViews();

    }

    private void loadLocale() {
        if (null != languageDao.getLanguage()) {
            Language savedLanguage = languageDao.getLanguage();
            setLanguage(savedLanguage.getLanguage());
        }
    }

    public void setLanguage(String language) {
        saveLanguage(language);
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.setLocale(locale);
        getResources().updateConfiguration(
                config,
                getResources().getDisplayMetrics()
        );
        refreshApp();
    }

    private void saveLanguage(String languageToSave)
    {
        languageDao.deleteAllRecords();
        Language language = new Language(languageToSave);
        languageDao.insert(language);
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
        return false;
    }

    public void openDrawer() {
        drawerLayout.openDrawer(GravityCompat.START);
    }

    private void bind() {
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        bottomNavigation = findViewById(R.id.bottom_navigation);
    }

    private void setupViews() {

        toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(item -> {
            performDrawerClick(item);
            return true;
        });


        MainFragment fragment = getMainFragment();

        bottomNavigation.setOnNavigationItemSelectedListener(item -> {
            performLevelChange(item.getItemId());
            return true;
        });

        attachFragment(fragment, MainFragment.TAG);
    }

    private MainFragment getMainFragment() {
        MainFragment fragment = MainFragment.newInstance();

        currentFragment = fragment.getClass().getName();
        Bundle args = new Bundle();
        args.putSerializable("level", currentLevel);
        fragment.setArguments(args);
        return fragment;
    }


    public void enableArrow(boolean enable) {

        if (enable) {

            toggle.setDrawerIndicatorEnabled(false);

            ActionBar bar = getSupportActionBar();
            if (bar != null) {
                bar.setDisplayHomeAsUpEnabled(true);
            }

            toggle.setToolbarNavigationClickListener(v -> onBackPressed());


        } else {

            ActionBar bar = getSupportActionBar();
            if (bar != null) {
                bar.setDisplayHomeAsUpEnabled(false);
            }

            toggle.setDrawerIndicatorEnabled(true);

            toggle.setToolbarNavigationClickListener(null);
        }
    }

    private void performLevelChange(int buttonLevelId) {
        new Handler().post(() -> {
            FragmentManager fm = getSupportFragmentManager();
            Fragment content = fm.findFragmentById(R.id.content);
            if (content instanceof MapViewFragment) {
                ((MapViewFragment) content).setLevel(Level.getByButtonId(buttonLevelId));
            }
        });
    }

    private void performDrawerClick(final MenuItem item) {

        item.setChecked(true);
        currentFragment = item.getTitle().toString();
        runFragment();

        new Handler().postDelayed(() -> {
            item.setChecked(false);
            drawerLayout.closeDrawer(Gravity.START, true);
        }, 200);
    }

    public void runFragment() {
        String title = currentFragment;

        if (title.equalsIgnoreCase(getString(R.string.nav_contact_title))) {
            attachFragment(ContactFragment.newInstance(getString(R.string.nav_contact_title), "subtitle"), ContactFragment.TAG, true);

        } else if (title.equalsIgnoreCase(getString(R.string.nav_help_title))) {
            attachFragment(HelpFragment.newInstance(getString(R.string.nav_help_title), "subtitle"), HelpFragment.TAG, true);

        } else if (title.equalsIgnoreCase(getString(R.string.nav_settings_title))) {
            attachFragment(SettingsFragment.newInstance(getString(R.string.nav_settings_title), "subtitle"), SettingsFragment.TAG, true);
        } else if (title.equalsIgnoreCase(getString(R.string.nav_search_title))) {
            attachFragment(SearchFragment.newInstance(
                    getString(R.string.nav_search_title), "subtitle",
                    roomId -> {
                        Room room = appDatabase.roomDao().getByRoomId(roomId);

                        onBackPressed();

                        FragmentManager fm = getSupportFragmentManager();
                        Fragment content = fm.findFragmentById(R.id.content);

                        if (content instanceof MapViewFragment) {
                            ((MapViewFragment) content).drawPathTo(room, true, true);
                        }

                        Log.i("Search handler", "Found " + room.getId() + " Color(" + room.getColorRed() + ", " + room.getColorGreen() + ", " + room.getColorGreen() + ")");
                    }
            ), SearchFragment.TAG, true);
        } else {
            attachFragment(getMainFragment(), MainFragment.TAG);
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
            attachFragment(getMainFragment(), MainFragment.TAG);
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
