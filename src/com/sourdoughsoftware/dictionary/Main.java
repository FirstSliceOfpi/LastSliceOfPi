package com.sourdoughsoftware.dictionary;

import com.sourdoughsoftware.interaction.Command;
import com.sourdoughsoftware.interaction.TextParser;
import com.sourdoughsoftware.utility.XmlParser;

public class Main {

    public static void main(String[] args) {
        new Verb("use", 1);
        XmlParser.parseItems();
        Command command = TextParser.parse("use golden feather on nest");

        String nounName = command.getNoun() == null ? null : command.getNoun().getName();
        String targetNounName = command.getTargetNoun() == null ? null : command.getTargetNoun().getName();
        String verbName = command.getVerb() == null ? null : command.getVerb().getName();

        System.out.println(nounName + " " + targetNounName + " " + verbName);
    }


}
