package org.constantgatherer.model.webdriver;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * User: ggomes
 * Date: 12-01-2014
 * Time: 18:13
 * Copyright Tango Telecom 2014
 */
@Entity
public class Command {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    CommandType commandType;
    String value;
    String target;

    public Command(){super();}
    public Command(CommandType commandType, String target, String value){
        this.commandType = commandType;
        this.target = target;
        this.value = value;
    }
    public Command(CommandType commandType, String target){
        this.commandType = commandType;
        this.target = target;
    }
    public Command(String string){
        super();
        String[] stringArray = string.split("5pl17");
        try{
            this.setCommandType(CommandType.valueOf(stringArray[0]));
            this.setTarget(stringArray[1].replaceAll("c0Mma", ","));
            this.setValue(stringArray[2].replaceAll("c0Mma", ","));
        }catch(ArrayIndexOutOfBoundsException ex){}
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CommandType getCommandType() {
        return commandType;
    }

    public void setCommandType(CommandType commandType) {
        this.commandType = commandType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String toString(){
        String thisString = "";
        thisString += this.getCommandType().toString();
        if(this.getTarget() != null)
            thisString += ("5pl17"+this.getTarget().replaceAll(",","c0Mma"));
        if(this.getValue() != null)
            thisString += ("5pl17"+this.getValue().replaceAll(",","c0Mma"));
        return thisString;
    }
}
