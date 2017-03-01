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
    private TextView centerAtomTV = (TextView) findViewById(R.id.center_atom_display);
    private TextView firstAttachedAtomTV = (TextView)findViewById(R.id.first_attached_atom_display);
    private TextView secondAttachedAtomTV = (TextView)findViewById(R.id.second_attached_atom_display);
    private TextView thirdAttachedAtomTV = (TextView)findViewById(R.id.third_attached_atom_display);
    private TextView fourthAttachedAtomTV = (TextView)findViewById(R.id.fourth_attached_atom_display);
    private TextView fifthAttachedAtomTV = (TextView)findViewById(R.id.fifth_attached_atom_display);
    private TextView sixthAttachedAtomTV = (TextView)findViewById(R.id.sixth_attached_atom_display);
    private TextView centerAtomCoefficientTV = (TextView)findViewById(R.id.num_center_atoms);
    private TextView firstAttachedAtomCoefficientTV = (TextView)findViewById(R.id.num_first_attached_atoms);
    private TextView secondAttachedAtomCoefficientTV = (TextView)findViewById(R.id.num_second_attached_atoms);
    private TextView thirdAttachedAtomCoefficientTV = (TextView)findViewById(R.id.num_third_attached_atoms);
    private TextView fourthAttachedAtomCoefficientTV = (TextView)findViewById(R.id.num_fourth_attached_atoms);
    private TextView fifthAttachedAtomCoefficientTV = (TextView)findViewById(R.id.num_fifth_attached_atoms);
    private TextView sixthAttachedAtomCoefficientTV = (TextView)findViewById(R.id.num_sixth_attached_atoms);
    private Spinner centerAtomSpinner = (Spinner)findViewById(R.id.center_atom_spinner);
    private Spinner attachedAtomSpinner = (Spinner) findViewById(R.id.attached_elements_spinner);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formula_creator);
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

    //clears the formula TextViews
    //does NOTHING with MoleculeBuilder etc. Only aesthetic, storage
    public void onReset(){
        Button resetButton = (Button)findViewById(R.id.reset_formula_btn);
        resetButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
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
        });
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
