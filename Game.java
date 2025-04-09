import java.util.*;

class Game {
    Scanner sc = new Scanner(System.in);
    boolean timeUp;
    Thread t;

    String[] questions = {
        "What is the Financial capital of India?",
        "Which planet is known as the Red Planet?",
        "What is the capital of India?",
        "Who was the first Prime Minister of India?",
        "Which is the national animal of India?",
        "What is the national sport of India?",
        "Which river is known as the Ganga's biggest tributary?",
        "Which is the largest state in India by area?",
        "Who is known as the Father of the Nation in India?",
        "Which is the national bird of India?"
    };

    String[][] options = {
    	    {"1. Mumbai", "2. Agra", "3. Bengaluru", "4. New Delhi", "5. Quit", "6. Life Line ?"},
    	    {"1. Mars", "2. Venus", "3. Jupiter", "4. Saturn", "5. Quit", "6. Life Line ?"},
    	    {"1. New Delhi", "2. Chennai", "3. Kolkata", "4. Mumbai", "5. Quit", "6. Life Line ?"},
    	    {"1. Jawaharlal Nehru", "2. Mahatma Gandhi", "3. Sardar Patel", "4. Indira Gandhi", "5. Quit", "6. Life Line ?"},
    	    {"1. Tiger", "2. Lion", "3. Elephant", "4. Peacock", "5. Quit", "6. Life Line ?"},
    	    {"1. Hockey", "2. Cricket", "3. Kabaddi", "4. Football", "5. Quit", "6. Life Line ?"},
    	    {"1. Yamuna", "2. Brahmaputra", "3. Godavari", "4. Chambal", "5. Quit", "6. Life Line ?"},
    	    {"1. Rajasthan", "2. Maharashtra", "3. Uttar Pradesh", "4. Madhya Pradesh", "5. Quit", "6. Life Line ?"},
    	    {"1. Mahatma Gandhi", "2. Subhash Chandra Bose", "3. B.R. Ambedkar", "4. Bhagat Singh", "5. Quit", "6. Life Line ?"},
    	    {"1. Peacock", "2. Eagle", "3. Sparrow", "4. Parrot", "5. Quit", "6. Life Line ?"}
    	};

