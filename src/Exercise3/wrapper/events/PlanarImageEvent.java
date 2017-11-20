package Exercise3.wrapper.events;

import javax.media.jai.PlanarImage;
import java.util.EventObject;

/**
 * Created by Christoph on 13.11.2017.
 */
public class PlanarImageEvent extends EventObject {

    private PlanarImage _image;

    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @param image
     * @throws IllegalArgumentException if source is null.
     */
    public PlanarImageEvent(Object source, PlanarImage image) {
        super(source);
        this._image = image;
    }

    public PlanarImage getImage() {
        return _image;
    }

    public void setImage(PlanarImage _image) {
        this._image = _image;
    }
}
