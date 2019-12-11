
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

public class CriarArq 
{   
    private String nome;
    private String caminho;

    public String getNome() 
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getCaminho() 
    {
        return caminho;
    }

    public void setCaminho(String caminho) 
    {
        this.caminho = caminho;
    }
    
    public void DiretDisc(ArrayList <Disciplina> Disc)
    {
        
    }
    
    public void DiretInst(ArrayList <Instituicao> Inst)
    {
        this.LerListInst();
        this.LerArqInst(this.LerListInst(), Inst);
        this.LerListPred(this.LerListInst());
        this.LerArqPred(this.LerListPred(this.LerListInst()), Inst);
        this.LerListSala(this.LerListPred(this.LerListInst()));
        this.LerArqSala(this.LerListSala(this.LerListPred(this.LerListInst())), Inst);
        this.LerListOcupaSala(this.LerListSala(this.LerListPred(this.LerListInst())));
        this.LerArqOcupaSala(this.LerListOcupaSala(this.LerListSala(this.LerListPred(this.LerListInst()))), Inst);
        
        this.LerListProf(this.LerListInst());
        this.LerArqProf(this.LerListInst(), Inst);
        //this.LerListTurmaProf(this.LerListProf(this.LerListInst()));
        //this.LerArqTurmaProf(this.LerListTurmaProf(this.LerListProf(this.LerListInst())), Inst);
        
        this.LerListAluno(this.LerListInst());
    }
   
