package drawing;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * Classe Interface graphique pour l'application de dessin
 */
public class Paint {

	private JFrame frame;
	private JButton clearButton;
	private JButton circleButton;
	private JButton rectangleButton;
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
		
		// Informations
		buttonPanel = new JPanel();
		buttonPanel.add(clearButton);
		buttonPanel.add(circleButton);
		buttonPanel.add(rectangleButton);
		
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
		this.test_7();
	}
	
	// Test pour vérifier la fonction calculOrigin()
	public void test_1() {
		Shape rectangle1 = new Rectangle(new Point(50, 40), 200, 200, Color.blue);
		Shape rectangle2 = new Rectangle(new Point(190, 200), 50, 150, Color.red);
		Shape circle1 = new Circle(new Point(320, 110), 60, Color.green);
		
		rectangle1.afficher();
		rectangle2.afficher();
		circle1.afficher();
		System.out.println("\n");
		
		drawing.addShape(rectangle1);
		drawing.addShape(rectangle2);
		drawing.addShape(circle1);
		
		ShapeGroup group1 = new ShapeGroup(drawing);
		group1.add(rectangle1);
		System.out.println(group1.calculOrigin());
		
		group1.add(rectangle2);
		System.out.println(group1.calculOrigin());
		
		group1.add(circle1);
		System.out.println(group1.calculOrigin());
		
	}
	
	// Test pour vérifier que la fonction d'ajout d'un Shape ne permet pas les doublons
	public void test_2() {
		Shape rectangle1 = new Rectangle(new Point(50, 40), 200, 200, Color.blue);
		Shape rectangle2 = new Rectangle(new Point(190, 200), 50, 150, Color.red);
		Shape circle1 = new Circle(new Point(320, 110), 60, Color.green);
		
		rectangle1.afficher();
		rectangle2.afficher();
		circle1.afficher();
		System.out.println("\n");
		
		drawing.addShape(rectangle1);
		drawing.addShape(rectangle2);
		drawing.addShape(circle1);
		
		ShapeGroup group1 = new ShapeGroup(drawing);
		System.out.println("Ajouts de formes déjà présentes dans le groupe.");
		group1.add(rectangle1);
		group1.add(rectangle2);
		group1.add(rectangle1);
		
		System.out.println("Ajout d'une forme non présente.");
		group1.add(circle1);
		group1.afficher();
		
		System.out.println("Suppression d'une forme présente puis ajout.");
		group1.remove(rectangle1);
		group1.add(rectangle1);
		group1.afficher();
	}
	
	// Test sur le groupement de formes
	public void test_3() {
		Shape rectangle1 = new Rectangle(new Point(50, 40), 200, 200, Color.blue);
		Shape rectangle2 = new Rectangle(new Point(190, 200), 50, 150, Color.red);
		Shape circle1 = new Circle(new Point(320, 110), 60, Color.green);
		
		drawing.addShape(rectangle1);
		drawing.addShape(rectangle2);
		drawing.addShape(circle1);
		
		ShapeGroup group1 = new ShapeGroup(drawing);
		group1.add(rectangle1);
		group1.add(rectangle2);
		
		System.out.println("Affichage drawing 1");
		drawing.print();
		
		ShapeGroup group2 = new ShapeGroup(drawing);
		group2.add(circle1);
		group2.add(rectangle1);
		group2.add(rectangle1);
		
		System.out.println("Affichage drawing 2");
		drawing.print();
	}
	
	// Test de ShapeGroup.dissociate()	
	public void test_4() {
		Shape rectangle1 = new Rectangle(new Point(50, 40), 200, 200, Color.blue);
		Shape rectangle2 = new Rectangle(new Point(190, 200), 50, 150, Color.red);
		Shape circle1 = new Circle(new Point(320, 110), 60, Color.green);
		
		drawing.addShape(rectangle1);
		drawing.addShape(rectangle2);
		drawing.addShape(circle1);
		
		ShapeGroup group1 = new ShapeGroup(drawing);
		group1.add(rectangle1);
		group1.add(rectangle2);
		
		System.out.println("Affichage drawing 1");
		drawing.print();
		
		group1.dissociate();
		System.out.println("Affichage drawing 2");
		drawing.print();
	}
	
	// Test pour associer deux groupes
	public void test_5() {
		Shape rectangle1 = new Rectangle(new Point(50, 40), 200, 200, Color.blue);
		Shape rectangle2 = new Rectangle(new Point(190, 200), 50, 150, Color.red);
		Shape circle1 = new Circle(new Point(320, 110), 60, Color.green);
		
		drawing.addShape(rectangle1);
		drawing.addShape(rectangle2);
		drawing.addShape(circle1);
		
		ShapeGroup group1 = new ShapeGroup(drawing);
		group1.add(rectangle1);
		group1.add(rectangle2);
		
		ShapeGroup group2 = new ShapeGroup(drawing);
		group2.add(circle1);
		group2.add(rectangle2);
		group2.add(group1);
		group2.afficher();
	}
	
	// Test pour le clonage sur les formes simples
	public void test_6() {
		Rectangle rectangle = new Rectangle(new Point(50, 40), 200, 200, Color.blue);
		Shape circle = new Circle(new Point(320, 110), 60, Color.green);
		
		Shape cloneRectangle = rectangle.clone();
		Shape cloneCircle = circle.clone();
		
		drawing.addShape(rectangle);
		drawing.addShape(circle);
		drawing.addShape(cloneRectangle);
		drawing.addShape(cloneCircle);
		
		drawing.print();
	}
	
	// Test pour le clonage sur les groupes de formes
	public void test_7() {
		Rectangle rectangle = new Rectangle(new Point(50, 40), 200, 200, Color.blue);
		Shape circle = new Circle(new Point(320, 110), 60, Color.green);
		
		ShapeGroup group = new ShapeGroup(drawing);
		group.add(rectangle);
		group.add(circle);
		
		Shape cloneGroup = group.clone();
		
		drawing.addShape(group);
		drawing.addShape(cloneGroup);
		
		drawing.print();
	}
	
	public static void main(String[] args){
		Paint app = new Paint();
		app.run();
	}
}