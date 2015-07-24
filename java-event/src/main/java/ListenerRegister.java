/**
 * Copyright (c) 2015, jdpay.com. All rights reserved.
 */

import java.util.Vector;

/**
 * <b>项目名</b>： event <br>
 * <b>包名称</b>： PACKAGE_NAME <br>
 * <b>类名称</b>： ListenerRegister <br>
 * <b>类描述</b>： <br>
 * <b>创建人</b>： <a href="mailto:bitium@126.com">李朋明</a> <br>
 * <b>修改人</b>： <br>
 * <b>创建时间</b>： 2015/7/24 <br>
 * <b>修改时间</b>： <br>
 * <b>修改备注</b>： <br>
 *
 * @version 1.0.0 <br>
 */
public class ListenerRegister {
    private Vector<ValueChangeListener> listeners = new Vector<ValueChangeListener>();

    public synchronized void addListener(ValueChangeListener a) {
        listeners.addElement(a);
    }

    public synchronized void removeListener(ValueChangeListener a) {
        listeners.removeElement(a);
    }

    @SuppressWarnings("unchecked")
    public void fireAEvent(ValueChangeEvent evt) {
        Vector<ValueChangeListener> currentListeners = null;
        synchronized (this) {
            currentListeners = (Vector<ValueChangeListener>) listeners.clone();
        }
        for (int i = 0; i < currentListeners.size(); i++) {
            ValueChangeListener listener = (ValueChangeListener) currentListeners
                    .elementAt(i);
            listener.performed(evt);
        }
    }
}
