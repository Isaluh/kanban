package controllers;

import entities.Acao;
import entities.Atividade;
import entities.PostIt;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import kanban.Kanban;
import utils.Relatorio;

public class KanbanPageController implements Initializable {

    @FXML
    private Label aFazerAreaDois;

    @FXML
    private Label aFazerAreaQuatro;

    @FXML
    private Label aFazerAreaTres;

    @FXML
    private Label aFazerAreaUm;
    
    @FXML
    private Button novoRelatorio;

    @FXML
    private Label tituloGerarRelatorio;

    @FXML
    private ProgressBar aFazerBarraPorcentDois;

    @FXML
    private ProgressBar aFazerBarraPorcentQuatro;

    @FXML
    private ProgressBar aFazerBarraPorcentTres;

    @FXML
    private ProgressBar aFazerBarraPorcentUm;

    @FXML
    private Pane aFazerCorEscolhidaBarraDois;

    @FXML
    private Pane aFazerCorEscolhidaBarraQuatro;

    @FXML
    private Pane aFazerCorEscolhidaBarraUm;

    @FXML
    private Pane aFazerCorEscolhidaDois;

    @FXML
    private Pane aFazerCorEscolhidaQuatro;

    @FXML
    private Pane aFazerCorEscolhidaTres;

    @FXML
    private Pane aFazerCorEscolhidaUm;

    @FXML
    private Pane aFazerDois;

    @FXML
    private Label aFazerDuracaoDois;

    @FXML
    private Label aFazerDuracaoQuatro;

    @FXML
    private Label aFazerDuracaoTres;

    @FXML
    private Label aFazerDuracaoUm;

    @FXML
    private Label aFazerInicioFimDois;

    @FXML
    private Label aFazerInicioFimQuatro;

    @FXML
    private Label aFazerInicioFimTres;

    @FXML
    private Label aFazerInicioFimUm;

    @FXML
    private ImageView aFazerMaisDois;

    @FXML
    private ImageView aFazerMaisQuatro;

    @FXML
    private ImageView aFazerMaisTres;

    @FXML
    private ImageView aFazerMaisUm;

    @FXML
    private ImageView aFazerMenosDois;

    @FXML
    private ImageView aFazerMenosQuatro;

    @FXML
    private ImageView aFazerMenosTres;

    @FXML
    private ImageView aFazerMenosUm;

    @FXML
    private Label aFazerNomeDois;

    @FXML
    private Label aFazerNomeQuatro;

    @FXML
    private Label aFazerNomeTres;

    @FXML
    private Label aFazerNomeUm;

    @FXML
    private Label aFazerPorcentDois;

    @FXML
    private Label aFazerPorcentQuatro;

    @FXML
    private Label aFazerPorcentTres;

    @FXML
    private Label aFazerPorcentUm;

    @FXML
    private ImageView aFazerProximo;

    @FXML
    private Pane aFazerQuatro;

    @FXML
    private Pane aFazerTres;

    @FXML
    private Pane aFazerUm;

    @FXML
    private Label aFazerUsuarioDois;

    @FXML
    private Label aFazerUsuarioQuatro;

    @FXML
    private Label aFazerUsuarioTres;

    @FXML
    private Label aFazerUsuarioUm;

    @FXML
    private ImageView deletarAcao;

    @FXML
    private Label errorKanbanLixo;

    @FXML
    private Label fazendoAreaDois;

    @FXML
    private Label fazendoAreaQuatro;

    @FXML
    private Label fazendoAreaTres;

    @FXML
    private Label fazendoAreaUm;

    @FXML
    private ProgressBar fazendoBarraPorcentDois;

    @FXML
    private ProgressBar fazendoBarraPorcentQuatro;

    @FXML
    private ProgressBar fazendoBarraPorcentTres;

    @FXML
    private ProgressBar fazendoBarraPorcentUm;

    @FXML
    private Pane fazendoCorEscolhidaBarraDois;

    @FXML
    private Pane fazendoCorEscolhidaBarraQuatro;

    @FXML
    private Pane fazendoCorEscolhidaBarraTres;

    @FXML
    private Pane fazendoCorEscolhidaDois;

    @FXML
    private Pane fazendoCorEscolhidaQuatro;

