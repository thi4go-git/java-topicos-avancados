package java8;

public class Produto{
    private String nome;
    private double preco;

    public Produto(){
    }

    public Produto(String nome, double preco){
        this.nome = nome;
        this.preco = preco;
    }

    public String getNome(){
        return nome;
    }

    public double getPreco(){
        return preco;
    }

    public static void imprimeProdutoStatic(Produto produto){
        System.out.println("Imprimindo produto estático: "+produto.getNome());
    }

    public void imprimeProdutoNotStatic(){
        System.out.println("Imprimindo produto: "+nome);
    }
}