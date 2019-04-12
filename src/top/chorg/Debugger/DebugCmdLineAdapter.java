package top.chorg.Debugger;

import top.chorg.ForeGuiAdapter;
import top.chorg.system.Sys;

import java.util.Scanner;

public class DebugCmdLineAdapter {
    public static void start() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to iClass GUI Debugger.");
        System.out.printf(
                "System running under debug command line mode (Ver %s).\n",
                "0.0.1"
        );
        ForeGuiAdapter.init();
        while (true) {
            outputDecoration();
            String cmd = sc.nextLine();
            if (cmd.equals("exit")) break;
            String[] args = cmd.split(" ");
            if (args.length == 0 || args[0].length() == 0) continue;
            if (!ForeGuiAdapter.containsEvent(args[0])) {
                Sys.errF("Debugger", "Event '%s' not found.", args[0]);
                continue;
            }
            int returnVal = ForeGuiAdapter.makeEvent(args);
            Sys.infoF("Debugger", "Event over with return value %d.", returnVal);
        }
    }
    public static void outputDecoration() {
        System.out.print("[Cmd line debugger] >>> ");
    }
}
