package entities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import entities.Predio;

public class Sala 
{
	private int taxaocupa;// horarios de aula ocupados dividido por horarios de aula desocupados
    private int idSala;// numero da sala
    private int capacidade;
    private boolean ar;
    private boolean projetor;
    private boolean tv;
    private Predio predio;
    private ArrayList <Ocupa> ocupacoes = new ArrayList<Ocupa>();
    private ArrayList <Turma> turmas = new ArrayList<Turma>();
    
    public int calcularOcupacao() {
    	int hrocupados, hrtotais, hrlivres ;
    	hrocupados = this.ocupacoes.size();
    	hrtotais = this.getPredio().getQuantaula() * 5 ; 
    	hrlivres = hrtotais- hrocupados;
    	taxaocupa = hrocupados/hrlivres;
    	return taxaocupa;
    	
    	
    }
    public int getTaxaocupa() 
    {
        return taxaocupa;
    }

    public void setTaxaocupa(int taxaocupa) 
    {
        this.taxaocupa = taxaocupa;
    }
    
    public Predio getPredio() 
    {
        return predio;
    }

    public void setPredio(Predio predio) 
    {
        this.predio = predio;
    }
   
    public ArrayList<Ocupa> getOcupa() 
    {
        return ocupacoes;
    }

    public void setOcupa(ArrayList<Ocupa> ocupa) 
    {
        this.ocupacoes = ocupa;
    }

    public ArrayList<Turma> getTurma() 
    {
        return turmas;
    }

    public void setTurma(ArrayList<Turma> turma) 
    {
        this.turmas = turma;
    }

    public int getIdSala() 
    {
        return idSala;
    }

    public void setIdSala(int idSala) 
    {
        this.idSala = idSala;
    }

    public int getCapacidade() 
    {
        return capacidade;
    }

    public void setCapacidade(int capacidade) 
    {
        this.capacidade = capacidade;
    }

    public boolean isAr() 
    {
        return ar;
    }

    public void setAr(boolean ar) 
    {
        this.ar = ar;
    }

    public boolean isProjetor() 
    {
        return projetor;
    }

    public void setProjetor(boolean projetor) 
    {
        this.projetor = projetor;
    }

    public boolean isTv() 
    {
        return tv;
    }

    public void setTv(boolean tv) 
    {
        this.tv = tv;
    }
    public void criaDiretSala()
    {
        File Inst = new File("diretorio//inst//"+this.predio.getInstituicao().getNome()+"//"+this.predio.getNome(), "sala");//cria um diretorio geral para sala
        boolean jk = Inst.mkdir();
        File sala = new File("diretorio//inst//"+this.predio.getInstituicao().getNome()+"//"+this.predio.getNome()+"//sala",Integer.toString(this.idSala));//cria uma pasta para cada sala
        boolean kk = sala.mkdir();
    }
    
    public String salvarLis()//cria um arquivo .txt com o nome de todos os objhetos para facilitar a busca em pastas
    {
            
        try 
        {
            FileWriter lt = new FileWriter("diretorio//inst//"+this.predio.getInstituicao().getNome()+"//"+this.predio.getNome()+"//sala//lista.txt",true);
            PrintWriter plt = new PrintWriter(lt);
            plt.println(this.idSala);    
            plt.flush();
            plt.close();//fecha a escrita
            lt.close();//fecha o arquivo
        }
            
        catch (IOException ex) 
        {
                Logger.getLogger(Sala.class.getName()).log(Level.SEVERE, null, ex);
        }

            return "Lista salva";
    }
    
    public String Salvar()
    {
        
        try 
        {
            FileWriter fw = new FileWriter("diretorio//inst//"+this.predio.getInstituicao().getNome()+"//"+this.predio.getNome()+"//sala//"+Integer.toString(this.idSala)+"//"+Integer.toString(this.idSala)+".txt");
            PrintWriter pw = new PrintWriter(fw);
            pw.println(this.predio.getNome());
            pw.println(this.idSala);
            pw.println(this.capacidade);
            pw.println(this.ar);
            pw.println(this.projetor);
            pw.println(this.tv);
            pw.println(this.taxaocupa);
            pw.flush();
            pw.close();
            fw.close();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Sala.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return "Alteração feita";
    }
    
    public Sala cadSala(Predio Pred)
    {
        Scanner cn = new Scanner(System.in);
        this.setPredio(Pred);
        System.out.printf("informe o número da sala ");
        this.setIdSala(cn.nextInt());
        System.out.printf("informe a capacidade ");
        this.setCapacidade(cn.nextInt());
        System.out.printf("informe se tem arcondicionado (true/false)");
        this.setAr(cn.nextBoolean());
        System.out.printf("informe se tem projetor (true/false)");
        this.setProjetor(cn.nextBoolean());
        System.out.printf("informe se tem TV (true/false)");
        this.setTaxaocupa(this.calcularOcupacao());
        this.setTv(cn.nextBoolean());
        this.criaDiretSala();
        this.Salvar();
        this.salvarLis();
        cn.close();
        return this;
    }
}
