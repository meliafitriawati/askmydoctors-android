package com.askmydoctors.askmydoctors.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.askmydoctors.askmydoctors.views.ArtikelFragment;
import com.askmydoctors.askmydoctors.views.ChatFragment;
import com.askmydoctors.askmydoctors.views.DiskusiFragment;
import com.askmydoctors.askmydoctors.views.DokterFragment;

/**
 * Created by meliafitriawati on 4/29/2017.
 */

public class LoginAdapter  extends FragmentStatePagerAdapter {
    public LoginAdapter(FragmentManager fm) {
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
                title="Pengguna";
                break;
            case 1:
                title="Dokter";
                break;
        }

        return title;
    }
}
