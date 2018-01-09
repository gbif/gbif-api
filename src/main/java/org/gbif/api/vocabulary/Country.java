/*
 * Copyright 2014 Global Biodiversity Information Facility (GBIF)
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.gbif.api.vocabulary;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Nullable;

import com.google.common.base.Function;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
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
 * Enumeration for all current ISO 3166-1 ALPHA2 country codes using 2 letters, with the exception of TW which
 * is overridden by GBIF.
 * Older country codes will be supported soon, @see #isDeprecated().
 * All user assigned codes (e.g. XX and QS) are mapped to the single enum USER_DEFINED.
 * The enumeration maps to ALPHA3 3-letter codes.
 *
 * @see <a href="https://www.iso.org/obp/ui/#home">ISO Online Browsing Platform</a>
 * @see <a href="http://data.okfn.org/data/core/country-codes">ISO 3166 in the Open Knowledge International database</a>
 * @see <a href="http://en.wikipedia.org/wiki/ISO_3166">ISO 3166 on Wikipedia</a>
 * @see <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">ISO 3166-1 alpha2 on Wikipedia</a>
 * @see <a href="http://en.wikipedia.org/wiki/ISO_3166-3">ISO_3166-3 on Wikipedia</a>
 *
 * TODO: deal with outdated codes from ISO_3166-3
 */

@JsonSerialize(using = Country.IsoSerializer.class)
@JsonDeserialize(using = Country.IsoDeserializer.class)
public enum Country {

  /**
   * Afghanistan.
   */
  AFGHANISTAN("AF", "AFG", 4, "Afghanistan", GbifRegion.ASIA),

  /**
   * Åland Islands.
   */
  ALAND_ISLANDS("AX", "ALA", 248, "Åland Islands", GbifRegion.EUROPE),

  /**
   * Albania.
   */
  ALBANIA("AL", "ALB", 8, "Albania", GbifRegion.EUROPE),

  /**
   * Algeria.
   */
  ALGERIA("DZ", "DZA", 12, "Algeria", GbifRegion.AFRICA),

  /**
   * American Samoa.
   */
  AMERICAN_SAMOA("AS", "ASM", 16, "American Samoa", GbifRegion.OCEANIA),

  /**
   * Andorra.
   */
  ANDORRA("AD", "AND", 20, "Andorra", GbifRegion.EUROPE),

  /**
   * Angola.
   */
  ANGOLA("AO", "AGO", 24, "Angola", GbifRegion.AFRICA),

  /**
   * Anguilla.
   */
  ANGUILLA("AI", "AIA", 660, "Anguilla", GbifRegion.LATIN_AMERICA),

  /**
   * Antarctica.
   */
  ANTARCTICA("AQ", "ATA", 10, "Antarctica", GbifRegion.ANTARCTICA),

  /**
   * Antigua and Barbuda.
   */
  ANTIGUA_BARBUDA("AG", "ATG", 28, "Antigua and Barbuda", GbifRegion.LATIN_AMERICA),

  /**
   * Argentina.
   */
  ARGENTINA("AR", "ARG", 32, "Argentina", GbifRegion.LATIN_AMERICA),

  /**
   * Armenia.
   */
  ARMENIA("AM", "ARM", 51, "Armenia", GbifRegion.EUROPE),

  /**
   * Aruba.
   */
  ARUBA("AW", "ABW", 533, "Aruba", GbifRegion.LATIN_AMERICA),

  /**
   * Australia.
   */
  AUSTRALIA("AU", "AUS", 36, "Australia", GbifRegion.OCEANIA),

  /**
   * Austria.
   */
  AUSTRIA("AT", "AUT", 40, "Austria", GbifRegion.EUROPE),

  /**
   * Azerbaijan.
   */
  AZERBAIJAN("AZ", "AZE", 31, "Azerbaijan", GbifRegion.EUROPE),

  /**
   * Bahamas.
   */
  BAHAMAS("BS", "BHS", 44, "Bahamas", GbifRegion.LATIN_AMERICA),

  /**
   * Bahrain.
   */
  BAHRAIN("BH", "BHR", 48, "Bahrain", GbifRegion.ASIA),

  /**
   * Bangladesh.
   */
  BANGLADESH("BD", "BGD", 50, "Bangladesh", GbifRegion.ASIA),

  /**
   * Barbados.
   */
  BARBADOS("BB", "BRB", 52, "Barbados", GbifRegion.LATIN_AMERICA),

  /**
   * Belarus.
   */
  BELARUS("BY", "BLR", 112, "Belarus", GbifRegion.EUROPE),

  /**
   * Belgium.
   */
  BELGIUM("BE", "BEL", 56, "Belgium", GbifRegion.EUROPE),

  /**
   * Belize.
   */
  BELIZE("BZ", "BLZ", 84, "Belize", GbifRegion.LATIN_AMERICA),

  /**
   * Benin.
   */
  BENIN("BJ", "BEN", 204, "Benin", GbifRegion.AFRICA),

  /**
   * Bermuda.
   */
  BERMUDA("BM", "BMU", 60, "Bermuda", GbifRegion.LATIN_AMERICA),

  /**
   * Bhutan.
   */
  BHUTAN("BT", "BTN", 64, "Bhutan", GbifRegion.ASIA),

  /**
   * Bolivia, Plurinational State of.
   */
  BOLIVIA("BO", "BOL", 68, "Bolivia, Plurinational State of", GbifRegion.LATIN_AMERICA),

  /**
   * Bonaire, Sint Eustatius and Saba.
   */
  BONAIRE_SINT_EUSTATIUS_SABA("BQ", "BES", 535, "Bonaire, Sint Eustatius and Saba", GbifRegion.LATIN_AMERICA),

  /**
   * Bosnia and Herzegovina.
   */
  BOSNIA_HERZEGOVINA("BA", "BIH", 70, "Bosnia and Herzegovina", GbifRegion.EUROPE),

