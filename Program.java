import java.util.Scanner;

import java.util.List;
import java.util.Random;

public class Program 
{
    public static void Game(List<String> words, String wordToFind, String[][] colors, String[][] guesses, int row, boolean won, String error)
    {
        String wordGuessed = "";

        System.out.println(" *  *  *  *  WORDLE  *  *  *  *\n");

        for (int i = 0; i < 6; i++) 
        {
            System.out.print("-------------------------------\n|");

            for (int j = 0; j < 5; j++) 
            {
                if(j == 4)
                {
                    if(colors[i][j] == "11111")
                    {
                        System.out.print(ConsoleColors.GREEN_BACKGROUND + "     " + ConsoleColors.RESET + "|\n|");
                    }
                    else if(colors[i][j] == "22222")
                    {
                        System.out.print(ConsoleColors.YELLOW_BACKGROUND + "     " + ConsoleColors.RESET + "|\n|");
                    }
                    else
                    {
                        System.out.print("     |\n|");
                    }
                }
                else
                {
                    if(colors[i][j] == "11111")
                    {
                        System.out.print(ConsoleColors.GREEN_BACKGROUND + "     " + ConsoleColors.RESET + "|");
                    }
                    else if(colors[i][j] == "22222")
                    {
                        System.out.print(ConsoleColors.YELLOW_BACKGROUND + "     " + ConsoleColors.RESET + "|");
                    }
                    else
                    {
                        System.out.print("     |");
                    }
                }
            }

            for (int j = 0; j < 5; j++) 
            {
                if(j == 4)
                {
                    if(colors[i][j] == "11111")
                    {
                        System.out.print(ConsoleColors.GREEN_BACKGROUND + "  " + guesses[i][j] + "  " + ConsoleColors.RESET + "|\n|");
                    }
                    else if(colors[i][j] == "22222")
                    {
                        System.out.print(ConsoleColors.YELLOW_BACKGROUND + "  " + guesses[i][j] + "  " + ConsoleColors.RESET + "|\n|");
                    }
                    else
                    {
                        System.out.print("  " + guesses[i][j] + "  |\n|");
                    }
                }
                else
                {
                    if(colors[i][j] == "11111")
                    {
                        System.out.print(ConsoleColors.GREEN_BACKGROUND + "  " + guesses[i][j] + "  " + ConsoleColors.RESET + "|");
                    }
                    else if(colors[i][j] == "22222")
                    {
                        System.out.print(ConsoleColors.YELLOW_BACKGROUND + "  " + guesses[i][j] + "  " + ConsoleColors.RESET + "|");
                    }
                    else
                    {
                        System.out.print("  " + guesses[i][j] + "  |");
                    }
                    
                }
            }

            for (int j = 0; j < 5; j++) 
            {
                if(colors[i][j] == "11111")
                {
                    System.out.print(ConsoleColors.GREEN_BACKGROUND + "     " + ConsoleColors.RESET + "|");
                }
                else if(colors[i][j] == "22222")
                {
                    System.out.print(ConsoleColors.YELLOW_BACKGROUND + "     " + ConsoleColors.RESET + "|");
                }
                else
                {
                    System.out.print("     |");
                }
            }            

            System.out.println();
        }

        System.out.print("-------------------------------\n");

        if(won)
        {
            System.out.println("You won!");
        }
        else if(row == 6 && !won)
        {
            System.out.println("You lose!");
            System.out.println("Word: " + wordToFind);
        }
        else
        {
            if(error != "")
            {
                System.out.println(error);
            }

            System.out.print("Type a word to check: ");
    
            Scanner obj = new Scanner(System.in);
            wordGuessed = obj.nextLine();
            //obj.close();
            
            if(wordGuessed.length() != 5 || !words.contains(wordGuessed))
            {
                try 
                {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                } 
                catch (Exception e) 
                {
                    System.out.print(e);
                }

                if(wordGuessed.length() != 5)
                {   
                    error = "Word '" + wordGuessed + "' is not 5 letters long!";
                }
                else if(!words.contains(wordGuessed))
                {
                    error = "Word '" + wordGuessed + "' doesn't exist as an option!";
                }
    
                System.out.flush();
                Game(words, wordToFind, colors, guesses, row, false, error);
            }
            else
            {
                error = "";
                char wordToFindArray[] = wordToFind.toCharArray();
                char wordGuessedArray[] = wordGuessed.toCharArray();
        
                for (int i = 0; i < 5; i++) 
                {
                    guesses[row][i] = Character.toString(wordGuessedArray[i]);
                }
        
                for (int i = 0; i < 5; i++) 
                {
                    if(wordToFindArray[i] == wordGuessedArray[i])
                    {
                        guesses[row][i] = ConsoleColors.BLACK + ConsoleColors.GREEN_BACKGROUND + Character.toString(wordGuessedArray[i]);
                        colors[row][i] = "11111";
                    }
                    else if(wordToFind.contains(Character.toString(wordGuessedArray[i])))
                    {
                        guesses[row][i] = ConsoleColors.BLACK + ConsoleColors.YELLOW_BACKGROUND + Character.toString(wordGuessedArray[i]);
                        colors[row][i] = "22222";
                    }
                }
    
                row++;
    
                try 
                {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                } 
                catch (Exception e) 
                {
                    System.out.print(e);
                }
    
                System.out.flush();
    
                if(wordToFind.equals(wordGuessed))
                {
                    Game(words, wordToFind, colors, guesses, row, true, "");
                }
                else
                {
                    Game(words, wordToFind, colors, guesses, row, false, "");
                }
            }
        }
    }

    public static void main(String[] args) 
    {
        List<String> words = ReadWords.readWordsList();
        Random random = new Random();
        String wordToFind = words.get(random.nextInt(2501));
        String[][] guesses = new String[6][5];
        String[][] colors = new String[6][5];
        //System.out.println(wordToFind);

        for (int i = 0; i < 6; i++) 
        {
            for (int j = 0; j < 5; j++) 
            {
                guesses[i][j] = " ";
                colors[i][j] = "00000";
            }
        }

        Game(words, wordToFind, colors, guesses, 0, false, "");
    }   
}