/**FINISHED
 * AudioPlayer.java
 * 
 * This class plays an audio file, namely a wave file. It uses a JNI link to
 * a DLL file to in order for the file to play.
 * 
 * @author David Hoffman
 */

import com.intermec.device.audio.Audio;
import com.intermec.device.audio.AudioException;

public class AudioPlayer {

	/**
	 * Declare needed variables.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * This method plays the audio file using JNI.
	 * 
	 * @param file The audio file to be played.
	 */
	public void playAudio(String file) {
		String soundToPlay = Main.soundDir + "\\" + file;

        //Play the audio file.
       	if (Main.DEBUG_PC) {
       		FrameUtils.showDialogBox(file + " would play now.");
       	} else {
       		try {
       			Audio.playSound(soundToPlay);
       		} catch (AudioException e) {
       			FrameUtils.showDialogBox("Audio file did not play.");
       		}
        }
	}
}
