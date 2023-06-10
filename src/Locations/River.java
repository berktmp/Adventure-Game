package Locations;

import Locations.BattleLoc;
import Obstacle.Bear;

public class River extends BattleLoc {
    public River(Player player) {
        super(player, "River",new Bear(),"water",2);
    }
}
