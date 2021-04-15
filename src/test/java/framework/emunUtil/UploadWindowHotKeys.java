package framework.emunUtil;

import java.awt.event.KeyEvent;

public enum UploadWindowHotKeys {
    VK_CONTROL(KeyEvent.VK_CONTROL),
    VK_V(KeyEvent.VK_V),
    VK_E(KeyEvent.VK_ENTER);
    private int key;

    UploadWindowHotKeys(int key) {
        this.key = key;
    }

    public int getKey() {
        return key;
    }
}
