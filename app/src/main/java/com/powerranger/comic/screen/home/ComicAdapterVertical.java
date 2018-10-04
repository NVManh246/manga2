package com.powerranger.comic.screen.home;

import android.content.Context;
import android.media.Image;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
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

public class ComicAdapterVertical extends RecyclerView.Adapter<ComicAdapterVertical.Viewholder>{

    private List<Comic> mComics;
    private OnClickItemComic mOnClickItemComic;
    private OnClickPopupMenu mOnClickPopupMenu;

    public ComicAdapterVertical(List<Comic> comics, OnClickItemComic onClickItemComic,
                                OnClickPopupMenu onClickPopupMenu) {
        mComics = comics;
        mOnClickItemComic = onClickItemComic;
        mOnClickPopupMenu = onClickPopupMenu;
    }

    @NonNull
    @Override
    public ComicAdapterVertical.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_comic_vertical, parent, false);
        return new Viewholder(parent.getContext(), view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComicAdapterVertical.Viewholder holder, int position) {
        holder.bind(mComics.get(position));
    }

    @Override
    public int getItemCount() {
        return mComics != null ? mComics.size() : 0;
    }

    public class Viewholder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ConstraintLayout mConstraintComic;
        private TextView mTextNameComic;
        private ImageView mImageComic;
        private Context mContext;
        private ImageView mImagePopupMenu;

        public Viewholder(Context context, View itemView) {
            super(itemView);
            mContext = context;
            mConstraintComic = itemView.findViewById(R.id.constraint_comic);
            mTextNameComic = itemView.findViewById(R.id.text_name_comic);
            mImageComic = itemView.findViewById(R.id.image_comic);
            mImagePopupMenu = itemView.findViewById(R.id.image_popup_menu);
            mConstraintComic.setOnClickListener(this);
            mImagePopupMenu.setOnClickListener(this);
        }

        private void bind(Comic comic){
            mTextNameComic.setText(comic.getTitle());
            Glide.with(mContext).load(comic.getCover()).into(mImageComic);
        }

        @Override
        public void onClick(View view) {
            int id = view.getId();
            switch (id) {
                case R.id.constraint_comic:
                    mOnClickItemComic.onClickItem(mImageComic, getAdapterPosition());
                    break;
                case R.id.image_popup_menu:
                    mOnClickPopupMenu.onClickPopup(getAdapterPosition(), mImagePopupMenu);
                    break;
            }
        }
    }

    public interface OnClickItemComic {
        void onClickItem(ImageView view, int position);
    }

    public interface OnClickPopupMenu {
        void onClickPopup(int position, ImageView view);
    }
}
