package chemia.httpsgithub.comahyoung1.chemia;

import java.io.Serializable;

/**
 * Created by Aaron on 4/23/2017.
 */

public class MoleculeRuleChecker implements Serializable {
    Element[] attachedAtoms;
    public MoleculeRuleChecker(Molecule molecule){
        this.attachedAtoms = attachedAtoms;
        int centerAtomValence = molecule.getCenterAtom().getNumOfValenceElectrons();
        switch (centerAtomValence){
            //Just hydrogen in the current scope of the project
            case 1:
                if (isDiatomicGas(attachedAtoms.length, molecule.getCenterAtom(), attachedAtoms[0])){
                    //  buildDiatomicGas(centerAtom, typeOfBond);
                }
                else{
                    //error hydrogen CAN'T be center atom
                }
                break;
            //
            case 4:
                if (isHyperValentMolecule(molecule.getCenterAtom(), attachedAtoms)){
                    //buildHyperValentMolecule(centerAtom, attachedAtoms);
                }
                //HAVE error generated in here - if !canHaveExpandedOctet && numofDesiredElectrons < numberOfAttachedAtoms
                break;
            //
            case 5:
                if(isDiatomicGas(attachedAtoms.length, molecule.getCenterAtom(), attachedAtoms[0])){
                    // buildDiatomicGas(centerAtom, typeOfBond);
                }
                else{

                }
                break;
            //
            case 6:
                if(isDiatomicGas(attachedAtoms.length, molecule.getCenterAtom(), attachedAtoms[0])){
                    // buildDiatomicGas(centerAtom, typeOfBond);
                }
                else{

                }
                break;
            //
            case 7:
                if(isDiatomicGas(attachedAtoms.length, molecule.getCenterAtom(), attachedAtoms[0])){
                    //buildDiatomicGas(centerAtom, typeOfBond);
                }
                else{

                }
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
    }

    private boolean isDiatomicGas(int numOfAttachedAtoms, Element centerAtom, Element attachedAtom){
        if (numOfAttachedAtoms!=1){
            return false;
        }
        else if (centerAtom.getName().equals(attachedAtom.getName())){
            return true;
        }
        else{
            return false;
        }
    }
/*
    private Molecule buildDiatomicGas(Element centerAtom, String typeOfBond){
        String name = centerAtom.getName()+" Gas";
        String[][] formula = new ChemFormulaBuilder(centerAtom, attachedAtoms).build();
        Bond[] bondArray = new Bond[6];
        bondArray[0] = new Bond(typeOfBond, centerAtom, centerAtom);
        Molecule molecule = new Molecule(centerAtom, attachedAtoms);
        return molecule;
    }*/

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
}
