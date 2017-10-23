package Exercise1;

import pmp.filter.Source;
import pmp.interfaces.Writeable;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Christoph on 23.10.2017.
 */
public class SrcFilterFileLoad extends Source<String> {
    public SrcFilterFileLoad(Writeable<String> output) {
        super(output);
    }

    @Override
    public String read() {

        FileReader fre = null;
        try {
            fre = new FileReader("aliceInWonderland.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        BufferedReader bre = new BufferedReader(fre);


        try {
            while(!bre.readLine().isEmpty()) {
                String line = "";
                m_Output.write(bre.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
