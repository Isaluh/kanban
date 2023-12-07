package utils;

import entities.Empresa;
import entities.Acao;
import entities.Atividade;
import entities.Projeto;
import entities.Usuario;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Relatorio {
    public static String criarRelatorio(Projeto projeto) {
        String relatorio = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY HH:mm");
        String dataAtual = sdf.format(new Date());
        relatorio += "RELATORIO PROJETO \""+projeto.getNome()+"\" "+dataAtual+"\n";
        relatorio += "DESCRIÇÃO: "+projeto.getDescricao() +"\n\n";
        relatorio += "*PREVIEW\n";
        int maior = 0;
        int numAcoes = 0;
        for (Atividade at:projeto.getAtividades()) {
            for (Acao ac:at.getAcoes()) {
                numAcoes++;
                if (ac.getNome().length()+(" (100%)".length())+2 > maior) {
                    maior = ac.getNome().length()+(" (100%)".length())+2;
                }
                if (ac.getUsrResponsavel().getNome().length()+("[]".length())+2 > maior) {
                    maior = ac.getUsrResponsavel().getNome().length()+("[]".length())+2;
                }
            }
        }
        if ("     A Fazer     ".length() > maior) {
            maior = "     A Fazer     ".length();
        }
        if ("     Fazendo     ".length() > maior) {
            maior = "     Fazendo     ".length();
        }
        if ("     Finalizado     ".length() > maior) {
            maior = "     Finalizado     ".length();
        }
        
        relatorio += " "+xS("_", (maior*3)+2)+"\n";
        
        String primeiraColuna = "";
        String segundaColuna = "";
        String terceiraColuna = "";
        
        primeiraColuna = xS(" ", (int) Math.floor((maior-"     A Fazer     ".length() )/2.0)) +  "     A Fazer     "  +xS(" ", (int) Math.ceil((maior-"     A Fazer     ".length() )/2.0));
        segundaColuna = xS(" ", (int) Math.floor((maior-"     Fazendo     ".length() )/2.0)) +  "     Fazendo     "  +xS(" ", (int) Math.ceil((maior-"     Fazendo     ".length() )/2.0));
        terceiraColuna = xS(" ", (int) Math.floor((maior-"     Finalizado     ".length() )/2.0)) +  "     Finalizado     "  +xS(" ", (int) Math.ceil((maior-"     Finalizado     ".length() )/2.0));
        relatorio += "/"+primeiraColuna+"|"+segundaColuna+"|"+terceiraColuna+"\\\n";

        String[][] acoesAFazer = new String[numAcoes+1][2];
        String[][] acoesFazendo = new String[numAcoes+1][2];
        String[][] acoesFinalizado = new String[numAcoes+1][2];
        for (int i = 0; i < numAcoes+1; i++) {
            acoesAFazer[i][0] = "";
            acoesAFazer[i][1] = "";
            acoesFazendo[i][0] = "";
            acoesFazendo[i][1] = "";
            acoesFinalizado[i][0] = "";
            acoesFinalizado[i][1] = "";
        }
        int a = 0;
        int b = 0;
        int c = 0;
        for (Atividade at:projeto.getAtividades()) {
            for (Acao ac:at.getAcoes()) {
                if (ac.getPorcentagem() == 0) {
                    acoesAFazer[a++][0] = " "+ac.getNome()+" (0%)"+" ";
                    acoesAFazer[a-1][1] = " "+"["+ac.getUsrResponsavel().getNome()+"]"+" ";
                }
                if (ac.getPorcentagem() > 0 && ac.getPorcentagem() < 1) {
                    acoesFazendo[b++][0] =" " +ac.getNome()+" ("+(int)(ac.getPorcentagem()*100)+")"+" ";
                    acoesFazendo[b-1][1] = " "+"["+ac.getUsrResponsavel().getNome()+"]"+" ";
                }
                if (ac.getPorcentagem() == 1) {
                    acoesFinalizado[c++][0] = " "+ac.getNome()+" (100%)"+" ";
                    acoesFinalizado[c-1][1] = " "+"["+ac.getUsrResponsavel().getNome()+"]"+" ";
                }
            }
        }
        int maior_ = 0;
        if (a>=b && a>=c) {
            maior_ = a;
        }
        if (b>=a && b>=c) {
            maior_ = b;
        }
        if (c>=a && c>=b) {
            maior_ = c;
        }
        for (int i = 0; i < maior_;i++) {
            relatorio += "|"+xS("-",maior)+"|"+xS("-",maior)+"|"+xS("-", maior)+"|\n";
            primeiraColuna = xS(" ", (int) Math.floor((maior-acoesAFazer[i][0].length() )/2.0)) +  acoesAFazer[i][0]  +xS(" ", (int) Math.ceil((maior-acoesAFazer[i][0].length() )/2.0));
            segundaColuna = xS(" ", (int) Math.floor((maior-acoesFazendo[i][0].length() )/2.0)) +  acoesFazendo[i][0]  +xS(" ", (int) Math.ceil((maior-acoesFazendo[i][0].length() )/2.0));
            terceiraColuna = xS(" ", (int) Math.floor((maior-acoesFinalizado[i][0].length() )/2.0)) +  acoesFinalizado[i][0]  +xS(" ", (int) Math.ceil((maior-acoesFinalizado[i][0].length() )/2.0));
            relatorio += "|"+primeiraColuna+"|"+segundaColuna+"|"+terceiraColuna+"|\n";
            primeiraColuna = xS(" ", (int) Math.floor((maior-acoesAFazer[i][1].length() )/2.0)) +  acoesAFazer[i][1]  +xS(" ", (int) Math.ceil((maior-acoesAFazer[i][1].length() )/2.0));
            segundaColuna = xS(" ", (int) Math.floor((maior-acoesFazendo[i][1].length() )/2.0)) +  acoesFazendo[i][1]  +xS(" ", (int) Math.ceil((maior-acoesFazendo[i][1].length() )/2.0));
            terceiraColuna = xS(" ", (int) Math.floor((maior-acoesFinalizado[i][1].length() )/2.0)) +  acoesFinalizado[i][1]  +xS(" ", (int) Math.ceil((maior-acoesFinalizado[i][1].length() )/2.0));
            relatorio += "|"+primeiraColuna+"|"+segundaColuna+"|"+terceiraColuna+"|\n";
        }
        relatorio += "|"+xS("_",maior)+"|"+xS("_",maior)+"|"+xS("_", maior)+"|\n";
        relatorio +="\n";
        relatorio += "#STATUS USUARIOS\n";
        int indiceDesignado = 0;
        int numDesignado = 0;
        for (Usuario usr: projeto.getUsuarios()) {
            relatorio += usr.getNome()+":\n";
            for (Atividade at:projeto.getAtividades()) {
                for (Acao ac:at.getAcoes()) {
                    if (ac.getUsrResponsavel().getNome().equals(usr.getNome())) {
                        numDesignado++;
                    }
                }
            }
            indiceDesignado = (int)((numDesignado / (double) numAcoes)*100);
            relatorio += "\tDesignado a: "+indiceDesignado+"% das Ações ("+numDesignado+")\n";
            relatorio += "\tAções Atrasadas:\n";
            int porcentagem = 0;
            for (Acao ac:usr.getAcoes()) {
                if (ac.getPrazo() - System.currentTimeMillis()/1000 < 0 && ac.getPorcentagem() < 1 && ac.getAtividade().getProjeto() == projeto) {
                    porcentagem = (int)(ac.getPorcentagem()*100) ;
                    relatorio += "\t\t";
                    relatorio += ac.getNome()+"("+ac.getAtividade().getNome()+")["+porcentagem+"%]: "+Metodos.tempoRestante(ac.getPrazo())+"\n";
                }
            }   
            relatorio+= "\tAções Completas:\n";
            for (Acao ac:usr.getAcoes()) {
                if (ac.getAtividade().getProjeto() != projeto) {
                    continue;
                }
                if (ac.getPorcentagem() == 1) {
                    porcentagem = (int)(ac.getPorcentagem()*100) ;
                    relatorio += "\t\t";
                    
                    if ( ac.getPrazo() - ac.getTermino() > 0) {
                        relatorio += ac.getNome()+"("+ac.getAtividade().getNome()+")["+porcentagem+"%]: adiantado em "+Metodos.tempoEntre(ac.getTermino(), ac.getPrazo())+"\n";
                    } else {
                        relatorio += ac.getNome()+"("+ac.getAtividade().getNome()+")["+porcentagem+"%]: atrasado em "+Metodos.tempoEntre(ac.getPrazo(), ac.getTermino())+"\n";
                    }
                }
            }
            relatorio+= "\tAções em Andamento:\n";
            for (Acao ac:usr.getAcoes()) {
                if (ac.getAtividade().getProjeto() != projeto) {
                    continue;
                }
                if (ac.getPorcentagem() >= 0 && ac.getPorcentagem() < 1 && ac.getPrazo() - System.currentTimeMillis()/1000 >= 0) {
                    porcentagem = (int)(ac.getPorcentagem()*100) ;
                    relatorio += "\t\t";
                    relatorio += ac.getNome()+"("+ac.getAtividade().getNome()+")["+porcentagem+"%]: "+Metodos.tempoRestante(ac.getPrazo())+"\n";
                }
            }
            relatorio+= "\n";
            
        }
        relatorio+= "#STATUS ATIVIDADES\n";
            
        int numAcoesCompletas;
        int numAcoesAtrasadas;
        int numAcoesAndamento;
        for (Atividade at:projeto.getAtividades()) {
            numAcoesCompletas = 0;
            numAcoesAtrasadas = 0;
            numAcoesAndamento = 0;
            relatorio += at.getNome()+":\n";
            relatorio += "\tNúmero de Ações: "+at.getAcoes().size()+"\n";
            for (Acao ac:at.getAcoes()) {
                if (ac.getPorcentagem() < 1 && ac.getPrazo() - System.currentTimeMillis()/1000 >= 0) {
                    numAcoesAndamento++;
                } else if (ac.getPorcentagem() < 1) {
                    numAcoesAtrasadas++;
                } else {
                    numAcoesCompletas++;
                }
            }
            relatorio += "\tNúmero de Ações em Andamento: "+numAcoesAndamento+"\n";
            relatorio += "\tNúmero de Ações Atrasadas: "+numAcoesAtrasadas+"\n";
            relatorio += "\tNúmero de Ações Completas: "+numAcoesCompletas+"\n";
        }
        relatorio += "#STATUS AÇÕES\n";
        for (Atividade at:projeto.getAtividades()) {
            for (Acao ac:at.getAcoes()) {
                relatorio += ac.getNome() +"("+at.getNome()+"):\n";
                
                relatorio += "\tInício: "+Metodos.dataDDMM(ac.getInicio())+"\n";
                if (ac.getPorcentagem() == 1) {
                    relatorio +="\tTérmino: "+Metodos.dataDDMM(ac.getTermino())+"\n";
                }
                relatorio += "\tPrazo: "+Metodos.dataDDMM(ac.getPrazo())+"\n";
                
                float percent = ac.getPorcentagem();
                relatorio += "\tPorcentagem: "+(int)(percent*100)+"%\n";
                if (percent == 0) {
                    relatorio += "\tStatus: A Fazer\n";
                } else if (percent > 0 && percent < 1 ) {
                    relatorio += "\tStatus: Fazendo\n";
                } else {
                    relatorio += "\tStatus: Finalizado\n";
                }
                relatorio += "\tHistórico: "+String.join(" -> ",ac.getHistorico())+"\n";
            }
        }
        
        return relatorio;
    }
    
    private static String xS(String str, int qtd) {
        String out = "";
        if (qtd < 0) {
            return out;
        }
        for (int i = 0; i < qtd; i++) {
            out += str;
        }
        return out;
    }
    public static void main(String[] args) throws InterruptedException {
        Empresa emp = new Empresa("nome","senha");
        emp.salvarSenha("senha");
        emp.criarProjeto("nome", "descricao");
        emp.getProjetos()[0].criarAtividade("atividade 1", "#0000FF");
        emp.criarArea("financeiro");
        emp.criarUsuario("usuario", "senha");
        emp.getProjetos()[0].getAtividadePorNome("atividade 1").criarAcao("acao", "usuario", "financeiro", System.currentTimeMillis()/1000, 1701900000);
        emp.getProjetos()[0].getAtividadePorNome("atividade 1").criarAcao("acao 2", "usuario", "financeiro", 1701291822, 1701900000);
        emp.getProjetos()[0].getAtividadePorNome("atividade 1").criarAcao("acao 3", "usuario", "financeiro", 1701291822, 1701900000);
        emp.getProjetos()[0].getAtividadePorNome("atividade 1").getAcaoPorNome("acao 2").aumentarPorcentagem(1);
        emp.getProjetos()[0].getAtividadePorNome("atividade 1").getAcaoPorNome("acao").aumentarPorcentagem(1);
//        emp.getProjetos()[0].getAtividadePorNome("atividade 1").getAcaoPorNome("acao 2").aumentarPorcentagem(-0.5);
//        emp.getProjetos()[0].getAtividadePorNome("atividade 1").getAcaoPorNome("acao 3").aumentarPorcentagem(1.7);
        System.out.println(criarRelatorio(emp.getProjetos()[0]));
//        TimeUnit.SECONDS.sleep(5);
//        emp.getProjetos()[0].getAtividadePorNome("atividade 1").getAcaoPorNome("acao").aumentarPorcentagem(0.5);
//        TimeUnit.SECONDS.sleep(5);
//        emp.getProjetos()[0].getAtividadePorNome("atividade 1").getAcaoPorNome("acao").aumentarPorcentagem(0.6);
//        System.out.println(emp.getProjetos()[0].getAtividadePorNome("atividade 1").getAcaoPorNome("acao").getHistorico());;        
    }
}
