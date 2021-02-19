package com.sourdoughsoftware.interaction;

import com.sourdoughsoftware.GameState;
import com.sourdoughsoftware.dictionary.Dictionary;
import com.sourdoughsoftware.dictionary.Noun;
import com.sourdoughsoftware.dictionary.Verb;
import com.sourdoughsoftware.gamepieces.Player;
import com.sourdoughsoftware.world.World;

import java.util.*;

public class TextParser {

    public static void parse(String userInput) {//grab chocolate crust
        //chocolate key -> chocolate, chocolate crust
        //crust key -> chocolate crust
        List<String> userInputWords = new ArrayList<>(Arrays.asList(userInput.split(" ")));
        Set<Noun> nounCandidates = getNounCandidates(userInputWords);

        Noun noun = getNoun(nounCandidates, userInputWords);
        Verb verb = getVerb(userInputWords);

        Set<Noun> targetNounCandidates = getNounCandidates(userInputWords);
        Noun targetNoun = getNoun(targetNounCandidates, userInputWords);


        if((verb != null && verb.getName().equals("feed") && !userInputWords.contains("to"))||(userInputWords.contains("with") && targetNoun != null)) {
            Noun temp = targetNoun;
            targetNoun = noun;
            noun = temp;
        }
        Command.setNoun(noun);
        Command.setVerb(verb);
        Command.setTargetNoun(targetNoun);
    }

    private static Noun getNoun(Set<Noun> nounSet, List<String> userInputWords) {
        if (nounSet == null || nounSet.size() == 0) {
            return null;
        }

        Noun noun = null;
        if (nounSet.size() == 1) {
            noun = nounSet.iterator().next();
        } else {
            Set<Noun> availableNouns = new HashSet<>(GameState.getPlayer().getInventory().getCurrentInventory());
            availableNouns.addAll(World.getCurrentRoom().getItemList());

            nounSet.retainAll(availableNouns);
            if(nounSet.size() > 1) {
                boolean valid = false;
                while(!valid) {
                    StringBuilder sb = new StringBuilder();
                    Noun[] nouns = nounSet.toArray(new Noun[0]);
                    sb.append("Which similar ingredient?");
                    for(int i = 0; i < nouns.length; i++) {
                        sb.append("\n");
                        sb.append(i+1);
                        sb.append(") ");
                        sb.append(nouns[i].getName());
                    }
                    String response = Prompter.prompt(sb.toString());
                    int resp = 0;
                    try {
                        resp = Integer.parseInt(response.trim());
                        valid = true;
                    } catch (NumberFormatException ignored) {
                    }
                    if(resp <= nouns.length && resp != 0) {
                        noun = nouns[resp-1];
                    }else {
                        noun = null;
                    }
                }

            } else {
                if(!(nounSet.size() == 0)) {
                    noun = nounSet.iterator().next();
                }
            }
        }
        if (noun != null) {
            String[] nounNameWords = noun.getName().split(" ");
            for (String nounNameWord : nounNameWords) {
                userInputWords.remove(nounNameWord);
            }
        }
        return noun;
    }

    private static Verb getVerb(List<String> userInputWords) {

        Dictionary dictionary = Dictionary.INSTANCE;

        for (String userInputWord : userInputWords) {
            Verb currentVerb = dictionary.getVerb(userInputWord);
            if (currentVerb != null) {
                return currentVerb;
            }
        }
        return null;
    }

    private static Set<Noun> getNounCandidates(List<String> userInputWords) {

        Dictionary dictionary = Dictionary.INSTANCE;
        Set<Noun> finalNounCandidates = null;

        for (String userInputWord : userInputWords) {
            Set<Noun> currentTargetNounCandidates = dictionary.getNounCandidates(userInputWord);

            if (currentTargetNounCandidates == null) {
                continue;
            }

            if (finalNounCandidates == null) {
                finalNounCandidates = currentTargetNounCandidates;
            }
            //checks if current word has nothing to do with current candidates
            if (Collections.disjoint(finalNounCandidates, currentTargetNounCandidates)) {
                continue;
            }

            finalNounCandidates.retainAll(currentTargetNounCandidates);
        }

        return finalNounCandidates;
    }



}
