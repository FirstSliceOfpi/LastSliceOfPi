package com.sourdoughsoftware.dictionary;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public enum Dictionary {
    INSTANCE;



    private final Map<String, Set<Noun>> nouns = new HashMap<>();
    private final Map<String, Verb> verbs = new HashMap<>();

    public void add(DictionaryEntry entry) {

        String[] entryNameWords = entry.getName().split(" ");

        for (String entryNameWord : entryNameWords) {
            if (entry instanceof Verb) {
                verbs.put(entryNameWord, (Verb) entry);
            } else {
                Set<Noun> nounResults = nouns.get(entryNameWord);

                if (nounResults == null) {
                    nouns.put(entryNameWord, new HashSet<>(){{add((Noun) entry);}});
                } else {
                    nounResults.add((Noun) entry);
                }
            }
        }
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


}
