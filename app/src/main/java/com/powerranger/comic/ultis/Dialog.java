package com.powerranger.comic.ultis;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

import com.powerranger.comic.R;

public class Dialog extends android.app.Dialog {

    private TextView mTextTitle;
    private TextView mTextMsg;
    private Button mButtonCancel;
    private Button mButtonOk;

    public Dialog(@NonNull Context context, Builder builder) {
        super(context);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog);
        setCanceledOnTouchOutside(builder.mCanceledOnTouchOutside);

        mTextTitle = findViewById(R.id.text_title_dialog);
        mTextMsg = findViewById(R.id.text_msg_dialog);
        mButtonCancel = findViewById(R.id.button_cancel);
        mButtonOk = findViewById(R.id.button_ok);

        mTextTitle.setText(builder.mTitle);
        mTextMsg.setText(builder.mMsg);
    }

    public void addClickListener(View.OnClickListener cancelListener, View.OnClickListener okListener) {
        mButtonCancel.setOnClickListener(cancelListener);
        mButtonOk.setOnClickListener(okListener);
    }

    public static class Builder {

        private Context mContext;
        private String mTitle;
        private String mMsg;
        private boolean mCanceledOnTouchOutside;

        public Builder(Context context) {
            mContext = context;
        }

        public Builder setTitle(String title) {
            mTitle = title;
            return this;
        }

        public Builder setMsg(String msg) {
            mMsg = msg;
            return this;
        }

        public Builder setCanceledOnTouchOutside(boolean b) {
            mCanceledOnTouchOutside = b;
            return this;
        }

        public Dialog build() {
            return new Dialog(mContext , this);
        }
    }
}
