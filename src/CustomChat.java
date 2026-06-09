import mindustry.Vars;
import mindustry.gen.Call;
import mindustry.gen.Player;
import mindustry.mod.Plugin;

public class CustomChat extends Plugin {

    @Override
    public void init() {
        Vars.netServer.admins.addChatFilter((p, msg) -> {
            if (p == null) return null;
            if (!msg.isEmpty() && msg.charAt(0) == '/') return msg;
            Call.sendMessage(p.name + "[#dadada]: " + msg);
            return null;
        });
    }
}
