package chemia.httpsgithub.comahyoung1.chemia;

import android.util.Log;

import java.io.Serializable;

/**
 * Created by Aaron on 4/23/2017.
 */

public class MoleculeRuleChecker implements Serializable {
    private Molecule molecule;
    private PeriodicTableBuilder periodicTable = new PeriodicTableBuilder();

    public MoleculeRuleChecker(Molecule molecule){
        this.molecule = molecule;
    }

    public void assignBondsLonePairs(){
        if (isDiatomicGas()){
            //yay
        }
        else {
            //so here im going to go through each attached and try to satisfy it by adding bonds
            //each time i add a bond i make note of it for the center, and at the end see if THAT satisfies
            //if it doesn't i check center element for simple octet or expanded
            //extra conditionals at the end for expanded/simple octet
            //check at the end for extra electrons???? HOW
            for (int i = 0; i < molecule.getNumberOfAttached()-1; i++) {
                Element attachedElementAti = molecule.getAttachedElementArray()[i];
                if (attachedElementAti != null) {
                    if (!attachedElementAti.getChemSymbol().equals("")) {
                        if (attachedElementAti.getChemSymbol().equals("H")) {
                            molecule.incTotalNumberOfBonds(1);
                            molecule.setBondAtIndex(i, 0, 1);
                            continue;
                        } else {
                            //adds the number of bonds that the attached needs to be whole
                            int bondsDesiredByElementAti = (8 - 1 - molecule.getAttachedElementArray()[i].getNumOfValenceElectrons());
                            molecule.incTotalNumberOfBonds(bondsDesiredByElementAti);
                            molecule.setBondAtIndex(i, 0, bondsDesiredByElementAti);
                            //upcoming loop adds more bonds to total AND sets bond values in molecule bondArray
                            //FOR all other atoms of the current Element
                            String moleculeSubscript = molecule.getAttachedAtomSubscriptArray()[i];
                            if (!moleculeSubscript.equals("")) {
                                for (int n = 0; n < Integer.parseInt(moleculeSubscript); n++) {
                                    molecule.setBondAtIndex(i, n, bondsDesiredByElementAti);
                                    molecule.incTotalNumberOfBonds(bondsDesiredByElementAti);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    public boolean bondMismatch(){
        if(molecule.getTotalNumberOfBonds() != molecule.getNumberOfAttached()){
            Log.d("myError", "number of bonds and attached was bad");
            return false;
            //something happened???? my logic was bad
        }
        return true;
    }

    //look for more comments?????? and print things to console
    public boolean electronMisplacement() {
        Log.d("total VAL", Integer.toString(molecule.getNumberOfTotalValence()));
        Log.d("numBONDS", Integer.toString(molecule.getTotalNumberOfBonds()));
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
        Log.d("after", Integer.toString(electronsAfter));
        Log.d("before", Integer.toString(electronsAfter));
        if(electronsAfter != electronsBefore) {
            return true;
        }
        else{
            return false;
        }
    }
    private boolean isDiatomicGas(){
        if (molecule.getAttachedElementArray()[0].getName().equals(molecule.getCenterAtom().getName())){
            if (molecule.getCenterAtom().getNumOfValenceElectrons()==1){
                molecule.setBondAtIndex(0,0,1);
            }
            else{
                int bondType = 8 -molecule.getCenterAtom().getNumOfValenceElectrons();
                molecule.setBondAtIndex(0,0,bondType);
            }
            return true;
        }
        else{
            return false;
        }
    }
    //DOUBLE CHECK THIS LATER
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
