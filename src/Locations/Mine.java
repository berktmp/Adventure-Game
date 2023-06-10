package Locations;

import Locations.BattleLoc;
import Obstacle.Snake;

public class Mine extends BattleLoc {
    public Mine(Player player) {
        super(player,"Mine", new Snake(), "treasury",5);
    }
}
