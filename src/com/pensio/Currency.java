package com.pensio;

public enum Currency
{
    ALL (8,"Lek")
    , DZD (12,"Algerian Dinar")
    , ARS (32,"Argentine Peso")
    , AUD (36,"Australian Dollar")
    , BSD (44,"Bahamian Dollar")
    , BHD (48,"Bahraini Dinar", 3)
    , BDT (50,"Taka")
    , AMD (51,"Armenian Dram")
    , BBD (52,"Barbados Dollar")
    , BMD (60,"Bermudian Dollar (customarily known as Bermuda Dollar)")
    , BTN (64,"Ngultrum")
    , BOB (68,"Boliviano")
    , BWP (72,"Pula")
    , BZD (84,"Belize Dollar")
    , SBD (90,"Solomon Islands Dollar")
    , BND (96,"Brunei Dollar")
    , MMK (104,"Kyat", 0)
    , BIF (108,"Burundi Franc", 0)
    , KHR (116,"Riel",0)
    , CAD (124,"Canadian Dollar")
    , CVE (132,"Cape Verde Escudo",0)
    , KYD (136,"Cayman Islands Dollar")
    , LKR (144,"Sri Lanka Rupee")
    , CLP (152,"Chilean Peso",0)
    , CNY (156,"Yuan Renminbi")
    , COP (170,"Colombian Peso")
    , KMF (174,"Comoro Franc",0)
    , CRC (188,"Costa Rican Colon")
    , HRK (191,"Croatian Kuna")
    , CUP (192,"Cuban Peso")
    , CZK (203,"Czech Koruna")
    , DKK (208,"Danish Krone")
    , DOP (214,"Dominican Peso")
    , SVC (222,"El Salvador Colon")
    , ETB (230,"Ethiopian Birr")
    , ERN (232,"Nakfa")
    , EEK (233,"Kroon")
    , FKP (238,"Falkland Islands Pound")
    , FJD (242,"Fiji Dollar")
    , DJF (262,"Djibouti Franc",0)
    , GMD (270,"Dalasi")
    , GIP (292,"Gibraltar Pound")
    , GTQ (320,"Quetzal")
    , GNF (324,"Guinea Franc",0)
    , GYD (328,"Guyana Dollar")
    , HTG (332,"Gourde")
    , HNL (340,"Lempira")
    , HKD (344,"Hong Kong Dollar")
    , HUF (348,"Forint")
    , ISK (352,"Iceland Krona",0)
    , INR (356,"Indian Rupee")
    , IDR (360,"Rupiah",2)
    , IRR (364,"Iranian Rial",0)
    , IQD (368,"Iraqi Dinar",0)
    , ILS (376,"New Israeli Sheqel")
    , JMD (388,"Jamaican Dollar")
    , JPY (392,"Yen",0)
    , KZT (398,"Tenge")
    , JOD (400,"Jordanian Dinar",3)
    , KES (404,"Kenyan Shilling")
    , KPW (408,"North Korean Won",0)
    , KRW (410,"Won",0)
    , KWD (414,"Kuwaiti Dinar",3)
    , KGS (417,"Som")
    , LAK (418,"Kip",0)
    , LBP (422,"Lebanese Pound",0)
    , LSL (426,"Loti")
    , LVL (428,"Latvian Lats")
    , LRD (430,"Liberian Dollar")
    , LYD (434,"Libyan Dinar",3)
    , LTL (440,"Lithuanian Litas")
    , MOP (446,"Pataca",1)
    , MWK (454,"Kwacha")
    , MYR (458,"Malaysian Ringgit")
    , MVR (462,"Rufiyaa")
    //, MRO (478,"Ouguiya", 0.69897...) this currency is disabled as the decimal numbers is not dividable by 10
    , MUR (480,"Mauritius Rupee")
    , MXN (484,"Mexican Peso")
    , MNT (496,"Tugrik")
    , MDL (498,"Moldovan Leu")
    , MAD (504,"Moroccan Dirham")
    , OMR (512,"Rial Omani",3)
    , NAD (516,"Namibia Dollar")
    , NPR (524,"Nepalese Rupee")
    , ANG (532,"Netherlands Antillian Guilder")
    , AWG (533,"Aruban Guilder")
    , VUV (548,"Vatu",0)
    , NZD (554,"New Zealand Dollar")
    , NIO (558,"Cordoba Oro")
    , NGN (566,"Naira")
    , NOK (578,"Norwegian Krone")
    , PKR (586,"Pakistan Rupee")
    , PGK (598,"Kina")
    , PYG (600,"Guarani",0)
    , PEN (604,"Nuevo Sol")
    , PHP (608,"Philippine Peso")
    , GWP (624,"Guinea-Bissau Peso",0)
    , QAR (634,"Qatari Rial")
    , RUB (643,"Russian Ruble")
    , RWF (646,"Rwanda Franc",0)
    , SHP (654,"Saint Helena Pound")
    , STD (678,"Dobra",0)
    , SAR (682,"Saudi Riyal")
    , SCR (690,"Seychelles Rupee")
    , SLL (694,"Leone",0)
    , SGD (702,"Singapore Dollar")
    , SKK (703,"Slovak Koruna", 1)
    , VND (704,"Dong",0)
    , SOS (706,"Somali Shilling")
    , ZAR (710,"Rand")
    , SZL (748,"Lilangeni")
    , SEK (752,"Swedish Krona")
    , CHF (756,"Swiss Franc")
    , SYP (760,"Syrian Pound")
    , THB (764,"Baht")
    , TOP (776,"Pa'anga")
    , TTD (780,"Trinidad and Tobago Dollar")
    , AED (784,"UAE Dirham")
    , TND (788,"Tunisian Dinar",3)
    , TMM (795,"Manat",0)
    , UGX (800,"Uganda Shilling",0)
    , MKD (807,"Denar")
    , EGP (818,"Egyptian Pound")
    , GBP (826,"Pound Sterling")
    , TZS (834,"Tanzanian Shilling")
    , USD (840,"US Dollar")
    , UYU (858,"Peso Uruguayo")
    , UZS (860,"Uzbekistan Sum")
    , WST (882,"Tala")
    , YER (886,"Yemeni Rial",0)
    , ZMK (894,"Zambian Kwacha",0)
    , TWD (901,"New Taiwan Dollar")
    , ZWR (935,"Zimbabwe Dollar",0)
    , GHS (936,"Cedi")
    , VEF (937,"Bolivar Fuerte")
    , SDG (938,"Sudanese Pound")
    , RSD (941,"Serbian Dinar")
    , MZN (943,"Metical")
    , AZN (944,"Azerbaijanian Manat")
    , RON (946,"New Leu")
    , CHE (947,"WIR Euro")
    , CHW (948,"WIR Franc")
    , TRY (949,"Turkish Lira")
    , XAF (950,"CFA Franc BEAC",0)
    , XCD (951,"East Caribbean Dollar")
    , XOF (952,"CFA Franc BCEAO",0)
    , XPF (953,"CFP Franc",0)
    , XBA (955,"Bond Markets Units European Composite Unit (EURCO)",0)
    , XBB (956,"European Monetary Unit (E.M.U.-6)",0)
    , XBC (957,"European Unit of Account 9(E.U.A.-9)",0)
    , XBD (958,"European Unit of Account 17(E.U.A.-17)",0)
    , XAU (959,"Gold",0)
    , XDR (960,"SDR",0)
    , XAG (961,"Silver",0)
    , XPT (962,"Platinum",0)
    , XTS (963,"Codes specifically reserved for testing purposes",0)
    , XPD (964,"Palladium",0)
    , SRD (968,"Surinam Dollar")
    //, MGA (969,"Malagasy Ariary",0.69897...)this currency is disabled as the decimal numbers is not dividable by 10
    , COU (970,"Unidad de Valor Real")
    , AFN (971,"Afghani")
    , TJS (972,"Somoni")
    , AOA (973,"Kwanza")
    , BYR (974,"Belarussian Ruble",0)
    , BGN (975,"Bulgarian Lev")
    , CDF (976,"Congolese Franc")
    , BAM (977,"Convertible Marks")
    , EUR (978,"Euro")
    , MXV (979,"Mexican Unidad de Inversion (UDI)")
    , UAH (980,"Hryvnia")
    , GEL (981,"Lari")
    , BOV (984,"Mvdol")
    , PLN (985,"Zloty")
    , BRL (986,"Brazilian Real")
    , CLF (990,"Unidades de fomento",0)
    , USN (997,"US Dollar (Next day)",2)
    , USS (998,"US Dollar (Same day)",2)
    , XXX (999,"The code assigned for transactions where no currency is involved",0);

    private final int numericValue;
    private final String currencyName;
    private int decimals;

    private Currency(int numericValue, String name)
    {
        this.numericValue = numericValue;
        this.currencyName = name;
        this.decimals = 2;
    }

    private Currency(int numericValue, String name, int decimals)
    {
        this.numericValue = numericValue;
        this.currencyName = name;
        this.decimals = decimals;
    }

    public int getNumeric()
    {
        return numericValue;
    }

    public int getDecimals()
    {
        return decimals;
    }

    public String getNumericString()
    {
        return String.format("%03d", numericValue);
    }

    public String getName()
    {
        return currencyName;
    }

    public static Currency fromNumeric(int numericValue)
    {
        for(Currency c : Currency.values())
        {
            if(c.numericValue == numericValue)
                return c;
        }

        throw new UnknownCurrencyException(numericValue);
    }

    public static Currency fromString(String stringValue)
    {
        for(Currency c : Currency.values())
        {
            if(c.name().equals(stringValue))
                return c;
        }

        throw new UnknownCurrencyException(stringValue);
    }
}
