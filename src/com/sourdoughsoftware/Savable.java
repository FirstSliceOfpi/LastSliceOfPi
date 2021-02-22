package com.sourdoughsoftware;
/**
 * Classes that implement this interface can have their data fields saved
 */

import java.util.HashMap;

public interface Savable {
    public HashMap<String, Object> getSaveFields();
    public boolean setSaveFields(HashMap<String, Object> result);
    // Save Class should call GameState.savableClasses(this) to add the class to the list of classes to save
    public void saveClass();
}
