package chemia.httpsgithub.comahyoung1.chemia;

import java.io.Serializable;

/**
 * Created by Aaron on 2/19/2017.
 */

public class Molecule implements Serializable{
    //Chemical formulas are going to be 2D string arrays
    //formula[0][n] will be chem symbol
    //formula[1][n] will be coefficient - empty string if none
    ChemFormulaBuilder formula;
    //maybe find a way to name? Naming is easy for these, right? ------ NOT NECESSARY???
    String name;
    Bond[] bondArray = new Bond[8];
    int numberOfTotalValence;

    //so this is going to work in such a way that a bond is created, then added tot he array
    Element centerAtom;
    Element[] attachedAtomArray;

    public Molecule(Element centerAtom, Element[] attachedAtoms){
        this.centerAtom = centerAtom;
        this.attachedAtomArray = attachedAtoms;

        numberOfTotalValence = calculateTotalValence();

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

    private int calculateTotalValence(){
        int sumOfAttached=0;
        for (int i=0; i<attachedAtomArray.length; i++){
            if (attachedAtomArray[i]!= null){
                sumOfAttached += attachedAtomArray[i].getNumOfValenceElectrons();
            }
        }
        return sumOfAttached + centerAtom.getNumOfValenceElectrons();
    }

    public int getNumberOfTotalValence(){
        return numberOfTotalValence;
    }

    //****************Getters*********************
    public Element getCenterAtom() {return centerAtom;}
    public Element[] getAttachedAtomArray() {return attachedAtomArray;}

}
