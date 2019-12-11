package entities;

import java.util.ArrayList;
import java.util.Scanner;

public class Laboratorio extends Sala
{
    private int qtd_computadores;
    private String tipo_equipamento;
    private int qtd_equipametos;

    public int getQtd_computadores() 
    {
        return qtd_computadores;
    }

    public void setQtd_computadores(int qtd_computadores) 
    {
        this.qtd_computadores = qtd_computadores;
    }

    public int getQtd_equipametos() 
    {
        return qtd_equipametos;
    }

    public void setQtd_equipametos(int qtd_equipametos) 
    {
        this.qtd_equipametos = qtd_equipametos;
    }

    public String getTipo_equipamento() 
    {
        return tipo_equipamento;
    }

    public void setTipo_equipamento(String tipo_equipamento) 
    {
        this.tipo_equipamento = tipo_equipamento;
    }
    
    public Sala cadLab(Predio Pred)//depois criar um diretório apenas para laboratórios e salvar todos os objetos de lab lá também
    {
        int m;
        ArrayList <String> equipList = new ArrayList();
        Scanner cn = new Scanner(System.in);
        Scanner cn2 = new Scanner(System.in);//Scanner para limpar obuffer e não dar erro de execução
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
        this.setTv(cn.nextBoolean());
        System.out.printf("informe a quantidade de computadores ");
        this.setQtd_computadores(cn.nextInt());
        System.out.printf("informe o tipo de equipamento ");
        this.setTipo_equipamento(cn2.nextLine());
        System.out.printf("informe a quantidade de equipamentos ");
        m = cn.nextInt();
        this.setQtd_equipametos(m);
        this.criaDiretSala();
        this.Salvar();
        this.salvarLis();
        
        return this;
    }

}