    int[] answers = {1, 1, 1, 1, 1, 1, 1, 1, 1, 1};
    int[] lifeline1choices = {2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[] rewards = {1000, 2000, 3000, 4000, 5000, 6000, 7000, 8000, 9000, 10000};

    void startTimer() {
        t = new Thread(() -> {
            try {
                System.out.println("\nRemaining time: ");
                for (int i = 30; i > 0; i--) {
                    System.out.print(i + " ");
                    Thread.sleep(1000);
                }
                System.out.println("\nXXXXXXXXXXXXX");
                System.out.println("XXXXXXXXXXXXX:::::::::::::::: Time's up! You won Rs." + Player.prizeMoney + " before termination.");
                System.out.println("XXXXXXXXXXXXX");
                timeUp = true;
            } catch (InterruptedException e) {

            }
        });
        t.start();
    }

    void start(Player p) {
    	
    	System.out.println(" |***************************************************|"
                       + "\n |*           Please read the Rules carefully:      *|"
                       + "\n |* 1.  Game starts only if you type 'yes'.         *|"
                       + "\n |* 2.  There are 12 multiple-choice questions.     *|"
                       + "\n |* 3.  Each question has 6 options.                *|"
                       + "\n |* 4.  You have 30 seconds to answer each.         *|"
                       + "\n |* 5.  Time out ends game with prize so far.       *|"
                       + "\n |* 6.  Each correct answer increases reward.       *|"
                       + "\n |* 7.  Wrong answer ends the game.                 *|"
                       + "\n |* 8.  Choose option 5 to quit with your prize.    *|"
                       + "\n |* 9.  Lifelines: 50-50 and Skip (once each).      *|"
                       + "\n |* 10. 50-50 shows 1 correct & 1 wrong option.     *|"
                       + "\n |* 11. Skip moves to next question safely.         *|"
                       + "\n |* 12. Lifelines can be used only once.            *|"
                       + "\n |* 13. Game warns when lifelines are over.         *|"
                       + "\n |* 14. Invalid inputs are rejected with retry.     *|"
                       + "\n |* 15. Answering all wins maximum prize!           *|"
                       + "\n |***************************************************|");

        System.out.println("\nAre you ready? (yes/no)");
        String response = sc.next();
        if (!response.equalsIgnoreCase("yes")) {
        	System.out.println("XXXXXXXXXXXXX");
            System.out.println("XXXXXXXXXXXXX:::::::::::::::: Game terminated. Thank you!");
            System.out.println("XXXXXXXXXXXXX");
            try {
            	System.exit(0);
            }
            catch (Exception e) {
            	System.out.println("There is a problem while exiting. Please try to exit manually");
            }
            
        } else {
        	System.out.println("\nLet's Go.....!\n");
        	try {
        		playGame(p);
        	}
        	catch (Exception e) {
        		System.out.println("Something is wrong while starting the game.");
        	}
        }
    }

    void playGame(Player p) {
        for (int i = 0; i < questions.length; i++) {
            timeUp = false;
            System.out.println("\nQuestion " + (i + 1) + ": " + questions[i]);
            for (String opt : options[i]) {
                System.out.println(opt);
            }
            try {
            	startTimer();
            }
            catch(Exception e) {
            	System.out.println("Issue while starting the timer.");
            }
            
            while (!timeUp) {
            	System.out.print("________________________");
                System.out.print("\nEnter your choice (1-6): ");
                int choice = sc.nextInt();
                
                switch (choice) {
                    case 1: case 2: case 3: case 4:
                        if (choice == answers[i]) {
                        	try {
                        		t.interrupt();
                        	}
                        	catch (Exception e) {
                        		System.out.println("Issue while stopping the timer.");
                        	}
                            p.prizeMoney += rewards[i];
                            System.out.println("XXXXXXXXXXXXX");
                            System.out.println("XXXXXXXXXXXXX:::::::::::::::: Correct! Current Purse is: Rs." + p.prizeMoney);
                            System.out.println("XXXXXXXXXXXXX");
                        } else {
                        	try {
                        		t.interrupt();
                        	}
                        	catch (Exception e) {
                        		System.out.println("Issue while stopping the timer.");
                        	}
                            System.out.println("XXXXXXXXXXXXX");
                            System.out.println("XXXXXXXXXXXXX:::::::::::::::: Wrong answer! You won Rs." + p.prizeMoney + " before termination.");
                            System.out.println("XXXXXXXXXXXXX");
                            return;
                        }
                        break;
                    case 5:
                    	System.out.println("XXXXXXXXXXXXX");
                    	System.out.println("XXXXXXXXXXXXX:::::::::::::::: You have quit the game with Rs." + p.prizeMoney);
                    	System.out.println("XXXXXXXXXXXXX");
                    	try {
                    		t.interrupt();
                    	}
                    	catch (Exception e) {
                    		System.out.println("Issue while stopping the timer.");
                    	}
                    	try {
                        	System.exit(0);
                        }
                        catch (Exception e) {
                        	System.out.println("There is a problem while exiting. Please try to exit manually");
                        }
                    case 6:
                        if(useLifeline(p, i)) continue;
                        break;
                    default:
                    	System.out.println("XXXXXXXXXXXXX");
                        System.out.println("XXXXXXXXXXXXX:::::::::::::::: Invalid choice. Try again ");
                        System.out.println("XXXXXXXXXXXXX");
                        continue;
                }
                break;
            }
        }
        System.out.println("\n|XXXXXXXXXXXX::::::::::::::::::::::::::::::::::::::::::::::::::::");
        System.out.println(  "|XXXXXXXXXXXX----Congratulations {" + Player.name + "} ! You have won Rs." + p.prizeMoney);
        System.out.println(  "|XXXXXXXXXXXX::::::::::::::::::::::::::::::::::::::::::::::::::::\n");
    }

    boolean useLifeline(Player p, int questionIndex) {
        if (!p.lifeline1 && !p.lifeline2) {
        	System.out.println("XXXXXXXXXXXXX");
            System.out.println("XXXXXXXXXXXXX:::::::::::::::: No lifelines left! Choose an answer ");
            System.out.println("XXXXXXXXXXXXX");
            return true;
        }
        	
        System.out.println("__________________");
        System.out.println("Choose a lifeline: \n1. 50-50 \n2. Skip\n");
        int choice = sc.nextInt();
        
        switch (choice) {
            case 1:
                if (p.lifeline1) {
                    p.lifeline1 = false;
                    
                    System.out.println("______________________________");
                    System.out.println("50-50 used. Remaining options:");
                    System.out.println("1. Option " + answers[questionIndex]);
                    System.out.println("2. Option " + lifeline1choices[questionIndex] + "\n");
                    return true;
                } else {
                	System.out.println("XXXXXXXXXXXXX");
                    System.out.println("XXXXXXXXXXXXX:::::::::::::::: 50-50 already used ");
                    System.out.println("XXXXXXXXXXXXX");
                    return true;
                }
            case 2:
                if (p.lifeline2) {
                    p.lifeline2 = false;
                    System.out.println("XXXXXXXXXXXXX");
                    System.out.println("XXXXXXXXXXXXX:::::::::::::::: Skip used. Moving to next question ");
                    System.out.println("XXXXXXXXXXXXX");
                    t.interrupt();
                    return false;
                } else {
                	System.out.println("XXXXXXXXXXXXX");
                    System.out.println("XXXXXXXXXXXXX:::::::::::::::: Skip already used");
                    System.out.println("XXXXXXXXXXXXX");
                    return true;
                }
            default:
            	System.out.println("XXXXXXXXXXXXX");
                System.out.println("XXXXXXXXXXXXX:::::::::::::::: Invalid lifeline choice ");
                System.out.println("XXXXXXXXXXXXX");
                return true;
        }
    }
}