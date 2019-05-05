package top.chorg.gui;

import top.chorg.system.Global;

/**
 * Receive orders from master adapter and control the gui.
 */
public abstract class WindowEventsAdapter {

    public WindowEventsAdapter(String...args) {
        this.args = args;
    }

    private String[] args;        // Arguments passed through assignArgs(Serializable).
    private int argIndex = 0;

    protected final int argAmount() {
        return args.length;
    }

    protected final boolean hasNextArg() {
        return argIndex < args.length;
    }

    protected final String nextArg() throws IndexOutOfBoundsException {
        if (argIndex >= args.length) throw new IndexOutOfBoundsException();
        return args[argIndex++];
    }

    protected final <T> T nextArg(Class<T> classOfT) {
        if (argIndex >= args.length) return null;
        return Global.gson.fromJson(args[argIndex++], classOfT);
    }

    protected final String[] remainArgs() {
        if (argIndex >= args.length) throw new IndexOutOfBoundsException();
        String[] temp = new String[args.length - argIndex];
        System.arraycopy(args, argIndex, temp, 0, args.length - argIndex);
        return temp;
    }

}
