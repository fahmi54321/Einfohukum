package com.example.einfohukum.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.einfohukum.HomeActivity;
import com.example.einfohukum.R;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;

public class LoginActivity extends AppCompatActivity {

    EditText editTextUsername, editTextPassword;
    Button bttnLogin;
    ProgressBar progressBar;
    FragmentManager fm = getSupportFragmentManager();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//       this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        init();
    }
    void init(){
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        bttnLogin = findViewById(R.id.buttonLogin);
        progressBar = findViewById(R.id.progressBar);
        //if user presses on login calling the method login
        findViewById(R.id.buttonLogin).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                bttnLogin.setText("Loading...");
                bttnLogin.setEnabled(false);
                userLogin();
            }
        });

    }
    private void userLogin() {
        //first getting the values
        final String username = editTextUsername.getText().toString();
        final String password = editTextPassword.getText().toString();
        //validating inputs
        if (TextUtils.isEmpty(username)) {
            progressBar.setVisibility(View.GONE);
            bttnLogin.setText("Login");
            bttnLogin.setEnabled(true);
            editTextUsername.setError("Please enter username");
            editTextUsername.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            progressBar.setVisibility(View.GONE);
            bttnLogin.setText("Login");
            bttnLogin.setEnabled(true);
            editTextPassword.setError("Please enter password");
            editTextPassword.requestFocus();
            return;
        }
        //if everything is fine

        UserLogin ul = new UserLogin(username,password);
        ul.execute();
    }
    class UserLogin extends AsyncTask<Void, Void, String> {

        String username, password;
        UserLogin(String username,String password) {
            this.username = username;
            this.password = password;
        }
        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            bttnLogin.setText("Loading...");
            bttnLogin.setEnabled(false);
            progressBar.setVisibility(View.VISIBLE);
        }
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                //converting response to json object
                JSONObject obj = new JSONObject(s);

                //if no error in response
                if (!obj.getBoolean("error")) {
                    Toast.makeText(getApplicationContext(), obj.getString("message"), Toast.LENGTH_SHORT).show();

                    //getting the user from the response
                    JSONObject userJson = obj.getJSONObject("user");

                    //creating a new user object
                    User user = new User(
                            userJson.getInt("id"),
                            userJson.getString("username"),
                            userJson.getString("kesatuan")
                    );

                    PrefManager.getInstance(getApplicationContext()).setUserLogin(user);
                    //starting the profile

                    startActivity(new Intent(getApplicationContext(), HomeActivity.class));
                    finish();
                } else {
                    progressBar.setVisibility(View.GONE);
                    bttnLogin.setText("Login");
                    bttnLogin.setEnabled(true);
                    Toast.makeText(getApplicationContext(), "Invalid username or password", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                progressBar.setVisibility(View.GONE);
                bttnLogin.setText("Login");
                bttnLogin.setEnabled(true);
                e.printStackTrace();
            }
        }

        @Override
        protected String doInBackground(Void... voids) {
            //creating request handler object
            RequestHandler requestHandler = new RequestHandler();

            //creating request parameters
            HashMap<String, String> params = new HashMap<>();
            params.put("username", username);
            params.put("password", password);

            //returing the response
            return requestHandler.sendPostRequest(URLS.URL_LOGIN, params);
        }
    }
}
