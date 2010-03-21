/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arms;

import java.io.Serializable;

/**
 *
 * @author vh4x0r
 */
public class Subject implements Serializable {

    public Subject(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return name;
    }
    private String name;
    private String id;
}
