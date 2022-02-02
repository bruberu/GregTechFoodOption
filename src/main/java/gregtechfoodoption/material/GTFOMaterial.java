package gregtechfoodoption.material;

import gregtech.api.util.SmallDigits;

public class GTFOMaterial {
    public String name;
    public int rgb;
    protected String chemicalFormula;

    protected String calculateChemicalFormula(String unformattedFormula) {
        StringBuilder sb = new StringBuilder();
        if (unformattedFormula != null && !unformattedFormula.isEmpty()) {
            for (char c : unformattedFormula.toCharArray()) {
                if (Character.isDigit(c))
                    sb.append(SmallDigits.toSmallDownNumbers(Character.toString(c)));
                else
                    sb.append(c);
            }
        }
        return sb.toString(); // returns "" if no formula, like other method
    }

    public String getFormula() {
        return chemicalFormula;
    }
}
