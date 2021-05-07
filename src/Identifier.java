import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Identifier {

    private final HashSet<Character> excludedCharacters = new HashSet<Character>(
            Set.of('!', '\"', '#', '$', '%', '&', '\'', '(', ')', '*', '+', ',', '-', '.', '/', ':',
            ';', '<', '=', '>', '?', '@', '[', '\\', ']', '^', '_', '`', '{', '|', '}', '~'));

    private final HashSet<Character> charactersToMatch;
    ArrayList<String> words;
    ArrayList<String> matches;
    private final int charactersNumber;
    private final int matchingCharactersNumber;

    public Identifier(String characterInput, String input){
        charactersToMatch = GetCharactersToMatch(characterInput);
        String trimmedInput = removeExcludedCharacters(input);
        words = new ArrayList<String>(Arrays.asList(trimmedInput.split(" ")));
        charactersNumber = countCharacters(trimmedInput);
        matchingCharactersNumber = countMatchingInString(trimmedInput);
        compute();
    }

    public String getOutput(){
        StringBuilder builder = new StringBuilder();

        for (String match:matches
        ) {
            builder.append(match);
            if (!match.equals("")) builder.append(("\n"));
        }

        return new String(builder);
    }

    private void compute(){
        matches = new ArrayList<>();

        for (String word : words
        ) {
            matches.add(createMatch(word));
        }

        matches.add(createSummary());
    }

    private String createSummary(){
        StringBuilder builder = new StringBuilder();

        builder.append("Total Frequency: ");
        builder.append(String.format("%.2f", (double)matchingCharactersNumber/charactersNumber));
        builder.append(" (");
        builder.append(matchingCharactersNumber);
        builder.append("/");
        builder.append(charactersNumber);
        builder.append(")");

        return new String(builder);
    }

    private String createMatch(String word){
        StringBuilder builder = new StringBuilder();
        builder.append("{(");
        int matchingCharacters = 0;
        HashSet<Character> includedCharacters = new HashSet<>();

        for (char character:word.toCharArray()
             ) {
            if(charactersToMatch.contains(character)){
                if (matchingCharacters > 0 && !includedCharacters.contains(character)) builder.append(", ");
                matchingCharacters++;
                if (!includedCharacters.contains(character)){
                    builder.append(character);
                    includedCharacters.add(character);
                }
            }
        }

        builder.append("), ");
        builder.append(word.length());
        builder.append("} = ");
        builder.append(String.format("%.2f", (double)matchingCharacters/matchingCharactersNumber));
        builder.append(" (");
        builder.append(matchingCharacters);
        builder.append("/");
        builder.append(matchingCharactersNumber);
        builder.append(")");

        if (matchingCharacters == 0) return new String("");
        else return new String(builder);
    }

    private String removeExcludedCharacters(String input){
        StringBuilder builder = new StringBuilder("");

        for (int i = 0; i < input.length(); i++){
            if (!excludedCharacters.contains(input.charAt(i))){
                builder.append(input.charAt(i));
            }
        }

        return new String(builder);
    }

    private int countCharacters(String string){
        return string.replaceAll("\\s","").length();
    }

    private int countMatchingInString(String input){
        int count = 0;

        for (char character:input.toCharArray()
        ) {
            if (charactersToMatch.contains(character)){
                count++;
            }
        }

        return count;
    }

    private HashSet<Character> GetCharactersToMatch(String input){
        HashSet<Character> characters = new HashSet<>();

        for (char character:input.split(" ")[0].toCharArray()
        ) {
            characters.add(character);
        }

        return characters;
    }
}