package Exercise1;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * Created by ClemensB on 24.10.17.
 */
public class BuffReader{
    private static BufferedReader singleton;

    /* A private Constructor prevents any other
     * class from instantiating.
     */
    private BuffReader() {
        try {
            singleton = new BufferedReader(new FileReader("aliceInWonderland.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /* Static 'instance' method */
    public static BufferedReader getInstance( ) {
        return singleton;
    }

    /* Other methods protected by singleton-ness */
    protected static void demoMethod( ) {
        System.out.println("demoMethod for singleton");
    }

}