    @FXML
    private Pane fazendoCorEscolhidaTres;

    @FXML
    private Pane fazendoCorEscolhidaUm;

    @FXML
    private Pane fazendoDois;

    @FXML
    private Label fazendoDuracaoDois;

    @FXML
    private Label fazendoDuracaoQuatro;

    @FXML
    private Label fazendoDuracaoTres;

    @FXML
    private Label fazendoDuracaoUm;

    @FXML
    private Label fazendoInicioFimDois;

    @FXML
    private Label fazendoInicioFimQuatro;

    @FXML
    private Label fazendoInicioFimTres;

    @FXML
    private Label fazendoInicioFimUm;

    @FXML
    private ImageView fazendoMaisDois;

    @FXML
    private ImageView fazendoMaisQuatro;

    @FXML
    private ImageView fazendoMaisTres;

    @FXML
    private ImageView fazendoMaisUm;

    @FXML
    private ImageView fazendoMenosDois;

    @FXML
    private ImageView fazendoMenosQuatro;

    @FXML
    private ImageView fazendoMenosTres;

    @FXML
    private ImageView fazendoMenosUm;

    @FXML
    private Label fazendoNomeDois;

    @FXML
    private Label fazendoNomeQuatro;

    @FXML
    private Label fazendoNomeTres;

    @FXML
    private Label fazendoNomeUm;

    @FXML
    private Label fazendoPorcentDois;

    @FXML
    private Label fazendoPorcentQuatro;

    @FXML
    private Label fazendoPorcentTres;

    @FXML
    private Label fazendoPorcentUm;

    @FXML
    private ImageView fazendoProximo;

    @FXML
    private Pane fazendoQuatro;

    @FXML
    private Pane fazendoTres;

    @FXML
    private Pane fazendoUm;

    @FXML
    private Label fazendoUsuarioDois;

    @FXML
    private Label fazendoUsuarioQuatro;

    @FXML
    private Label fazendoUsuarioTres;

    @FXML
    private Label fazendoUsuarioUm;

    @FXML
    private Label finalizadoAreaDois;

    @FXML
    private Label finalizadoAreaQuatro;

    @FXML
    private Label finalizadoAreaTres;

    @FXML
    private Label finalizadoAreaUm;

    @FXML
    private ProgressBar finalizadoBarraPorcentDois;

    @FXML
    private ProgressBar finalizadoBarraPorcentQuatro;

    @FXML
    private ProgressBar finalizadoBarraPorcentTres;

    @FXML
    private ProgressBar finalizadoBarraPorcentUm;

    @FXML
    private Pane finalizadoCorEscolhidaBarraDois;

    @FXML
    private Pane finalizadoCorEscolhidaBarraQuatro;

    @FXML
    private Pane finalizadoCorEscolhidaBarraTres;

    @FXML
    private Pane finalizadoCorEscolhidaBarraUm;

    @FXML
    private Pane finalizadoCorEscolhidaDois;

    @FXML
    private Pane finalizadoCorEscolhidaQuatro;

    @FXML
    private Pane finalizadoCorEscolhidaTres;

    @FXML
    private Pane finalizadoCorEscolhidaUm;

    @FXML
    private Pane finalizadoDois;

    @FXML
    private Label finalizadoDuracaoDois;

    @FXML
    private Label finalizadoDuracaoQuatro;

    @FXML
    private Label finalizadoDuracaoTres;

    @FXML
    private Label finalizadoDuracaoUm;

    @FXML
    private Label finalizadoInicioFimDois;

    @FXML
    private Label finalizadoInicioFimQuatro;

    @FXML
    private Label finalizadoInicioFimTres;

    @FXML
    private Label finalizadoInicioFimUm;

    @FXML
    private ImageView finalizadoMaisDois;

    @FXML
    private ImageView finalizadoMaisQuatro;

    @FXML
    private ImageView finalizadoMaisTres;

    @FXML
    private ImageView finalizadoMaisUm;

    @FXML
    private ImageView finalizadoMenosDois;

    @FXML
    private ImageView finalizadoMenosQuatro;

    @FXML
    private ImageView finalizadoMenosTres;

    @FXML
    private ImageView finalizadoMenosUm;

