package entities;

import java.util.ArrayList;
import java.util.Scanner;
import service.Aluno;
import service.Professor;

public class Main 
{
    ArrayList<Predio> PredLista;
    ArrayList<Professor> ProfLista;
    ArrayList<Laboratorio> LabLista;
    ArrayList<String> EquipLista;
    
    ArrayList<Instituicao> InstAux;
    ArrayList<Disciplina> DiscAux;
    ArrayList<Turma> TurmaAux;
    ArrayList<Sala> SalaAux;
    ArrayList<Aluno> AlunoAux;
    ArrayList<Ocupa> OcupaAux;
    
    ArrayList<Instituicao> InstFim;
    ArrayList<Disciplina> DiscFim;
    ArrayList<Turma> TurmaFim;
    ArrayList<Sala> SalaFim;
    ArrayList<Aluno> alunoFim;
    ArrayList<Ocupa> OcupaFim;
    
    public Main()
    {   
        PredLista = new ArrayList();
        ProfLista = new ArrayList();
        LabLista = new ArrayList();
        EquipLista = new ArrayList();
        
        InstAux = new ArrayList();
        DiscAux = new ArrayList();
        TurmaAux = new ArrayList();
        SalaAux = new ArrayList();
        AlunoAux = new ArrayList();
        OcupaAux = new ArrayList();
        
        InstFim = new ArrayList();
        DiscFim = new ArrayList();
        TurmaFim = new ArrayList();
        SalaFim = new ArrayList();
        alunoFim = new ArrayList();
        OcupaFim = new ArrayList();
        
        cadastroInst();
        
    }
    
    public static void main(String[] args)
    {      
        //ArrayList<ArrayList> lista = new ArrayList<ArrayList>(); 
        new Main();
    }
    
    void cadastroInst()
    {
        Instituicao Inst = new Instituicao();
        Scanner cn = new Scanner(System.in);
	System.out.printf("informe o nome ");
        Inst.setNome(cn.nextLine());
        System.out.printf("Possue funfionamento integral?(true/false)");
        Inst.setIntegral(cn.nextBoolean());
        System.out.printf("Possue funfionamento Noturno?(true/false)");
        Inst.setNoturno(cn.nextBoolean());
        InstAux.add(Inst);
    }
    
    void InstFinal()
    {
       
        for(int a=0; a<InstAux.size(); a++)
        {
            Instituicao I = new Instituicao(); 
            for(int b=0; b<PredLista.size(); b++)
            {
                ArrayList <Predio> PredioLocal = new ArrayList();
                if(PredLista.get(b).getInstituicao().getNome().equals(InstAux.get(a).getNome()))
                {
                    PredioLocal.add(PredLista.get(b));
                }
                I.setNome(InstAux.get(a).getNome());
                I.setIntegral(InstAux.get(a).getIntegral());
                I.setNoturno(InstAux.get(a).getNoturno());
                I.setProfessores(ProfLista);
                I.setDisciplinas(DiscFim);
                I.setAlunos(alunoFim);
                I.setPredios(PredioLocal);
                InstFim.add(I);
            }
        }
    }

    void cadastroPredioSalas(ArrayList<Instituicao> InstAux)
    {
        int n;
        String Inst;
        Predio Pred = new Predio();
        Scanner cn = new Scanner(System.in);
        ArrayList<Sala> SalaPred = new ArrayList();
        System.out.printf("informe o nome da Instituicao ");
        Inst = cn.nextLine();
        for(int i=0; i<InstAux.size();i++)
        {
            if(Inst.equals(InstAux.get(i).getNome()))
            {
                Pred.setInstituicao(InstAux.get(i));
                break;
            }
        }
        System.out.printf("informe o nome do prédio ");
        Pred.setNome(cn.nextLine());
        System.out.printf("informe o numero de salas ");
        n = cn.nextInt();
        Pred.setQtd_salas(n);
        
        for(int i=0; i<n;i++)
        {
            int m;
            String select;
            System.out.printf("deseja adicionar sala ou labarotório? ");
            select = cn.nextLine();
            if("sala".equals(select))
            {
             Sala S = new Sala();
             S.setPredio(Pred);
             System.out.printf("informe o número de salas ");
             S.setIdSala(cn.nextInt());
             System.out.printf("informe a capacidade ");
             S.setCapacidade(cn.nextInt());
             System.out.printf("informe se tem arcondicionado (true/false)");
             S.setAr(cn.nextBoolean());
             System.out.printf("informe se tem projetor (true/false)");
             S.setProjetor(cn.nextBoolean());
             System.out.printf("informe se tem TV (true/false)");
             S.setTv(cn.nextBoolean());
             SalaAux.add(S);
             SalaPred.add(S);
            }
            else
            {
               Laboratorio L = new Laboratorio();
               L.setPredio(Pred);
               System.out.printf("informe o número de salas ");
               L.setIdSala(cn.nextInt());
               System.out.printf("informe a capacidade ");
               L.setCapacidade(cn.nextInt());
               System.out.printf("informe se tem arcondicionado (true/false)");
               L.setAr(cn.nextBoolean());
               System.out.printf("informe se tem projetor (true/false)");
               L.setProjetor(cn.nextBoolean());
               System.out.printf("informe se tem TV (true/false)");
               L.setTv(cn.nextBoolean());
               System.out.printf("informe a quantidade de computadores");
               L.setQtd_computadores(cn.nextInt());
               System.out.printf("informe a quantidade de equipamentos");
               m = cn.nextInt();
               L.setQtd_equipametos(m);
               for (int j=0; j<m; j++)
               {
                   System.out.printf("informe o tipo de equipamento");
                   EquipLista.add(cn.nextLine());
               }
               L.setTipos_equipamentos(EquipLista);
               SalaAux.add(L);
               SalaPred.add(L);
               LabLista.add(L);
            }  
        }
        Pred.setSala(SalaPred);
        PredLista.add(Pred);
    }
    
