package chemia.httpsgithub.comahyoung1.chemia;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
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
    private TextView centerAtomSub;


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
        centerAtomSub = (TextView)findViewById(R.id.num_center_atoms);
        centerAtomSub.setText(molecule.getCenterAtomSubscript());
        attachedElementTVArray[0]=(TextView)findViewById(R.id.first_attached_atom_display);
        attachedElementTVArray[1]=(TextView)findViewById(R.id.second_attached_atom_display);
        attachedElementTVArray[2]=(TextView)findViewById(R.id.third_attached_atom_display);
        attachedElementTVArray[3]=(TextView)findViewById(R.id.fourth_attached_atom_display);
        attachedElementTVArray[4]=(TextView)findViewById(R.id.fifth_attached_atom_display);
        attachedElementTVArray[5]=(TextView)findViewById(R.id.sixth_attached_atom_display);
        for (int i=0; i<6; i++){
            if (molecule.getAttachedElementArray()[i] != null){
                attachedElementTVArray[i].setText(molecule.getAttachedElementArray()[i].getChemSymbol());
                attachedElementSubsTVArray[i].setText(molecule.getAttachedAtomSubscriptArray()[i]);
            }
            else{
                attachedElementTVArray[i].setText("");
                attachedElementSubsTVArray[i].setText("");
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
    private void initializeFormulaAndFeedback(){
        feedbackTV = (TextView)findViewById(R.id.answer_feedback_tv);
        feedbackTV.setText("");
        centerAtomSub = (TextView)findViewById(R.id.num_center_atoms);
        centerAtomSub.setText("");
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
    //******************************Feedback****************************************************
    private void correctRadioSelectedFeedback(){
        feedbackTV.setText(R.string.correct_string);
        feedbackTV.setTextColor(Color.parseColor(getString(R.string.correct_textColor)));
        feedbackTV.setBackgroundColor(Color.parseColor(getString(R.string.correct_backgroundColor)));
    }
    private void wrongRadioSelectedFeedback(){
        feedbackTV.setText(R.string.try_string);
        feedbackTV.setTextColor(Color.parseColor(getString(R.string.try_textColor)));
        feedbackTV.setBackgroundColor(Color.parseColor(getString(R.string.try_backgroundColor)));
    }
    private void noRadioSelectedFeedback(){
        feedbackTV.setText(R.string.selectOption_string);
        feedbackTV.setTextColor(Color.parseColor(getString(R.string.selectOption_textColor)));
        feedbackTV.setBackgroundColor(Color.parseColor(getString(R.string.selectOption_backgroundColor)));
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
