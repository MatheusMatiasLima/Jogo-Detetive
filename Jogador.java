/*
A classe jogador foi criada para separar as caracteristicas do jogador
da classe Jogo, deste modo, qualquer ateração que faça parte do jogador e 
não do jogo será feita nessa parte. Por exemplo, no futuro iremos adicionar 
um objeto Item. Esse objeto armazenara a arma do crime.

*/

import java.util.*;

public class Jogador {

    private Ambiente ambienteAtual;
    private Stack<Ambiente> ambienteAnterior;
    private Item itemCarregado;
    private String nomeAssassino;


    public Jogador () {
        ambienteAnterior = new Stack<Ambiente>();
        nomeAssassino = null;

    }

    public String getNomeAssassino() {
        return nomeAssassino;
    }

    public String getItemCarregado() {
        return itemCarregado.getNome();
    }

    public void definirAmbienteAtual(Ambiente ambiente) {
        ambienteAtual = ambiente;

    }

    public Ambiente getAmbienteAtual() {
        return ambienteAtual;
    }

    public void salvarAmbienteAnterior() {
        ambienteAnterior.push(ambienteAtual);

    }

    public Ambiente retornarAmbienteAnterior() {
        return ambienteAnterior.pop();
    }
    
    //retorna true se for o ambiente inicial
    public boolean ehAmbienteInicial() {
        return ambienteAnterior.empty();
    }

    public void pegarItem(Item item) {
        itemCarregado = item;
    }

    public void mostrarItemQueEstaCarregando() {
        if (itemCarregado == null) {
            System.out.println("Voce nao esta carregando nenhum item.");
        }
        else {
            System.out.println("Voce esta carregando um(a) " + itemCarregado.getNome());
        }
    }

    public void mostrarAcusacao() {
        if (nomeAssassino == null) {
            System.out.println("Voce nao esta acusando ninguem.");
        }
        else {
            System.out.println("Voce esta acusando " + nomeAssassino);
        }
    }

    public void definirAssassino(String nome) {
        nomeAssassino = nome;
        mostrarAcusacao();
    }


}
