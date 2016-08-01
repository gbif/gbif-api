/*
 * Copyright 2014 Global Biodiversity Information Facility (GBIF)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gbif.api.vocabulary;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.codehaus.jackson.map.ser.std.SerializerBase;

/**
 * Enumeration for all ISO 639-1 language codes using 2 lower case letters.
 * The enumeration maps to 3 letter codes and Locales.
 *
 * @see <a href="http://en.wikipedia.org/wiki/ISO_639">Wikipedia on ISO-639</a>
 * @see <a href="http://docs.oracle.com/javase/6/docs/api/java/util/Locale.html">Locale javadoc</a>
 */
@JsonSerialize(using = Language.IsoSerializer.class)
@JsonDeserialize(using = Language.LenientDeserializer.class)
public enum Language {

  /**
   * Abkhazian.
   */
  ABKHAZIAN("ab"),

  /**
   * Afar.
   */
  AFAR("aa"),

  /**
   * Afrikaans.
   */
  AFRIKAANS("af"),

  /**
   * Akan.
   */
  AKAN("ak"),

  /**
   * Albanian.
   */
  ALBANIAN("sq"),

  /**
   * Amharic.
   */
  AMHARIC("am"),

  /**
   * Arabic.
   */
  ARABIC("ar"),

  /**
   * Aragonese.
   */
  ARAGONESE("an"),

  /**
   * Armenian.
   */
  ARMENIAN("hy"),

  /**
   * Assamese.
   */
  ASSAMESE("as"),

  /**
   * Avaric.
   */
  AVARIC("av"),

  /**
   * Avestan.
   */
  AVESTAN("ae"),

  /**
   * Aymara.
   */
  AYMARA("ay"),

  /**
   * Azerbaijani.
   */
  AZERBAIJANI("az"),

  /**
   * Bambara.
   */
  BAMBARA("bm"),

  /**
   * Bashkir.
   */
  BASHKIR("ba"),

  /**
   * Basque.
   */
  BASQUE("eu"),

  /**
   * Belarusian.
   */
  BELARUSIAN("be"),

  /**
   * Bengali.
   */
  BENGALI("bn"),

  /**
   * Bihari.
   */
  BIHARI("bh"),

  /**
   * Bislama.
   */
  BISLAMA("bi"),

  /**
   * Bosnian.
   */
  BOSNIAN("bs"),

  /**
   * Breton.
   */
  BRETON("br"),

  /**
   * Bulgarian.
   */
  BULGARIAN("bg"),

  /**
   * Burmese.
   */
  BURMESE("my"),

  /**
   * Catalan.
   */
  CATALAN("ca"),

  /**
   * Chamorro.
   */
  CHAMORRO("ch"),

  /**
   * Chechen.
   */
  CHECHEN("ce"),

  /**
   * Chinese.
   */
  CHINESE("zh"),

  /**
   * Church Slavic.
   */
  CHURCH_SLAVIC("cu"),

  /**
   * Chuvash.
   */
  CHUVASH("cv"),

  /**
   * Cornish.
   */
  CORNISH("kw"),

  /**
   * Corsican.
   */
  CORSICAN("co"),

  /**
   * Cree.
   */
  CREE("cr"),

  /**
   * Croatian.
   */
  CROATIAN("hr"),

  /**
   * Czech.
   */
  CZECH("cs"),

  /**
   * Danish.
   */
  DANISH("da"),

  /**
   * Divehi.
   */
  DIVEHI("dv"),

  /**
   * Dutch.
   */
  DUTCH("nl"),

  /**
   * Dzongkha.
   */
  DZONGKHA("dz"),

  /**
   * English.
   */
  ENGLISH("en"),

  /**
   * Esperanto.
   */
  ESPERANTO("eo"),

  /**
   * Estonian.
   */
  ESTONIAN("et"),

  /**
   * Ewe.
   */
  EWE("ee"),

  /**
   * Faroese.
   */
  FAROESE("fo"),

  /**
   * Fijian.
   */
  FIJIAN("fj"),

  /**
   * Finnish.
   */
  FINNISH("fi"),

  /**
   * French.
   */
  FRENCH("fr"),

  /**
   * Frisian.
   */
  FRISIAN("fy"),

  /**
   * Fulah.
   */
  FULAH("ff"),

