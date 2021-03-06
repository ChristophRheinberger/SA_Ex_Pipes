package Exercise1B;

import Exercise1A.CreateWordList;
import Exercise1A.FileSaveSink;
import Exercise1A.ShiftWordList;
import Exercise1A.SortListFilter;
import pmp.interfaces.Writeable;
import pmp.pipes.SimplePipe;

import java.util.ArrayList;

/**
 * Created by Christoph on 29.10.2017.
 */
public class main {

    public static void main(String args[]) {

        FileSaveSink IndexSaveSink = new FileSaveSink();

        SimplePipe<ArrayList<String>> filePipe = new SimplePipe<>((Writeable<ArrayList<String>>) IndexSaveSink);

        SortListFilter sortListFilter = new SortListFilter(filePipe);

        SimplePipe<ArrayList<String>> pipeSortList = new SimplePipe<>((Writeable) sortListFilter);

        ShiftWordList shiftWordList = new ShiftWordList(pipeSortList);

        SimplePipe<ArrayList<String>> pipeLineList = new SimplePipe<>((Writeable<ArrayList<String>>) shiftWordList);

        CreateWordList createWordList = new CreateWordList(pipeLineList);



        AlignedFileSaveSink alignmentSaveSink = new AlignedFileSaveSink();

        SplitPipe splitPipe = new SplitPipe(createWordList, alignmentSaveSink);

        AlignmentFilter alignmentFilter = new AlignmentFilter(splitPipe, Integer.parseInt(args[1]), args[2]);

        SimplePipe<Line> pipeAlignmentFilter = new SimplePipe<>((Writeable<Line>) alignmentFilter);

        LineFilter lineFilter = new LineFilter(pipeAlignmentFilter, Integer.parseInt(args[1]));

        SimplePipe<Word> lineSimplePipe = new SimplePipe<Word>(lineFilter);

        ConstructWordsFilter constructWordsFilter = new ConstructWordsFilter(lineSimplePipe);

        SimplePipe<Character> characterSimplePipe = new SimplePipe<Character>(constructWordsFilter);

        SrcCharFilter srcCharFilter = new SrcCharFilter(characterSimplePipe, args[0]);

        srcCharFilter.run();

    }
}
