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
		private int horariofunc[];
	    private int qtd_salas;
	    private String nome;
	    private Instituicao instituicao;//faz a associação com instituições
	    private ArrayList <Sala> salas = new ArrayList<Sala>();
	
	    
	    public void definirHorariofunc() {
	    	
	    	 String a, b ;
	    	 int i,p,w;
	    	Scanner hf = new Scanner(System.in);
	    	System.out.println("Defina a quantidade de aulas/dia no prédio.");
	    	a = hf.nextLine();
	    	p= Integer.parseInt(a);
	    	horariofunc = new int [p];
	    	System.out.println("Defina o horario de abertura do predio.");
	    	Scanner ha = new Scanner(System.in);
	    	b = ha.nextLine();
	    	w = Integer.parseInt(b);
	    	horariofunc[0]= w;
	    	for (i = 1 ;i < p; i ++) {
	    		horariofunc[i] = horariofunc[0]+200;// os horrios sairiam em 0800,1000,1200
	    		System.out.println("o horario de funcionamento do prédio e de"+ horariofunc[0] + "-" + horariofunc[i]);
	    	}
	    	
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


