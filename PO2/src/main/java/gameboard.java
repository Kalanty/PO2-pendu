
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author maxim
 */
public class gameboard extends javax.swing.JFrame {
    ArrayList<String> histLettres = new ArrayList<>();
    private static String[] motsMysteres = 
        {"jeux", "espoir", "apprendre", "joie", "patate",
        "poulet", "camion", "voiture", "ordinateur", "programmer"};
    private String joueur1;
    private String motChoisi;
    private StringBuilder motH;
    private int nbErreurs = 0;
    private int pointage =0;
    private final String IMAGE_DIRECTORY = "/Images/";
    private final String IMAGE_BASE_NAME = "hangman";
    private final String IMAGE_TYPE = ".png";
    String texteMotMystere;
    private Component frame;
    
    /**
     * Creates new form gameboard
     */
    public gameboard() {
        initComponents();
        startWindow();
        motCache();
        nomDuJoueur.setEditable(false);
        score.setEditable(false);
        mot.setEditable(false);
        //creation d'un String pour y stocker le resultat de la comparaison
        //entre mon motChoisi et ma liste de lettre essayer.
        texteMotMystere = motMystereCache(motChoisi, histLettres);
        //afficher le String apres chaque comparaison
        mot.setText(texteMotMystere);   
        addCloseWindowListener();
        refreshImage();
    }
    //Demande le nom au joueur
    public void startWindow(){
        int rdy = 0;
        String username = "";
        while((rdy == 0)){
            username = (String)JOptionPane.showInputDialog(null, "Commencer par entrer le nom du joueur.");
            
            if ((username != null)&&(username.length()> 0)){
                rdy++;
            }
            else{
                rdy = 0;
            }
            System.out.println(rdy);
        }
        System.out.println(username);
        nomDuJoueur.setText(username);
        
    }
    //lorsque la partie est terminé
    public void endWindow(){
        int dialogButton = 0;
        Object[] options = { "Oui", "Non" };
        int dialogResult = JOptionPane.showOptionDialog
            (null,
            "Voulez vous rejouer une partie?",
            "Fin de la partie",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]);