  /**
   * Botswana.
   */
  BOTSWANA("BW", "BWA", 72, "Botswana", GbifRegion.AFRICA),

  /**
   * Bouvet Island.
   */
  BOUVET_ISLAND("BV", "BVT", 74, "Bouvet Island", GbifRegion.ANTARCTICA),

  /**
   * Brazil.
   */
  BRAZIL("BR", "BRA", 76, "Brazil", GbifRegion.LATIN_AMERICA),

  /**
   * British Indian Ocean Territory.
   */
  BRITISH_INDIAN_OCEAN_TERRITORY("IO", "IOT", 86, "British Indian Ocean Territory", GbifRegion.ASIA),

  /**
   * Brunei Darussalam.
   */
  BRUNEI_DARUSSALAM("BN", "BRN", 96, "Brunei Darussalam", GbifRegion.ASIA),

  /**
   * Bulgaria.
   */
  BULGARIA("BG", "BGR", 100, "Bulgaria", GbifRegion.EUROPE),

  /**
   * Burkina Faso.
   */
  BURKINA_FASO("BF", "BFA", 854, "Burkina Faso", GbifRegion.AFRICA),

  /**
   * Burundi.
   */
  BURUNDI("BI", "BDI", 108, "Burundi", GbifRegion.AFRICA),

  /**
   * Cambodia.
   */
  CAMBODIA("KH", "KHM", 116, "Cambodia", GbifRegion.ASIA),

  /**
   * Cameroon.
   */
  CAMEROON("CM", "CMR", 120, "Cameroon", GbifRegion.AFRICA),

  /**
   * Canada.
   */
  CANADA("CA", "CAN", 124, "Canada", GbifRegion.NORTH_AMERICA),

  /**
   * Cabo Verde.
   */
  CAPE_VERDE("CV", "CPV", 132, "Cabo Verde", GbifRegion.AFRICA),

  /**
   * Cayman Islands.
   */
  CAYMAN_ISLANDS("KY", "CYM", 136, "Cayman Islands", GbifRegion.LATIN_AMERICA),

  /**
   * Central African Republic.
   */
  CENTRAL_AFRICAN_REPUBLIC("CF", "CAF", 140, "Central African Republic", GbifRegion.AFRICA),

  /**
   * Chad.
   */
  CHAD("TD", "TCD", 148, "Chad", GbifRegion.AFRICA),

  /**
   * Chile.
   */
  CHILE("CL", "CHL", 152, "Chile", GbifRegion.LATIN_AMERICA),

  /**
   * China.
   */
  CHINA("CN", "CHN", 156, "China", GbifRegion.ASIA),

  /**
   * Christmas Island.
   */
  CHRISTMAS_ISLAND("CX", "CXR", 162, "Christmas Island", GbifRegion.ASIA),

  /**
   * COCOS (Keeling) Islands.
   */
  COCOS_ISLANDS("CC", "CCK", 166, "Cocos (Keeling) Islands", GbifRegion.AFRICA),

  /**
   * Colombia.
   */
  COLOMBIA("CO", "COL", 170, "Colombia", GbifRegion.LATIN_AMERICA),

  /**
   * Comoros.
   */
  COMOROS("KM", "COM", 174, "Comoros", GbifRegion.AFRICA),

  /**
   * Congo, the Democratic Republic of the.
   */
  CONGO_DEMOCRATIC_REPUBLIC("CD", "COD", 180, "Congo, Democratic Republic of the", GbifRegion.AFRICA),

  /**
   * Congo.
   */
  CONGO("CG", "COG", 178, "Congo, Republic of the", GbifRegion.AFRICA),

  /**
   * Cook Islands.
   */
  COOK_ISLANDS("CK", "COK", 184, "Cook Islands", GbifRegion.OCEANIA),

  /**
   * Costa Rica.
   */
  COSTA_RICA("CR", "CRI", 188, "Costa Rica", GbifRegion.LATIN_AMERICA),

  /**
   * Côte d'Ivoire.
   */
  CÔTE_DIVOIRE("CI", "CIV", 384, "Côte d'Ivoire", GbifRegion.AFRICA),

  /**
   * Croatia.
   */
  CROATIA("HR", "HRV", 191, "Croatia", GbifRegion.EUROPE),

  /**
   * Cuba.
   */
  CUBA("CU", "CUB", 192, "Cuba", GbifRegion.LATIN_AMERICA),

  /**
   * Curaçao.
   */
  CURAÇAO("CW", "CUW", 531, "Curaçao", GbifRegion.LATIN_AMERICA),

  /**
   * Cyprus.
   */
  CYPRUS("CY", "CYP", 196, "Cyprus", GbifRegion.EUROPE),

  /**
   * Czechia.
   */
  CZECH_REPUBLIC("CZ", "CZE", 203, "Czechia", GbifRegion.EUROPE),

  /**
   * Denmark.
   */
  DENMARK("DK", "DNK", 208, "Denmark", GbifRegion.EUROPE),

  /**
   * Djibouti.
   */
  DJIBOUTI("DJ", "DJI", 262, "Djibouti", GbifRegion.AFRICA),

  /**
   * Dominica.
   */
  DOMINICA("DM", "DMA", 212, "Dominica", GbifRegion.LATIN_AMERICA),

  /**
   * Dominican Republic.
   */
  DOMINICAN_REPUBLIC("DO", "DOM", 214, "Dominican Republic", GbifRegion.LATIN_AMERICA),

  /**
   * Ecuador.
   */
  ECUADOR("EC", "ECU", 218, "Ecuador", GbifRegion.LATIN_AMERICA),

  /**
   * Egypt.
   */
  EGYPT("EG", "EGY", 818, "Egypt", GbifRegion.AFRICA),

  /**
   * El Salvador.
   */
  EL_SALVADOR("SV", "SLV", 222, "El Salvador", GbifRegion.LATIN_AMERICA),

