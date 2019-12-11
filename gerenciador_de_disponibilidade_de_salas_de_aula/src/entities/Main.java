package entities;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import service.Aluno;
import service.Professor;

public class Main // classe main auxiliar para mexer em elementos não estáticos
{
    ArrayList<Predio> PredLista;
    ArrayList<Professor> ProfLista;
    ArrayList<Laboratorio> LabLista;
    ArrayList<String> EquipLista;

    ArrayList<Instituicao> InstFim;//listas que serão salvas(possivelmente serão modificadas)
    ArrayList<Disciplina> DiscFim;
    ArrayList<Turma> TurmaFim;
    ArrayList<Sala> SalaFim;
    ArrayList<Aluno> AlunoFim;
    ArrayList<Ocupa> OcupaFim;
    
    public void LerArqDisc()
    {
        
    }
    
    public void menu()//método para printar na tela as opções do menu
    {
        System.out.println("Cadastro");
        System.out.println("0. Fim");
        System.out.println("1. Instituicao");
        System.out.println("2. Predio");
        System.out.println("3. Sala");
        System.out.println("4. Professor");
        System.out.println("5. Disciplina");
        System.out.println("6. Turma");
        System.out.println("7. Aluno");
        System.out.println("Opcao: ");
    }
    
    public void funcMenu()//função que instancia o menu
    {
        int opcao;
        CriarArq CI = new CriarArq();
        CI.DiretInst(InstFim);
        
        //LerArqInst();
        Scanner entrada = new Scanner(System.in);
        
        do
        {//loop para repetir o menu até que o usuário queira sair
            menu();
            opcao = entrada.nextInt();

            switch(opcao)
            {
            case 1:
                
                InstFim.add(cadastroInst());//função que cadastra Inst => Pred => Sala
                break;//volta pra dentro do laço do

            case 2://só pode ser usada depois da primeira execução de cadstro inst
                if(!InstFim.isEmpty())
		{
                    cadastroPredio();//função que cadastra só pred => sala
                    break;//acho que será nescessário colocar uyma condição
                }
            case 3:
                if(!PredLista.isEmpty())		
                {
                    cadastroSala();//função que cadastra apenas sala
                    break;
                }
            case 4:// só pode ser executada depois de cadastro Inst
                if(!SalaFim.isEmpty())
		{
                    cadastroProfessor();//função que cadastra professor
                    break;
                }
            case 5://só pode ser executado depois de castro professor
                if(!ProfLista.isEmpty())
                {
                    cadastroDiciplina();//cadastra Disciplina => Turma => Ocupação
                    break;
                }
            case 6://só pode ser executada se já tiver uma disciplina cadastrada
                if (!DiscFim.isEmpty())
                {
                    cadastroTurma();//cadastra turm => Ocupação
                    break;
                }
            case 7://só pode ser executado depois que CadInst => cadProf => CadDisc
                if(!DiscFim.isEmpty())
                {
                    cadastroAluno();//cadastra um aluno e suas turmas
                    break;
                }
            default:
                System.out.println("Opção inválida.");
            }
        }while(opcao != 0); 
    }
    
    public Main()
    {   
        PredLista = new ArrayList();//instanciando os arrayLists
        ProfLista = new ArrayList();
        LabLista = new ArrayList();
        EquipLista = new ArrayList();
        
        InstFim = new ArrayList();
        DiscFim = new ArrayList();
        TurmaFim = new ArrayList();
        SalaFim = new ArrayList();
        AlunoFim = new ArrayList();
        OcupaFim = new ArrayList();
        
        File diretorio = new File("diretorio");//cria uma pasta chamada diretorio para poder guardar os arquivos
        boolean mk = diretorio.mkdir();
        System.out.println("diretorio criado " + mk);//confirma se o diretorio foi criado
        File Inst = new File(diretorio, "inst");// criam duas pastas para separar os derivados de instituicao e os de disciplina
        boolean jk = Inst.mkdir();
        File Disc = new File(diretorio, "disc");
        boolean ik = Disc.mkdir(); 
        
        //LerArqInst();//se quiser testar a função tá pronta só ´demora muito(talvez tenha como arrumar isso)
        
        funcMenu();// cham a função do menu
    }
    
