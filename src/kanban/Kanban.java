package kanban;

import controllers.CreateProjectController;
import controllers.KanbanPageController;
import controllers.NewActionController;
import controllers.NewActivitController;
import controllers.NewAreaController;
import controllers.NewNameProjectController;
import controllers.NewUserController;
import controllers.ReloadController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import entities.Empresa;
import controllers.SelectProjectController;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;

public class Kanban extends Application {
    public static Stage telaSelecionada;
    private static Scene sceneSelectProject;
    private static SelectProjectController controllerSelectProject;
    private static Scene sceneCreateProject;
    public static CreateProjectController controllerCreateProject;
    public static Scene sceneKanbanPage;
    public static KanbanPageController controllerKanbanPage;
    private static ReloadController controllerReload;
    public static Scene sceneReload;
    private static Scene sceneNewNameProject;
    public static NewNameProjectController controllerNewNameProject;
    private static Scene sceneNewAction;
    public static NewActionController controllerNewAction;
    private static Scene sceneLoginPage;
    private static Scene sceneNewActivit;
    public static NewActivitController controllerNewActivity;
    private static Scene sceneNewUser;
    public static NewUserController controllerNewUser;
    private static Scene sceneNewArea;
    public static NewAreaController controllerNewArea;
    public static Empresa[] empresas;
    public static String currentUser;
    public static int projetoAberto;
    public static boolean loginAdmin;
    public static int paginaAFazer;
    public static int paginaFazendo;
    public static int paginaFinalizado;
    
    // Método estático para obter a empresa atual com base no login do usuário.
    public static Empresa empresaAtual() {
        // Verifica se o login é de um administrador.
        if (loginAdmin) {
            // Itera sobre as empresas para encontrar a empresa com o mesmo nome do usuário atual.
            for (Empresa emp:empresas) {
                if (currentUser.equals(emp.getNome())) {
                    return emp;
                }
            }
        } else {
            // Itera sobre as empresas para encontrar a empresa que contém o usuário atual.
            for (Empresa emp:empresas) {
                // Verifica se a empresa não é nula e se o usuário atual pertence a ela.
                if (emp != null && emp.getUsuarioPorNome(currentUser) != null) {
                    return emp;
                }
            }
        }
        // Retorna null se não encontrar a empresa.
        return null;
    }

    @Override
    // Método de inicialização do aplicativo.
    public void start(Stage stage) throws Exception {
        // Configurações iniciais.
        telaSelecionada = stage;
        empresas = new Empresa[32];
        loginAdmin = false;
        paginaAFazer = 0;
        paginaFazendo = 0;
        paginaFinalizado = 0;
        
        // Criação de uma empresa de exemplo.
        empresas[0] = new Empresa("a","a");
        empresas[0].salvarSenha("a");
        empresas[0].criarProjeto("Projeto 1", "Projeto Criado Automaticamente");
        empresas[0].criarUsuario("Usuario 1", "123");
        empresas[0].criarUsuario("Usuario 2", "123");
        empresas[0].criarArea("Area 1");
        empresas[0].logout();
        
        // Carregamento das telas usando FXMLLoader.
        Parent fxmlLoginPage = FXMLLoader.load(getClass().getResource("/views/loginPage.fxml"));
        sceneLoginPage = new Scene(fxmlLoginPage,  1535, 800);
        
        FXMLLoader fxmlSelectProject = new FXMLLoader(getClass().getResource("/views/selectProject.fxml"));
        sceneSelectProject = new Scene(fxmlSelectProject.load(),  1535, 800);
        controllerSelectProject = fxmlSelectProject.getController();
        
        
        FXMLLoader fxmlcreateProject = new FXMLLoader(getClass().getResource("/views/createProject.fxml"));
        sceneCreateProject = new Scene(fxmlcreateProject.load(),  1535, 800);
        controllerCreateProject = fxmlcreateProject.getController();        
        
        FXMLLoader fxmlNewNameProject = new FXMLLoader(getClass().getResource("/views/newNameProject.fxml"));
        sceneNewNameProject = new Scene(fxmlNewNameProject.load(),  1535, 800);
        controllerNewNameProject = fxmlNewNameProject.getController();
        
        FXMLLoader fxmlKanbanPage = new FXMLLoader(getClass().getResource("/views/kanbanPage.fxml"));
        sceneKanbanPage = new Scene(fxmlKanbanPage.load(),  1535, 800);
        controllerKanbanPage = fxmlKanbanPage.getController();
        controllerKanbanPage.definirPostIts(sceneKanbanPage);
        
        FXMLLoader fxmlReload = new FXMLLoader(getClass().getResource("/views/reload.fxml"));
        sceneReload = new Scene(fxmlReload.load(),  1535, 800);
        controllerReload = fxmlReload.getController();
        controllerReload.definirPostIts(sceneReload);
        
        FXMLLoader fxmlNewAction = new FXMLLoader(getClass().getResource("/views/newAction.fxml"));
        sceneNewAction = new Scene(fxmlNewAction.load(),  1535, 800);
        controllerNewAction = fxmlNewAction.getController();
        controllerNewAction.definirPostIts(sceneNewAction);
        
        FXMLLoader fxmlNewActivit = new FXMLLoader(getClass().getResource("/views/newActivit.fxml"));
        sceneNewActivit = new Scene(fxmlNewActivit.load(),  1535, 800);
        controllerNewActivity = fxmlNewActivit.getController();
        controllerNewActivity.definirPostIts(sceneNewActivit);
        
        FXMLLoader fxmlNewUser = new FXMLLoader(getClass().getResource("/views/newUser.fxml"));
        sceneNewUser = new Scene(fxmlNewUser.load(),  1535, 800);
        controllerNewUser = fxmlNewUser.getController();
        
        
        FXMLLoader fxmlNewArea = new FXMLLoader(getClass().getResource("/views/newArea.fxml"));
        sceneNewArea = new Scene(fxmlNewArea.load(),  1535, 800);
        controllerNewArea = fxmlNewArea.getController();
        
        // Configuração do ícone da aplicação.
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/assets/imgs/imgKanban.png")));
        
        // Configuração da cena inicial.
        stage.setScene(sceneLoginPage);
        stage.show();
    }
    
