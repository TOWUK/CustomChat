import arc.util.CommandHandler;
import mindustry.Vars;
import mindustry.gen.Call;
import mindustry.gen.Groups;
import mindustry.gen.Player;
import mindustry.mod.Plugin;
public class CustomChat extends Plugin {
    private String fmt(Player p, String prefix, String msg) { return prefix + p.name + "[#dadada]: " + msg; }
    @Override
    public void registerClientCommands(CommandHandler handler) {
        handler.register("t", "<msg...>", "Team chat", (String[] a, Player p) ->
            Groups.player.each(r -> r.team() == p.team(), r -> r.sendMessage(fmt(p, "[#dadada][T] ", a[0])))
        );
        handler.register("a", "<msg...>", "Admin chat", (String[] a, Player p) ->
            Groups.player.each(Player::admin, r -> r.sendMessage(fmt(p, "[#dadada][A] ", a[0])))
        );
    }
    @Override
    public void init() {
        Vars.netServer.admins.addChatFilter((p, m) -> {
            if (p == null || m.startsWith("/")) return m;
            Call.sendMessage(fmt(p, "", m));
            return null;
        });
    }
}