    public static void main(String[] args)//classe main estática
    {      
        new Main(); // declaração do metodo main 
    }
    
    public Instituicao cadastroInst()//cadastra instituições
    {//primeiro a ser cadastrado
        Instituicao Inst = new Instituicao();
        int a;
        ArrayList<Predio> PredioLocal = new ArrayList();//array list apenas para mexer internamente na função
        Scanner cn = new Scanner(System.in);
	System.out.printf("informe o nome da instituição ");
        Inst.setNome(cn.nextLine());
        Inst.criaDiretInst();
        Inst.Salvar();//cria um arquivo .txt com as informações fornecidas e mostra um balão na tela quando completo 
        Inst.salvarLis();//cria um arquivo .txt com o nome dos objetos
        System.out.printf("informe o numero de predios ");
        a = cn.nextInt();
        for(int b=0; b<a; b++)
        {
            Predio Pred = new Predio();
            PredioLocal.add(Pred.cadPred(Inst));//instancia a função cadPredio da classe Predio
            PredLista.add(Pred);//adiciona o objeto para a lista de predios
            
        }
        for(int c=0; c<PredioLocal.size(); c++)//funçãp que pecorre o Array de predio local
        {
            for(int d=0; d<PredioLocal.get(c).getSala().size(); d++)//percorre as salas dentro do aray
            {
                SalaFim.add(PredioLocal.get(c).getSala().get(d));//adiciona as salas
            }
        }
        Inst.setPredios(PredioLocal);//adiciona a lista de predios para o objeto inst
        
        return Inst;
    } 

    public void cadastroPredio()//cadastra apenas prédio e salas
    {
        int n;
        String Inst;
        Predio Pred = new Predio();
        Scanner cn = new Scanner(System.in);
        System.out.printf("informe o nome da Instituicao ");
        Inst = cn.nextLine();
        for(int i=0; i<InstFim.size();i++)//verifica se a instituição existe
        {
            if(Inst.equals(InstFim.get(i).getNome()))//compara duas strings para descobrir se os predios são iguais
            {
                InstFim.get(i).getPredios().add(Pred.cadPred(InstFim.get(i)));//instanbcia a função cadPred
                PredLista.add(Pred);
                for(int j=0;j<Pred.getSala().size(); j++)//separa as salas recem adicionada para poder adicionar em novas listas
                {
                    SalaFim.add(Pred.getSala().get(j));
                    InstFim.get(i).Salvar();
                }
                
                break;
            }
        }
        
    }
    
    public void cadastroSala()//cadastra apenas salas
    {
        int n;
        String Pred;
        Sala S = new Sala();
        Scanner cn = new Scanner(System.in);
        System.out.printf("informe o nome do Predio ");
        Pred = cn.nextLine();
        for(int i=0; i<PredLista.size();i++)//verifica se o predio existe
        {
            if(Pred.equals(PredLista.get(i).getNome()))
            {
                PredLista.get(i).getSala().add(S.cadSala(PredLista.get(i)));//instancia cadsala
                SalaFim.add(S);//depois criar uma função para abrir o arquivo e modificar a quantidade de salas do predio
                for(int j=0; j<InstFim.size(); j++)
                {
                    for(int k=0; k<InstFim.get(j).getPredios().size(); k++)
                    {
                        if(InstFim.get(i).getPredios().get(k).getNome().equals(Pred))
                        {
                            InstFim.get(i).getPredios().get(k).getSala().add(S);
                            InstFim.get(i).Salvar();
                        }
                    }
                }
                break;
            }
        }
        
    }        
    
