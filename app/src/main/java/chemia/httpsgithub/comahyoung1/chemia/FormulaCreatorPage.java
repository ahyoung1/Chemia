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

public class FormulaCreatorPage extends AppCompatActivity {
    private PeriodicTableBuilder periodicTable = new PeriodicTableBuilder();
    private TextView centerAtomTV;
    private TextView firstAttachedAtomTV;
    private TextView secondAttachedAtomTV;
    private TextView thirdAttachedAtomTV;
    private TextView fourthAttachedAtomTV;
    private TextView fifthAttachedAtomTV;
    private TextView sixthAttachedAtomTV;
    private TextView centerAtomSubscriptTV;
    private TextView firstAttachedAtomSubscriptTV;
    private TextView secondAttachedAtomSubscriptTV;
    private TextView thirdAttachedAtomSubscriptTV;
    private TextView fourthAttachedAtomSubscriptTV;
    private TextView fifthAttachedAtomSubscriptTV;
    private TextView sixthAttachedAtomSubscriptTV;
    private TextView[] attachedAtomsTVArray = new TextView[6];
    private TextView[] attachedAtomsSubscriptTVArray = new TextView[6];
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
            attachedAtomsTVArray[n].setText("");
            attachedAtomsSubscriptTVArray[n].setText("");
        }
        selectedCenterAtom = centerAtomSpinner.getSelectedItem().toString();
        selectedCenterAtomChemSymbol = periodicTable.getElementByName(selectedCenterAtom).getChemSymbol();
        centerAtomTV.setText(selectedCenterAtomChemSymbol);
        centerAtomSubscriptTV.setText("");
    }

    //************************Add attached atoms************************
    public void onAttachedAtomAdded(View v){
        if (centerAtomTV.getText().equals("")){
            showSetCenterAtomAlert();
        }
        else if (hasSixAtoms()){
            showSixAtomsAlert();
        }
        else if(centerAtomSubscriptTV.getText().equals("2")){
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
            centerAtomSubscriptTV.setText("2");
            return true;
        }
        for (int i=0; i<6; i++){
            //if text of ith TextView in array is NOT ""
            if (!attachedAtomsTVArray[i].getText().equals("")){
                if (attachedAtomsTVArray[i].getText().equals(selectedAttachedAtomChemSymbol)) {
                    if (attachedAtomsSubscriptTVArray[i].getText()==""){
                        attachedAtomsSubscriptTVArray[i].setText("2");
                    }
                    else{
                        int subscript = Integer.valueOf(attachedAtomsSubscriptTVArray[i].getText().toString());
                        subscript++;
                        attachedAtomsSubscriptTVArray[i].setText(Integer.toString(subscript));
                    }
                    return true;
                }
            }
        }
        for (int n=0; n<6; n++){
            if(attachedAtomsTVArray[n].getText().equals("")){
                String chemSymbol = periodicTable.getElementByName(selectedAttachedAtom).getChemSymbol();
                attachedAtomsTVArray[n].setText(chemSymbol);
                return true;
            }
        }
        return false;
    }

    //a note: .getText().equals("") is similar to checking for null
    private boolean hasSixAtoms(){
        int numberOfAtoms = 0;
        for (int n=0; n<6; n++){
            if(attachedAtomsTVArray[n].getText().equals("")){
                continue;
            }
            else if (!attachedAtomsSubscriptTVArray[n].getText().equals("")){
                numberOfAtoms += Integer.valueOf(attachedAtomsSubscriptTVArray[n].getText().toString());
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
        selectedAttachedAtomChemSymbol = periodicTable.getElementByName(selectedAttachedAtom).getChemSymbol();
        if (centerAtomTV.getText().equals(selectedAttachedAtomChemSymbol)){
            if (centerAtomSubscriptTV.getText().equals("2")){
                removeDiatomicSubscript();
            }
            else{
                showSubtractCenterAtomAlert();
            }
        }
        else{
            boolean atomPresentFlag = false;
            for (int n=0; n<6; n++){
                if (attachedAtomsTVArray[n].getText().equals(selectedAttachedAtomChemSymbol)){
                    if (attachedAtomsSubscriptTVArray[n].getText().equals("")){
                        removeAttachedAtom(n);
                        atomPresentFlag = true;
                    }
                    else{
                        reduceAttachedAtomSubscript(n);
                        atomPresentFlag = true;
                    }
                }
            }
            if (!atomPresentFlag){
                showSelectedNotAttachedAlert();
            }
        }
    }

    private void removeDiatomicSubscript(){
        centerAtomSubscriptTV.setText("");
    }

    private void removeAttachedAtom(int n){
        for (int i=n; i<5; i++){
            attachedAtomsTVArray[i].setText(attachedAtomsTVArray[i+1].getText());
            attachedAtomsSubscriptTVArray[i].setText(attachedAtomsSubscriptTVArray[i+1].getText());
        }
        attachedAtomsTVArray[5].setText("");
        attachedAtomsSubscriptTVArray[5].setText("");
    }

    private void reduceAttachedAtomSubscript(int n){
        if (attachedAtomsSubscriptTVArray[n].getText().equals("2")){
            attachedAtomsSubscriptTVArray[n].setText("");
        }
        else{
            int newSubscript = Integer.valueOf(attachedAtomsSubscriptTVArray[n].getText().toString());
            newSubscript -=1;
            attachedAtomsSubscriptTVArray[n].setText(Integer.toString(newSubscript));
        }
    }

    //clears the formula TextViews --- does NOTHING with MoleculeBuilder etc. Only aesthetic, storage
    public void onResetClick(View v){
        centerAtomTV.setText("");
        for (int n=0; n<6; n++){
            attachedAtomsTVArray[n].setText("");
            attachedAtomsSubscriptTVArray[n].setText("");
        }
        centerAtomSubscriptTV.setText("");
    }
//*******************************************working on********************************************************
    public void onMakeMoleculeClick(View v){
        //THIS is where there should be an error message IF the center atom is less electro-negative??????
        Element centerAtom = periodicTable.getElementBySymbol(centerAtomTV.getText().toString());
        String centerAtomSubscript = centerAtomSubscriptTV.getText().toString();
        Element[] elementArray = new Element[6];
        for (int n=0; n<6; n++) {
            if (attachedAtomsTVArray[n].getText().equals("")){
                continue;
            }
            else {
                elementArray[n] = periodicTable.getElementBySymbol(attachedAtomsTVArray[n].getText().toString());
            }
        }
        String[] subscriptArray = new String[6];
        for (int n=0; n<6; n++){
            subscriptArray[n] = attachedAtomsSubscriptTVArray[n].getText().toString();
        }
        molecule = new Molecule(centerAtom, centerAtomSubscript, elementArray, subscriptArray);
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
        centerAtomSubscriptTV = (TextView)findViewById(R.id.num_center_atoms);
        firstAttachedAtomSubscriptTV = (TextView)findViewById(R.id.num_first_attached_atoms);
        secondAttachedAtomSubscriptTV = (TextView)findViewById(R.id.num_second_attached_atoms);
        thirdAttachedAtomSubscriptTV = (TextView)findViewById(R.id.num_third_attached_atoms);
        fourthAttachedAtomSubscriptTV = (TextView)findViewById(R.id.num_fourth_attached_atoms);
        fifthAttachedAtomSubscriptTV = (TextView)findViewById(R.id.num_fifth_attached_atoms);
        sixthAttachedAtomSubscriptTV = (TextView)findViewById(R.id.num_sixth_attached_atoms);
        centerAtomSubscriptTV.setText("");
        firstAttachedAtomSubscriptTV.setText("");
        secondAttachedAtomSubscriptTV.setText("");
        thirdAttachedAtomSubscriptTV.setText("");
        fourthAttachedAtomSubscriptTV.setText("");
        fifthAttachedAtomSubscriptTV.setText("");
        sixthAttachedAtomSubscriptTV.setText("");
        centerAtomSpinner = (Spinner)findViewById(R.id.center_atom_spinner);
        attachedAtomSpinner = (Spinner) findViewById(R.id.attached_elements_spinner);
        attachedAtomsTVArray[0] = firstAttachedAtomTV;
        attachedAtomsTVArray[1] = secondAttachedAtomTV;
        attachedAtomsTVArray[2] = thirdAttachedAtomTV;
        attachedAtomsTVArray[3] = fourthAttachedAtomTV;
        attachedAtomsTVArray[4] = fifthAttachedAtomTV;
        attachedAtomsTVArray[5] = sixthAttachedAtomTV;
        attachedAtomsSubscriptTVArray[0] = firstAttachedAtomSubscriptTV;
        attachedAtomsSubscriptTVArray[1] = secondAttachedAtomSubscriptTV;
        attachedAtomsSubscriptTVArray[2] = thirdAttachedAtomSubscriptTV;
        attachedAtomsSubscriptTVArray[3] = fourthAttachedAtomSubscriptTV;
        attachedAtomsSubscriptTVArray[4] = fifthAttachedAtomSubscriptTV;
        attachedAtomsSubscriptTVArray[5] = sixthAttachedAtomSubscriptTV;
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
