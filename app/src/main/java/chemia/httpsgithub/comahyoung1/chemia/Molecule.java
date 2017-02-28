package chemia.httpsgithub.comahyoung1.chemia;

/**
 * Created by Aaron on 2/19/2017.
 */

public class Molecule {
    ChemFormula formula;
    //maybe find a way to name? Naming is easy for these, right?
    String name;
    Bond[] bondArray = new Bond[8];
    //so this is going to work in such a way that a bond is created, then added tot he array

    public Molecule(Element centerAtom, Element firstAttachedAtom, Element secAttachedAtom){
        int numberOfDesiredBonds;
        if(centerAtom.getCanHaveSimpleOctet()){
            numberOfDesiredBonds = 3;
        }
        else if(centerAtom.getCanHaveExpandedOctet()){

        }
        else{
            //someFunctionThatINeed();
        }
    }

    private void someFunctionThatINeed(Element centerAtom, Element[] attachedAtoms, int numOfAttachedAtoms){
        int centerAtomValence = centerAtom.getNumOfValenceElectrons();
        int numberOfDesireBonds;
        switch (centerAtomValence){
            //
            case 1:
                if (isDiatomicGas(numOfAttachedAtoms, centerAtom, attachedAtoms[0])){
                    buildDiatomicGas(centerAtom);
                }
                else{
                    //error hydrogen CAN'T be center atom
                }
                break;
            //
            case 4:
                break;
            //
            case 5:
                if(isDiatomicGas(numOfAttachedAtoms, centerAtom, attachedAtoms[0])){
                    buildDiatomicGas(centerAtom);
                }
                else{

                }
                break;
            //
            case 6:
                if(isDiatomicGas(numOfAttachedAtoms, centerAtom, attachedAtoms[0])){
                    buildDiatomicGas(centerAtom);
                }
                else{

                }
                break;
            //
            case 7:
                if(isDiatomicGas(numOfAttachedAtoms, centerAtom, attachedAtoms[0])){
                    buildDiatomicGas(centerAtom);
                }
                else{

                }
                break;
            //
            case 8:
                if (centerAtom.getCanHaveExpandedOctet()){

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

    private void buildDiatomicGas(Element centerAtom){
        this.name = centerAtom.getName()+" Gas";
        this.formula = new ChemFormula(centerAtom.getChemSymbol(), 2);
    }
}
