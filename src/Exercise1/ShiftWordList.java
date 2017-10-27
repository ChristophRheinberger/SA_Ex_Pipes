package Exercise1;

import pmp.filter.DataTransformationFilter2;
import pmp.interfaces.Writeable;

import java.io.FileNotFoundException;
import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Christoph on 23.10.2017.
 */
public class ShiftWordList extends DataTransformationFilter2<ArrayList<String>, ArrayList<String>> {

    protected List<String> _ignoredWords = Arrays.asList("the", "of", "and", "to", "a", "in", "is", "you", "are", "for", "that", "or", "it", "her", "his",
                                                                "as", "be", "on", "your", "with", "can", "have", "this", "an", "by", "not", "but", "at", "from",
                                                                "I", "they", "more", "will", "if", "some", "there", "what", "about", "which", "when", "one", "their",
                                                                "all", "also", "how", "many", "do", "has", "most", "other", "so", "was", "we", "these", "like", "use",
                                                                "into", "than", "up", "out", "who", "them", "make", "because", "such", "through", "get", "work", "even",
                                                                "different", "its", "no", "our", "new", "just", "only", "see", "used", "good", "been", "need", "should",
                                                                "very", "any", "often", "well", "were", "then", "my", "would", "over", "where", "much", "while", "he", "look", "*", " ");


    public ShiftWordList(Writeable output) throws InvalidParameterException {
        super(output);
    }

    @Override
    protected ArrayList<String> process(ArrayList<String> entity) throws FileNotFoundException, StreamCorruptedException {

        ArrayList<String> inputList = entity;
        ArrayList<String> outputList = new ArrayList<>();
        String arrayElement;

        if (inputList.size() == 0) {
            return inputList;
        }

        String element = null;

        for (int i = 1; i <= inputList.size()-1; i++) {

            arrayElement = String.join(" ", inputList);
            arrayElement = arrayElement.replaceAll("[-+.^:,]","");

            if (!_ignoredWords.contains(inputList.get(0).toLowerCase())) {
                outputList.add(arrayElement);
            }

            element = inputList.remove(0);
            inputList.add(inputList.size()-1, element);
        }

        return outputList;
    }
}