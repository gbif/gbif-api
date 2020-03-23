package org.gbif.api.vocabulary;

import org.gbif.common.shaded.com.google.common.base.Strings;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.ser.std.SerializerBase;

/**
 * Enumeration for all the translation languages that we use in Crowdin.
 *
 * <p>It stores the locale, the ISO_639_1 and ISO_639_3 codes, and the name of the language in
 * English.
 *
 * @see <a href="https://support.crowdin.com/api/language-codes/">Crowdin languages</a>
 */
@JsonSerialize(using = TranslationLanguage.LocaleSerializer.class)
@JsonDeserialize(using = TranslationLanguage.LocaleDeserializer.class)
public enum TranslationLanguage {
  ACHOLI("ach-UG", "ach", "ach", "Acholi"),
  AFAR("aa-ER", "aa", "aar", "Afar"),
  AFRIKAANS("af-ZA", "af", "afr", "Afrikaans"),
  AKAN("ak-GH", "ak", "aka", "Akan"),
  AKAN_TWI("tw-TW", "tw", "twi", "Akan, Twi"),
  ALBANIAN("sq-AL", "sq", "sqi", "Albanian"),
  AMHARIC("am-ET", "am", "amh", "Amharic"),
  ARABIC("ar-SA", "ar", "ara", "Arabic"),
  ARABIC_BAHRAIN("ar-BH", "ar", "ara", "Arabic, Bahrain"),
  ARABIC_EGYPT("ar-EG", "ar", "ara", "Arabic, Egypt"),
  ARABIC_SAUDI_ARABIA("ar-SA", "ar", "ara", "Arabic, Saudi Arabia"),
  ARABIC_YEMEN("ar-YE", "ar", "ara", "Arabic, Yemen"),
  ARAGONESE("an-ES", "an", "arg", "Aragonese"),
  ARMENIAN("hy-AM", "hy", "hye", "Armenian"),
  ARPITAN("frp-IT", "frp", "frp", "Arpitan"),
  ASSAMESE("as-IN", "as", "asm", "Assamese"),
  ASTURIAN("ast-ES", "ast", "ast", "Asturian"),
  ATAYAL("tay-TW", "tay", "tay", "Atayal"),
  AVARIC("av-DA", "av", "ava", "Avaric"),
  AVESTAN("ae-IR", "ae", "ave", "Avestan"),
  AYMARA("ay-BO", "ay", "aym", "Aymara"),
  AZERBAIJANI("az-AZ", "az", "aze", "Azerbaijani"),
  BALINESE("ban-ID", "ban", "ban", "Balinese"),
  BALOCHI("bal-BA", "bal", "bal", "Balochi"),
  BAMBARA("bm-ML", "bm", "bam", "Bambara"),
  BASHKIR("ba-RU", "ba", "bak", "Bashkir"),
  BASQUE("eu-ES", "eu", "eus", "Basque"),
  BELARUSIAN("be-BY", "be", "bel", "Belarusian"),
  BENGALI("bn-BD", "bn", "ben", "Bengali"),
  BENGALI_INDIA("bn-IN", "bn", "ben", "Bengali, India"),
  BERBER("ber-DZ", "ber", "ber", "Berber"),
  BIHARI("bh-IN", "bh", "bih", "Bihari"),
  BIRIFOR("bfo-BF", "bfo", "bfo", "Birifor"),
  BISLAMA("bi-VU", "bi", "bis", "Bislama"),
  BOSNIAN("bs-BA", "bs", "bos", "Bosnian"),
  BRETON("br-FR", "br", "bre", "Breton"),
  BULGARIAN("bg-BG", "bg", "bul", "Bulgarian"),
  BURMESE("my-MM", "my", "mya", "Burmese"),
  CATALAN("ca-ES", "ca", "cat", "Catalan"),
  CEBUANO("ceb-PH", "ceb", "ceb", "Cebuano"),
  CHAMORRO("ch-GU", "ch", "cha", "Chamorro"),
  CHECHEN("ce-CE", "ce", "che", "Chechen"),
  CHEROKEE("chr-US", "chr", "chr", "Cherokee"),
  CHEWA("ny-MW", "ny", "nya", "Chewa"),
  CHINESE_SIMPLIFIED("zh-CN", "zh", "zho", "Chinese Simplified"),
  CHINESE_TRADITIONAL("zh-TW", "zh", "zho", "Chinese Traditional"),
  CHINESE_TRADITIONAL_HONG_KONG("zh-HK", "zh", "zho", "Chinese Traditional, Hong Kong"),
  CHINESE_TRADITIONAL_MACAU("zh-MO", "zh", "zho", "Chinese Traditional, Macau"),
  CHINESE_TRADITIONAL_SINGAPORE("zh-SG", "zh", "zho", "Chinese Traditional, Singapore"),
  CHUVASH("cv-CU", "cv", "chv", "Chuvash"),
  CORNISH("kw-GB", "kw", "cor", "Cornish"),
  CORSICAN("co-FR", "co", "cos", "Corsican"),
  CREE("cr-NT", "cr", "cre", "Cree"),
  CROATIAN("hr-HR", "hr", "hrv", "Croatian"),
  CZECH("cs-CZ", "cs", "ces", "Czech"),
  DANISH("da-DK", "da", "dan", "Danish"),
  DARI("fa-AF", "fa", "prs", "Dari"),
  DHIVEHI("dv-MV", "dv", "div", "Dhivehi"),
  DUTCH("nl-NL", "nl", "nld", "Dutch"),
  DUTCH_BELGIUM("nl-BE", "nl", "nld", "Dutch, Belgium"),
  DUTCH_SURINAME("nl-SR", "nl", "nld", "Dutch, Suriname"),
  DZONGKHA("dz-BT", "dz", "dzo", "Dzongkha"),
  ENGLISH("en-US", "en", "eng", "English"),
  ENGLISH_UPSIDE_DOWN("en-UD", "en", "eng", "English (upside down)"),
  ENGLISH_ARABIA("en-AR", "en", "eng", "English, Arabia"),
  ENGLISH_AUSTRALIA("en-AU", "en", "eng", "English, Australia"),
  ENGLISH_BELIZE("en-BZ", "en", "eng", "English, Belize"),
  ENGLISH_CANADA("en-CA", "en", "eng", "English, Canada"),
  ENGLISH_CARIBBEAN("en-CB", "en", "eng", "English, Caribbean"),
  ENGLISH_CHINA("en-CN", "en", "eng", "English, China"),
  ENGLISH_DENMARK("en-DK", "en", "eng", "English, Denmark"),
  ENGLISH_HONG_KONG("en-HK", "en", "eng", "English, Hong Kong"),
  ENGLISH_INDIA("en-IN", "en", "eng", "English, India"),
  ENGLISH_INDONESIA("en-ID", "en", "eng", "English, Indonesia"),
  ENGLISH_IRELAND("en-IE", "en", "eng", "English, Ireland"),
  ENGLISH_JAMAICA("en-JM", "en", "eng", "English, Jamaica"),
  ENGLISH_JAPAN("en-JA", "en", "eng", "English, Japan"),
  ENGLISH_MALAYSIA("en-MY", "en", "eng", "English, Malaysia"),
  ENGLISH_NEW_ZEALAND("en-NZ", "en", "eng", "English, New Zealand"),
  ENGLISH_NORWAY("en-NO", "en", "eng", "English, Norway"),
  ENGLISH_PHILIPPINES("en-PH", "en", "eng", "English, Philippines"),
  ENGLISH_PUERTO_RICO("en-PR", "en", "eng", "English, Puerto Rico"),
  ENGLISH_SINGAPORE("en-SG", "en", "eng", "English, Singapore"),
  ENGLISH_SOUTH_AFRICA("en-ZA", "en", "eng", "English, South Africa"),
  ENGLISH_SWEDEN("en-SE", "en", "eng", "English, Sweden"),
  ENGLISH_UNITED_KINGDOM("en-GB", "en", "eng", "English, United Kingdom"),
  ENGLISH_UNITED_STATES("en-US", "en", "eng", "English, United States"),
  ENGLISH_ZIMBABWE("en-ZW", "en", "eng", "English, Zimbabwe"),
  ESPERANTO("eo-UY", "eo", "epo", "Esperanto"),
  ESTONIAN("et-EE", "et", "est", "Estonian"),
  EWE("ee-GH", "ee", "ewe", "Ewe"),
  FAROESE("fo-FO", "fo", "fao", "Faroese"),
  FIJIAN("fj-FJ", "fj", "fij", "Fijian"),
  FILIPINO("fil-PH", "fil", "fil", "Filipino"),
  FINNISH("fi-FI", "fi", "fin", "Finnish"),
  FLEMISH("vls-BE", "vls", "vls", "Flemish"),
  FRANCONIAN("fra-DE", "fra", "gem", "Franconian"),
  FRENCH("fr-FR", "fr", "fra", "French"),
  FRENCH_BELGIUM("fr-BE", "fr", "fra", "French, Belgium"),
  FRENCH_CANADA("fr-CA", "fr", "fra", "French, Canada"),
  FRENCH_LUXEMBOURG("fr-LU", "fr", "fra", "French, Luxembourg"),
  FRENCH_QUEBEC("fr-QC", "fr", "fra", "French, Quebec"),
  FRENCH_SWITZERLAND("fr-CH", "fr", "fra", "French, Switzerland"),
  FRISIAN("fy-NL", "fy", "fry", "Frisian"),
  FRIULIAN("fur-IT", "fur", "fur", "Friulian"),
  FULA("ff-ZA", "ff", "ful", "Fula"),
  GA("gaa-GH", "gaa", "gaa", "Ga"),
  GALICIAN("gl-ES", "gl", "glg", "Galician"),
  GEORGIAN("ka-GE", "ka", "kat", "Georgian"),
  GERMAN("de-DE", "de", "deu", "German"),
  GERMAN_AUSTRIA("de-AT", "de", "deu", "German, Austria"),
  GERMAN_BELGIUM("de-BE", "de", "deu", "German, Belgium"),
  GERMAN_LIECHTENSTEIN("de-LI", "de", "deu", "German, Liechtenstein"),
  GERMAN_LUXEMBOURG("de-LU", "de", "deu", "German, Luxembourg"),
  GERMAN_SWITZERLAND("de-CH", "de", "deu", "German, Switzerland"),
  GOTHIC("got-DE", "got", "got", "Gothic"),
  GREEK("el-GR", "el", "ell", "Greek"),
  GREEK_CYPRUS("el-CY", "el", "ell", "Greek, Cyprus"),
  GREENLANDIC("kl-GL", "kl", "kal", "Greenlandic"),
  GUARANI("gn-PY", "gn", "grn", "Guarani"),
  GUJARATI("gu-IN", "gu", "guj", "Gujarati"),
  HAITIAN_CREOLE("ht-HT", "ht", "hat", "Haitian Creole"),
  HAUSA("ha-HG", "ha", "hau", "Hausa"),
  HAWAIIAN("haw-US", "haw", "haw", "Hawaiian"),
  HEBREW("he-IL", "he", "heb", "Hebrew"),
  HERERO("hz-NA", "hz", "her", "Herero"),
  HILIGAYNON("hil-PH", "hil", "hil", "Hiligaynon"),
  HINDI("hi-IN", "hi", "hin", "Hindi"),
  HIRI_MOTU("ho-PG", "ho", "hmo", "Hiri Motu"),
  HMONG("hmn-CN", "hmn", "hmn", "Hmong"),
  HUNGARIAN("hu-HU", "hu", "hun", "Hungarian"),
  ICELANDIC("is-IS", "is", "isl", "Icelandic"),
  IDO("io-EN", "io", "ido", "Ido"),
  IGBO("ig-NG", "ig", "ibo", "Igbo"),
  ILOKANO("ilo-PH", "ilo", "ilo", "Ilokano"),
  INDONESIAN("id-ID", "id", "ind", "Indonesian"),
  INUKTITUT("iu-NU", "iu", "iku", "Inuktitut"),
  IRISH("ga-IE", "ga", "gle", "Irish"),
  ITALIAN("it-IT", "it", "ita", "Italian"),
  ITALIAN_SWITZERLAND("it-CH", "it", "ita", "Italian, Switzerland"),
  JAPANESE("ja-JP", "ja", "jpn", "Japanese"),
  JAVANESE("jv-ID", "jv", "jav", "Javanese"),
  QUICHE("quc-GT", "quc", "quc", "K'iche'"),
  KABYLE("kab-KAB", "kab", "kab", "Kabyle"),
  KANNADA("kn-IN", "kn", "kan", "Kannada"),
  KAPAMPANGAN("pam-PH", "pam", "pam", "Kapampangan"),
  KASHMIRI("ks-IN", "ks", "kas", "Kashmiri"),
  KASHMIRI_PAKISTAN("ks-PK", "ks", "kas", "Kashmiri, Pakistan"),
  KASHUBIAN("csb-PL", "csb", "csb", "Kashubian"),
  KAZAKH("kk-KZ", "kk", "kaz", "Kazakh"),
  KHMER("km-KH", "km", "khm", "Khmer"),
  KINYARWANDA("rw-RW", "rw", "kin", "Kinyarwanda"),
  KLINGON("tlh-AA", "tlh", "tlh", "Klingon"),
  KOMI("kv-KO", "kv", "kom", "Komi"),
  KONGO("kg-CG", "kg", "kon", "Kongo"),
  KONKANI("kok-IN", "kok", "kok", "Konkani"),
  KOREAN("ko-KR", "ko", "kor", "Korean"),
  KURDISH("ku-TR", "ku", "kur", "Kurdish"),
  KURMANJI_KURDISH("kmr-TR", "ku", "kmr", "Kurmanji (Kurdish)"),
  KWANYAMA("kj-AO", "kj", "kua", "Kwanyama"),
  KYRGYZ("ky-KG", "ky", "kir", "Kyrgyz"),
  LAO("lo-LA", "lo", "lao", "Lao"),
  LATIN("la-LA", "la", "lat", "Latin"),
  LATVIAN("lv-LV", "lv", "lav", "Latvian"),
  LIGURIAN("lij-IT", "lij", "lij", "Ligurian"),
  LIMBURGISH("li-LI", "li", "lim", "Limburgish"),
  LINGALA("ln-CD", "ln", "lin", "Lingala"),
  LITHUANIAN("lt-LT", "lt", "lit", "Lithuanian"),
  LOJBAN("jbo-EN", "jbo", "jbo", "Lojban"),
  LOLCAT("lol-US", "lol", "lol", "LOLCAT"),
  LOW_GERMAN("nds-DE", "nds", "nds", "Low German"),
  LOWER_SORBIAN("dsb-DE", "dsb", "dsb", "Lower Sorbian"),
  LUGANDA("lg-UG", "lg", "lug", "Luganda"),
  LUHYA("luy-KE", "luy", "luy", "Luhya"),
  LUXEMBOURGISH("lb-LU", "lb", "ltz", "Luxembourgish"),
  MACEDONIAN("mk-MK", "mk", "mkd", "Macedonian"),
  MAITHILI("mai-IN", "mai", "mai", "Maithili"),
  MALAGASY("mg-MG", "mg", "mlg", "Malagasy"),
  MALAY("ms-MY", "ms", "msa", "Malay"),
  MALAY_BRUNEI("ms-BN", "ms", "msa", "Malay, Brunei"),
  MALAYALAM("ml-IN", "ml", "mal", "Malayalam"),
  MALTESE("mt-MT", "mt", "mlt", "Maltese"),
  MANX("gv-IM", "gv", "glv", "Manx"),
  MAORI("mi-NZ", "mi", "mri", "Maori"),
  MAPUDUNGUN("arn-CL", "arn", "arn", "Mapudungun"),
  MARATHI("mr-IN", "mr", "mar", "Marathi"),
  MARSHALLESE("mh-MH", "mh", "mah", "Marshallese"),
  MOHAWK("moh-CA", "moh", "moh", "Mohawk"),
  MONGOLIAN("mn-MN", "mn", "mon", "Mongolian"),
  MONTENEGRIN_CYRILLIC("sr-Cyrl-ME", "sr", "srp", "Montenegrin (Cyrillic)"),
  MONTENEGRIN_LATIN("me-ME", "me", "srp", "Montenegrin (Latin)"),
  MOSSI("mos-MOS", "mos", "mos", "Mossi"),
  NAURU("na-NR", "na", "nau", "Nauru"),
  NDONGA("ng-NA", "ng", "ndo", "Ndonga"),
  NEPALI("ne-NP", "ne", "nep", "Nepali"),
  NEPALI_INDIA("ne-IN", "ne", "nep", "Nepali, India"),
  NIGERIAN_PIDGIN("pcm-NG", "pcm", "pcm", "Nigerian Pidgin"),
  NORTHERN_SAMI("se-NO", "se", "sme", "Northern Sami"),
  NORTHERN_SOTHO("ns-ZA", "nso", "nso", "Northern Sotho"),
  NORWEGIAN("no-NO", "no", "nor", "Norwegian"),
  NORWEGIAN_BOKMAL("nb-NO", "nb", "nob", "Norwegian Bokmal"),
  NORWEGIAN_NYNORSK("nn-NO", "nn", "nno", "Norwegian Nynorsk"),
  OCCITAN("oc-FR", "oc", "oci", "Occitan"),
  ODIA("or-IN", "or", "ori", "Odia"),
  OJIBWE("oj-CA", "oj", "oji", "Ojibwe"),
  OROMO("om-ET", "om", "orm", "Oromo"),
  OSSETIAN("os-SE", "os", "oss", "Ossetian"),
  PALI("pi-IN", "pi", "pli", "Pali"),
  PAPIAMENTO("pap-PAP", "pap", "pap", "Papiamento"),
  PASHTO("ps-AF", "ps", "pus", "Pashto"),
  PERSIAN("fa-IR", "fa", "fas", "Persian"),
  PIRATE_ENGLISH("en-PT", "en", "eng", "Pirate English"),
  POLISH("pl-PL", "pl", "pol", "Polish"),
  PORTUGUESE("pt-PT", "pt", "por", "Portuguese"),
  PORTUGUESE_BRAZILIAN("pt-BR", "pt", "por", "Portuguese, Brazilian"),
  PUNJABI("pa-IN", "pa", "pan", "Punjabi"),
  PUNJABI_PAKISTAN("pa-PK", "pa", "pan", "Punjabi, Pakistan"),
  QUECHUA("qu-PE", "qu", "que", "Quechua"),
  QUENYA("qya-AA", "qya", "qya", "Quenya"),
  ROMANIAN("ro-RO", "ro", "ron", "Romanian"),
  ROMANSH("rm-CH", "rm", "roh", "Romansh"),
  RUNDI("rn-BI", "rn", "run", "Rundi"),
  RUSSIAN("ru-RU", "ru", "rus", "Russian"),
  RUSSIAN_BELARUS("ru-BY", "ru", "rus", "Russian, Belarus"),
  RUSSIAN_MOLDOVA("ru-MD", "ru", "rus", "Russian, Moldova"),
  RUSSIAN_UKRAINE("ru-UA", "ru", "rus", "Russian, Ukraine"),
  RUSYN("ry-UA", "ry", "sla", "Rusyn"),
  SAKHA("sah-SAH", "sah", "sah", "Sakha"),
  SANGO("sg-CF", "sg", "sag", "Sango"),
  SANSKRIT("sa-IN", "sa", "san", "Sanskrit"),
  SANTALI("sat-IN", "sat", "sat", "Santali"),
  SARDINIAN("sc-IT", "sc", "srd", "Sardinian"),
  SCOTS("sco-GB", "sco", "sco", "Scots"),
  SCOTTISH_GAELIC("gd-GB", "gd", "gla", "Scottish Gaelic"),
  SERBIAN_CYRILLIC("sr-SP", "sr", "srp", "Serbian (Cyrillic)"),
  SERBIAN_LATIN("sr-CS", "sr", "srp", "Serbian (Latin)"),
  SERBO_CROATIAN("sh-HR", "sh", "hbs", "Serbo-Croatian"),
  SEYCHELLOIS_CREOLE("crs-SC", "crs", "crs", "Seychellois Creole"),
  SHONA("sn-ZW", "sn", "sna", "Shona"),
  SICHUAN_YI("ii-CN", "ii", "iii", "Sichuan Yi"),
  SINDHI("sd-PK", "sd", "snd", "Sindhi"),
  SINHALA("si-LK", "si", "sin", "Sinhala"),
  SLOVAK("sk-SK", "sk", "slk", "Slovak"),
  SLOVENIAN("sl-SI", "sl", "slv", "Slovenian"),
  SOMALI("so-SO", "so", "som", "Somali"),
  SONGHAY("son-ZA", "son", "son", "Songhay"),
  SORANI_KURDISH("ckb-IR", "ku", "ckb", "Sorani (Kurdish)"),
  SOUTHERN_NDEBELE("nr-ZA", "nr", "nbl", "Southern Ndebele"),
  SOUTHERN_SAMI("sma-NO", "sma", "sma", "Southern Sami"),
  SOUTHERN_SOTHO("st-ZA", "st", "sot", "Southern Sotho"),
  SPANISH("es-ES", "es", "spa", "Spanish"),
  SPANISH_MODERN("es-EM", "es", "spa", "Spanish (Modern)"),
  SPANISH_ARGENTINA("es-AR", "es", "spa", "Spanish, Argentina"),
  SPANISH_BOLIVIA("es-BO", "es", "spa", "Spanish, Bolivia"),
  SPANISH_CHILE("es-CL", "es", "spa", "Spanish, Chile"),
  SPANISH_COLOMBIA("es-CO", "es", "spa", "Spanish, Colombia"),
  SPANISH_COSTA_RICA("es-CR", "es", "spa", "Spanish, Costa Rica"),
  SPANISH_DOMINICAN_REPUBLIC("es-DO", "es", "spa", "Spanish, Dominican Republic"),
  SPANISH_ECUADOR("es-EC", "es", "spa", "Spanish, Ecuador"),
  SPANISH_EL_SALVADOR("es-SV", "es", "spa", "Spanish, El Salvador"),
  SPANISH_GUATEMALA("es-GT", "es", "spa", "Spanish, Guatemala"),
  SPANISH_HONDURAS("es-HN", "es", "spa", "Spanish, Honduras"),
  SPANISH_MEXICO("es-MX", "es", "spa", "Spanish, Mexico"),
  SPANISH_NICARAGUA("es-NI", "es", "spa", "Spanish, Nicaragua"),
  SPANISH_PANAMA("es-PA", "es", "spa", "Spanish, Panama"),
  SPANISH_PARAGUAY("es-PY", "es", "spa", "Spanish, Paraguay"),
  SPANISH_PERU("es-PE", "es", "spa", "Spanish, Peru"),
  SPANISH_PUERTO_RICO("es-PR", "es", "spa", "Spanish, Puerto Rico"),
  SPANISH_UNITED_STATES("es-US", "es", "spa", "Spanish, United States"),
  SPANISH_URUGUAY("es-UY", "es", "spa", "Spanish, Uruguay"),
  SPANISH_VENEZUELA("es-VE", "es", "spa", "Spanish, Venezuela"),
  SUNDANESE("su-ID", "su", "sun", "Sundanese"),
  SWAHILI("sw-KE", "sw", "swa", "Swahili"),
  SWAHILI_KENYA("sw-KE", "sw", "swa", "Swahili, Kenya"),
  SWAHILI_TANZANIA("sw-TZ", "sw", "swa", "Swahili, Tanzania"),
  SWATI("ss-ZA", "ss", "ssw", "Swati"),
  SWEDISH("sv-SE", "sv", "swe", "Swedish"),
  SWEDISH_FINLAND("sv-FI", "sv", "swe", "Swedish, Finland"),
  SYRIAC("syc-SY", "syc", "syc", "Syriac"),
  TAGALOG("tl-PH", "tl", "tgl", "Tagalog"),
  TAHITIAN("ty-PF", "ty", "tah", "Tahitian"),
  TAJIK("tg-TJ", "tg", "tgk", "Tajik"),
  TALOSSAN("tzl-TZL", "tzl", "tzl", "Talossan"),
  TAMIL("ta-IN", "ta", "tam", "Tamil"),
  TATAR("tt-RU", "tt", "tat", "Tatar"),
  TELUGU("te-IN", "te", "tel", "Telugu"),
  TEM_KOTOKOLI("kdh-KDH", "kdh", "kdh", "Tem (Kotokoli)"),
  THAI("th-TH", "th", "tha", "Thai"),
  TIBETAN("bo-BT", "bo", "tib", "Tibetan"),
  TIGRINYA("ti-ER", "ti", "tir", "Tigrinya"),
  TSONGA("ts-ZA", "ts", "tso", "Tsonga"),
  TSWANA("tn-ZA", "tn", "tsn", "Tswana"),
  TURKISH("tr-TR", "tr", "tur", "Turkish"),
  TURKISH_CYPRUS("tr-CY", "tr", "tur", "Turkish, Cyprus"),
  TURKMEN("tk-TM", "tk", "tuk", "Turkmen"),
  UKRAINIAN("uk-UA", "uk", "ukr", "Ukrainian"),
  UPPER_SORBIAN("hsb-DE", "hsb", "hsb", "Upper Sorbian"),
  URDU_INDIA("ur-IN", "ur", "urd", "Urdu (India)"),
  URDU_PAKISTAN("ur-PK", "ur", "urd", "Urdu (Pakistan)"),
  UYGHUR("ug-CN", "ug", "uig", "Uyghur"),
  UZBEK("uz-UZ", "uz", "uzb", "Uzbek"),
  VALENCIAN("val-ES", "val", "val", "Valencian"),
  VENDA("ve-ZA", "ve", "ven", "Venda"),
  VENETIAN("vec-IT", "vec", "vec", "Venetian"),
  VIETNAMESE("vi-VN", "vi", "vie", "Vietnamese"),
  WALLOON("wa-BE", "wa", "wln", "Walloon"),
  WELSH("cy-GB", "cy", "cym", "Welsh"),
  WOLOF("wo-SN", "wo", "wol", "Wolof"),
  XHOSA("xh-ZA", "xh", "xho", "Xhosa"),
  YIDDISH("yi-DE", "yi", "yid", "Yiddish"),
  YORUBA("yo-NG", "yo", "yor", "Yoruba"),
  ZEELANDIC("zea-ZEA", "zea", "zea", "Zeelandic"),
  ZULU("zu-ZA", "zu", "zul", "Zulu"),
  /** Unknown or Invalid language. */
  UNKNOWN("", "", "", "Unknown");

