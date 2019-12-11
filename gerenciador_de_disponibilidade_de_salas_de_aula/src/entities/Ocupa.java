package entities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ocupa 
{
    private String dia;//informa o dia da semana em que a ocupação ocorre
    private int horario;
    private Turma turma;
    private Sala sala;

    public String getDia() 
    {
        return dia;
    }

    public void setDia(String dia) 
    {
        this.dia = dia;
    }

    public int getHorario() 
    {
        return horario;
    }

    public void setHorario(int horario) 
    {
        this.horario = horario;
    }

    public Turma getTurma() 
    {
        return turma;
    }

    public void setTurma(Turma turma) 
    {
        this.turma = turma;
    }

    public Sala getSala() 
    {
        return sala;
    }

    public void setSala(Sala sala) 
    {
        this.sala = sala;
    }
    
    public void criaDiretOcupaSala()
    {
        File Insti = new File("diretorio//inst//"+this.sala.getPredio().getInstituicao().getNome()+"//"+this.sala.getPredio().getNome()+"//sala//"+Integer.toString(this.sala.getIdSala()), "ocupa");
        boolean jk = Insti.mkdir();
    }
    
    public void criaDiretOcupaTurma()
    {
        File Insti = new File("diretorio//disc//"+this.turma.getDisciplina().getNome()+"//"+this.turma.getNome(), "ocupa");
        boolean jk = Insti.mkdir();
    }
    
    public String salvarNomeArq()
    {
        try 
        {
            FileWriter ltT = new FileWriter("diretorio//disc//"+this.turma.getDisciplina().getNome()+"//"+this.turma.getNome()+"//ocupa//lista.txt",true);
            PrintWriter pltT = new PrintWriter(ltT);
            
            pltT.println(this.getTurma().getNome()+"_"+this.getDia()+"_"+Integer.toString(this.getHorario()));
            pltT.flush();
            pltT.close();
            ltT.close();
            
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Ocupa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try 
        {
            FileWriter ltS = new FileWriter("diretorio//inst//"+this.sala.getPredio().getInstituicao().getNome()+"//"+this.sala.getPredio().getNome()+"//sala//"+Integer.toString(this.sala.getIdSala())+"//ocupa//lista.txt",true);
            PrintWriter pltS = new PrintWriter(ltS);
            pltS.println(this.getTurma().getNome()+"_"+this.getDia()+"_"+Integer.toString(this.getHorario()));
            pltS.flush();
            pltS.close();
            ltS.close();
            
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Ocupa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "Lista Salva";
    }
    
    public double taxaOcupa(int A, int B)
    {
        double valor;
        
        A = this.sala.getPredio().getNumOcupa();
        B = this.sala.getPredio().getNumOcupaTotal();
        
        valor = B/A;
        
        
        return valor;
    }
    
    public String Salvar()
    {
        try 
        {
            FileWriter fwT = new FileWriter("diretorio//disc//"+this.turma.getDisciplina().getNome()+"//"+this.turma.getNome()+"//ocupa//"+this.getTurma().getNome()+"_"+this.getDia()+"_"+Integer.toString(this.getHorario())+".txt",true);
            PrintWriter pwT = new PrintWriter(fwT);
            pwT.println(this.turma.getNome());
            pwT.println(this.sala.getIdSala());
            pwT.println(this.dia);
            pwT.println(this.horario);
            pwT.flush();
            pwT.close();
            fwT.close();
        } 
        catch (IOException ex)
        {
            Logger.getLogger(Ocupa.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try 
        {
            FileWriter fwS = new FileWriter("diretorio//inst//"+this.sala.getPredio().getInstituicao().getNome()+"//"+this.sala.getPredio().getNome()+"//sala//"+Integer.toString(this.sala.getIdSala())+"//ocupa//"+this.getTurma().getNome()+"_"+this.getDia()+"_"+Integer.toString(this.getHorario())+".txt",true);
            PrintWriter pwS = new PrintWriter(fwS);
            pwS.println(this.turma.getNome());
            pwS.println(this.sala.getIdSala());
            pwS.println(this.dia);
            pwS.println(this.horario);
            pwS.flush();
            pwS.close();
            fwS.close();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Ocupa.class.getName()).log(Level.SEVERE, null, ex);
        }
            
        return "Alteração feita";
    }
  
    public Ocupa cadOcupa(Turma T, ArrayList<Sala> SalaLocal)
    {
        int s, a=0;
        String pred;
        Scanner cn = new Scanner(System.in);
        Scanner cn2 = new Scanner(System.in);
        this.setTurma(T);
        System.out.printf("informe o nome do predio ");
        pred = cn.nextLine();

        for(int i=0; i<SalaLocal.size(); i++)//percorre a lista de salas recebida
        {
            System.out.printf("informe o numero da sala ");
            s = cn2.nextInt();
            if(s == (SalaLocal.get(i).getIdSala()))//verifica se os nomes das salas são iguais
            {
                if(pred.equals(SalaLocal.get(i).getPredio().getNome()))//verifica se o nome dos predios são iguais
                {
                    if(SalaLocal.get(i).getCapacidade() >= T.getCapacidadeMax())
                    {
                        this.setSala(SalaLocal.get(i));//seta o objeto da sala escolhido na lista
                        break;
                    }
                    else
                    {
                       System.out.printf("limite além da capacidade da sala ");
                       i--;
                    }
                    
                }
            }
        }
        System.out.printf("informe o dia da semana ");
        this.setDia(cn.nextLine());
        System.out.printf("informe informe o horário ");
        this.setHorario(cn2.nextInt());
        return this;
    }
}
    
