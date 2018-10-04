package com.powerranger.comic.screen.content;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.powerranger.comic.R;
import com.powerranger.comic.ultis.GlideApp;

import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private List<String> mUrlImages;

    public ImageAdapter(List<String> urlImages) {
        mUrlImages = urlImages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_image, parent, false);
        return new ViewHolder(parent.getContext(), view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mUrlImages.get(position));
    }

    @Override
    public int getItemCount() {
        return mUrlImages != null ? mUrlImages.size() : 0;
    }

    public void setUrlImages(List<String> urlImages){
        if(urlImages != null) {
            mUrlImages.addAll(urlImages);
        } else {
            mUrlImages.clear();
        }
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private Context mContext;
        private ImageView mImage;

        public ViewHolder(Context context, View itemView) {
            super(itemView);
            mContext = context;
            mImage = itemView.findViewById(R.id.image_comic);
        }

        private void bind(String urlImage){
            Glide.with(mContext).load(urlImage).into(mImage);
        }
    }
}
