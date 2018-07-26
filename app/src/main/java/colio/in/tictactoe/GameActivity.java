package colio.in.tictactoe;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GameActivity extends AppCompatActivity {
    Button btnInput;
    Button btnLeft;
    Button btnMid;
    Button btnRight;
    Boolean isFirstPlayer = true;
    Drawable background;
    Boolean isPlaying=true;
    int array[][]=new int[3][3];
    @BindView(R.id.btn_start)
    Button btnStart;
    @BindView(R.id.text_start)
    TextView textStart;
    @BindView(R.id.btn_one)
    Button btnOne;
    @BindView(R.id.btn_two)
    Button btnTwo;
    @BindView(R.id.btn_three)
    Button btnThree;
    @BindView(R.id.btn_four)
    Button btnFour;
    @BindView(R.id.btn_five)
    Button btnFive;
    @BindView(R.id.btn_six)
    Button btnSix;
    @BindView(R.id.btn_seven)
    Button btnSeven;
    @BindView(R.id.btn_eight)
    Button btnEight;
    @BindView(R.id.btn_nine)
    Button btnNine;
    @BindView(R.id.btn_1p)
    Button btn1p;
    @BindView(R.id.btn_2p)
    Button btn2p;

    @SuppressLint("NewApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recreate();
                isPlaying=true;
            }
        });

    }

    @OnClick({R.id.btn_one, R.id.btn_two, R.id.btn_three, R.id.btn_four, R.id.btn_five, R.id.btn_six, R.id.btn_seven, R.id.btn_eight, R.id.btn_nine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_one:
                gameLogic(0,0,btnOne);
                break;
            case R.id.btn_two:gameLogic(0,1,btnTwo);
                break;
            case R.id.btn_three:gameLogic(0,2,btnThree);
                break;
            case R.id.btn_four:gameLogic(1,0,btnFour);
                break;
            case R.id.btn_five:gameLogic(1,1,btnFive);
                break;
            case R.id.btn_six:gameLogic(1,2,btnSix);
                break;
            case R.id.btn_seven:gameLogic(2,0,btnSeven);
                break;
            case R.id.btn_eight:gameLogic(2,1,btnEight);
                break;
            case R.id.btn_nine:gameLogic(2,2,btnNine);
                break;
        }
    }
    @SuppressLint("NewApi")
    void gameLogic(int rowIndex, int columnIndex, Button btn){
        if(isPlaying) {
            btnInput = btn;
            if (array[rowIndex][columnIndex] == 0) {
                if (isFirstPlayer) {
                    btnInput.setBackground(getResources().getDrawable(R.drawable.img_zero, null));
                    array[rowIndex][columnIndex] = 1;
                    if (winLogic(array, rowIndex, columnIndex)) {
                        textStart.setText("1p Won");
                        isPlaying=false;
                    }
                    isFirstPlayer = false;

                } else {
                    btnInput.setBackground(getResources().getDrawable(R.drawable.ic_cross, null));
                    array[rowIndex][columnIndex] = 2;
                    if (winLogic(array, rowIndex, columnIndex)) {
                        textStart.setText("2p Won");
                        isPlaying=false;
                    }
                    isFirstPlayer = true;
                }
            }
        }
    }
    boolean winLogic(int[][] a,int rowIndex,int columnIndex){

        if(rowCrossed(a,rowIndex)||columnCrossed(a,columnIndex)||diagonalCrossed(a))return true;
        return false;
    }

    boolean rowCrossed(int[][] board,int i)
    {
            if (board[i][0] == board[i][1] &&
                    board[i][1] == board[i][2] &&
                    board[i][0] != 0)
            {
                if(i==0)
                {
                    btnLeft=btnOne;
                    btnRight=btnTwo;
                    btnMid=btnThree;
                }
                else if(i==1)
                {
                    btnLeft=btnFour;
                    btnRight=btnFive;
                    btnMid=btnSix;
                }
                else if(i==2)
                {
                    btnLeft=btnSeven;
                    btnRight=btnEight;
                    btnMid=btnNine;
                }
                btnLeft.setBackground(getResources().getDrawable(R.drawable.btn_horizontal_line));
                btnRight.setBackground(getResources().getDrawable(R.drawable.btn_horizontal_line));
                btnMid.setBackground(getResources().getDrawable(R.drawable.btn_horizontal_line));
                return (true);
            }
            else return(false);
    }

    // A function that returns true if any of the column
// is crossed with the same player's move
    boolean columnCrossed(int[][] board,int i)
    {
            if (board[0][i] == board[1][i] &&
                    board[1][i] == board[2][i] &&
                    board[0][i] !=0)
            {
                if(i==0)
                {
                    btnLeft=btnOne;
                    btnRight=btnFour;
                    btnMid=btnSeven;
                }
                else if(i==1)
                {
                    btnLeft=btnTwo;
                    btnRight=btnFive;
                    btnMid=btnEight;
                }
                else if(i==2)
                {
                    btnLeft=btnThree;
                    btnRight=btnSix;
                    btnMid=btnNine;
                }
                btnLeft.setBackground(getResources().getDrawable(R.drawable.btn_vertical_line));
                btnRight.setBackground(getResources().getDrawable(R.drawable.btn_vertical_line));
                btnMid.setBackground(getResources().getDrawable(R.drawable.btn_vertical_line));
                return (true);
            }
            else return(false);
    }

    boolean diagonalCrossed(int[][] board)
    {
        if (board[0][0] == board[1][1] &&
                board[1][1] == board[2][2] &&
                board[0][0] != 0)
        {

            btnLeft=btnOne;
            btnRight=btnFive;
            btnMid=btnNine;

            btnLeft.setBackground(getResources().getDrawable(R.drawable.btn_angle_acute_line));
            btnRight.setBackground(getResources().getDrawable(R.drawable.btn_angle_acute_line));
            btnMid.setBackground(getResources().getDrawable(R.drawable.btn_angle_acute_line));
            return(true);
        }

        if (board[0][2] == board[1][1] &&
                board[1][1] == board[2][0] &&
                board[0][2] != 0)
        {

            btnLeft=btnThree;
            btnRight=btnFive;
            btnMid=btnSeven;
            btnLeft.setBackground(getResources().getDrawable(R.drawable.btn_angle_obtuse_line));
            btnRight.setBackground(getResources().getDrawable(R.drawable.btn_angle_obtuse_line));
            btnMid.setBackground(getResources().getDrawable(R.drawable.btn_angle_obtuse_line));
            return(true);
        }
        return(false);
    }
}
