package chemia.httpsgithub.comahyoung1.chemia;

/**
 * Created by Aaron on 2/27/2017.
 * This is a data-storage/formatting class that is used when displaying the chemical formula
 * of molecules to the user
 */

public class ChemFormulaBuilder {
    //"Atom" is given as chemical symbol
    private Element centerAtom;
    private Element[] attachedElements = new Element[6];
    //coefficients were made to be of type int to support comparisons
    private int centerAtomCoefficient=0;
    //coefficient index mirrors attachedElements index
    private int[] coefficients = new int[6];
    //to tell what type of bond *structure* there is
    private boolean twoAtomsFlag = false;
    private boolean threeAtomsFlag = false;
    private boolean fourAtomsFlag = false;
    private boolean fiveAtomsFlag = false;
    private boolean sixAtomsFlag = false;
    private boolean sevenAtomsFlag = false;
    private boolean diatomicFlag = false;

    //*************************************Constructors*************************************
    //constructors must accommodate up to six attachedAtoms

    public ChemFormulaBuilder(Element centerAtom, Element[] attachedAtoms){
        //this.centerAtom = centerAtom.getChemSymbol();
        //cycle through input
        for(int i=0; i<attachedAtoms.length; i++) {
            //only add to Elements if does not exist there already
            boolean addToElements = true;
            //index of where to add to Elements next
            int elementAddIndex = 0;
            //cycle through added Atoms
            for (int n = 0; i < this.attachedElements.length; i++) {
                if (attachedAtoms[i].equals(this.attachedElements[n])) {
                    coefficients[n]++;
                    addToElements = false;
                    break;
                }
            }
            if (addToElements) {
                this.attachedElements[elementAddIndex] = attachedAtoms[i];
                elementAddIndex++;
            }
        }
        switch(attachedAtoms.length){
            //#AtomsFlag counts the center atom because it is used for bond configuration
            case 0:
                //error
                break;
            case 1:
                twoAtomsFlag=true;
                if (attachedAtoms[0].equals(centerAtom)){
                    diatomicFlag=true;
                }
                break;
            case 2:
                threeAtomsFlag=true;
                break;
            case 3:
                fourAtomsFlag=true;
                break;
            case 4:
                fiveAtomsFlag = true;
                break;
            case 5:
                sixAtomsFlag = true;
                break;
            case 6:
                sevenAtomsFlag = true;
                break;
        }
    }
    //*************************************Getters*************************************

    public int getCenterAtomCoefficient() {return centerAtomCoefficient;}

