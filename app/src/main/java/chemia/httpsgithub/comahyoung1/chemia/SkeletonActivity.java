package chemia.httpsgithub.comahyoung1.chemia;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SkeletonActivity extends AppCompatActivity {
    Button nextButton;
    Molecule molecule;
    TextView feedbackTV;
    TextView centerAtomTV;
    TextView centerAtomSubTV;
    TextView[] attachedElementTVArray = new TextView[6];
    TextView[] attachedElementSubsTVArray = new TextView[6];
    Element centerAtom;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_skeleton);
        nextButton = (Button)findViewById(R.id.nextButton);
        nextButton.setVisibility(View.INVISIBLE);
        Intent intent = getIntent();
        molecule = (Molecule)intent.getSerializableExtra("molecule");
        initializeFormulaAndFeedback();
    }
    //*********************************Initializations*******************************************
    private void initializeFormulaAndFeedback(){
        //center element
        centerAtom = molecule.getCenterAtom();
        centerAtomTV = (TextView)findViewById(R.id.center_atom_display);
        centerAtomTV.setText(centerAtom.getChemSymbol());
        centerAtomSubTV = (TextView)findViewById(R.id.num_center_atoms);
        centerAtomSubTV.setText(molecule.getCenterAtomSubscript());
        //attached elements
        attachedElementTVArray[0]=(TextView)findViewById(R.id.first_attached_atom_display);
        attachedElementTVArray[1]=(TextView)findViewById(R.id.second_attached_atom_display);
        attachedElementTVArray[2]=(TextView)findViewById(R.id.third_attached_atom_display);
        attachedElementTVArray[3]=(TextView)findViewById(R.id.fourth_attached_atom_display);
        attachedElementTVArray[4]=(TextView)findViewById(R.id.fifth_attached_atom_display);
        attachedElementTVArray[5]=(TextView)findViewById(R.id.sixth_attached_atom_display);
        //attached subscripts
        attachedElementSubsTVArray[0] = (TextView)findViewById(R.id.num_first_attached_atoms);
        attachedElementSubsTVArray[1] = (TextView)findViewById(R.id.num_second_attached_atoms);
        attachedElementSubsTVArray[2] = (TextView)findViewById(R.id.num_third_attached_atoms);
        attachedElementSubsTVArray[3] = (TextView)findViewById(R.id.num_fourth_attached_atoms);
        attachedElementSubsTVArray[4] = (TextView)findViewById(R.id.num_fifth_attached_atoms);
        attachedElementSubsTVArray[5] = (TextView)findViewById(R.id.num_sixth_attached_atoms);
        for (int i=0; i<6; i++){
            if (molecule.getAttachedElementArray()[i] != null || !molecule.getAttachedElementArray()[i].equals("-1")){
                attachedElementTVArray[i].setText(molecule.getAttachedElementArray()[i].getChemSymbol());
                attachedElementSubsTVArray[i].setText(molecule.getAttachedAtomSubscriptArray()[i]);
            }
            else{
                attachedElementTVArray[i].setText("");
                attachedElementSubsTVArray[i].setText("");
            }
        }
        feedbackTV = (TextView)findViewById(R.id.answer_feedback_tv);
        feedbackTV.setText("");
    }
    //******************************Feedback****************************************************
    private void correctRadioSelectedFeedback(){
        feedbackTV.setText(R.string.correct_string);
        feedbackTV.setTextColor(Color.parseColor(getString(R.string.correct_textColor)));
        feedbackTV.setBackgroundColor(Color.parseColor(getString(R.string.correct_backgroundColor)));

    }
    private void wrongRadioSelectedFeedback() {
        feedbackTV.setText(R.string.try_string);
        feedbackTV.setTextColor(Color.parseColor(getString(R.string.try_textColor)));
        feedbackTV.setBackgroundColor(Color.parseColor(getString(R.string.try_backgroundColor)));
    }
    //*******************************menu bar and home button************************************
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
