package com.example.krnx.padawan;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.material.joanbarroso.flipper.CoolImageFlipper;

import java.lang.reflect.Array;

/**
 * Created by inlab on 07/07/2016.
 */
public class MemoryActivity extends BaseActivity implements View.OnClickListener {
    CoolImageFlipper imatge;
    ImageView card[];
    Integer memo[];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);

        imatge = new CoolImageFlipper(getApplicationContext());
        card = new ImageView[16];
        memo = new Integer[16];

        for (int i = 0; i < 16; i++) {
            card[i] = (ImageView) findViewById(getResources().getIdentifier("memory_card"+i,"id",MemoryActivity.this.getPackageName()));
            card[i].setOnClickListener(this);

//          memo[i]


        }





    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            default:
                imatge.flipImage(getDrawable(R.mipmap.ic_launcher), (ImageView) view);
                break;
        }
    }
}