  private final String locale;
  // ISO 639-1 code
  private final String iso2LetterCode;
  // ISO 639-3 code
  private final String iso3LetterCode;
  private final String name;

  TranslationLanguage(String locale, String iso2LetterCode, String iso3LetterCode, String name) {
    this.locale = locale;
    this.iso2LetterCode = iso2LetterCode;
    this.iso3LetterCode = iso3LetterCode;
    this.name = name;
  }

  public String getLocale() {
    return locale;
  }

  public String getIso2LetterCode() {
    return iso2LetterCode;
  }

  public String getIso3LetterCode() {
    return iso3LetterCode;
  }

  public String getName() {
    return name;
  }

  /**
   * Returns the corresponding {@link TranslationLanguage} for the locale specified.
   *
   * @param locale language locLW
   * @return the matching TranslationLanguage or UNKNOWN
   */
  public static TranslationLanguage fromLocale(String locale) {
    if (!Strings.isNullOrEmpty(locale)) {
      String localeCode = locale.trim();
      for (TranslationLanguage language : TranslationLanguage.values()) {
        if (localeCode.equalsIgnoreCase(language.getLocale())) {
          return language;
        }
      }
    }
    return UNKNOWN;
  }

  /** Serializes the value using the locale. */
  public static class LocaleSerializer extends SerializerBase<TranslationLanguage> {

    public LocaleSerializer() {
      super(TranslationLanguage.class);
    }

    @Override
    public void serialize(
        TranslationLanguage value, JsonGenerator jgen, SerializerProvider provider)
        throws IOException {
      jgen.writeString(value.getLocale());
    }
  }

  /** Deserializes the value from the locale. */
  public static class LocaleDeserializer extends JsonDeserializer<TranslationLanguage> {

    @Override
    public TranslationLanguage deserialize(JsonParser jp, DeserializationContext ctxt)
        throws IOException {
      try {
        if (jp != null && jp.getTextLength() > 0) {
          return TranslationLanguage.fromLocale(jp.getText());
        } else {
          return TranslationLanguage.UNKNOWN; // none provided
        }
      } catch (Exception e) {
        throw new IOException(
            "Unable to deserialize language from provided value (hint: are you using the locale?): "
                + jp.getText());
      }
    }
  }
}
