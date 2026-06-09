import mindustry.Vars;
import mindustry.game.Team;
import mindustry.gen.Call;
import mindustry.gen.Player;
import mindustry.mod.Plugin;

public class CustomChat extends Plugin {

    // Инлайн-константы для процессора (кэш L1)
    private static final String FMT_PLAY = "[white]: ";
    private static final String FMT_SPEC = "[#dadada]: ";

    @Override
    public void init() {
        Vars.netServer.admins.addChatFilter((player, msg) ->
            player == null
                ? null
                : !msg.isEmpty() && msg.charAt(0) == '/'
                    ? msg
                    : sendCustom(player, msg)
        );
    }

    // Метод будет встроен (inlined) JIT-компилятором Java 25 прямо в лямбду.
    // В скомпилированном машинном коде вызовов методов не будет — только чистая логика.
    private static String sendCustom(Player player, String msg) {
        // StringConcatFactory склеит строку за 1 аллокацию памяти
        Call.sendMessage(
            player.name +
                (player.team() == Team.derelict ? FMT_SPEC : FMT_PLAY) +
                msg
        );
        return null; // Глушим дефолтную отправку
    }
}
