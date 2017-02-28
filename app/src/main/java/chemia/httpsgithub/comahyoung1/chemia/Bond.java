package chemia.httpsgithub.comahyoung1.chemia;

/**
 * Created by Aaron on 2/27/2017.
 */

public class Bond {
    private String bondType;
    private Element centerAtom;
    private Element attachedAtom;

    public Bond(String bondType, Element centerAtom, Element attachedAtom){
        this.bondType = bondType;
        this.centerAtom = centerAtom;
        this.attachedAtom = attachedAtom;
    }

    public String getBondType() {
        return bondType;
    }

    public Element getCenterAtom(){
        return centerAtom;
    }

    public Element getAttachedAtom(){
        return attachedAtom;
    }
}