  /**
   * Equatorial Guinea.
   */
  EQUATORIAL_GUINEA("GQ", "GNQ", 226, "Equatorial Guinea", GbifRegion.AFRICA),

  /**
   * Eritrea.
   */
  ERITREA("ER", "ERI", 232, "Eritrea", GbifRegion.AFRICA),

  /**
   * Estonia.
   */
  ESTONIA("EE", "EST", 233, "Estonia", GbifRegion.EUROPE),

  /**
   * Ethiopia.
   */
  ETHIOPIA("ET", "ETH", 231, "Ethiopia", GbifRegion.AFRICA),

  /**
   * Falkland Islands (Malvinas).
   */
  FALKLAND_ISLANDS("FK", "FLK", 238, "Falkland Islands (Malvinas)", GbifRegion.LATIN_AMERICA),

  /**
   * Faroe Islands.
   */
  FAROE_ISLANDS("FO", "FRO", 234, "Faroe Islands", GbifRegion.EUROPE),

  /**
   * Fiji.
   */
  FIJI("FJ", "FJI", 242, "Fiji", GbifRegion.OCEANIA),

  /**
   * Finland.
   */
  FINLAND("FI", "FIN", 246, "Finland", GbifRegion.EUROPE),

  /**
   * France.
   */
  FRANCE("FR", "FRA", 250, "France", GbifRegion.EUROPE),

  /**
   * French Guiana.
   */
  FRENCH_GUIANA("GF", "GUF", 254, "French Guiana", GbifRegion.LATIN_AMERICA),

  /**
   * French Polynesia.
   */
  FRENCH_POLYNESIA("PF", "PYF", 258, "French Polynesia", GbifRegion.OCEANIA),

  /**
   * French Southern Territories.
   */
  FRENCH_SOUTHERN_TERRITORIES("TF", "ATF", 260, "French Southern Territories", GbifRegion.ANTARCTICA),

  /**
   * Gabon.
   */
  GABON("GA", "GAB", 266, "Gabon", GbifRegion.AFRICA),

  /**
   * Gambia.
   */
  GAMBIA("GM", "GMB", 270, "Gambia", GbifRegion.AFRICA),

  /**
   * Georgia.
   */
  GEORGIA("GE", "GEO", 268, "Georgia", GbifRegion.EUROPE),

  /**
   * Germany.
   */
  GERMANY("DE", "DEU", 276, "Germany", GbifRegion.EUROPE),

  /**
   * Ghana.
   */
  GHANA("GH", "GHA", 288, "Ghana", GbifRegion.AFRICA),

  /**
   * Gibraltar.
   */
  GIBRALTAR("GI", "GIB", 292, "Gibraltar", GbifRegion.EUROPE),

  /**
   * Greece.
   */
  GREECE("GR", "GRC", 300, "Greece", GbifRegion.EUROPE),

  /**
   * Greenland.
   */
  GREENLAND("GL", "GRL", 304, "Greenland", GbifRegion.EUROPE),

  /**
   * Grenada.
   */
  GRENADA("GD", "GRD", 308, "Grenada", GbifRegion.LATIN_AMERICA),

  /**
   * Guadeloupe.
   */
  GUADELOUPE("GP", "GLP", 312, "Guadeloupe", GbifRegion.LATIN_AMERICA),

  /**
   * Guam.
   */
  GUAM("GU", "GUM", 316, "Guam", GbifRegion.OCEANIA),

  /**
   * Guatemala.
   */
  GUATEMALA("GT", "GTM", 320, "Guatemala", GbifRegion.LATIN_AMERICA),

  /**
   * Guernsey.
   */
  GUERNSEY("GG", "GGY", 831, "Guernsey", GbifRegion.EUROPE),

  /**
   * Guinea.
   */
  GUINEA("GN", "GIN", 324, "Guinea", GbifRegion.AFRICA),

  /**
   * Guinea-Bissau.
   */
  GUINEA_BISSAU("GW", "GNB", 624, "Guinea-Bissau", GbifRegion.AFRICA),

  /**
   * Guyana.
   */
  GUYANA("GY", "GUY", 328, "Guyana", GbifRegion.LATIN_AMERICA),

  /**
   * Haiti.
   */
  HAITI("HT", "HTI", 332, "Haiti", GbifRegion.LATIN_AMERICA),

  /**
   * Heard Island and McDonald Islands.
   */
  HEARD_MCDONALD_ISLANDS("HM", "HMD", 334, "Heard Island and McDonald Islands", GbifRegion.ANTARCTICA),

  /**
   * Holy See (Vatican City State).
   */
  VATICAN("VA", "VAT", 336, "Holy See (Vatican City State)", GbifRegion.EUROPE),

  /**
   * Honduras.
   */
  HONDURAS("HN", "HND", 340, "Honduras", GbifRegion.LATIN_AMERICA),

  /**
   * Hong Kong.
   */
  HONG_KONG("HK", "HKG", 344, "Hong Kong", GbifRegion.ASIA),

  /**
   * Hungary.
   */
  HUNGARY("HU", "HUN", 348, "Hungary", GbifRegion.EUROPE),

  /**
   * Iceland.
   */
  ICELAND("IS", "ISL", 352, "Iceland", GbifRegion.EUROPE),

  /**
   * India.
   */
  INDIA("IN", "IND", 356, "India", GbifRegion.ASIA),

  /**
   * Indonesia.
   */
  INDONESIA("ID", "IDN", 360, "Indonesia", GbifRegion.ASIA),

  /**
   * Iran, Islamic Republic of.
   */
  IRAN("IR", "IRN", 364, "Iran, Islamic Republic of", GbifRegion.ASIA),

  /**
   * Iraq.
   */
  IRAQ("IQ", "IRQ", 368, "Iraq", GbifRegion.ASIA),

  /**
   * Ireland.
   */
  IRELAND("IE", "IRL", 372, "Ireland", GbifRegion.EUROPE),

  /**
   * Isle of Man.
   */
  ISLE_OF_MAN("IM", "IMN", 833, "Isle of Man", GbifRegion.EUROPE),

