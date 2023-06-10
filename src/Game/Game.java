package Game;

import Locations.*;

import java.util.Scanner;

public class Game {
    private Scanner input = new Scanner(System.in);

    public void start() {
        System.out.println("Welcome to the Adventure Game!");
        System.out.println("Please enter a name: ");
        String playerName = input.nextLine();
        Player player = new Player(playerName);
        System.out.println("Dear " + player.getName() + ", welcome to this dark and foggy island!! Everything happening here is real!!");
        System.out.println("Please select a character:");
        System.out.println("---------------------------------------------------------");
        player.selectChar();

        Location location = null;
        while (true) {
            player.printInfo();
            if (player.getInventory().getWater().equals("water") && player.getInventory().getFirewood().equals("fireWood") && player.getInventory().getFood().equals("food") && player.getInventory().getSnake().equals("treasury")) {
                System.out.println("------------CONGRATULATIONS------------");
                System.out.println();
                System.out.println("You have completed all levels and finished the game!");
                break;
            }
            System.out.println();
            System.out.println("----------------Locations----------------");
            System.out.println();
            System.out.println("1 - Safe House --> This is a safe place for you, there are no enemies!");
            System.out.println("2 - Tool Store --> You can buy weapons or armor here!");
            System.out.println("3 - Cave --> Reward<Food>, be careful, a zombie may appear!");
            System.out.println("4 - Forest --> Reward<Firewood>, be careful, a vampire may appear!");
            System.out.println("5 - River --> Reward<Water>, be careful, a bear may appear!");
            System.out.println("6 - Mine --> Be careful, a snake may appear!");
            System.out.println("0 - Exit Game --> End the game!");
            System.out.println("Please select the location you want to go to:");
            int selectloc = input.nextInt();
            boolean isCompleted = true;
            switch (selectloc) {
                case 0:
                    location = null;
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    if (player.getInventory().getFood().equals("food")) {
                        System.out.println("You have already completed this level and received the reward!");
                        isCompleted = false;
                        break;
                    }
                    location = new Cave(player);
                    break;
                case 4:
                    if (player.getInventory().getFood().equals("firewood")) {
                        System.out.println("You have already completed this level and received the reward!");
                        isCompleted = false;
                        break;
                    }
                    location = new Forest(player);
                    break;
                case 5:
                    if (player.getInventory().getFood().equals("water")) {
                        System.out.println("You have already completed this level and received the reward!");
                        isCompleted = false;
                        break;
                    }
                    location = new River(player);
                    break;
                case 6:
                    if (player.getInventory().getFood().equals("treasury")) {
                        System.out.println("You have already completed this level and received the reward!");
                        isCompleted = false;
                        break;
                    }
                    location = new Mine(player);
                    break;
                default:
                    System.out.println("Please enter a valid location!");
            }
            if (location == null) {
                System.out.println("You gave up on this dark and foggy island too soon!");
                break;
            }

            if (isCompleted) {
                if (!location.onLocation()) {
                    System.out.println("GAME OVER!");
                    break;
                }
            }
        }
    }
}
