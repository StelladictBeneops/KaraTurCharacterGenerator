import java.util.Scanner;
import java.util.Arrays;
import java.util.Random;


public class KaraTurCharacterCreator {
    public static void main(String[] args) {
        Scanner sushiPrinter = new Scanner(System.in);

        String[] theClasses = {"Barbarian", "Bushi", "Kensai", "Monk", "Ninja", "Samurai", "Shukenja", "Sohei", "Wu Jen", "Yakuza"};
        String[] theRaces = {"Human", "Korobokuru", "Hengeyokai", "Spirit Folk"};
        String[] theSpirit = {"Bamboo", "River", "Sea"};
        String[] theCreature = {"Carp", "Cat", "Crab", "Crane", "Dog", "Drake", "Fox", "Hare", "Monkey", "Raccoon Dog", "Rat", "Sparrow"};
        String[] theAlignmentGNEs = {"Good", "Neutral", "Evil"}; 
        String[] theAlignmentLNCs = {"Lawful", "Neutral", "Chaotic"}; 

        String myClass = getMyClass(theClasses); 
        String myRace = getMyRace(theRaces); 
    	String mySecondary;
        String myAlignmentGNE; 
        String myAlignmentLNC; 

        if(myRace.equals("Hengeyokai") || myRace.equals("Spirit Folk")){
            mySecondary = getMySecondary(myClass, myRace, theSpirit, theCreature);
        } else {}
        
        myAlignmentGNE = (myClass, myRace, mySecondary, theAlignmentGNEs);
        myAlignmentLNC = (myClass, myRace, mySecondary, theAlignmentLNCs);

        int STR = getSTR(myClass, myRace, mySecondary);
        int DEX = getDEX(myClass, myRace, mySecondary);
        int CON = getCON(myClass, myRace, mySecondary);
        int INT = getINT(myClass, myRace, mySecondary);
        int WIS = getWIS(myClass, myRace, mySecondary);
        int CHA = getCHA(myClass, myRace, mySecondary);
        int COM = getCOM(myRace, CHA);

        System.out.println("Character Name: " + myName());
        System.out.println("Class: " + myClass());
        if (mySecondary != null) {
            System.out.println("Race: " + mySecondary + " " + myRace());
        } else {
            System.out.println("Race: " + myRace());
        }
        System.out.println("Ability Scores:");
        System.out.println("STR: " + STR);
        System.out.println("DEX: " + DEX);
        System.out.println("CON: " + CON);
        System.out.println("INT: " + INT);
        System.out.println("WIS: " + WIS);
        System.out.println("CHA: " + CHA);
        System.out.println("COM: " + COM);
        
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
        
        public static int roll3d6() {
            Random rand = new Random();
            int result = 0;
            for (int i = 0; i < 3; i++) {
                result += rand.nextInt(6) + 1;
            }
            return result;
        }

        public static int roll6TimesTakeHighest() {
            int highest = 0;
            for (int i = 0; i < 6; i++) {
                int roll = roll3d6();
                if (roll > highest) {
                    highest = roll;
                }
            }
            return highest;
        }

        private static int getSTR(String myClass, String myRace, String mySecondary) {
            int STR = roll6TimesTakeHighest();
            if (myRace.equals("Korobokuru")) {
                STR++;
            }
            if (mySecondary.equals("Carp")) {
                STR--;
            } else if (mySecondary.equals("Crab")) {
                STR += 2;
            } else if (mySecondary.equals("Hare")) {
                STR--;
            } else if (mySecondary.equals("Raccoon Dog")) {
                STR += 2;
            }
            if (myRace.equals("Spirit Folk") && STR < 6) {
                STR = 6;
            } else if (myRace.equals("Korobokuru") && STR < 8) {
                STR = 8;
            } else if ((myClass.equals("Shukenja") || myClass.equals("Bushi")) && STR < 9) {
                STR = 9;
            } else if ((myRace.equals("Hengeyokai") || myClass.equals("Kensai")) && STR < 12) {
                STR = 12;
            } else if ((myClass.equals("Samurai") || myClass.equals("Sohei")) && STR < 13) {
                STR = 13;
            } else if ((myClass.equals("Barbarian") || myClass.equals("Monk")) && STR < 15) {
                STR = 15;
            } else if ((myClass.equals("Shukenja") || myClass.equals("Monk") || myClass.equals("Wu Jen") || myClass.equals("Yakuza")) && STR > 18) {
                STR = 18;
            }
            return STR;
        }
    
        public static int getDEX(String myClass, String myRace, String mySecondary) {
            int DEX = roll6TimesTakeHighest();

            if (mySecondary.equals("Cat")) {
                DEX += 1;
            } else if (mySecondary.equals("Crane")) {
                DEX -= 1;
            } else if (mySecondary.equals("Drake")) {
                DEX -= 1;
            } else if (mySecondary.equals("Monkey")) {
                DEX += 2;
            }

            if (myRace.equals("Korobokuru") && DEX < 6) {
                DEX = 6;
            } else if (myClass.equals("Bushi") && DEX < 8) {
                DEX = 8;
            } else if (myRace.equals("Hengeyokai") && DEX < 9) {
                DEX = 9;
            } else if (myRace.equals("Spirit Folk") && DEX < 12) {
                DEX = 12;
            } else if ((myClass.equals("Kensai") || myClass.equals("Barbarian") || myClass.equals("Ninja")) && DEX < 14) {
                DEX = 14;
            } else if (myClass.equals("Monk") && DEX < 15) {
                DEX = 15;
            }

            return DEX;
        }

        public static int getCON(String myClass, String myRace, String mySecondary) {
            int CON = roll6TimesTakeHighest();

            if (myRace.equals("Korobokuru")) {
                CON += 1;
            }

            if (mySecondary.equals("Dog")) {
                CON += 1;
            } else if (mySecondary.equals("Rat")) {
                CON += 2;
            } else if (mySecondary.equals("Sparrow")) {
                CON -= 2;
            }

            if (CON < 6 && myRace.equals("Spirit Folk")) {
                CON = 6;
            } else if (CON < 8 && myClass.equals("Bushi")) {
                CON = 8;
            } else if (CON < 9 && myClass.equals("Shukenja")) {
                CON = 9;
            } else if (CON < 10 && myClass.equals("Sohei")) {
                CON = 10;
            } else if (CON < 11 && myClass.equals("Monk")) {
                CON = 11;
            } else if (CON < 12 && (myRace.equals("Korobokuru") || myRace.equals("Hengeyokai"))) {
                CON = 12;
            } else if (CON < 13 && myClass.equals("Samurai")) {
                CON = 13;
            } else if (CON > 14 && myRace.equals("Spirit Folk")) {
                CON = 14;
            } else if (CON < 15 && myClass.equals("Barbarian")) {
                CON = 15;
            }

            return CON;
        }

        public static int getINT(String myClass, String myRace, String mySecondary) {
            int INT = roll6TimesTakeHighest();
            if (myRace.equals("Korobokuru")) {
                INT -= 2;
            }
            if (mySecondary.equals("Fox")) {
                INT += 1;
            } else if (mySecondary.equals("Dog")) {
                INT -= 1;
            }

            if (INT < 3 && myRace.equals("Korobokuru")) {
                INT = 3;
            } else if (INT < 12 && (myRace.equals("Hengeyokai") || myRace.equals("Spirit Folk"))) {
                INT = 12;
            } else if (INT < 13 && myClass.equals("Wu Jen")) {
                INT = 13;
            } else if (INT < 14 && myClass.equals("Samurai")) {
                INT = 14;
            } else if (INT > 15 && (myClass.equals("Ninja") || myRace.equals("Korobokuru"))) {
                INT = 15;
            }
            return INT;
        }

        public int getWIS(String myClass, String myRace, String mySecondary) {
            int WIS = roll6TimesTakeHighest();
            if (mySecondary.equals("Carp")) {
                WIS += 1;
            } else if (mySecondary.equals("Crane")) {
                WIS += 1;
            } else if (mySecondary.equals("Hare")) {
                WIS += 1;
            } else if (mySecondary.equals("Cat")) {
                WIS -= 1;
            } else if (mySecondary.equals("Fox")) {
                WIS -= 1;
            } else if (mySecondary.equals("Monkey")) {
                WIS -= 2;
            } else if (mySecondary.equals("Raccoon Dog")) {
                WIS -= 2;
            }

            if (WIS < 9 && myRace.equals("Spirit Folk")) {
                WIS = 9;
            } else if (WIS < 10 && myClass.equals("Sohei")) {
                WIS = 10;
            } else if (WIS < 12 && (myClass.equals("Kensai") || myClass.equals("Shukenja") || myRace.equals("Hengeyokai"))) {
                WIS = 12;
            } else if (WIS < 13 && myClass.equals("Samurai")) {
                WIS = 13;
            } else if (WIS < 15 && myClass.equals("Monk")) {
                WIS = 15;
            } else if (WIS > 16 && myClass.equals("Barbarian")) {
                WIS = 16;
            } else if (WIS > 17 && myRace.equals("Korobokuru")) {
                WIS = 17;
            }

            return WIS;
        }

        public static int getCHA(String myClass, String myRace, String mySecondary) {
            int CHA = roll6TimesTakeHighest();
            if (mySecondary.equals("Drake")) {
                CHA += 1;
            } else if (mySecondary.equals("Sparrow")) {
                CHA += 2;
            } else if (mySecondary.equals("Crab")) {
                CHA -= 2;
            } else if (mySecondary.equals("Rat")) {
                CHA -= 2;
            }

            if (CHA < 12 && myRace.equals("Hengeyokai")) {
                CHA = 12;
            } else if (CHA < 14 && (myRace.equals("Spirit Folk") || myClass.equals("Ninja"))) {
                CHA = 14;
            } else if (CHA > 16 && myRace.equals("Korobokuru")) {
                CHA = 16;
            } else if (CHA > 17 && myRace.equals("Hengeyokai")) {
                CHA = 17;
            }

            return CHA;
        }
        
        public static int getCOM(String myRace, int CHA) {
            int COM = roll6TimesTakeHighest();

            if (myRace.equals("Korobokuru")) {
                COM -= 2;
            } else if (myRace.equals("Hengeyokai")) {
                COM -= 1;
            } else if (myRace.equals("Spirit Folk")) {
                COM += 1;
            }

            if (CHA <= 2) {
                COM -= 8;
            } else if (CHA == 3) {
                COM -= 5;
            } else if (CHA == 4 || CHA == 5) {
                COM -= 3;
            } else if (CHA >= 6 && CHA <= 8) {
                COM -= 1;
            } else if (CHA >= 13 && CHA <= 15) {
                COM += 1;
            } else if (CHA >= 16 && CHA <= 17) {
                COM += 2;
            } else if (CHA == 18) {
                COM += 3;
            } else if (CHA >= 19) {
                COM += 5;
            }

            return COM;
        }

    private static String[] removeStringFromArray(String[] array, String stringToRemove) {
            List<String> list = new ArrayList<String>(Arrays.asList(array));
            list.remove(stringToRemove);
            return list.toArray(new String[0]);
        }
    
    
}
