package ExerciseB;

import pmp.interfaces.IOable;
import pmp.interfaces.Readable;
import pmp.interfaces.Writeable;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;

/**
 * Created by ClemensB on 28.10.17.
 */
public class SplitPipe<T> implements IOable<T, T> {

    private Readable<T> m_Input = null;
    private Writeable<T> m_Output = null;
    private Writeable<T> m_Output2 = null;

    public SplitPipe(Readable<T> input)   {
        if (input == null){
            throw new InvalidParameterException("input filter can't be null!");
        }
        m_Input = input;
    }

    public SplitPipe(Writeable<T> output, Writeable<T> output2)   {
        if (output == null || output2 == null){
            throw new InvalidParameterException("output filter can't be null!");
        }
        m_Output = output;
        m_Output2 = output2;
    }

    public SplitPipe(Readable<T> input, Writeable<T> output, Writeable<T> output2)   {
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

    public T read() throws StreamCorruptedException, FileNotFoundException {
        if ( m_Input == null )
            throw new InvalidParameterException("input filter can't be null!");

        return m_Input.read();
    }

    public void write(T input) throws IOException {
        if ( m_Output == null || m_Output2 == null )
            throw new InvalidParameterException("output filter can't be null!");

        m_Output.write(input);
        //m_Output2.write(input.copy());  // es muss eine kopie weiter geschickt werden, da ansonsten mit dem gleichen objekt in zwei klassen gearbeitet wird
    }

}
