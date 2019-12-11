package entities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Predio 
{
    private int qtd_salas;
    private String nome;
    private Instituicao instituicao;//faz a associação com instituições
    private ArrayList <Sala> salas = new ArrayList<Sala>();
    private double taxaocuppredio;
    private int numOcupa;
    private int numOcupaTotal;

    public int getNumOcupaTotal() 
    {
        return numOcupaTotal;
    }

    public void setNumOcupaTotal(int numOcupaTotal) 
    {
        this.numOcupaTotal = numOcupaTotal;
    }

    public int getNumOcupa() 
    {
        return numOcupa;
    }

    public void setNumOcupa(int numOcupa) 
    {
        this.numOcupa = numOcupa;
    }

    public ArrayList<Sala> getSalas() 
    {
        return salas;
    }

    public void setSalas(ArrayList<Sala> salas) 
    {
        this.salas = salas;
    }

    public double getTaxaocuppredio() 
    {
        return taxaocuppredio;
    }

    public void setTaxaocuppredio(double taxaocuppredio) {
        this.taxaocuppredio = taxaocuppredio;
    }
    
    public Instituicao getInstituicao() 
    {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) 
    {
        this.instituicao = instituicao;
    }
    

    public ArrayList<Sala> getSala() 
    {
        return salas;
    }

    public void setSala(ArrayList<Sala> sala) 
    {
        this.salas = sala;
    }

    public int getQtd_salas() 
    {
        return qtd_salas;
    }

    public void setQtd_salas(int qtd_salas) 
    {
        this.qtd_salas = qtd_salas;
    }

    public String getNome() 
    {
        return nome;
    }

    public void setNome(String nome) 
    {
        this.nome = nome;
    }
    
    public void criaDiretPredio()//cria uma pasta para cada objeto predio
    {
        File Inst = new File("diretorio//inst//"+this.instituicao.getNome(), this.nome);//cocatena strings para poder criar o caminho
        boolean jk = Inst.mkdir();
    }
    
    public String salvarLis()//cria um arquivo .txt com o nome de todos os objhetos para facilitar a busca em pastas
    {
            
        try 
        {
        FileWriter lt = new FileWriter("diretorio//inst//"+this.instituicao.getNome()+"//lista.txt",true);
        PrintWriter plt = new PrintWriter(lt);
        plt.println(this.nome);
                
        plt.flush();
        plt.close();//fecha a escrita
        lt.close();//fecha o arquivo
        }
            
        catch (IOException ex) 
        {
            Logger.getLogger(Predio.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "Lista salva";
    }
    
    public String Salvar()
    {
        try 
        {
            FileWriter fw = new FileWriter("diretorio//inst//"+this.instituicao.getNome()+"//"+this.nome+"//"+this.nome+".txt");
            PrintWriter pw = new PrintWriter(fw);
            pw.println(this.instituicao.getNome());
            pw.println(this.nome);
            pw.println(this.qtd_salas);
            pw.flush();
            pw.close();
            fw.close();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Predio.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "Alteração feita";
    }
    
    public Predio cadPred(Instituicao Inst)
    {
        int n, i=0;
        Scanner cn = new Scanner(System.in);
        Scanner cn2 = new Scanner(System.in);//scanner para limpar o buffer(estava dando erro antes)
        ArrayList<Sala> SalaPred = new ArrayList();//arrayList temporário para salas
        this.setInstituicao(Inst);
        System.out.printf("informe o nome do prédio ");
        this.setNome(cn.nextLine());
        System.out.printf("informe o numero de salas ");
        n = cn.nextInt();
        this.setQtd_salas(n);
        this.criaDiretPredio();
        this.Salvar();
        this.salvarLis();
        this.setTaxaocuppredio(0);
        this.setNumOcupa(0);
        do
        {
            String select;
            System.out.printf("deseja adicionar sala ou laboratorio? ");
            select = cn2.nextLine();//seleciona o tipo de sala
            switch(select)
            {
                case "sala":
                    Sala S = new Sala();
                    SalaPred.add(S.cadSala(this));
                    break;

                case "laboratorio":
                    Laboratorio L = new Laboratorio();
                    SalaPred.add(L.cadLab(this));
                    break;

                default:
                    System.out.println("Opção inválida.");
            }
            i++;
        }while(i<n);
        this.setSala(SalaPred);
        
        return this;
    }
            
}
