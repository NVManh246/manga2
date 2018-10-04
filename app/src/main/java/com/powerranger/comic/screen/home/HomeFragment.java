package com.powerranger.comic.screen.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.powerranger.comic.R;
import com.powerranger.comic.data.model.Comic;
import com.powerranger.comic.screen.detail.ComicDetailActivity;
import com.powerranger.comic.ultis.Dialog;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements HomeContract.View,
        ComicAdapterVertical.OnClickItemComic, ComicAdapterVertical.OnClickPopupMenu {

    private static final int ITEM_SPACING = 15;

    private ViewPager mViewPager;
    private List<Comic> mTrendingComic;
    private TrendingComicAdapter mTrendingComicAdapter;
    private LinearLayout mLinearDot;
    private List<Comic> mHotComics;
    private ComicAdapterVertical mHotComicAdapter;
    private RecyclerView mRecyclerHotComic;

    private HomeContract.Presenter mPresenter;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupSlide(view);
        mPresenter = new HomePresenter(this);
        mPresenter.getHotComics();

        setupHotComicRecycler();
    }

    private void setupSlide(View view) {
        mViewPager = view.findViewById(R.id.viewpager);
        mLinearDot = view.findViewById(R.id.linear_dot);
        mRecyclerHotComic = view.findViewById(R.id.recycler_one);

        mTrendingComic = new ArrayList<>();
        mTrendingComic.add(new Comic());
        mTrendingComic.add(new Comic());
        mTrendingComic.add(new Comic());
        mTrendingComic.add(new Comic());
        mTrendingComic.add(new Comic());
        mTrendingComicAdapter = new TrendingComicAdapter(mTrendingComic);
        mViewPager.setAdapter(mTrendingComicAdapter);

    }

    private void initDots(int select) {
        int size = mTrendingComic.size();
        mLinearDot.removeAllViews();
        for(int i = 0; i < size; i++) {
            ImageView imageView = new ImageView(getContext());
            imageView.setImageResource(i == select ? R.drawable.ic_dot_select : R.drawable.ic_dot_unselect);
            imageView.setPadding(5, 5, 5, 5);
            mLinearDot.addView(imageView);
        }
    }

    private void setupHotComicRecycler() {
        mHotComics = new ArrayList<>();
        mHotComicAdapter = new ComicAdapterVertical(mHotComics, this, this);
        mRecyclerHotComic.setAdapter(mHotComicAdapter);
        mRecyclerHotComic.setLayoutManager(new LinearLayoutManager(getContext(),
                LinearLayoutManager.HORIZONTAL, false));
        mRecyclerHotComic.addItemDecoration(new ItemComicVerticalDecoration(ITEM_SPACING));
    }

    @Override
    public void showHotComics(List<Comic> comics) {
        mHotComics.addAll(comics);
        mHotComicAdapter.notifyDataSetChanged();
    }

    @Override
    public void showPopupMenu(final Comic comic, final boolean isFavorite, ImageView view) {
        PopupMenu popupMenu = new PopupMenu(getContext(), view);
        Menu menu = popupMenu.getMenu();
        popupMenu.getMenuInflater().inflate(R.menu.popup_menu_favorite, menu);
        if(isFavorite) {
            menu.findItem(R.id.menu_favorite)
                    .setTitle(getContext().getString(R.string.remove_fav));
        } else {
            menu.findItem(R.id.menu_favorite)
                    .setTitle(getContext().getString(R.string.add_fav));
        }
        popupMenu.show();
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                mPresenter.handelFavorite(comic, isFavorite);
                return true;
            }
        });
    }


    @Override
    public void showError() {
        Dialog.Builder builder = new Dialog.Builder(getContext());
        final Dialog dialog = builder
                .setTitle("Thông Báo")
                .setMsg("Connect errors")
                .setCanceledOnTouchOutside(false)
                .build();
        dialog.show();
        dialog.addClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        }, new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
    }

    @Override
    public void showResultFavorite(String action, boolean b) {
        switch (action){
            case "REMOVE":
                if(b){
                    Toast.makeText(getContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Xóa thất bại", Toast.LENGTH_SHORT).show();
                }
                break;
            case "ADD":
                if(b){
                    Toast.makeText(getContext(), "Thêm thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Thêm thất bại", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    @Override
    public Context getHomeContext() {
        return getContext();
    }

    @Override
    public void onClickItem(ImageView imageView, int position) {
        Comic comic = mHotComics.get(position);
        Intent intent = new Intent(getContext(), ComicDetailActivity.class);
        intent.putExtra(ComicDetailActivity.EXTRA_COMIC, comic.getId());
        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeSceneTransitionAnimation(getActivity(), imageView, "image_comic");
        startActivity(intent, options.toBundle());
    }

    @Override
    public void onClickPopup(int position, ImageView view) {
        mPresenter.checkFavorite(mHotComics.get(position), view);
    }
}
