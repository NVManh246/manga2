package com.powerranger.comic.screen.search;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.powerranger.comic.R;
import com.powerranger.comic.data.model.Comic;

import java.util.List;

public class ComicAdapter extends RecyclerView.Adapter<ComicAdapter.ViewHolder> {

    private List<Comic> mComics;

    public ComicAdapter(List<Comic> comics) {
        mComics = comics;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comic_horizon, parent, false);
        return new ViewHolder(parent.getContext(), view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mComics.get(position));
    }

    @Override
    public int getItemCount() {
        return mComics != null ? mComics.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView mImageComic;
        private TextView mTextTitle;
        private Context mContext;

        public ViewHolder(Context context, View itemView) {
            super(itemView);
            mContext = context;
            mImageComic = itemView.findViewById(R.id.image_comic);
            mTextTitle = itemView.findViewById(R.id.text_name_comic);
        }

        private void bind(Comic comic) {
            mTextTitle.setText(comic.getTitle());
            Glide.with(mContext).load(comic.getCover()).into(mImageComic);
        }
    }
}
