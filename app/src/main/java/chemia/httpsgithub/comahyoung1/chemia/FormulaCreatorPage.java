package chemia.httpsgithub.comahyoung1.chemia;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

public class FormulaCreatorPage extends AppCompatActivity {
    private PeriodicTableBuilder periodicTable = new PeriodicTableBuilder();
    private TextView centerAtomTV;
    private TextView firstAttachedAtomTV;
    private TextView secondAttachedAtomTV;
    private TextView thirdAttachedAtomTV;
    private TextView fourthAttachedAtomTV;
    private TextView fifthAttachedAtomTV;
    private TextView sixthAttachedAtomTV;
    private TextView centerAtomCoefficientTV;
    private TextView firstAttachedAtomCoefficientTV;
    private TextView secondAttachedAtomCoefficientTV;
    private TextView thirdAttachedAtomCoefficientTV;
    private TextView fourthAttachedAtomCoefficientTV;
    private TextView fifthAttachedAtomCoefficientTV;
    private TextView sixthAttachedAtomCoefficientTV;
    private Spinner centerAtomSpinner;
    private Spinner attachedAtomSpinner;
    private Molecule molecule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formula_creator);
        centerAtomTV = (TextView) findViewById(R.id.center_atom_display);
        firstAttachedAtomTV = (TextView)findViewById(R.id.first_attached_atom_display);
        secondAttachedAtomTV = (TextView)findViewById(R.id.second_attached_atom_display);
        thirdAttachedAtomTV = (TextView)findViewById(R.id.third_attached_atom_display);
        fourthAttachedAtomTV = (TextView)findViewById(R.id.fourth_attached_atom_display);
        fifthAttachedAtomTV = (TextView)findViewById(R.id.fifth_attached_atom_display);
        sixthAttachedAtomTV = (TextView)findViewById(R.id.sixth_attached_atom_display);
        centerAtomCoefficientTV = (TextView)findViewById(R.id.num_center_atoms);
        firstAttachedAtomCoefficientTV = (TextView)findViewById(R.id.num_first_attached_atoms);
        secondAttachedAtomCoefficientTV = (TextView)findViewById(R.id.num_second_attached_atoms);
        thirdAttachedAtomCoefficientTV = (TextView)findViewById(R.id.num_third_attached_atoms);
        fourthAttachedAtomCoefficientTV = (TextView)findViewById(R.id.num_fourth_attached_atoms);
        fifthAttachedAtomCoefficientTV = (TextView)findViewById(R.id.num_fifth_attached_atoms);
        sixthAttachedAtomCoefficientTV = (TextView)findViewById(R.id.num_sixth_attached_atoms);
        centerAtomSpinner = (Spinner)findViewById(R.id.center_atom_spinner);
        attachedAtomSpinner = (Spinner) findViewById(R.id.attached_elements_spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, periodicTable.getListOfElementNames());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        centerAtomSpinner.setAdapter(adapter);
        attachedAtomSpinner.setAdapter(adapter);
    }

    //keep logic outta here!!!!!!

    public void onCenterAtomSet(){
        centerAtomTV.setText(periodicTable.getElementByName(this.centerAtomSpinner.getSelectedItem().toString()).getChemSymbol());
    }

    public void onAttachedAtomAdded(){
        //get chem symbol from
    }

    //clears the formula TextViews --- does NOTHING with MoleculeBuilder etc. Only aesthetic, storage
    public void onResetClick(View v){
        centerAtomTV.setText("");
        firstAttachedAtomTV.setText("");
        secondAttachedAtomTV.setText("");
        thirdAttachedAtomTV.setText("");
        fourthAttachedAtomTV.setText("");
        fifthAttachedAtomTV.setText("");
        sixthAttachedAtomTV.setText("");
        centerAtomCoefficientTV.setText("");
        firstAttachedAtomCoefficientTV.setText("");
        secondAttachedAtomCoefficientTV.setText("");
        thirdAttachedAtomCoefficientTV.setText("");
        fourthAttachedAtomCoefficientTV.setText("");
        fifthAttachedAtomCoefficientTV.setText("");
        sixthAttachedAtomCoefficientTV.setText("");
    }

    public void onMakeMoleculeClick(View v){
        //NEED method call here to
        //TEMPORARY
        Element centerAtom = periodicTable.getElementByName("Hydrogen");
        Element attachedAtom = periodicTable.getElementByName("Hydrogen");
        Element[] elementArray = new Element[6];
        elementArray[0] = attachedAtom;
        Molecule molecule = new Molecule(centerAtom, elementArray);
        sendMoleculeToValenceQuestion(molecule);
    }

    private void sendMoleculeToValenceQuestion(Molecule molecule){
        Intent ValenceQuestionPage = new Intent(getApplicationContext(), chemia.httpsgithub.comahyoung1.chemia.ValenceQuestionPage.class);
        ValenceQuestionPage.putExtra("molecule", molecule);
        startActivity(ValenceQuestionPage);
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
