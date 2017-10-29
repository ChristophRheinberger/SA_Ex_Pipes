package ExerciseA;

import ExerciseB.Line;
import pmp.interfaces.Writeable;
import pmp.pipes.SimplePipe;

import java.util.ArrayList;

/**
 * Created by Christoph on 23.10.2017.
 */
public class main {
    public static void main(String args[]) {

        FileSaveSink fileSaveSink = new FileSaveSink("BookIndex.txt");

        SimplePipe<ArrayList<String>> filePipe = new SimplePipe<>((Writeable<ArrayList<String>>) fileSaveSink);

        SortListFilter sortListFilter = new SortListFilter(filePipe);

        SimplePipe<ArrayList<String>> pipeSortList = new SimplePipe<>((Writeable) sortListFilter);

        ShiftWordList shiftWordList = new ShiftWordList(pipeSortList);

        SimplePipe<ArrayList<String>> pipeLineList = new SimplePipe<>((Writeable<ArrayList<String>>) shiftWordList);

        CreateWordList createWordList = new CreateWordList(pipeLineList);

        SimplePipe<Line> pipeLineString = new SimplePipe<Line>(createWordList);

        SrcFilterFileLoad srcFilterFileLoad = new SrcFilterFileLoad(pipeLineString);

        srcFilterFileLoad.run();

    }
}
