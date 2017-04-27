package chemia.httpsgithub.comahyoung1.chemia;

import android.util.Log;

import java.io.Serializable;

/**
 * Created by Aaron on 4/23/2017.
 */

public class MoleculeRuleChecker implements Serializable {
    private Molecule molecule;

    public MoleculeRuleChecker(Molecule molecule){
        this.molecule = molecule;
    }

    public void assignBondsLonePairs() {
        if (isDiatomicGas()) {
            //this is a weird thing-----center atoms don't keep track of bonds
            //so when dealing with diatomic-s, just remember that they are #0
            if (molecule.getCenterAtom().getNumOfValenceElectrons() == 1) {
                molecule.setBondAtIndex(0, 0, 1);
            } else {
                int bondType = 8 - molecule.getCenterAtom().getNumOfValenceElectrons();
                molecule.setBondAtIndex(0, 0, bondType);
            }
        } else {
            //so here im going to go through each attached and try to satisfy it by adding bonds
            //each time i add a bond i make note of it for the center, and at the end see if THAT satisfies
            //if it doesn't i check center element for simple octet or expanded
            //extra conditionals at the end for expanded/simple octet
            //check at the end for extra electrons???? HOW
            for (int i = 0; i < 6; i++) {
                Log.d("BIIIIG assignLooOoOoOp", Integer.toString(i));
                Element attachedElementAti = molecule.getAttachedElementArray()[i];
                if (!molecule.elementIsBlank(attachedElementAti)) {
                    if (attachedElementAti.getChemSymbol().equals("H")) {
                        molecule.setBondAtIndex(i, 0, 1);
                        Log.d("THIS IS THE IF hydrogen", "+++++++++++++++++++++");
                        //THIS is also repeated below
                        if (!molecule.subscriptIsBlank(molecule.getAttachedAtomSubscriptArray()[i])) {
                            Log.d("HEy THSSSSSSS is a subb", molecule.getAttachedAtomSubscriptArray()[i]);
                            int moleculeSubscript = Integer.parseInt(molecule.getAttachedAtomSubscriptArray()[i]);
                            for (int n = 1; n < moleculeSubscript; n++) {
                                Log.d("THISthisthisthis", "is the H sub loop");
                                molecule.setBondAtIndex(i, n, 1);
                            }
                        }
                    }
                    else {
                        Log.d("MADE IT TO THE ELSE", "uhuh**************");
                        //adds the number of bonds that the attached needs to be whole
                        int bondsDesiredByElementAti = (8 - attachedElementAti.getNumOfValenceElectrons());
                        Log.d("ALSO", " wants "+bondsDesiredByElementAti+" bonds");
                        molecule.setBondAtIndex(i, 0, bondsDesiredByElementAti);
                        //upcoming loop adds more bonds to total AND sets bond values in molecule bondArray
                        //FOR all other atoms of the current Element
                        //have to also keep the "" here because also talking about the subs that are blank
                        //have to start n at 1 because index 0 is taken by the above code
                        if (molecule.subscriptIsBlank(molecule.getAttachedAtomSubscriptArray()[i])) {
                            Log.d("HEy THSSSSSSS is a subb", molecule.getAttachedAtomSubscriptArray()[i]);
                            int moleculeSubscript = Integer.parseInt(molecule.getAttachedAtomSubscriptArray()[i]);
                            //This loop is to set bonds in the bondArray for each of the elements that are
                            //counted by the subscript, so expect the number counte
                            // d here to be one less
                            //starts at n=1 because one element is accounted for above
                            for (int n = 1; n < moleculeSubscript; n++) {
                                molecule.setBondAtIndex(i, n, bondsDesiredByElementAti);
                            }
                        }
                    }
                }
            }
        }
    }
    public boolean bondMismatch(){
        if(molecule.getTotalNumberOfBonds() != molecule.getNumberOfAttached()){
            return true;
        }
        return false;
    }

    //look for more comments??????
    public boolean electronMisplacement() {
        //Log.d("total VAL", Integer.toString(molecule.getNumberOfTotalValence()));
        //Log.d("numBONDS", Integer.toString(molecule.getTotalNumberOfBonds()));
        int electronsBefore = molecule.getNumberOfTotalValence();
        int electronsAfter = molecule.getTotalNumberOfBonds()*2;
        electronsAfter += (molecule.getCenterLonePairs()*2);
        for(int i=0; i<6; i++){
            int arraySecondIndex = 0;
            while(molecule.getLonePairArray()[i][arraySecondIndex] != 0){
                electronsAfter += (molecule.getLonePairArray()[i][arraySecondIndex]*2);
                arraySecondIndex++;
            }
        }
        //Log.d("after", Integer.toString(electronsAfter));
        //Log.d("before", Integer.toString(electronsAfter));
        if(electronsAfter != electronsBefore) {
            return true;
        }
        else{
            return false;
        }
    }
    private boolean isDiatomicGas(){
        if (molecule.getCenterAtomSubscript().equals("2")){
            return true;
        }
        else{
            return false;
        }
    }
    //DOUBLE CHECK THIS LATER
    //when TIME add electronegativity vals
    public boolean centerIsLessElectroNegative() {
        for (int i=0; i<molecule.getAttachedElementArray().length; i++){
            if (molecule.getCenterAtom().getAtomicNumber() < molecule.getAttachedElementArray()[i].getAtomicNumber()){
                return true;
            }
        }
        return false;
    }
    public boolean tooManyAttachedForCenter(){
        if (!molecule.getCenterAtom().getCanHaveExpandedOctet()){
            if(molecule.getNumberOfAttached()>4){
                return true;
            }
        }
        return false;
    }
}
