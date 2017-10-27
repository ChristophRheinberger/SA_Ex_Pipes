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
public class SrcFilterFileLoad extends Source<String>{
    public BufferedReader br = null;

    public SrcFilterFileLoad(Writeable<String> output) {
        super(output);
    }

    private BufferedReader getBr() throws FileNotFoundException {
        if(br == null){
            br = new BufferedReader(new FileReader("aliceInWonderland-Kopie.txt"));
        }
        return br;
    }

    @Override
    public String read() throws FileNotFoundException {
        String line = null;
        BufferedReader buffReader = getBr();
        try {
            while((line = buffReader.readLine()) != null) {
                while(line.isEmpty() || line.trim().equals("") || line.trim().equals(System.lineSeparator())){
                    line = buffReader.readLine();
                }
                line = line.replaceAll("[-+.$%&!·?/^:,;*\"_\\[\\]\\(\\)\\d#]", " ");
                line = line.replaceAll("\\s\\s", "");
                line = line.trim();
                return line;
            }
            return null;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
