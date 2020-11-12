package com.example.icroqueta;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    EditText cumpleaños;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        cumpleaños= findViewById(R.id.cumpleaños);

        cumpleaños.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    if ((start == 1 || start == 4) && (s.toString().charAt(s.length() - 2) != '/')) {
                        cumpleaños.setText(s + "/");
                        cumpleaños.setSelection(cumpleaños.getText().length());
                    }
                }catch(StringIndexOutOfBoundsException e){}
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void afterTextChanged(Editable s) {

            }
        });
    }


    public void openLogin(View view) {
        Intent intent= new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void openBack(View view) {
        Intent intent= new Intent(this, LoginActivity.class);
        startActivity(intent);
    }


}