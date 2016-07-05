package com.example.krnx.padawan;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

/**
 * Created by arnau on 05/07/16.
 */
public class PlayerActivity extends BaseActivity {
    private MediaPlayer mediaPlayer = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


//                ActivityCompat.requestPermissions(PlayerActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);


        int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_PHONE_STATE)) {
                Snackbar.make(findViewById(R.id.player_layout), "Location access is required to show coffee shops nearby.", Snackbar.LENGTH_LONG).
                        setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ActivityCompat.requestPermissions(PlayerActivity.this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
                            }
                        }).show();
//                                v => ActivityCompat.requestPermissions(this, Manifest.permission.READ_PHONE_STATE));


                //showExplanation("Permission Needed", "Rationale", Manifest.permission.READ_PHONE_STATE, 1);
                Toast.makeText(this, "Need permission", Toast.LENGTH_LONG).show();
            } else {
                //requestPermission(Manifest.permission.READ_PHONE_STATE, 1);
                Toast.makeText(this, "Need permission2", Toast.LENGTH_LONG).show();
                Snackbar.make(findViewById(R.id.player_layout), "Location access is required to show coffee shops nearby.", Snackbar.LENGTH_LONG).
                        setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                ActivityCompat.requestPermissions(PlayerActivity.this, new String[]{Manifest.permission.READ_PHONE_STATE}, 1);
                            }
                        }).show();
            }
        } else {
            Toast.makeText(PlayerActivity.this, "Permission (already) Granted!", Toast.LENGTH_SHORT).show();
        }





            /*if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                // Should we show an explanation?
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    // Show an expanation to the user *asynchronously* -- don't block
                    // this thread waiting for the user's response! After the user
                    // sees the explanation, try again to request the permission.
                    Toast.makeText(this, "Need permission", Toast.LENGTH_LONG).show();
                } else {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 0);
                }
            }*/
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 0:
                Toast.makeText(this, "RC: " + grantResults[0], Toast.LENGTH_LONG).show();
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Intent file = new Intent(Intent.ACTION_PICK, MediaStore.Audio.Media.EXTERNAL_CONTENT_URI);
//                    file.setType("file/*");
                    startActivityForResult(file, 0);
                } else {
                    Toast.makeText(this, "Nop!", Toast.LENGTH_LONG).show();
                }
                return;
            case 1:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(PlayerActivity.this, "Permission Granted!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(PlayerActivity.this, "Permission Denied!", Toast.LENGTH_SHORT).show();
                }
                break;

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Si la petición se hizo correctamente y requestCode es 0
        if (resultCode == Activity.RESULT_OK && requestCode == 0) {
//            Toast.makeText(this, "File Ok", Toast.LENGTH_LONG).show();
            Uri uri = data.getData();
//            Toast.makeText(this, "File:  " + uri.toString(), Toast.LENGTH_LONG).show();
            File song = new File(uri.getPath());

//            File sdCard = Environment.getExternalStorageDirectory();
//            File song = new File(sdCard.getAbsolutePath() + "/Downloads/spinningmerkaba_-_Summertime_Featuring_JeffSpeed68.mp3");

            mediaPlayer = new MediaPlayer();
            try {
                Log.v("Player", song.getAbsolutePath());
//                Toast.makeText(this, "File:  " + song.getAbsolutePath().toString(), Toast.LENGTH_LONG).show();
                mediaPlayer.reset();
                mediaPlayer.setDataSource(this, uri);
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), "Ha fallat IO: " + e.getMessage(), Toast.LENGTH_LONG).show();
                Log.v("Player", "Ha fallat IO: " + e.getMessage());
            }
//                mediaPlayer.prepare();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                public void onPrepared(MediaPlayer mp) {
                    Toast.makeText(getApplicationContext(), "Comença!", Toast.LENGTH_SHORT).show();
                    mediaPlayer.start();
                }
            });
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                public void onCompletion(MediaPlayer mp) {
                    Toast.makeText(getApplicationContext(), "This is the end!", Toast.LENGTH_SHORT).show();
                    //finish();
                }
            });
            mediaPlayer.prepareAsync();

        } else
            Toast.makeText(this, "File Failed", Toast.LENGTH_LONG).show();

    }

    private void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        releaseMediaPlayer();
    }

    protected void onPause() {
        super.onPause();
        if (mediaPlayer.getAudioSessionId() != 0) {
            mediaPlayer.pause();
            if (isFinishing()) {
                mediaPlayer.stop();
                mediaPlayer.release();
            }
        }
    }

}