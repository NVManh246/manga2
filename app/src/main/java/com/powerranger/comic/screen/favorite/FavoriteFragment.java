package com.powerranger.comic.screen.favorite;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.powerranger.comic.R;
import com.powerranger.comic.data.model.Comic;
import com.powerranger.comic.screen.detail.ComicDetailActivity;
import com.powerranger.comic.screen.home.ComicAdapterVertical;

import java.util.ArrayList;
import java.util.List;

public class FavoriteFragment extends Fragment implements FavoriteContract.View,
        ComicAdapterVertical.OnClickItemComic, ComicAdapterVertical.OnClickPopupMenu{

    private FavoriteContract.Presenter mPresenter;
    private RecyclerView mRecyclerFavorite;
    private ComicAdapterVertical mComicAdapter;
    private List<Comic> mComics;
    private GridLayoutManager mLayoutManager;
    private int mRemovePosition;

    public static FavoriteFragment newInstance() {
        FavoriteFragment fragment = new FavoriteFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerFavorite = view.findViewById(R.id.recycler_favorite);
        mComics = new ArrayList<>();
        mComicAdapter = new ComicAdapterVertical(mComics, this, this);
        mRecyclerFavorite.setAdapter(mComicAdapter);
        mLayoutManager = new GridLayoutManager(getContext(), 3);
        mRecyclerFavorite.addItemDecoration(new GridSpacingItemDecoration(3, 50, true));
        mRecyclerFavorite.setLayoutManager(mLayoutManager);
        mPresenter = new FavoritePresenter(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        mPresenter.getFavoriteComics();
    }

    @Override
    public void showComics(List<Comic> comics) {
        mComics.clear();
        mComics.addAll(comics);
        mComicAdapter.notifyDataSetChanged();
    }

    @Override
    public void showError() {
        Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
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
    public void showResultFavorite(String action, boolean b) {
        switch (action){
            case "REMOVE":
                if(b){
                    Toast.makeText(getContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                    Log.d("kiemtra", mComics.size() + " - " + mRemovePosition);
                    mComics.remove(mRemovePosition);
                    mComicAdapter.notifyItemRemoved(mRemovePosition);
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
    public Context getFavoriteContext() {
        return getContext();
    }

    @Override
    public void onClickItem(ImageView view, int position) {
        Comic comic = mComics.get(position);
        Intent intent = new Intent(getContext(), ComicDetailActivity.class);
        intent.putExtra(ComicDetailActivity.EXTRA_COMIC, comic.getId());
        ActivityOptionsCompat options = ActivityOptionsCompat
                .makeSceneTransitionAnimation(getActivity(), view, "image_comic");
        startActivity(intent, options.toBundle());
    }

    @Override
    public void onClickPopup(int position, ImageView view) {
        mRemovePosition = position;
        mPresenter.checkFavorite(mComics.get(position), view);
    }
}
