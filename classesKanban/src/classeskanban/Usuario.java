package classeskanban;

import java.util.ArrayList;

/**
 * @author Kaique Bahmad Tavares <kaiquebahmadt@gmail.com>
 * @date 10/11/2023
 * @brief Class Usuario
 */
public class Usuario {
    protected ArrayList<Atividade> atividades;
    protected Projeto pai;
    protected String nome;
        
    public Usuario(Projeto pai, String nome, String cor) {
        this.atividades = new ArrayList<>(); 
        this.pai = pai;
        this.nome = nome;
    }
//    Adicionar atividade agora Requer passar a atividade já construida
//    public void adicionarAtividade(String nome, long inicio, long prazo) {
//        this.atividades.add(new Atividade(this, nome, inicio, prazo));
//    }
    
    public void adicionarAtividade(Atividade atividade) {
        this.atividades.add(atividade);
    }
    
    public Atividade getAtividadeByNome(String nome) {
        for (Atividade atividade:this.atividades) {
            if (nome.equals(atividade.getNome())) {
                return atividade;
            }
        }
        return null;
    }
    
    public void removeAtividadeByNome(String nome) {
        for (Atividade atividade:this.atividades) {
            if (nome.equals(atividade.getNome())) {
                this.atividades.remove(atividade);
            }
        }
    }

    public String getNome() {
        return this.nome;
    }
    
    public Projeto getProjeto() {
        return pai;
    }
    
    public ArrayList<Atividade> getAtividades() {
        return this.atividades;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}