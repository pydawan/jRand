package me.xdrop.jrand.builders.text;

import me.xdrop.jrand.CharUtils;
import me.xdrop.jrand.Constants;
import me.xdrop.jrand.Generator;
import me.xdrop.jrand.Tuple;
import me.xdrop.jrand.builders.basics.CharacterGenerator;
import me.xdrop.jrand.builders.basics.NaturalGenerator;

public class SyllableGenerator extends Generator<String> {

    private CharacterGenerator charGen;
    private int length;
    private boolean capitalize;

    public SyllableGenerator() {
        this.charGen = new CharacterGenerator();
        this.length = new NaturalGenerator().range(2,3).gen();
        this.capitalize = false;
    }

    /**
     * Set the syllable length
     *
     * @param length Syllable length
     * @return
     */
    public SyllableGenerator length(int length) {
        this.length = length;
        return this;
    }

    /**
     * Capitalize the first letter of the syllable
     *
     * @param capitalize True for capitalize, false otherwise
     * @return
     */
    public SyllableGenerator capitalize(boolean capitalize) {
        this.capitalize = capitalize;
        return this;
    }

    @Override
    public String gen() {
        StringBuilder sbr = new StringBuilder(4);
        String consonants = Constants.consonants;
        String vowels = Constants.vowels;
        String all = consonants + vowels;
        boolean lastConsonant = false;

        for (int i = 0; i < length; i++) {

            // First character to be anything
            if (i == 0) {
                Tuple<Character, Integer> ch = charGen.pool(all).genWithIndex();
                sbr.append(ch.getKey());

                // Utilise a trick to know if a constant was returned.
                // The pool is sorted as consonants => vowels so use
                // character index to determine if consonant was
                // returned in the end.
                if (ch.getVal() <= consonants.length() - 1) {
                    lastConsonant = true;
                }
            } else if (!lastConsonant) {
                // If vowel was last, then return a consonant
                sbr.append(charGen.pool(consonants).gen());
                lastConsonant = true;
            } else {
                sbr.append(charGen.pool(vowels).gen());
            }
        }

        if (capitalize) {
            return CharUtils.capitalize(sbr.toString());
        } else {
            return sbr.toString();
        }
    }
}
