package com.example.georeview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    static String username;
    String password;
    UserLogModal userLogModal;
    DBHandler dbHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button logBtn = findViewById(R.id.button);
        EditText editUsername = findViewById(R.id.editTextTextPersonName);
        EditText editPassword = findViewById(R.id.editTextTextPassword);

        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = editUsername.getText().toString();
                password = editPassword.getText().toString();
                userLogging(username, password);
            }
        });
    }

    // A placeholder username validation check
    private boolean isUserNameValid(String username) {
        String[] charSequence = {" ", "!", "\"", "#", "$", "%", "&", "'", "(", ")", "*", "+", ",", "-", ".", "/", ":", ";", "<", "=", ">", "?", "@", "[", "\\", "]", "^", "_", "`", "{", "|", "}", "~"};
        if (username == null) return false;
        for (String s : charSequence) if (username.contains(s)) return !username.trim().isEmpty();
        return true;
    }

    // A placeholder password validation check
    private boolean isPasswordValid(String password) {
        String[] charSequence = {" ", "!", "\"", "#", "$", "%", "&", "'", "(", ")", "*", "+", ",", "-", ".", "/", ":", ";", "<", "=", ">", "?", "@", "[", "\\", "]", "^", "_", "`", "{", "|", "}", "~"};
        for (String s : charSequence) if (password.contains(s)) return password.trim().isEmpty();
        return password.trim().length() > 5;
    }
    //A placeholder logging validation check
    private void userLogging(String username, String password) {
        if (isUserNameValid(username) && isPasswordValid(password)) {
            userLogModal = new UserLogModal(username);
            dbHandler.setMETHOD_NAME("login");
            dbHandler.setUserLogModal(userLogModal);
            userLogModal.setLogged(dbHandler.execute(username, password));
        } else {
            Toast.makeText(getApplicationContext(), "Le mot de passe ou le nom d'utilisateur est invalide.", Toast.LENGTH_SHORT).show();
        }
        if (userLogModal.isLogged()) {
            userLogModal.setUsername(username);
            Intent i = new Intent(getApplicationContext(), ReviewActivity.class);
            Bundle b = new Bundle();
            b.putString("username", userLogModal.getUsername());
            b.putBoolean("logged", userLogModal.isLogged());
            startActivity(i, b);
        }
    }
}