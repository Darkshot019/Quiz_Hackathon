package com.example.quiz;

import static com.example.quiz.sets.category_id;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuestionsActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView Question_num, question, timer;
    private Button Option1, Option2, Option3, Option4;
    private List<ModalClass> questionList;
    private int quesNum;
    private CountDownTimer countDownTimer;
    private int score;
    /*  private FirebaseFirestore firestore;
      private int setNo;*/
    private Dialog loadingDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        question = findViewById(R.id.Question);
        Question_num = findViewById(R.id.Question_num);
        timer = findViewById(R.id.countDown);

        Option1 = findViewById(R.id.Option_1);
        Option2 = findViewById(R.id.Option_2);
        Option3 = findViewById(R.id.Option_3);
        Option4 = findViewById(R.id.Option_4);


        Option1.setOnClickListener(this);
        Option2.setOnClickListener(this);
        Option3.setOnClickListener(this);
        Option4.setOnClickListener(this);

        loadingDialog = new Dialog(QuestionsActivity.this);
        loadingDialog.setContentView(R.layout.loading_progressbar);
        loadingDialog.setCancelable(false);
        loadingDialog.getWindow().setBackgroundDrawableResource(R.drawable.pogressbackground);
        loadingDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        loadingDialog.show();

        //questionList = new ArrayList<>();

