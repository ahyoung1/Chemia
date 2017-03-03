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
    private Element centerAtom;
    private Element[] attachedElementArray;
    private String[] attachedAtomSubscriptArray;
    private String centerAtomSubscript;

    public Molecule(Element centerAtom, String centerAtomSubscript, Element[] attachedAtoms, String[] attachedAtomSubscriptArray){
        this.centerAtom = centerAtom;
        this.centerAtomSubscript = centerAtomSubscript;
        this.attachedElementArray = attachedAtoms;
        this.attachedAtomSubscriptArray = attachedAtomSubscriptArray;

        numberOfTotalValence = calculateTotalValence();
        //This is a silly logic block
        /*
        int numberOfDesiredBonds;
        if(centerAtom.getCanHaveSimpleOctet()){
            numberOfDesiredBonds = 3;
        }
        else if(centerAtom.getCanHaveExpandedOctet()){

        }
        else{
            //someFunctionThatINeed();
        }
        */
    }

    private int calculateTotalValence(){
        int sumOfAttached=0;
        if(!centerAtomSubscript.equals("")){
            return 2*centerAtom.getNumOfValenceElectrons();
        }
        else {
            for (int i = 0; i < attachedElementArray.length; i++) {
                if (attachedElementArray[i] != null){
                    int subscript=1;
                    //*********************************watch this part
                    if (!attachedAtomSubscriptArray[i].equals("")){
                        subscript=Integer.valueOf(attachedAtomSubscriptArray[i]);
                    }
                    sumOfAttached = sumOfAttached+(subscript*attachedElementArray[i].getNumOfValenceElectrons());
                }
            }
            return (sumOfAttached + centerAtom.getNumOfValenceElectrons());
        }
    }

    public int getNumberOfTotalValence(){
        return numberOfTotalValence;
    }

    //****************Getters*********************
    public Element getCenterAtom() {return centerAtom;}
    public Element[] getAttachedElementArray() {return attachedElementArray;}
    public String[] getAttachedAtomSubscriptArray(){return attachedAtomSubscriptArray;}
    public String getCenterAtomSubscript(){return centerAtomSubscript;}

}
