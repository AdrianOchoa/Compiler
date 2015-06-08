/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

/**
 *
 * @author Adrián
 */
public class Identifier {
    
    private final String type;
    private final String name;

    /**
     * Constructs a new Identifier with data type and name
     * @param type the basic data type od the identifier
     * @param name the name
     */
    
    public Identifier(String type, String name) {
        this.name = name;
        this.type = type;
    }
    
    public String getName() {
        return name;
    }
    
    public String getType() {
        return type;
        
    }
    
}
