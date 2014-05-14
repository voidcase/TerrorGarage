package garage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SpringLayout;

import springUtilities.SpringUtilities;

public class Operator {

	private BicycleGarageDatabase database;
	private BicycleGarageManager manager;
	private JPanel buttonPanel, textPanel;
	private JTextArea mainTextField;
	private JFrame frame;
	private JButton add, edit, remove;
	private JMenu settings, view, about;
	private JMenuBar menuBar;
	private JMenuItem options, showBarcodeReader, showBarcodeWrite, showPIN,
			showLock, showAbout;

	/**
	 * Skapar ett GUI med alla knappar, f�nster och dylikt som ska finnas med i
	 * operat�rprogrammet.
	 * 
	 * @param database
	 *            cykelgaragets databas som skall anv�ndas
	 */
	public Operator(BicycleGarageDatabase database, BicycleGarageManager manager) {
		this.database = database;
		this.manager = manager;

		buttonPanel = new JPanel();
		buttonPanel.setVisible(true);

		textPanel = new JPanel();
		textPanel.setVisible(true);
		textPanel.setLayout(new BorderLayout());

		frame = new JFrame();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setPreferredSize(new Dimension(500, 200));
		frame.setMinimumSize(new Dimension(470, 200));
		frame.setResizable(true);
		frame.setTitle("Bicycle Garage Manager");

		settings = new JMenu("Inst�llningar");
		settings.setMnemonic(KeyEvent.VK_A);
		settings.getAccessibleContext().setAccessibleDescription("");
		view = new JMenu("Visa");
		view.setMnemonic(KeyEvent.VK_A);
		view.getAccessibleContext().setAccessibleDescription("");
		about = new JMenu("Om");
		about.setMnemonic(KeyEvent.VK_A);
		about.getAccessibleContext().setAccessibleDescription("");

		options = new JMenuItem("Alternativ", KeyEvent.VK_T);
		options.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1,
				ActionEvent.ALT_MASK));
		options.getAccessibleContext().setAccessibleDescription(
				"This doesn't really do anything");
		settings.add(options);

		showBarcodeReader = new JCheckBoxMenuItem("Visa streckkodsl�sare");
		showBarcodeReader.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1,
				ActionEvent.CTRL_MASK));
		showBarcodeReader.getAccessibleContext().setAccessibleDescription("");
		view.add(showBarcodeReader);

		showBarcodeWrite = new JCheckBoxMenuItem("Visa streckkodsskrivare");
		showBarcodeWrite.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2,
				ActionEvent.CTRL_MASK));
		showBarcodeWrite.getAccessibleContext().setAccessibleDescription("");
		view.add(showBarcodeWrite);

		showPIN = new JCheckBoxMenuItem("Visa PIN-kodsterminal");
		showPIN.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3,
				ActionEvent.CTRL_MASK));
		showPIN.getAccessibleContext().setAccessibleDescription("");
		view.add(showPIN);

		showLock = new JCheckBoxMenuItem("Visa d�rrl�s");
		showLock.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4,
				ActionEvent.CTRL_MASK));
		showLock.getAccessibleContext().setAccessibleDescription("");
		view.add(showLock);

		showAbout = new JMenuItem("Alternativ", KeyEvent.VK_T);
		showAbout.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1,
				ActionEvent.SHIFT_MASK));
		showAbout.getAccessibleContext().setAccessibleDescription("");
		about.add(showAbout);

		add = new JButton("L�gg till cykel�gare");
		add.addActionListener(new AddBikeOwner());
		edit = new JButton("Redigera cykel�gare");
		remove = new JButton("Ta bort cykel�gare");
		remove.addActionListener(new RemoveUser());

		mainTextField = new JTextArea();
		mainTextField.setSize(600, 500);
		mainTextField.setEditable(false);
		mainTextField.setBackground(new Color(255, 255, 255));

		menuBar = new JMenuBar();

		menuBar.add(settings);
		menuBar.add(view);
		menuBar.add(about);

		frame.setJMenuBar(menuBar);

		textPanel.add(mainTextField, BorderLayout.CENTER);
		textPanel.setBackground(new Color(124, 230, 242));

		buttonPanel.add(add);
		buttonPanel.add(edit);
		buttonPanel.add(remove);

		frame.add(buttonPanel, BorderLayout.SOUTH);
		frame.add(textPanel, BorderLayout.CENTER);
		frame.pack();
	}

	class AddBikeOwner implements ActionListener {

		private JFrame addFrame;
		private JTextField[] textFields;
		private String[] labels = { "PIN: ", "Streckkod: ", "Namn: ",
				"Telefonnummer: ", "Antal Streckkodskopior: " };

		public void actionPerformed(ActionEvent e) {
			int numPairs = labels.length;

			textFields = new JTextField[labels.length];
			JPanel p = new JPanel(new SpringLayout());
			for (int i = 0; i < numPairs; i++) {
				JLabel l = new JLabel(labels[i], JLabel.TRAILING);
				p.add(l);
				JTextField textField = new JTextField(10);
				textFields[i] = textField;
				l.setLabelFor(textField);
				p.add(textField);
			}

			SpringUtilities.makeCompactGrid(p, numPairs, 2, 6, 6, 6, 6);

			addFrame = new JFrame("SpringForm");
			addFrame.setLayout(new BorderLayout());
			addFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

			p.setOpaque(true);
			addFrame.add(p, BorderLayout.CENTER);

			JPanel buttons = new JPanel();
			buttons.setLayout(new BorderLayout());

			JButton cancel = new JButton("Avbryt");
			cancel.addActionListener(new Cancel());

			JButton apply = new JButton("Verkst�ll");
			apply.addActionListener(new Apply());

			JButton generate = new JButton("Generera");
			generate.addActionListener(new Generate());

			buttons.add(cancel, BorderLayout.LINE_START);
			buttons.add(apply, BorderLayout.LINE_END);
			buttons.add(generate, BorderLayout.SOUTH);

			addFrame.add(buttons, BorderLayout.SOUTH);
			addFrame.pack();
			addFrame.setVisible(true);
		}

		class Cancel implements ActionListener {

			public void actionPerformed(ActionEvent arg0) {
				addFrame.setVisible(false);
			}
		}

		class Apply implements ActionListener {

			public void actionPerformed(ActionEvent arg0) {
				mainTextField.setText("");
				if (database.checkBarcodeRegistered(textFields[1].getText())) {
					JOptionPane.showMessageDialog(null,
							"Streckkoden �r upptagen", "Felmeddelande",
							JOptionPane.ERROR_MESSAGE);
				} else if (textFields[0].getText().equals("")
						|| textFields[1].getText().equals("")
						|| textFields[2].getText().equals("")
						|| textFields[3].getText().equals("")
						|| textFields[4].getText().equals("")) {

					JOptionPane.showMessageDialog(null,
							"Var v�nlig fyll i alla uppgifter",
							"Felmeddelande", JOptionPane.ERROR_MESSAGE);

				} else {

					database.addUser(textFields[0].getText(),
							textFields[1].getText(), textFields[2].getText(),
							textFields[3].getText());

					StringBuilder sb = new StringBuilder();

					sb.append("Cykel�garen har lagts till\n");

					for (int i = 0; i < textFields.length; i++) {
						sb.append(labels[i]);
						sb.append(textFields[i].getText() + "\n");
					}

					mainTextField.setText(sb.toString());

					addFrame.setVisible(false);
				}
			}
		}

		class Generate implements ActionListener {

			private Random rand = new Random();
			private String newPin;
			private String newBarcode;

			public void actionPerformed(ActionEvent arg0) {

				StringBuilder pinBuilder = new StringBuilder();

				for (int i = 0; i < 4; i++) {
					int a = rand.nextInt(10);
					pinBuilder.append(String.valueOf(a));
				}

				newPin = pinBuilder.toString();
				textFields[0].setText(newPin);
				StringBuilder barcodeBuilder = new StringBuilder();

				for (int i = 0; i < 5; i++) {
					int b = rand.nextInt(10);
					barcodeBuilder.append(String.valueOf(b));
				}

				while (database.checkPinRegistered(newPin)) {
					pinBuilder.delete(0, 4);

					for (int i = 0; i < 4; i++) {
						int a = rand.nextInt(10);
						pinBuilder.append(String.valueOf(a));
					}

					newPin = pinBuilder.toString();
				}

				newBarcode = barcodeBuilder.toString();

				while (database.checkBarcodeRegistered(newBarcode)) {
					barcodeBuilder.delete(0, 5);

					for (int i = 0; i < 5; i++) {
						int b = rand.nextInt(10);
						barcodeBuilder.append(String.valueOf(b));
					}

					newBarcode = barcodeBuilder.toString();
				}

				textFields[1].setText(newBarcode);
			}
		}

	}

	class RemoveUser implements ActionListener {

		private JFrame removeFrame;
		private JTextField[] textFields;
		private String[] labels = { "Cykel�garens streckkodsnummer: ", "PIN: " };

		public void actionPerformed(ActionEvent e) {
			int numPairs = labels.length;

			textFields = new JTextField[labels.length];
			JPanel p = new JPanel(new SpringLayout());
			for (int i = 0; i < numPairs; i++) {
				JLabel l = new JLabel(labels[i], JLabel.TRAILING);
				p.add(l);
				JTextField textField = new JTextField(10);
				textFields[i] = textField;
				l.setLabelFor(textField);
				p.add(textField);
			}

			SpringUtilities.makeCompactGrid(p, numPairs, 2, 6, 6, 6, 6);

			removeFrame = new JFrame("SpringForm");
			removeFrame.setLayout(new BorderLayout());
			removeFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

			p.setOpaque(true);
			removeFrame.add(p, BorderLayout.CENTER);

			JPanel buttons = new JPanel();
			buttons.setLayout(new BorderLayout());

			JButton cancel = new JButton("Avbryt");
			cancel.addActionListener(new Cancel());
			//
			JButton apply = new JButton("Ta bort");
			apply.addActionListener(new Apply());

			buttons.add(cancel, BorderLayout.LINE_START);
			buttons.add(apply, BorderLayout.LINE_END);

			removeFrame.add(buttons, BorderLayout.SOUTH);
			removeFrame.pack();
			removeFrame.setVisible(true);
		}

		class Cancel implements ActionListener {

			public void actionPerformed(ActionEvent arg0) {
				removeFrame.setVisible(false);
			}

		}

		class Apply implements ActionListener {

			public void actionPerformed(ActionEvent arg0) {
				mainTextField.setText("");
				if (!database.checkBarcodeRegistered(textFields[0].getText())) {
					JOptionPane.showMessageDialog(null,
							"Det finns ingen cykel�gare med denna streckkod",
							"Felmeddelande", JOptionPane.ERROR_MESSAGE);
				} else if (textFields[0].getText().equals("")
						|| textFields[1].getText().equals("")) {

					JOptionPane.showMessageDialog(null,
							"Var v�nlig fyll i alla uppgifter",
							"Felmeddelande", JOptionPane.ERROR_MESSAGE);

				} else if (database.getUserByBarcode(textFields[0].getText())
						.getBikesInGarage() > 0) {
					if(JOptionPane
							.showConfirmDialog(
									null,
									"Cykel�garen har cyklar i garaget.\n�r du s�ker p� att du vill ta bort cykel�garen?",
									"Felmeddelande", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION) {
						delete();
					}

				} else {
					delete();
				}
			}
			
			public void delete() {
				User u = database.getUserByBarcode(textFields[0].getText());
				database.removeUser(textFields[0].getText());

				StringBuilder sb = new StringBuilder();

				sb.append("Cykel�garen har tagits bort \n");
				sb.append("Namn: ");
				sb.append(u.getName() + "\n");

				for (int i = 0; i < textFields.length; i++) {
					sb.append(labels[i]);
					sb.append(textFields[i].getText() + "\n");
				}

				mainTextField.setText(sb.toString());

				removeFrame.setVisible(false);
			}
		}
	}

	/**
	 * Returnerar en specifik cykel�gare.
	 * 
	 * @param barcode
	 *            cykel�garens streckkod
	 * @return cykel�garen
	 */
	public User getUser(String barcode) {
		return null;
	}

	public boolean running() {
		if (frame.isVisible()) {
			return true;
		}
		return false;
	}

	/**
	 * S�ker efter streckkoden f�r en viss cykel�gare.
	 * 
	 * @param name
	 *            cykel�garens namn
	 * @return cykel�garens streckkod
	 */
	public String findBarcodeWithName(String name) {
		return null;
	}

	/**
	 * Genererar en ny 4-siffrig PIN-kod.
	 * 
	 * @return ny PIN-kod
	 */
	public String GeneratePin() {
		return null;
	}

	public static void main(String[] args) {
		BicycleGarageDatabase database = new BicycleGarageDatabase();
		Operator main = new Operator(database);
		while (main.running()) {
			try {
				Thread.sleep(4000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			continue;
		}
		System.exit(0);

	}
}
