package smu.reservation_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class SignActivity extends AppCompatActivity {
    private EditText eid,epw, eCheckPW;
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    boolean isUser;
    ImageButton homebutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Button signup;

        signup = (Button) findViewById(R.id.buttonsignup);
        eid = (EditText) findViewById(R.id.editID);
        epw = (EditText) findViewById(R.id.editPW);
        homebutton = (ImageButton) findViewById(R.id.Homebutton_signac);
        eCheckPW = (EditText) findViewById(R.id.editCheckPW);

        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (epw.getText().toString().equals(eCheckPW.getText().toString())) {
                    mAuth.createUserWithEmailAndPassword(eid.getText().toString(), epw.getText().toString())
                            .addOnCompleteListener(SignActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        if (user != null) {
                                            Map<String, Object> userMap = new HashMap<>();
                                            userMap.put(FirebaseID.documentId, user.getUid());
                                            userMap.put(FirebaseID.email, eid.getText().toString());
                                            userMap.put(FirebaseID.password, epw.getText().toString());
                                            mStore.collection(FirebaseID.user).document(user.getUid()).set(userMap, SetOptions.merge());
                                            finish();
                                        }

                                    } else {
                                        Toast.makeText(SignActivity.this, "Sign up error",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else {
                    Toast.makeText(SignActivity.this, "비밀번호가 일치하지 않음", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

}