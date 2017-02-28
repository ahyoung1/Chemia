package chemia.httpsgithub.comahyoung1.chemia;

/**
 * Created by Aaron on 2/27/2017.
 * This is a data-storage/formatting class that is used when displaying the chemical formula
 * of molecules to the user
 */

public class ChemFormulaBuilder {
    //"Atom" is given as chemical symbol
    private String centerAtom="";
    private String firstAttachedAtom="";
    private String secondAttachedAtom="";
    private String thirdAttachedAtom="";
    private String fourthAttachedAtom="";
    private String fifthAttachedAtom="";
    private String sixthAttachedAtom="";
    //coefficients were made to be of type int to add clarity to constructors
    private int centerAtomCoefficient=0;
    private int firstAtomCoefficient=0;
    private int secondAtomCoefficient=0;
    private int thirdAtomCoefficient=0;
    private int fourthAtomCoefficient=0;
    private int fifthAtomCoefficient=0;
    private int sixthAtomCoefficient=0;
    //to tell what type of bond structure there is
    private boolean twoAtomsFlag = false;
    private boolean threeAtomsFlag = false;
    private boolean fourAtomsFlag = false;
    private boolean fiveAtomsFlag = false;
    private boolean sixAtomsFlag = false;
    private boolean sevenAtomsFlag = false;
    private boolean diatomicFlag = false;

    //*************************************Constructors*************************************
    //constructors must accommodate up to six attachedAtoms

