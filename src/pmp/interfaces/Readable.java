package pmp.interfaces;

import java.io.FileNotFoundException;
import java.io.StreamCorruptedException;

public interface Readable<T> extends java.lang.Readable {
	public T read() throws StreamCorruptedException, FileNotFoundException;
}
