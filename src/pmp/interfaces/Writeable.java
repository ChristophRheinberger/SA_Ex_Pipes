package pmp.interfaces;

import java.io.IOException;
import java.io.StreamCorruptedException;

public interface Writeable<T> {
	public void write(T value) throws IOException;
}
