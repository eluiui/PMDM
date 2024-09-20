package com.example.e11_listas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class like extends AppCompatActivity {
    boolean salidatriangulo=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_like);
        int colorFondo = getIntent().getIntExtra("colorFondo", -1);
        if (colorFondo != -1) {
            View view = findViewById(R.id.color);
            view.setBackgroundResource(colorFondo);
        }
    }

    public void onclickrb(View view) {
        if (view.getId() == R.id.entendido) {
            RadioGroup radioGroup = findViewById(R.id.rg);
            int selectedRadioButtonId = radioGroup.getCheckedRadioButtonId();
            if (selectedRadioButtonId != 0) {
                RadioButton radioButton = findViewById(selectedRadioButtonId);
                switch (radioButton.getId()) {
                    case R.id.rb_si:
                        salidatriangulo=true;
                        finish();
                        Toast.makeText(this, "Me alegra que te guste", Toast.LENGTH_SHORT).show();

                        break;
                    case R.id.rb_no:
                        salidatriangulo=true;
                        Intent returnIntent = new Intent();
                        returnIntent.putExtra("resultOk", true);
                        setResult(externo.RESULT_OK, returnIntent);
                        finish();
                        break;
                }
                finish();
            }
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Toast.makeText(this, "El usuario no contesta", Toast.LENGTH_SHORT).show();
    }
}
