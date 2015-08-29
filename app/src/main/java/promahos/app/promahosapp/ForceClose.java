package promahos.app.promahosapp;

import android.app.Activity;
import android.os.Bundle;

import promahos.app.error.ExceptionHandler;
import promahos.rbazua.promahosapp.R;


public class ForceClose extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Thread.setDefaultUncaughtExceptionHandler(new ExceptionHandler(this));
        setContentView(R.layout.activity_force_close);
    }
}
