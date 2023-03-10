package driver;

import files.Folder;

import java.util.*;

public class Main {
    private static HashMap<String, Folder> generateFolders(String[] folderNames) {
        HashMap<String, Folder> folderMappings = new HashMap<>();

        for (String name : folderNames) {
            folderMappings.put(name, new Folder(name));
        }

        return folderMappings;
    }

    private static void createFiles(Map<String, Folder> folderMappings, Map<String, String[]> fileNames) {
        for (String folderName : fileNames.keySet()) {
            Folder folder = folderMappings.get(folderName);
            for (String fileName : fileNames.get(folderName)) {
                folder.createFile(fileName);
            }
        }
    }

    private static void addNestedFolders(Map<String, Folder> folderMappings, Map<String, String[]> subFolderNames) {
        for (String folderName : subFolderNames.keySet()) {
            Folder folder = folderMappings.get(folderName);
            for (String subFolderName : subFolderNames.get(folderName)) {
                Folder subFolder = folderMappings.get(subFolderName);
                folder.addSubFolder(subFolder);
            }
        }
    }

    public static void main(String[] args) {
        String rootFolderName = "php_demo1";
        String[] folderNames = {
            rootFolderName,
            "Source Files",
            "Include Path",
            "Remote Files",
            ".phalcon",
            "app",
            "cache",
            "public",
            "config",
            "controllers",
            "library",
            "migrations",
            "models",
            "views"
        };
        Map<String, Folder> folderMappings = generateFolders(folderNames);
        Map<String, String[]> fileNames = Map.ofEntries(
            Map.entry("Source Files", new String[]{".htaccess", ".htrouter.php", "index.html"})
        );
        Map<String, String[]> subFolderNames = Map.ofEntries(
            Map.entry("php_demo1", new String[]{"Source Files", "Include Path", "Remote Files"}),
            Map.entry("Source Files", new String[]{".phalcon", "app", "cache", "public"}),
            Map.entry("app", new String[]{"config", "controllers", "library", "migrations", "models", "views"})
        );

        createFiles(folderMappings, fileNames);
        addNestedFolders(folderMappings, subFolderNames);

        Folder rootFolder = folderMappings.get(rootFolderName);
        System.out.println("Initial structure:");
        rootFolder.print();
        Folder sourceFilesFolder = folderMappings.get("Source Files");
        sourceFilesFolder.deleteSubFolder("app");
        System.out.println("Deleted 'app' folder:");
        rootFolder.print();
        sourceFilesFolder.deleteSubFolder("public");
        System.out.println("Deleted 'public' folder:");
        rootFolder.print();
    }
}