package Exercise1;

import pmp.filter.DataTransformationFilter1;
import pmp.interfaces.Writeable;

import java.security.InvalidParameterException;
import java.util.ArrayList;

/**
 * Created by Christoph on 23.10.2017.
 */
public class SortListFilter extends DataTransformationFilter1<ArrayList<String>> {

    protected ArrayList<String> sortedIndex = new ArrayList<>();

    public SortListFilter(Writeable output) throws InvalidParameterException {
        super(output);
    }

    @Override
    protected void process(ArrayList<String> entity) {

        boolean inserted = false;
        int i = 0;

        for(int j = 0; j < entity.size(); j++) {
            do {
                if (entity.get(j).compareTo(sortedIndex.get(i)) > 0) {
                    sortedIndex.add(i, entity.get(j));
                    inserted = true;
                }
                i++;
            } while (inserted != true);
            i = 0;
        }
    }
}