  /**
   * Israel.
   */
  ISRAEL("IL", "ISR", 376, "Israel", GbifRegion.EUROPE),

  /**
   * Italy.
   */
  ITALY("IT", "ITA", 380, "Italy", GbifRegion.EUROPE),

  /**
   * Jamaica.
   */
  JAMAICA("JM", "JAM", 388, "Jamaica", GbifRegion.LATIN_AMERICA),

  /**
   * Japan.
   */
  JAPAN("JP", "JPN", 392, "Japan", GbifRegion.ASIA),

  /**
   * Jersey.
   */
  JERSEY("JE", "JEY", 832, "Jersey", GbifRegion.EUROPE),

  /**
   * Jordan.
   */
  JORDAN("JO", "JOR", 400, "Jordan", GbifRegion.ASIA),

  /**
   * Kazakhstan.
   */
  KAZAKHSTAN("KZ", "KAZ", 398, "Kazakhstan", GbifRegion.EUROPE),

  /**
   * Kenya.
   */
  KENYA("KE", "KEN", 404, "Kenya", GbifRegion.AFRICA),

  /**
   * Kiribati.
   */
  KIRIBATI("KI", "KIR", 296, "Kiribati", GbifRegion.OCEANIA),

  /**
   * Korea, Democratic People's Republic of.
   */
  KOREA_NORTH("KP", "PRK", 408, "Korea, Democratic People's Republic of", GbifRegion.ASIA),

  /**
   * Korea, Republic of.
   */
  KOREA_SOUTH("KR", "KOR", 410, "Korea, Republic of", GbifRegion.ASIA),

  /**
   * Kuwait.
   */
  KUWAIT("KW", "KWT", 414, "Kuwait", GbifRegion.ASIA),

  /**
   * Kyrgyzstan.
   */
  KYRGYZSTAN("KG", "KGZ", 417, "Kyrgyzstan", GbifRegion.EUROPE),

  /**
   * Lao People's Democratic Republic.
   */
  LAO("LA", "LAO", 418, "Lao People's Democratic Republic", GbifRegion.ASIA),

  /**
   * Latvia.
   */
  LATVIA("LV", "LVA", 428, "Latvia", GbifRegion.EUROPE),

  /**
   * Lebanon.
   */
  LEBANON("LB", "LBN", 422, "Lebanon", GbifRegion.ASIA),

  /**
   * Lesotho.
   */
  LESOTHO("LS", "LSO", 426, "Lesotho", GbifRegion.AFRICA),

  /**
   * Liberia.
   */
  LIBERIA("LR", "LBR", 430, "Liberia", GbifRegion.AFRICA),

  /**
   * Libya.
   */
  LIBYA("LY", "LBY", 434, "Libya", GbifRegion.AFRICA),

  /**
   * Liechtenstein.
   */
  LIECHTENSTEIN("LI", "LIE", 438, "Liechtenstein", GbifRegion.EUROPE),

  /**
   * Lithuania.
   */
  LITHUANIA("LT", "LTU", 440, "Lithuania", GbifRegion.EUROPE),

  /**
   * Luxembourg.
   */
  LUXEMBOURG("LU", "LUX", 442, "Luxembourg", GbifRegion.EUROPE),

  /**
   * Macao.
   */
  MACAO("MO", "MAC", 446, "Macao", GbifRegion.ASIA),

  /**
   * Macedonia, the former Yugoslav Republic of.
   */
  MACEDONIA("MK", "MKD", 807, "Macedonia, the former Yugoslav Republic of", GbifRegion.EUROPE),

  /**
   * Madagascar.
   */
  MADAGASCAR("MG", "MDG", 450, "Madagascar", GbifRegion.AFRICA),

  /**
   * Malawi.
   */
  MALAWI("MW", "MWI", 454, "Malawi", GbifRegion.AFRICA),

  /**
   * Malaysia.
   */
  MALAYSIA("MY", "MYS", 458, "Malaysia", GbifRegion.ASIA),

  /**
   * Maldives.
   */
  MALDIVES("MV", "MDV", 462, "Maldives", GbifRegion.ASIA),

  /**
   * Mali.
   */
  MALI("ML", "MLI", 466, "Mali", GbifRegion.AFRICA),

  /**
   * Malta.
   */
  MALTA("MT", "MLT", 470, "Malta", GbifRegion.EUROPE),

  /**
   * Marshall Islands.
   */
  MARSHALL_ISLANDS("MH", "MHL", 584, "Marshall Islands", GbifRegion.OCEANIA),

  /**
   * Martinique.
   */
  MARTINIQUE("MQ", "MTQ", 474, "Martinique", GbifRegion.LATIN_AMERICA),

  /**
   * Mauritania.
   */
  MAURITANIA("MR", "MRT", 478, "Mauritania", GbifRegion.AFRICA),

  /**
   * Mauritius.
   */
  MAURITIUS("MU", "MUS", 480, "Mauritius", GbifRegion.AFRICA),

  /**
   * Mayotte.
   */
  MAYOTTE("YT", "MYT", 175, "Mayotte", GbifRegion.AFRICA),

  /**
   * Mexico.
   */
  MEXICO("MX", "MEX", 484, "Mexico", GbifRegion.LATIN_AMERICA),

  /**
   * Micronesia, Federated States of.
   */
  MICRONESIA("FM", "FSM", 583, "Micronesia, Federated States of", GbifRegion.OCEANIA),

  /**
   * Moldova, Republic of.
   */
  MOLDOVA("MD", "MDA", 498, "Moldova, Republic of", GbifRegion.EUROPE),

  /**
   * Monaco.
   */
  MONACO("MC", "MCO", 492, "Monaco", GbifRegion.EUROPE),

  /**
   * Mongolia.
   */
  MONGOLIA("MN", "MNG", 496, "Mongolia", GbifRegion.ASIA),

