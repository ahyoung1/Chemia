package chemia.httpsgithub.comahyoung1.chemia;

/**
 * Created by Aaron on 2/19/2017.
 */

public class Molecule {
    String formula;
    //maybe find a way to name? Naming is easy for these, right?
    String name;


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
                buildDiatomicGas(centerAtom);
                break;
            //
            case 4:
                break;
            //
            case 5:
                break;
            //
            case 6:
                break;
            //
            case 7:
                if(numOfAttachedAtoms==1){
                    if(attachedAtoms[0].equals(centerAtom)){
                        buildDiatomicGas(centerAtom);
                    }
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

    private void buildDiatomicGas(Element centerAtom){
        this.name = centerAtom.getName()+" Gas";
    }
}
