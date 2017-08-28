package cn.kevin.wechat.enums;

/**
 * 图灵回复的枚举类型
 * Created by yongkang.zhang on 2017/8/28.
 */
public enum TulingResponseTypeEnum {

    TEXT("100000", "文本类"), LINK("200000", "链接类"),
    NEWS("302000", "新闻类"), COOKBOOK("308000", "菜谱"),
    CHILD_SONG("313000", "儿歌类"), POEM("314000", "诗词类");

    private String code;
    private String decs;

    TulingResponseTypeEnum(String code, String decs) {
        this.code = code;
        this.decs = decs;
    }

    public String getCode() {
        return code;
    }

    public String getDecs() {
        return decs;
    }
}