  /**
   * Gallegan.
   */
  GALLEGAN("gl"),

  /**
   * Ganda.
   */
  GANDA("lg"),

  /**
   * Georgian.
   */
  GEORGIAN("ka"),

  /**
   * German.
   */
  GERMAN("de"),

  /**
   * Greek.
   */
  GREEK("el"),

  /**
   * Greenlandic.
   */
  GREENLANDIC("kl"),

  /**
   * Guarani.
   */
  GUARANI("gn"),

  /**
   * Gujarati.
   */
  GUJARATI("gu"),

  /**
   * Haitian.
   */
  HAITIAN("ht"),

  /**
   * Hausa.
   */
  HAUSA("ha"),

  /**
   * Hebrew.
   */
  HEBREW("he"),

  /**
   * Herero.
   */
  HERERO("hz"),

  /**
   * Hindi.
   */
  HINDI("hi"),

  /**
   * Hiri Motu.
   */
  HIRI_MOTU("ho"),

  /**
   * Hungarian.
   */
  HUNGARIAN("hu"),

  /**
   * Icelandic.
   */
  ICELANDIC("is"),

  /**
   * Ido.
   */
  IDO("io"),

  /**
   * Igbo.
   */
  IGBO("ig"),

  /**
   * Indonesian.
   */
  INDONESIAN("id"),

  /**
   * Interlingua.
   */
  INTERLINGUA("ia"),

  /**
   * Interlingue.
   */
  INTERLINGUE("ie"),

  /**
   * Inuktitut.
   */
  INUKTITUT("iu"),

  /**
   * Inupiaq.
   */
  INUPIAQ("ik"),

  /**
   * Irish.
   */
  IRISH("ga"),

  /**
   * Italian.
   */
  ITALIAN("it"),

  /**
   * Japanese.
   */
  JAPANESE("ja"),

  /**
   * Javanese.
   */
  JAVANESE("jv"),

  /**
   * Kannada.
   */
  KANNADA("kn"),

  /**
   * Kanuri.
   */
  KANURI("kr"),

  /**
   * Kashmiri.
   */
  KASHMIRI("ks"),

  /**
   * Kazakh.
   */
  KAZAKH("kk"),

  /**
   * Khmer.
   */
  KHMER("km"),

  /**
   * Kikuyu.
   */
  KIKUYU("ki"),

  /**
   * Kinyarwanda.
   */
  KINYARWANDA("rw"),

  /**
   * Kirghiz.
   */
  KIRGHIZ("ky"),

  /**
   * Komi.
   */
  KOMI("kv"),

  /**
   * Kongo.
   */
  KONGO("kg"),

  /**
   * Korean.
   */
  KOREAN("ko"),

  /**
   * Kurdish.
   */
  KURDISH("ku"),

  /**
   * Kwanyama.
   */
  KWANYAMA("kj"),

  /**
   * Lao.
   */
  LAO("lo"),

  /**
   * Latin.
   */
  LATIN("la"),

  /**
   * Latvian.
   */
  LATVIAN("lv"),

  /**
   * Limburgish.
   */
  LIMBURGISH("li"),

  /**
   * Lingala.
   */
  LINGALA("ln"),

  /**
   * Lithuanian.
   */
  LITHUANIAN("lt"),

  /**
   * Luba-Katanga.
   */
  LUBA_KATANGA("lu"),

  /**
   * Luxembourgish.
   */
  LUXEMBOURGISH("lb"),

  /**
   * Macedonian.
   */
  MACEDONIAN("mk"),

  /**
   * Malagasy.
   */
  MALAGASY("mg"),

  /**
   * Malay.
   */
  MALAY("ms"),

  /**
   * Malayalam.
   */
  MALAYALAM("ml"),

  /**
   * Maltese.
   */
  MALTESE("mt"),

  /**
   * Manx.
   */
  MANX("gv"),

  /**
   * Maori.
   */
  MAORI("mi"),

  /**
   * Marathi.
   */
  MARATHI("mr"),

  /**
   * Marshallese.
   */
  MARSHALLESE("mh"),

  /**
   * Moldavian.
   */
  MOLDAVIAN("mo"),

  /**
   * Mongolian.
   */
  MONGOLIAN("mn"),

