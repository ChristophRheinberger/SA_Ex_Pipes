package Exercise1A;

import pmp.filter.DataCompositionFilter;
import pmp.interfaces.Writeable;

import java.io.IOException;
import java.nio.CharBuffer;
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

    @Override
    public int read(CharBuffer cb) throws IOException {
        return 0;
    }
}
