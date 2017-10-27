package Exercise1;

import pmp.filter.DataCompositionFilter;
import pmp.interfaces.Writeable;

import java.security.InvalidParameterException;
import java.util.ArrayList;

/**
 * Created by Christoph on 23.10.2017.
 */
public class SortListFilter extends DataCompositionFilter<ArrayList<String>, ArrayList<String>> {

    public SortListFilter(Writeable output) throws InvalidParameterException {
        super(output);
    }

    @Override
    protected boolean fillEntity(ArrayList<String> nextVal, ArrayList<String> entity) {
        boolean inserted = false;

        int i = 0;

        if (nextVal != null) {
            for (int j = 0; j < nextVal.size(); j++) {
                do {
                    if (entity.isEmpty()) {
                        entity.add(nextVal.get(j));
                        inserted = true;
                    } else if (nextVal.get(j).compareTo(entity.get(0)) < 0) {
                        entity.add(0, nextVal.get(j));
                        inserted = true;
                    } else if (nextVal.get(j).compareTo(entity.get(i)) > 0) {
                        if (entity.size() < i) {
                            entity.add(nextVal.get(j));
                        } else {
                            entity.add(i, nextVal.get(j));
                        }
                        inserted = true;
                    } else {
                        i++;
                    }
                } while (inserted != true);
                i = 0;
                inserted = false;
            }
            return false;
        } else {
            return true;
        }

    }

    @Override
    protected ArrayList<String> getNewEntityObject() {
        return new ArrayList<String>();
    }
}
