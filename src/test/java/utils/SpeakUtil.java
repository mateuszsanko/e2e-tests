package utils;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class SpeakUtil {

    public void speak(String text) {
        System.setProperty("freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        VoiceManager vm = VoiceManager.getInstance();
        Voice voice = vm.getVoice("kevin16");
        voice.allocate();
        voice.speak(text);
    }
}