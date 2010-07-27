/**FINISHED
 * AudioException.java
 * 
 * This is a class that describes an exception for playing an audio file.
 *
 * @author David Hoffman
 */

public class AudioException extends Exception {

	/**
	 * Declare needed variables.
	 */
	private static final long serialVersionUID = 1L;

	public static final int UNSATISFIED_LINK_ERROR = 120;
    static final String EX_MSG_ERROR = "Native method failed";
    private int m_iErrorCode;

	/**
	 * The constructor for the AudioException.
	 * 
	 * @param str The message error string.
	 * @param errorCode The error code.
	 */
    public AudioException(String str, int errorCode) {
        
    	super(str);
        m_iErrorCode = errorCode;
    }

    /**
     * Returns the error code of the exception that occurred.
     * 
     * @return The error code.
     */
	public int getErrorCode() {
        
		return m_iErrorCode;
    }
}
