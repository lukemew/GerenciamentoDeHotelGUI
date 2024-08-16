public class Inquilino {
    private String nome;
    private String profissao;
    private int idade;


    public void mostrarDetalhes(){
        System.out.println("Exibindo informações do inquilino");
        System.out.println("Inquilino hospedado: " + this.nome);
        System.out.println("Profissão: " + this.profissao);
        System.out.println("Idade: " + this.idade);
        System.out.println("---");
    }

    // Métodos de inserção e extração
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getProfissao() {
        return this.profissao;
    }

    public void setIdade(int idade){
        this.idade = idade;
    }

    public int getIdade(){
        return this.idade;
    }

}

