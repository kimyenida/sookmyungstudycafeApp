package smu.reservation_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Seat extends AppCompatActivity {
    Button seatBtn[] = new Button[35];
    Button timeBtn[] = new Button[7];
    LinearLayout selectTime;
    boolean isPageOpen = false;
    Button reservationBtn, backBtn;
    TextView costText, selectSeat;
    String seatNum, time, cost;
    Button nowSeat;
    ImageButton homebutton;

    Integer[] buttonID = {
            R.id.seatBtn1, R.id.seatBtn2, R.id.seatBtn3, R.id.seatBtn4,
            R.id.seatBtn5, R.id.seatBtn6, R.id.seatBtn7, R.id.seatBtn8,
            R.id.seatBtn9, R.id.seatBtn10, R.id.seatBtn11, R.id.seatBtn12,
            R.id.seatBtn13, R.id.seatBtn14, R.id.seatBtn15, R.id.seatBtn16,
            R.id.seatBtn17, R.id.seatBtn18, R.id.seatBtn19, R.id.seatBtn20,
            R.id.seatBtn21, R.id.seatBtn22, R.id.seatBtn23, R.id.seatBtn24,
            R.id.seatBtn25, R.id.seatBtn26, R.id.seatBtn27, R.id.seatBtn28,
            R.id.seatBtn29, R.id.seatBtn30, R.id.seatBtn31, R.id.seatBtn32,
            R.id.seatBtn33, R.id.seatBtn34
    };

    Integer[] timeBtnID = {
            R.id.time1, R.id.time2, R.id.time3, R.id.time4, R.id.time5, R.id.time6
    };

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.seat_layout);
        homebutton = (ImageButton) findViewById(R.id.Homebutton_seatac);
        homebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        selectTime = findViewById(R.id.selectTime);
        reservationBtn = findViewById(R.id.reservationBtn);
        backBtn = findViewById(R.id.backBtn);
        costText = findViewById(R.id.costText);
        selectSeat = findViewById(R.id.selectSeat);

        // 좌석 선택
        for (int i=0; i< buttonID.length; i++) {
            int j = i+1;
            seatBtn[i] = findViewById(buttonID[i]);
            seatBtn[i].setText(String.valueOf(j));
        }

        for (int i=0; i< buttonID.length; i++) {
            seatBtn[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    nowSeat = (Button) view;
                    seatNum = nowSeat.getText().toString();
                    selectSeat.setText("선택하신 좌석: " + seatNum);
                    isPageOpen = true;
                    if (isPageOpen) {
                        selectTime.setVisibility(View.VISIBLE);
                    }
                }
            });
        }

        // 시간 선택
        for (int i=0; i< timeBtnID.length; i++) {
            timeBtn[i] = findViewById(timeBtnID[i]);
        }

        for (int i=0; i< timeBtnID.length; i++) {
            timeBtn[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Button selectTime = (Button) view;
                    time = selectTime.getText().toString();
                    switch (view.getId()) {
                        case R.id.time1:
                            costText.setText("가격: 2000원");
                            break;
                        case R.id.time2:
                            costText.setText("가격: 3000원");
                            break;
                        case R.id.time3:
                            costText.setText("가격: 5000원");
                            break;
                        case R.id.time4:
                            costText.setText("가격: 8000원");
                            break;
                        case R.id.time5:
                            costText.setText("가격: 50000원");
                            break;
                        case R.id.time6:
                            costText.setText("가격: 90000원");
                            break;
                        default:
                            break;
                    }
                    cost = costText.getText().toString();
                }
            });
        }


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectTime.setVisibility(View.INVISIBLE);
                isPageOpen = false;
            }
        });

        reservationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nowSeat.setSelected(true);
                Intent intent = new Intent(Seat.this, Payment.class);
                intent.putExtra("seatNum", seatNum);
                intent.putExtra("cost", cost);
                intent.putExtra("time", time);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

}