import java.util.Scanner;
import java.util.Arrays;

public class KaraTurCharacterCreator {
    public static void main(String[] args) {
        Scanner sushiPrinter = new Scanner(System.in);

        String[] theClasses = {"Barbarian", "Bushi", "Kensai", "Monk", "Ninja", "Samurai", "Shukenja", "Sohei", "Wu Jen", "Yakuza"}; // String array for character class
        String[] theRaces = {"Human", "Korobokuru", "Hengeyokai", "Spirit Folk"}; // String array for character race
        String[] theSpirit = {"Bamboo", "River", "Sea"}; // String array for character spirit
        String[] theCreature = {"Carp", "Cat", "Crab", "Crane", "Dog", "Drake", "Fox", "Hare", "Monkey", "Raccoon Dog", "Rat", "Sparrow"}; // String array for character creature
        String[] theAlignmentGNEs = {"Good", "Neutral", "Evil"}; // String array for character good/neutral/evil alignment
        String[] theAlignmentLNCs = {"Lawful", "Neutral", "Chaotic"}; // String array for character lawful/neutral/chaotic alignment
        String[] theAbilityScores = {"Strength", "Dexterity", "Constitution", "Intelligence", "Wisdom", "Charisma"};

        String myClass = getMyClass(theClasses); // String for character class
        String myRace = getMyRace(theRaces); // String for character race
    	  String mySecondary;
        String myAlignmentGNE; // String for character good/neutral/evil alignment
        String myAlignmentLNC; // String for character lawful/neutral/chaotic alignment
        String[] theAbilityScores; // String array for character ability scores
        int[] myAbilityScores; // Integer array for character ability scores

        if(myRace.equals("Hengeyokai") || myRace.equals("Spirit Folk")){
            mySecondary = getMySecondary(myClass, myRace, theSpirit, theCreature);
        } else {
            // leave mySecondary undeclared
        }
        myAlignmentGNE = (myClass, myRace, mySecondary, theAlignmentGNEs);
        myAlignmentLNC = (myClass, myRace, mySecondary, theAlignmentLNCs);
        myAbilityScores = getMyAbilityScores(myClass, myRace, mySecondary);        
        }

        public static String getMyClass(String[] classes) {
            System.out.println("Please select your class " + "(Enter a number between 1 - " + classes.length);
            for (int i = 0; i < classes.length; i++) {
                System.out.println((i+1) + ". " + classes[i]);
            }
            Scanner input = new Scanner(System.in);
            int userInput = input.nextInt();
            return classes[userInput-1];
        }

        public static String getMyRace(String[] races, String[] classes) {
            String[] validRaces = races.clone(); // make a copy of the original races array

            // remove Hengeyokai if class is Samurai, Sohei, Barbarian, Monk, Ninja, or Yakuza
            if (Arrays.asList(classes).contains("Samurai") || Arrays.asList(classes).contains("Sohei") || Arrays.asList(classes).contains("Barbarian") || Arrays.asList(classes).contains("Monk") || Arrays.asList(classes).contains("Ninja") || Arrays.asList(classes).contains("Yakuza")) {
                validRaces = removeStringFromArray(validRaces, "Hengeyokai");
            }

            // remove Korobokuru if class is Shukenja, Sohei, Kensai, Monk, or Ninja
            if (Arrays.asList(classes).contains("Shukenja") || Arrays.asList(classes).contains("Sohei") || Arrays.asList(classes).contains("Kensai") || Arrays.asList(classes).contains("Monk") || Arrays.asList(classes).contains("Ninja")) {
                validRaces = removeStringFromArray(validRaces, "Korobokuru");
            }

            // remove Spirit Folk if class is Shukenja, Sohei, Barbarian, Wu Jen, Ninja, or Yakuza
            if (Arrays.asList(classes).contains("Shukenja") || Arrays.asList(classes).contains("Sohei") || Arrays.asList(classes).contains("Barbarian") || Arrays.asList(classes).contains("Wu Jen") || Arrays.asList(classes).contains("Ninja") || Arrays.asList(classes).contains("Yakuza")) {
                validRaces = removeStringFromArray(validRaces, "Spirit Folk");
            }

            System.out.println("Please select your race " + "(Enter a number between 1 - " + validRaces.length);
            for (int i = 0; i < validRaces.length; i++) {
                System.out.println((i+1) + ". " + validRaces[i]);
            }
            Scanner input = new Scanner(System.in);
            int userInput = input.nextInt();
            return validRaces[userInput-1];
        }

