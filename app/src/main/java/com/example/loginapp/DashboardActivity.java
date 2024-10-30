package com.example.loginapp;

public class DashboardActivity extends BaseActivity {


    @Override
    int getContentViewId() {
        return R.layout.activity_profile;
    }

    @Override
    int getNavigationMenuItemId() {
        return R.id.navigation_dashboard;
    }

}