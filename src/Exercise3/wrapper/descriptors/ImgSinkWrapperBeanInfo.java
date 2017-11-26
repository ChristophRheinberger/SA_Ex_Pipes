package Exercise3.wrapper.descriptors;

import Exercise3.wrapper.ImgSinkWrapper;
import Exercise3.wrapper.LoadImgSrcWrapper;
import Exercise3.wrapper.events.ArrayCooridinatesEvent;
import Exercise3.wrapper.interfaces.ArrayListListener;
import Exercise3.wrapper.interfaces.PlanarImageListener;

import java.beans.EventSetDescriptor;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

public class ImgSinkWrapperBeanInfo extends SimpleBeanInfo {

    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            PropertyDescriptor a, f;
            Class cls = ImgSinkWrapper.class;
            a = new PropertyDescriptor("tolerance", cls);
            f = new PropertyDescriptor("expectedRadius", cls);
            PropertyDescriptor pds[] = { a, f };
            return pds;
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public EventSetDescriptor[] getEventSetDescriptors() {
        try {
            EventSetDescriptor esd1;
            Class c = ImgSinkWrapper.class;
            String es = "image";
            Class lc = ArrayListListener.class;
            String names[] = { "coordinatesChangedEvent" };
            String al = "addArrayListListener";
            String rl  = "removeArrayListListener";
            esd1 = new EventSetDescriptor(c, es, lc, names, al, rl);
            EventSetDescriptor esd[] = { esd1 };
            return esd;
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public MethodDescriptor[] getMethodDescriptors() {
        MethodDescriptor mds[] = { };
        return mds;
    }
}