    @FXML
    private Label finalizadoNomeDois;

    @FXML
    private Label finalizadoNomeQuatro;

    @FXML
    private Label finalizadoNomeTres;

    @FXML
    private Label finalizadoNomeUm;

    @FXML
    private Label finalizadoPorcentDois;

    @FXML
    private Label finalizadoPorcentQuatro;

    @FXML
    private Label finalizadoPorcentTres;

    @FXML
    private Label finalizadoPorcentUm;

    @FXML
    private ImageView finalizadoProximo;

    @FXML
    private Pane finalizadoQuatro;

    @FXML
    private Pane finalizadoTres;

    @FXML
    private Pane finalizadoUm;

    @FXML
    private Label finalizadoUsuarioDois;

    @FXML
    private Label finalizadoUsuarioQuatro;

    @FXML
    private Label finalizadoUsuarioTres;

    @FXML
    private Label finalizadoUsuarioUm;

    @FXML
    private Button logout;

    @FXML
    private Button novaAcao;

    @FXML
    private Button novaAtividade;

    @FXML
    private Label tituloNovaAcao;

    @FXML
    private Label tituloNovaAtividade;

    @FXML
    private Button voltarProjetos;
    
    private boolean lixeiraAberta;
    
    public PostIt[][] postIts;
    public static int[] postItSelecionado;
    public static ArrayList<Acao> aFazer;
    public static ArrayList<Acao> fazendo;
    public static ArrayList<Acao> finalizado;

    @FXML
    private void aFazerProximo(MouseEvent event) {
        if (aFazer.size() > (Kanban.paginaAFazer + 1)*4) {
            Kanban.paginaAFazer++;
        } else {
            Kanban.paginaAFazer = 0;
        }
        loadAtividades();
    }
    
    @FXML
    private void deletarAcao(MouseEvent event) {
//        errorKanbanLixo;
//        lixeiraAberta;
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Deletar "+PostIt.selecionados.size()+" ações ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();
        if (alert.getResult() == ButtonType.YES) {
            PostIt.deletarAcoes();
        } else if (alert.getResult() == ButtonType.NO) {
            PostIt.cancelarSelecao();
        }
    }

    @FXML
    private void fazendoProximo(MouseEvent event) {
        if (fazendo.size() > (Kanban.paginaFazendo + 1)*4) {
            Kanban.paginaFazendo++;
        } else {
            Kanban.paginaFazendo = 0;
        }
        loadAtividades();
    }

    @FXML
    private void finalizadoProximo(MouseEvent event) {
        if (finalizado.size() > (Kanban.paginaFinalizado + 1)*4) {
            Kanban.paginaFinalizado++;
        } else {
            Kanban.paginaFinalizado = 0;
        }
        loadAtividades();
    }

    @FXML
    private void logout(ActionEvent event) {
        errorKanbanLixo.setText("");
        if (Kanban.loginAdmin) {
            Kanban.empresaAtual().logout();
        } else {
            Kanban.empresaAtual().getUsuarioPorNome(Kanban.currentUser).logout();
        }
        PostIt.cancelarSelecao();
        Kanban.telas("loginPage");
    }

    @FXML
    private void novaAcao(ActionEvent event) {
        if (Kanban.loginAdmin) {
            errorKanbanLixo.setText("");
            PostIt.cancelarSelecao();
            Kanban.telas("newAction");
       }
        
    }
    
    @FXML
    private void novaAtividade(ActionEvent event) {
        if (Kanban.loginAdmin) {
            errorKanbanLixo.setText("");
            PostIt.cancelarSelecao();
            Kanban.telas("newActivit");
       }
        
    }
    
    @FXML
   private void novoRelatorio(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(
                 new ExtensionFilter("Text Files", "*.txt")
        );
         File selectedFile = fileChooser.showSaveDialog(Kanban.telaSelecionada);
         if (selectedFile != null) {
            try (BufferedWriter br = Files.newBufferedWriter(selectedFile.toPath(), StandardCharsets.UTF_8)) {
                br.write(Relatorio.criarRelatorio(Kanban.empresaAtual().getProjetos()[Kanban.projetoAberto]));
            }
         }
    }

