package robotricochet.entity;


/**
 *the class Case is used to define the content of a case
 */
public class Case {


    CaseType caseType;

    /**
     * class constructor
     * @param caseType type of case
     */

    public Case(CaseType caseType) {

        this.caseType = caseType;
    }

    /**
     * getter of caseType
     * @return type of case
     */
    public CaseType getCaseType() {

        return this.caseType;
    }

    /**
     * gives the correct character depending of the Type of the Case
     * @return character of case
     */
    public String toString() {
        String string = null;
        switch (this.caseType) {
            case OBSTACLE:
                string = "#";
                break;
            case EMPTYSPACE:
                string = " ";
                break;
            case RED_ROBOT_START:
                string = "r";
                break;
            case GREEN_ROBOT_START:
                string = "g";
                break;
            case BLUE_ROBOT_START:
                string = "b";
                break;
            case YELLOW_ROBOT_START:
                string = "y";
                break;
            case RED_CIRCLE:
                string = "0";
                break;
            case GREEN_CIRCLE:
                string = "1";
                break;
            case BLUE_CIRCLE:
                string = "2";
                break;
            case YELLOW_CIRCLE:
                string = "3";
                break;
            case RED_SQUARE:
                string = "4";
                break;
            case GREEN_SQUARE:
                string = "5";
                break;
            case BLUE_SQUARE:
                string = "6";
                break;
            case YELLOW_SQUARE:
                string = "7";
                break;
            case RED_TRIANGLE:
                string = "8";
                break;
            case GREEN_TRIANGLE:
                string = "9";
                break;
            case BLUE_TRIANGLE:
                string = "A";
                break;
            case YELLOW_TRIANGLE:
                string = "B";
                break;
            case RED_DIAMOND:
                string = "C";
                break;
            case GREEN_DIAMOND:
                string = "D";
                break;
            case BLUE_DIAMOND:
                string = "E";
                break;
            case YELLOW_DIAMOND:
                string = "F";
                break;
            case MULTICOLOR_VORTEX:
                string = "G";
                break;
            case ANTISLASH_RED:
                string = "H";
                break;
            case SLASH_RED:
                string = "I";
                break;
            case ANTISLASH_GREEN:
                string = "J";
                break;
            case SLASH_GREEN:
                string = "K";
                break;
            case ANTISLASH_BLUE:
                string = "L";
                break;
            case SLASH_BLUE:
                string = "M";
                break;
            case ANTISLASH_YELLOW:
                string = "N";
                break;
            case SLASH_YELLOW:
                string = "O";
                break;
            default:
                string = "null";
                break;
        }
        return string;
    }
}
