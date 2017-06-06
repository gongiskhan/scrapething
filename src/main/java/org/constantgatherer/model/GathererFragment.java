package org.constantgatherer.model;

import org.constantgatherer.model.webdriver.Command;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * User: ggomes
 * Date: 12-01-2014
 * Time: 13:42
 */
@Entity
public class GathererFragment {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String name;
    private String visibility; //static, notification
    @OneToMany(cascade = CascadeType.ALL)
    private List<Command> commands = new ArrayList<Command>();

    public GathererFragment(){ super(); }
    public GathererFragment(String asString){
        String[] arr = asString.split("sPl1T");
        try{
            this.setName(arr[0]);
            this.setVisibility(arr[1]);
            String commandsString = arr[2];
            String[] commandsStringArray = commandsString.split("SpL1t");
            for(int i = 0; i < commandsStringArray.length; i++){
                String commandString = commandsStringArray[i];
                commands.add(new Command(commandString));
            }
        }catch(ArrayIndexOutOfBoundsException ex){}
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Command> getCommands() {
        return commands;
    }

    public void setCommands(List<Command> commands) {
        this.commands = commands;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public String toString(){
        String commands = "";
        int count = 0;
        for(Command command : this.getCommands()){
            count++;
            commands += (command.toString() + (count < this.getCommands().size() ? "SpL1t" : ""));
        }
        return this.getName()+"sPl1T"+this.getVisibility()+"sPl1T"+ commands;
    }
}
