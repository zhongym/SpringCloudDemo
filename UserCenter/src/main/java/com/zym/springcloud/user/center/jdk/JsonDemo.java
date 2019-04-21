package com.zym.springcloud.user.center.jdk;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class JsonDemo {
    public static void main(String[] args) {
        String json = "[\"com.laimi.store.common.User\",{\"storeId\":150105,\"operId\":10214289," +
                "\"storeTitle\":\"13360014323\",\"name\":\"13360014323\",\"mobile\":\"13360014323\",\"loginName\":\"1539592763908\",\"isMobileBind\":true,\"regionId\":3114,\"status\":\"enable\",\"initStatus\":1,\"isInfoComplete\":true,\"isChainStore\":false,\"areaAgentCodes\":[\"java.util.ArrayList\",[\"zskx_fs\"]],\"clientVersion\":[\"com.laimi.store.common.ext.ClientVersion\",{\"appVersion\":\"ios.store.client:2.43.0\",\"kind\":\"ios.store.client\",\"versionNum\":\"2.43.0\",\"android\":false,\"ios\":true}],\"latitude\":23.13971603,\"longitude\":113.36504501,\"province\":\"广东\",\"city\":\"佛山\",\"county\":\"禅城\",\"district\":\"张槎\",\"creationDate\":[\"java.util.Date\",1529543643000],\"deviceId\":\"648F7072-4B7E-4C22-B2E2-06409066F675\",\"mobileBind\":true,\"infoComplete\":true,\"chainStore\":false}]";

        ObjectMapper om = new ObjectMapper();
        List map = om.convertValue(json, List.class);
        System.out.println(map);
    }
}