/*
        setNo = getIntent().getIntExtra("SETNO", 1);
        firestore = FirebaseFirestore.getInstance();
*/


        getQuestionsList();

        score = 0;
    }

    private void getQuestionsList() {

        questionList = new ArrayList<>();
        questionList.add(new ModalClass("Which is the first city in India to have water metro project?", "Kolkata", "Kochi", "Chennai", "Mumbai", 2));
        questionList.add(new ModalClass("Who is the head of working group set up to strengthen television rating point (TRP) services?", "Shashi shekhar vempati", "Anurag singh thakur", "L Murugan", "Sukant Vatsa", 1));
        questionList.add(new ModalClass("India signed a lol on new and renewable energy technology with country?", "Israel", "Australia", "Usa", "New Zealand", 2));
        questionList.add(new ModalClass("The passenger returning from which country are to be screened for novel corona virus (nCov), in selected indian airports?", "Indonesia", "China", "Thailand", "Japan", 2));
        questionList.add(new ModalClass("As per the recent survey of Transparency International,which state has been ranked as the top state in budget formulation practices?", "Odisha", "Andhra Pradesh", "Assam", "West Bengal", 3));
        questionList.add(new ModalClass("A Rs148/- core project of Electric vehicle park and Rs500/- core project of Data center park are the recent initiatives of which state/UT?", "Karnataka", "West Bengal", "Tamil Nadu", "New Delhi", 3));
        questionList.add(new ModalClass("Center Railside Warehouse Company Ltd(CRWC)is set to be merged with which entity?", "NABARD", "NAFED", "Central Warehousing Corporation", "ICAR", 3));
        questionList.add(new ModalClass("Which is the top state of Indian in production of coal?", "Jharkhand", "Chhattisgarh", "Odisha", "Telangana", 2));
        questionList.add(new ModalClass("Karbi peace Accord, which was seen in the news recently,is associated whith state?", "Haryana", "Assam", "Meghalaya", "Mizoram", 4));
        questionList.add(new ModalClass("With which organization, Indian Naval Placement Agency has partnered through which opportunities for the recruitment of naval veterans will be explored?", "Amazon", "Reliance", "Infosys", "Flipkart", 3));
//        questionList.add(new ModalClass("which entity operates'Digital Sky'platform in Indian?","NITI Aayog","Reserve Bank of india","",));
        loadingDialog.cancel();
        setQuestion();
/*
        questionList.clear();


        firestore.collection("QUIZ").document("CAT" + String.valueOf(category_id))
                .collection("SET" + String.valueOf(setNo))
                .get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()) {
                            QuerySnapshot questions = task.getResult();

                            for (QueryDocumentSnapshot doc : questions) {
                                questionList.add(new ModalClass(doc.getString("QUESTION"),
                                        doc.getString("A"),
                                        doc.getString("B"),
                                        doc.getString("C"),
                                        doc.getString("D"),
                                        Integer.valueOf(doc.getString("ANSWER"))
                                ));

                            }
                            setQuestion();

                        } else {
                            Toast.makeText(QuestionsActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        loadingDialog.cancel();
                    }
                });
*/


    }

    private void setQuestion() {
        timer.setText(String.valueOf(20));
        question.setText(questionList.get(0).getQuestion());
        Option1.setText(questionList.get(0).getoA());
        Option2.setText(questionList.get(0).getoB());
        Option3.setText(questionList.get(0).getoC());
        Option4.setText(questionList.get(0).getoD());

        Collections.shuffle(questionList);
        Question_num.setText(String.valueOf(1) + "/" + String.valueOf(questionList.size()));

        startTimer();

        quesNum = 0;
    }

    private void startTimer() {
        countDownTimer = new CountDownTimer(22000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished < 20000)
                    timer.setText(String.valueOf(millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                changeQuestion();

            }
        };
        countDownTimer.start();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {

        int selectedOption = 0;

        switch (v.getId()) {
            case R.id.Option_1:
                selectedOption = 1;
                break;
            case R.id.Option_2:
                selectedOption = 2;
                break;
            case R.id.Option_3:
                selectedOption = 3;
                break;
            case R.id.Option_4:
                selectedOption = 4;
                break;
            default:
        }
        countDownTimer.cancel();
        checkAnswer(selectedOption, v);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void checkAnswer(int selectedOption, View view) {
        if (selectedOption == questionList.get(quesNum).getAns()) {
            //Right Answer
            ((Button) view).setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
            score++;

        } else {
            //wrong answer
            ((Button) view).setBackgroundTintList(ColorStateList.valueOf(Color.RED));

            switch (questionList.get(quesNum).getAns()) {

                case 1:
                    Option1.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;

                case 2:
                    Option2.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;

                case 3:
                    Option3.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;

                case 4:
                    Option4.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;
            }
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                changeQuestion();
            }
        }, 2000);


    }

    private void changeQuestion() {
        if (quesNum < questionList.size() - 1) {

            quesNum++;

            playAnim(question, 0, 0);
            playAnim(Option1, 0, 1);
            playAnim(Option2, 0, 2);
            playAnim(Option3, 0, 3);
            playAnim(Option4, 0, 4);

            Question_num.setText(String.valueOf(quesNum + 1) + "/" + String.valueOf(questionList.size()));
            timer.setText(String.valueOf(20));
            startTimer();

        } else {
            //GO to Score Activity
            Intent intent = new Intent(QuestionsActivity.this, ScoreActivity.class);
            intent.putExtra("SCORE", String.valueOf(score) + "/" + String.valueOf(questionList.size()));
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            QuestionsActivity.this.finish();
        }
    }

    private void playAnim(View view, final int value, int viewNum) {
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500)
                .setStartDelay(100).setInterpolator(new DecelerateInterpolator())
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        if (value == 0) {
                            switch (viewNum) {
                                case 0:
                                    ((TextView) view).setText(questionList.get(quesNum).getQuestion());
                                    break;
                                case 1:
                                    ((Button) view).setText(questionList.get(quesNum).getoA());
                                    break;
                                case 2:
                                    ((Button) view).setText(questionList.get(quesNum).getoB());
                                    break;
                                case 3:
                                    ((Button) view).setText(questionList.get(quesNum).getoC());
                                    break;
                                case 4:
                                    ((Button) view).setText(questionList.get(quesNum).getoD());
                                    break;
                            }

                            if (viewNum != 0)
                                ((Button) view).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#31906E")));

                            playAnim(view, 1, viewNum);
                        }

                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        countDownTimer.cancel();
    }
}