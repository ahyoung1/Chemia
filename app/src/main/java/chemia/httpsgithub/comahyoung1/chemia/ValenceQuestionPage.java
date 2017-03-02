package chemia.httpsgithub.comahyoung1.chemia;

import android.content.Intent;
import android.graphics.Color;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by Aaron on 3/1/2017.
 */

public class ValenceQuestionPage extends AppCompatActivity {
    private RadioGroup radioGroup;
    private RadioButton radioButtonOne;
    private RadioButton radioButtonTwo;
    private RadioButton radioButtonThree;
    private RadioButton radioButtonFour;
    private int numOfValence;
    private TextView feedbackTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valence_question);
        radioGroup = (RadioGroup)findViewById(R.id.answer_radio_group);
        radioButtonOne = (RadioButton)findViewById(R.id.valence_radio_one);
        radioButtonTwo = (RadioButton)findViewById(R.id.valence_radio_two);
        radioButtonThree = (RadioButton)findViewById(R.id.valence_radio_three);
        radioButtonFour = (RadioButton)findViewById(R.id.valence_radio_four);
        feedbackTV = (TextView)findViewById(R.id.answer_feedback_tv);
    }

    public void submitValence(View v){
        int selectedRadioID = radioGroup.getCheckedRadioButtonId();
        RadioButton selectedRadio = (RadioButton)findViewById(selectedRadioID);
        if (Integer.toString(numOfValence).equals(selectedRadio.getText())){
            feedbackTV.setText("Correct!");
            feedbackTV.setTextColor(Color.parseColor("#42f468"));
        }
        else{
            feedbackTV.setText("Try Again...");
            feedbackTV.setTextColor(Color.parseColor("#e20000"));
        }
    }


    //menu bar and home button
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_home:
                startActivity(new Intent(this, HomePage.class));
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
