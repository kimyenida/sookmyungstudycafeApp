package smu.reservation_project;

import static smu.reservation_project.FirebaseID.user;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    public static final int Code_login = 101;
    Button seatPageBtn, loginBtn, exitBtn, sugBtn;
    public static int santae = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seatPageBtn = findViewById(R.id.seatPageBtn);
        loginBtn = findViewById(R.id.loginBtn);
        exitBtn = findViewById(R.id.exitBtn);
        sugBtn = findViewById(R.id.sugBtn);
        Intent intent2 = getIntent();
        Boolean isUser = intent2.getBooleanExtra("isUser", false);

        seatPageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (santae==0) {
                    Intent intent = new Intent(MainActivity.this, Seat.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "로그인 후 이용하실 수 있습니다.",
                            Toast.LENGTH_SHORT).show();
                }

            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (santae == 1) {

                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivityForResult(intent, Code_login);
                }
                else if (santae == 0){
                    Toast.makeText(getApplicationContext(), "로그아웃완료", Toast.LENGTH_SHORT).show();

                    logout();
                    santae =1;


                }
            }
        });
        exitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (santae == 0) {
                    new AlertDialog.Builder(MainActivity.this)
                            .setTitle("숙명 스터디카페 퇴실").setMessage("정말로 퇴실하시겠습니까?")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    loginBtn.setText("회원가입/로그인");
                                    FirebaseAuth.getInstance().signOut();
                                    santae = 1;
                                    Toast.makeText(MainActivity.this, "퇴실이 완료되었습니다.", Toast.LENGTH_SHORT).show();

                                    finish();

                                }
                            }).setNegativeButton("NO", null)
                            .show();
                } else {
                    Toast.makeText(MainActivity.this, "로그인 후 이용하실 수 있습니다.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        sugBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (santae ==0) {
                    Intent intent = new Intent(MainActivity.this, SugActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(MainActivity.this, "로그인 후 이용하실 수 있습니다.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    public void onActivityResult(int requestcode, int resultcode, @Nullable Intent data){
        super.onActivityResult(requestcode,resultcode,data);
        if (requestcode==Code_login){

            if (resultcode==RESULT_OK){
                Toast.makeText(getApplicationContext(), "good!", Toast.LENGTH_SHORT).show();
                loginBtn.setText("로그아웃");

            }
        }
    }
    private void logout(){
        loginBtn.setText("회원가입/로그인");
        FirebaseAuth.getInstance().signOut();
        finish();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}