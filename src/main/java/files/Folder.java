package files;

import java.util.ArrayList;

public class Folder {
    private String name;
    private final ArrayList<File> files;
    private final ArrayList<Folder> subFolders;

    public Folder(String name) {
        this.name = name;
        this.files = new ArrayList<>();
        this.subFolders = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean checkHasContents() {
        return files.size() > 0 || subFolders.size() > 0;
    }

    public File findFile(String fileName) {
        int fileIndex = findFileIndex(fileName);

        if (fileIndex < 0) {
            return null;
        }

        return files.get(fileIndex);
    }

    private int findFileIndex(String fileName) {
        int numberOfFiles = files.size();
        for (int i = 0; i < numberOfFiles; i++) {
            if (files.get(i).getName().equals(fileName)) {
                return i;
            }
        }

        return -1;
    }

    public Folder findSubFolder(String folderName) {
        int subFolderIndex = findSubFolderIndex(folderName);

        if (subFolderIndex < 0) {
            return null;
        }

        return subFolders.get(subFolderIndex);
    }

    private int findSubFolderIndex(String folderName) {
        int numberOfSubFolders = subFolders.size();
        for (int i = 0; i < numberOfSubFolders; i++) {
            if (subFolders.get(i).getName().equals(folderName)) {
                return i;
            }
        }

        return -1;
    }

    public void createFile(String fileName) {
        this.files.add(new File(fileName));
    }

    public void deleteFile(String fileName) {
        int fileIndex = findFileIndex(fileName);

        if (fileIndex < 0) {
            return;
        }

        files.remove(fileIndex);
    }

    public void createSubFolder(String subFolderName) {
        addSubFolder(new Folder(subFolderName));
    }

    public void addSubFolder(Folder folder) {
        subFolders.add(folder);
    }

    public void deleteSubFolder(String folderName) {
        int subFolderIndex = findSubFolderIndex(folderName);

        if (subFolderIndex < 0) {
            return;
        }

        subFolders.remove(subFolderIndex);
    }

    private String convertContentsToString() {
        int indentationLevel = 4;
        String contentsString = convertSubFoldersToString() + convertFilesToString();
        return contentsString.indent(indentationLevel);
    }

    private String convertSubFoldersToString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Folder subFolderInstance : subFolders) {
            stringBuilder.append(
                subFolderInstance.toString()
            );
        }

        return stringBuilder.toString();
    }


    private String convertFilesToString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (File fileInstance : files) {
            stringBuilder.append(String.format("%s%n", fileInstance.toString()));
        }

        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("%s (directory)%n", name));
        if (checkHasContents()) {
            stringBuilder.append(String.format("%s%n", "=".repeat(20)));
            stringBuilder.append(convertContentsToString());
        }


        return stringBuilder.toString();
    }

    public void print() {
        System.out.print(this);
    }
}
