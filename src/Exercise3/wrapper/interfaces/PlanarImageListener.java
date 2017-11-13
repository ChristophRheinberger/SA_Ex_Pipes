package Exercise3.wrapper.interfaces;

import Exercise3.wrapper.events.PlanarImageEvent;

import java.util.EventListener;

/**
 * Created by Christoph on 13.11.2017.
 */
public interface PlanarImageListener extends EventListener {
    public abstract void imageChangedEvent(PlanarImageEvent image);
}