  /**
   * Nauru.
   */
  NAURU("na"),

  /**
   * Navajo.
   */
  NAVAJO("nv"),

  /**
   * Ndonga.
   */
  NDONGA("ng"),

  /**
   * Nepali.
   */
  NEPALI("ne"),

  /**
   * North Ndebele.
   */
  NORTH_NDEBELE("nd"),

  /**
   * Northern Sami.
   */
  NORTHERN_SAMI("se"),

  /**
   * Norwegian Bokmål.
   */
  NORWEGIAN_BOKMAL("nb"),

  /**
   * Norwegian Nynorsk.
   */
  NORWEGIAN_NYNORSK("nn"),

  /**
   * Norwegian.
   */
  NORWEGIAN("no"),

  /**
   * Nyanja.
   */
  NYANJA("ny"),

  /**
   * Occitan.
   */
  OCCITAN("oc"),

  /**
   * Ojibwa.
   */
  OJIBWA("oj"),

  /**
   * Oriya.
   */
  ORIYA("or"),

  /**
   * Oromo.
   */
  OROMO("om"),

  /**
   * Ossetian.
   */
  OSSETIAN("os"),

  /**
   * Pali.
   */
  PALI("pi"),

  /**
   * Panjabi.
   */
  PANJABI("pa"),

  /**
   * Persian.
   */
  PERSIAN("fa"),

  /**
   * Polish.
   */
  POLISH("pl"),

  /**
   * Portuguese.
   */
  PORTUGUESE("pt"),

  /**
   * Pushto.
   */
  PUSHTO("ps"),

  /**
   * Quechua.
   */
  QUECHUA("qu"),

  /**
   * Raeto-Romance.
   */
  RAETO_ROMANCE("rm"),

  /**
   * Romanian.
   */
  ROMANIAN("ro"),

  /**
   * Rundi.
   */
  RUNDI("rn"),

  /**
   * Russian.
   */
  RUSSIAN("ru"),

  /**
   * Samoan.
   */
  SAMOAN("sm"),

  /**
   * Sango.
   */
  SANGO("sg"),

  /**
   * Sanskrit.
   */
  SANSKRIT("sa"),

  /**
   * Sardinian.
   */
  SARDINIAN("sc"),

  /**
   * Scottish Gaelic.
   */
  SCOTTISH_GAELIC("gd"),

  /**
   * Serbian.
   */
  SERBIAN("sr"),

  /**
   * Shona.
   */
  SHONA("sn"),

  /**
   * Sichuan Yi.
   */
  SICHUAN_YI("ii"),

  /**
   * Sindhi.
   */
  SINDHI("sd"),

  /**
   * Sinhalese.
   */
  SINHALESE("si"),

  /**
   * Slovak.
   */
  SLOVAK("sk"),

  /**
   * Slovenian.
   */
  SLOVENIAN("sl"),

  /**
   * Somali.
   */
  SOMALI("so"),

  /**
   * South Ndebele.
   */
  SOUTH_NDEBELE("nr"),

  /**
   * Southern Sotho.
   */
  SOUTHERN_SOTHO("st"),

  /**
   * Spanish.
   */
  SPANISH("es"),

  /**
   * Sundanese.
   */
  SUNDANESE("su"),

  /**
   * Swahili.
   */
  SWAHILI("sw"),

  /**
   * Swati.
   */
  SWATI("ss"),

  /**
   * Swedish.
   */
  SWEDISH("sv"),

  /**
   * Tagalog.
   */
  TAGALOG("tl"),

  /**
   * Tahitian.
   */
  TAHITIAN("ty"),

  /**
   * Tajik.
   */
  TAJIK("tg"),

  /**
   * Tamil.
   */
  TAMIL("ta"),

  /**
   * Tatar.
   */
  TATAR("tt"),

  /**
   * Telugu.
   */
  TELUGU("te"),

  /**
   * Thai.
   */
  THAI("th"),

  /**
   * Tibetan.
   */
  TIBETAN("bo"),

  /**
   * Tigrinya.
   */
  TIGRINYA("ti"),

  /**
   * Tonga.
   */
  TONGA("to"),

  /**
   * Tsonga.
   */
  TSONGA("ts"),

  /**
   * Tswana.
   */
  TSWANA("tn"),