    public String[][] build(){

        //take list of unique elements
        //string put into formula array
        //add coefficients accordingly
        int numUniqueElements = attachedElements.length;
        //?????will this always return 6?????
        String[][] chemFormula = new String[2][numUniqueElements];

        for (int i=0; i<numUniqueElements; i++){
            chemFormula[0][i] = attachedElements[i].getChemSymbol();
            chemFormula[1][i] = Integer.toString(coefficients[i]);
        }

        /*
        if (twoAtomsFlag){
            if(diatomicFlag){
                return buildDiatomicFormula();
            }
            else{
                return buildTwoElementFormula();
            }
        }
        else if (threeAtomsFlag){
            //this is a little redundent, but I'm not sure if I want to pass in 0 or 1
            if(coefficients[0]==2){
                return buildTwoElementFormula();
            }
            else if {

            }
            else{
                //error, coefficient out of bounds
            }
            String[][] chemFormula = new String[3][3];
            chemFormula[0][0] = centerAtom;
            chemFormula[1][0] = firstAttachedAtom;
        }
        else if(fourAtomsFlag){

        }

        else if (fiveAtomsFlag){

        }
        else if (sixAtomsFlag){

        }
        else if (sevenAtomsFlag){
            String[][] chemFormula = new String[7][2];
            return chemFormula;
        }
        else{
            //error
        }
    }

    //chemFormula[0][n] will be chem symbol
    //chemFormula[1][n] will be coefficient - empty string/1/0 if none????????????
    private String[][] buildDiatomicFormula(){
        String[][] chemFormula = new String[1][2];
        chemFormula[0][0] = centerAtom;
        chemFormula[1][0] = Integer.toString(centerAtomCoefficient);
        return chemFormula;
    }
    private String[][] buildTwoElementFormula(){
        String[][] chemFormula = new String[2][2];
        chemFormula[0][0] = centerAtom;
        chemFormula[0][1] = firstAttachedAtom;
        chemFormula[1][1] = Integer.toString(firstAtomCoefficient);
        return chemFormula;
    }
    private String[][] buildThreeElementFormula(){
        String[][] chemFormula = new String[2][3];
        chemFormula[0][0] = centerAtom;
        chemFormula[0][1] = firstAttachedAtom;
        chemFormula[1][1] = Integer.toString(firstAtomCoefficient);
        chemFormula[0][2] = secondAttachedAtom;
        chemFormula[1][2] = Integer.toString(secondAtomCoefficient);
        return chemFormula;
    }
    private String[][] buildFourElementFormula(){
        String[][] chemFormula = new String[2][4];
        chemFormula[0][0] = centerAtom;
        chemFormula[0][1] = firstAttachedAtom;
        chemFormula[1][1] = Integer.toString(firstAtomCoefficient);
        chemFormula[0][2] = secondAttachedAtom;
        chemFormula[1][2] = Integer.toString(secondAtomCoefficient);
        chemFormula[0][3] = thirdAttachedAtom;
        chemFormula[1][3] = Integer.toString(thirdAtomCoefficient);
        return chemFormula;
    }
    private String[][] buildFiveElementFormula(){
        String[][] chemFormula = new String[2][5];
        chemFormula[0][0] = centerAtom;
        chemFormula[0][1] = firstAttachedAtom;
        chemFormula[1][1] = Integer.toString(firstAtomCoefficient);
        chemFormula[0][2] = secondAttachedAtom;
        chemFormula[1][2] = Integer.toString(secondAtomCoefficient);
        chemFormula[0][3] = thirdAttachedAtom;
        chemFormula[1][3] = Integer.toString(thirdAtomCoefficient);
        chemFormula[0][4] = fourthAttachedAtom;
        chemFormula[1][4] = Integer.toString(fourthAtomCoefficient);
        return chemFormula;
    }
    private String[][] buildSixElementFormula(){
        String[][] chemFormula = new String[2][6];
        chemFormula[0][0] = centerAtom;
        chemFormula[0][1] = firstAttachedAtom;
        chemFormula[1][1] = Integer.toString(firstAtomCoefficient);
        chemFormula[0][2] = secondAttachedAtom;
        chemFormula[1][2] = Integer.toString(secondAtomCoefficient);
        chemFormula[0][3] = thirdAttachedAtom;
        chemFormula[1][3] = Integer.toString(thirdAtomCoefficient);
        chemFormula[0][4] = fourthAttachedAtom;
        chemFormula[1][4] = Integer.toString(fourthAtomCoefficient);
        chemFormula[0][5] = fifthAttachedAtom;
        chemFormula[1][5] = Integer.toString(fifthAtomCoefficient);
        return chemFormula;
    }
    private String[][] buildSevenElementFormula(){
        String[][] chemFormula = new String[2][7];
        chemFormula[0][0] = centerAtom;
        chemFormula[0][1] = firstAttachedAtom;
        chemFormula[1][1] = Integer.toString(firstAtomCoefficient);
        chemFormula[0][2] = secondAttachedAtom;
        chemFormula[1][2] = Integer.toString(secondAtomCoefficient);
        chemFormula[0][3] = thirdAttachedAtom;
        chemFormula[1][3] = Integer.toString(thirdAtomCoefficient);
        chemFormula[0][4] = fourthAttachedAtom;
        chemFormula[1][4] = Integer.toString(fourthAtomCoefficient);
        chemFormula[0][5] = fifthAttachedAtom;
        chemFormula[1][5] = Integer.toString(fifthAtomCoefficient);
        chemFormula[0][6] = sixthAttachedAtom;
        chemFormula[1][6] = Integer.toString(sixthAtomCoefficient);
        return chemFormula;
    }
    */
}
