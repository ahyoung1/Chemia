package chemia.httpsgithub.comahyoung1.chemia;

import android.util.Log;

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
    public static Element dummyFillerElement = new Element("", -1, -1, "", false, false);

    public Molecule(Element centerAtom, String centerAtomSubscript, Element[] attachedAtoms, String[] attachedAtomSubscriptArray){
        this.centerAtom = centerAtom;
        this.centerAtomSubscript = centerAtomSubscript;
        this.attachedElementArray = attachedAtoms;
        this.attachedAtomSubscriptArray = attachedAtomSubscriptArray;
        calculateTotalValence();
        calculateNumberOfAttached();
    }

    private void calculateNumberOfAttached() {
        if(centerAtomSubscript=="2"){
            numberOfAttached = 1;
            return;
        }
        for(int i=0; i<6; i++) {
            if (attachedElementArray[i].getChemSymbol().equals("")){
                return;
            }
            else{
                numberOfAttached++;
                String attachedSub = attachedAtomSubscriptArray[i];
                if(!attachedSub.equals("") && !attachedSub.equals("-1")){
                    numberOfAttached = Integer.parseInt(attachedSub);
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
                if (attachedElementArray[i] != null && !attachedElementArray[i].getChemSymbol().equals("")) {
                    int subscript = 1;
                    if (!attachedAtomSubscriptArray[i].equals("-1") && !attachedAtomSubscriptArray[i].equals("")) {
                        subscript = Integer.parseInt(attachedAtomSubscriptArray[i]);
                    }
                    sumOfAttached += (subscript * attachedElementArray[i].getNumOfValenceElectrons());
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
    public void setBondAtIndex(int index, int occurrence, int bondNumber){
        bondArray[index][occurrence]=bondNumber;
        incTotalNumberOfBonds(bondNumber);
    }
    public void increaseBondAtIndex(int index, int occurrence){bondArray[index][occurrence]++;}
    public int[][] getLonePairArray(){return lonePairArray;}
    public  int getCenterLonePairs(){return centerLonePairs;}
    public int[][] getBondArray(){return  bondArray;}
    public void incTotalNumberOfBonds(int inc){totalNumberOfBonds+=inc;}
    public int getTotalNumberOfBonds(){return totalNumberOfBonds;}

    //this is definitely utility and shouldn't be here
    public boolean elementIsBlank(Element attachedElementAti){
        if (attachedElementAti.equals(null)) {
            return true;
        }
        else if(attachedElementAti.getChemSymbol().equals("")){
            return true;
        }
        else if(attachedElementAti.equals(this.dummyFillerElement)){
            return true;
        }
        else{
            return false;
        }
    }

    public boolean subscriptIsBlank(String subscript){
        if(subscript.equals("")){
            return true;
        }
        else if(subscript.equals(null)){
            return true;
        }
        else if(Integer.parseInt(subscript)>6){
            return true;
        }
        else if(Integer.parseInt(subscript)<0){
            return true;
        }
        else{
            return false;
        }
    }

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
