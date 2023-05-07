package smu.reservation_project;

import static smu.reservation_project.FirebaseID.email;
import static smu.reservation_project.FirebaseID.password;
import static smu.reservation_project.FirebaseID.user;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    TextView btnsug, textEmail, textPW,findtext;
    Button signupBtn;
    ImageButton homebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textEmail = findViewById(R.id.textEmail);
        textPW = findViewById(R.id.textPW);
        btnsug = (TextView) findViewById(R.id.btntextviewsuges);
        signupBtn = (Button) findViewById(R.id.buttonlogin);
        homebutton = (ImageButton) findViewById(R.id.Homebutton_logac);
        findtext = (TextView) findViewById(R.id.findpassword);

        findtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),FindActivity.class);
                startActivity(intent);
            }
        });

        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        btnsug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),SignActivity.class);
                startActivityForResult(intent,101);

            }
        });

        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signInWithEmailAndPassword(textEmail.getText().toString(), textPW.getText().toString())
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    MainActivity.santae=0;

                                    if (user != null) {
                                        Toast.makeText(LoginActivity.this, "로그인 성공", Toast.LENGTH_SHORT).show();
                                        Intent intent = new Intent();
                                        setResult(RESULT_OK,intent);
                                        finish();
                                    }
                                } else {
                                    Toast.makeText(LoginActivity.this, "아이디/비밀번호가 일치하지 않습니다",
                                            Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
            }
        });

    }

}