    // Sobrecargas do método "telas" para lidar com eventos específicos.
    public static void telas(String tela, ActionEvent event){
        telas(tela);
    }
    public static void telas(String tela, MouseEvent event){
        telas(tela);
    }
    // Método de navegação entre telas do aplicativo
    public static void telas(String tela){
        switch(tela){
            // Caso: Página de seleção de projeto
            case "selectProject" -> {
                // Oculta elementos desnecessários e carrega projetos
                controllerSelectProject.esconderElementos();
                controllerSelectProject.loadProjetos();
                // Define a cena para a página de seleção de projeto
                telaSelecionada.setScene(sceneSelectProject);
                // Reinicia o índice do projeto aberto
                projetoAberto = -1;
            }
            // Caso: Página de criação de projeto
            case "createProject" -> {
                // Oculta elementos desnecessários e carrega projetos
                controllerCreateProject.esconderElementos();
                controllerCreateProject.loadProjetos();
                // Define a cena para a página de criação de projeto
                telaSelecionada.setScene(sceneCreateProject);
            }
            case "kanbanPage" -> {
                // Oculta elementos desnecessários e carrega atividades
                controllerKanbanPage.esconderElementos();
                controllerKanbanPage.loadAtividades();
                // Define a cena para a página do Kanban
                telaSelecionada.setScene(sceneKanbanPage);
                controllerReload.esconderElementos();
                controllerReload.loadAtividades();
                telaSelecionada.setScene(sceneReload);
            }
            // Caso: Página de renomeação de projeto
            case "newName" -> {
                // Carrega a lista de projetos na página de renomeação
                controllerNewNameProject.loadProjetos();
                // Define a cena para a página de renomeação de projeto
                telaSelecionada.setScene(sceneNewNameProject);
            }
            // Caso: Página de criação de nova ação
            case "newAction" -> {
                // Carrega listas necessárias na página de nova ação
                controllerNewAction.loadAreasLista();
                controllerNewAction.loadUsuariosLista();
                controllerNewAction.loadAtividadesLista();
                // Esconde elementos específicos da página de nova ação
                controllerNewAction.esconderElementos();
                // Carrega as atividades na página de nova ação
                controllerNewAction.loadAtividades();
                // Esconde o popup na página de nova ação
                controllerNewAction.hidePopup();
                // Define a cena para a página de nova ação
                telaSelecionada.setScene(sceneNewAction);
                // Esconde elementos específicos da página de recarregamento
                controllerReload.esconderElementos();
                // Carrega as atividades na página de recarregamento
                controllerReload.loadAtividades();
                // Define a cena para a página de recarregamento
                telaSelecionada.setScene(sceneReload);
            }
            // Caso: Página de login
            case "loginPage" -> telaSelecionada.setScene(sceneLoginPage);
            // Caso: Página de criação de nova atividade
            case "newActivit" -> {
                // Esconde elementos específicos da página de nova atividade
                controllerNewActivity.esconderElementos();
                // Carrega as atividades na página de nova atividade
                controllerNewActivity.loadAtividades();
                // Define a cena para a página de nova atividade
                telaSelecionada.setScene(sceneNewActivit);
            }
            // Caso: Página de criação de novo usuário
            case "newUser" -> {
                // Esconde elementos específicos da página de novo usuário
                controllerNewUser.esconderElementos();
                // Carrega os projetos na página de novo usuário
                controllerNewUser.loadProjetos();
                // Define a cena para a página de novo usuário
                telaSelecionada.setScene(sceneNewUser);
            }
            // Caso: Página de criação de nova área
            case "newArea" -> {
                // Esconde elementos específicos da página de nova área
                controllerNewArea.esconderElementos();
                // Carrega os projetos na página de nova área
                controllerNewArea.loadProjetos();
                // Define a cena para a página de nova área
                telaSelecionada.setScene(sceneNewArea);
            }
        }
        // Verifica se a tela desejada é a página do Kanban
        if (tela.equals("kanbanPage")) {
            controllerKanbanPage.esconderElementos();
            controllerKanbanPage.loadAtividades();
            telaSelecionada.setScene(sceneKanbanPage);
        }
        if (tela.equals("newAction")) {
            controllerNewAction.loadAreasLista();
            controllerNewAction.loadUsuariosLista();
            controllerNewAction.loadAtividadesLista();
            controllerNewAction.esconderElementos();
            controllerNewAction.loadAtividades();
            controllerNewAction.showPopup();
            telaSelecionada.setScene(sceneNewAction);
        }
    }
    

    public static void main(String[] args) {
        launch(args);
    }
}
