import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class AutoScreenshot {
	
	private JFrame frame;	
	private JLabel label;
	private JLabel labelPath;
	private JLabel labelCaptures;
	private JLabel labelStartCount;
	private JLabel labelXRegion;
	private JLabel labelWCapture;
	private JLabel labelYRegion;
	private JLabel labelHCapture;
	
	private JLabel labelMousePosition;
	
	private JPanel panel;
    private GridBagConstraints cst;
	
	private JButton buttonSetRegion;
	private JButton buttonStart;
	private JButton buttonPause;
	private JButton buttonStop;
	
	private JTextField inputPath;
	private JTextField inputCaptures;
	private JTextField inputStartCount;
	private JTextField inputXRegion;
	private JTextField inputWCapture;
	private JTextField inputYRegion;
	private JTextField inputHCapture;
	
	private JTextField inputMousePostion;
	
	
	private Robot robot;

	private Rectangle captureRect;

	private int rows = 5;
	private int cols = 4;
	
	private String pathSave;
	private String format ="png";
	private int numberCaptures;
	private int startCount;

	private int xCapture;
	private int yCapture;
	private int wCapture;
	private int HCapture;
	
	public AutoScreenshot(){
		frame = new JFrame("Setup Auto Screenshot");
		
		panel = new JPanel(new GridBagLayout());        

		cst = new GridBagConstraints();
	    
	    this.init();

	    frame.setContentPane(panel);
	    frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	    
		frame.setSize(600,300);
	    frame.setVisible(true);
	}
	
	public void init() {
		label = new JLabel("AutoScreenshot v1.0");
        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridx = 1;
        cst.gridy = 0;
        cst.gridwidth = 4;   
        panel.add(label,cst);
		
        
		labelPath = new JLabel("Path to save the images ");
        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridwidth = 1;
        cst.gridx = 0;
        cst.gridy = 1;
        panel.add(labelPath,cst);
        
		inputPath = new JTextField();
        cst.fill = GridBagConstraints.HORIZONTAL;
		cst.gridwidth = 3;
		cst.gridx = 1;
        cst.gridy = 1;
        panel.add(inputPath,cst);
        
		labelCaptures = new JLabel("Number of captures ",SwingConstants.CENTER);
        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridx = 0;
        cst.gridy = 2;
        cst.gridwidth = 1;   
        panel.add(labelCaptures,cst);

		inputCaptures = new JTextField();
        cst.gridx = 1;
        cst.gridy = 2;
        panel.add(inputCaptures,cst);

		labelStartCount= new JLabel("Number to start count ",SwingConstants.CENTER);
        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridx = 0;
        cst.gridy = 3;
        cst.gridwidth = 1;   
        panel.add(labelStartCount,cst);

		inputStartCount = new JTextField();
        cst.gridx = 1;
        cst.gridy = 3;
        panel.add(inputStartCount,cst);
        
		labelXRegion= new JLabel("Point X to Capture ",SwingConstants.CENTER);
        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridx = 0;
        cst.gridy = 4;
        cst.gridwidth = 1;   
        panel.add(labelXRegion,cst);

		inputXRegion= new JTextField();
        //cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridx = 1;
        cst.gridy = 4;
        //cst.gridwidth = 1;
        panel.add(inputXRegion,cst);

		labelWCapture= new JLabel("Width Capture ",SwingConstants.CENTER);
        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridx = 0;
        cst.gridy = 5;
        cst.gridwidth = 1;   
        panel.add(labelWCapture,cst);

		inputWCapture= new JTextField();
        //cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridx = 1;
        cst.gridy = 5;
        //cst.gridwidth = 1;
        panel.add(inputWCapture,cst);

    	
		labelYRegion= new JLabel("Point Y to Capture ",SwingConstants.CENTER);
        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridx = 0;
        cst.gridy = 6;
        cst.gridwidth = 1;   
        panel.add(labelYRegion,cst);

		inputYRegion= new JTextField();
        //cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridx = 1;
        cst.gridy = 6;
        panel.add(inputYRegion,cst);

		labelHCapture= new JLabel("Height Capture ",SwingConstants.CENTER);
        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridx = 0;
        cst.gridy = 7;
        cst.gridwidth = 1;   
        panel.add(labelHCapture,cst);

		inputHCapture= new JTextField();
        //cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridx = 1;
        cst.gridy = 7;
        panel.add(inputHCapture,cst);

        
        buttonSetRegion = new JButton("Test Capture");
        buttonSetRegion.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				setRegionButton();
			}
			
		});
		cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridx = 0;
        cst.gridy = 8;
        cst.gridwidth = 4;   
        panel.add(buttonSetRegion,cst);

        
        
		buttonStart = new JButton("Start AutoScreenshot!");
		buttonStart.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				startButton();
			}
			
		});
		cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridwidth = 1;
        cst.gridx = 0;
        cst.gridy = 9;
        panel.add(buttonStart,cst);

		buttonPause = new JButton("Pause AutoScreenshot");
		buttonPause.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				pauseButton();
			}
			
		});
        cst.gridx = 1;
        cst.gridy = 9;
        panel.add(buttonPause,cst);

        buttonStop = new JButton("Stop AutoScreenshot!");
        buttonStop.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				stopButton();
			}
			
		});
        cst.gridx = 2;
        cst.gridy = 9;
        panel.add(buttonStop,cst);
        
        
		labelMousePosition= new JLabel("Key Press: ",SwingConstants.CENTER);
        cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridx = 1;
        cst.gridy = 10;
        cst.gridwidth = 1;   
        panel.add(labelMousePosition,cst);

        inputMousePostion = new JTextField();
        //inputMousePostion.setEnabled(false);
        inputMousePostion.addKeyListener(new MyKeyListener());
                
        //cst.fill = GridBagConstraints.HORIZONTAL;
        cst.gridx = 2;
        cst.gridy = 10;
        //cst.gridwidth = 1;
        panel.add(inputMousePostion,cst);
	
        
	}
	private void setRegionButton(){
		pathSave = this.inputPath.getText();		
		try {
			robot = new Robot();
			// x,y,w,h
			//captureRect = new Rectangle(xCapture, yCapture, wCapture,HCapture);
			captureRect  = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage screenFullImage = robot.createScreenCapture(captureRect);
            ImageIO.write(screenFullImage, format, new File(pathSave+"/test"));
		
		}catch (Exception e){
			System.err.println("Erro: " + e.getMessage());
		}
	}
	private void startButton() {
		
		pathSave = this.inputPath.getText();
		numberCaptures = Integer.parseInt(this.inputCaptures.getText());
		startCount = Integer.parseInt(this.inputStartCount.getText());
		
		
		xCapture = Integer.parseInt(this.inputXRegion.getText());
		yCapture = Integer.parseInt(this.inputYRegion.getText());
		wCapture = Integer.parseInt(this.inputWCapture.getText());
		HCapture = Integer.parseInt(this.inputHCapture.getText());

		int tempStartCount = startCount;
		try {
			
			robot = new Robot();
			Thread.sleep(5000);
			
			for(int i = 0; i <= numberCaptures; i++) {
				//captureRect  = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
				// x,y,w,h
				captureRect = new Rectangle(xCapture, yCapture, wCapture,HCapture);
				BufferedImage screenFullImage = robot.createScreenCapture(captureRect);
	            ImageIO.write(screenFullImage, format, new File(pathSave+"/page_"+tempStartCount));
	            tempStartCount++;
	            robot.keyPress(39);   
				robot.keyRelease(39);
				Thread.sleep(1000);
			}
		}catch (Exception e){
			System.err.println("Erro: " + e.getMessage());
		}
	}
	private void pauseButton() {
		
	}
	private void stopButton() {
		
	}
	public static void main(String[] args) {
		AutoScreenshot autoScreenshot = new AutoScreenshot();
	}
}

class MyKeyListener extends KeyAdapter {
	  public void keyPressed(KeyEvent evt) {
		  System.out.println("Check for key codes: " + evt.getKeyCode());  
	  }
	}
