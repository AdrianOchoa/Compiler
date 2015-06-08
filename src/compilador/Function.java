/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

import java.util.HashMap;

/**
 *
 * @author Adrián
 */
public class Function {

    private String type;
    private String identifier;
    private HashMap<String, Identifier> parameters;
    private HashMap<String, Identifier> localVariables;
    private boolean defined;

    /**
     * Constructs a new Function already defined with parameters and local variables
     * @param type the type of the declared function
     * @param identifier the name of the function
     * @param parameters the parameters of the function
     * @param localVariables the variables of the function
     * @param defined true if is already defined, false otherwise
     */
    
    public Function(String type, String identifier,
            HashMap<String, Identifier> parameters,
            HashMap<String, Identifier> localVariables, boolean defined) {
        this.type = type;
        this.identifier = identifier;
        this.parameters = parameters;
        this.localVariables = localVariables;
        this.defined = defined;
    }

    /**
     * Constructs a new Function with only type and identifier
     * @param type the type of the declared function
     * @param identifier the name of the function
     */
    
    public Function (String type, String identifier) {
        this(type, identifier, new HashMap(), new HashMap(), false);
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the identifier
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * @param identifier the identifier to set
     */
    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }

    /**
     * @return the parameters
     */
    public HashMap<String, Identifier> getParameters() {
        return parameters;
    }

    /**
     * @param parameters the parameters to set
     */
    public void setParameters(HashMap<String, Identifier> parameters) {
        this.parameters = parameters;
    }

    /**
     * @return the localVariables
     */
    public HashMap<String, Identifier> getLocalVariables() {
        return localVariables;
    }

    /**
     * @param localVariables the localVariables to set
     */
    public void setLocalVariables(HashMap<String, Identifier> localVariables) {
        this.localVariables = localVariables;
    }

    /**
     * @return the defined
     */
    public boolean isDefined() {
        return defined;
    }

    /**
     * @param defined the defined to set
     */
    public void setDefined(boolean defined) {
        this.defined = defined;
    }

}
