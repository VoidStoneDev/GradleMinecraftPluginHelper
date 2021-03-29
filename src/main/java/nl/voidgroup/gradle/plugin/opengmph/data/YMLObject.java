package nl.voidgroup.gradle.plugin.opengmph.data;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class YMLObject {
    private HashMap<String, Object> ObjectContent;

    public YMLObject() {
        ObjectContent = new HashMap<>();
    }

    public String getStr(int indent) {
        StringBuilder out = new StringBuilder();
        for(Map.Entry<String, Object> map : ObjectContent.entrySet()) {

            if(map.getValue() instanceof String[]) {
                out.append(new String(new char[indent * 2]).replace("\0", " ") + map.getKey() + ": \n");
                Iterator iterator = Arrays.stream(((String[]) map.getValue())).iterator();
                while(iterator.hasNext()) {
                    out.append(new String(new char[indent * 2]).replace("\0", " ") + "  - " + iterator.next() + "\n");
                }
                continue;
            }
            if(map.getValue() instanceof YMLObject) {
                out.append(new String(new char[indent * 2]).replace("\0", " ") + map.getKey() + ": \n");
                out.append(((YMLObject) map.getValue()).getStr(indent + 1));
                continue;
            }
            out.append(new String(new char[indent * 2]).replace("\0", " ") + map.getKey() + ": " + map.getValue() + "\n");

        }
        return out.toString();
    }

    public YMLObject add(String str, Object val) {
        ObjectContent.put(str, val);
        return this;
    }

}
