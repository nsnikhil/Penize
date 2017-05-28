package com.nrs.nsnik.penize;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;

import com.nrs.nsnik.penize.fragments.MainFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private static final String[] mFragmentTags = {"MainFragment"};
    @BindView(R.id.mainToolBar)
    Toolbar mMainToolbar;
    @BindView(R.id.mainDrawerLayout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.mainNavigationView)
    NavigationView mNavigationView;
    @BindView(R.id.mainContainer)
    FrameLayout mMainContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setTheme(R.style.transparentStatusBar);
        }
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initialize();
        initializeDrawer();
        listeners();
    }

    private void initialize() {
        setSupportActionBar(mMainToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportFragmentManager().beginTransaction().add(R.id.mainContainer, new MainFragment(), mFragmentTags[0]).commit();
    }

    private void initializeDrawer() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.mainDrawerLayout);
        mNavigationView = (NavigationView) findViewById(R.id.mainNavigationView);
        mNavigationView.getMenu().getItem(0).setChecked(true);
        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mMainToolbar, R.string.drawerOpen, R.string.drawerClose) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        mDrawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawers();
                switch (item.getItemId()) {
                    case R.id.navItem1:
                        drawerAction(0);
                        break;
                    case R.id.navItem2:
                        drawerAction(1);
                        break;
                    case R.id.navItem3:
                        drawerAction(2);
                        break;
                    case R.id.navItem4:
                        drawerAction(3);
                        break;
                }
                return false;
            }
        });
    }

    private void drawerAction(int key) {
        invalidateOptionsMenu();
        MenuItem navItem1 = mNavigationView.getMenu().getItem(0).setChecked(false);
        MenuItem navItem2 = mNavigationView.getMenu().getItem(1).setChecked(false);
        MenuItem navItem3 = mNavigationView.getMenu().getItem(2).setChecked(false);
        MenuItem navItem4 = mNavigationView.getMenu().getItem(3).setChecked(false);
        switch (key) {
            case 0:
                navItem1.setChecked(true);
                break;
            case 1:
                navItem2.setChecked(true);
                break;
            case 2:
                navItem3.setChecked(true);
                break;
            case 3:
                navItem4.setChecked(true);
                break;
        }
    }

    public void replaceFragment(Fragment fragment, String tag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.mainContainer, fragment, tag);
        ft.setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out);
        ft.commit();
    }

    private void listeners() {

    }

}
