/*
Essa classe eh resposavel por armazenar quais os nomes dos
NPCs existentes no jogo. Ela funciona semelhante a classe PalavrasComando


*/





public class RegistroNPC {
    private static final String[] nomesValidos = { "Matheus", "Joicy", "Luiz", "Mariana" };
    private static final String[] comandoNPC = { "acusar", "perguntar" };

    RegistroNPC(){};

    public void mostrarNomesValidos () {

        for (String nome : nomesValidos) {
            System.out.print(nome + " ");
        }
    }

    /**
     * Verifica se uma dada String eh uma palavra de comando valida. 
     * @return true se a string dada eh um comando valido,
     * false se nao eh.
     */
    public boolean ehNome(String umaString)
    {
        for(int i = 0; i < nomesValidos.length; i++) {
            if(nomesValidos[i].equals(umaString))
                return true;
        }
        // se chegamos aqui, a string nao foi encontrada nos comandos.
        return false;
    }

    public boolean ehComandoNPC(String umaString)
    {
        for(int i = 0; i < nomesValidos.length; i++) {
            if(comandoNPC[i].equals(umaString))
                return true;
        }
        // se chegamos aqui, a string nao foi encontrada nos comandos.
        return false;
    }
}