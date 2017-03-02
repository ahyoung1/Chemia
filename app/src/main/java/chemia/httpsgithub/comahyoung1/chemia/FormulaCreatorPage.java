package chemia.httpsgithub.comahyoung1.chemia;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
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
    private TextView[] attachedAtomsArray = new TextView[6];
    private TextView[] attachedAtomsCoefficientArray = new TextView[6];
    private Spinner centerAtomSpinner;
    private Spinner attachedAtomSpinner;
    private String selectedCenterAtom;
    private String selectedCenterAtomChemSymbol;
    private String selectedAttachedAtom;
    private String selectedAttachedAtomChemSymbol;
    private Molecule molecule;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formula_creator);
        initializePageElements();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, periodicTable.getListOfElementNames());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        centerAtomSpinner.setAdapter(adapter);
        attachedAtomSpinner.setAdapter(adapter);
    }

    //keep logic outta here!!!!!!

    public void onCenterAtomSet(View v){
        for (int n=0; n<6; n++){
            attachedAtomsArray[n].setText("");
            attachedAtomsCoefficientArray[n].setText("");
        }
        selectedCenterAtom = centerAtomSpinner.getSelectedItem().toString();
        selectedCenterAtomChemSymbol = periodicTable.getElementByName(selectedCenterAtom).getChemSymbol();
        centerAtomTV.setText(selectedCenterAtomChemSymbol);
        centerAtomCoefficientTV.setText("");
    }

    //************************Add attached atoms************************
    public void onAttachedAtomAdded(View v){
        if (centerAtomTV.getText().equals("")){
            showSetCenterAtomAlert();
        }
        else if (hasSixAtoms()){
            showSixAtomsAlert();
        }
        else if(centerAtomCoefficientTV.getText().equals("2")){
            showDiatomicAlert();
        }
        else{
            selectedAttachedAtom = attachedAtomSpinner.getSelectedItem().toString();
            selectedAttachedAtomChemSymbol = periodicTable.getElementByName(selectedAttachedAtom).getChemSymbol();
            if(!attachAtomToFormula()) {
                showMysteryAddingAlert();
            }
        }
    }

    private boolean attachAtomToFormula(){
        if(centerAtomTV.getText().equals(selectedAttachedAtomChemSymbol)){
            centerAtomCoefficientTV.setText("2");
            return true;
        }
        for (int i=0; i<6; i++){
            //if text of ith TextView in array is NOT ""
            if (!attachedAtomsArray[i].getText().equals("")){
                if (attachedAtomsArray[i].getText().equals(selectedAttachedAtomChemSymbol)) {
                    if (attachedAtomsCoefficientArray[i].getText()==""){
                        attachedAtomsCoefficientArray[i].setText("2");
                    }
                    else{
                        int coefficient = Integer.valueOf(attachedAtomsCoefficientArray[i].getText().toString());
                        coefficient++;
                        attachedAtomsCoefficientArray[i].setText(Integer.toString(coefficient));
                    }
                    return true;
                }
            }
        }
        for (int n=0; n<6; n++){
            if(attachedAtomsArray[n].getText().equals("")){
                String chemSymbol = periodicTable.getElementByName(selectedAttachedAtom).getChemSymbol();
                attachedAtomsArray[n].setText(chemSymbol);
                return true;
            }
        }
        return false;
    }

    //a note: .getText().equals("") is similar to checking for null
    private boolean hasSixAtoms(){
        int numberOfAtoms = 0;
        for (int n=0; n<6; n++){
            if(attachedAtomsArray[n].getText().equals("")){
                continue;
            }
            else if (!attachedAtomsCoefficientArray[n].getText().equals("")){
                numberOfAtoms += Integer.valueOf(attachedAtomsCoefficientArray[n].getText().toString());
            }
            else{
                numberOfAtoms++;
            }
        }
        return numberOfAtoms == 6;
    }

    //************************Subtract Atoms************************
    public void onAttachedAtomSubtracted(View v){
        selectedAttachedAtom = attachedAtomSpinner.getSelectedItem().toString();
        selectedCenterAtomChemSymbol = periodicTable.getElementByName(selectedAttachedAtom).getChemSymbol();
        if (centerAtomTV.getText().equals(selectedAttachedAtomChemSymbol)){
            if (centerAtomCoefficientTV.getText().equals("2")){
                removeDiatomicCoefficient();
            }
            else{
                showSubtractCenterAtomAlert();
            }
        }
        else{
            boolean atomPresentFlag = false;
            for (int n=0; n<6; n++){
                if (attachedAtomsArray[n].getText().equals(selectedAttachedAtomChemSymbol)){
                    if (attachedAtomsCoefficientArray[n].getText().equals("")){
                        removeAttachedAtom(n);
                        atomPresentFlag = true;
                    }
                    else{
                        reduceAttachedAtomCoefficient(n);
                        atomPresentFlag = true;
                    }
                }
            }
            if (!atomPresentFlag){
                showSelectedNotAttachedAlert();
            }
        }
    }

    private void removeDiatomicCoefficient(){
        centerAtomCoefficientTV.setText("");
    }

    private void removeAttachedAtom(int n){
        for (int i=n; i<5; i++){
            attachedAtomsArray[i].setText(attachedAtomsArray[i+1].getText());
        }
        attachedAtomsArray[5].setText("");
    }

    private void reduceAttachedAtomCoefficient(int n){
        if (attachedAtomsCoefficientArray[n].getText().equals("2")){
            attachedAtomsCoefficientArray[n].setText("");
        }
        else{
            int newCoefficient = Integer.valueOf(attachedAtomsCoefficientArray[n].getText().toString());
            newCoefficient -=1;
            attachedAtomsCoefficientArray[n].setText(Integer.toString(newCoefficient));
        }
    }

    //clears the formula TextViews --- does NOTHING with MoleculeBuilder etc. Only aesthetic, storage
    public void onResetClick(View v){
        centerAtomTV.setText("");
        for (int n=0; n<6; n++){
            attachedAtomsArray[n].setText("");
            attachedAtomsCoefficientArray[n].setText("");
        }
        centerAtomCoefficientTV.setText("");
    }

    public void onMakeMoleculeClick(View v){
        //NEED method call here to
        //TEMPORARY

        Element centerAtom = periodicTable.getElementByName("Hydrogen");
        Element attachedAtom = periodicTable.getElementByName("Hydrogen");
        Element[] elementArray = new Element[6];
        elementArray[0] = attachedAtom;

        molecule = new Molecule(centerAtom, elementArray);
        sendMoleculeToValenceQuestionPage(molecule);
    }

    private void sendMoleculeToValenceQuestionPage(Molecule molecule){
        Intent ValenceQuestionPage = new Intent(getApplicationContext(), chemia.httpsgithub.comahyoung1.chemia.ValenceQuestionPage.class);
        ValenceQuestionPage.putExtra("molecule", molecule);
        startActivity(ValenceQuestionPage);
    }

    //*******************initialization*******************
    private void initializePageElements(){
        centerAtomTV = (TextView) findViewById(R.id.center_atom_display);
        firstAttachedAtomTV = (TextView)findViewById(R.id.first_attached_atom_display);
        secondAttachedAtomTV = (TextView)findViewById(R.id.second_attached_atom_display);
        thirdAttachedAtomTV = (TextView)findViewById(R.id.third_attached_atom_display);
        fourthAttachedAtomTV = (TextView)findViewById(R.id.fourth_attached_atom_display);
        fifthAttachedAtomTV = (TextView)findViewById(R.id.fifth_attached_atom_display);
        sixthAttachedAtomTV = (TextView)findViewById(R.id.sixth_attached_atom_display);
        centerAtomTV.setText("");
        firstAttachedAtomTV.setText("");
        secondAttachedAtomTV.setText("");
        thirdAttachedAtomTV.setText("");
        fourthAttachedAtomTV.setText("");
        fifthAttachedAtomTV.setText("");
        sixthAttachedAtomTV.setText("");
        centerAtomCoefficientTV = (TextView)findViewById(R.id.num_center_atoms);
        firstAttachedAtomCoefficientTV = (TextView)findViewById(R.id.num_first_attached_atoms);
        secondAttachedAtomCoefficientTV = (TextView)findViewById(R.id.num_second_attached_atoms);
        thirdAttachedAtomCoefficientTV = (TextView)findViewById(R.id.num_third_attached_atoms);
        fourthAttachedAtomCoefficientTV = (TextView)findViewById(R.id.num_fourth_attached_atoms);
        fifthAttachedAtomCoefficientTV = (TextView)findViewById(R.id.num_fifth_attached_atoms);
        sixthAttachedAtomCoefficientTV = (TextView)findViewById(R.id.num_sixth_attached_atoms);
        centerAtomCoefficientTV.setText("");
        firstAttachedAtomCoefficientTV.setText("");
        secondAttachedAtomCoefficientTV.setText("");
        thirdAttachedAtomCoefficientTV.setText("");
        fourthAttachedAtomCoefficientTV.setText("");
        fifthAttachedAtomCoefficientTV.setText("");
        sixthAttachedAtomCoefficientTV.setText("");
        centerAtomSpinner = (Spinner)findViewById(R.id.center_atom_spinner);
        attachedAtomSpinner = (Spinner) findViewById(R.id.attached_elements_spinner);
        attachedAtomsArray[0] = firstAttachedAtomTV;
        attachedAtomsArray[1] = secondAttachedAtomTV;
        attachedAtomsArray[2] = thirdAttachedAtomTV;
        attachedAtomsArray[3] = fourthAttachedAtomTV;
        attachedAtomsArray[4] = fifthAttachedAtomTV;
        attachedAtomsArray[5] = sixthAttachedAtomTV;
        attachedAtomsCoefficientArray[0] = firstAttachedAtomCoefficientTV;
        attachedAtomsCoefficientArray[1] = secondAttachedAtomCoefficientTV;
        attachedAtomsCoefficientArray[2] = thirdAttachedAtomCoefficientTV;
        attachedAtomsCoefficientArray[3] = fourthAttachedAtomCoefficientTV;
        attachedAtomsCoefficientArray[4] = fifthAttachedAtomCoefficientTV;
        attachedAtomsCoefficientArray[5] = sixthAttachedAtomCoefficientTV;
    }

    //*******************alerts*******************
    private void showSixAtomsAlert(){
        AlertDialog.Builder alertSixAtoms = new AlertDialog.Builder(this);
        alertSixAtoms.setMessage("You cannot add any more atoms.\nTry removing some???");
        alertSixAtoms.setPositiveButton("Ok", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                dialog.dismiss();
            }
        }).create();
        alertSixAtoms.show();
    }
    private void showSetCenterAtomAlert(){
        AlertDialog.Builder alertSetCenterAtom = new AlertDialog.Builder(this);
        alertSetCenterAtom.setMessage("You need to set the center atom before attaching any atoms");
        alertSetCenterAtom.setPositiveButton("Ok", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                dialog.dismiss();
            }
        }).create();
        alertSetCenterAtom.show();
    }
    private void showDiatomicAlert(){
        AlertDialog.Builder diatomicAlert = new AlertDialog.Builder(this);
        diatomicAlert.setMessage("You cannot add anymore atoms because you have made a diatomic molecule");
        diatomicAlert.setPositiveButton("Ok", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                dialog.dismiss();
            }
        }).create();
        diatomicAlert.show();
    }
    private void showMysteryAddingAlert(){
        AlertDialog.Builder mysteryAddingAlert = new AlertDialog.Builder(this);
        mysteryAddingAlert.setMessage("Mystery Error?");
        mysteryAddingAlert.setPositiveButton("Ok", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                dialog.dismiss();
            }
        }).create();
        mysteryAddingAlert.show();
    }
    private void showSubtractCenterAtomAlert(){
        AlertDialog.Builder subtractCenterAtomAlert = new AlertDialog.Builder(this);
        subtractCenterAtomAlert.setMessage("You cannot subtract the center atom. Try setting a different atom as the center instead.");
        subtractCenterAtomAlert.setPositiveButton("Ok", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                dialog.dismiss();
            }
        }).create();
        subtractCenterAtomAlert.show();
    }
    private void showSelectedNotAttachedAlert(){
        AlertDialog.Builder selectedNotAttachedAlert = new AlertDialog.Builder(this);
        selectedNotAttachedAlert.setMessage("The atom you want to remove is not in this molecule. Try adding it before you remove it.");
        selectedNotAttachedAlert.setPositiveButton("Ok", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                dialog.dismiss();
            }
        }).create();
        selectedNotAttachedAlert.show();
    }
    //*******************menu bar and home button*******************
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
