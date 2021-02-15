package com.sourdoughsoftware;

import java.util.HashMap;

public interface Savable {
    public HashMap<String, Object> getSaveFields();
    public boolean setSaveFields(HashMap<String, Object> result);
    public void saveClass();
}
