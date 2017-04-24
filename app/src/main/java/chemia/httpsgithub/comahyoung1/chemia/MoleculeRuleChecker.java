package chemia.httpsgithub.comahyoung1.chemia;

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

    public boolean areEnoughElectrons(){
        if (isDiatomicGas()){
            return true;
        }
        /*
        int centerAtomValence = molecule.getCenterAtom().getNumOfValenceElectrons();
        switch (centerAtomValence){
            //Just hydrogen in the current scope of the project
            case 1:
                if (isDiatomicGas()){return true;}
                else{
                    //error hydrogen CAN'T be center atom
                }
                break;
            //
            case 4:
                if (isHyperValentMolecule(molecule.getCenterAtom(), molecule.getAttachedElementArray())){
                    //buildHyperValentMolecule(centerAtom, attachedAtoms);
                }
                //HAVE error generated in here - if !canHaveExpandedOctet && numofDesiredElectrons < numberOfAttachedAtoms
                break;
            //
            case 5:
                if(isDiatomicGas(molecule.getAttachedElementArray().length, molecule.getCenterAtom(), molecule.getAttachedElementArray()[0])){
                    // buildDiatomicGas(centerAtom, typeOfBond);
                }
                else{

                }
                break;
            //
            case 6:
                if(isDiatomicGas(molecule.getAttachedElementArray().length, molecule.getCenterAtom(), molecule.getAttachedElementArray()[0])){
                    // buildDiatomicGas(centerAtom, typeOfBond);
                }
                else{

                }
                break;
            //
            case 7:
                break;
            //
            case 8:
                if (molecule.getCenterAtom().getCanHaveExpandedOctet()){

                }
                else{
                    //error msg can't bond?????
                }
                break;
        }
        */
        return true;
        //this SHOULD NOT BEEEEEEEEE
    }
    private boolean isDiatomicGas(){
        if (molecule.getAttachedElementArray()[0].getName().equals(molecule.getCenterAtom().getName())){
            return true;
        }
        else{
            return false;
        }
    }
    private boolean isHyperValentMolecule(Element centerAtom, Element[] attachedAtoms){
        if (centerAtom.getCanHaveExpandedOctet() && attachedAtoms.length>4) {
            return true;
        }
        else if (attachedAtoms.length>4){
            //error for MORE atoms than center atom wants (less than period 3)
            return true;
        }
        else{
            return false;
        }
    }
    public boolean centerIsLessElectroNegative() {
        for (int i=0; i<molecule.getAttachedElementArray().length; i++){
            if (molecule.getCenterAtom().getAtomicNumber() < molecule.getAttachedElementArray()[i].getAtomicNumber()){
                return true;
            }
        }
        return false;
    }
}
