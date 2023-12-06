package controllers;

import entities.Empresa;
import entities.Projeto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import kanban.Kanban;

public class SelectProjectController {

    @FXML
    private ImageView editarProjetoDois;

    @FXML
    private ImageView editarProjetoQuatro;

    @FXML
    private ImageView editarProjetoTres;

    @FXML
    private ImageView editarProjetoUm;

    @FXML
    private Label errorNovoProjeto;

    @FXML
    private ImageView excluirProjetoDois;

    @FXML
    private ImageView excluirProjetoQuatro;

    @FXML
    private ImageView excluirProjetoTres;

    @FXML
    private ImageView excluirProjetoUm;

    @FXML
    private Button logout;

    @FXML
    private Label nomeProjetoDois;

    @FXML
    private Label nomeProjetoQuatro;

    @FXML
    private Label nomeProjetoTres;

    @FXML
    private Label nomeProjetoUm;

    @FXML
    private Button novaArea;

    @FXML
    private Button novoProjeto;

    @FXML
    private Button novoUsuario;

    @FXML
    private Label numPostProjetoDois;

    @FXML
    private Label numPostProjetoQuatro;

    @FXML
    private Label numPostProjetoTres;

    @FXML
    private Label numPostProjetoUm;

    @FXML
    private ProgressIndicator progressoTotalDois;

    @FXML
    private ProgressIndicator progressoTotalQuatro;

    @FXML
    private ProgressIndicator progressoTotalTres;

    @FXML
    private ProgressIndicator progressoTotalUm;

    @FXML
    private Pane projetoDois;

    @FXML
    private Pane projetoQuatro;

    @FXML
    private Pane projetoTres;

    @FXML
    private Pane projetoUm;

    @FXML
    private Label tituloCriarNovaArea;

    @FXML
    private Label tituloCriarNovoProjeto;

    @FXML
    private Label tituloCriarNovoUsuario;
    
    @FXML
    private void editarProjetoUm(MouseEvent event) {
        // verificar se projeto existe
        if (!Kanban.loginAdmin || Kanban.empresaAtual().getProjetos()[0] == null) {
            return;
        }
        Kanban.projetoAberto = 0;
        Kanban.telas("newName", event);
    }
    @FXML
    private void editarProjetoDois(MouseEvent event) {
        // verificar se projeto existe
        if (!Kanban.loginAdmin || Kanban.empresaAtual().getProjetos()[1] == null) {
            return;
        }
        Kanban.projetoAberto = 1;
        Kanban.telas("newName", event);
    }

    @FXML
    private void editarProjetoTres(MouseEvent event) {
        // verificar se projeto existe
        if (!Kanban.loginAdmin || Kanban.empresaAtual().getProjetos()[2] == null) {
            return;
        }
        Kanban.projetoAberto = 2;
        Kanban.telas("newName", event);
    }

    
    @FXML
    private void editarProjetoQuatro(MouseEvent event) {
        // verificar se projeto existe
        if (!Kanban.loginAdmin || Kanban.empresaAtual().getProjetos()[3] == null) {
            return;
        }
        Kanban.projetoAberto = 3;
        Kanban.telas("newName", event);
    }
    
    @FXML
    private void excluirProjetoUm(MouseEvent event) {
        if (!Kanban.loginAdmin || Kanban.empresaAtual().getProjetos()[0] == null) {
            return;
        }
        Alert alert = new Alert(AlertType.CONFIRMATION, "Deletar "+Kanban.empresaAtual().getProjetos()[0].getNome()+" ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            Kanban.empresaAtual().deletarProjeto(0);
            loadProjetos();
        }
        
    }
    @FXML
    private void excluirProjetoDois(MouseEvent event) {
        if (!Kanban.loginAdmin || Kanban.empresaAtual().getProjetos()[1] == null) {
            return;
        }
        Alert alert = new Alert(AlertType.CONFIRMATION, "Deletar "+Kanban.empresaAtual().getProjetos()[1].getNome()+" ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            Kanban.empresaAtual().deletarProjeto(1);
            loadProjetos();
        }
    }