  /**
   * Montenegro.
   */
  MONTENEGRO("ME", "MNE", 499, "Montenegro", GbifRegion.EUROPE),

  /**
   * Montserrat.
   */
  MONTSERRAT("MS", "MSR", 500, "Montserrat", GbifRegion.LATIN_AMERICA),

  /**
   * Morocco.
   */
  MOROCCO("MA", "MAR", 504, "Morocco", GbifRegion.AFRICA),

  /**
   * Mozambique.
   */
  MOZAMBIQUE("MZ", "MOZ", 508, "Mozambique", GbifRegion.AFRICA),

  /**
   * Myanmar.
   */
  MYANMAR("MM", "MMR", 104, "Myanmar", GbifRegion.ASIA),

  /**
   * Namibia.
   */
  NAMIBIA("NA", "NAM", 516, "Namibia", GbifRegion.AFRICA),

  /**
   * Nauru.
   */
  NAURU("NR", "NRU", 520, "Nauru", GbifRegion.OCEANIA),

  /**
   * Nepal.
   */
  NEPAL("NP", "NPL", 524, "Nepal", GbifRegion.ASIA),

  /**
   * Netherlands.
   */
  NETHERLANDS("NL", "NLD", 528, "Netherlands", GbifRegion.EUROPE),

  /**
   * New Caledonia.
   */
  NEW_CALEDONIA("NC", "NCL", 540, "New Caledonia", GbifRegion.OCEANIA),

  /**
   * New Zealand.
   */
  NEW_ZEALAND("NZ", "NZL", 554, "New Zealand", GbifRegion.OCEANIA),

  /**
   * Nicaragua.
   */
  NICARAGUA("NI", "NIC", 558, "Nicaragua", GbifRegion.LATIN_AMERICA),

  /**
   * Niger.
   */
  NIGER("NE", "NER", 562, "Niger", GbifRegion.AFRICA),

  /**
   * Nigeria.
   */
  NIGERIA("NG", "NGA", 566, "Nigeria", GbifRegion.AFRICA),

  /**
   * Niue.
   */
  NIUE("NU", "NIU", 570, "Niue", GbifRegion.OCEANIA),

  /**
   * Norfolk Island.
   */
  NORFOLK_ISLAND("NF", "NFK", 574, "Norfolk Island", GbifRegion.OCEANIA),

  /**
   * Northern Mariana Islands.
   */
  NORTHERN_MARIANA_ISLANDS("MP", "MNP", 580, "Northern Mariana Islands", GbifRegion.OCEANIA),

  /**
   * Norway.
   */
  NORWAY("NO", "NOR", 578, "Norway", GbifRegion.EUROPE),

  /**
   * Oman.
   */
  OMAN("OM", "OMN", 512, "Oman", GbifRegion.ASIA),

  /**
   * Pakistan.
   */
  PAKISTAN("PK", "PAK", 586, "Pakistan", GbifRegion.ASIA),

  /**
   * Palau.
   */
  PALAU("PW", "PLW", 585, "Palau", GbifRegion.OCEANIA),

  /**
   * Palestine, State Of.
   */
  PALESTINIAN_TERRITORY("PS", "PSE", 275, "Palestine, State Of", GbifRegion.ASIA),

  /**
   * Panama.
   */
  PANAMA("PA", "PAN", 591, "Panama", GbifRegion.LATIN_AMERICA),

  /**
   * Papua New Guinea.
   */
  PAPUA_NEW_GUINEA("PG", "PNG", 598, "Papua New Guinea", GbifRegion.OCEANIA),

  /**
   * Paraguay.
   */
  PARAGUAY("PY", "PRY", 600, "Paraguay", GbifRegion.LATIN_AMERICA),

  /**
   * Peru.
   */
  PERU("PE", "PER", 604, "Peru", GbifRegion.LATIN_AMERICA),

  /**
   * Philippines.
   */
  PHILIPPINES("PH", "PHL", 608, "Philippines", GbifRegion.ASIA),

  /**
   * Pitcairn.
   */
  PITCAIRN("PN", "PCN", 612, "Pitcairn", GbifRegion.OCEANIA),

  /**
   * Poland.
   */
  POLAND("PL", "POL", 616, "Poland", GbifRegion.EUROPE),

  /**
   * Portugal.
   */
  PORTUGAL("PT", "PRT", 620, "Portugal", GbifRegion.EUROPE),

  /**
   * Puerto Rico.
   */
  PUERTO_RICO("PR", "PRI", 630, "Puerto Rico", GbifRegion.LATIN_AMERICA),

  /**
   * Qatar.
   */
  QATAR("QA", "QAT", 634, "Qatar", GbifRegion.ASIA),

  /**
   * Réunion.
   */
  RÉUNION("RE", "REU", 638, "Réunion", GbifRegion.AFRICA),

  /**
   * Romania.
   */
  ROMANIA("RO", "ROU", 642, "Romania", GbifRegion.EUROPE),

  /**
   * Russian Federation.
   */
  RUSSIAN_FEDERATION("RU", "RUS", 643, "Russian Federation", GbifRegion.EUROPE),

  /**
   * Rwanda.
   */
  RWANDA("RW", "RWA", 646, "Rwanda", GbifRegion.AFRICA),

  /**
   * Saint Barthélemy.
   */
  SAINT_BARTHÉLEMY("BL", "BLM", 652, "Saint Barthélemy", GbifRegion.LATIN_AMERICA),

  /**
   * Saint Helena, Ascension and Tristan da Cunha.
   */
  SAINT_HELENA_ASCENSION_TRISTAN_DA_CUNHA("SH", "SHN", 654, "Saint Helena, Ascension and Tristan da Cunha", GbifRegion.AFRICA),

  /**
   * Saint Kitts and Nevis.
   */
  SAINT_KITTS_NEVIS("KN", "KNA", 659, "Saint Kitts and Nevis", GbifRegion.LATIN_AMERICA),