        public static String getMySecondary(String myClass, String myRace, String[] theSpirits, String[] theCreatures) {
            Scanner sushiPrinter = new Scanner(System.in);
            String[] myCreatures = {};
            String[] mySpirits = {};
            String prompt = "";

            // remove invalid creatures based on class
            if (myClass.equals("Samurai") || myClass.equals("Sohei") || myClass.equals("Kensai") || myClass.equals("Monk") || myClass.equals("Yakuza")) {
                List<String> validCreatures = new ArrayList<String>(Arrays.asList(theCreatures));
                validCreatures.remove("Cat");
                validCreatures.remove("Monkey");
                myCreatures = validCreatures.toArray(new String[validCreatures.size()]);
            } else if (myClass.equals("Shukenja")) {
                List<String> validCreatures = new ArrayList<String>(Arrays.asList(theCreatures));
                validCreatures.remove("Fox");
                validCreatures.remove("Raccoon Dog");
                validCreatures.remove("Rat");
                myCreatures = validCreatures.toArray(new String[validCreatures.size()]);
            } else if (myClass.equals("Ninja")) {
                List<String> validCreatures = new ArrayList<String>(Arrays.asList(theCreatures));
                validCreatures.remove("Carp");
                validCreatures.remove("Crane");
                validCreatures.remove("Dog");
                validCreatures.remove("Drake");
                validCreatures.remove("Hare");
                validCreatures.remove("Sparrow");
                myCreatures = validCreatures.toArray(new String[validCreatures.size()]);
            }

            if (myRace.equals("Hengeyokai")) {
                prompt = "Please select your creature (Enter a number between 1 - " + myCreatures.length + "):\n";
            } else if (myRace.equals("Spirit Folk")) {
                mySpirits = theSpirits;
                prompt = "Please select your spirit (Enter a number between 1 - " + theSpirits.length + "):\n";
            }

            int numOptions = myCreatures.length + mySpirits.length;

            if (numOptions == 0) {
                return "";
            }

            for (int i = 0; i < numOptions; i++) {
                if (myCreatures.length > 0 && i < myCreatures.length) {
                    prompt += (i + 1) + ". " + myCreatures[i] + "\n";
                } else {
                    prompt += (i + 1) + ". " + mySpirits[i - myCreatures.length] + "\n";
                }
            }

            System.out.println(prompt);

            int userInput = sushiPrinter.nextInt();

            if (myCreatures.length > 0) {
                return myCreatures[userInput - 1];
            } else {
                return mySpirits[userInput - 1];
            }
        }

        public static String myAlignmentGNE(String myClass, String myRace, String mySecondary, String[] theAlignmentGNEs) {
            Scanner scanner = new Scanner(System.in);
            String prompt = "Please select your Moral Alignment (Enter a number between 1 - ";

            // Remove invalid options based on class
            if (myClass.equals("Shukenja")) {
                theAlignmentGNEs = removeAlignment(theAlignmentGNEs, "evil");
            } else if (myClass.equals("Ninja")) {
                theAlignmentGNEs = removeAlignment(theAlignmentGNEs, "good");
            }

            // Remove invalid options based on mySecondary
            if (Arrays.asList("Carp", "Crane", "Dog", "Drake", "Hare", "Sparrow").contains(mySecondary)) {
                theAlignmentGNEs = removeAlignment(theAlignmentGNEs, "neutral", "evil");
            } else if (Arrays.asList("Fox", "Raccoon Dog", "Rat").contains(mySecondary)) {
                theAlignmentGNEs = removeAlignment(theAlignmentGNEs, "good", "neutral");
            }

            prompt += theAlignmentGNEs.length + "):\n";

            for (int i = 0; i < theAlignmentGNEs.length; i++) {
                prompt += (i + 1) + ". " + theAlignmentGNEs[i] + "\n";
            }

            System.out.println(prompt);

            int userInput = scanner.nextInt();

            return theAlignmentGNEs[userInput - 1];
        }

        private static String[] removeAlignment(String[] alignments, String... toRemove) {
            List<String> newAlignments = new ArrayList<>(Arrays.asList(alignments));
            newAlignments.removeAll(Arrays.asList(toRemove));
            return newAlignments.toArray(new String[0]);
        }



        public static String myAlignmentLNC(String myClass, String myRace, String mySecondary, String[] theAlignmentLNCs) {
            Scanner scanner = new Scanner(System.in);
            String prompt = "Please select your Moral Alignment (Enter a number between 1 - ";
            String[] availableOptions = theAlignmentLNCs;

            if (myClass.equals("Samurai") || myClass.equals("Sohei") || myClass.equals("Kensai") || myClass.equals("Monk") || myClass.equals("Yakuza")) {
                availableOptions = removeStringFromArray(theAlignmentLNCs, "Neutral");
                availableOptions = removeStringFromArray(availableOptions, "Chaotic");
            } else if (myClass.equals("Barbarian") || myClass.equals("Wu Jen")) {
                availableOptions = removeStringFromArray(theAlignmentLNCs, "Lawful");
            }

            if (mySecondary.equals("Cat") || mySecondary.equals("Monkey")) {
                availableOptions = removeStringFromArray(availableOptions, "Lawful");
                availableOptions = removeStringFromArray(availableOptions, "Neutral");
            }

            prompt += availableOptions.length + "):\n";

            for (int i = 0; i < availableOptions.length; i++) {
                prompt += (i + 1) + ". " + availableOptions[i] + "\n";
            }

            System.out.println(prompt);

            int userInput = scanner.nextInt();
            return availableOptions[userInput - 1];
        }

        // helper method to remove a string from an array
        private static String[] removeStringFromArray(String[] array, String stringToRemove) {
            List<String> list = new ArrayList<String>(Arrays.asList(array));
            list.remove(stringToRemove);
            return list.toArray(new String[0]);
        }
}
