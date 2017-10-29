package ExerciseB;

import pmp.filter.DataCompositionFilter;
import pmp.interfaces.Writeable;

import java.security.InvalidParameterException;

/**
 * Created by ClemensB on 28.10.17.
 */
public class LineFilter extends DataCompositionFilter<Word, Line> {
    private int length = 100;       // Länge einer Linie

    public LineFilter(Writeable<Line> output) throws InvalidParameterException {
        super(output);
    }

    @Override
    protected boolean fillEntity(Word nextVal, Line entity) {

        System.out.println(nextVal);

        if (nextVal != null && length >= (nextVal.length() + entity.length())) {
            entity.addWord(nextVal);
            System.out.println(entity);
            return false;
        }


        return true;
    }

    @Override
    protected Line getNewEntityObject() {
        return new Line();
    }

    @Override
    protected void beforeSendingData() {

    }
}
