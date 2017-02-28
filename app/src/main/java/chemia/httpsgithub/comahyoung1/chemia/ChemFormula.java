package chemia.httpsgithub.comahyoung1.chemia;

/**
 * Created by Aaron on 2/27/2017.
 */

public class ChemFormula {
    //"Atom" is given as chemical symbol
    private String centerAtom;
    private String firstAttachedAtom;
    private String secondAttachedAtom;
    private String thirdAttachedAtom;
    private String fourthAttachedAtom;
    private String fifthAttachedAtom;
    private String sixthAttachedAtom;
    private int centerAtomCoefficient;
    private int firstAtomCoefficient;
    private int secondAtomCoefficient;
    private int thirdAtomCoefficient;
    private int fourthAtomCoefficient;
    private int fifthAtomCoefficient;
    private int sixthAtomCoefficient;
    //coefficients were made to be of type int to add clarity to constructors

    //*************************************Constructors*************************************

    public ChemFormula(String centerAtom, String firstAttachedAtom, int firstAtomCoefficient, String secondAttachedAtom, int secondAtomCoefficient,
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
    }
    public ChemFormula(String centerAtom, String firstAttachedAtom, int firstAtomCoefficient, String secondAttachedAtom, int secondAtomCoefficient,
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
    }
    public ChemFormula(String centerAtom, String firstAttachedAtom, int firstAtomCoefficient, String secondAttachedAtom, int secondAtomCoefficient,
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
    }
    public ChemFormula(String centerAtom, String firstAttachedAtom, int firstAtomCoefficient, String secondAttachedAtom, int secondAtomCoefficient, String thirdAttachedAtom, int thirdAtomCoefficient){
        this.centerAtom = centerAtom;
        this.firstAttachedAtom = firstAttachedAtom;
        this.firstAtomCoefficient = firstAtomCoefficient;
        this.secondAttachedAtom = secondAttachedAtom;
        this.secondAtomCoefficient = secondAtomCoefficient;
        this.thirdAttachedAtom = thirdAttachedAtom;
        this.thirdAtomCoefficient = thirdAtomCoefficient;
    }
    public ChemFormula(String centerAtom, String firstAttachedAtom, int firstAtomCoefficient, String secondAttachedAtom, int secondAtomCoefficient){
        this.centerAtom = centerAtom;
        this.firstAttachedAtom = firstAttachedAtom;
        this.firstAtomCoefficient = firstAtomCoefficient;
        this.secondAttachedAtom = secondAttachedAtom;
        this.secondAtomCoefficient = secondAtomCoefficient;
    }
    public ChemFormula(String centerAtom, String firstAttachedAtom, int firstAtomCoefficient){
        this.centerAtom = centerAtom;
        this.firstAttachedAtom = firstAttachedAtom;
        this.firstAtomCoefficient = firstAtomCoefficient;
    }
    public ChemFormula(String centerAtom, int centerAtomCoefficient){
        this.centerAtom = centerAtom;
        this.centerAtomCoefficient = centerAtomCoefficient;
    }
    //*************************************Getters*************************************

    public String getCenterAtom() {
        return centerAtom;
    }
    public String getFirstAttachedAtom() {
        return firstAttachedAtom;
    }
    public String getSecondAttachedAtom() {
        return secondAttachedAtom;
    }
    public String getThirdAttachedAtom() {
        return thirdAttachedAtom;
    }
    public String getFourthAttachedAtom() {
        return fourthAttachedAtom;
    }
    public String getFifthAttachedAtom() {
        return fifthAttachedAtom;
    }
    public String getSixthAttachedAtom() {
        return sixthAttachedAtom;
    }
    public int getCenterAtomCoefficient() {
        return centerAtomCoefficient;
    }
    public int getFirstAtomCoefficient() {
        return firstAtomCoefficient;
    }
    public int getSecondAtomCoefficient() {
        return secondAtomCoefficient;
    }
    public int getThirdAtomCoefficient() {
        return thirdAtomCoefficient;
    }
    public int getFourthAtomCoefficient() {
        return fourthAtomCoefficient;
    }
    public int getFifthAtomCoefficient() {
        return fifthAtomCoefficient;
    }
    public int getSixthAtomCoefficient() {
        return sixthAtomCoefficient;
    }
}
