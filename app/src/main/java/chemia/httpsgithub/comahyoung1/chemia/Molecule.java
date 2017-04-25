package chemia.httpsgithub.comahyoung1.chemia;

import java.io.Serializable;

/**
 * Created by Aaron on 2/19/2017.
 */

public class Molecule implements Serializable{
    //Chemical formulas are going to be 2D string arrays
    //formula[0][n] will be chem symbol
    //formula[1][n] will be coefficient - empty string if none
    private int numberOfTotalValence;
    private Element centerAtom;
    private Element[] attachedElementArray;
    private String[] attachedAtomSubscriptArray;
    private String centerAtomSubscript;
    //this is not the best way to keep track of bonds...
    //because repeated elements are stored in the same index in attachedElementsArray there's difficulty
    //if there are multiple i'll just double nest I guess
    private int[][] bondArray = new int[6][6];
    private int numberOfAttached=0;
    private int[][] lonePairArray = new int[6][6];
    private int centerLonePairs =0;
    private int totalNumberOfBonds =0;

    public Molecule(Element centerAtom, String centerAtomSubscript, Element[] attachedAtoms, String[] attachedAtomSubscriptArray){
        this.centerAtom = centerAtom;
        this.centerAtomSubscript = centerAtomSubscript;
        this.attachedElementArray = attachedAtoms;
        this.attachedAtomSubscriptArray = attachedAtomSubscriptArray;
        calculateTotalValence();
        calculateNumberOfAttached();
    }

    private void calculateNumberOfAttached() {
        for(int i=0; i<6; i++) {
            if (!attachedElementArray[i].equals("")){
                numberOfAttached++;
                String attachedSub = attachedAtomSubscriptArray[i];
                if(!attachedSub.equals("")){
                    numberOfAttached += Integer.getInteger(attachedSub);
                }
            }
        }
    }

    private void calculateTotalValence() {
        int sumOfAttached = 0;
        if (!centerAtomSubscript.equals("")) {
            numberOfTotalValence = 2 * centerAtom.getNumOfValenceElectrons();
        } else {
            for (int i = 0; i < attachedElementArray.length; i++) {
                if (attachedElementArray[i] != null) {
                    int subscript = 1;
                    //*********************************watch this part
                    if (!attachedAtomSubscriptArray[i].equals("")) {
                        subscript = Integer.valueOf(attachedAtomSubscriptArray[i]);
                    }
                    sumOfAttached = sumOfAttached + (subscript * attachedElementArray[i].getNumOfValenceElectrons());
                }
            }
            numberOfTotalValence = (sumOfAttached + centerAtom.getNumOfValenceElectrons());
        }
    }

    //****************Getters*********************
    public Element getCenterAtom(){return centerAtom;}
    public Element[] getAttachedElementArray(){return attachedElementArray;}
    public String[] getAttachedAtomSubscriptArray(){return attachedAtomSubscriptArray;}
    public String getCenterAtomSubscript(){return centerAtomSubscript;}
    public int getNumberOfTotalValence(){return numberOfTotalValence;}
    public int getNumberOfAttached(){return numberOfAttached;}
    public void setBondAtIndex(int index, int occurrence, int bondNumber){bondArray[index][occurrence]=bondNumber;}
    public void increaseBondAtIndex(int index, int occurrence){bondArray[index][occurrence]++;}
    public int[][] getLonePairArray(){return lonePairArray;}
    public  int getCenterLonePairs(){return centerLonePairs;}
    public int[][] getBondArray(){return  bondArray;}
    public void incTotalNumberOfBonds(int inc){totalNumberOfBonds+=inc;}
    public int getTotalNumberOfBonds(){return totalNumberOfBonds;}


    public boolean checkAllFields() {
        if(numberOfTotalValence==0){
            return false;
        }
        else if(centerAtom==null){
            return false;
        }
        else if(attachedElementArray==null){
            return false;
        }
        else if(attachedAtomSubscriptArray==null){
            return false;
        }
        else if(centerAtomSubscript==null){
            return false;
        }
        else if(bondArray[0][0]==0){
            return false;
        }
        else if(numberOfAttached==0){
            return false;
        }
        else if(lonePairArray[0][0]==0){
            return false;
        }
        else if(centerLonePairs==0){
            return false;
        }
        else if(totalNumberOfBonds==0){
            return false;
        }
        else{
            return true;
        }
    }
}
