package top.chorg;

import top.chorg.system.Global;
import top.chorg.system.Sys;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;

public class ForeGuiAdapter {

    /**
     * This method will be invoked by the AftGuiAdapter.
     */
    public static void init() {
        register("startup", top.chorg.adapters.auth.OpenLoginWindow.class, "onInvoke");
        register("gotLoginResult", top.chorg.adapters.auth.GetLoginResult.class, "onInvoke");
    }

    private static HashMap<String, Class<?>> ClassRecords = new HashMap<>();
    private static HashMap<String, Method> MethodRecords = new HashMap<>();

    /**
     * Run the event.
     * This method will be invoked by the AftGuiAdapter.
     *
     * @param args Arguments.
     * @return event execution result
     */
    public static int makeEvent(String...args) {
        if (!containsEvent(args[0])) {
            Sys.warnF("Adapter", "Unhandled event (%s).", args[0]);
            Sys.devInfoF("Adapter", "Unhandled event arguments are: (%s).", Arrays.toString(args));
            return -1;
        }
        try {
            String[] vars = new String[args.length - 1];
            System.arraycopy(args, 1, vars, 0, args.length - 1);
            return (int) MethodRecords.get(args[0]).invoke(
                    ClassRecords.get(args[0]).getDeclaredConstructor(String[].class).newInstance((Object) vars)
            );
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
        return ClassRecords.containsKey(key);
    }

    public static void register(String name, Class<?> event, String methodName) {
        if (ClassRecords.containsKey(name)) {
            Sys.errF(
                    "Adapter",
                    "Interface '%s' already exists!",
                    name
            );
            Sys.exit(3);
        }
        if (!event.getSuperclass().equals(WindowEventsAdapter.class)) {
            Sys.errF(
                    "Adapter",
                    "Register target (%s) is not a WindowEventsAdapter!",
                    event
            );
        }
        try {
            Method method = event.getMethod(methodName);
            MethodRecords.put(name, method);
        } catch (NoSuchMethodException e) {
            Sys.errF(
                "Adapter",
                "Register event (%s) cannot find method (%s)!",
                event, methodName
            );
        }
        ClassRecords.put(name, event);
    }
}
