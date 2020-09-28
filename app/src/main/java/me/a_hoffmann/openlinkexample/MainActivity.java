package me.a_hoffmann.openlinkexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    EditText urlInput;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        urlInput = findViewById(R.id.urlInput);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onOpenUrlButtonClicked();
            }
        });
    }

    private void onOpenUrlButtonClicked() {
        String inputString = urlInput.getText().toString();
        Uri parsedUrl = null;
        try {
            parsedUrl = Uri.parse(inputString);
        }catch (Exception ex){
            Log.e(TAG, "Failed to parse input to a URL. Input: " + inputString);
            Toast.makeText(this, "Failed to parse URL", Toast.LENGTH_LONG).show();
        }

        if(parsedUrl != null){
            try {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(parsedUrl);
                startActivity(intent);
            }catch (ActivityNotFoundException ex){
                Toast.makeText(this, "Can't open URL. Did you enter a valid URL (https://...)", Toast.LENGTH_LONG).show();
            }

        }
    }
}