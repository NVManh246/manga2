package com.powerranger.comic.screen.detail;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.powerranger.comic.R;

import java.util.List;

public class KindAdapter extends RecyclerView.Adapter<KindAdapter.ViewHolder> {

    private List<String> mKinds;

    public KindAdapter(List<String> kinds) {
        mKinds = kinds;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_kind_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(mKinds.get(position));
    }

    @Override
    public int getItemCount() {
        return mKinds != null ? mKinds.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mTextNameKind;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextNameKind = itemView.findViewById(R.id.text_name_kind);
        }

        private void bind(String kind) {
            mTextNameKind.setText(kind);
        }
    }
}
