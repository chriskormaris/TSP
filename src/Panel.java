import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Abstract base class for all visual examples.
 */
public abstract class Panel extends JPanel {
	/** Version id for serialization. */
	private static final long serialVersionUID = 8221256658243821951L;

	/** First corporate color used for normal coloring.*/
	protected static final Color COLOR1 = new Color(55, 170, 200);
	/** Second corporate color used as signal color */
	protected static final Color COLOR2 = new Color(200,  80,  75);
	
	/**
	 * Performs basic initialization of an example,
	 * like setting a default size.
	 */
	public Panel() {
		super(new BorderLayout());
		setPreferredSize(new Dimension(800, 600));
		setBackground(Color.WHITE);
	}

	/**
	 * Returns a short title for the example.
	 * @return A title text.
	 */
	public abstract String getTitle();

	/**
	 * Returns a more detailed description of the example contents.
	 * @return A description of the example.
	 */
	public abstract String getDescription();

	/**
	 * Opens a frame and shows the example in it.
	 * @return the frame instance used for displaying the example.
	 */
	protected JFrame showInFrame() {
		JFrame frame = new JFrame(getTitle());
		frame.getContentPane().add(this, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(getPreferredSize());
		frame.setVisible(true);
		centerWindow(frame);
		return frame;
	}

	@Override
	public String toString() {
		return getTitle();
	}
	
	public static void centerWindow(JFrame frame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) (((dimension.getWidth() - frame.getWidth()) / 2));
	    int y = (int) (((dimension.getHeight() - frame.getHeight()) / 2));
	    frame.setLocation(x, y);
	}
	
}
