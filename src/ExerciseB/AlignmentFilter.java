package ExerciseB;

import pmp.filter.DataTransformationFilter1;
import pmp.interfaces.Writeable;

import java.security.InvalidParameterException;

/**
 * Created by ClemensB on 28.10.17.
 */
public class AlignmentFilter extends DataTransformationFilter1<Line> {
    private int length = 100;       // Länge einer Linie
    private Enum<Alignment> alignment = Alignment.left;

    public AlignmentFilter(Writeable<Line> output) throws InvalidParameterException {
        super(output);
    }

    @Override
    protected void process(Line entity) {

        if ( entity.toString() != null) {
            StringBuilder builder = new StringBuilder(entity.toString());
            int amount = length - builder.length();

            if (alignment == Alignment.right) {
                for (int i = 0; i < amount; i++) {
                    builder.insert(0, " ");
                }

                entity.setLine(builder.toString());
            } else if (alignment == Alignment.centered) {
                int front = amount / 2;
                for (int i = 0; i < front; i++) {
                    builder.insert(0, " ");
                }
                for (int i = 0; i < (amount - front); i++) {
                    builder.append(" ");
                }

                entity.setLine(builder.toString());
            } else if (alignment == Alignment.left) {
                for (int i = 0; i < amount; i++) {
                    builder.append(" ");
                }

                System.out.println(entity);
                entity.setLine(builder.toString());
            }
        }
    }
}
