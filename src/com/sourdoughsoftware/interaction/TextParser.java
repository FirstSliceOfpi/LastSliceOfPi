package com.sourdoughsoftware.interaction;

import com.sourdoughsoftware.dictionary.Dictionary;
import com.sourdoughsoftware.dictionary.Noun;
import com.sourdoughsoftware.dictionary.Verb;

import java.util.*;

public class TextParser {

    public static Command parse(String userInput) {

        List<String> userInputWords = new ArrayList<>(Arrays.asList(userInput.split(" ")));

        Set<Noun> nounCandidates = getNounCandidates(userInputWords);
        Noun noun = getNoun(nounCandidates, userInputWords);

        Verb verb = getVerb(userInputWords);

        Set<Noun> targetNounCandidates = getNounCandidates(userInputWords);
        Noun targetNoun = getNoun(targetNounCandidates, userInputWords);

        return targetNoun == null ? Command.getInstance().setInstance(noun, verb)
                : Command.getInstance().setInstance(noun, verb, targetNoun);

    }

    private static Noun getNoun(Set<Noun> nounSet, List<String> userInputWords) {
        if(nounSet == null) {
            return null;
        }
        Noun noun = null;

        Iterator<Noun> setIterator = nounSet.iterator();

//        Set<Noun> availableNouns = new HashSet<>(inventory.getCurrentInventory());

//        nounSet.retainAll(availableNouns);

        if(nounSet.size() == 1) {
            noun = setIterator.next();
        } else if(nounSet.size() > 1) {
            setIterator.next();
            noun = setIterator.next();
        }

        if(noun != null) {
            String[]nounNameWords = noun.getName().split(" ");
            for(String nounNameWord : nounNameWords) {
                userInputWords.remove(nounNameWord);
            }
        }

        return noun;

    }

    private static Verb getVerb(List<String> userInputWords) {

        Dictionary dictionary = Dictionary.INSTANCE;

        for(String userInputWord : userInputWords) {
            Verb currentVerb = dictionary.getVerb(userInputWord);
            if(currentVerb != null) {
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