  /**
   * Turkish.
   */
  TURKISH("tr"),

  /**
   * Turkmen.
   */
  TURKMEN("tk"),

  /**
   * Twi.
   */
  TWI("tw"),

  /**
   * Uighur.
   */
  UIGHUR("ug"),

  /**
   * Ukrainian.
   */
  UKRAINIAN("uk"),

  /**
   * Urdu.
   */
  URDU("ur"),

  /**
   * Uzbek.
   */
  UZBEK("uz"),

  /**
   * Venda.
   */
  VENDA("ve"),

  /**
   * Vietnamese.
   */
  VIETNAMESE("vi"),

  /**
   * Volapük.
   */
  VOLAPÜK("vo"),

  /**
   * Walloon.
   */
  WALLOON("wa"),

  /**
   * Welsh.
   */
  WELSH("cy"),

  /**
   * Wolof.
   */
  WOLOF("wo"),

  /**
   * Xhosa.
   */
  XHOSA("xh"),

  /**
   * Yiddish.
   */
  YIDDISH("yi"),

  /**
   * Yoruba.
   */
  YORUBA("yo"),

  /**
   * Zhuang.
   */
  ZHUANG("za"),

  /**
   * Zulu.
   */
  ZULU("zu"),

  /**
   * Unknown or Invalid language.
   */
  UNKNOWN("");

  public static final List<Language> LANGUAGES;

  private final String code;

  static {
    LANGUAGES = ImmutableList.copyOf(Language.values());
  }

  /**
   * @param code the case insensitive 2 or 3 letter codes
   *
   * @return the matching language or UNKNOWN
   */
  public static Language fromIsoCode(String code) {
    if (!Strings.isNullOrEmpty(code)) {
      String codeLower = code.toLowerCase().trim();
      if (codeLower.length() == 2){
        for (Language language : Language.values()) {
          if (codeLower.equals(language.getIso2LetterCode())) {
            return language;
          }
        }
      } else if (codeLower.length() == 3) {
        for (Language language : Language.values()) {
          if (codeLower.equals(language.getIso3LetterCode())) {
            return language;
          }
        }
      }
    }
    return UNKNOWN;
  }

  Language(String code) {
    this.code = code;
  }

  /**
   * @return the 2 letter iso 639-1 code in lower case.
   */
  public String getIso2LetterCode() {
    return code;
  }

  /**
   * @return the 3 letter iso 639-2 code in lower case.
   */
  public String getIso3LetterCode() {
    return getLocale().getISO3Language();
  }

  public Locale getLocale() {
    return new Locale(code);
  }

  /**
   * @return the language title in the English language.
   */
  public String getTitleEnglish() {
    return getLocale().getDisplayLanguage(Locale.ENGLISH);
  }

  /**
   * @return the language title in the native language.
   */
  public String getTitleNative() {
    Locale loc = getLocale();
    return loc.getDisplayLanguage(loc);
  }

  /**
   * Serializes the value in a 3 letter ISO format.
   */
  public static class IsoSerializer extends SerializerBase<Language> {

    public IsoSerializer() {
      super(Language.class);
    }

    @Override
    public void serialize(Language value, JsonGenerator jgen, SerializerProvider provider) throws IOException,
      JsonGenerationException {
      jgen.writeString(value.getIso3LetterCode());
    }

  }

  /**
   * Deserializes the value from a 3 letter ISO format or the enumeration name itself to maintain as much
   * backwards compatibility as possible with e.g. the registry api.
   */
  public static class LenientDeserializer extends JsonDeserializer<Language> {

    @Override
    public Language deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
      try {
        if (jp != null && jp.getTextLength() > 0) {
          return lenientParse(jp.getText());
        } else {
          return Language.UNKNOWN; // none provided
        }
      } catch (Exception e) {
        throw new IOException("Unable to deserialize language from provided value (hint: not an ISO 2 or 3 character?): "
                              + jp.getText());
      }
    }

    @VisibleForTesting
    static Language lenientParse(String value) {
      Language l = Language.fromIsoCode(value);
      // backwards compatible
      if (UNKNOWN == l) {
        try {
          l = valueOf(value);
        } catch( IllegalArgumentException e) {
          l = UNKNOWN;
        }
      }
      return l;
    }
  }
}
