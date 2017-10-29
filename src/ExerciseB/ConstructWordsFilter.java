package ExerciseB;

import pmp.filter.DataCompositionFilter;
import pmp.interfaces.Writeable;

import java.security.InvalidParameterException;

/**
 * Created by ClemensB on 28.10.17.
 */
public class ConstructWordsFilter extends DataCompositionFilter<Character, Word> {

    public ConstructWordsFilter(Writeable<Word> output) throws InvalidParameterException {
        super(output);
    }

    @Override
    protected boolean fillEntity(Character nextVal, Word entity) {

        if (nextVal != null) {
            if (!nextVal.equals(' ')) {    //null = Ende vom Text und ' ' signalisiert das Ende eines Wortes
                entity.addChar(nextVal);
            }
            return false;
        }

        return true;    // zum senden des Wortes
    }

    @Override
    protected Word getNewEntityObject() {
        return new Word();
    }

    @Override
    protected void beforeSendingData() {

    }
}
