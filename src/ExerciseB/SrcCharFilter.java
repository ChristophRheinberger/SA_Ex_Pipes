package ExerciseB;

import pmp.filter.Source;
import pmp.interfaces.Writeable;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamCorruptedException;

/**
 * Created by ClemensB on 28.10.17.
 */
public class SrcCharFilter extends Source<Character> {

    private FileReader fr;

    private StringBuilder sb = new StringBuilder();

    public SrcCharFilter(Writeable<Character> output) {
        super(output);
    }

    private FileReader getFr() throws FileNotFoundException {
        if(fr == null){
            fr = new FileReader("aliceInWonderland.txt");
        }
        return fr;
    }

    @Override
    public Character read() throws StreamCorruptedException, FileNotFoundException {
        int r;
        fr = getFr();       // holen des FileReaders
        try {
            while((r = fr.read()) != -1){       //-1 ist das Ende vom File
                return((char) r);           // jeder Char wird weiter geleitet
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
