package com.powerranger.comic.screen.content;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.powerranger.comic.R;
import com.powerranger.comic.data.model.Chapter;

import java.util.List;

public class ChapAdapter extends RecyclerView.Adapter<ChapAdapter.ViewHolder> {

    private List<Chapter> mChapters;
    private OnClickItemChapterListener mListener;

    public ChapAdapter(List<Chapter> chapters, OnClickItemChapterListener listener) {
        mChapters = chapters;
        mListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_chapter, parent, false);
        return new ViewHolder(parent.getContext(), view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mChapters.get(position));
    }

    @Override
    public int getItemCount() {
        return mChapters != null ? mChapters.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private Context mContext;
        private TextView mTextChapter;
        private OnClickItemChapterListener mListener;

        public ViewHolder(Context context, View itemView, OnClickItemChapterListener listener) {
            super(itemView);
            mContext = context;
            mListener = listener;
            mTextChapter = itemView.findViewById(R.id.text_name_chapter);
            mTextChapter.setOnClickListener(this);
        }

        @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
        private void bind(Chapter chapter) {
            mTextChapter.setText(chapter.getName());
            if(chapter.isReading()) {
                mTextChapter.setBackgroundColor(mContext.getResources().getColor(R.color.colorPrimaryBlur));
                mTextChapter.setTextColor(Color.WHITE);
            } else {
                mTextChapter.setBackground(mContext.getResources().getDrawable(R.drawable.effect_click_kind));
                mTextChapter.setTextColor(mContext.getResources().getColor(R.color.color_dark_gray));
            }
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(getAdapterPosition());
        }
    }

    public interface OnClickItemChapterListener {
        void onClick(int position);
    }
}
