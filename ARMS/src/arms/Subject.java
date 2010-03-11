/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arms;

/**
 *
 * @author vh4x0r
 */
public class Subject {

    public Subject(String name, String id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public String toString() {
        return name;
    }
    private String name;
    private String id;
}
