package proiect;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

@SuppressWarnings("unchecked")
public class User implements Serializable {
    private String email;
    private String password;
    private ArrayList<Integer> preferences;
    private String location;

    public User(String email, String password, ArrayList<Integer> preferences, String location) {
        this.email = email;
        this.password = password;
        this.preferences = preferences;
        this.location = location;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public User(String password) {
        this.password = password;
    }

    public void addPreferences(ArrayList<Integer> preferences) {
        this.preferences.clear();
        this.preferences.addAll(preferences);
    }

    public void addPreference(int preference) {
        if(this.preferences.size() == 0) {
            this.preferences.add(preference);
        }
        else if(!this.preferences.contains(preference)) {
            this.preferences.add(preference);
        }
    }
   
    static ArrayList<User> readUsers() {
        try {
            StringBuffer path = new StringBuffer(System.getProperty("user.dir"));
            path.append("\\src\\data\\users.txt");

            ObjectInputStream in = new ObjectInputStream(new FileInputStream(path.toString()));
            ArrayList<User> users = (ArrayList<User>) in.readObject();
            //System.out.println(users);
            return users;
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch(ClassNotFoundException e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public static void saveUser(User user) {
        try {
            StringBuffer path = new StringBuffer(System.getProperty("user.dir"));
            path.append("\\src\\data\\users.txt");

            ArrayList<User> users = readUsers();
            int index = 0;
            do {
                if(user.email.equals(users.get(index).email)) break;
                index++;
            } while(index < users.size());
            users.set(index, user);

            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path.toString()));
            out.writeObject(users);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadUser() {
        ArrayList<User> users = readUsers();
        for(int i = 0; i < users.size(); i++) {
            if(users.get(i).email.equals(this.email)) {
                this.preferences = users.get(i).preferences;
                this.location = users.get(i).location;
                break;
            }
        }
    }

    public static void saveUsers(ArrayList<User> users) {
        try {
            StringBuffer path = new StringBuffer(System.getProperty("user.dir"));
            path.append("\\src\\data\\users.txt");

            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path.toString()));
            out.writeObject(users);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean login() {
        if(userExists(this) && isPasswordCorrect(this)) {
            loadUser();
            return true;
        }
        return false;
    }

    public boolean loginAsAdmin() {
        boolean r = false;
        try {
            StringBuffer path = new StringBuffer(System.getProperty("user.dir"));
            path.append("\\src\\data\\admin.txt");

            BufferedReader file = new BufferedReader(new FileReader(path.toString()));
            if(password.equals(file.readLine())) {
                r = true;
            }
            else {
                r = false;
            }

            file.close();
            return r;
        } catch(IOException ex) {
            ex.printStackTrace();
            return r;
        }
    }

    public boolean changeAdminPassword(String pass) {
        try {
            StringBuffer path = new StringBuffer(System.getProperty("user.dir"));
            path.append("\\src\\data\\admin.txt");

            BufferedWriter file = new BufferedWriter(new FileWriter(path.toString()));
            file.write(pass);
            file.close();
            return true;
        } catch(IOException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public static boolean userExists(User user) {
        ArrayList<User> existentUsers = User.readUsers();
        for(int i = 0; i < existentUsers.size(); i++) {
            if(existentUsers.get(i).getEmail().equalsIgnoreCase(user.email)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isPasswordCorrect(User user) {
        ArrayList<User> existentUsers = User.readUsers();
        for(int i = 0; i < existentUsers.size(); i++) {
            if(existentUsers.get(i).getPassword().equals(user.password)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmailValid() {
        return (this.email.contains("@") && this.email.contains(".") && this.email.length() > 5);
    }

    public boolean isPasswordValid() {
        return (this.password.length() >= 3);
    }

    public static void registerUser(User user) {
        ArrayList<User> users = User.readUsers();
        users.add(user);
        User.saveUsers(users);
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Integer> getPreferences() {
        return this.preferences;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String toString(){
       StringBuffer sb = new StringBuffer();
       sb.append("Email: ");
       sb.append(getEmail());
       sb.append("\nPassword: ");
       sb.append(getPassword());
       sb.append("\nPreferences: ");
       sb.append(getPreferences());
       sb.append("\nLocation: ");
       sb.append(getLocation());
       return sb.toString(); 
    }
}