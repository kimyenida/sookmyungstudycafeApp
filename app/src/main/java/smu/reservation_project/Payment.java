package smu.reservation_project;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Payment extends AppCompatActivity {
    TextView seatNum;
    TextView timeText;
    TextView costText;
    Button mainBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.payment_layout);

        seatNum = findViewById(R.id.seatNum);
        timeText = findViewById(R.id.timeText);
        costText = findViewById(R.id.costText);
        mainBtn = findViewById(R.id.mainBtn);

        Intent intent = getIntent();
        String seatnum = intent.getStringExtra("seatNum");
        String cost = intent.getStringExtra("cost");
        String time = intent.getStringExtra("time");
        seatNum.setText("좌석: " + seatnum + "번");
        timeText.setText(time);
        costText.setText(cost);

        mainBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);

            }
        });
    }
}