  /**
   * Saint Lucia.
   */
  SAINT_LUCIA("LC", "LCA", 662, "Saint Lucia", GbifRegion.LATIN_AMERICA),

  /**
   * SAINT MARTIN (French part).
   */
  SAINT_MARTIN_FRENCH("MF", "MAF", 663, "Saint Martin (French part)", GbifRegion.LATIN_AMERICA),

  /**
   * Saint Pierre and Miquelon.
   */
  SAINT_PIERRE_MIQUELON("PM", "SPM", 666, "Saint Pierre and Miquelon", GbifRegion.NORTH_AMERICA),

  /**
   * Saint Vincent and the Grenadines.
   */
  SAINT_VINCENT_GRENADINES("VC", "VCT", 670, "Saint Vincent and the Grenadines", GbifRegion.LATIN_AMERICA),

  /**
   * Samoa.
   */
  SAMOA("WS", "WSM", 882, "Samoa", GbifRegion.OCEANIA),

  /**
   * San Marino.
   */
  SAN_MARINO("SM", "SMR", 674, "San Marino", GbifRegion.EUROPE),

  /**
   * Sao Tome and Principe.
   */
  SAO_TOME_PRINCIPE("ST", "STP", 678, "Sao Tome and Principe", GbifRegion.AFRICA),

  /**
   * Saudi Arabia.
   */
  SAUDI_ARABIA("SA", "SAU", 682, "Saudi Arabia", GbifRegion.ASIA),

  /**
   * Senegal.
   */
  SENEGAL("SN", "SEN", 686, "Senegal", GbifRegion.AFRICA),

  /**
   * Serbia.
   */
  SERBIA("RS", "SRB", 688, "Serbia", GbifRegion.EUROPE),

  /**
   * Seychelles.
   */
  SEYCHELLES("SC", "SYC", 690, "Seychelles", GbifRegion.AFRICA),

  /**
   * Sierra Leone.
   */
  SIERRA_LEONE("SL", "SLE", 694, "Sierra Leone", GbifRegion.AFRICA),

  /**
   * Singapore.
   */
  SINGAPORE("SG", "SGP", 702, "Singapore", GbifRegion.ASIA),

  /**
   * SINT MAARTEN (Dutch part).
   */
  SINT_MAARTEN("SX", "SXM", 534, "Sint Maarten (Dutch part)", GbifRegion.LATIN_AMERICA),

  /**
   * Slovakia.
   */
  SLOVAKIA("SK", "SVK", 703, "Slovakia", GbifRegion.EUROPE),

  /**
   * Slovenia.
   */
  SLOVENIA("SI", "SVN", 705, "Slovenia", GbifRegion.EUROPE),

  /**
   * Solomon Islands.
   */
  SOLOMON_ISLANDS("SB", "SLB", 90, "Solomon Islands", GbifRegion.OCEANIA),

  /**
   * Somalia.
   */
  SOMALIA("SO", "SOM", 706, "Somalia", GbifRegion.AFRICA),

  /**
   * South Africa.
   */
  SOUTH_AFRICA("ZA", "ZAF", 710, "South Africa", GbifRegion.AFRICA),

  /**
   * South Georgia and the South Sandwich Islands.
   */
  SOUTH_GEORGIA_SANDWICH_ISLANDS("GS", "SGS", 239, "South Georgia and the South Sandwich Islands", GbifRegion.ANTARCTICA),

  /**
   * South Sudan.
   */
  SOUTH_SUDAN("SS", "SSD", 728, "South Sudan", GbifRegion.AFRICA),

  /**
   * Spain.
   */
  SPAIN("ES", "ESP", 724, "Spain", GbifRegion.EUROPE),

  /**
   * Sri Lanka.
   */
  SRI_LANKA("LK", "LKA", 144, "Sri Lanka", GbifRegion.ASIA),

  /**
   * Sudan.
   */
  SUDAN("SD", "SDN", 729, "Sudan", GbifRegion.AFRICA),

  /**
   * Suriname.
   */
  SURINAME("SR", "SUR", 740, "Suriname", GbifRegion.LATIN_AMERICA),

  /**
   * Svalbard and Jan Mayen.
   */
  SVALBARD_JAN_MAYEN("SJ", "SJM", 744, "Svalbard and Jan Mayen", GbifRegion.EUROPE),

  /**
   * Swaziland.
   */
  SWAZILAND("SZ", "SWZ", 748, "Swaziland", GbifRegion.AFRICA),

  /**
   * Sweden.
   */
  SWEDEN("SE", "SWE", 752, "Sweden", GbifRegion.EUROPE),

  /**
   * Switzerland.
   */
  SWITZERLAND("CH", "CHE", 756, "Switzerland", GbifRegion.EUROPE),

  /**
   * Syrian Arab Republic.
   */
  SYRIA("SY", "SYR", 760, "Syrian Arab Republic", GbifRegion.ASIA),

  /**
   * Taiwan.
   */
  /*
   * The GBIF participant is called Chinese Taipei, but the location is still Taiwan.
   * https://github.com/gbif/portal-feedback/issues/686
   */
  TAIWAN("TW", "TWN", 158, "Taiwan", GbifRegion.ASIA),

  /**
   * Tajikistan.
   */
  TAJIKISTAN("TJ", "TJK", 762, "Tajikistan", GbifRegion.EUROPE),

  /**
   * Tanzania, United Republic of.
   */
  TANZANIA("TZ", "TZA", 834, "Tanzania, United Republic of", GbifRegion.AFRICA),

  /**
   * Thailand.
   */
  THAILAND("TH", "THA", 764, "Thailand", GbifRegion.ASIA),

  /**
   * Timor-Leste.
   */
  TIMOR_LESTE("TL", "TLS", 626, "Timor-Leste", GbifRegion.ASIA),

  /**
   * Togo.
   */
  TOGO("TG", "TGO", 768, "Togo", GbifRegion.AFRICA),

