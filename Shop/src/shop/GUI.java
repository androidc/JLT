package shop;

// Графический интерфейс

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;


public class GUI extends JFrame implements ActionListener{
    
    // Интерфейс окна сделать в нестандартном виде
    
    public GUI(){
        
        
        
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
    
    
    
    @Override
    public void actionPerformed(ActionEvent ae){
        
    }
    
    
    
}
