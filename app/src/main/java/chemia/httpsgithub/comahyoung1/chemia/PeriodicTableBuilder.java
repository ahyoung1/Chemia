package chemia.httpsgithub.comahyoung1.chemia;

/**
 * Created by Aaron on 2/19/2017.
 */

public class PeriodicTableBuilder {
    private Element[] listOfElements = new Element[18];
    private String[] listOfDiatomicSymbols = new String[7];

    public PeriodicTableBuilder(){
        listOfElements[0] = new Element("Hydrogen", 1, 1, "H", false, false);
        listOfElements[1] = new Element("Boron", 5, 3, "B", true, true);
        listOfElements[2] = new Element("Carbon", 6, 4, "C", false, false);
        listOfElements[3] = new Element("Nitrogen", 7, 5, "N", false, false);
        listOfElements[4] = new Element("Oxygen", 8, 6, "O", false, false);
        listOfElements[5] = new Element("Fluorine", 9, 7, "F", false, false);
        listOfElements[6] = new Element("Silicon", 14, 4, "Si", false, false);
        listOfElements[7] = new Element("Phosphorus", 15, 5, "P", false, true);
        listOfElements[8] = new Element("Sulfur", 16, 6, "S", false, true);
        listOfElements[9] = new Element("Chlorine", 17, 7, "Cl", false, true);
        listOfElements[10] = new Element("Arsenic", 33, 5, "As", false, true);
        listOfElements[11] = new Element("Selenium", 34, 6, "Se", false, true);
        listOfElements[12] = new Element("Bromine", 35, 7, "Br", false, true);
        listOfElements[13] = new Element("Krypton", 36, 8, "Kr", false, true);
        listOfElements[14] = new Element("Tellurium", 52, 6, "Te", false, true);
        listOfElements[15] = new Element("Iodine", 53, 7, "I", false, true);
        listOfElements[16] = new Element("Xenon", 54, 8, "Xe", false, true);
        listOfElements[17] = new Element("Astatine", 85, 7, "At", false, true);

        listOfDiatomicSymbols[0] = "H";
        listOfDiatomicSymbols[1] = "N";
        listOfDiatomicSymbols[2] = "O";
        listOfDiatomicSymbols[3] = "F";
        listOfDiatomicSymbols[4] = "Cl";
        listOfDiatomicSymbols[5] = "I";
        listOfDiatomicSymbols[6] = "Br";
    }

    public Element[] getListOfElements(){
        return listOfElements;
    }

    public String[] getListOfElementNames(){
        String[] listOfElementNames = new String[18];
        for(int i=0; i<18; i++){
            listOfElementNames[i] = listOfElements[i].getName();
        }
        return listOfElementNames;
    }

    public Element getElementBySymbol(String elementSymbol){
        for(int i=0; i<18; i++){
            if(listOfElements[i].getChemSymbol().equals(elementSymbol)){
                return listOfElements[i];
            }
        }
        return new Element("Couldn't find Element", -1, -1, "1", false, false);
    }

    public Element getElementByName(String elementName){
        for(int i=0; i<18; i++){
            if(listOfElements[i].getName().equals(elementName)){
                return listOfElements[i];
            }
        }
        return new Element("Couldn't find Element", -1, -1, "1", false, false);
    }

    public String[] getListOfDiatomicSymbols(){return listOfDiatomicSymbols;}
}
