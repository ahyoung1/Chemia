package chemia.httpsgithub.comahyoung1.chemia;

import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Created by Aaron on 4/25/2017.
 */

public class MoleculeUnitTest {
    private Element dummyFillerElement = new Element("", -1, -1, "", false, false);
    private Element hydrogen = new Element("Hydrogen", 1, 1, "H", false, false);
    private Element carbon = new Element("Carbon", 6, 4, "C", false, false);
    //input that molecule expects
    private Element[] elementArray = new Element[6];
    private String[] subscriptArray = new String[6];

    @Test
    public void setHydrogenDiatomic() throws Exception {
        elementArray[0] = dummyFillerElement;
        elementArray[1] = dummyFillerElement;
        elementArray[2] = dummyFillerElement;
        elementArray[3] = dummyFillerElement;
        elementArray[4] = dummyFillerElement;
        elementArray[5] = dummyFillerElement;
        subscriptArray[0] = "";
        subscriptArray[1] = "";
        subscriptArray[2] = "";
        subscriptArray[3] = "";
        subscriptArray[4] = "";
        subscriptArray[5] = "";
        Molecule molecule = new Molecule(hydrogen, "2", elementArray, subscriptArray);
        assertEquals(molecule.getCenterAtom(), hydrogen);
        assertEquals(molecule.getCenterAtomSubscript(), "2");
        assertEquals(molecule.getAttachedElementArray(), elementArray);
        assertEquals(molecule.getAttachedAtomSubscriptArray(), subscriptArray);
        assertEquals(molecule.getNumberOfTotalValence(), 2);
        assertEquals(molecule.getNumberOfAttached(), 1);
    }


    @Test
    public void setMethane() throws Exception {
        elementArray[0] = hydrogen;
        elementArray[1] = hydrogen;
        elementArray[2] = hydrogen;
        elementArray[3] = hydrogen;
        elementArray[4] = dummyFillerElement;
        elementArray[5] = dummyFillerElement;
        subscriptArray[0] = "";
        subscriptArray[1] = "";
        subscriptArray[2] = "";
        subscriptArray[3] = "";
        subscriptArray[4] = "";
        subscriptArray[5] = "";
        Molecule molecule = new Molecule(carbon, "", elementArray, subscriptArray);
        assertEquals(molecule.getCenterAtom(), carbon);
        assertEquals(molecule.getCenterAtomSubscript(), "");
        assertEquals(molecule.getAttachedElementArray(), elementArray);
        assertEquals(molecule.getAttachedAtomSubscriptArray(), subscriptArray);
        assertEquals(molecule.getNumberOfTotalValence(), 8);
        assertEquals(molecule.getNumberOfAttached(), 4);
    }
}
