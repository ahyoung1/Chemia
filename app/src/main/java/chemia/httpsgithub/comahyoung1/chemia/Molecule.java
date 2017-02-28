package chemia.httpsgithub.comahyoung1.chemia;

/**
 * Created by Aaron on 2/19/2017.
 */

public class Molecule {
    //Chemical formulas are going to be 2D string arrays
    //formula[0][n] will be chem symbol
    //formula[1][n] will be coefficient - empty string if none
    ChemFormulaBuilder formula;
    //maybe find a way to name? Naming is easy for these, right?
    String name;
    Bond[] bondArray = new Bond[8];
    //so this is going to work in such a way that a bond is created, then added tot he array

    public Molecule(Element centerAtom, Element[] attachedAtoms){
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

}
