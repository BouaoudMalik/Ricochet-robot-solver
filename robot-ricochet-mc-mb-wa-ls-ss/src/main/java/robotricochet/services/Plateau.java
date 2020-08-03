
package robotricochet.services;

import robotricochet.config.PropertiesSingleton;
import robotricochet.entity.Case;
import robotricochet.entity.CaseType;
import robotricochet.entity.Position;
import robotricochet.utils.PlateauUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Logger;

/**
 * allowing us the full construction of the grid game
 */
public class Plateau {
    private static Logger logger = Logger.getAnonymousLogger();
    private static final String SUB_PLATEAU = "subPlateau/";
    private static final boolean DEBUG = true; // debug value to test program
    public static final String FILE_PLATEAU_1 = "file.plateau1";
    public static final String FILE_PLATEAU_2 = "file.plateau2";
    public static final String FILE_PLATEAU_3 = "file.plateau3";
    public static final String FILE_PLATEAU_4 = "file.plateau4";
    private Properties propertiesConfig = PropertiesSingleton.getInstance();
    private Case[][] grid;

    /**
     *constructor of the class
     * @throws IOException thrown by the method constructPlateau
     */
    public Plateau() throws IOException {
        this.grid = this.constructPlateau();
    }

    /**
     * searchPositionOf
     * @param caseType the type of the case researched
     * @return the position of the caseType given
     */
    public Position searchPositionOf(CaseType caseType) {
        for (int i = 0; i < grid[0].length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j].getCaseType() == caseType) {
                    return new Position(i, j);
                }
            }
        }
        return null;
    }

    /**
     * getter of the grid
     * @return the grid game
     */
    public Case[][] getPlateau() {
        return grid;
    }

    /**
     * printPltaeau print the grid
     * @param plateau game grid
     */
    public void printPlateau(Case[][] plateau) {
        int i = 0;
        while (i < plateau.length) {
            for (int j = 0; j < plateau.length; j++) {
                System.out.print(plateau[i][j]);
            }
            System.out.println("");
            i++;
        }
    }

    /**
     * printPlateau
     * print the grid without any parametre
     */
    public void printPlateau() {
        printPlateau(this.grid);
    }

    /**
     *  take a path file name and convert all the characters to an element of SaseType enum
     * @param fileName the path of the subPlateau
     * @return a 2D array with a shape of (9,9)
     * @throws IOException thrown while reading the file
     */
    private static Case[][] readFileSubPlateau(String fileName) throws IOException {
        Case[][] subPlateau = new Case[9][9];

        InputStream inputStream = Plateau.class.getResourceAsStream("/" + fileName);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferReader = new BufferedReader(inputStreamReader);

        String line;
        int i = 0;
        while ((line = bufferReader.readLine()) != null) {
            String[] charactersInLine = line.split("(?!^)");

            for (int j = 0; j < charactersInLine.length; j++) {
                PlateauUtil.parsePlaneCharacters(subPlateau, i, charactersInLine, j);
            }
            i++;
        }
        inputStreamReader.close();
        return subPlateau;
    }

    /**
     * calls all the methods of the class to create the game plateau
     * @return the grid game
     * @throws IOException can be thrown while reading the file
     */
    public Case[][] constructPlateau() throws IOException {
        Random rand = new Random();
        int randomNumber;
        ArrayList<String> subPlateauFiles = new ArrayList<>(Arrays.asList(
                propertiesConfig.getProperty(FILE_PLATEAU_1),
                propertiesConfig.getProperty(FILE_PLATEAU_2),
                propertiesConfig.getProperty(FILE_PLATEAU_3),
                propertiesConfig.getProperty(FILE_PLATEAU_4)));

        Case[][] subPlateauTopRight = new Case[9][9];
        Case[][] subPlateauBottomLeft = new Case[9][9];
        Case[][] subPlateauBottomRight = new Case[9][9];

        randomNumber = rand.nextInt(subPlateauFiles.size());
        Case[][] subPlateauTopLeft = readFileSubPlateau(SUB_PLATEAU + subPlateauFiles.remove(randomNumber));

        randomNumber = rand.nextInt(subPlateauFiles.size());
        Case[][] subPlateauTopRightTemp = readFileSubPlateau(SUB_PLATEAU + subPlateauFiles.remove(randomNumber));


        randomNumber = rand.nextInt(subPlateauFiles.size());
        Case[][] subPlateauBottomLeftTemp = readFileSubPlateau(SUB_PLATEAU + subPlateauFiles.remove(randomNumber));


        randomNumber = rand.nextInt(subPlateauFiles.size());
        Case[][] subPlateauBottomRightTemp = readFileSubPlateau(SUB_PLATEAU + subPlateauFiles.remove(randomNumber));

        finalSubPlateauRotated(subPlateauTopRight, subPlateauBottomLeft, subPlateauBottomRight, subPlateauTopRightTemp, subPlateauBottomLeftTemp, subPlateauBottomRightTemp);
        return concatenateSubPlateau(subPlateauTopLeft, subPlateauTopRight, subPlateauBottomLeft, subPlateauBottomRight);
    }

    /**
     * do the rotation of the subPlateau(subGrids),rotate the ricochet of the top right sub grid,and bottom left one
     * @param subPlateauTopRight
     * @param subPlateauBottomLeft
     * @param subPlateauBottomRight
     * @param subPlateauTopRightTemp temporary subPlateau
     * @param subPlateauBottomLeftTemp temporary subPlateau
     * @param subPlateauBottomRightTemp temporary subPlateau
     */
    private void finalSubPlateauRotated(Case[][] subPlateauTopRight, Case[][] subPlateauBottomLeft, Case[][] subPlateauBottomRight, Case[][] subPlateauTopRightTemp, Case[][] subPlateauBottomLeftTemp, Case[][] subPlateauBottomRightTemp) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                subPlateauTopRight[i][j] = subPlateauTopRightTemp[i][(8 - j)];
                subPlateauBottomLeft[i][j] = subPlateauBottomLeftTemp[8 - i][j];
                subPlateauBottomRight[i][j] = subPlateauBottomRightTemp[8 - i][8 - j];
                PlateauUtil.subPlateauRotation(subPlateauTopRight, i, j);
                PlateauUtil.subPlateauRotation(subPlateauBottomLeft, i, j);
            }
        }
    }

    /**
     * concatenate all the 4 subGrids to form one
     * @param subPlateauTopLeft
     * @param subPlateauTopRight
     * @param subPlateauBottomLeft
     * @param subPlateauBottomRight
     * @return the final form of the grid game
     */
    private Case[][] concatenateSubPlateau(Case[][] subPlateauTopLeft, Case[][] subPlateauTopRight, Case[][] subPlateauBottomLeft, Case[][] subPlateauBottomRight) {
        Case[][] concatenationOfAllSubPlateau = new Case[18][18];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                concatenationOfAllSubPlateau[i][j] = subPlateauTopLeft[i][j];
                concatenationOfAllSubPlateau[i][j + 9] = subPlateauTopRight[i][j];
                concatenationOfAllSubPlateau[i + 9][j] = subPlateauBottomLeft[i][j];
                concatenationOfAllSubPlateau[i + 9][j + 9] = subPlateauBottomRight[i][j];
            }
        }

        if (DEBUG) {
            logger.info("Plateau final : ");
            this.printPlateau(concatenationOfAllSubPlateau);
        }
        return concatenationOfAllSubPlateau;
    }

}
