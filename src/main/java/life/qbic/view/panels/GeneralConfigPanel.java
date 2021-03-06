package life.qbic.view.panels;

import com.vaadin.ui.*;
import life.qbic.model.Globals;
import life.qbic.model.beans.AlignmentFileBean;
import life.qbic.model.beans.ProjectBean;
import life.qbic.presenter.Presenter;
import life.qbic.testing.TestData;
import life.qbic.view.InfoBar;
import life.qbic.view.MyGrid;

/**
 * This component has a panel where the user chooses a nameField for his project, selects the type of study,
 * and uploads an alignment file (if he selected "Strain or species")
 *
 * @author jmueller
 */
public class GeneralConfigPanel extends CustomComponent{
    private Presenter presenter;
    private Panel generalConfigPanel;
    private Layout contentLayout;
    private Grid<ProjectBean> projectGrid;
    private Grid<AlignmentFileBean> alignmentFileGrid;
    RadioButtonGroup<String> projectTypeButtonGroup;

    public GeneralConfigPanel(Presenter presenter) {
        generalConfigPanel = designPanel();
        setCompositionRoot(generalConfigPanel);
        this.presenter = presenter;

    }

    private Panel designPanel() {
        Panel panel = new Panel();
        contentLayout = new FormLayout();
        projectGrid = new MyGrid<>("Select your project");
        projectGrid.addColumn(ProjectBean::getName).setCaption("Project Name");
        projectGrid.addColumn(ProjectBean::getRegistrationDate).setCaption("Registration Date");

        projectGrid.addStyleName("my-file-grid");

        projectTypeButtonGroup = new RadioButtonGroup<>("Select type of study");
        String strainOrSpecies = Globals.COMPARE_GENOMES;
        String conditions = Globals.COMPARE_CONDITIONS;
        projectTypeButtonGroup.setItems(strainOrSpecies, conditions);


        projectTypeButtonGroup.setSelectedItem(strainOrSpecies);

        alignmentFileGrid = new MyGrid<>("Select alignment file");
        alignmentFileGrid.addColumn(AlignmentFileBean::getName).setCaption("File name");
        alignmentFileGrid.addColumn(AlignmentFileBean::getCreationDate).setCaption("Creation Date");
        alignmentFileGrid.addColumn(AlignmentFileBean::getSizeInKB).setCaption("Size (kB)");
        alignmentFileGrid.addStyleName("my-file-grid");
        //The alignment file selection should only be visible if strains/species are to be compared
        alignmentFileGrid.setVisible(!Globals.IS_CONDITIONS_INIT);
        contentLayout.addComponents(projectTypeButtonGroup, projectGrid, alignmentFileGrid);
        panel.setContent(new VerticalLayout(new InfoBar(Globals.GENERAL_CONFIG_INFO), contentLayout));



        //<-- TESTING
        projectGrid.setItems(TestData.createProjectBeanList());
        alignmentFileGrid.setItems(TestData.createAlignmentFileBeanList());
        //TESTING -->


        return panel;
    }


    public Grid<ProjectBean> getProjectGrid() {
        return projectGrid;
    }

    public Grid<AlignmentFileBean> getAlignmentFileGrid() {
        return alignmentFileGrid;
    }

    public RadioButtonGroup<String> getProjectTypeButtonGroup() {
        return projectTypeButtonGroup;
    }
}
