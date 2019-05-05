package top.chorg.gui;

import top.chorg.Debugger.DebugCmdLineAdapter;
import top.chorg.system.Global;
import top.chorg.system.Sys;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;

public class FullGuiAdapter implements GuiAdapter {

    private HashMap<String, Class<?>> ClassRecords = new HashMap<>();
    private HashMap<String, Method> MethodRecords = new HashMap<>();

    /**
     * Run the event.
     * This method will be invoked by the AftGuiAdapter.
     *
     * @param args Arguments.
     * @return event execution result
     */
    public int makeEvent(String...args) {
        if (Sys.isDevEnv()) Sys.devInfoF("GUI EVENT", Arrays.toString(args));
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
            Sys.errF("Adapter", "Error while making event (%s).", e.getMessage());
            e.printStackTrace();
        }
        return -2;
    }

    /**
     * Send the cmd to the kernel.
     */
    public void executeCmd(String...args) {
        if (Sys.isGuiDevEnv()) {
            System.out.println(Arrays.toString(args));
            DebugCmdLineAdapter.outputDecoration();
        } else {
            if (Sys.isDevEnv()) Sys.devInfoF("GUI CMD", Arrays.toString(args));
            Global.cmdManPrivate.execute(args);
        }
    }

    public boolean containsEvent(String key) {
        return ClassRecords.containsKey(key);
    }

    public void register(String name, Class<?> event, String methodName) {
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
