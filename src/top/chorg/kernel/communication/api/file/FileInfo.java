package top.chorg.kernel.communication.api.file;

import top.chorg.support.DateTime;
import top.chorg.system.Global;

import java.math.BigDecimal;

public class FileInfo {
    public int id;
    public String name;
    public int uploader;
    public DateTime date;
    public int classId, level;
    public BigDecimal size;

    public FileInfo(int id, String name, int uploader, DateTime date, int classId, int level, BigDecimal size) {
        this.id = id;
        this.name = name;
        this.uploader = uploader;
        this.date = date;
        this.classId = classId;
        this.level = level;
        this.size = size;
    }

    public static void main(String[] args) {
        System.out.println(Global.gson.toJson(new FileInfo(
                0, "defaultUserIcon.png", 0, new DateTime(), 0, 0, new BigDecimal(233)
        )));
        //{"id":0,"name":"defaultUserIcon.png","uploader":0,"date":{"date":{"year":2019,"month":5,"day":3},"hour":16,"minute":58,"second":46},"classId":0,"level":0,"size":233}
    }
}
