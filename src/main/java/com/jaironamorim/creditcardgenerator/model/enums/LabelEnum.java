package com.jaironamorim.creditcardgenerator.model.enums;

import lombok.Getter;

import java.util.*;
import java.util.stream.Stream;

@Getter
public enum LabelEnum {
    VISA ("VISA"),
    MASTER ("MASTER"),
    AMEX ("AMEX"),
    DINERS ("DINERS"),
    DISCOVER("DISCOVER"),
    ENROUTE("ENROUTE"),
    JCB_16("JCB16"),
    JCB_15("JCB15"),
    VOYAGER("VOYAGER");

    private final String label;

    private static final Map<LabelEnum, List<String>> prefixMap;

    LabelEnum(String label){
        this.label = label;
    }

    static {

        prefixMap = new EnumMap<>(LabelEnum.class);
        prefixMap.put(VISA, Arrays.asList("4539", "4556", "4916", "4532", "4929", "40240071", "4485", "4716", "4"));
        prefixMap.put(MASTER, Arrays.asList("51", "52", "53", "54", "55" ));
        prefixMap.put(AMEX, Arrays.asList( "34", "37"));
        prefixMap.put(DISCOVER, Collections.singletonList("6011"));
        prefixMap.put(DINERS, Arrays.asList("300", "301", "302", "303", "36", "38"));
        prefixMap.put(ENROUTE, Arrays.asList("2014", "2149"));
        prefixMap.put(JCB_16, Arrays.asList("3088", "3096", "3112", "3158", "3337", "3528"));
        prefixMap.put(JCB_15, Arrays.asList("2100", "1800"));
        prefixMap.put(VOYAGER,  Collections.singletonList("8699"));

    }

    public static LabelEnum getLabel(String label){
        final String labelFilter = label.toUpperCase();
        return Stream.of(LabelEnum.values()).filter(e -> e.label.equals(labelFilter))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Rótulo desconhecido: ["+labelFilter+"]"));
    }

    public static List<String> getPrefixList(LabelEnum label){
        return prefixMap.get(label);
    }

}
