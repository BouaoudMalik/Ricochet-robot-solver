package robotricochet.main;

import robotricochet.services.GameBuilder;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

/**
 * Main
 */
public class Main {


    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {


        System.out.println("----------------PLAY:-----------------");

        GameBuilder gameBuilder = new GameBuilder();
        gameBuilder.play();


    }

}
