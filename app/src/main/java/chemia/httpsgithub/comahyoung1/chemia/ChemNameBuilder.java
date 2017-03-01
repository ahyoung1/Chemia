package chemia.httpsgithub.comahyoung1.chemia;

/**
 * Created by Aaron on 2/28/2017.
 */

public class ChemNameBuilder {
    private String name;

    public ChemNameBuilder(Element centerAtom, Element[] attachedAtoms){
        this.name = "";
    }
    public String getName(){
        return this.name;
    }
}
