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
		
		// TU
		this.test_10();
	}
	
	/**
	 * Test pour vérifier la fonction de calcul de l'origine
	 * */
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
	
	/**
	 * Test pour vérifier l'unicité d'une même forme (référence) au sein d'un groupe de formes
	 * */
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
	
	/**
	 * Test sur le groupement de formes
	 * */
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
	
	/**
	 * Test sur le dégroupement des formes
	 * */	
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
	
	/**
	 * Test sur le groupement de deux groupes
	 * Scénario :
	 * - Création de trois formes :  S1, S2 et S3
	 * - Création de deux groupes : 
	 * 		G1 = S1 + S2
	 * 		G2 = S2 + S3 + G1
	 *	
	 * Résultat attendu :
	 * - G2 = S1 + S2 + S3
	 * Le groupe S2 est composé de formes simples et uniques.
	 * */
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
		//group2.afficher();
		
		drawing.print();
	}
	
	/**
	 * Test sur le clonage de formes simples
	 * Résultat attendu :
	 * - le drawing contient 4 formes
	 * */
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
	
	/**
	 * Test sur le clonage des groupes de formes
	 * Résultat attendu :
	 * - 4 formes attachées au drawing (2 formes + 2 groupes dont 1 issu du clonage)
	 * */
	public void test_7() {
		Shape rectangle = new Rectangle(new Point(50, 40), 200, 200, Color.blue);
		Shape circle = new Circle(new Point(320, 110), 60, Color.green);
		
		drawing.addShape(rectangle);
		drawing.addShape(circle);
		
		ShapeGroup group = new ShapeGroup(drawing);
		group.add(rectangle);
		group.add(circle);
		
		Shape cloneGroup = group.clone();
		
		drawing.print();
	}
	
	/**
	 * Test pour créer une forme depuis la méthode statique
	 * 
	 * */
	public void test_8() {
		Shape rectangle = Shape.create("rectangle", new Object[]{new Point(190, 200), 50, 150, Color.red});
		Shape circle = Shape.create("circle", new Object[]{new Point(320, 110), 60.0, Color.green});
		
		drawing.addShape(rectangle);
		drawing.addShape(circle);
		
		ShapeGroup group = new ShapeGroup(drawing);
		group.add(rectangle);
		group.add(circle);
		
		Shape cloneGroup = group.clone();
		
		drawing.print();
	}
	
	/**
	 * Test pour la commande de création de formes
	 * Résultat attendu :
	 * - 2 commandes dans l'historique
	 * - indice courant de l'historique vaut 1
	 * */
	public void test_9() {
		Object[] rectangle = new Object[]{drawing, "rectangle", new Point(190, 200), 50, 150, Color.red};		
		Shape circle = Shape.create("circle", new Object[]{new Point(320, 110), 60.0, Color.green});
		
		CommandFactory invoker = CommandFactory.init();
		
		invoker.executeCommand("1", rectangle);
		invoker.executeCommand("1", new Object[]{drawing, circle});
		
		invoker.printHistory(); // 2 commandes et pointage vers la 2e
		invoker.undo(); // retrait du cercle
		invoker.printHistory(); // 2 commandes et pointage vers la 1re
		invoker.redo(); // rajout du cercle
		invoker.printHistory(); // 2 commandes et pointage vers la 2e
		
		Object[] blueRectangle = new Object[]{drawing, "rectangle", new Point(50, 40), 200, 200, Color.blue};
		
		invoker.undo(); // retrait du cercle
		invoker.executeCommand("1", blueRectangle); // ajout du rectangle bleu
		invoker.printHistory(); // 2 commandes et pointage vers la 2e
	}
	
	/**
	 * Test pour la commande de groupement de formes
	 * Résultat attendu :
	 * - 3 commandes dans l'historique
	 * - 3 formes (2 formes et 1 groupe) dans le drawing
	 * - l'indice courant de l'historique vaut 2 (pointe vers la commande 3)
	 * */
	public void test_10() {		
		CommandFactory invoker = CommandFactory.init();
		
		invoker.executeCommand("1", new Object[]{drawing, "rectangle", new Point(190, 200), 50, 150, Color.red});
		
		Shape circle = Shape.create("circle", new Object[]{new Point(320, 110), 60.0, Color.green});
		invoker.executeCommand("1", new Object[]{drawing, circle});
		
		invoker.executeCommand("2", new Object[]{drawing, drawing.getShape(0), drawing.getShape(1)});
		
		invoker.printHistory();
		drawing.print();
	}
	
	public static void main(String[] args){
		Paint app = new Paint();
		app.run();
	}
}
