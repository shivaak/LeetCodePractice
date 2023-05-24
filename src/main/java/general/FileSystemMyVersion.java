package general;

import java.util.*;

/*
Given a list of [FileName, FileSize, [Collection]] - Collection is optional, i.e., a collection can have 1 or more files. Same file can be a part of more than 1 collection.
How would you design a system

To calculate total size of files processed.
To calculate Top K collections based on size.
Example:
file1.txt(size: 100)
file2.txt(size: 200) in collection "collection1"
file3.txt(size: 200) in collection "collection1"
file4.txt(size: 300) in collection "collection2"
file5.txt(size: 100)
Output:

Total size of files processed: 900
Top 2 collections:
- collection1 : 400
- collection2 : 300
 */
public class FileSystemMyVersion {

    class File{
        private String fileName;
        private int fileSize;

        public File(String fileName, int fileSize) {
            this.fileName = fileName;
            this.fileSize = fileSize;
        }

        public String getFileName() {
            return fileName;
        }

        public void setFileName(String fileName) {
            this.fileName = fileName;
        }

        public int getFileSize() {
            return fileSize;
        }

        public void setFileSize(int fileSize) {
            this.fileSize = fileSize;
        }
    }

    class Collection {

        private String name;
        private List<File> files;
        private int collectionSize;

        public Collection(String name) {
            this.files = new ArrayList<>();
            this.collectionSize = 0;
            this.name = name;
        }

        public void addFile(File file){
            this.files.add(file);
            this.collectionSize += file.getFileSize();
        }

        public List<File> getFiles() {
            return files;
        }

        public int getCollectionSize() {
            return collectionSize;
        }

        public String getName() {
            return name;
        }
    }


    private List<File> files;
    private Map<String, Collection> collectionMap;

    PriorityQueue<Collection> maxHeap;

    private int totalFileSize;

    public FileSystemMyVersion() {
        this.files = new ArrayList<>();
        collectionMap = new HashMap<>();
        maxHeap = new PriorityQueue<>((a,b)-> b.getCollectionSize() - a.getCollectionSize());
    }

    private void addFile(String fileName, int size, String collectionName){

        if(collectionName.isEmpty()){
            files.add(new File(fileName, size));
        }else {
            if(!collectionMap.containsKey(collectionName)){
                collectionMap.put(collectionName, new Collection(collectionName));
                maxHeap.add(collectionMap.get(collectionName));
            }
            Collection c = collectionMap.get(collectionName);
            c.addFile(new File(fileName, size));
            maxHeap.remove(c);
            maxHeap.add(c);
        }
        totalFileSize+=size;
    }

    private List<Collection> getTopNCollectionBySize(int N) {

        int counter=N;
        List<Collection> tempCollection = new ArrayList<>();
        while(!maxHeap.isEmpty()){
            tempCollection.add(maxHeap.poll());
            counter--;
            if(counter==0) break;
        }

        maxHeap.addAll(tempCollection);
        return tempCollection;
    }

    public List<File> getFiles() {
        return files;
    }

    public Map<String, Collection> getCollectionMap() {
        return collectionMap;
    }

    public int getTotalFileSize() {
        return totalFileSize;
    }

    public static void main(String[] args) {
        FileSystemMyVersion fs = new FileSystemMyVersion();

        /*fs.addFile("file1.txt", 100, "collection2");
        fs.addFile("file2.txt", 200, "collection1");
        fs.addFile("file3.txt", 300, "collection3");
        fs.addFile("file4.txt", 300, "collection2");
        fs.addFile("file5.txt", 100, "collection2");*/

        fs.addFile("file5.txt", 400, "collection4");
        fs.addFile("file2.txt", 100, "collection1");
        fs.addFile("file3.txt", 400, "collection1");

        //Total size
        System.out.println(fs.getTotalFileSize());

        for(Collection c : fs.getTopNCollectionBySize(2)){
            System.out.println(c.getName() + " " + c.getCollectionSize());
        }


    }


}
