package ActionTotal;
import java.io.File;
import java.io.FileInputStream;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class MusicPlay
{
	public static AudioStream music(String str)
	{
		File file = new File(str);
		FileInputStream fis = null;
		AudioStream as = null;
		try
		{
			fis = new FileInputStream(file);
			as = new AudioStream(fis);
			AudioPlayer.player.start(as);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return as;
	}
}
