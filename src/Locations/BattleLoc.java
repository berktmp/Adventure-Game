package Locations;

import Obstacle.Obstacle;

import java.util.Random;

public abstract class BattleLoc extends Location {
    private Obstacle obstacle;
    private String award;
    private int maxObstacle;

    public BattleLoc(Player player, String name, Obstacle obstacle, String award, int maxObstacle) {
        super(player, name);
        this.obstacle = obstacle;
        this.award = award;
        this.maxObstacle = maxObstacle;
    }

    @Override
    boolean onLocation() {
        int obsNumber = this.randomObstacleNumber();
        System.out.println("You are currently at: " + this.getName());
        System.out.println("Be careful! There are " + obsNumber + " " + this.getObstacle().getName() + " living here!");
        System.out.print("Enter <F>ight or <R>un: ");
        String selectCase = Location.input.nextLine();
        selectCase = selectCase.toUpperCase();

        if (selectCase.equals("F") && combat(obsNumber)) {
            // Fight
            if (combat(obsNumber)) {
                System.out.println("You have defeated all the enemies at " + this.getName() + "!");
                return true;
            }
        }

        if (this.getPlayer().getHealth() <= 0) {
            System.out.println("You died!");
            return false;
        }

        return true;
    }

    public boolean combat(int obsNumber) {
        for (int i = 1; i <= obsNumber; i++) {
            int firstHit = firstHit();
            this.getObstacle().setHealth(this.getObstacle().getOriginalHealth());
            playerStats();
            obstacleStats(i);

            while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0) {
                System.out.print("Attack <A> or <R>un: ");
                String selectCombat = Location.input.nextLine().toUpperCase();

                if (selectCombat.equals("A")) {
                    if (firstHit == 0) {
                        System.out.println("You attacked!");
                        this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();

                        if (this.getObstacle().getHealth() > 0) {
                            System.out.println();
                            System.out.println("The enemy attacked you!");
                            int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                            if (obstacleDamage < 0) {
                                obstacleDamage = 0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                            afterHit();
                        }
                    } else if (firstHit == 1) {
                        if (this.getObstacle().getHealth() > 0) {
                            System.out.println();
                            System.out.println("The enemy attacked you!");
                            int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                            if (obstacleDamage < 0) {
                                obstacleDamage = 0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                            afterHit();
                        }
                        System.out.println("You attacked!");
                        this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                        afterHit();
                    }
                } else {
                    return false;
                }
            }

            if (this.getObstacle().getHealth() < this.getPlayer().getHealth()) {
                System.out.println("You have defeated the enemy!");
                if (this.getObstacle().getName().equals("Snake")) {
                    snakeAward();
                }
                System.out.println("You earned " + this.getObstacle().getAward() + " money!");
                this.getPlayer().setMoney(this.getPlayer().getMoney() + this.getObstacle().getAward());
                locationAward();
                System.out.println("Your current money: " + this.getPlayer().getMoney());
            } else {
                return false;
            }
        }

        return true;
    }

    public void snakeAward() {
        Random r = new Random();
        int chance = r.nextInt(100);
        int itemChance = r.nextInt(100);
        int weaponChance = r.nextInt(100);
        int armorChance = r.nextInt(100);
        int moneyChance = r.nextInt(100);

        if (chance < 55) {
            // An item is dropped
            if (itemChance <= 30) {
                if (weaponChance <= 20) {
                    this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(3));
                    System.out.println("You earned the weapon: " + this.getPlayer().getInventory().getWeapon().getName() + "!");
                } else if (weaponChance > 20 && weaponChance <= 50) {
                    this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(2));
                    System.out.println("You earned the weapon: " + this.getPlayer().getInventory().getWeapon().getName() + "!");
                } else if (weaponChance > 50) {
                    this.getPlayer().getInventory().setWeapon(Weapon.getWeaponObjByID(1));
                    System.out.println("You earned the weapon: " + this.getPlayer().getInventory().getWeapon().getName() + "!");
                }
            } else if (itemChance > 30 && itemChance <= 60) {
                if (armorChance <= 20) {
                    this.getPlayer().getInventory().setArmor(Armor.getArmorObjByID(3));
                    System.out.println("You earned the armor: " + this.getPlayer().getInventory().getArmor().getName() + "!");
                } else if (armorChance > 20 && armorChance <= 50) {
                    this.getPlayer().getInventory().setArmor(Armor.getArmorObjByID(2));
                    System.out.println("You earned the armor: " + this.getPlayer().getInventory().getArmor().getName() + "!");
                } else if (armorChance > 50) {
                    this.getPlayer().getInventory().setArmor(Armor.getArmorObjByID(1));
                    System.out.println("You earned the armor: " + this.getPlayer().getInventory().getArmor().getName() + "!");
                }
            } else if (itemChance > 60) {
                if (moneyChance <= 20) {
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + 10);
                    System.out.println("You earned 10 gold!");
                } else if (moneyChance > 20 && moneyChance <= 50) {
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + 5);
                    System.out.println("You earned 5 gold!");
                } else if (moneyChance > 50) {
                    this.getPlayer().setMoney(this.getPlayer().getMoney() + 1);
                    System.out.println("You earned 1 gold!");
                }
            }
        } else {
            System.out.println("You didn't earn any rewards!");
        }
    }

    public void locationAward() {
        if (this.getName().equals("Cave")) {
            this.getPlayer().getInventory().setFood(this.award);
        }
        if (this.getName().equals("Forest")) {
            this.getPlayer().getInventory().setFood(this.award);
        }
        if (this.getName().equals("River")) {
            this.getPlayer().getInventory().setFood(this.award);
        }
        if (this.getName().equals("Mine")) {
            this.getPlayer().getInventory().setFood(this.award);
        }
    }

    public int firstHit() {
        Random r = new Random();
        return r.nextInt(2);
    }

    public void afterHit() {
        System.out.println("Your Health: " + this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName() + " Health: " + this.getObstacle().getHealth());
        System.out.println();
    }

    public void playerStats() {
        System.out.println("Player Stats");
        System.out.println("--------------------");
        System.out.println("Health: " + this.getPlayer().getHealth());
        System.out.println("Weapon: " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Armor: " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Block: " + this.getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Damage: " + this.getPlayer().getTotalDamage());
        System.out.println("Money: " + this.getPlayer().getMoney());
    }

    public void obstacleStats(int i) {
        System.out.println(i + " - " + this.getObstacle().getName() + " Stats");
        System.out.println("--------------------");
        System.out.println("Health: " + this.getObstacle().getHealth());
        System.out.println("Damage: " + this.getObstacle().getDamage());
        System.out.println("Reward: " + this.getObstacle().getAward());
    }

    public int randomObstacleNumber() {
        Random r = new Random();
        return r.nextInt(this.maxObstacle) + 1;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public void setAward(String award) {
        this.award = award;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }
}
