package chemia.httpsgithub.comahyoung1.chemia;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
    private TextView feedbackTV;
    private Molecule molecule;
    private TextView[] attachedElementTVArray = new TextView[6];
    private TextView[] attachedElementSubsTVArray = new TextView[6];
    private TextView centerAtomTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valence_question);
        //populateFormula must come before initializeRadio because it makes the molecule. MESSY.
        //ALSO the initialize formula and feedback should be incorporated with populate formula so that I'm not repeating
            //also they kind of belong together
            //but not initializing feedback that should be elsewhere
        initializeFormulaAndFeedback();
        populateFormulaTVs();
        initializeRadioGroup();
    }

    public void submitValence(View v){
        int selectedRadioID = radioGroup.getCheckedRadioButtonId();
        if (selectedRadioID!=-1) {
            radioButtonRightorWrong(selectedRadioID);
            //SOMETHING here about going to the next item
        }
        else{
            noRadioSelectedFeedback();
        }
    }

    private void radioButtonRightorWrong(int selectedRadioID){
        RadioButton selectedRadio = (RadioButton) findViewById(selectedRadioID);
        if (Integer.toString(molecule.getNumberOfTotalValence()).equals(selectedRadio.getText())) {
            correctRadioSelectedFeedback();
        }
        else {
            wrongRadioSelectedFeedback();
        }
    }
    private void populateFormulaTVs(){
        Intent intent = getIntent();
        molecule = (Molecule)intent.getSerializableExtra("molecule");
        Element centerAtom = molecule.getCenterAtom();
        centerAtomTV = (TextView)findViewById(R.id.center_atom_display);
        centerAtomTV.setText(centerAtom.getChemSymbol());
        attachedElementTVArray[0]=(TextView)findViewById(R.id.first_attached_atom_display);
        attachedElementTVArray[1]=(TextView)findViewById(R.id.second_attached_atom_display);
        attachedElementTVArray[2]=(TextView)findViewById(R.id.third_attached_atom_display);
        attachedElementTVArray[3]=(TextView)findViewById(R.id.fourth_attached_atom_display);
        attachedElementTVArray[4]=(TextView)findViewById(R.id.fifth_attached_atom_display);
        attachedElementTVArray[5]=(TextView)findViewById(R.id.sixth_attached_atom_display);
        for (int i=0; i<6; i++){
            if (molecule.attachedAtomArray[i] != null){
                attachedElementTVArray[i].setText(molecule.attachedAtomArray[i].getChemSymbol());
            }
            else{
                attachedElementTVArray[i].setText("");
            }
        }
    }
    private void initializeRadioGroup(){
        radioGroup = (RadioGroup)findViewById(R.id.answer_radio_group);
        radioButtonOne = (RadioButton)findViewById(R.id.valence_radio_one);
        radioButtonTwo = (RadioButton)findViewById(R.id.valence_radio_two);
        radioButtonThree = (RadioButton)findViewById(R.id.valence_radio_three);
        radioButtonFour = (RadioButton)findViewById(R.id.valence_radio_four);
        radioButtonOne.setText("1");
        //these should all be randomized somehow, but going to be fixed for now
        radioButtonTwo.setText(Integer.toString(molecule.getNumberOfTotalValence()));
        radioButtonThree.setText("3");
        radioButtonFour.setText("5");
    }
    private void correctRadioSelectedFeedback(){
        feedbackTV.setText("Correct!");
        feedbackTV.setTextColor(Color.parseColor("#42f468"));
        feedbackTV.setBackgroundColor(Color.parseColor("#00ffffff"));
    }
    private void wrongRadioSelectedFeedback(){
        feedbackTV.setText("Try Again...");
        feedbackTV.setTextColor(Color.parseColor("#e20000"));
        feedbackTV.setBackgroundColor(Color.parseColor("#00ffffff"));
    }
    private void noRadioSelectedFeedback(){
        feedbackTV.setText("You need to select an option");
        feedbackTV.setTextColor(Color.parseColor("#dc42f4"));
        feedbackTV.setBackgroundColor(Color.parseColor("#fffb3f"));
    }
    private void initializeFormulaAndFeedback(){
        feedbackTV = (TextView)findViewById(R.id.answer_feedback_tv);
        feedbackTV.setText("");
        attachedElementSubsTVArray[0] = (TextView)findViewById(R.id.num_first_attached_atoms);
        attachedElementSubsTVArray[1] = (TextView)findViewById(R.id.num_second_attached_atoms);
        attachedElementSubsTVArray[2] = (TextView)findViewById(R.id.num_third_attached_atoms);
        attachedElementSubsTVArray[3] = (TextView)findViewById(R.id.num_fourth_attached_atoms);
        attachedElementSubsTVArray[4] = (TextView)findViewById(R.id.num_fifth_attached_atoms);
        attachedElementSubsTVArray[5] = (TextView)findViewById(R.id.num_sixth_attached_atoms);
        for (int i=0; i<6; i++){
            attachedElementSubsTVArray[i].setText("");
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
