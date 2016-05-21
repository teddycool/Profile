package com.scaniahack.profile;


import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.content.Context;

import com.scaniahack.profile.R;

public class Card extends RelativeLayout {
    private TextView title;
    private TextView description;
    private TextView department;
    private ImageView thumbnail;
    private ImageView icon;

    public Card(Context context) {
        super(context);
        init();
    }

    public Card(Context context,ResJson res_jason) {
        super(context);
        init();
        ImageDownloader img_d = new ImageDownloader();
        img_d.setImgView(thumbnail);
        img_d.execute(res_jason.getImageUrl());
        title.setText(res_jason.getName());
        description.setText(res_jason.getRole());
        department.setText(res_jason.getDepartment());
        //thumbnail.setImageDrawable();
    }

    public Card(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Card(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.card, this);
        this.description = (TextView)findViewById(R.id.description);
        this.title = (TextView)findViewById(R.id.title);
        this.thumbnail = (ImageView)findViewById(R.id.thumbnail);
        this.icon = (ImageView)findViewById(R.id.icon);
        this.department = (TextView)findViewById(R.id.department);
    }

    public void setTitle(String str_title) {
        title.setText(str_title);
    }
}