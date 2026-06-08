import mindustry.Vars;
import mindustry.game.Team;
import mindustry.gen.Call;
import mindustry.gen.Player;
import mindustry.mod.Plugin;

public class CustomChat extends Plugin {

    @Override
    public void init() {
        Vars.netServer.admins.addChatFilter((player, msg) -> {
            if (player == null) return null;
            Team t = player.team();
            String msgColor = t == Team.derelict ? "[#dadada]" : "[white]";
            String formatted =
                t.emoji + " " + player.name + "[#dadada]: " + msgColor + msg;
            Call.sendMessage(formatted);
            return null;
        });
    }
}
