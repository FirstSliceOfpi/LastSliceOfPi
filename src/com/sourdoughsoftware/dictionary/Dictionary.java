package com.sourdoughsoftware.dictionary;

import com.sourdoughsoftware.GameState;
import com.sourdoughsoftware.Savable;
import com.sourdoughsoftware.gamepieces.Enemy;
import com.sourdoughsoftware.gamepieces.Item;
import com.sourdoughsoftware.gamepieces.Pie;
import com.sourdoughsoftware.world.Room;

import java.io.Serializable;
import java.util.*;
import java.util.function.Function;

public enum Dictionary implements Serializable, Savable {
    INSTANCE;

    private Map<String, Set<Noun>> nouns = new HashMap<>();
    private Map<String, Verb> verbs = new HashMap<>();
    private ArrayList<Noun> allNouns = new ArrayList<>();

    public void add(DictionaryEntry entry) {

        String[] entryNameWords = entry.getName().split(" ");

        for (String entryNameWord : entryNameWords) {
            if (entry instanceof Verb) {
                verbs.put(entryNameWord, (Verb) entry);
            } else {
                Set<Noun> nounResults = nouns.get(entryNameWord);
                allNouns.add((Noun) entry);
                if (nounResults == null) {
                    nouns.put(entryNameWord, new HashSet<>(){{add((Noun) entry);}});
                } else {
                    nounResults.add((Noun) entry);
                }

            }
        }
    }

    public ArrayList<Noun> getAllNouns() {
        return allNouns;
    }
    public Verb getVerb(String name) {
        return verbs.get(name);
    }

    public Set<Noun> getNounCandidates(String name) {
        return nouns.get(name);
    }

    public Map<String, Set<Noun>> getNouns() {
        return nouns;
    }
    public Noun getNoun(String str) {
        String[] words = str.split(" ");
        Set<Noun> nounCandidates = new HashSet<>();
        Arrays.stream(words).forEach(word -> {
            nounCandidates.addAll(nouns.get(word));
        });
        for(Noun noun : nounCandidates) {
            if(noun.getName().equals(str)) {
                return noun;
            }
        }
        return null;
    }

    public Map<String, Verb> getVerbs() { return verbs; }

    public boolean setSaveFields(HashMap<String, Object> result) {
        try {
            nouns = (Map<String, Set<Noun>>) result.get("nouns");
            verbs = (Map<String, Verb>) result.get("verbs");
        }catch (Exception e) {
            System.out.println(e);
            return false;
        }
        return true;
    }

    public HashMap<String, Object> getSaveFields() {
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("nouns", nouns);
        result.put("verbs", verbs);
        return result;
    }

    public void deleteNoun(Noun noun) {
        String[] words = noun.getName().split(" ");

        Arrays.stream(words).forEach(word -> {
            Set<Noun> i = nouns.get(word);
            if(i.size() == 1) {
                nouns.remove(word);
            } else {
                nouns.get(word).remove(noun);
            }
        });
    }

    public void killNounRespawn(Noun noun) {
        allNouns.remove(noun);
    }

    public void saveClass() {
        GameState.addSavable(this);
    }
}