    public void cadastroDiciplina()// cadastras novas disciplinas => Turmas => Ocupações
    {
        Disciplina D = new Disciplina();
        int a;
        String prof;
        Scanner cn = new Scanner(System.in);
        Scanner cn2 = new Scanner(System.in);
        ArrayList <Turma> TurmaLocal = new ArrayList();
        System.out.printf("informe o nome da disciplina ");
        D.setNome(cn.nextLine());
        System.out.printf("informe o código ");
        D.setCodigo(cn2.nextInt());
        D.escolherGrau();//instancia a função para escolher o grau da disciplina
        System.out.printf("informe o numero de turmas ");
        a = cn2.nextInt();
        D.criaDiretDisciplina();
        D.salvarLis();
        D.Salvar();
        for(int i=0; i<a; i++)//loop para instanciar vários objetos de turma
        {
            Turma T = new Turma();
            System.out.printf("informe o nome do professor ");
            prof = cn.nextLine();
            for(int j=0; j<ProfLista.size(); j++)//verifica se o professor já foi registrado
            {
                if(prof.equals(ProfLista.get(j).getNome()))
                {
                    T.cadTurma(D, ProfLista.get(j), SalaFim, OcupaFim);
                    TurmaLocal.add(T);
                    TurmaFim.add(T);
                    ProfLista.get(j).setTurma(T);
                    for(int x=0; x<T.getOcupacoes().size(); x++)
                    {
                        OcupaFim.add(T.getOcupacoes().get(x));
                    }
                    for(int b=0; b<SalaFim.size(); b++)//sequencia de salos para instanciar cada turma em um cada campo das listas (não tenho certeza se essa parte está certa)
                    {
                        for(int c=0; c<T.getOcupacoes().size(); c++)
                        {
                            if(SalaFim.get(b).getIdSala() == (T.getOcupacoes().get(c).getSala().getIdSala()))
                            {
                                SalaFim.get(b).getOcupa().add(T.getOcupacoes().get(c));
                                if(SalaFim.get(b).getPredio().getInstituicao().getNome().equals(T.getOcupacoes().get(c).getSala().getPredio().getInstituicao().getNome()))
                                {
                                    for(int d=0; d<InstFim.size(); i++)
                                    {
                                        if(InstFim.get(d).getNome().equals(T.getOcupacoes().get(c).getSala().getPredio().getInstituicao().getNome()))
                                        {
                                           for(int e=0; e<InstFim.get(d).getPredios().size(); e++)
                                           {
                                               if(InstFim.get(d).getPredios().get(e).getNome().equals(T.getOcupacoes().get(c).getSala().getPredio().getNome()))
                                               {
                                                   for(int f=0; f<InstFim.get(d).getPredios().get(e).getSala().size(); f++)
                                                   {
                                                       if(Integer.toString(InstFim.get(d).getPredios().get(e).getSala().get(f).getIdSala()).equals(Integer.toString(T.getOcupacoes().get(c).getSala().getIdSala())))
                                                       {
                                                           InstFim.get(d).getPredios().get(e).getSala().get(f).getTurma().add(T);
                                                       }
                                                   }
                                               }
                                           }
                                        }
                                    }
                                }
                            }
                        }
                    }
                 break;
                }
            }
            
        }
        D.setTurmas(TurmaLocal);
        DiscFim.add(D);
    }
    
    public void cadastroProfessor()//função para cadastrar professor
    {
        Professor P = new Professor();
        String Inst;
        Scanner cn = new Scanner(System.in);
        Scanner cn2 = new Scanner(System.in);
        System.out.printf("informe a instituicao");
        Inst = cn.nextLine();
        for(int a=0; a<InstFim.size(); a++)//verifica se a institução existe
        {
            if(Inst.equals(InstFim.get(a).getNome()))
            {
                P.setInst(InstFim.get(a));
                break;
            }
        }
        System.out.printf("informe nome ");
        P.setNome(cn.nextLine());
        System.out.printf("informe a matrícula");
        P.setMatricula(cn2.nextLine());
        System.out.printf("informe a especialização");
        P.setEspecializacao(cn.nextLine());
        P.criaDiretProf();
        P.salvarLisProf();
        P.Salvar();
        ProfLista.add(P);
    }
    
