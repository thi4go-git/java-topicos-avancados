package java8;

public class Produto{
    private String nome;
    private double preco;
    private TipoProduto tipoProduto;

    public Produto(){
    }

    public Produto(String nome, double preco){
        this.nome = nome;
        this.preco = preco;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public void setpreco(double preco){
        this.preco = preco;
    }

    public String getNome(){
        return nome;
    }

    public double getPreco(){
        return preco;
    }

    public void setTipoProduto(TipoProduto tipoProduto){
        this.tipoProduto = tipoProduto;
    }

    public TipoProduto getTipoProduto(){
        return tipoProduto;
    }

    public static void imprimeProdutoStatic(Produto produto){
        System.out.println("Imprimindo produto estático: "+produto.getNome());
    }

    public void imprimeProdutoNotStatic(){
        System.out.println("Imprimindo produto: "+nome);
    }
}