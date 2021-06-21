package com.saurav.apnidukan.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.saurav.apnidukan.fragment.CancelledFragment;
import com.saurav.apnidukan.fragment.DeliveredProductFragment;
import com.saurav.apnidukan.fragment.PendingOrdersFragment;
import com.saurav.apnidukan.fragment.ShippedFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 1:
                return new ShippedFragment();
            case 2:
                return new CancelledFragment();
            case 3:
                return new DeliveredProductFragment();
            default:
                return new PendingOrdersFragment();
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title;
        switch (position){
            case 1:
                title = "Shipped";
                break;
            case 2:
                title = "Cancelled";
                break;
            case 3:
                title = "Delivered";
                break;
            default:
                title = "Pending";
        }
        return title;
    }
}
