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


    public Jogador () {
        ambienteAnterior = new Stack<Ambiente>();

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
}
