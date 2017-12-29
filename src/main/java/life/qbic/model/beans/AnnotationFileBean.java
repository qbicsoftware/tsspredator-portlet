package life.qbic.model.beans;

/**
 * @author jmueller
 * Represents a genome annotation file (*.gff)
 */
public class AnnotationFileBean {
    private String name, creationDate;
    private int sizeInKB;

    @Override
    public String toString() {
        return name + " (" + creationDate + ", " + sizeInKB + "kB)";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public int getSizeInKB() {
        return sizeInKB;
    }

    public void setSizeInKB(int sizeInKB) {
        this.sizeInKB = sizeInKB;
    }
}
