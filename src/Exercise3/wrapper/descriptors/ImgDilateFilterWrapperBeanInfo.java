package Exercise3.wrapper.descriptors;

import Exercise3.wrapper.ImgDilateFilterWrapper;
import Exercise3.wrapper.ImgErodeFilterWrapper;
import Exercise3.wrapper.LoadImgSrcWrapper;
import Exercise3.wrapper.interfaces.PlanarImageListener;

import java.beans.EventSetDescriptor;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

public class ImgDilateFilterWrapperBeanInfo extends SimpleBeanInfo {

    public PropertyDescriptor[] getPropertyDescriptors() {
        try {
            PropertyDescriptor a;
            Class cls = ImgDilateFilterWrapper.class;
            a = new PropertyDescriptor("amount", cls);
            PropertyDescriptor pds[] = { a };
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
            Class c = ImgDilateFilterWrapper.class;
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
