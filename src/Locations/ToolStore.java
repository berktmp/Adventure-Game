package Locations;

public class ToolStore extends NormalLoc {
    public ToolStore(Player player) {
        super(player, "Mağaza");
    }

    @Override
    public boolean onLocation(){
        System.out.println("----- Welcome to the Store! -----");
        boolean showMenu = true;
        while(showMenu){
           System.out.println("1 - Weapons");
           System.out.println("2 - Armors");
           System.out.println("3 - Exit");
           System.out.print("Your choice: ");
           int selectCase = Location.input.nextInt();

           while(selectCase < 1 || selectCase > 3){
               System.out.print("Invalid value, please enter again: ");
               selectCase = Location.input.nextInt();
           }
           switch (selectCase){
               case 1:
                   printWeapon();
                   buyWeapon();
                   break;
               case 2:
                   printArmor();
                   buyArmor();
                   break;
               case 3:
                   System.out.println("See you again!");
                   showMenu = false;
                   break;
           }
       }
        return true;
    }
    
    public void printWeapon(){
        System.out.println("------ Weapons ------");
        System.out.println();
        for (Weapon weapon : Weapon.weapons()){
            System.out.println(weapon.getId()+" - "
                    +weapon.getName() +
                    " <Price: "+ weapon.getPrice() +
                    ", Damage: " + weapon.getDamage()+ ">");
        }
        System.out.println("0 - Exit");
    }

    public void buyWeapon(){
        System.out.println("Select a weapon: ");
        int selectWeaponID = Location.input.nextInt();
        while (selectWeaponID < 0 || selectWeaponID > Weapon.weapons().length){
            System.out.println("Invalid value, please enter again:");
            selectWeaponID = Location.input.nextInt();
        }
        if (selectWeaponID != 0){
            Weapon selectedWeapon = Weapon.getWeaponObjByID(selectWeaponID);
            if (selectedWeapon != null){
                if (selectedWeapon.getPrice() > this.getPlayer().getMoney()){
                    System.out.println("Insufficient funds!");
                }else {
                    // Purchase
                    System.out.println("You purchased the weapon: " + selectedWeapon.getName() + "!");
                    int balance = this.getPlayer().getMoney() - selectedWeapon.getPrice();
                    this.getPlayer().setMoney(balance);
                    System.out.println("Your remaining balance: " + this.getPlayer().getMoney());
                    System.out.println("Previous Weapon: " + this.getPlayer().getInventory().getWeapon().getName());
                    this.getPlayer().getInventory().setWeapon(selectedWeapon);
                    System.out.println("New Weapon: " + this.getPlayer().getInventory().getWeapon().getName());
                }
            }
        }

    }
    
    public void printArmor(){
        System.out.println("------ Armors ------");
        for (Armor armor : Armor.armors()){
            System.out.println(armor.getId() + " - " +
                    armor.getName() +
                    " <Price: " + armor.getPrice() +
                    ", Armor: " + armor.getBlock() + ">");
        }
        System.out.println("0 - Exit");

    }

    public void buyArmor(){
        System.out.println("Select an armor: ");
        int selectArmorID = Location.input.nextInt();
        while (selectArmorID < 0 || selectArmorID > Armor.armors().length){
            System.out.println("Invalid value, please enter again:");
            selectArmorID = Location.input.nextInt();
        }

        if (selectArmorID != 0){
            Armor selectedArmor = Armor.getArmorObjByID(selectArmorID);

            if (selectedArmor != null){
                if (selectedArmor.getPrice() > this.getPlayer().getMoney()){
                    System.out.println("Insufficient funds!");
                }else {
                    System.out.println("You purchased the armor: " + selectedArmor.getName() + "!");
                    this.getPlayer().setMoney(this.getPlayer().getMoney() - selectedArmor.getPrice());
                    this.getPlayer().getInventory().setArmor(selectedArmor);
                    System.out.println("Remaining Balance: " + this.getPlayer().getMoney());
                }
            }
        }
    }
}
