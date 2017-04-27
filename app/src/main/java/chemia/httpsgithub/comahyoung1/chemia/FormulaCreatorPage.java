package chemia.httpsgithub.comahyoung1.chemia;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
        selectedAttachedAtom = attachedAtomSpinner.getSelectedItem().toString();
        selectedAttachedAtomChemSymbol = periodicTable.getElementByName(selectedAttachedAtom).getChemSymbol();
        if (centerAtomTV.getText().equals("")){
            showAlertWithMessage(getString(R.string.centerAtomNotSetAlert));
        }
        else if (hasMaxUnique()){
            showAlertWithMessage(getString(R.string.maxUniqueAtomsAlert));
        }
        else if (hasSixAtoms()){
            showAlertWithMessage(getString(R.string.maxAtomsAlert));
        }
        else if(centerAtomSubscriptTV.getText().equals("2")){
            showAlertWithMessage(getString(R.string.diatomicAlert));
        }
        else{
            if(!attachAtomToFormula()) {
                showAlertWithMessage(getString(R.string.mysteryAlert));
            }
        }
    }

    //method ONLY returns false when there is some unexplainable error --- will return true and return explainable errors
    private boolean attachAtomToFormula(){
        if(centerAtomTV.getText().equals(selectedAttachedAtomChemSymbol)){
            if(atomCanBeDiatomic()){
                centerAtomSubscriptTV.setText("2");
                return true;
            }
            else{
                showAlertWithMessage(getString(R.string.badDiatomicAlert));
                return true;
            }
        }
        for (int i=0; i<6; i++){
            //if text of ith TextView in array is NOT ""
            if (!attachedAtomsTVArray[i].getText().equals("")){
                if (attachedAtomsTVArray[i].getText().equals(selectedAttachedAtomChemSymbol)) {
                    if (attachedAtomsSubscriptTVArray[i].getText()==""){
                        attachedAtomsSubscriptTVArray[i].setText("2");
                    }
                    else{

                        //NEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE
                        //EEEEEEEEEEEEEEEEEEEEEEEEEED to move the check for uniques down here
                        /*
                        * while we're on it let's talka briefly about the other checks
                        * electronegativity is good
                        * in short  the other one
                        * we need to check the number of electrons in the system
                        * agains the DESIRED# of electrons in the system
                        * find a way to test bonds and see if we can make it work
                        * remember a bond is two atoms each sharing one to count for 2 for both*/
                        int subscript = Integer.valueOf(attachedAtomsSubscriptTVArray[i].getText().toString());
                        subscript++;
                        attachedAtomsSubscriptTVArray[i].setText(Integer.toString(subscript));
                    }
                    return true;
                }
                else{

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
                break;
            }
            else if (!attachedAtomsSubscriptTVArray[n].getText().equals("")){
                numberOfAtoms += Integer.valueOf(attachedAtomsSubscriptTVArray[n].getText().toString());
            }
            else{
                numberOfAtoms++;
                //breaks if no more elements - adds sub if element has sub, adds 1 for element in the else
            }
        }
        return numberOfAtoms == 6;
    }
    private boolean hasMaxUnique(){
        if(isNotUnique()){
            return false;
        }
        else{
            //this counter starts at 1 because of the center atom - code cannot be reached without a center atom being set
            int numberOfUniqueAtoms = 1;
            for(int i=0; i<6; i++){
                if(!attachedAtomsTVArray[i].getText().equals("")){
                    numberOfUniqueAtoms++;
                }
                else{
                    break;
                }
            }
            if (numberOfUniqueAtoms >= 3){
                return true;
            }
            else{
                return false;
            }
        }
    }
    private boolean atomCanBeDiatomic(){
        String[] listOfDiatomicSymbols = periodicTable.getListOfDiatomicSymbols();
        for(int i=0; i<listOfDiatomicSymbols.length; i++){
            if(listOfDiatomicSymbols[i].equals(selectedCenterAtomChemSymbol)){
                return true;
            }
        }
        return false;
    }
    private boolean isNotUnique(){
        for(int i=0; i<6; i++) {
            if (attachedAtomsTVArray[i].getText().equals(selectedAttachedAtomChemSymbol)) {
                return true;
            }
        }
        return false;
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
                showAlertWithMessage(getString(R.string.cannotSubtractCenterAtomAlert));
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
                showAlertWithMessage(getString(R.string.cannotSubtractAtomAlert));
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

    //clears the formula TextViews --- does NOTHING with mbuilder etc. Only aesthetic, storage
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
        Element centerAtom = periodicTable.getElementBySymbol(centerAtomTV.getText().toString());
        String centerAtomSubscript = centerAtomSubscriptTV.getText().toString();
        Element[] elementArray = new Element[6];
        String[] subscriptArray = new String[6];
        //this loop is responsible for initializing the Element and subscript arrays --- ABSTRACT THIS
        for (int n=0; n<6; n++) {
            if (attachedAtomsTVArray[n].getText().equals("")){
                elementArray[n] = molecule.dummyFillerElement;
                subscriptArray[n] = "-1";
                //this may cause some problems????? but I think the array needs these values just to work
            }
            else {
                elementArray[n] = periodicTable.getElementBySymbol(attachedAtomsTVArray[n].getText().toString());
            }
            String sub = attachedAtomsSubscriptTVArray[n].getText().toString();
            if(sub.equals(null) || sub.equals("")){
                subscriptArray[n] = "";
            }
            else{
                subscriptArray[n] = attachedAtomsSubscriptTVArray[n].getText().toString();
            }
        }
        molecule = new Molecule(centerAtom, centerAtomSubscript, elementArray, subscriptArray);
        MoleculeRuleChecker ruleChecker = new MoleculeRuleChecker(molecule);
        Log.d("FIRST", "there are"+molecule.getTotalNumberOfBonds()+" bonds------------");
        ruleChecker.assignBondsLonePairs();
        Log.d("SECOND", "there are"+molecule.getTotalNumberOfBonds()+" bonds------------");
        if(ruleChecker.centerIsLessElectroNegative()){
            showAlertWithMessage(getString(R.string.electroNegativityAlert));
            return;
        }
        else if(ruleChecker.tooManyAttachedForCenter()){
            showAlertWithMessage(getString(R.string.tooManyAttachedAlert));
            return;
        }
        else if(ruleChecker.bondMismatch()){

            Log.d("there are ", Integer.toString(molecule.getTotalNumberOfBonds())+" bonds");

            showAlertWithMessage(getString(R.string.bondMisAlert));
            return;
        }
        else if(ruleChecker.electronMisplacement()){
            showAlertWithMessage(getString(R.string.electronMisPlaceAlert));
            return;
        }
        //not valid molecule --- for purposes of this exercise --- bonds and lone pairs cannot be configured
        if(molecule.checkAllFields()){
            showAlertWithMessage(getString(R.string.moleculeFieldAlert));
            return;
        }
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

    //*******************ALERT func*******************
    private void showAlertWithMessage(String msg) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setMessage(msg);
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {dialog.dismiss();}
        }).create();
        alert.show();
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