    @FXML
    private void excluirProjetoTres(MouseEvent event) {
        if (!Kanban.loginAdmin || Kanban.empresaAtual().getProjetos()[2] == null) {
            return;
        }
        Alert alert = new Alert(AlertType.CONFIRMATION, "Deletar "+Kanban.empresaAtual().getProjetos()[2].getNome()+" ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            Kanban.empresaAtual().deletarProjeto(2);
            loadProjetos();
        }
    }
    @FXML
    private void excluirProjetoQuatro(MouseEvent event) {
        if (!Kanban.loginAdmin || Kanban.empresaAtual().getProjetos()[3] == null) {
            return;
        }
        Alert alert = new Alert(AlertType.CONFIRMATION, "Deletar "+Kanban.empresaAtual().getProjetos()[3].getNome()+" ?", ButtonType.YES, ButtonType.NO);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            Kanban.empresaAtual().deletarProjeto(3);
            loadProjetos();
        }
    }

    @FXML
    private void logout(ActionEvent event) {
        if (Kanban.loginAdmin) {
            Kanban.empresaAtual().logout();
        } else {
            Kanban.empresaAtual().getUsuarioPorNome(Kanban.currentUser).logout();
        }
        Kanban.telas("loginPage", event);
    }
    
    @FXML
    private void novaArea(ActionEvent event) {
        if (Kanban.loginAdmin) {
            Kanban.telas("newArea");
       }
    }

    @FXML
    private void novoProjeto(ActionEvent event) {
       // verificar a quantidade de projetos criados
       // Apenas são possíveis até 4 projetos (errorNovoProjeto)
       // trocar opacidade e cursor (projeto, editar e excluir)
        if (!Kanban.loginAdmin) {
            return;
        }
        for (Projeto projeto:Kanban.empresaAtual().getProjetos()) {
             if (projeto == null) {
                 Kanban.telas("createProject", event);
                 return;
             }
         }
        errorNovoProjeto.setText("Limite de 4 Projetos excedido.");
    }
    
    @FXML
    private void novoUsuario(ActionEvent event) {
        if (Kanban.loginAdmin) {
            Kanban.telas("newUser", event);
       }
    }
    
    @FXML
    private void projetoUm(MouseEvent event) {
        if (Kanban.empresaAtual().getProjetos()[0] != null) {
            Kanban.projetoAberto = 0;
            Kanban.telas("kanbanPage", event);
        }
    }
    
    @FXML
    private void projetoDois(MouseEvent event) {
        if (Kanban.empresaAtual().getProjetos()[1] != null) {
            Kanban.projetoAberto = 1;
            Kanban.telas("kanbanPage", event);
        }
    }
    @FXML
    private void projetoTres(MouseEvent event) {
        if (Kanban.empresaAtual().getProjetos()[2] != null) {
            Kanban.projetoAberto = 2;
            Kanban.telas("kanbanPage", event);
        }
    }
    @FXML
    private void projetoQuatro(MouseEvent event) {
        if (Kanban.empresaAtual().getProjetos()[3] != null) {
            Kanban.projetoAberto = 3;
            Kanban.telas("kanbanPage", event);
        }
    }

    

    
    
    public void esconderElementos() {
        int opacidade = Kanban.loginAdmin ? 1:0;
        errorNovoProjeto.setText("");
        novoProjeto.setOpacity(opacidade);
        tituloCriarNovoProjeto.setOpacity(opacidade);
        novoUsuario.setOpacity(opacidade);
        tituloCriarNovoUsuario.setOpacity(opacidade);
        novaArea.setOpacity(opacidade);
        tituloCriarNovaArea.setOpacity(opacidade);
        opacidade = 0;
        editarProjetoUm.setOpacity(opacidade);
        excluirProjetoUm.setOpacity(opacidade);        
        editarProjetoDois.setOpacity(opacidade);
        excluirProjetoDois.setOpacity(opacidade);
        editarProjetoTres.setOpacity(opacidade);
        excluirProjetoTres.setOpacity(opacidade);
        editarProjetoQuatro.setOpacity(opacidade);
        excluirProjetoQuatro.setOpacity(opacidade);
    }

    public void loadProjetos() {
        Pane[] projetos = {projetoUm, projetoDois, projetoTres, projetoQuatro};
        Label[] projetoNomes = {nomeProjetoUm, nomeProjetoDois, nomeProjetoTres, nomeProjetoQuatro};
        Label[] projetoQtdAcoes = {numPostProjetoUm, numPostProjetoDois, numPostProjetoTres, numPostProjetoQuatro};
        ProgressIndicator[] projetoProgressos = {progressoTotalUm, progressoTotalDois, progressoTotalTres, progressoTotalQuatro};
        ImageView[] projetosExcluir = {excluirProjetoUm, excluirProjetoDois, excluirProjetoTres, excluirProjetoQuatro};
        ImageView[] projetosEditar = {editarProjetoUm, editarProjetoDois, editarProjetoTres, editarProjetoQuatro};
        Empresa empresa = Kanban.empresaAtual();
        Projeto[] projetosE = empresa.getProjetos();
        for (int i = 0; i < 4; i++) {
            int opacidade = projetosE[i] != null ? 1:0;
            projetos[i].setOpacity(opacidade);
            if (projetosE[i] != null && Kanban.loginAdmin) {
                projetosExcluir[i].setCursor(Cursor.CLOSED_HAND);
                projetosEditar[i].setCursor(Cursor.CLOSED_HAND);
                projetosExcluir[i].setOpacity(1);
                projetosEditar[i].setOpacity(1);
                projetos[i].setCursor(Cursor.CLOSED_HAND);
            }
            if (projetosE[i] != null && !Kanban.loginAdmin) {
                projetosExcluir[i].setCursor(Cursor.DEFAULT);
                projetosEditar[i].setCursor(Cursor.DEFAULT);
                projetosExcluir[i].setOpacity(0);
                projetosEditar[i].setOpacity(0);
                projetos[i].setCursor(Cursor.CLOSED_HAND);
            }
            if (projetosE[i] == null ) {
                projetosExcluir[i].setCursor(Cursor.DEFAULT);
                projetosEditar[i].setCursor(Cursor.DEFAULT);
                projetos[i].setCursor(Cursor.DEFAULT);
                projetosExcluir[i].setOpacity(0);
                projetosEditar[i].setOpacity(0);
                continue;
            }
            
            
            projetoNomes[i].setText(projetosE[i].getNome());
            int numPostIts = projetosE[i].numAcoes();
            if (numPostIts == 1) {
                String mensagem = Integer.toString(numPostIts)+" post-it";
                projetoQtdAcoes[i].setText(mensagem);
            } else {
                String mensagem = Integer.toString(numPostIts)+" post-its";
                projetoQtdAcoes[i].setText(mensagem);
            }
            projetoProgressos[i].setProgress(projetosE[i].getPorcentagem());
            
        }
    }
}
