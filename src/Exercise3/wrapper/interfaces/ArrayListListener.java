package Exercise3.wrapper.interfaces;

import Exercise3.wrapper.events.ArrayCooridinatesEvent;

import java.util.EventListener;

public interface ArrayListListener extends EventListener {
    public abstract void coordinatesChangedEvent(ArrayCooridinatesEvent image);
}
