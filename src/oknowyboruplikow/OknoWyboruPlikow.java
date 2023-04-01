package oknowyboruplikow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;

public class OknoWyboruPlikow extends JFrame
{
    public OknoWyboruPlikow()
    {
        this.setTitle("Okno wyboru plikow");
        this.setBounds(250, 300, 300, 250);
        
        final JFileChooser wybor = new JFileChooser();
        wybor.setCurrentDirectory(new File(System.getProperty("user.dir")));
        wybor.setMultiSelectionEnabled(true);
        
        wybor.setAcceptAllFileFilterUsed(true);
        
        wybor.setFileFilter(new FiltrRozszerzen("Pola tekstowe", rozszerzeniaTekstowe));
        wybor.setFileFilter(new FileFilter(){
            @Override
            public boolean accept(File f) {
                return f.isDirectory();
            }

            @Override
            public String getDescription() {
                return "Katalogi";
            }
        });
        
        button1.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                int tmp = wybor.showOpenDialog(rootPane);
                if(tmp == 0)
                    stworzPlikZip(wybor.getSelectedFiles());
            }
        });
        
        button2.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                wybor.showDialog(rootPane, "Zipuj w tym miejscu");
            }
        });
        
        this.panelButtonow.add(button1);
        this.panelButtonow.add(button2);
        this.getContentPane().add(panelButtonow);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    private JPanel panelButtonow = new JPanel();
    private JButton button1 = new JButton("Otworz");
    private JButton button2 = new JButton("Zapisz");
    private String[] rozszerzeniaTekstowe = new String[]{".txt", ".xml", ".mf"};
    
    public void stworzPlikZip(File[] pliki)
    {
        for(int i = 0; i< pliki.length; i++)
            System.out.println(pliki[i].getPath());
    }
    
    private class FiltrRozszerzen extends FileFilter
    {
        public FiltrRozszerzen(String opis, String[] rozszerzenia)
        {
            this.opis = opis;
            this.rozszerzenia = rozszerzenia;
        }

        @Override
        public boolean accept(File f) {
            for(int i = 0; i < rozszerzenia.length; i++)
                if(f.getName().toLowerCase().endsWith(this.rozszerzenia[i]) || f.isDirectory())
                    return true;
            
            return false;
        }

        @Override
        public String getDescription() {
            return opis;
        }
        
        private String opis;
        private String[] rozszerzenia;
    }
    
    public static void main(String[] args) 
    {
        new OknoWyboruPlikow().setVisible(true);
    }
    
}
