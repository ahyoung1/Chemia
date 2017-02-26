package chemia.httpsgithub.comahyoung1.chemia;

/**
 * Created by Aaron on 2/19/2017.
 */

public class Molecule {
    String formula;
    //maybe find a way to name? Naming is easy for these, right?
    String name;

    public Molecule(Element centerAtom, Element firstAttachedAtom, Element secAttachedAtom){
        if(centerAtom.getCanHaveSimpleOctet()){

        }
        else if(centerAtom.getCanHaveExpandedOctet()){

        }
        else{
            //someFunctionThatINeed();
        }
    }

    private void someFunctionThatINeed(Element centerAtom){
        int centerAtomValence = centerAtom.getNumOfValenceElectrons();
        switch (centerAtomValence){
            case 1:
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
        }
    }
}
