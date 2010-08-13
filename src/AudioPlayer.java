/**FINISHED
 * AudioPlayer.java
 * 
 * This class plays an audio file, namely a wave file. It uses a JNI link to
 * a DLL file to in order for the file to play.
 * 
 * @author David Hoffman
 */

public class AudioPlayer {

	/**
	 * Declare needed variables.
	 */
	private static final long serialVersionUID = 1L;

    private static boolean emulating = false;
    
    static final native boolean PlaySound(String str);
    static final native int GetOutputVolume(int ai[]);
    static final native int SetOutputVolume(int i);

	/**
	 * This method plays the audio file using JNI.
	 * 
	 * @param file The audio file to be played.
	 * @throws AudioException 
	 */
	public boolean playAudio(String file) throws AudioException {
		String soundToPlay = Main.soundDir + "\\" + file;
        boolean flag = true;

        //Play the audio file if not emulating.
        if(!emulating) {
        	try {
        		flag = PlaySound(soundToPlay);
        	} catch (UnsatisfiedLinkError unsatisfiedlinkerror) {
        		handleUnsatisfiedLinkError("PlaySound");
				FrameUtils.showDialogBox("Audio file did not play.");
        	}
        }
        
        return flag;
	}

    /**
     * Gets the output volume using JNI.
     * 
     * @return The volume.
     * @throws AudioException
     */
    public static int getOutputVolume() throws AudioException {
        int volume = 0;
        
        //Get the output volume if not emulating.
        if(!emulating) {
            int ai[] = new int[1];
            
            //Get the output volume using JNI.
            try {
                int code = GetOutputVolume(ai);
                checkNativeReturnCode(code);
                volume = ai[0];
            }
            catch(UnsatisfiedLinkError unsatisfiedlinkerror) {
                handleUnsatisfiedLinkError("GetOutputVolume");
            }
        }
        return volume;
    }

    /**
     * Sets the output volume using JNI.
     * 
     * @param volume The volume to set.
     * @throws AudioException
     */
    public static void setOutputVolume(int volume) throws AudioException {
        
    	//Set the volume if not emulating.
    	if(!emulating) {
            //Set the output volume using JNI.
    		try {
                int code = SetOutputVolume(volume);
                checkNativeReturnCode(code);
            }
            catch(UnsatisfiedLinkError unsatisfiedlinkerror) {
                handleUnsatisfiedLinkError("SetOutputVolume");
            }
    	}
    }

    /**
     * This method converts the code returned from JNI when getting or
     * setting the output volume.
     * 
     * @param code The returned code from JNI.
     * @throws AudioException
     */
    private static void checkNativeReturnCode(int code) throws AudioException {

    	//Convert the code if it is not zero.
    	if(code != 0) {
            String str = "Native method failed";
            
            //Convert the code.
            switch(code) {
            	case 1: // '\001'
            		str += ", MMS error";
            		break;

            	case 2: // '\002'
            		str += ", MMS device ID out of range";
            		break;

            	case 3: // '\003'
            		str += ", MMS driver failed enable";
            		break;

            	case 4: // '\004'
            		str += ", MMS device already allocated";
            		break;

            	case 5: // '\005'
            		str += ", MMS device handle is invalid";
            		break;

            	case 6: // '\006'
            		str += ", MMS no device driver present";
            		break;

            	case 7: // '\007'
            		str += ", MMS memory allocation error";
            		break;

            	case 8: // '\b'
            		str += ", MMS function isn't supported";
            		break;

            	case 9: // '\t'
            		str += ", MMS error value out of range";
            		break;

            	case 10: // '\n'
            		str += ", MMS invalid flag passed";
            		break;

            	case 11: // '\013'
            		str += ", MMS invalid parameter passed";
            		break;

            	case 12: // '\f'
            		str += ", MMS handle being used simultaneously on another thread";
            		break;

            	case 13: // '\r'
            		str += ", MMS specified alias not found";
            		break;

            	case 14: // '\016'
            		str += ", MMS bad registry database";
            		break;

            	case 15: // '\017'
            		str += ", MMS registry key not found";
            		break;

            	case 16: // '\020'
            		str += ", MMS registry read error";
            		break;

            	case 17: // '\021'
            		str += ", MMS registry write error";
            		break;

            	case 18: // '\022'
            		str += ", MMS registry delete error";
            		break;

            	case 19: // '\023'
            		str += ", MMS registry value not found";
            		break;

            	case 20: // '\024'
            		str += ", MMS driver does not call DriverCallback";
            		break;

            	default:
            		str = "Exception in itcAudioJni native code, hResult "
            				+ Integer.toHexString(code);
            		break;
            }
            	
            throw new AudioException(str, code);
        } else {
           	return;
        }
    }

    /**
     * This method throws an exception when there is an unsatisfied link error.
     * 
     * @param str The string that represents the error.
     * @throws AudioException
     */
    private static void handleUnsatisfiedLinkError(String str) throws AudioException {
        
    	//Throws and exception when an error occurs.
    	throw new AudioException("JNI method " + str
    			+ " cannot be found in itcAudioJni.dll", 120);
    }

    /**
     * This method determined if the system is emulating.
     * 
     * @return Emulating if true, false otherwise.
     */
    private static boolean emulate() {
        
    	String str = System.getProperty("emulate");
        
    	//Determine if the system property emulate is null.
    	if(str == null) {
    		emulating = false;
    	} else {
        	//Sets emulating to false if system property is not equal to off.
    		if(str.equalsIgnoreCase("OFF")) {
        		emulating = false;
        	} else {
        		//Otherwise emulating equals true.
        		emulating = true;
        	}
        }
    	
        return emulating;
    }

    static {
        try {
            if(!emulate()) System.loadLibrary("itcAudioJni");
        }
        catch(UnsatisfiedLinkError unsatisfiedlinkerror) {
        	System.err.println("itcAudioJni failed to load. \n" + unsatisfiedlinkerror);
        	System.exit(1);
        }
    }
}
