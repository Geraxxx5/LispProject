package modelo;

import java.util.HashMap;
import java.util.List;

public class VariableModel {
    HashMap<String, List<Object>> variable = new HashMap<>();
    
    /**
     *
     * @param name
     * @param Value
     * Create new Variable
     */
    public void createNewVariable(String name, List<Object> Value){
        variable.put(name, Value); 
    }
    
    /**
     *
     * @param name
     * @param value
     * 
     */
    public void addTempValue(String name,String value){
        variable.get(name).add(value);
    }
    
    /**
     *
     * @param name
     * @return
     * to recycle the same variable"
     */
    public boolean varibaleExist(String name){
        return variable.containsKey(name);
    }
    
    /**
     *
     * @param key
     * @return
     * Obtain the last value of the list by the key
     */
    public Object lastValue(String key){
        return variable.get(key).get(variable.get(key).size()-1);
    }
    
    /**
     *
     * @param name
     * Remove temporal value
     */
    public void removeTempValue(String name){
        variable.get(name).remove(variable.size() -1);
    }
    
    /**
     *
     * @param key
     * @return Last value
     */
    public Object peekVariable(String key){
        return variable.get(key).get(variable.get(key).size());
    }
    
    /**
     *
     * @return
     */
    public HashMap<String, List<Object>> getVariables(){
        return variable;
    }
    
}