    public void cadastroAluno()//ultimo a ser cadastrado
    {
        Aluno A = new Aluno();
        String modo, Disc, turma, Inst;
        int i=1;
        ArrayList <Turma> TurmaLocal = new ArrayList();//array temporário
        Scanner cn = new Scanner(System.in);
        Scanner cn2 = new Scanner(System.in);
        System.out.printf("informe o nome");
        A.setNome(cn.nextLine());
        System.out.printf("informe a matrícula");
        A.setMatricula(cn2.nextLine());
        System.out.printf("informe o curso");
        A.setCurso(cn.nextLine());
        System.out.printf("informe a instituicao");
        Inst = cn.nextLine();
        for(int a=0; a<InstFim.size(); a++)//verifica se institução existe
        {
            if(Inst.equals(InstFim.get(a).getNome()))
            {
                A.setInst(InstFim.get(a));
                break;
            }
        }
        A.escolherGrau();//instancia a função para determinar o grau do aluno
        A.criaDiretAluno();
        A.criarDiretTurma();
        do//loop para adicionar quantas turmas forem nescessárias(como o i não incrementa ele sempre vai ser menor do que 42)
        {
            System.out.printf("informe a disciplina");
            Disc = cn.nextLine();
            for(int j=0; j<DiscFim.size();j++)//procura as disciplinas
            {
                if(Disc.equals(DiscFim.get(j).getNome()))
                {
                    if(DiscFim.get(j).getGrau().equals(A.getGrau()))//iverifiaca se os graus são iguais
                    {    
                        System.out.printf("informe a turma ");
                        turma = cn.nextLine();
                        for(int k=0; k<DiscFim.get(j).getTurmas().size();k++)//procura a turma
                        {
                            if(turma.equals(DiscFim.get(j).getTurmas().get(k).getNome()))
                            {
                                if(DiscFim.get(j).getTurmas().get(k).getNumAlunos() < DiscFim.get(j).getTurmas().get(k).getCapacidadeMax())
                                {
                                    DiscFim.get(j).getTurmas().get(k).setNumAlunos(DiscFim.get(j).getTurmas().get(k).getNumAlunos()+1);
                                    DiscFim.get(j).getTurmas().get(k).getAlunos().add(A);//adiciona o objeto a na lista de disciplinas na lista interna para turmas
                                    TurmaLocal.add(DiscFim.get(j).getTurmas().get(k));//adiciona a turma
                                    A.salvaLisTurma(DiscFim.get(j).getTurmas().get(k));
                                    A.SalvarTurma(DiscFim.get(j).getTurmas().get(k));
                                    break; 
                                }
                                else
                                {
                                    System.out.printf("não foi possível cadastrar nessa turma");
                                    j--;
                                }
                                
                            }
                        }
                    }    
                }
                break;
            }
            System.out.printf("deseja adicionar mais matérias?");
            modo = cn.nextLine();
            if ("n".equals(modo))//condição de saida do loop
            {
                i=42;
            }
        }while(i<42);
        A.setTurmas(TurmaLocal);
        A.salvaLisAluno();
        A.Salvar();//metodo polimorfo para salvar em arquivo
        AlunoFim.add(A);
    }
    
    public void cadastroTurma()//cadastra apenas turma e ocupação
    {
        int n, Sala;
        String Prof, Disc;
        Turma T = new Turma();
        ArrayList<Ocupa> OcupaCont = new ArrayList();
        Scanner cn = new Scanner(System.in); 
        System.out.printf("informe o nome da disciplina ");
        Disc = cn.nextLine();
        for(int i=0; i<DiscFim.size();i++)//procura na lista de disciplinas
        {
            if(Disc.equals(DiscFim.get(i).getNome()))
            {
                System.out.printf("informe o nome do professor");
                Prof = cn.nextLine();
                for(int j=0; j<ProfLista.size();j++)//procura na lista de professores
                {
                    if(Prof.equals(ProfLista.get(j).getNome()))
                    {
                        DiscFim.get(i).getTurmas().add(T.cadTurma(DiscFim.get(i), ProfLista.get(j), SalaFim, OcupaFim));//instancia a função cadTurma
                        TurmaFim.add(T);//adiciona na lista geral de turmas
                        for(int x=0; x<T.getOcupacoes().size(); x++)
                        {
                            OcupaFim.add(T.getOcupacoes().get(x));
                        }
                        for(int b=0; b<SalaFim.size(); b++)//for para adicio nar turma nas listas de turma(não sei se está totalmente correto)
                        {
                            for(int c=0; c<TurmaFim.get(TurmaFim.size()-1).getOcupacoes().size(); c++)
                            {
                                if(SalaFim.get(b).equals(TurmaFim.get(TurmaFim.size()-1).getOcupacoes().get(c).getSala()))
                                {
                                    SalaFim.get(b).getOcupa().add(TurmaFim.get(TurmaFim.size()-1).getOcupacoes().get(c));
                                }
                            }
                        }
                        break;
                    }   
                }
                break;
            }
        }
        
    }
}
