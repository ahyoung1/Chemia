package chemia.httpsgithub.comahyoung1.chemia;
import java.io.Serializable;
import static java.lang.Math.*;

/**
 * Created by Aaron on 2/19/2017.
 */

public class Element implements Serializable{
    private String name;
    private int numOfElectrons;
    private String chemSymbol;
    private boolean canHaveSimpleOctet;
    private boolean canHaveExpandedOctet;
    private int numOfDesiredBonds;
    //"canHaveSimpleOctet" in this context means that the element (only Boron in this context) is "happy" with 6 valence electrons instead of 8
    //"canHaveExpandedOctet" in this context means that _____________

    public Element(String name, int numOfValenceElectrons, String chemSymbol, boolean canHaveSimpleOctet, boolean canHaveExpandedOctet){
        this.name = name;
        this.numOfElectrons = numOfValenceElectrons;
        this.chemSymbol = chemSymbol;
        this.canHaveSimpleOctet = canHaveSimpleOctet;
        this.canHaveExpandedOctet = canHaveExpandedOctet;
        this.numOfDesiredBonds =  (int) ceil((8-numOfValenceElectrons)/2.0);
    }

    public String getName(){
        return this.name;
    }
    public String getChemSymbol() { return this.chemSymbol; }
    public int getNumOfValenceElectrons(){
        return this.numOfElectrons;
    }
    public boolean getCanHaveSimpleOctet(){
        return this.canHaveSimpleOctet;
    }
    public boolean getCanHaveExpandedOctet(){
        return this.canHaveExpandedOctet;
    }
    public int getNumOfDesiredBonds(){return this.numOfDesiredBonds;}
}
