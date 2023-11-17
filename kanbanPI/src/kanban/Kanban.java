package kanban;

import controllers.KanbanPageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import entities.Empresa;
import controllers.SelectProjectController;

public class Kanban extends Application {
    private static Stage telaSelecionada;
    private static Scene sceneSelectProject;
    private static SelectProjectController controllerSelectProject;
    private static Scene sceneCreateProject;
    private static Scene sceneKanbanPage;
    private static KanbanPageController controllerKanbanPage;
    private static Scene sceneNewNameProject;
    private static Scene sceneNewAction;
    private static Scene sceneLoginPage;
    private static Scene sceneNewActivit;
    private static Scene sceneNewUser;
    public static Empresa[] empresas;
    //public static Usuario[] usuarios;
    public static boolean loginAdmin;

    @Override
    public void start(Stage stage) throws Exception {
        // adicionar cadastrar área (selectProject) - botão gerar relatorio
        telaSelecionada = stage;
        empresas = new Empresa[16];
        loginAdmin = false;
        
        Parent fxmlLoginPage = FXMLLoader.load(getClass().getResource("../views/loginPage.fxml"));
        sceneLoginPage = new Scene(fxmlLoginPage,  1535, 800);
        
        FXMLLoader fxmlSelectProject = new FXMLLoader(getClass().getResource("../views/selectProject.fxml"));
        sceneSelectProject = new Scene(fxmlSelectProject.load(),  1535, 800);
        controllerSelectProject = fxmlSelectProject.getController();
        
        Parent fxmlcreateProject = FXMLLoader.load(getClass().getResource("../views/createProject.fxml"));
        sceneCreateProject = new Scene(fxmlcreateProject,  1535, 800);
        
        FXMLLoader fxmlKanbanPage = new FXMLLoader(getClass().getResource("../views/kanbanPage.fxml"));
        sceneKanbanPage = new Scene(fxmlKanbanPage.load(),  1535, 800);
        controllerKanbanPage = fxmlKanbanPage.getController();
        
        Parent fxmlNewNameProject = FXMLLoader.load(getClass().getResource("../views/newNameProject.fxml"));
        sceneNewNameProject = new Scene(fxmlNewNameProject,  1535, 800);
        
        Parent fxmlNewAction = FXMLLoader.load(getClass().getResource("../views/newAction.fxml"));
        sceneNewAction = new Scene(fxmlNewAction,  1535, 800);
        
        Parent fxmlNewActivit = FXMLLoader.load(getClass().getResource("../views/newActivit.fxml"));
        sceneNewActivit = new Scene(fxmlNewActivit,  1535, 800);
        
        Parent fxmlNewUser = FXMLLoader.load(getClass().getResource("../views/newUser.fxml"));
        sceneNewUser = new Scene(fxmlNewUser,  1535, 800);
        
        stage.setScene(sceneLoginPage);
        stage.show();  
    }
    
    public static void telas(String tela){
        switch(tela){
            case "selectProject" -> {
                controllerSelectProject.esconderElementos();
                telaSelecionada.setScene(sceneSelectProject);
            }
            case "createProject" -> telaSelecionada.setScene(sceneCreateProject);
            case "kanbanPage" -> {
                controllerKanbanPage.esconderElementos();
                telaSelecionada.setScene(sceneKanbanPage);
            }
            case "newName" -> telaSelecionada.setScene(sceneNewNameProject);
            case "newAction" -> telaSelecionada.setScene(sceneNewAction);
            case "loginPage" -> telaSelecionada.setScene(sceneLoginPage);
            case "newActivit" -> telaSelecionada.setScene(sceneNewActivit);
            case "newUser" -> telaSelecionada.setScene(sceneNewUser);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
