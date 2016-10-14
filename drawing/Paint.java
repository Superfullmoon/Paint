package drawing;

import java.awt.*;

import javax.swing.*;

/**
 * Classe Interface graphique pour l'application de dessin
 */
public class Paint {

	private JFrame frame;
	private JButton clearButton;
	private JButton circleButton;
	private JButton rectangleButton;
	private JButton associateButton;
	private JButton dissociateButton;
	private JPanel buttonPanel;
	private JPanel mainPanel;
	private Drawing drawing;
	private JPanel infoPanel;
	private StatusBar statusBar;
	
	public void run(){
		frame = new JFrame("Paint");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainPanel = new JPanel(new BorderLayout());
		
		drawing = new Drawing();
		drawing.setBackground(Color.WHITE);
		clearButton = new JButton("Clear");
		circleButton = new JButton("Circle");
		rectangleButton = new JButton("Rectangle");
		associateButton = new JButton("Associate");
		dissociateButton = new JButton("Dissociate");
		
		// Informations
		buttonPanel = new JPanel();
		buttonPanel.add(clearButton);
		buttonPanel.add(circleButton);
		buttonPanel.add(rectangleButton);
		buttonPanel.add(associateButton);
		buttonPanel.add(dissociateButton);
		
		infoPanel = new JPanel();
		infoPanel.setBackground(Color.WHITE);
		infoPanel.setPreferredSize(new Dimension(frame.getBounds().width, 25));
		statusBar = new StatusBar(drawing);
		infoPanel.add(statusBar);
		
		JPanel containerInfo = new JPanel(new BorderLayout());
		containerInfo.add(buttonPanel, BorderLayout.SOUTH);
		containerInfo.add(infoPanel, BorderLayout.NORTH);
		
		mainPanel.add(containerInfo, BorderLayout.SOUTH);
		mainPanel.add(drawing, BorderLayout.CENTER);	
		
		//listeners pour les boutons
		clearButton.addActionListener(new ClearButtonListener(drawing));
		circleButton.addActionListener(new CircleButtonListener(drawing));
		rectangleButton.addActionListener(new RectangleButtonListener(drawing));
		
		//listeners pour la zone de dessin
		DrawingMouseListener l = new DrawingMouseListener(drawing);
		drawing.addMouseListener(l);
		drawing.addMouseMotionListener(l);

		frame.getContentPane().add(mainPanel);
		frame.setSize(640,480);
		frame.setVisible(true);
		
		// TU1
		this.test_1();
		
	}
	
	public void test_1() {
		Shape rectangle1 = new Rectangle(new Point(50, 40), 200, 200, Color.blue);
		Shape rectangle2 = new Rectangle(new Point(190,200), 50, 150, Color.red);
		Shape circle1 = new Circle(new Point(320, 110), 60, Color.green);
		
		System.out.println("Affichage des formes");
		System.out.println("Rectangle 1");
		rectangle1.afficher();
		
		System.out.println("Rectangle 2");
		rectangle2.afficher();
		
		System.out.println("Circle 1");
		circle1.afficher();
		System.out.println("\n");
		
		drawing.addShape(rectangle1);
		drawing.addShape(rectangle2);
		drawing.addShape(circle1);
		
		GroupShape group1 = new GroupShape();
		group1.add(rectangle1);
		System.out.println(group1.calculOrigin());
		
		group1.add(rectangle2);
		System.out.println(group1.calculOrigin());
		
		group1.add(circle1);
		System.out.println(group1.calculOrigin());
	}
	
	
	public static void main(String[] args){
		Paint app = new Paint();
		app.run();
		
		
	}
}
