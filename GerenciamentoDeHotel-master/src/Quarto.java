public class Quarto {
    private int numero;
    private String tipoDeQuarto;
    private int capacidade;
    private double valor;


    public void mostrarDetalhes() {
        System.out.println("Exibindo informações sobre o quarto " + this.numero);
        System.out.println("Valor da diária: " + this.valor);
        System.out.println("Tipo de quarto: " + this.tipoDeQuarto);
        System.out.println("Capacidade do quarto: " + this.capacidade);
        System.out.println("---");
    }

    // Métodos de inserção e extração
    public double getValor(){
        return this.valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getTipoDeQuarto(){
        return this.tipoDeQuarto;
    }

    public void setTipoDeQuarto(String tipo) {
        if (tipo == "Luxo" || tipo == "Simples") {
            this.tipoDeQuarto = tipo;
        }
    }

    public int getCapacidade(){
        return this.capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public int getNumero(){
        return this.numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }


}
