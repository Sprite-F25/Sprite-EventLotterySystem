package com.example.sprite;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sprite.MainActivity;
import com.example.sprite.R;
import com.example.sprite.Models.User;
import com.example.sprite.Controllers.Authentication_Service;

public class SignUpActivity extends AppCompatActivity {
    private EditText nameField, emailField, passwordField,ConfirmPasswordField, PhoneNumberField;
    private Button signUpButton;
    private TextView loginRedirectText;
    private Authentication_Service authService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_screen);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        authService = new Authentication_Service();

        nameField = findViewById(R.id.inputFullName);
        emailField = findViewById(R.id.inputEmail);
        PhoneNumberField = findViewById(R.id.inputPhone);
        passwordField = findViewById(R.id.inputPassword);
        ConfirmPasswordField = findViewById(R.id.inputConfirmPassword);
        signUpButton = findViewById(R.id.btnSignUp);
        ImageButton backButton = findViewById(R.id.btnBackSignUp);
        //loginRedirectText = findViewById(R.id.loginRedirectText);

        signUpButton.setOnClickListener(v -> attemptSignUp());
//        loginRedirectText.setOnClickListener(v ->
//                startActivity(new Intent(this, SignInActivity.class))
//        );

        backButton.setOnClickListener(v -> {
            startActivity(new Intent(this, WelcomeActivity.class));
            finish();
        });
    }

    private void attemptSignUp() {
        String name = nameField.getText().toString().trim();
        String email = emailField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if (password.length() < 6) {
            Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show();
            return;
        }

        signUpButton.setEnabled(false);
        signUpButton.setText("Creating account...");

        authService.createUserWithEmail(email, password, name, User.UserRole.ENTRANT, new Authentication_Service.AuthCallback() {
            @Override
            public void onSuccess(User user) {
                Toast.makeText(SignUpActivity.this, "Welcome, " + user.getName(), Toast.LENGTH_SHORT).show();
                startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                finish();
            }

            @Override
            public void onFailure(String error) {
                signUpButton.setEnabled(true);
                signUpButton.setText("Sign Up");
                Toast.makeText(SignUpActivity.this, "Sign-up failed: " + error, Toast.LENGTH_LONG).show();
            }
        });
    }
}
