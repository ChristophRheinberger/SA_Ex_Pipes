package Exercise3.wrapper;

import Exercise3.filter.ImgSink;
import Exercise3.wrapper.events.PlanarImageEvent;
import Exercise3.wrapper.interfaces.PlanarImageListener;

import java.io.Serializable;

/**
 * Created by Clemens on 24.11.2017.
 */
public class ImgSinkWrapper implements PlanarImageListener, Serializable {
    private ImgSink imgSink;

    public ImgSinkWrapper(){

    }

    @Override
    public void imageChangedEvent(PlanarImageEvent image) {

    }
}