    public ArrayList <CriarArq> LerListInst()
    {
        ArrayList <CriarArq> list = new ArrayList();
        BufferedReader arqList;
        try 
        {
            arqList = new BufferedReader(new FileReader("diretorio//inst//lista.txt")); //procura e abre para leitura o elemeto da lista.txt
            String linha = null;
            try 
            {
                linha = arqList.readLine();
                while(linha != null)
                {
                    CriarArq local = new CriarArq();
                    String l = ("diretorio//inst//"+linha+"//");
                    local.setNome(linha);
                    local.setCaminho(l);
                    list.add(local);
                    
                    linha = arqList.readLine();
                }
                arqList.close();
            } 
            catch (IOException ex) 
            {
                Logger.getLogger(CriarArq.class.getName()).log(Level.SEVERE, null, ex);
            }   
        
        } 
        catch (FileNotFoundException ex) 
        {
            Logger.getLogger(CriarArq.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
    public ArrayList <Instituicao> LerArqInst(ArrayList<CriarArq> List ,ArrayList<Instituicao> Inst)
    {
        for(int i=0; i<List.size(); i++)
        {
            try 
            {
                BufferedReader arqInst = new BufferedReader(new FileReader(List.get(i).getCaminho()+List.get(i).getNome()+".txt"));
                try 
                {
                    Instituicao I = new Instituicao();
                    I.setNome(arqInst.readLine());
                    Inst.add(I);
                    
  
                    arqInst.close();

                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(CriarArq.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            catch (FileNotFoundException ex) 
            {
                Logger.getLogger(CriarArq.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        return Inst;
    }
    
    public ArrayList <CriarArq> LerListPred(ArrayList<CriarArq> ListAnt)//recebe lista de inst
    {
        ArrayList <CriarArq> list = new ArrayList();
        for(int i=0; i<ListAnt.size(); i++)
        {
            BufferedReader arqList;
            try 
            {
                arqList = new BufferedReader(new FileReader(ListAnt.get(i).getCaminho()+"lista.txt")); //procura e abre para leitura o elemeto da lista.txt
                String linha = null;
                try 
                {
                    linha = arqList.readLine();
                    while(linha != null)
                    {
                        CriarArq local = new CriarArq();
                        String l = (ListAnt.get(i).getCaminho()+linha+"//");
                        local.setNome(linha);
                        local.setCaminho(l);
                        list.add(local);

                        linha = arqList.readLine();
                    }
                    arqList.close();
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(CriarArq.class.getName()).log(Level.SEVERE, null, ex);
                }   

            } 
            catch (FileNotFoundException ex) 
            {
                Logger.getLogger(CriarArq.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
    public ArrayList <CriarArq> LerListProf(ArrayList<CriarArq> ListAnt)//recebe lista de inst
    {
        ArrayList <CriarArq> list = new ArrayList();
        for(int i=0; i<ListAnt.size(); i++)
        {
            BufferedReader arqList;
            try 
            {
                arqList = new BufferedReader(new FileReader(ListAnt.get(i).getCaminho()+"professor//lista.txt")); //procura e abre para leitura o elemeto da lista.txt
                String linha = null;
                try 
                {
                    linha = arqList.readLine();
                    while(linha != null)
                    {
                        CriarArq local = new CriarArq();
                        String l = (ListAnt.get(i).getCaminho()+"professor//"+linha+"//");
                        local.setNome(linha);
                        local.setCaminho(l);
                        list.add(local);
                        

                        linha = arqList.readLine();
                    }
                    arqList.close();
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(CriarArq.class.getName()).log(Level.SEVERE, null, ex);
                }   

            } 
            catch (FileNotFoundException ex) 
            {
                Logger.getLogger(CriarArq.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
    public ArrayList <CriarArq> LerListAluno(ArrayList<CriarArq> ListAnt)//recebe lista de inst
    {
        ArrayList <CriarArq> list = new ArrayList();
        for(int i=0; i<ListAnt.size(); i++)
        {
            BufferedReader arqList;
            try 
            {
                arqList = new BufferedReader(new FileReader(ListAnt.get(i).getCaminho()+"aluno//lista.txt")); //procura e abre para leitura o elemeto da lista.txt
                String linha = null;
                try 
                {
                    linha = arqList.readLine();
                    while(linha != null)
                    {
                        CriarArq local = new CriarArq();
                        String l = (ListAnt.get(i).getCaminho()+"aluno//"+linha+"//");
                        local.setNome(linha);
                        local.setCaminho(l);
                        list.add(local);

                        linha = arqList.readLine();
                    }
                    arqList.close();
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(CriarArq.class.getName()).log(Level.SEVERE, null, ex);
                }   

            } 
            catch (FileNotFoundException ex) 
            {
                Logger.getLogger(CriarArq.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
    public ArrayList <Instituicao> LerArqPred(ArrayList<CriarArq> List ,ArrayList<Instituicao> Inst)
    {
        for(int i=0; i<List.size(); i++)    
        {
            try 
            {
                BufferedReader arqCont = new BufferedReader(new FileReader(List.get(i).getCaminho()+List.get(i).getNome()+".txt"));
                try 
                {
                    String cont1, cont2, cont3;
                    cont1 = arqCont.readLine();
                    cont2 = arqCont.readLine();
                    cont3 = arqCont.readLine();
                    for(int a=0; a<Inst.size(); a++)
                    {
                        if(Inst.get(a).getNome().equals(cont1))
                        {
                            Predio Pred = new Predio();
                            Pred.setInstituicao(Inst.get(a));
                            Pred.setNome(cont2);
                            Pred.setQtd_salas(Integer.parseInt(cont3));
                            Inst.get(a).getPredios().add(Pred);
                            
                        } 
                    }
                    //arqCont.close();
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(CriarArq.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            catch (FileNotFoundException ex) 
            {
                Logger.getLogger(CriarArq.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        return Inst;
    }
    
    public ArrayList <CriarArq> LerListSala(ArrayList<CriarArq> ListAnt)//recebe lista de inst
    {
        ArrayList <CriarArq> list = new ArrayList();
        for(int i=0; i<ListAnt.size(); i++)
        {
            BufferedReader arqList;
            try 
            {
                arqList = new BufferedReader(new FileReader(ListAnt.get(i).getCaminho()+"sala//lista.txt")); //procura e abre para leitura o elemeto da lista.txt
                String linha = null;
                try 
                {
                    linha = arqList.readLine();
                    while(linha != null)
                    {
                        CriarArq local = new CriarArq();
                        String l = (ListAnt.get(i).getCaminho()+"sala//"+linha+"//");
                        local.setNome(linha);
                        local.setCaminho(l);
                        list.add(local);

                        linha = arqList.readLine();
                    }
                    arqList.close();
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(CriarArq.class.getName()).log(Level.SEVERE, null, ex);
                }   

            } 
            catch (FileNotFoundException ex) 
            {
                Logger.getLogger(CriarArq.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
    public ArrayList <Instituicao> LerArqSala(ArrayList<CriarArq> List ,ArrayList<Instituicao> Inst)
    {
        for(int i=0; i<List.size(); i++)    
        {
            try 
            {
                BufferedReader arqCont = new BufferedReader(new FileReader(List.get(i).getCaminho()+List.get(i).getNome()+".txt"));
                try 
                {
                    String cont1, cont2, cont3, cont4, cont5, cont6;
                    cont1 = arqCont.readLine();
                    cont2 = arqCont.readLine();
                    cont3 = arqCont.readLine();
                    cont4 = arqCont.readLine();
                    cont5 = arqCont.readLine();
                    cont6 = arqCont.readLine();
                    
                    for(int a=0; a<Inst.size(); a++)
                    {
                        for (int b=0; b<Inst.get(a).getPredios().size(); b++)
                        {
                            if(Inst.get(a).getPredios().get(b).getNome().equals(cont1))
                            {
                                Sala S = new Sala();
                                S.setPredio(Inst.get(a).getPredios().get(b));
                                S.setIdSala(Integer.parseInt(cont2));
                                S.setCapacidade(Integer.parseInt(cont3));
                                S.setAr(Boolean.parseBoolean(cont4));
                                S.setProjetor(Boolean.parseBoolean(cont5));
                                S.setTv(Boolean.parseBoolean(cont6));
                                Inst.get(a).getPredios().get(b).getSala().add(S);
                                System.out.println("cheguei em "+cont1+" S"+cont2);
                            }
                        }    
                    }
                    //arqCont.close();
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(CriarArq.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            catch (FileNotFoundException ex) 
            {
                Logger.getLogger(CriarArq.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        return Inst;
    }    
     
    public ArrayList <CriarArq> LerListOcupaSala(ArrayList<CriarArq> ListAnt)//recebe lista de inst
    {
        ArrayList <CriarArq> list = new ArrayList();
        for(int i=0; i<ListAnt.size(); i++)
        {
            BufferedReader arqList;
            try 
            {
                arqList = new BufferedReader(new FileReader(ListAnt.get(i).getCaminho()+"ocupa//lista.txt")); //procura e abre para leitura o elemeto da lista.txt
                String linha = null;
                try 
                {
                    linha = arqList.readLine();
                    while(linha != null)
                    {
                        CriarArq local = new CriarArq();
                        String l = (ListAnt.get(i).getCaminho()+"ocupa//");
                        local.setNome(linha);
                        local.setCaminho(l);
                        list.add(local);

                        linha = arqList.readLine();
                    }
                    arqList.close();
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(CriarArq.class.getName()).log(Level.SEVERE, null, ex);
                }   

            } 
            catch (FileNotFoundException ex) 
            {
                Logger.getLogger(CriarArq.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
    public ArrayList <Instituicao> LerArqOcupaSala(ArrayList<CriarArq> List ,ArrayList<Instituicao> Inst)
    {
        for(int i=0; i<List.size(); i++)    
        {
            try 
            {
                BufferedReader arqCont = new BufferedReader(new FileReader(List.get(i).getCaminho()+List.get(i).getNome()+".txt"));
                try 
                {
                    String cont1, cont2, cont3, cont4;
                    cont1 = arqCont.readLine();
                    cont2 = arqCont.readLine();
                    cont3 = arqCont.readLine();
                    cont4 = arqCont.readLine();
                    for(int a=0; a<Inst.size(); a++)
                    {
                        for (int b=0; b<Inst.get(a).getPredios().size(); b++)
                        {
                            for(int c=0; c<Inst.get(a).getPredios().get(b).getSala().size(); c++)    
                            {    
                                if(Inst.get(a).getPredios().get(b).getSala().get(c).getIdSala() == (Integer.parseInt(cont2)))
                                {
                                    Ocupa O = new Ocupa();
                                    //O.getTurma().setNome(cont1);//esse termo precisa de todos os elementos de ocupa:(
                                    O.setSala(Inst.get(a).getPredios().get(b).getSala().get(c));
                                    O.setDia(cont3);
                                    O.setHorario(Integer.parseInt(cont4));
                                    Inst.get(a).getPredios().get(b).getSala().get(c).getOcupa().add(O);
                                    System.out.println("cheguei em "+cont1+" S"+cont2+"Ocupa"+cont1);
                                }
                            }    
                        }    
                    }
                    //arqCont.close();
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(CriarArq.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            catch (FileNotFoundException ex) 
            {
                Logger.getLogger(CriarArq.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        return Inst;
    }
    
    public ArrayList <Instituicao> LerArqProf(ArrayList<CriarArq> List ,ArrayList<Instituicao> Inst)
    {
        for(int i=0; i<List.size(); i++)    
        {
            try 
            {
                BufferedReader arqCont = new BufferedReader(new FileReader(List.get(i).getCaminho()+List.get(i).getNome()+".txt"));
                try 
                {
                    String cont1, cont2, cont3, cont4;
                    cont1 = arqCont.readLine();
                    cont2 = arqCont.readLine();
                    cont3 = arqCont.readLine();
                    cont4 = arqCont.readLine();
                    
                    for(int a=0; a<Inst.size(); a++)
                    {
                        if(Inst.get(a).getNome().equals(cont1))
                        {
                            Professor Prof = new Professor();
                            Prof.setInst(Inst.get(a));
                            Prof.setNome(cont2);
                            Prof.setMatricula(cont3);
                            Prof.setEspecializacao(cont4);
                            Inst.get(a).getProfessores().add(Prof);
                        } 
                    }
                    arqCont.close();
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(CriarArq.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            catch (FileNotFoundException ex) 
            {
                Logger.getLogger(CriarArq.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        return Inst;
    }
    
    /*public ArrayList <CriarArq> LerListTurmaProf(ArrayList<CriarArq> ListAnt)//recebe lista de inst
    {
        ArrayList <CriarArq> list = new ArrayList();
        for(int i=0; i<ListAnt.size(); i++)
        {
            BufferedReader arqList;
            try 
            {
                arqList = new BufferedReader(new FileReader(ListAnt.get(i).getCaminho()+"lista.txt")); //procura e abre para leitura o elemeto da lista.txt
                String linha = null;
                try 
                {
                    linha = arqList.readLine();
                    while(linha != null)
                    {
                        CriarArq local = new CriarArq();
                        String l = (ListAnt.get(i).getCaminho()+linha+"//");
                        local.setNome(linha);
                        local.setCaminho(l);
                        list.add(local);
                        

                        linha = arqList.readLine();
                    }
                    arqList.close();
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(CriarArq.class.getName()).log(Level.SEVERE, null, ex);
                }   

            } 
            catch (FileNotFoundException ex) 
            {
                Logger.getLogger(CriarArq.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return list;
    }
    
    public ArrayList <Instituicao> LerArqTurmaProf(ArrayList<CriarArq> List ,ArrayList<Instituicao> Inst)
    {
        for(int i=0; i<List.size(); i++)    
        {
            try 
            {
                BufferedReader arqCont = new BufferedReader(new FileReader(List.get(i).getCaminho()+List.get(i).getNome()+".txt"));
                try 
                {
                    String cont1, cont2, cont3, cont4, cont5;
                    cont1 = arqCont.readLine();
                    cont2 = arqCont.readLine();
                    cont3 = arqCont.readLine();
                    cont4 = arqCont.readLine();
                    cont5 = arqCont.readLine();
                    
                    for(int a=0; a<Inst.size(); a++)
                    {
                        for (int b=0; b<Inst.get(a).getProfessores().size(); b++)
                        { 
                            if(Inst.get(a).getProfessores().get(b).getNome().equals(cont2))
                            {
                                Turma T = new Turma();
                                T.setProfessor(Inst.get(a).getProfessores().get(b));
                                T.setNome(cont3);
                                T.setNumAlunos(Integer.parseInt(cont4));
                                T.setNumDiasSema(Integer.parseInt(cont5));
                                Inst.get(a).getProfessores().get(b).setTurma(T);
                                System.out.println("cheguei em "+cont3);
                            }
                                
                        }    
                    }
                    //arqCont.close();
                } 
                catch (IOException ex) 
                {
                    Logger.getLogger(CriarArq.class.getName()).log(Level.SEVERE, null, ex);
                }
            } 
            catch (FileNotFoundException ex) 
            {
                Logger.getLogger(CriarArq.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        return Inst;
    }*/
}
