/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package consolacomandos;

import static consolacomandos.Main_Consola.comandos;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author jenniferbueso
 */
public class ConsolaComandos extends javax.swing.JFrame {
    
    public ConsolaComandos() {
        initComponents();
        Terminal.setCaretColor(Color.WHITE);
        
        SimpleDateFormat formato = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        
        Terminal.append("Last Login: " + formato.format(calendar.getTime()) + "\n");
        Terminal.append(comandos.directorioActual + "~ ");
        
    }

    public void procesarComando(String line) throws IOException {
       String[] commands = line.split("~");

        for (String commandLine : commands) {
            String[] parts = commandLine.trim().split(" ", 2);
            if (parts.length >= 1) {
                String command = parts[0].trim();
                String argument = "";
                if (parts.length > 1) {
                    argument = parts[1].trim();
                }

                command = command.toLowerCase();
                argument = argument.toLowerCase();
                
                switch (command) {
                    case "mkdir":
                        if (!argument.isEmpty() && !argument.contains(" ")) {
                            Terminal.append(comandos.mkdir(argument));
                        } else {
                            Terminal.append("\nFormato incorrecto. Uso correcto: Mkdir <nombre_directorio>\n\n");
                        }
                        break;
                    case "mfile":
                        if (!argument.isEmpty() && !argument.contains(" ")) {
                            Terminal.append(comandos.mFile(argument));
                        } else {
                            Terminal.append("\nFormato incorrecto. Uso correcto: Mfile <nombre_archivo>\n\n");
                        }
                        break;
                    case "rm":
                        if (!argument.isEmpty() && !argument.contains(" ")) {
                            Terminal.append(comandos.rm(argument));
                        } else {
                            Terminal.append("\nFormato incorrecto. Uso correcto: Rm <nombre>\n\n");
                        }
                        break;
                    case "cd":
                        if (!argument.isEmpty() && !argument.contains(" ")) {
                            Terminal.append(comandos.cd(argument));
                        } else {
                            Terminal.append("\nFormato incorrecto. Uso correcto: Cd <nombre_carpeta>\n\n");
                        }
                        break;
                    case "...":
                        if (argument.isEmpty()) {
                            Terminal.append(comandos.cdRegresar());
                        } else {
                            Terminal.append("\nError: el comando <...> no debe tener argumentos.\n\n");
                        }
                        break;
                    case "dir":
                        if (argument.isEmpty()) {
                            Terminal.append(comandos.dir());
                        } else {
                            Terminal.append("\nError: el comando <...> no debe tener argumentos.\n\n");
                        }
                        break;
                    case "date":
                        if (argument.isEmpty()) {
                            Terminal.append(comandos.date());
                        } else {
                            Terminal.append("\nError: el comando <...> no debe tener argumentos.\n\n");
                        }
                        break;
                    case "time":
                        if (argument.isEmpty()) {
                            Terminal.append(comandos.time());
                        } else {
                            Terminal.append("\nError: el comando <...> no debe tener argumentos.\n\n");
                        }
                        break;
                    case "escribir":
                        String folderName = "";
                        String text = "";
                        if (argument.equals("<wr>")) {
                            Terminal.append("\nPor favor, ingresa el nombre de la carpeta y el texto que deseas escribir en el formato: <nombre_carpeta> <texto>\n\n");
                            String inputArgument = getInput();
                            String[] args = inputArgument.split(" ", 2);
                            if (args.length == 2) {
                                folderName = args[0];
                                text = args[1];
                            }
                        } else {
                            String[] args = argument.split(" ", 2);
                            if (args.length == 2) {
                                folderName = args[0];
                                text = args[1];
                            }
                        }

                        folderName = folderName.replaceAll("[<>]", "");
                        text = text.replaceAll("[<>]", "");

                        if (!folderName.isEmpty() && !text.isEmpty()) {
                            Terminal.append(comandos.escribir(folderName, text));
                        } else {
                            Terminal.append("\nFormato incorrecto. Uso correcto: Escribir <nombre_carpeta> <texto>\n\n");
                        }
                        break;
                    case "leer":
                        if (!argument.isEmpty() && !argument.contains(" ")) {
                            Terminal.append(comandos.leer(argument));
                        } else {
                            Terminal.append("\nFormato incorrecto. Uso correcto: Leer <nombre_archivo>\n\n");
                        }
                        break;
                    case "help":
                        Terminal.append(comandos.help());
                 }
            }
        }
    }
    
    private String getInput() {
        String input = Terminal.getText().trim();
        Terminal.setText("\n");
        return input;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Terminal = new javax.swing.JTextArea();
        Fondo = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Terminal");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPane1.setBackground(new java.awt.Color(30, 30, 30));
        jScrollPane1.setBorder(null);

        Terminal.setBackground(new java.awt.Color(30, 30, 30));
        Terminal.setColumns(20);
        Terminal.setFont(new java.awt.Font("Menlo", 1, 14)); // NOI18N
        Terminal.setForeground(new java.awt.Color(255, 255, 255));
        Terminal.setLineWrap(true);
        Terminal.setRows(5);
        Terminal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                TerminalKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(Terminal);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 840, 450));

        Fondo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Elementos/Terminal.png"))); // NOI18N
        jPanel1.add(Fondo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void TerminalKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TerminalKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            evt.consume(); 
            String[] lines = Terminal.getText().split("\n");
            String lastLine = lines[lines.length - 1];
            try {
                procesarComando(lastLine);
            } catch (IOException ex) {
                Terminal.append("\nError al procesar el comando: " + ex.getMessage() + "\n\n");
            }
            Terminal.append(comandos.directorioActual + "~ ");
        }
    }//GEN-LAST:event_TerminalKeyPressed

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
            java.util.logging.Logger.getLogger(ConsolaComandos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsolaComandos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsolaComandos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsolaComandos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsolaComandos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Fondo;
    private javax.swing.JTextArea Terminal;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
