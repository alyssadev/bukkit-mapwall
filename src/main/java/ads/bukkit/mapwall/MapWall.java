package ads.bukkit.mapwall;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.MapMeta;
import org.bukkit.map.MapView;
import org.bukkit.map.MapView.Scale;
import org.bukkit.plugin.java.JavaPlugin;

public class MapWall extends JavaPlugin {
	private MapWall mapwall;
	
	public void onEnable() {
		mapwall = this;
	}
	
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player player = null;
		if (sender instanceof Player) {
			player = (Player)sender;
		} else {
			return false;
		}
		// Doesn't actually work
		for (int x=0; x<9; x++) {
			for (int z=0; z<3; z++) {
				int x_coord = x*128;
				int z_coord = z*128;
				int slot = 9+x*z;
				MapView mv = mapwall.getServer().createMap(player.getWorld());
				mv.setCenterX(x_coord);
				mv.setCenterZ(z_coord);
				mv.setScale(Scale.CLOSEST);
				ItemStack is = new ItemStack(Material.FILLED_MAP, 1);
				MapMeta im = (MapMeta)player.getInventory().getItemInMainHand().getItemMeta();
				im.setMapView(mv);
				player.getInventory().setItem(slot, is);
			}
		}
		return true;
	}
}
