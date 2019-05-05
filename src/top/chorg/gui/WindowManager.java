package top.chorg.gui;

import com.google.gson.internal.Primitives;

import java.util.HashMap;
import java.util.Objects;

public class WindowManager {

    private static HashMap<Integer, Object> list = new HashMap<>();

    private static int getNewId(int lowerBound, int upperBound) {
        synchronized (WindowManager.class) {
            for (int i = lowerBound; i < upperBound; i++) {
                if (list.containsKey(i)) continue;
                return i;
            }
            return -1;
        }
    }

    public static boolean isIdAvail(int id) {
        return !list.containsKey(id);
    }

    public static boolean add(Object frame, int idLowerBound, int idUpperBound) {
        int id = getNewId(idLowerBound, idUpperBound);
        if (id == -1) return false;
        list.put(id, frame);
        return true;
    }

    public static boolean add(Object frame, int id) {
        if (!isIdAvail(id)) return false;
        list.put(id, frame);
        return true;
    }

    public static boolean remove(int id) {
        if (!list.containsKey(id)) return false;
        list.remove(id);
        return true;
    }

    public static boolean containsKey(int id) {
        return list.containsKey(id);
    }

    public static boolean containsFrame(Object frame) {
        return list.containsValue(frame);
    }

    public static <T> T get(int id, Class<T> classOfT) {
        return Objects.requireNonNull(Primitives.wrap(classOfT).cast(list.get(id)));
    }
}