    void cadastroDiciplina()
    {
        Disciplina D = new Disciplina();
        Scanner cn = new Scanner(System.in);
        System.out.printf("informe o nome da disciplina ");
        D.setNome(cn.nextLine());
        System.out.printf("informe o código ");
        D.setCodigo(cn.nextInt());
        System.out.printf("informe a abordagem pedagógica ");
        D.setAbordagem_pedagogica(cn.nextLine());
        DiscAux.add(D);
    }
    
    void cadastroProfessor()
    {
        Professor P = new Professor();
        Scanner cn = new Scanner(System.in);
	System.out.printf("informe o nome ");
        P.setNome(cn.nextLine());
	System.out.printf("informe a matricula ");
        P.setMatricula(cn.nextLong());
	System.out.printf("informe a especialização ");
        P.setEspecializacao(cn.nextLine());
	System.out.printf("informe o salário ");
        P.setSalario(cn.nextFloat());
        ProfLista.add(P);
    }
    
    void cadastroAluno()
    {
        Aluno A = new Aluno();
        String modo, Disc, Turma;
        int i=1;
        ArrayList <Turma> TurmaLocal = new ArrayList();
        Scanner cn = new Scanner(System.in);
        System.out.printf("informe o nome");
        A.setNome(cn.nextLine());
        System.out.printf("informe a matrícula");
        A.setMatricula(cn.nextLong());
        System.out.printf("informe o curso");
        A.setCurso(cn.nextLine());
        while(i<42)
        {
            //arrumar o disc fim por aux 
            System.out.printf("informe a disciplina");
            Disc = cn.nextLine();
            for(int j=0; j<DiscFim.size();j++)
            {
                if(Disc.equals(DiscFim.get(j).getNome()))
                {
                    System.out.printf("informe a turma");
                    Turma = cn.nextLine();
                    for(int k=0; k<DiscFim.get(j).getTurmas().size();k++)
                    {
                        if(Disc.equals(DiscFim.get(j).getTurmas().get(k).getNome()))
                        {
                            TurmaLocal.add(DiscFim.get(j).getTurmas().get(k));
                            break;
                        }
                    }
                }
                break;
            }
            System.out.printf("deseja adicionar mais matérias?");
            modo = cn.nextLine();
            if ("s".equals(modo))
            {
                break;
            }
        }
    }
    
    void cadastroTurma()
    {
        int n, Sala;
        String Prof, Disc, Predio;
        Turma T = new Turma();
        ArrayList<Ocupa> OcupaCont = new ArrayList();
        Scanner cn = new Scanner(System.in); 
        System.out.printf("informe o nome da turma ");
        T.setNome(cn.nextLine());
        System.out.printf("informe o nome da disciplina ");
        Disc = cn.nextLine();
        for(int i=0; i<DiscAux.size();i++)
        {
            if(Disc.equals(DiscAux.get(i).getNome()))
            {
                T.setDisciplina(DiscAux.get(i));
                break;
            }
        }
        
        System.out.printf("informe o nome do professor");
        Prof = cn.nextLine();
        for(int j=0; j<ProfLista.size();j++)
        {
            if(Prof.equals(ProfLista.get(j).getNome()))
            {
                T.setProfessor(ProfLista.get(j));
                break;
            }
        }
        System.out.printf("informe o numero de alunos");
        T.setNumAlunos(cn.nextInt());
        System.out.printf("informe o numero de dias na semana");
        n = cn.nextInt();
        T.setNumDiasSema(n);
        
        for (int k=0; k<n; k++)
        {
            Ocupa O = new Ocupa();
            
            Predio = cn.nextLine();
            for(int l=0; l<PredLista.size();l++)
            {
                if(Predio.equals(PredLista.get(l).getNome()))
                {
                    Sala = cn.nextInt();
                    for(int a=0; a<PredLista.get(l).getSala().size();a++)
                    {
                        if(Sala == (PredLista.get(l).getSala().get(a).getIdSala()))
                        {
                            O.setSala(PredLista.get(l).getSala().get(a));
                            break;
                        }
                    }
                    break;
                }
            }
            
            O.setTurma(T);
            System.out.printf("informe o dia da semana dessa ocupacao");
            O.setDia(cn.nextLine());
            System.out.printf("informe o dia horario dessa ocupacao");
            O.setHorario(cn.nextInt());
            OcupaAux.add(O);
            OcupaCont.add(O);
        }
        T.setOcupacoes(OcupaCont);
        TurmaAux.add(T);
    }
}
