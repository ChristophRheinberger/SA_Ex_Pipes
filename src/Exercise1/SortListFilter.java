package Exercise1;

import pmp.filter.DataCompositionFilter;
import pmp.interfaces.Writeable;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
                    entity.add(nextVal.get(j));
                    inserted = true;
                    /*
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
                    */
                } while (inserted != true);
                i = 0;
                inserted = false;
            }
        }
        return false;


    }

    @Override
    protected ArrayList<String> getNewEntityObject() {
        return new ArrayList<String>();
    }

    @Override
    protected void beforeSendingData() {
        Collections.sort(m_TempWriteEntity, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return s1.compareToIgnoreCase(s2);
            }
        });
    }
}
