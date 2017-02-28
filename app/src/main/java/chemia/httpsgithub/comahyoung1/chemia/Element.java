package chemia.httpsgithub.comahyoung1.chemia;

/**
 * Created by Aaron on 2/19/2017.
 */

public class Element {
    private String name;
    private int numOfElectrons;
    private String chemSymbol;
    private boolean canHaveSimpleOctet;
    private boolean canHaveExpandedOctet;
    //"canHaveSimpleOctet" in this context means that the element (only Boron in this context) is "happy" with 6 valence electrons instead of 8
    //"canHaveExpandedOctet" in this context means that

    public Element(String name, int numOfValenceElectrons, String chemSymbol, boolean canHaveSimpleOctet, boolean canHaveExpandedOctet){
        this.name = name;
        this.numOfElectrons = numOfValenceElectrons;
        this.chemSymbol = chemSymbol;
        this.canHaveSimpleOctet = canHaveSimpleOctet;
        this.canHaveExpandedOctet = canHaveExpandedOctet;
    }

    public String getName(){
        return this.name;
    }

    public String getChemSymbol(){
        return this.chemSymbol;
    }

    public int getNumOfValenceElectrons(){
        return this.numOfElectrons;
    }

    public boolean getCanHaveSimpleOctet(){
        return this.canHaveSimpleOctet;
    }

    public boolean getCanHaveExpandedOctet(){
        return this.canHaveExpandedOctet;
    }
}
