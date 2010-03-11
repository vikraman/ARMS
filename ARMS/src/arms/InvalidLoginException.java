/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package arms;

/**
 *
 * @author vh4x0r
 */
public class InvalidLoginException extends Exception {

    /**
     * Creates a new instance of <code>InvalidLoginException</code> without detail message.
     */
    public InvalidLoginException() {
    }


    /**
     * Constructs an instance of <code>InvalidLoginException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public InvalidLoginException(String msg) {
        super(msg);
    }
}