    public ChemFormulaBuilder(String centerAtom, String firstAttachedAtom, int firstAtomCoefficient, String secondAttachedAtom, int secondAtomCoefficient,
                              String thirdAttachedAtom, int thirdAtomCoefficient, String fourthAttachedAtom, int fourthAtomCoefficient, String fifthAttachedAtom, int fifthAtomCoefficient,
                              String sixthAttachedAtom, int sixthAtomCoefficient){
        this.centerAtom = centerAtom;
        this.firstAttachedAtom = firstAttachedAtom;
        this.firstAtomCoefficient = firstAtomCoefficient;
        this.secondAttachedAtom = secondAttachedAtom;
        this.secondAtomCoefficient = secondAtomCoefficient;
        this.thirdAttachedAtom = thirdAttachedAtom;
        this.thirdAtomCoefficient = thirdAtomCoefficient;
        this.fourthAttachedAtom = fourthAttachedAtom;
        this.fourthAtomCoefficient = fourthAtomCoefficient;
        this.fifthAttachedAtom = fifthAttachedAtom;
        this.fifthAtomCoefficient = fifthAtomCoefficient;
        this.sixthAttachedAtom = sixthAttachedAtom;
        this.sixthAtomCoefficient = sixthAtomCoefficient;

        sevenAtomsFlag = true;
    }
    public ChemFormulaBuilder(String centerAtom, String firstAttachedAtom, int firstAtomCoefficient, String secondAttachedAtom, int secondAtomCoefficient,
                              String thirdAttachedAtom, int thirdAtomCoefficient, String fourthAttachedAtom, int fourthAtomCoefficient, String fifthAttachedAtom, int fifthAtomCoefficient){
        this.centerAtom = centerAtom;
        this.firstAttachedAtom = firstAttachedAtom;
        this.firstAtomCoefficient = firstAtomCoefficient;
        this.secondAttachedAtom = secondAttachedAtom;
        this.secondAtomCoefficient = secondAtomCoefficient;
        this.thirdAttachedAtom = thirdAttachedAtom;
        this.thirdAtomCoefficient = thirdAtomCoefficient;
        this.fourthAttachedAtom = fourthAttachedAtom;
        this.fourthAtomCoefficient = fourthAtomCoefficient;
        this.fifthAttachedAtom = fifthAttachedAtom;
        this.fifthAtomCoefficient = fifthAtomCoefficient;

        sixAtomsFlag = true;
    }
    public ChemFormulaBuilder(String centerAtom, String firstAttachedAtom, int firstAtomCoefficient, String secondAttachedAtom, int secondAtomCoefficient,
                              String thirdAttachedAtom, int thirdAtomCoefficient, String fourthAttachedAtom, int fourthAtomCoefficient){
        this.centerAtom = centerAtom;
        this.firstAttachedAtom = firstAttachedAtom;
        this.firstAtomCoefficient = firstAtomCoefficient;
        this.secondAttachedAtom = secondAttachedAtom;
        this.secondAtomCoefficient = secondAtomCoefficient;
        this.thirdAttachedAtom = thirdAttachedAtom;
        this.thirdAtomCoefficient = thirdAtomCoefficient;
        this.fourthAttachedAtom = fourthAttachedAtom;
        this.fourthAtomCoefficient = fourthAtomCoefficient;

        fiveAtomsFlag = true;
    }
    public ChemFormulaBuilder(String centerAtom, String firstAttachedAtom, int firstAtomCoefficient, String secondAttachedAtom, int secondAtomCoefficient, String thirdAttachedAtom, int thirdAtomCoefficient){
        this.centerAtom = centerAtom;
        this.firstAttachedAtom = firstAttachedAtom;
        this.firstAtomCoefficient = firstAtomCoefficient;
        this.secondAttachedAtom = secondAttachedAtom;
        this.secondAtomCoefficient = secondAtomCoefficient;
        this.thirdAttachedAtom = thirdAttachedAtom;
        this.thirdAtomCoefficient = thirdAtomCoefficient;

        fourAtomsFlag = true;
    }
    public ChemFormulaBuilder(String centerAtom, String firstAttachedAtom, int firstAtomCoefficient, String secondAttachedAtom, int secondAtomCoefficient){
        this.centerAtom = centerAtom;
        this.firstAttachedAtom = firstAttachedAtom;
        this.firstAtomCoefficient = firstAtomCoefficient;
        this.secondAttachedAtom = secondAttachedAtom;
        this.secondAtomCoefficient = secondAtomCoefficient;

        threeAtomsFlag = true;
    }
    public ChemFormulaBuilder(String centerAtom, String firstAttachedAtom, int firstAtomCoefficient){
        this.centerAtom = centerAtom;
        this.firstAttachedAtom = firstAttachedAtom;
        this.firstAtomCoefficient = firstAtomCoefficient;

        twoAtomsFlag = true;
    }
    public ChemFormulaBuilder(String centerAtom, int centerAtomCoefficient){
        this.centerAtom = centerAtom;
        this.centerAtomCoefficient = centerAtomCoefficient;

        twoAtomsFlag = true;
        diatomicFlag = true;
    }
    //*************************************Getters*************************************

    public String getCenterAtom() {return centerAtom;}
    public String getFirstAttachedAtom() {return firstAttachedAtom;}
    public String getSecondAttachedAtom() {return secondAttachedAtom;}
    public String getThirdAttachedAtom() {return thirdAttachedAtom;}
    public String getFourthAttachedAtom() {return fourthAttachedAtom;}
    public String getFifthAttachedAtom() {return fifthAttachedAtom;}
    public String getSixthAttachedAtom() {return sixthAttachedAtom;}
    public int getCenterAtomCoefficient() {return centerAtomCoefficient;}
    public int getFirstAtomCoefficient() {return firstAtomCoefficient;}
    public int getSecondAtomCoefficient() {return secondAtomCoefficient;}
    public int getThirdAtomCoefficient() {return thirdAtomCoefficient;}
    public int getFourthAtomCoefficient() {return fourthAtomCoefficient;}
    public int getFifthAtomCoefficient() {return fifthAtomCoefficient;}
    public int getSixthAtomCoefficient() {return sixthAtomCoefficient;}

    public String[][] build(){
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
            if(firstAtomCoefficient==2){
                return buildTwoElementFormula()
            }
            else if (firstAtomCoefficient==2){

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
}
