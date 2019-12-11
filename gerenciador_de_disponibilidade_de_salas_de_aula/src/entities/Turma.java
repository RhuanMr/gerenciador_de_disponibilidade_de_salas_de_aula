package entities;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import service.Aluno;
import service.Professor;

public class Turma 
{
    private String nome;
    private int numAlunos;
    private int numDiasSema;
    private int capacidadeMax;
    private Disciplina disciplina;
    private ArrayList <Aluno> alunos = new ArrayList<Aluno>();
    private Professor professor;
    private ArrayList <Ocupa> ocupacoes = new ArrayList<Ocupa>();

    public String getNome() 
    {
        return nome;
    }

    public void setNome(String nome) 
    {
        this.nome = nome;
    }
    
    public int getNumDiasSema() 
    {
        return numDiasSema;
    }

    public void setNumDiasSema(int numDiasSema) 
    {
        this.numDiasSema = numDiasSema;
    }

    public ArrayList<Ocupa> getOcupacoes() 
    {
        return ocupacoes;
    }

    public void setOcupacoes(ArrayList<Ocupa> ocupacoes) 
    {
        this.ocupacoes = ocupacoes;
    }
    
    public Disciplina getDisciplina() 
    {
        return disciplina;
    }

    public void setDisciplina(Disciplina disciplina) 
    {
        this.disciplina = disciplina;
    }

    public ArrayList<Aluno> getAlunos() 
    {
        return alunos;
    }

    public void setAlunos(ArrayList<Aluno> alunos) 
    {
        this.alunos = alunos;
    }

    public Professor getProfessor() 
    {
        return professor;
    }

    public void setProfessor(Professor professor) 
    {
        this.professor = professor;
    }
    
    public int getNumAlunos() 
    {
        return numAlunos;
    }

    public int getCapacidadeMax() 
    {
        return capacidadeMax;
    }

    public void setCapacidadeMax(int capacidadeMax) 
    {
        this.capacidadeMax = capacidadeMax;
    }

    public void setNumAlunos(int numAlunos) 
    {
        this.numAlunos = numAlunos;
    }
    
    public void criaDiretTurmaDisc()
    {
        File Insti = new File("diretorio//disc//"+this.disciplina.getNome(), this.nome);
        boolean jk = Insti.mkdir();
        File Al = new File(Insti, "aluno");//a cadasro aluno vai completar essse elemento
        boolean aa = Al.mkdir();
    }
    
    public String salvarLisDisc()//cria um arquivo .txt com o nome de todos os objhetos para facilitar a busca em pastas
    {
            
        try 
        {
        FileWriter lt = new FileWriter("diretorio//disc//"+this.disciplina.getNome()+"//lista.txt",true);
        PrintWriter plt = new PrintWriter(lt);
        plt.println(this.nome); 
        plt.flush();
        plt.close();//fecha a escrita
        lt.close();//fecha o arquivo
        }
            
        catch (IOException ex) 
        {
            Logger.getLogger(Turma.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "Lista salva";
    }
    
    public void criaDiretTurmaProf()
    {
        File Insti = new File("diretorio//inst//"+this.professor.getInst().getNome()+"//professor//"+this.professor.getNome(), this.nome);
        boolean jk = Insti.mkdir();
    }
    
    public String salvarLisProf()//cria um arquivo .txt com o nome de todos os objhetos para facilitar a busca em pastas
    {
            
        try 
        {
        FileWriter lt = new FileWriter("diretorio//inst//"+this.professor.getInst().getNome()+"//pessoa//professor//"+this.professor.getNome()+"//lista.txt", true);
        PrintWriter plt = new PrintWriter(lt);
        plt.println(this.nome);
                
        plt.flush();
        plt.close();//fecha a escrita
        lt.close();//fecha o arquivo
        }
            
        catch (IOException ex) 
        {
            Logger.getLogger(Turma.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "Lista salva";
    }
    
    public String Salvar()
    {
        try 
        {
            FileWriter fwD = new FileWriter("diretorio//disc//"+this.disciplina.getNome()+"//"+this.nome+"//"+this.nome+".txt");
            PrintWriter pwD = new PrintWriter(fwD);
            pwD.println(this.disciplina.getNome());
            pwD.println(this.professor.getNome());
            pwD.println(this.nome);
            pwD.println(this.numAlunos);
            pwD.println(this.numDiasSema);
            pwD.println(this.capacidadeMax);
            pwD.flush();
            pwD.close();
            fwD.close();
        } catch (IOException ex) 
        {
            Logger.getLogger(Turma.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try 
        {
            FileWriter fwP = new FileWriter("diretorio//inst//"+this.professor.getInst().getNome()+"//pessoa//professor//"+this.professor.getNome()+"//"+this.nome+"//"+this.nome+".txt");
            PrintWriter pwP = new PrintWriter(fwP);
            pwP.println(this.disciplina.getNome());
            pwP.println(this.professor.getNome());
            pwP.println(this.nome);
            pwP.println(this.numAlunos);
            pwP.println(this.numDiasSema);
            pwP.flush(); 
            pwP.close();
            fwP.close();
        } 
        catch (IOException ex) 
        {
            Logger.getLogger(Turma.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "Alteração feita";
    }
    
    public Turma cadTurma(Disciplina D, Professor P, ArrayList<Sala> SalaLocal, ArrayList<Ocupa> OcupaFora)//completar com as partes de alunos
    {
        int n, a=0, k;
        Sala sala;
        Scanner cn = new Scanner(System.in);
        Scanner cn2 = new Scanner(System.in);
        ArrayList <Ocupa> OcupaLocal = new ArrayList();
        this.setDisciplina(D);
        this.setProfessor(P);
        System.out.printf("informe o nome da turma ");
        this.setNome(cn.nextLine());
        this.setNumAlunos(0);
        System.out.printf("informe o número de dias na semana ");
        n = cn.nextInt();
        System.out.printf("informe a capacidade ");
        k = cn2.nextInt();
        this.setCapacidadeMax(k);
        this.setNumDiasSema(n);
        this.criaDiretTurmaDisc();
        this.criaDiretTurmaProf();
        this.salvarLisDisc();
        this.salvarLisProf();
        this.Salvar();
        for (int i=0; i<n; i++)
        {
            Ocupa O = new Ocupa();
            O.cadOcupa(this, SalaLocal);
            if(!OcupaFora.contains(O))
            {
                O.criaDiretOcupaSala();
                O.criaDiretOcupaTurma();
                O.salvarNomeArq();
                O.Salvar();
            }
            else
            {
                System.out.printf("Ocupação inválida ");
            }
        }
        return this;
    }
    
}