    @FXML
    private void voltarProjetos(ActionEvent event) {
        errorKanbanLixo.setText("");
        PostIt.cancelarSelecao();
        Kanban.telas("selectProject");
    }
    
    public void esconderElementos() {
        int opacidade = Kanban.loginAdmin ? 1:0;
        novaAcao.setOpacity(opacidade);
        tituloNovaAcao.setOpacity(opacidade);
        novaAtividade.setOpacity(opacidade);
        tituloNovaAtividade.setOpacity(opacidade);
        deletarAcao.setOpacity(0);
        if (!Kanban.loginAdmin) {
            novaAtividade.setCursor(Cursor.DEFAULT);
            novaAcao.setCursor(Cursor.DEFAULT);
            novoRelatorio.setLayoutX(65);
            novoRelatorio.setLayoutY(70);
            tituloGerarRelatorio.setLayoutX(129);
            tituloGerarRelatorio.setLayoutY(77);
        } else {
            novaAtividade.setCursor(Cursor.HAND);
            novaAcao.setCursor(Cursor.HAND);
            novoRelatorio.setLayoutX(626);
            novoRelatorio.setLayoutY(70);
            tituloGerarRelatorio.setLayoutX(690);
            tituloGerarRelatorio.setLayoutY(77);
            
        }
    }

    public void loadAtividades() {
        Scene cena = Kanban.sceneKanbanPage;
        aFazer = new ArrayList<>();
        fazendo = new ArrayList<>();
        finalizado = new ArrayList<>();
        if (PostIt.selecionando && Kanban.loginAdmin) {
            deletarAcao.setOpacity(1);
            deletarAcao.setCursor(Cursor.HAND);
        } else {
            deletarAcao.setOpacity(0);
            deletarAcao.setCursor(Cursor.DEFAULT);
        }
        for (Atividade at:Kanban.empresaAtual().getProjetos()[Kanban.projetoAberto].getAtividades()) {
            for (Acao ac:at.getAcoes()) {
                float porcentagem = ac.getPorcentagem();
                if (porcentagem <= 0) {
                    aFazer.add(ac);
                }
                if (porcentagem > 0 && porcentagem < 1) {
                    fazendo.add(ac);
                }
                if (porcentagem >= 1) {
                    finalizado.add(ac);
                }
            }
        }
        if (aFazer.size() > 4) {
            aFazerProximo.setOpacity(1);
        } else {
            aFazerProximo.setOpacity(0);
        }
        if (fazendo.size() > 4) {
            fazendoProximo.setOpacity(1);
        } else {
            fazendoProximo.setOpacity(0);
        }
        if (finalizado.size() > 4) {
            finalizadoProximo.setOpacity(1);
        } else {
            finalizadoProximo.setOpacity(0);
        }
        if (Kanban.paginaAFazer > 0 || Kanban.paginaFazendo > 0 ||Kanban.paginaFinalizado > 0 ) {
            if (Kanban.paginaAFazer >= aFazer.size() / 4.0) {
                Kanban.paginaAFazer = aFazer.size() / 4 - 1;
            }
            if (Kanban.paginaFazendo >= fazendo.size() / 4.0) {
                Kanban.paginaFazendo = fazendo.size() / 4 - 1;
            }
            if (Kanban.paginaFinalizado >= finalizado.size() / 4.0) {
                Kanban.paginaFinalizado = finalizado.size() / 4 - 1;
            }
        }
        for (PostIt[] i:postIts) {
            for (PostIt postIt:i) {
                postIt.sePreencher();
            }
        }
        
    }

    @FXML
    public void teste(ActionEvent e) {
        
    }
    
    public void definirPostIts(Scene cena) {
        String[] secoes = {"aFazer", "fazendo","finalizado"};
        String[] numerais = {"Um","Dois","Tres","Quatro"};
        this.postIts = new PostIt[secoes.length][numerais.length];
        int i = 0;
        int j = 0;
        for (String secao: secoes) {
            j = 0;
            for (String numero:numerais) {
                postIts[i][j++] = new PostIt(cena, i, j-1);
            }
            i++;
        }
        
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        lixeiraAberta = false;
        postItSelecionado = new int[2];
        postItSelecionado[0] = -1;
        postItSelecionado[1] = -1;
    }
}
