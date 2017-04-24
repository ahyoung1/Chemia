package chemia.httpsgithub.comahyoung1.chemia;

import java.io.Serializable;

/**
 * Created by Aaron on 2/19/2017.
 */

public class Molecule implements Serializable{
    //Chemical formulas are going to be 2D string arrays
    //formula[0][n] will be chem symbol
    //formula[1][n] will be coefficient - empty string if none
    int numberOfTotalValence;
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

    //****************Getters*********************
    public Element getCenterAtom(){return centerAtom;}
    public Element[] getAttachedElementArray(){return attachedElementArray;}
    public String[] getAttachedAtomSubscriptArray(){return attachedAtomSubscriptArray;}
    public String getCenterAtomSubscript(){return centerAtomSubscript;}
    public int getNumberOfTotalValence(){return numberOfTotalValence;}

}
