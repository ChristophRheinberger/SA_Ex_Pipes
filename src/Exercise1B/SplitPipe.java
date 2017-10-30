package Exercise1B;

import pmp.interfaces.IOable;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.nio.CharBuffer;
import java.security.InvalidParameterException;

/**
 * Created by ClemensB on 28.10.17.
 */
public class SplitPipe implements IOable<Line, Line> {

    private Readable<Line> m_Input = null;
    private Writeable<Line> m_Output = null;
    private Writeable<Line> m_Output2 = null;

    public SplitPipe(Readable<Line> input)   {
        if (input == null){
            throw new InvalidParameterException("input filter can't be null!");
        }
        m_Input = input;
    }

    public SplitPipe(Writeable<Line> output, Writeable<Line> output2)   {
        if (output == null || output2 == null){
            throw new InvalidParameterException("output filter can't be null!");
        }
        m_Output = output;
        m_Output2 = output2;
    }

    public SplitPipe(Readable<Line> input, Writeable<Line> output, Writeable<Line> output2)   {
        if (output == null || output2 == null){
            throw new InvalidParameterException("output filter can't be null!");
        }
        m_Output = output;
        m_Output2 = output2;

        if (input == null){
            throw new InvalidParameterException("input filter can't be null!");
        }
        m_Input = input;
    }

    public Line read() throws StreamCorruptedException, FileNotFoundException {
        if ( m_Input == null )
            throw new InvalidParameterException("input filter can't be null!");

        return m_Input.read();
    }

    public void write(Line input) throws IOException {
        if ( m_Output == null || m_Output2 == null )
            throw new InvalidParameterException("output filter can't be null!");

        m_Output.write(input);

        if(input != null) {
            m_Output2.write(input.copy());
        } else {
            m_Output2.write(input);
        }
    }

    @Override
    public int read(CharBuffer cb) throws IOException {
        return 0;
    }
}
