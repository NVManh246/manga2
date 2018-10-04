package com.powerranger.comic.screen.main;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.powerranger.comic.R;
import com.powerranger.comic.screen.favorite.FavoriteFragment;
import com.powerranger.comic.screen.home.HomeFragment;
import com.powerranger.comic.screen.kind.KindFragment;
import com.powerranger.comic.screen.search.SearchActivity;


public class MainActivity extends AppCompatActivity
        implements BottomNavigationView.OnNavigationItemSelectedListener{

    private FragmentManager mFragmentManager;
    private HomeFragment mHomeFragment;
    private KindFragment mKindFragment;
    private FavoriteFragment mFavoriteFragment;
    private Fragment mCurrentFragment;
    private BottomNavigationView mBottomNavigation;
    private TextView mTextTitle;
    private ImageView mImageSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBottomNavigation = findViewById(R.id.bottom_navigation);
        mTextTitle = findViewById(R.id.text_title);
        mImageSearch = findViewById(R.id.image_search);

        mBottomNavigation.setOnNavigationItemSelectedListener(this);
        mFragmentManager = getSupportFragmentManager();
        initFragment();
        CoordinatorLayout.LayoutParams layoutParams =
                (CoordinatorLayout.LayoutParams) mBottomNavigation.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationBehavior());
        BottomNavigationViewHelper.disableShiftMode(mBottomNavigation);
        mImageSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = SearchActivity.getSearchItent(MainActivity.this);
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.menu_home:
                showFragment(mHomeFragment, mCurrentFragment);
                mCurrentFragment = mHomeFragment;
                mTextTitle.setText(R.string.home);
                break;
            case R.id.menu_kind:
                showFragment(mKindFragment, mCurrentFragment);
                mCurrentFragment = mKindFragment;
                mTextTitle.setText(R.string.kind);
                break;
            case R.id.menu_3:
                showFragment(mFavoriteFragment, mCurrentFragment);
                mCurrentFragment = mFavoriteFragment;
                break;
        }
        return true;
    }

    private void initFragment(){
        mHomeFragment = HomeFragment.newInstance();
        mKindFragment = KindFragment.newInstance();
        mFavoriteFragment = FavoriteFragment.newInstance();
        mFragmentManager.beginTransaction()
                .add(R.id.frame_container, mHomeFragment)
                .add(R.id.frame_container, mKindFragment)
                .add(R.id.frame_container, mFavoriteFragment)
                .commit();
        mFragmentManager.beginTransaction()
                .hide(mKindFragment)
                .hide(mFavoriteFragment)
                .show(mHomeFragment)
                .commit();
        mCurrentFragment = mHomeFragment;
    }

    private void showFragment(Fragment fShow, Fragment fHide){
        mFragmentManager.beginTransaction().hide(fHide).show(fShow).commit();
    }
}