        if(dialogResult == JOptionPane.YES_OPTION){
            histLettres.clear();
            nbErreurs = 0;
            motCache();
            activerBouton();
            texteMotMystere = motMystereCache(motChoisi, histLettres);
            mot.setText(texteMotMystere);
            refreshImage();
        }
        else{
            quitWindow();
        }
    }
    //invite confirmation avant de quitter
    public void quitWindow(){
        int dialogButton = 0;
        Object[] options = { "Oui", "Non" };
        int dialogResult = JOptionPane.showOptionDialog
            (null,
            "Voulez vous vraiment quiter le jeu?", "Fin de la partie",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]);

        if(dialogResult == JOptionPane.YES_OPTION){
            System.exit(0);
        }
        else{
            endWindow();
        }
    }
    /** Returns an ImageIcon, or null if the path was invalid. */
    protected ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = getClass().getClassLoader().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
    
    private void activerBouton(){
        var boutons = Clavier.getComponents();
        for(int i = 0; i< boutons.length; i++){
            if(boutons[i] instanceof javax.swing.JButton){
                boutons[i].setEnabled(true);
            }
        }
    }
    
    //telecharger l'image en fonction du path dans l'endroit approprié
    private void refreshImage(){
        String path;
        if(nbErreurs == 6){
            path = IMAGE_BASE_NAME + "_lose" + IMAGE_TYPE;
        }
        else if (texteMotMystere.indexOf("_") == -1){
            path = IMAGE_BASE_NAME + "_win" + IMAGE_TYPE;
        }
        else{
            path = IMAGE_BASE_NAME + "_" + nbErreurs + IMAGE_TYPE;
        }
        ImageIcon ico = createImageIcon(path);
        imageLabel.setIcon(ico);
        getContentPane().add(imageLabel);
    }
    
    public void lettreTapper(String lettre){
        histLettres.add(lettre);
        System.out.println(histLettres);
        // Si la lettre n'est pas dans le mot
        //incrementation de la variable nbErreurs
        if(estErreur(motChoisi, lettre)) {
            nbErreurs++;
            refreshImage();
        }
        else{
            ajusterPointage(1);
        }
        
        System.out.println(nbErreurs + "/" + nbEssais());
        //creation du String texteMotMystere qui prend la valeur
        texteMotMystere = motMystereCache(motChoisi, histLettres);
        //s'il n'y a plus de "_" dans la varible texteMotMystere, 
        // YOU WIN!!!!!
        //effacer l'historique de lettres
        //selection d'un nouveau mot dans la banque de mot
        mot.setText(texteMotMystere); 

        if(texteMotMystere.indexOf("_") == -1) {
            System.out.println("YOU ROCK! YOU WIN! CHICKEN DINNER!");
            ajusterPointage(5);
            refreshImage();
            endWindow();

        }else if(nbErreurs == 6){
            System.out.println("ECHEC, VOUS AVEZ PERDU LA PARTIE!!!");
            endWindow();
        }
        
    }
    //Selection du mot mystere dans la banque de mot.
    //Force le mot choisi en majuscule pour eviter les conflits avec le clavier.
    //afficher le mot dans la console.
    public void motCache(){
        motChoisi = motsMysteres[(int) (Math.random() * motsMysteres.length)].toUpperCase();
        System.out.println(motChoisi);
    }
    //methode pour incrementer le score du joueur par le nombre
    //en parametre
    private int ajusterPointage(int ajustement){
        pointage = pointage + ajustement;
        //System.out.println(pointage);
        String scoreJoueur = Integer.toString(pointage);
        score.setText(scoreJoueur);        
        return pointage;
    }
       
    //creation d'une methode generique pour comparer un mot a un tableau de lettre
    //Boucle qui separe le mot lettre par lettre
    private String motMystereCache(String word, ArrayList<String> lettres) {       
        StringBuilder chaine = new StringBuilder();
        //boucle pour remplacer tout les lettres du motChoisi par des "_ "
        for(int i = 0; i < word.length(); i++){
            String lettreCourante = String.valueOf(word.charAt(i));
            //Si les lettres du mot fais partir de mes lettres testées,
            //remplacer la lettre dans le mot
            if(lettres.indexOf(lettreCourante)>-1){
                chaine.append(lettreCourante);
             }
            // laisser le "_ " si la lettre n'est pas dans le mot
            else{
                chaine.append("_ ");
            }
        }
        return chaine.toString();
    }
    // Invite l'utilisateur à confirmer avant de quitter la fenêtre.
    private void addCloseWindowListener() {
	// REMARQUE : Doit être DO_NOTHING_ON_CLOSE pour que l'invite fonctionne
	// correctement
	setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                quitWindow();	
            }
	});
    }    
    // methode qui retourne faux (-1) si une lettre ne fais pas partie d'un mot
    private boolean estErreur(String word, String lettre) {
        return word.indexOf(lettre) == -1;
    }
    //methode qui retourne la quantité de lettre essayés
    private int nbEssais() {
        return histLettres.size();
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Dessin = new javax.swing.JPanel();
        imageLabel = new javax.swing.JLabel();
        rightPanel = new javax.swing.JPanel();
        labelJoueur = new javax.swing.JLabel();
        nomDuJoueur = new javax.swing.JTextField();
        labelScore = new javax.swing.JLabel();
        score = new javax.swing.JTextField();
        labelMotMystere = new javax.swing.JLabel();
        mot = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();
        Clavier = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        mbFichier = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        miQuitter = new javax.swing.JMenuItem();
        mbAide = new javax.swing.JMenu();
        aProposDe = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        Dessin.setBackground(new java.awt.Color(255, 255, 255));

        imageLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        imageLabel.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        javax.swing.GroupLayout DessinLayout = new javax.swing.GroupLayout(Dessin);
        Dessin.setLayout(DessinLayout);
        DessinLayout.setHorizontalGroup(
            DessinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, DessinLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        DessinLayout.setVerticalGroup(
            DessinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(imageLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
        );

        labelJoueur.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelJoueur.setText("Nom du joueur :");

        nomDuJoueur.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        labelScore.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelScore.setText("Score :");

        score.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        score.setText("0");

        labelMotMystere.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelMotMystere.setText("Mot mystere");

        mot.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jButton4.setText("Débuter Partie");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                debuterPartieActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout rightPanelLayout = new javax.swing.GroupLayout(rightPanel);
        rightPanel.setLayout(rightPanelLayout);
        rightPanelLayout.setHorizontalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightPanelLayout.createSequentialGroup()
                .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(rightPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelMotMystere)
                            .addComponent(labelScore)
                            .addComponent(labelJoueur, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nomDuJoueur)
                            .addComponent(score)
                            .addComponent(mot)))
                    .addGroup(rightPanelLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jButton4)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        rightPanelLayout.setVerticalGroup(
            rightPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rightPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelJoueur)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(nomDuJoueur, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelScore)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(score, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelMotMystere)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(mot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton4)
                .addGap(33, 33, 33))
        );

        jButton1.setText("Q");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clavierActionPerformed(evt);
            }
        });

        jButton2.setText("W");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clavierActionPerformed(evt);
            }
        });

        jButton3.setText("E");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clavierActionPerformed(evt);
            }
        });

        jButton5.setText("R");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clavierActionPerformed(evt);
            }
        });

        jButton6.setText("T");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clavierActionPerformed(evt);
            }
        });

        jButton7.setText("Y");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clavierActionPerformed(evt);
            }
        });

        jButton8.setText("U");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clavierActionPerformed(evt);
            }
        });

        jButton9.setText("I");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clavierActionPerformed(evt);
            }
        });

        jButton10.setText("O");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clavierActionPerformed(evt);
            }
        });

        jButton11.setText("A");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clavierActionPerformed(evt);
            }
        });

        jButton12.setText("S");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clavierActionPerformed(evt);
            }
        });

        jButton13.setText("D");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clavierActionPerformed(evt);
            }
        });

        jButton14.setText("F");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clavierActionPerformed(evt);
            }
        });

        jButton15.setText("G");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clavierActionPerformed(evt);
            }
        });

        jButton16.setText("H");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clavierActionPerformed(evt);
            }
        });

        jButton17.setText("J");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clavierActionPerformed(evt);
            }
        });

        jButton18.setText("K");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clavierActionPerformed(evt);
            }
        });

        jButton19.setText("L");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clavierActionPerformed(evt);
            }
        });

        jButton20.setText("Z");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clavierActionPerformed(evt);
            }
        });

        jButton21.setText("X");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clavierActionPerformed(evt);
            }
        });

        jButton22.setText("C");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clavierActionPerformed(evt);
            }
        });

        jButton23.setText("V");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clavierActionPerformed(evt);
            }
        });

        jButton24.setText("B");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clavierActionPerformed(evt);
            }
        });

        jButton25.setText("N");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clavierActionPerformed(evt);
            }
        });

        jButton26.setText("P");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clavierActionPerformed(evt);
            }
        });

        jButton27.setText("M");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clavierActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout ClavierLayout = new javax.swing.GroupLayout(Clavier);
        Clavier.setLayout(ClavierLayout);
        ClavierLayout.setHorizontalGroup(
            ClavierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ClavierLayout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(ClavierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addGroup(ClavierLayout.createSequentialGroup()
                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ClavierLayout.createSequentialGroup()
                        .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ClavierLayout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addGap(4, 4, 4)
                        .addComponent(jButton2)
                        .addGap(4, 4, 4)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
        );

        ClavierLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jButton1, jButton10, jButton11, jButton12, jButton13, jButton14, jButton15, jButton16, jButton17, jButton18, jButton19, jButton2, jButton20, jButton21, jButton22, jButton23, jButton24, jButton25, jButton26, jButton27, jButton3, jButton5, jButton6, jButton7, jButton8, jButton9});

        ClavierLayout.setVerticalGroup(
            ClavierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ClavierLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(ClavierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3)
                    .addComponent(jButton5)
                    .addComponent(jButton6)
                    .addComponent(jButton7)
                    .addComponent(jButton8)
                    .addComponent(jButton9)
                    .addComponent(jButton10)
                    .addComponent(jButton26))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ClavierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton11)
                    .addComponent(jButton12)
                    .addComponent(jButton13)
                    .addComponent(jButton14)
                    .addComponent(jButton15)
                    .addComponent(jButton16)
                    .addComponent(jButton17)
                    .addComponent(jButton18)
                    .addComponent(jButton19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ClavierLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton20)
                    .addComponent(jButton21)
                    .addComponent(jButton22)
                    .addComponent(jButton23)
                    .addComponent(jButton24)
                    .addComponent(jButton25)
                    .addComponent(jButton27))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        mbFichier.setText("Fichier");

        jMenuItem1.setText("Nouvelle Partie");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                debuterPartieActionPerformed(evt);
            }
        });
        mbFichier.add(jMenuItem1);

        miQuitter.setText("Quitter");
        miQuitter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miQuitterActionPerformed(evt);
            }
        });
        mbFichier.add(miQuitter);

        jMenuBar1.add(mbFichier);

        mbAide.setText("Aide");

        aProposDe.setText("A Propos De");
        aProposDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aProposDeActionPerformed(evt);
            }
        });
        mbAide.add(aProposDe);

        jMenuBar1.add(mbAide);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Dessin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(Clavier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addComponent(rightPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Dessin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(Clavier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
   
    private void miQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miQuitterActionPerformed
        quitWindow();
    }//GEN-LAST:event_miQuitterActionPerformed

    private void aProposDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aProposDeActionPerformed
        // TODO add your handling code here:
        
        //int dialogResult = JOptionPane.showMessageDialog(null, "contenu txt", "Titre", HEIGHT, icon);
        //int dialogResult = JOptionPane.showMessageDialog(null, "contenu txt", "Titre", HEIGHT, icon));
        JOptionPane.showMessageDialog(frame,
        "Voici une liste de règlement pour le bonnehomme pendu:\n\n"
        + "Vous devez commencer par entrer votre nom, ce qui doit déjà être fait,\n\n"
        + "Un mot aléatoire à été choisi, il vous suffit d'essayer des lettres\n"
        + "pour voir si elles font partie du mot mystère.\n"
        + "Chaque erreur fera changer l'image et ajouter un membre au bonnehomme pendu.\n\n"
        + "Si vous avez trouvé le mot mystère, vous avez l'option de rejouer ou arrêter\n"
        + "Si vous cummulez 6 erreurs, vous aurez l'option de rejouer ou arrêter.",
        "Instruction du jeu",
        JOptionPane.PLAIN_MESSAGE);
        //int dialogResult = JOptionPane.showMessageDialog(frame, Dessin, joueur1, HEIGHT);
        //(null, "Voici une petite liste des règlements pour le jeu du bonnehomme pendu :"
          //      + "/n Vous devez commencer par entrer le nom du joueur", "Règlement du jeu!", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

    }//GEN-LAST:event_aProposDeActionPerformed
    
    private void clavierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clavierActionPerformed
        //creation de la variable bouton
        //lors d'un clic de la souris sur le clavier, prendre son contenu txt et l'ajouter
        //au ArrayList histLettres
        var bouton = (javax.swing.JButton)evt.getSource();
        bouton.setEnabled(false);
        lettreTapper(bouton.getText());
        
        //rend impossible l'utilisation des boutons avec la première
        //utilisation
    }//GEN-LAST:event_clavierActionPerformed
    
    
    private void debuterPartieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_debuterPartieActionPerformed
        // TODO add your handling code here:
        histLettres.clear();
        nbErreurs = 0;
        motCache();
        activerBouton();
        texteMotMystere = motMystereCache(motChoisi, histLettres);
        mot.setText(texteMotMystere);
        refreshImage();
        pointage = 0;
        String scoreJoueur = Integer.toString(pointage);
        score.setText(scoreJoueur);        

        //popup de la fenetre pour nom du joueur.
        //reinitialisation de la variable score.
        //selection du mot mystere.
    }//GEN-LAST:event_debuterPartieActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Clavier;
    private javax.swing.JPanel Dessin;
    private javax.swing.JMenuItem aProposDe;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JLabel labelJoueur;
    private javax.swing.JLabel labelMotMystere;
    private javax.swing.JLabel labelScore;
    private javax.swing.JMenu mbAide;
    private javax.swing.JMenu mbFichier;
    private javax.swing.JMenuItem miQuitter;
    private javax.swing.JTextField mot;
    private javax.swing.JTextField nomDuJoueur;
    private javax.swing.JPanel rightPanel;
    private javax.swing.JTextField score;
    // End of variables declaration//GEN-END:variables
}
