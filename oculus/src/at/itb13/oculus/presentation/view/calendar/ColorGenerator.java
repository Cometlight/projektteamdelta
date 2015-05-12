package at.itb13.oculus.presentation.view.calendar;

import java.util.ArrayList;
import java.util.List;

import javafx.scene.paint.Color;

/**
 * Generates easily distinguishable
 * Color source: http://eleanormaclure.files.wordpress.com/2011/03/colour-coding.pdf
 * 
 * @author Daniel Scheffknecht
 * @date May 11, 2015
 */
public class ColorGenerator {
	private List<Color> _colors;
	private int _indexLastColor;
	
	public ColorGenerator() {
		_indexLastColor = 0;
		_colors = new ArrayList<>();
		_colors.add(new Color(255/255d, 179/255d,   0/255d, 1d));	// vivid yellow
		_colors.add(new Color(128/255d,  62/255d, 117/255d, 1d));	// strong purple
		_colors.add(new Color(255/255d, 104/255d,   0/255d, 1d));	// vivid_orange
		_colors.add(new Color(166/255d, 189/255d, 215/255d, 1d));	// very light blue
		_colors.add(new Color(193/255d,   0/255d,  32/255d, 1d));	// vivid red
//		_colors.add(new Color(206/255d, 162/255d,  98/255d, 1d));	// grayish yellow
//		_colors.add(new Color(129/255d, 112/255d, 102/255d, 1d));	// medium gray
		_colors.add(new Color(  0/255d, 125/255d,  52/255d, 1d));	// vivid green
		_colors.add(new Color(246/255d, 118/255d, 142/255d, 1d));	// strong purplish pink
		_colors.add(new Color(  0/255d,  83/255d, 138/255d, 1d));	// strong blue
		_colors.add(new Color(255/255d, 122/255d,  92/255d, 1d));	// strong yellowish pink
		_colors.add(new Color( 83/255d,  55/255d, 122/255d, 1d));	// strong violet
		_colors.add(new Color(255/255d, 142/255d,   0/255d, 1d));	// vivid orange yellow
		_colors.add(new Color(179/255d,  40/255d,  81/255d, 1d));	// strong purplish red
		_colors.add(new Color(244/255d, 200/255d,   0/255d, 1d));	// vivid greenish yellow
		_colors.add(new Color(127/255d,  24/255d,  13/255d, 1d));	// strong reddish brown
		_colors.add(new Color(147/255d, 170/255d,   0/255d, 1d));	// vivid yellowish green
		_colors.add(new Color( 89/255d,  51/255d,  21/255d, 1d));	// deep yellowish brown
		_colors.add(new Color(241/255d,  58/255d,  19/255d, 1d));	// vivid reddish orange
		_colors.add(new Color( 35/255d,  44/255d,  22/255d, 1d));	// dark olive green
	}
	
	public Color getNextColor() {
		return _colors.get(_indexLastColor++ % _colors.size());
	}
	
	public void reset() {
		_indexLastColor = 0;
	}
	
	public void reset(int seed) {
		_indexLastColor = seed;
	}
	
	public static String colorToString(Color color) {
		return String.format( "#%02X%02X%02X",
	            (int)( color.getRed() * 255 ),
	            (int)( color.getGreen() * 255 ),
	            (int)( color.getBlue() * 255 ) );
	}
}
