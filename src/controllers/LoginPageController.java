package controllers;

import entities.Usuario;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import kanban.Kanban;
import utils.Metodos;

public class LoginPageController {
    
    @FXML
    private PasswordField cadastroConfirmarSenhaEmpresa;

    @FXML
    private TextField cadastroNomeEmpresa;

    @FXML
    private PasswordField cadastroSenhaEmpresa;

    @FXML
    private TextField entrarNomeEmpresa;

    @FXML
    private PasswordField entrarSenhaEmpresa;

    @FXML
    private Button entrarUsuario;

    @FXML
    private Label errorCadastro;

    @FXML
    private Label errorEntrar;

    @FXML
    private Button novoCadastro;

    @FXML
    private void entrarUsuario(ActionEvent event) throws IOException {
        String nomeInserido = entrarNomeEmpresa.getText();
        String senhaInserida = entrarSenhaEmpresa.getText();
        boolean nomeCadastrado = false;
        
        if (nomeInserido.equals("") || senhaInserida.equals("")) {
            errorEntrar.setText("Há campos em branco");
        } else {
            errorEntrar.setText("");
            for (entities.Empresa empresa:Kanban.empresas) {
                if (empresa == null) {
                    continue;
                }                
                if (empresa.getNome().equals(nomeInserido)) {
                    nomeCadastrado = true;
                    empresa.salvarSenha(senhaInserida);
                    if (empresa.auth()) {
                        Kanban.currentUser = empresa.getNome();
                        Kanban.loginAdmin = true;
                        Kanban.telas("selectProject", event);
                        entrarNomeEmpresa.clear();
                        entrarSenhaEmpresa.clear();
                        errorCadastro.setText("");
                        cadastroNomeEmpresa.clear();
                        cadastroSenhaEmpresa.clear();
                        cadastroConfirmarSenhaEmpresa.clear();
                    } else {
                        errorEntrar.setText("Senha está inválida");
                    }
                    break;
                }
                for (Usuario user:empresa.getUsuarios()) {
                    if (nomeInserido.equals(user.getNome())) {
                        nomeCadastrado = true;
                        user.salvarSenha(senhaInserida);
                        if (user.auth()) {
                            Kanban.currentUser = user.getNome();
                            Kanban.loginAdmin = false;
                            Kanban.telas("selectProject", event);
                            entrarNomeEmpresa.clear();
                            entrarSenhaEmpresa.clear();
                            errorCadastro.setText("");
                            cadastroNomeEmpresa.clear();
                            cadastroSenhaEmpresa.clear();
                            cadastroConfirmarSenhaEmpresa.clear();
                        }
                        else {
                            errorEntrar.setText("Senha está inválida");
                        }
                        break;
                    }
                }
            }
            if (!nomeCadastrado) {
                errorEntrar.setText("Empresa/Usuario não cadastrado");
            }
        }
    }

    @FXML
    private void novoCadastro(ActionEvent event) {
        String nomeInserido = cadastroNomeEmpresa.getText();
        String senhaInformada = cadastroSenhaEmpresa.getText();
        String senha2Informada = cadastroConfirmarSenhaEmpresa.getText();
        if(nomeInserido.equals("") || senhaInformada.equals("") || senha2Informada.equals("")) {
            errorCadastro.setText("Há campos em branco");
        } else if (!Metodos.verificarEspacos(nomeInserido)) {
            errorCadastro.setText("Política de uso de espaços inadequada");
        }
        else{
            for(entities.Empresa empresa: Kanban.empresas){
                if (empresa == null) {
                    continue;
                }
                if(empresa.getNome().equals(nomeInserido)){
                    errorCadastro.setText("Empresa já cadastrada");
                    return;
                }
                for (entities.Usuario usr:empresa.getUsuarios()) {
                    if (nomeInserido.equals(usr.getNome())) {
                    errorCadastro.setText("Nome já utilizado para usuário");
                    return;
                    }
                }
            }
            if (senhaInformada.equals(senha2Informada)) {
                for (int i = 0; i < Kanban.empresas.length; i++) {
                    if (Kanban.empresas[i] == null) {
                        Kanban.empresas[i] = new entities.Empresa(nomeInserido, senhaInformada);
                        break;
                    }
                }
                errorCadastro.setText("");
                cadastroNomeEmpresa.clear();
                cadastroSenhaEmpresa.clear();
                cadastroConfirmarSenhaEmpresa.clear();
            } else {
                errorCadastro.setText("Senhas diferem entre sí");

            }
            
        }

    }

}
