package entities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Disciplina 
{
    private String nome;
    private long codigo;
   private ArrayList <Turma> turmas = new ArrayList<Turma>();
    private String grau;
    
    public ArrayList<Turma> getTurmas() 
    {
        return turmas;
    }

    public void setTurmas(ArrayList<Turma> turmas) 
    {
        this.turmas = turmas;
    }

    public String getNome() 
    {
        return nome;
    }

    public void setNome(String nome) 
    {
        this.nome = nome;
    }

    public long getCodigo() 
    {
        return codigo;
    }

    public void setCodigo(long codigo) 
    {
        this.codigo = codigo;
    }

    
    public void setGrau(String gr) {
    	this.grau = gr;	
    } 
    public String getGrau() {
    	return this.grau;
    }
    public void escolherGrau() 
    {
    	String gr;
        Scanner cn = new Scanner(System.in);
        System.out.println("Defina o Grau acadêmico da Disciplina");
        gr = cn.nextLine();
    	  	if(gr.equals("graduação")|| gr.equals("graduacao")) 
                {
    		setGrau("Graduação.");
    		
                }
                
    	  	if (gr.equals("extensão") || gr.equals("extensao")) 
                {
    			setGrau("Extensão.");
    	  	
    	  	}
    	  	
    	  	if (gr.equals("Lato Sensu")) 
                {
    	  		setGrau("Lato Sensu.");
                }
                
    	  	if(gr.equals("stricto sensu"))
                {
        		setGrau("Stricto Sensu.");
        		
        	}
    
    	  	else 
                {
                    System.out.println("Erro! Digite um Grau válido!");
    	  	}
    	System.out.println("O Grau da disciplina é " + this.grau );
    	      
    }
    
    public void criaDiretDisciplina()
    {
        File Insti = new File("diretorio//disc", this.nome);
        boolean jk = Insti.mkdir();
    }
    
    public String salvarLis()//cria um arquivo .txt com o nome de todos os objetos para facilitar a busca em pastas
    {
            
        try 
        {
            FileWriter lt = new FileWriter("diretorio//disc//lista.txt",true);
            PrintWriter plt = new PrintWriter(lt);
            plt.println(this.nome);

            plt.flush();
            plt.close();//fecha a escrita
            lt.close();//fecha o arquivo
        }
            
        catch (IOException ex) 
        {
            Logger.getLogger(Disciplina.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "Lista salva";
    }
    
    public String Salvar()
    {
        try 
        {
            FileWriter fw = new FileWriter("diretorio//disc//"+this.nome+"//"+this.nome+".txt");
            PrintWriter pw = new PrintWriter(fw);
            pw.println(this.getNome());
            pw.println(this.getCodigo());
            pw.println(this.getGrau());
            pw.flush();
            pw.close();
            fw.close();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Disciplina.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "Alteração feita";
    }
}