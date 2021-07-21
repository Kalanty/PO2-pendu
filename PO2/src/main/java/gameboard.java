
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.util.ArrayList;

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
            {"jeux", "espoir", "apprendre", "joie", "patate"
            , "poulet", "camion", "voiture", "ordinateur", "programmer"};
    private String joueur1;
    private String motChoisi;
    private StringBuilder motH;
    private int nbErreurs = 0;

    /**
     * Creates new form gameboard
     */
    public gameboard() {
       
        initComponents();
        //Demander le nom du joueur
        motCache();
        //creation d'un String pour y stocker le resultat de la comparaison
        //entre mon motChoisi et ma liste de lettre essayer.
        String texteMotMystere = motMystereCache(motChoisi, histLettres);
        //afficher le String apres chaque comparaison
        mot.setText(texteMotMystere);    
        
        //joueur1 = nomDuJoueur.getText();
        
        //System.out.println(joueur1);
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

        javax.swing.GroupLayout DessinLayout = new javax.swing.GroupLayout(Dessin);
        Dessin.setLayout(DessinLayout);
        DessinLayout.setHorizontalGroup(
            DessinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 404, Short.MAX_VALUE)
        );
        DessinLayout.setVerticalGroup(
            DessinLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 271, Short.MAX_VALUE)
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
        mot.setText("jTextField3");

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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(Dessin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74))
                    .addComponent(Clavier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addComponent(rightPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(Dessin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Clavier, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(rightPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    public void motCache(){
        //Selection du mot mystere dans la banque de mot.
        //Force le mot choisi en majuscule pour eviter les conflits avec le clavier.
        //afficher le mot dans la console.
        motChoisi = motsMysteres[(int) (Math.random() * motsMysteres.length)].toUpperCase();
        System.out.println(motChoisi);
        
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
    // methode qui donne -1 si une lettre ne fais pas partie d'un mot
    private boolean estErreur(String word, String lettre) {
        return word.indexOf(lettre) == -1;
    }
    //methode qui retourne la quantité de lettre essayés
    private int nbEssais() {
        return histLettres.size();
    }
    
    private void miQuitterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miQuitterActionPerformed
        System.out.println("Fermeture de l'application");
        System.exit(0);
    }//GEN-LAST:event_miQuitterActionPerformed

    private void aProposDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aProposDeActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_aProposDeActionPerformed
    
    private void clavierActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clavierActionPerformed
        //creation de la variable bouton
        //lors d'un clic de la souris sur le clavier, prendre son contenu txt et l'ajouter
        //au ArrayList histLettres
        var bouton = (javax.swing.JButton)evt.getSource();
        lettreTapper(bouton.getText());
        //rend impossible l'utilisation des boutons avec la première
        //utilisation
        bouton.setEnabled(false);
    }//GEN-LAST:event_clavierActionPerformed
    //private void activerBouton(){
     //   var bouton = (javax.swing.JButton)getSource();
     //   bouton.setEnabled(false);

    //}
    
    public void lettreTapper(String lettre){
        histLettres.add(lettre);
        System.out.println(histLettres);        
        score.setText(histLettres.toString());
        // SI la methode estErreur() qui compare les lettres avec le mot
        //incrementation de la variable nbErreurs
        if(estErreur(motChoisi, lettre)) {
            nbErreurs++;
        }
        System.out.println(nbErreurs + "/" + nbEssais());
        //creation du String texteMotMystere qui prend la valeur
        String texteMotMystere = motMystereCache(motChoisi, histLettres);
        //s'il n'y a plus de "_" dans la varible texteMotMystere, 
        // YOU WIN!!!!!
        //effacer l'historique de lettres
        //selection d'un nouveau mot dans la banque de mot
        
        if(texteMotMystere.indexOf("_") == -1) {
            System.out.println("YOU ROCK! YOU WIN! CHICKEN DINNER!");
            histLettres.clear();
            
            motCache();
            texteMotMystere = motMystereCache(motChoisi, histLettres);
            mot.setText(texteMotMystere);
        }
        
        mot.setText(texteMotMystere); 
    }
    
    private void debuterPartieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_debuterPartieActionPerformed
        // TODO add your handling code here:
        this.dispose();
        System.out.println("Debuter nouvelle partie.");
        new gameboard().setVisible(true);
        //popup de la fenetre pour nom du joueur.
        //reinitialisation de la variable score.
        //selection du mot mystere.
    }//GEN-LAST:event_debuterPartieActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(gameboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(gameboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(gameboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(gameboard.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new gameboard().setVisible(true);
            }
        });
        /*
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if(e.getID() == KeyEvent.KEY_TYPED) {
                    String lettre = String.valueOf(e.getKeyChar()).toUpperCase();
                    String lettresAcceptes = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
                    
                    if(lettresAcceptes.contains(lettre)) {
                        System.out.println(lettre);
                        //histLettres.add(lettre);
                        //lettreTapper(lettre);
                    }
                }
                return false;
            }
        });*/
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel Clavier;
    private javax.swing.JPanel Dessin;
    private javax.swing.JMenuItem aProposDe;
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