  /**
   * Tokelau.
   */
  TOKELAU("TK", "TKL", 772, "Tokelau", GbifRegion.OCEANIA),

  /**
   * Tonga.
   */
  TONGA("TO", "TON", 776, "Tonga", GbifRegion.OCEANIA),

  /**
   * Trinidad and Tobago.
   */
  TRINIDAD_TOBAGO("TT", "TTO", 780, "Trinidad and Tobago", GbifRegion.LATIN_AMERICA),

  /**
   * Tunisia.
   */
  TUNISIA("TN", "TUN", 788, "Tunisia", GbifRegion.AFRICA),

  /**
   * Turkey.
   */
  TURKEY("TR", "TUR", 792, "Turkey", GbifRegion.EUROPE),

  /**
   * Turkmenistan.
   */
  TURKMENISTAN("TM", "TKM", 795, "Turkmenistan", GbifRegion.EUROPE),

  /**
   * Turks and Caicos Islands.
   */
  TURKS_CAICOS_ISLANDS("TC", "TCA", 796, "Turks and Caicos Islands", GbifRegion.LATIN_AMERICA),

  /**
   * Tuvalu.
   */
  TUVALU("TV", "TUV", 798, "Tuvalu", GbifRegion.OCEANIA),

  /**
   * Uganda.
   */
  UGANDA("UG", "UGA", 800, "Uganda", GbifRegion.AFRICA),

  /**
   * Ukraine.
   */
  UKRAINE("UA", "UKR", 804, "Ukraine", GbifRegion.EUROPE),

  /**
   * United Arab Emirates.
   */
  UNITED_ARAB_EMIRATES("AE", "ARE", 784, "United Arab Emirates", GbifRegion.ASIA),

  /**
   * United Kingdom.
   */
  UNITED_KINGDOM("GB", "GBR", 826, "United Kingdom", GbifRegion.EUROPE),

  /**
   * United States.
   */
  UNITED_STATES("US", "USA", 840, "United States", GbifRegion.NORTH_AMERICA),

  /**
   * United States Minor Outlying Islands.
   */
  UNITED_STATES_OUTLYING_ISLANDS("UM", "UMI", 581, "United States Minor Outlying Islands", GbifRegion.OCEANIA),

  /**
   * Uruguay.
   */
  URUGUAY("UY", "URY", 858, "Uruguay", GbifRegion.LATIN_AMERICA),

  /**
   * Uzbekistan.
   */
  UZBEKISTAN("UZ", "UZB", 860, "Uzbekistan", GbifRegion.EUROPE),

  /**
   * Vanuatu.
   */
  VANUATU("VU", "VUT", 548, "Vanuatu", GbifRegion.OCEANIA),

  /**
   * Venezuela, Bolivarian Republic of.
   */
  VENEZUELA("VE", "VEN", 862, "Venezuela, Bolivarian Republic of", GbifRegion.LATIN_AMERICA),

  /**
   * Viet Nam.
   */
  VIETNAM("VN", "VNM", 704, "Viet Nam", GbifRegion.ASIA),

  /**
   * Virgin Islands, British.
   */
  VIRGIN_ISLANDS_BRITISH("VG", "VGB", 92, "Virgin Islands, British", GbifRegion.LATIN_AMERICA),

  /**
   * Virgin Islands, U.S..
   */
  VIRGIN_ISLANDS("VI", "VIR", 850, "Virgin Islands, U.S.", GbifRegion.LATIN_AMERICA),

  /**
   * Wallis and Futuna.
   */
  WALLIS_FUTUNA("WF", "WLF", 876, "Wallis and Futuna", GbifRegion.OCEANIA),

  /**
   * Western Sahara.
   */
  WESTERN_SAHARA("EH", "ESH", 732, "Western Sahara", GbifRegion.AFRICA),

  /**
   * Yemen.
   */
  YEMEN("YE", "YEM", 887, "Yemen", GbifRegion.ASIA),

  /**
   * Zambia.
   */
  ZAMBIA("ZM", "ZMB", 894, "Zambia", GbifRegion.AFRICA),

  /**
   * Zimbabwe.
   */
  ZIMBABWE("ZW", "ZWE", 716, "Zimbabwe", GbifRegion.AFRICA),

  /**
   * Bucket for all user defined codes not managed by GBIF.
   * User-assigned code elements are codes at the disposal of users who need to add further names
   * of countries, territories, or other geographical entities to their in-house application of ISO 3166-1,
   * and the ISO 3166/MA will never use these codes in the updating process of the standard.
   * The following codes can be user-assigned:
   * Alpha-2: AA, QM to QZ, XA to XZ, and ZZ
   * Alpha-3: AAA to AAZ, QMA to QZZ, XAA to XZZ, and ZZA to ZZZ
   * Numeric: 900 to 999
   */
  USER_DEFINED("AA", "AAA", 900, "user defined"),

  /**
   * Kosovo.
   * User-assigned temporary code, XK and XKX are the same as used by several other international organizations.
   * 902 is assigned by GBIF, but these codes aren't used anywhere.
   */
  KOSOVO("XK", "XKX", 902, "Kosovo", GbifRegion.EUROPE),

  /**
   * @see <a href="http://en.wikipedia.org/wiki/UN/LOCODE">UN/LOCODE</a>
   */
  INTERNATIONAL_WATERS("XZ", "XZZ", 901, "international waters"),

  /**
   * Unknown or Invalid territory.
   *
   * @see <a href="http://en.wikipedia.org/wiki/Common_Locale_Data_Repository">Unicode Common Locale Data Repository</a>
   */
  UNKNOWN("ZZ", "ZZZ", 999, "unknown or invalid");

  /**
   * A set of all 2 and 3 letter codes that are reserved by ISO for custom application specific usages.
   * The following codes can be user-assigned:
   * Alpha-2: AA, QM to QZ, XA to XZ, and ZZ
   * Alpha-3: AAA to AAZ, QMA to QZZ, XAA to XZZ, and ZZA to ZZZ
   */
  public static final Set<String> CUSTOM_CODES;
  public static final List<Country> OFFICIAL_COUNTRIES;

