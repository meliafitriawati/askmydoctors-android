package com.askmydoctors.askmydoctors.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.askmydoctors.askmydoctors.fragments.ArtikelFragment;
import com.askmydoctors.askmydoctors.fragments.ChatFragment;
import com.askmydoctors.askmydoctors.fragments.DiskusiFragment;

/**
 * Created by meliafitriawati on 5/12/2017.
 */

public class PagerAdapterDokter extends FragmentStatePagerAdapter {
    public PagerAdapterDokter(FragmentManager fm) {
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
        }
        return frag;
    }

    @Override
    public int getCount() {
        return 3;
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
        }

        return title;
    }
}