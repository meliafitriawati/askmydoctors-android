package com.askmydoctors.askmydoctors.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.askmydoctors.askmydoctors.views.ArtikelFragment;
import com.askmydoctors.askmydoctors.views.ChatFragment;
import com.askmydoctors.askmydoctors.views.DiskusiFragment;
import com.askmydoctors.askmydoctors.views.DokterFragment;

/**
 * Created by meliafitriawati on 4/1/2017.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    public PagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        Fragment frag=null;
        switch (position){
            case 0:
                frag=new ChatFragment();
                break;
            case 1:
                frag=new DiskusiFragment();
                break;
            case 2:
                frag=new ArtikelFragment();
                break;
            case 3:
                frag=new DokterFragment();
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title=" ";
        switch (position){
            case 0:
                title="Chat";
                break;
            case 1:
                title="Diskusi";
                break;
            case 2:
                title="Artikel";
                break;
            case 3:
                title="Dokter";
                break;
        }

        return title;
    }
}