  private final String alpha2;
  private final String alpha3;
  private final int numericalCode;
  private final String title;
  private final GbifRegion gbifRegion;

  static {
    List<Country> officials = Lists.newArrayList();
    for (Country c : Country.values()) {
      if (c.isOfficial()) {
        officials.add(c);
      }
    }
    OFFICIAL_COUNTRIES = ImmutableList.copyOf(officials);

    Set<String> custom = Sets.newHashSet("AA", "ZZ");
    // QM-QZ
    for (char c = 'M'; c <= 'Z'; c++) {
      custom.add("Q" + c);
    }
    // AAA-AAZ, ZZA-ZZZ
    for (char c = 'A'; c <= 'Z'; c++) {
      custom.add("AA" + c);
      custom.add("ZZ" + c);
    }
    // QMA-QZZ
    for (char c = 'M'; c <= 'Z'; c++) {
      for (char c2 = 'A'; c2 <= 'Z'; c2++) {
        custom.add("Q" + c + c2);
      }
    }
    // XA-XZ, XAA to XZZ
    for (char c = 'A'; c <= 'Z'; c++) {
      custom.add("X" + c);
      for (char c2 = 'A'; c2 <= 'Z'; c2++) {
        custom.add("X" + c + c2);
      }
    }
    CUSTOM_CODES = ImmutableSet.copyOf(custom);
  }

  public static boolean isCustomCode(String code) {
    return code != null && CUSTOM_CODES.contains(code.toUpperCase());
  }

  /**
   * @param code the case insensitive 2 or 3 letter codes
   * @return the matching country or null
   */
  public static Country fromIsoCode(String code) {
    if (!Strings.isNullOrEmpty(code)) {
      String codeUpper = code.toUpperCase().trim();
      for (Country c : Country.values()) {
        if (codeUpper.equals(c.getIso2LetterCode()) || codeUpper.equals(c.getIso3LetterCode())) {
          return c;
        }
      }
    }
    return null;
  }

  /**
   * Temporary constructor to keep the code stable until https://github.com/gbif/gbif-api/issues/6 is fully
   * implemented.
   *
   * @param alpha2
   * @param alpha3
   * @param numericalCode
   * @param title
   */
  Country(String alpha2, String alpha3, int numericalCode, String title) {
    this(alpha2, alpha3, numericalCode, title, null);
  }

  /**
   * @param alpha2
   * @param alpha3
   * @param numericalCode
   * @param title
   * @param gbifRegion    GBIF region is NOT a geographic region. Participant countries make an active decision which
   *                      GBIF region they want to belong to.
   */
  Country(String alpha2, String alpha3, int numericalCode, String title, GbifRegion gbifRegion) {
    this.alpha2 = alpha2;
    this.alpha3 = alpha3;
    this.numericalCode = numericalCode;
    this.title = title;
    this.gbifRegion = gbifRegion;
  }

  /**
   * @return the country name in the English language as maintained by ISO.
   */
  public String getTitle() {
    return title;
  }

  /**
   * @return the 2 letter ISO 3166-1 ALPHA2 code in upper case.
   */
  public String getIso2LetterCode() {
    return alpha2;
  }

  /**
   * @return the 3 letter ISO 3166-1 ALPHA3 code in upper case.
   */
  public String getIso3LetterCode() {
    return alpha3;
  }

  /**
   * @return the numerical ISO 3166-1 code.
   */
  public Integer getIsoNumericalCode() {
    return numericalCode;
  }

  /**
   * Get the {@link GbifRegion} associated with this {@link Country}.
   *
   * @return
   */
  public GbifRegion getGbifRegion() {
    return gbifRegion;
  }

  /**
   * @return true if its a non user defined, current ISO 3166-1 alpha2 code.
   */
  public boolean isOfficial() {
    return !(this == UNKNOWN || this == USER_DEFINED || this == INTERNATIONAL_WATERS || this == KOSOVO);
  }

  /**
   * Serializes the value in a 2 letter ISO format.
   */
  public static class IsoSerializer extends SerializerBase<Country> {

    public IsoSerializer() {
      super(Country.class);
    }

    @Override
    public void serialize(Country value, JsonGenerator jgen, SerializerProvider provider) throws IOException,
      JsonGenerationException {
      jgen.writeString(value.alpha2);
    }

  }

  /**
   * Deserializes the value from a 2 letter ISO format.
   */
  public static class IsoDeserializer extends JsonDeserializer<Country> {

    @Override
    public Country deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
      try {
        if (jp != null && jp.getTextLength() > 0) {
          return Country.fromIsoCode(jp.getText());
        } else {
          return Country.UNKNOWN; // none provided
        }
      } catch (Exception e) {
        throw new IOException("Unable to deserialize country from provided value (not an ISO 2 character?): "
          + jp.getText());
      }
    }
  }

  /**
   * Serializes the value as the english country title.
   */
  public static class TitleSerializer extends SerializerBase<Country> {

    public TitleSerializer() {
      super(Country.class);
    }

    @Override
    public void serialize(Country value, JsonGenerator jgen, SerializerProvider provider) throws IOException {
      jgen.writeString(value.title);
    }

  }

  /**
   * Deserializes the value from an english country title exactly as given by the enumeration.
   */
  public static class TitleDeserializer extends JsonDeserializer<Country> {
    private static Map<String, Country> TITLE_LOOKUP = Maps.uniqueIndex(Lists.newArrayList(Country.values()),
                                                                       new Function<Country, String>() {
      @Nullable
      @Override
      public String apply(@Nullable Country c) {
        return c.title;
      }
    });

    @Override
    public Country deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException {
      try {
        return TITLE_LOOKUP.get(jp.getText());
      } catch (Exception e) {
        throw new IOException("Unable to deserialize country from provided title : " + jp.getText());
      }
    }
  }
}
