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
    private static final List<String> VISA_PREFIX_LIST;
    private static final List<String> MASTERCARD_PREFIX_LIST;
    private static final List<String> AMEX_PREFIX_LIST;
    private static final List<String> DISCOVER_PREFIX_LIST;
    private static final List<String> DINERS_PREFIX_LIST;
    private static final List<String> ENROUTE_PREFIX_LIST;
    private static final List<String> JCB_16_PREFIX_LIST;
    private static final List<String> JCB_15_PREFIX_LIST;
    private static final List<String> VOYAGER_PREFIX_LIST;

    LabelEnum(String label){
        this.label = label;
    }

    static {
        VISA_PREFIX_LIST = Arrays.asList("4539", "4556", "4916", "4532", "4929", "40240071", "4485", "4716", "4");
        MASTERCARD_PREFIX_LIST = Arrays.asList("51", "52", "53", "54", "55" );
        AMEX_PREFIX_LIST = Arrays.asList( "34", "37");
        DISCOVER_PREFIX_LIST = Collections.singletonList("6011");
        DINERS_PREFIX_LIST = Arrays.asList("300", "301", "302", "303", "36", "38");
        ENROUTE_PREFIX_LIST = Arrays.asList("2014", "2149");
        JCB_16_PREFIX_LIST = Arrays.asList("3088", "3096", "3112", "3158", "3337", "3528");
        JCB_15_PREFIX_LIST = Arrays.asList("2100", "1800");
        VOYAGER_PREFIX_LIST = Collections.singletonList("8699");
    }

    private static Map<LabelEnum, List<String>> getMapLabel(){
        EnumMap<LabelEnum, List<String>> labelMap = new EnumMap<>(LabelEnum.class);
        labelMap.put(VISA, VISA_PREFIX_LIST);
        labelMap.put(MASTER, MASTERCARD_PREFIX_LIST);
        labelMap.put(AMEX, AMEX_PREFIX_LIST);
        labelMap.put(DINERS, DINERS_PREFIX_LIST);
        labelMap.put(DISCOVER, DISCOVER_PREFIX_LIST);
        labelMap.put(ENROUTE, ENROUTE_PREFIX_LIST);
        labelMap.put(JCB_16, JCB_16_PREFIX_LIST);
        labelMap.put(JCB_15, JCB_15_PREFIX_LIST);
        labelMap.put(VOYAGER, VOYAGER_PREFIX_LIST);
        return labelMap;
    }


    public static LabelEnum getLabel(String label){
        final String labelFilter = label.toUpperCase();
        return Stream.of(LabelEnum.values()).filter(e -> e.label.equals(labelFilter))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("Rótulo desconhecido: ["+labelFilter+"]"));
    }

    public static List<String> getPrefixList(LabelEnum label){
        return getMapLabel().get(label);
    }

}
