package Exercise3.wrapper.descriptors;

import Exercise3.wrapper.ImgThreshholdFilterWrapper;
import Exercise3.wrapper.interfaces.PlanarImageListener;

import java.beans.EventSetDescriptor;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

public class ImgThreshholdFilterWrapperBeanInfo extends SimpleBeanInfo {

    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            PropertyDescriptor a, f, g;
            Class cls = ImgThreshholdFilterWrapper.class;
            a = new PropertyDescriptor("low", cls);
            f = new PropertyDescriptor("high", cls);
            g = new PropertyDescriptor("target", cls);
            PropertyDescriptor pds[] = { a, f, g };
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
            Class c = ImgThreshholdFilterWrapper.class;
            String es = "image";
            Class lc = PlanarImageListener.class;
            String names[] = { "imageChangedEvent" };
            String al = "addPlanarImageListener";
            String rl  = "removePlanarImageListener";
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
