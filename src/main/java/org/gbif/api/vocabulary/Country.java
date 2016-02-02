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
 * Enumeration for all current ISO 3166-1 ALPHA2 country codes using 2 letters, with the exception of PS and TW which
 * are overridden by GBIF.
 * Older country codes will be supported soon, @see #isDeprecated().
 * All user assigned codes (e.g. XX and QS) are mapped to the single enum USER_DEFINED.
 * The enumeration maps to ALPHA3 3-letter codes.
 *
 * @see <a href="https://www.iso.org/obp/ui/#home">ISO Online Browsing Platform</a>
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
  AFGHANISTAN("AF", "AFG", 4, "Afghanistan"),

  /**
   * Åland Islands.
   */
  ALAND_ISLANDS("AX", "ALA", 248, "Åland Islands"),

  /**
   * Albania.
   */
  ALBANIA("AL", "ALB", 8, "Albania"),

  /**
   * Algeria.
   */
  ALGERIA("DZ", "DZA", 12, "Algeria"),

  /**
   * American Samoa.
   */
  AMERICAN_SAMOA("AS", "ASM", 16, "American Samoa"),

  /**
   * Andorra.
   */
  ANDORRA("AD", "AND", 20, "Andorra"),

  /**
   * Angola.
   */
  ANGOLA("AO", "AGO", 24, "Angola"),

  /**
   * Anguilla.
   */
  ANGUILLA("AI", "AIA", 660, "Anguilla"),

  /**
   * Antarctica.
   */
  ANTARCTICA("AQ", "ATA", 10, "Antarctica"),

  /**
   * Antigua and Barbuda.
   */
  ANTIGUA_BARBUDA("AG", "ATG", 28, "Antigua and Barbuda"),

  /**
   * Argentina.
   */
  ARGENTINA("AR", "ARG", 32, "Argentina"),

  /**
   * Armenia.
   */
  ARMENIA("AM", "ARM", 51, "Armenia"),

  /**
   * Aruba.
   */
  ARUBA("AW", "ABW", 533, "Aruba"),

  /**
   * Australia.
   */
  AUSTRALIA("AU", "AUS", 36, "Australia"),

  /**
   * Austria.
   */
  AUSTRIA("AT", "AUT", 40, "Austria"),

  /**
   * Azerbaijan.
   */
  AZERBAIJAN("AZ", "AZE", 31, "Azerbaijan"),

  /**
   * Bahamas.
   */
  BAHAMAS("BS", "BHS", 44, "Bahamas"),

  /**
   * Bahrain.
   */
  BAHRAIN("BH", "BHR", 48, "Bahrain"),

  /**
   * Bangladesh.
   */
  BANGLADESH("BD", "BGD", 50, "Bangladesh"),

  /**
   * Barbados.
   */
  BARBADOS("BB", "BRB", 52, "Barbados"),

  /**
   * Belarus.
   */
  BELARUS("BY", "BLR", 112, "Belarus"),

  /**
   * Belgium.
   */
  BELGIUM("BE", "BEL", 56, "Belgium"),

  /**
   * Belize.
   */
  BELIZE("BZ", "BLZ", 84, "Belize"),

  /**
   * Benin.
   */
  BENIN("BJ", "BEN", 204, "Benin"),

  /**
   * Bermuda.
   */
  BERMUDA("BM", "BMU", 60, "Bermuda"),

  /**
   * Bhutan.
   */
  BHUTAN("BT", "BTN", 64, "Bhutan"),

  /**
   * Bolivia, Plurinational State of.
   */
  BOLIVIA("BO", "BOL", 68, "Bolivia, Plurinational State of"),

  /**
   * Bonaire, Sint Eustatius and Saba.
   */
  BONAIRE_SINT_EUSTATIUS_SABA("BQ", "BES", 535, "Bonaire, Sint Eustatius and Saba"),

  /**
   * Bosnia and Herzegovina.
   */
  BOSNIA_HERZEGOVINA("BA", "BIH", 70, "Bosnia and Herzegovina"),

  /**
   * Botswana.
   */
  BOTSWANA("BW", "BWA", 72, "Botswana"),

  /**
   * Bouvet Island.
   */
  BOUVET_ISLAND("BV", "BVT", 74, "Bouvet Island"),

  /**
   * Brazil.
   */
  BRAZIL("BR", "BRA", 76, "Brazil"),

  /**
   * British Indian Ocean Territory.
   */
  BRITISH_INDIAN_OCEAN_TERRITORY("IO", "IOT", 86, "British Indian Ocean Territory"),

  /**
   * Brunei Darussalam.
   */
  BRUNEI_DARUSSALAM("BN", "BRN", 96, "Brunei Darussalam"),

  /**
   * Bulgaria.
   */
  BULGARIA("BG", "BGR", 100, "Bulgaria"),

  /**
   * Burkina Faso.
   */
  BURKINA_FASO("BF", "BFA", 854, "Burkina Faso"),

  /**
   * Burundi.
   */
  BURUNDI("BI", "BDI", 108, "Burundi"),

  /**
   * Cambodia.
   */
  CAMBODIA("KH", "KHM", 116, "Cambodia"),

  /**
   * Cameroon.
   */
  CAMEROON("CM", "CMR", 120, "Cameroon"),

  /**
   * Canada.
   */
  CANADA("CA", "CAN", 124, "Canada"),

  /**
   * Cape Verde.
   */
  CAPE_VERDE("CV", "CPV", 132, "Cape Verde"),

  /**
   * Cayman Islands.
   */
  CAYMAN_ISLANDS("KY", "CYM", 136, "Cayman Islands"),

  /**
   * Central African Republic.
   */
  CENTRAL_AFRICAN_REPUBLIC("CF", "CAF", 140, "Central African Republic"),

  /**
   * Chad.
   */
  CHAD("TD", "TCD", 148, "Chad"),

  /**
   * Chile.
   */
  CHILE("CL", "CHL", 152, "Chile"),

  /**
   * China.
   */
  CHINA("CN", "CHN", 156, "China"),

  /**
   * Christmas Island.
   */
  CHRISTMAS_ISLAND("CX", "CXR", 162, "Christmas Island"),

  /**
   * COCOS (Keeling) Islands.
   */
  COCOS_ISLANDS("CC", "CCK", 166, "Cocos (Keeling) Islands"),

  /**
   * Colombia.
   */
  COLOMBIA("CO", "COL", 170, "Colombia"),

  /**
   * Comoros.
   */
  COMOROS("KM", "COM", 174, "Comoros"),

  /**
   * Congo, the Democratic Republic of the.
   */
  CONGO_DEMOCRATIC_REPUBLIC("CD", "COD", 180, "Congo, Democratic Republic of the"),

  /**
   * Congo.
   */
  CONGO("CG", "COG", 178, "Congo, Republic of the"),

  /**
   * Cook Islands.
   */
  COOK_ISLANDS("CK", "COK", 184, "Cook Islands"),

  /**
   * Costa Rica.
   */
  COSTA_RICA("CR", "CRI", 188, "Costa Rica"),

  /**
   * Côte d'Ivoire.
   */
  CÔTE_DIVOIRE("CI", "CIV", 384, "Côte d'Ivoire"),

  /**
   * Croatia.
   */
  CROATIA("HR", "HRV", 191, "Croatia"),

  /**
   * Cuba.
   */
  CUBA("CU", "CUB", 192, "Cuba"),

  /**
   * Curaçao.
   */
  CURAÇAO("CW", "CUW", 531, "Curaçao"),

  /**
   * Cyprus.
   */
  CYPRUS("CY", "CYP", 196, "Cyprus"),

  /**
   * Czech Republic.
   */
  CZECH_REPUBLIC("CZ", "CZE", 203, "Czech Republic"),

  /**
   * Denmark.
   */
  DENMARK("DK", "DNK", 208, "Denmark"),

  /**
   * Djibouti.
   */
  DJIBOUTI("DJ", "DJI", 262, "Djibouti"),

  /**
   * Dominica.
   */
  DOMINICA("DM", "DMA", 212, "Dominica"),

  /**
   * Dominican Republic.
   */
  DOMINICAN_REPUBLIC("DO", "DOM", 214, "Dominican Republic"),

  /**
   * Ecuador.
   */
  ECUADOR("EC", "ECU", 218, "Ecuador"),

  /**
   * Egypt.
   */
  EGYPT("EG", "EGY", 818, "Egypt"),

  /**
   * El Salvador.
   */
  EL_SALVADOR("SV", "SLV", 222, "El Salvador"),

  /**
   * Equatorial Guinea.
   */
  EQUATORIAL_GUINEA("GQ", "GNQ", 226, "Equatorial Guinea"),

  /**
   * Eritrea.
   */
  ERITREA("ER", "ERI", 232, "Eritrea"),

  /**
   * Estonia.
   */
  ESTONIA("EE", "EST", 233, "Estonia"),

  /**
   * Ethiopia.
   */
  ETHIOPIA("ET", "ETH", 231, "Ethiopia"),

  /**
   * FALKLAND ISLANDS (Malvinas).
   */
  FALKLAND_ISLANDS("FK", "FLK", 238, "Falkland Islands (Malvinas)"),

  /**
   * Faroe Islands.
   */
  FAROE_ISLANDS("FO", "FRO", 234, "Faroe Islands"),

  /**
   * Fiji.
   */
  FIJI("FJ", "FJI", 242, "Fiji"),

  /**
   * Finland.
   */
  FINLAND("FI", "FIN", 246, "Finland"),

  /**
   * France.
   */
  FRANCE("FR", "FRA", 250, "France"),

  /**
   * French Guiana.
   */
  FRENCH_GUIANA("GF", "GUF", 254, "French Guiana"),

  /**
   * French Polynesia.
   */
  FRENCH_POLYNESIA("PF", "PYF", 258, "French Polynesia"),

  /**
   * French Southern Territories.
   */
  FRENCH_SOUTHERN_TERRITORIES("TF", "ATF", 260, "French Southern Territories"),

  /**
   * Gabon.
   */
  GABON("GA", "GAB", 266, "Gabon"),

  /**
   * Gambia.
   */
  GAMBIA("GM", "GMB", 270, "Gambia"),

  /**
   * Georgia.
   */
  GEORGIA("GE", "GEO", 268, "Georgia"),

  /**
   * Germany.
   */
  GERMANY("DE", "DEU", 276, "Germany"),

  /**
   * Ghana.
   */
  GHANA("GH", "GHA", 288, "Ghana"),

  /**
   * Gibraltar.
   */
  GIBRALTAR("GI", "GIB", 292, "Gibraltar"),

  /**
   * Greece.
   */
  GREECE("GR", "GRC", 300, "Greece"),

  /**
   * Greenland.
   */
  GREENLAND("GL", "GRL", 304, "Greenland"),

  /**
   * Grenada.
   */
  GRENADA("GD", "GRD", 308, "Grenada"),

  /**
   * Guadeloupe.
   */
  GUADELOUPE("GP", "GLP", 312, "Guadeloupe"),

  /**
   * Guam.
   */
  GUAM("GU", "GUM", 316, "Guam"),

  /**
   * Guatemala.
   */
  GUATEMALA("GT", "GTM", 320, "Guatemala"),

  /**
   * Guernsey.
   */
  GUERNSEY("GG", "GGY", 831, "Guernsey"),

  /**
   * Guinea.
   */
  GUINEA("GN", "GIN", 324, "Guinea"),

  /**
   * Guinea-Bissau.
   */
  GUINEA_BISSAU("GW", "GNB", 624, "Guinea-Bissau"),

  /**
   * Guyana.
   */
  GUYANA("GY", "GUY", 328, "Guyana"),

  /**
   * Haiti.
   */
  HAITI("HT", "HTI", 332, "Haiti"),

  /**
   * Heard Island and McDonald Islands.
   */
  HEARD_MCDONALD_ISLANDS("HM", "HMD", 334, "Heard Island and McDonald Islands"),

  /**
   * HOLY SEE (Vatican City State).
   */
  VATICAN("VA", "VAT", 336, "Holy See (Vatican City State)"),

  /**
   * Honduras.
   */
  HONDURAS("HN", "HND", 340, "Honduras"),

  /**
   * Hong Kong.
   */
  HONG_KONG("HK", "HKG", 344, "Hong Kong"),

  /**
   * Hungary.
   */
  HUNGARY("HU", "HUN", 348, "Hungary"),

  /**
   * Iceland.
   */
  ICELAND("IS", "ISL", 352, "Iceland"),

  /**
   * India.
   */
  INDIA("IN", "IND", 356, "India"),

  /**
   * Indonesia.
   */
  INDONESIA("ID", "IDN", 360, "Indonesia"),

  /**
   * Iran, Islamic Republic of.
   */
  IRAN("IR", "IRN", 364, "Iran, Islamic Republic of"),

  /**
   * Iraq.
   */
  IRAQ("IQ", "IRQ", 368, "Iraq"),

  /**
   * Ireland.
   */
  IRELAND("IE", "IRL", 372, "Ireland"),

  /**
   * Isle of Man.
   */
  ISLE_OF_MAN("IM", "IMN", 833, "Isle of Man"),

  /**
   * Israel.
   */
  ISRAEL("IL", "ISR", 376, "Israel"),

  /**
   * Italy.
   */
  ITALY("IT", "ITA", 380, "Italy"),

  /**
   * Jamaica.
   */
  JAMAICA("JM", "JAM", 388, "Jamaica"),

  /**
   * Japan.
   */
  JAPAN("JP", "JPN", 392, "Japan"),

  /**
   * Jersey.
   */
  JERSEY("JE", "JEY", 832, "Jersey"),

  /**
   * Jordan.
   */
  JORDAN("JO", "JOR", 400, "Jordan"),

  /**
   * Kazakhstan.
   */
  KAZAKHSTAN("KZ", "KAZ", 398, "Kazakhstan"),

  /**
   * Kenya.
   */
  KENYA("KE", "KEN", 404, "Kenya"),

  /**
   * Kiribati.
   */
  KIRIBATI("KI", "KIR", 296, "Kiribati"),

  /**
   * Korea, Democratic People's Republic of.
   */
  KOREA_NORTH("KP", "PRK", 408, "Korea, Democratic People's Republic of"),

  /**
   * Korea, Republic of.
   */
  KOREA_SOUTH("KR", "KOR", 410, "Korea, Republic of"),

  /**
   * Kuwait.
   */
  KUWAIT("KW", "KWT", 414, "Kuwait"),

  /**
   * Kyrgyzstan.
   */
  KYRGYZSTAN("KG", "KGZ", 417, "Kyrgyzstan"),

  /**
   * Lao People's Democratic Republic.
   */
  LAO("LA", "LAO", 418, "Lao People's Democratic Republic"),

  /**
   * Latvia.
   */
  LATVIA("LV", "LVA", 428, "Latvia"),

  /**
   * Lebanon.
   */
  LEBANON("LB", "LBN", 422, "Lebanon"),

  /**
   * Lesotho.
   */
  LESOTHO("LS", "LSO", 426, "Lesotho"),

  /**
   * Liberia.
   */
  LIBERIA("LR", "LBR", 430, "Liberia"),

  /**
   * Libya.
   */
  LIBYA("LY", "LBY", 434, "Libya"),

  /**
   * Liechtenstein.
   */
  LIECHTENSTEIN("LI", "LIE", 438, "Liechtenstein"),

  /**
   * Lithuania.
   */
  LITHUANIA("LT", "LTU", 440, "Lithuania"),

  /**
   * Luxembourg.
   */
  LUXEMBOURG("LU", "LUX", 442, "Luxembourg"),

  /**
   * Macao.
   */
  MACAO("MO", "MAC", 446, "Macao"),

  /**
   * Macedonia, the former Yugoslav Republic of.
   */
  MACEDONIA("MK", "MKD", 807, "Macedonia, the former Yugoslav Republic of"),

  /**
   * Madagascar.
   */
  MADAGASCAR("MG", "MDG", 450, "Madagascar"),

  /**
   * Malawi.
   */
  MALAWI("MW", "MWI", 454, "Malawi"),

  /**
   * Malaysia.
   */
  MALAYSIA("MY", "MYS", 458, "Malaysia"),

  /**
   * Maldives.
   */
  MALDIVES("MV", "MDV", 462, "Maldives"),

  /**
   * Mali.
   */
  MALI("ML", "MLI", 466, "Mali"),

  /**
   * Malta.
   */
  MALTA("MT", "MLT", 470, "Malta"),

  /**
   * Marshall Islands.
   */
  MARSHALL_ISLANDS("MH", "MHL", 584, "Marshall Islands"),

  /**
   * Martinique.
   */
  MARTINIQUE("MQ", "MTQ", 474, "Martinique"),

  /**
   * Mauritania.
   */
  MAURITANIA("MR", "MRT", 478, "Mauritania"),

  /**
   * Mauritius.
   */
  MAURITIUS("MU", "MUS", 480, "Mauritius"),

  /**
   * Mayotte.
   */
  MAYOTTE("YT", "MYT", 175, "Mayotte"),

  /**
   * Mexico.
   */
  MEXICO("MX", "MEX", 484, "Mexico"),

  /**
   * Micronesia, Federated States of.
   */
  MICRONESIA("FM", "FSM", 583, "Micronesia, Federated States of"),

  /**
   * Moldova, Republic of.
   */
  MOLDOVA("MD", "MDA", 498, "Moldova, Republic of"),

  /**
   * Monaco.
   */
  MONACO("MC", "MCO", 492, "Monaco"),

  /**
   * Mongolia.
   */
  MONGOLIA("MN", "MNG", 496, "Mongolia"),

  /**
   * Montenegro.
   */
  MONTENEGRO("ME", "MNE", 499, "Montenegro"),

  /**
   * Montserrat.
   */
  MONTSERRAT("MS", "MSR", 500, "Montserrat"),

  /**
   * Morocco.
   */
  MOROCCO("MA", "MAR", 504, "Morocco"),

  /**
   * Mozambique.
   */
  MOZAMBIQUE("MZ", "MOZ", 508, "Mozambique"),

  /**
   * Myanmar.
   */
  MYANMAR("MM", "MMR", 104, "Myanmar"),

  /**
   * Namibia.
   */
  NAMIBIA("NA", "NAM", 516, "Namibia"),

  /**
   * Nauru.
   */
  NAURU("NR", "NRU", 520, "Nauru"),

  /**
   * Nepal.
   */
  NEPAL("NP", "NPL", 524, "Nepal"),

  /**
   * Netherlands.
   */
  NETHERLANDS("NL", "NLD", 528, "Netherlands"),

  /**
   * New Caledonia.
   */
  NEW_CALEDONIA("NC", "NCL", 540, "New Caledonia"),

  /**
   * New Zealand.
   */
  NEW_ZEALAND("NZ", "NZL", 554, "New Zealand"),

  /**
   * Nicaragua.
   */
  NICARAGUA("NI", "NIC", 558, "Nicaragua"),

  /**
   * Niger.
   */
  NIGER("NE", "NER", 562, "Niger"),

  /**
   * Nigeria.
   */
  NIGERIA("NG", "NGA", 566, "Nigeria"),

  /**
   * Niue.
   */
  NIUE("NU", "NIU", 570, "Niue"),

  /**
   * Norfolk Island.
   */
  NORFOLK_ISLAND("NF", "NFK", 574, "Norfolk Island"),

  /**
   * Northern Mariana Islands.
   */
  NORTHERN_MARIANA_ISLANDS("MP", "MNP", 580, "Northern Mariana Islands"),

  /**
   * Norway.
   */
  NORWAY("NO", "NOR", 578, "Norway"),

  /**
   * Oman.
   */
  OMAN("OM", "OMN", 512, "Oman"),

  /**
   * Pakistan.
   */
  PAKISTAN("PK", "PAK", 586, "Pakistan"),

  /**
   * Palau.
   */
  PALAU("PW", "PLW", 585, "Palau"),

  /**
   * Palestine, State Of.
   */
  PALESTINIAN_TERRITORY("PS", "PSE", 275, "Palestine, State Of"),

  /**
   * Panama.
   */
  PANAMA("PA", "PAN", 591, "Panama"),

  /**
   * Papua New Guinea.
   */
  PAPUA_NEW_GUINEA("PG", "PNG", 598, "Papua New Guinea"),

  /**
   * Paraguay.
   */
  PARAGUAY("PY", "PRY", 600, "Paraguay"),

  /**
   * Peru.
   */
  PERU("PE", "PER", 604, "Peru"),

  /**
   * Philippines.
   */
  PHILIPPINES("PH", "PHL", 608, "Philippines"),

  /**
   * Pitcairn.
   */
  PITCAIRN("PN", "PCN", 612, "Pitcairn"),

  /**
   * Poland.
   */
  POLAND("PL", "POL", 616, "Poland"),

  /**
   * Portugal.
   */
  PORTUGAL("PT", "PRT", 620, "Portugal"),

  /**
   * Puerto Rico.
   */
  PUERTO_RICO("PR", "PRI", 630, "Puerto Rico"),

  /**
   * Qatar.
   */
  QATAR("QA", "QAT", 634, "Qatar"),

  /**
   * Réunion.
   */
  RÉUNION("RE", "REU", 638, "Réunion"),

  /**
   * Romania.
   */
  ROMANIA("RO", "ROU", 642, "Romania"),

  /**
   * Russian Federation.
   */
  RUSSIAN_FEDERATION("RU", "RUS", 643, "Russian Federation"),

  /**
   * Rwanda.
   */
  RWANDA("RW", "RWA", 646, "Rwanda"),

  /**
   * Saint Barthélemy.
   */
  SAINT_BARTHÉLEMY("BL", "BLM", 652, "Saint Barthélemy"),

  /**
   * Saint Helena, Ascension and Tristan da Cunha.
   */
  SAINT_HELENA_ASCENSION_TRISTAN_DA_CUNHA("SH", "SHN", 654, "Saint Helena, Ascension and Tristan da Cunha"),

  /**
   * Saint Kitts and Nevis.
   */
  SAINT_KITTS_NEVIS("KN", "KNA", 659, "Saint Kitts and Nevis"),

  /**
   * Saint Lucia.
   */
  SAINT_LUCIA("LC", "LCA", 662, "Saint Lucia"),

  /**
   * SAINT MARTIN (French part).
   */
  SAINT_MARTIN_FRENCH("MF", "MAF", 663, "Saint Martin (French part)"),

  /**
   * Saint Pierre and Miquelon.
   */
  SAINT_PIERRE_MIQUELON("PM", "SPM", 666, "Saint Pierre and Miquelon"),

  /**
   * Saint Vincent and the Grenadines.
   */
  SAINT_VINCENT_GRENADINES("VC", "VCT", 670, "Saint Vincent and the Grenadines"),

  /**
   * Samoa.
   */
  SAMOA("WS", "WSM", 882, "Samoa"),

  /**
   * San Marino.
   */
  SAN_MARINO("SM", "SMR", 674, "San Marino"),

  /**
   * Sao Tome and Principe.
   */
  SAO_TOME_PRINCIPE("ST", "STP", 678, "Sao Tome and Principe"),

  /**
   * Saudi Arabia.
   */
  SAUDI_ARABIA("SA", "SAU", 682, "Saudi Arabia"),

  /**
   * Senegal.
   */
  SENEGAL("SN", "SEN", 686, "Senegal"),

  /**
   * Serbia.
   */
  SERBIA("RS", "SRB", 688, "Serbia"),

  /**
   * Seychelles.
   */
  SEYCHELLES("SC", "SYC", 690, "Seychelles"),

  /**
   * Sierra Leone.
   */
  SIERRA_LEONE("SL", "SLE", 694, "Sierra Leone"),

  /**
   * Singapore.
   */
  SINGAPORE("SG", "SGP", 702, "Singapore"),

  /**
   * SINT MAARTEN (Dutch part).
   */
  SINT_MAARTEN("SX", "SXM", 534, "Sint Maarten (Dutch part)"),

  /**
   * Slovakia.
   */
  SLOVAKIA("SK", "SVK", 703, "Slovakia"),

  /**
   * Slovenia.
   */
  SLOVENIA("SI", "SVN", 705, "Slovenia"),

  /**
   * Solomon Islands.
   */
  SOLOMON_ISLANDS("SB", "SLB", 90, "Solomon Islands"),

  /**
   * Somalia.
   */
  SOMALIA("SO", "SOM", 706, "Somalia"),

  /**
   * South Africa.
   */
  SOUTH_AFRICA("ZA", "ZAF", 710, "South Africa"),

  /**
   * South Georgia and the South Sandwich Islands.
   */
  SOUTH_GEORGIA_SANDWICH_ISLANDS("GS", "SGS", 239, "South Georgia and the South Sandwich Islands"),

  /**
   * South Sudan.
   */
  SOUTH_SUDAN("SS", "SSD", 728, "South Sudan"),

  /**
   * Spain.
   */
  SPAIN("ES", "ESP", 724, "Spain"),

  /**
   * Sri Lanka.
   */
  SRI_LANKA("LK", "LKA", 144, "Sri Lanka"),

  /**
   * Sudan.
   */
  SUDAN("SD", "SDN", 729, "Sudan"),

  /**
   * Suriname.
   */
  SURINAME("SR", "SUR", 740, "Suriname"),

  /**
   * Svalbard and Jan Mayen.
   */
  SVALBARD_JAN_MAYEN("SJ", "SJM", 744, "Svalbard and Jan Mayen"),

  /**
   * Swaziland.
   */
  SWAZILAND("SZ", "SWZ", 748, "Swaziland"),

  /**
   * Sweden.
   */
  SWEDEN("SE", "SWE", 752, "Sweden"),

  /**
   * Switzerland.
   */
  SWITZERLAND("CH", "CHE", 756, "Switzerland"),

  /**
   * Syrian Arab Republic.
   */
  SYRIA("SY", "SYR", 760, "Syrian Arab Republic"),

  /**
   * Chinese Taipei.
   */
  TAIWAN("TW", "TWN", 158, "Chinese Taipei"),

  /**
   * Tajikistan.
   */
  TAJIKISTAN("TJ", "TJK", 762, "Tajikistan"),

  /**
   * Tanzania, United Republic of.
   */
  TANZANIA("TZ", "TZA", 834, "Tanzania, United Republic of"),

  /**
   * Thailand.
   */
  THAILAND("TH", "THA", 764, "Thailand"),

  /**
   * Timor-Leste.
   */
  TIMOR_LESTE("TL", "TLS", 626, "Timor-Leste"),

  /**
   * Togo.
   */
  TOGO("TG", "TGO", 768, "Togo"),

  /**
   * Tokelau.
   */
  TOKELAU("TK", "TKL", 772, "Tokelau"),

  /**
   * Tonga.
   */
  TONGA("TO", "TON", 776, "Tonga"),

  /**
   * Trinidad and Tobago.
   */
  TRINIDAD_TOBAGO("TT", "TTO", 780, "Trinidad and Tobago"),

  /**
   * Tunisia.
   */
  TUNISIA("TN", "TUN", 788, "Tunisia"),

  /**
   * Turkey.
   */
  TURKEY("TR", "TUR", 792, "Turkey"),

  /**
   * Turkmenistan.
   */
  TURKMENISTAN("TM", "TKM", 795, "Turkmenistan"),

  /**
   * Turks and Caicos Islands.
   */
  TURKS_CAICOS_ISLANDS("TC", "TCA", 796, "Turks and Caicos Islands"),

  /**
   * Tuvalu.
   */
  TUVALU("TV", "TUV", 798, "Tuvalu"),

  /**
   * Uganda.
   */
  UGANDA("UG", "UGA", 800, "Uganda"),

  /**
   * Ukraine.
   */
  UKRAINE("UA", "UKR", 804, "Ukraine"),

  /**
   * United Arab Emirates.
   */
  UNITED_ARAB_EMIRATES("AE", "ARE", 784, "United Arab Emirates"),

  /**
   * United Kingdom.
   */
  UNITED_KINGDOM("GB", "GBR", 826, "United Kingdom"),

  /**
   * United States.
   */
  UNITED_STATES("US", "USA", 840, "United States"),

  /**
   * United States Minor Outlying Islands.
   */
  UNITED_STATES_OUTLYING_ISLANDS("UM", "UMI", 581, "United States Minor Outlying Islands"),

  /**
   * Uruguay.
   */
  URUGUAY("UY", "URY", 858, "Uruguay"),

  /**
   * Uzbekistan.
   */
  UZBEKISTAN("UZ", "UZB", 860, "Uzbekistan"),

  /**
   * Vanuatu.
   */
  VANUATU("VU", "VUT", 548, "Vanuatu"),

  /**
   * Venezuela, Bolivarian Republic of.
   */
  VENEZUELA("VE", "VEN", 862, "Venezuela, Bolivarian Republic of"),

  /**
   * Viet Nam.
   */
  VIETNAM("VN", "VNM", 704, "Viet Nam"),

  /**
   * Virgin Islands, British.
   */
  VIRGIN_ISLANDS_BRITISH("VG", "VGB", 92, "Virgin Islands, British"),

  /**
   * Virgin Islands, U.S..
   */
  VIRGIN_ISLANDS("VI", "VIR", 850, "Virgin Islands, U.S."),

  /**
   * Wallis and Futuna.
   */
  WALLIS_FUTUNA("WF", "WLF", 876, "Wallis and Futuna"),

  /**
   * Western Sahara.
   */
  WESTERN_SAHARA("EH", "ESH", 732, "Western Sahara"),

  /**
   * Yemen.
   */
  YEMEN("YE", "YEM", 887, "Yemen"),

  /**
   * Zambia.
   */
  ZAMBIA("ZM", "ZMB", 894, "Zambia"),

  /**
   * Zimbabwe.
   */
  ZIMBABWE("ZW", "ZWE", 716, "Zimbabwe"),

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
   * @see <a href="http://en.wikipedia.org/wiki/UN/LOCODE">UN/LOCODE</a>
   */
  INTERNATIONAL_WATERS("XZ", "XZZ", 901, "international waters"),

  /**
   * A multi-territory region containing Antarctica, Bouvet Island, the Cocos (Keeling) Islands, Christmas Island,
   * South Georgia and the South Sandwich Islands, Heard Island and McDonald Islands,
   * the British Indian Ocean Territory, the French Southern Territories, and the United States Minor Outlying
   * Islands).
   *
   * @see <a href="http://en.wikipedia.org/wiki/Common_Locale_Data_Repository">Unicode Common Locale Data Repository</a>
   */
  OCEANIA("QO", "QOO", 902, "Oceania"),


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

  Country(String alpha2, String alpha3, int numericalCode, String title) {
    this.alpha2 = alpha2;
    this.alpha3 = alpha3;
    this.numericalCode = numericalCode;
    this.title = title;
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
   * @return true if its a non user defined, current ISO 3166-1 alpha2 code.
   */
  public boolean isOfficial() {
    return !(this == UNKNOWN || this == USER_DEFINED || this == INTERNATIONAL_WATERS || this == OCEANIA);
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
