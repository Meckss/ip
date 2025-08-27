package Storage;

import Tasks.*;

import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.FileWriter;

public class TaskStorage {
    File file;


    public TaskStorage(File file) {
        this.file = file;
    }

    public void append(Task t) {
        try(FileWriter fileWriter = new FileWriter(file, true);){
            fileWriter.append(t.format()).append("\n");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    public void update(ToDoList list) {
        try (FileWriter fileWriter = new FileWriter(file);){
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < list.size(); i++) {
                if(i == list.size() - 1) {
                    sb.append(list.get(i).format());
                    break;
                }
                sb.append(list.get(i).format()).append("\n");
            }
            fileWriter.write(sb.toString());
        } catch (IOException e) {
            return;
        }

    }

    public ArrayList<Task> toList() {
        ArrayList<Task> ans = new ArrayList<Task>();
        try(Scanner s = new Scanner(file);) {
            int length = 0;
            while(s.hasNext()) {
                String tempRawData = s.nextLine();
                String[] tempData = tempRawData.split("\\|");
                String dataType = tempData[0];
                switch (dataType) {
                case("T"):
                    Task t = new ToDoTask(tempData[2]);
                    if(tempData[1].equals("1")) {
                        t.markAsDone();
                    }
                    ans.add(t);
                    break;
                case("D"):
                    Task t1 = new DeadlineTask(tempData[2], tempData[3]);
                    if(tempData[1].equals("1")) {
                        t1.markAsDone();
                    }
                    ans.add(t1);
                    break;
                case("E"):
                    Task t2 = new EventTask(tempData[2], tempData[3], tempData[4]);
                    if(tempData[1].equals("1")) {
                        t2.markAsDone();
                    }
                    ans.add(t2);
                    break;
                }
            }
            return ans;
        } catch(FileNotFoundException e) {
            return ans;
        }

    }
}
