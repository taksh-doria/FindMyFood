package com.maverick.findmyfood.appmain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import com.ismaeldivita.chipnavigation.ChipNavigationBar;
import com.maverick.findmyfood.R;

public class HomeActivity extends AppCompatActivity {

    ChipNavigationBar navbar;
    FragmentManager fragmentManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        navbar=(ChipNavigationBar)findViewById(R.id.navbar);
        fragmentManager=getSupportFragmentManager();
        if (savedInstanceState==null)
        {
            System.out.println("no fragmants selected");
            navbar.setItemSelected(R.id.home,true);
            HomeFragment homeFragment=new HomeFragment();
            fragmentManager.beginTransaction().add(R.id.fragment_container,homeFragment).commit();
        }
        else
        {
            System.out.println("not null saved instance");
        }
        navbar.setOnItemSelectedListener(new ChipNavigationBar.OnItemSelectedListener() {
            @Override
            public void onItemSelected(int i) {
                Fragment fragment=null;
                switch (i)
                {
                    case R.id.home:
                        fragment=new HomeFragment();
                        break;
                    case R.id.account:
                        fragment=new AccountFragment();
                        break;
                    case R.id.favourites:
                        fragment=new FavouriteFragment();
                        break;
                }
                if (fragment!=null)
                {
                    fragmentManager.beginTransaction().replace(R.id.fragment_container,fragment).commit();
                }
                else
                {
                    System.out.print("Error creating fragment");
                }
            }
        });
    }
}