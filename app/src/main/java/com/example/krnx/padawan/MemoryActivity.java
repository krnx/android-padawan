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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);

        imatge = new CoolImageFlipper(getApplicationContext());

        card = new ImageView[16];
        for (int i = 0; i < 16; i++) {
            Log.v("Memory", "num:"+i);
            card[i] = (ImageView) findViewById(getResources().getIdentifier("memory_card"+i,"id",MemoryActivity.this.getPackageName()));
            card[i].setOnClickListener(this);
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.memory_card0:
                imatge.flipImage(getDrawable(R.mipmap.ic_launcher), (ImageView) view);
                break;
            case R.id.memory_card1:
                imatge.flipImage(getDrawable(R.mipmap.ic_launcher), (ImageView) view);
                break;
            case R.id.memory_card2:
                imatge.flipImage(getDrawable(R.mipmap.ic_launcher), (ImageView) view);
                break;
            case R.id.memory_card3:
                imatge.flipImage(getDrawable(R.mipmap.ic_launcher), (ImageView) view);
                break;
            case R.id.memory_card4:
                imatge.flipImage(getDrawable(R.mipmap.ic_launcher), (ImageView) view);
                break;
            case R.id.memory_card5:
                imatge.flipImage(getDrawable(R.mipmap.ic_launcher), (ImageView) view);
                break;
            case R.id.memory_card6:
                imatge.flipImage(getDrawable(R.mipmap.ic_launcher), (ImageView) view);
                break;
            case R.id.memory_card7:
                imatge.flipImage(getDrawable(R.mipmap.ic_launcher), (ImageView) view);
                break;
            case R.id.memory_card8:
                imatge.flipImage(getDrawable(R.mipmap.ic_launcher), (ImageView) view);
                break;
            case R.id.memory_card9:
                imatge.flipImage(getDrawable(R.mipmap.ic_launcher), (ImageView) view);
                break;
            case R.id.memory_card10:
                imatge.flipImage(getDrawable(R.mipmap.ic_launcher), (ImageView) view);
                break;
            case R.id.memory_card11:
                imatge.flipImage(getDrawable(R.mipmap.ic_launcher), (ImageView) view);
                break;
            case R.id.memory_card12:
                imatge.flipImage(getDrawable(R.mipmap.ic_launcher), (ImageView) view);
                break;
            case R.id.memory_card13:
                imatge.flipImage(getDrawable(R.mipmap.ic_launcher), (ImageView) view);
                break;
            case R.id.memory_card14:
                imatge.flipImage(getDrawable(R.mipmap.ic_launcher), (ImageView) view);
                break;
            case R.id.memory_card15:
                imatge.flipImage(getDrawable(R.mipmap.ic_launcher), (ImageView) view);
                break;
            default:
                break;
        }
    }
}
