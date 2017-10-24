package Exercise1;

import pmp.filter.ForwardingFilter;
import pmp.interfaces.Writeable;

import java.security.InvalidParameterException;
import java.util.ArrayList;

/**
 * Created by Christoph on 23.10.2017.
 */
public class SortListFilter extends ForwardingFilter<ArrayList<String>> {

    protected ArrayList<String> sortedIndex = new ArrayList<>();

    public SortListFilter(Writeable output) throws InvalidParameterException {
        super(output);
    }

    @Override
    protected boolean forward(ArrayList<String> entity) {
        boolean inserted = false;
        int i = 0;

        System.out.println(entity.toString());

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

        return false;
    }
}
