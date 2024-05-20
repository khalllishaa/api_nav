package com.example.try_out1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class HomeAct extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    TextView nameTextView, emailTextView;
    Button logoutButton;
    ImageView profileImageView;

    // SharedPreferences file name
    private static final String PREFS_NAME = "MyPrefsFile";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        // Pastikan ID yang digunakan benar dan tipe view sesuai
        nameTextView = findViewById(R.id.namea);
        emailTextView = findViewById(R.id.pww);
        logoutButton = findViewById(R.id.logout);
        profileImageView = findViewById(R.id.imageViewProfile);

        String name = sharedPreferences.getString(KEY_NAME, null);
        String email = sharedPreferences.getString(KEY_EMAIL, null);

        if (name != null && email != null) {
            // Set data on TextViews
            nameTextView.setText(name);
            emailTextView.setText(email);
        }

        String imageUrl = "https://i.pinimg.com/564x/fe/fd/26/fefd2613841f0f0392666d0e6e068a46.jpg";
        Glide.with(this).load(imageUrl).into(profileImageView);

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

        // Handling back button to go back to Arsenal activity
        View backs = findViewById(R.id.back);
        backs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Move back to Arsenal activity
                Intent intent = new Intent(HomeAct.this, welcome.class);
                startActivity(intent);
                finish(); // Close the current activity and return to Arsenal activity
            }
        });
    }
}
