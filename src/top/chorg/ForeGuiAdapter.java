package top.chorg;

import top.chorg.system.Sys;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.HashMap;

public class ForeGuiAdapter {

    /**
     * This method will be invoked by the AftGuiAdapter.
     */
    public static void init() {
        register("startup", top.chorg.adapters.auth.OpenLoginWindow.class);
        register("gotLoginResult", top.chorg.adapters.auth.GetLoginResult.class);
    }

    private static HashMap<String, Class<?>> records = new HashMap<>();

    /**
     * Run the event.
     * This method will be invoked by the AftGuiAdapter.
     *
     * @param args Arguments.
     * @return event execution result
     */
    public static int makeEvent(String...args) {
        if (!containsEvent(args[0])) {
            return -1;
        }
        Class<?> adapter = records.get(args[0]);
        try {
            String[] vars = new String[args.length - 1];
            System.arraycopy(args, 1, vars, 0, args.length - 1);
            return ((WindowEventsAdapter) adapter.getDeclaredConstructor(String[].class).newInstance((Object) vars))
                    .onInvoke();
        } catch (InstantiationException | IllegalAccessException
                | InvocationTargetException | NoSuchMethodException e) {
            Sys.err("Adapter", "Error while making event.");
        }
        return -2;
    }

    /**
     * Send the cmd to the kernel.
     */
    public static void executeCmd(String...args) {
        // TODO: Execute cmd.
        System.out.println(Arrays.toString(args));
    }

    public static boolean containsEvent(String key) {
        return records.containsKey(key);
    }

    public static void register(String name, Class<?> event) {
        if (records.containsKey(name)) {
            Sys.errF(
                    "Adapter",
                    "Interface '%s' already exists!",
                    name
            );
            Sys.exit(3);
        }
        records.put(name, event);
    }
}
