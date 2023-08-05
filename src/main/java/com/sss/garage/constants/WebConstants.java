package com.sss.garage.constants;

public class WebConstants {
    public static final String ELO_ENDPOINT = "/elo";
    public static final String DRIVER_ELO_ENDPOINT = ELO_ENDPOINT + "/driver/{driver}";
    public static final String ELO_CALCULATION_ENDPOINT = ELO_ENDPOINT + "/calculation";

    public static final String GAME_ENDPOINT = "/game";

    public static final String RACE_ENDPOINT = "/race";

    public static final String LEAGUE_ENDPOINT = "/league";

    public static final String DRIVER_ENDPOINT = "/driver";

    public static final String RACE_RESULT_ENDPOINT = "/raceresult";

    public static final String SPLIT_ENDPOINT = "/split";

    public static final String TRACK_ENDPOINT = "/track";

    public static final String ACC_SESSION_ENDPOINT = "/accsession";

    public static final String ACC_LAP_ENDPOINT = "/acclap";

    public static final String NON_ACCESSIBLE_PATH="/JEBAC_MONTREYA";
    public static final String AUTHORIZATION_HEADER_BEARER_PREFIX="Bearer ";
    public static final String AUTHENTICATION_TOKEN_CHANGED_HEADER_NAME="sss-auth-token-replacement";
    public static final String AUTHENTICATION_TOKEN_EXPIRY_HEADER_NAME="sss-auth-token-replacement-expiry";

    public static final String OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME = "oauth2_auth_request";
    public static final String REDIRECT_URI_PARAM_COOKIE_NAME = "redirect_uri";

    public static final String PARENT_RACE_NAME="Parent race";

    public static final String DEFAULT_CURRENT_PAGE = "0";
    public static final String DEFAULT_PAGE_SIZE = "10";
}
