package Exercise1;

import pmp.pipes.SimplePipe;

import java.util.ArrayList;

/**
 * Created by Christoph on 23.10.2017.
 */
public class main {
    public static void main(String args[]) {

        FileSaveSink fileSaveSink = new FileSaveSink();

        SimplePipe<ArrayList<String>> filePipe = new SimplePipe<>(fileSaveSink);

        SortListFilter sortListFilter = new SortListFilter(filePipe);

        SimplePipe<ArrayList<String>> pipeSortList = new SimplePipe<>(null, sortListFilter);

        ShiftWordList shiftWordList = new ShiftWordList(pipeSortList);

        SimplePipe<ArrayList<String>> pipeLineList = new SimplePipe<>(null, shiftWordList);

        CreateWordList createWordList = new CreateWordList(pipeLineList);

        SimplePipe<String> pipeLineString = new SimplePipe<String>(createWordList);

        SrcFilterFileLoad srcFilterFileLoad = new SrcFilterFileLoad(pipeLineString);

        srcFilterFileLoad.run();

    }
}
