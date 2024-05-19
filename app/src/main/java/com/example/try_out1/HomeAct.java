package com.example.try_out1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class HomeAct extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    TextView nameTextView, emailTextView;
    Button logoutButton;

    // SharedPreferences file name
    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        nameTextView = findViewById(R.id.namee);
        emailTextView = findViewById(R.id.emaill);
        logoutButton = findViewById(R.id.logout);

        String name = sharedPreferences.getString(KEY_NAME, null);
        String email = sharedPreferences.getString(KEY_EMAIL, null);

        if (name != null && email != null) {
            // Set data on TextViews
            nameTextView.setText(name);
            emailTextView.setText(email);
        }

        // Button for logout session
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear(); // Clear all data from SharedPreferences
                editor.apply(); // Save the changes
                Toast.makeText(HomeAct.this, "Logout successfully", Toast.LENGTH_SHORT).show();

                // Move back to MainActivity
                Intent intent = new Intent(HomeAct.this, MainActivity.class);
                startActivity(intent);
                finish(); // Close the current activity after logout
            }
        });
    }
}
